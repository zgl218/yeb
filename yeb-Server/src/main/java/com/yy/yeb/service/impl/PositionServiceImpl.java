package com.yy.yeb.service.impl;

import com.yy.yeb.mapper.EmployeeMapper;
import com.yy.yeb.pojo.Joblevel;
import com.yy.yeb.pojo.Position;
import com.yy.yeb.mapper.PositionMapper;
import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

    @Resource
    private PositionMapper positionMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    LocalDateTime now;

    /**
     * 查询所有职位
     * @return
     */
    @Override
    public List<Position> selectPositionAll() {
        return positionMapper.selectPositionAll();
    }


    /**
     * 添加职位
     * @param position 职位信息
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean insertPosition(Position position) {
//        id不连续问题解决
        positionMapper.continuousKey();
//        判断并执行操作
        if (position == null) {
            return RespBean.error("请输入要添加的职位");
        } else {
            Position positionByName = positionMapper.getPositionByName(position.getName());
            if (positionByName != null) {
                return RespBean.error("此职位已存在！！！");
            } else {
//                设置添加时间
                now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
                position.setCreateDate(now);
//                执行添加操作
                int i1 = positionMapper.insertPosition(position);
                if (i1 == 1) {
                    return RespBean.success("此职位添加成功！");
                } else {
                    return RespBean.error("此职位添加失败！");
                }
            }
        }
    }

    /**
     * 根据id删除职位
     * @param id 职位ID
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean deletePositionById(Integer id) {

        if (id == null) {
            return RespBean.error("请选择要删除的职称！！！");
        } else {

//            先处理被关联的员工
            updateEmployeeByPosId(id);

            //        执行删除操作
            int i = positionMapper.deletePositionById(id);
            if (i == 1) {
                return RespBean.success("删除职位成功！！！");
            } else {
                return RespBean.error("删除职位失败！！！");
            }
        }
    }

    /**
     * 删除员工关联（posid设为null）
     * @param id
     */
    private void updateEmployeeByPosId(Integer id) {
        employeeMapper.updateEmployeeByPosId(id);
    }

    /**
     *  批量删除职位
     * @param ids
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean deletePositionsByIds(String[] ids) {
        if (ids == null || ids.length == 0) {
            return RespBean.error("请选择要删除的职位！！！");
        } else {

//
            employeeMapper.updateEmployeeByPosIds(ids);
            //        执行删除操作
            int i = positionMapper.deletePositionsByIds(ids);
//            删除操作结果判断
            if (i == ids.length) {
                return RespBean.success("删除成功！！！");
            } else {
                return RespBean.error("删除失败！！！");
            }
        }
    }

    /**
     * 修改职位信息
     * @param position
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean updatePosition(Position position) {
        if (position == null) {
            return RespBean.error("请选择要更新的职位！！");
        }
//        通过职位名称在数据库中查询
        Position positionByName = positionMapper.getPositionByName(position.getName());
        now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
        position.setCreateDate(now);
//        position.setId(position.getId());
        int i;
//        数据库中没有此职称
        if (positionByName == null) {
            i = positionMapper.updatePosition(position);
            if (i == 1) {
                return RespBean.success("修改成功！！！");
            } else {
                return RespBean.error("修改失败！！！");
            }
        }
//        数据库中有次职称并且不是要更新记录
        else if (positionByName.getId() != position.getId() && positionByName != null) {
            return RespBean.error("此职称已存在！！！");
        }

//        数据库中有此职称并且是当前职称
        else {
            i = positionMapper.updatePosition(position);
//            如果没做任何修改
            if (position.getName().equals(positionByName.getName())) {
                return RespBean.success("更新成功，但您并没有做任何修改！！");
            }
//            做了修改
            else {
                return RespBean.success("更新成功！！");
            }
        }

    }

}
