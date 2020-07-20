package com.yy.yeb.service.impl;

import com.yy.yeb.pojo.Position;
import com.yy.yeb.mapper.PositionMapper;
import com.yy.yeb.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.yeb.utils.AssertUtil;
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


    LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));

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
//    @Transactional(propagation = Propagation.REQUIRED)
    public int insertPosition(Position position) {
        System.out.println(position.getName());
        System.out.println();
        System.out.println();
        position.setCreateDate(now);
        return positionMapper.insertPosition(position);
    }

    /**
     * 根据id删除职位
     * @param id 职位ID
     * @return
     */
    @Override
    public int deletePositionById(Integer id) {
        return positionMapper.deletePositionById(id);
    }

    /**
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deletePositionsByIds(String[] ids) {
        AssertUtil.isTrue(ids == null || ids.length == 0, "请传入要删除的职位");
        int i = positionMapper.deletePositionsByIds(ids);
        AssertUtil.isTrue(i < ids.length, "删除失败！！！");
        return i;
    }

    /**
     * 修改职位信息
     * @param position
     * @return
     */
    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
    public int updatePosition(Position position) {
        System.out.println(position);
        Position position1 = new Position();
        position1.setName(position.getName());
        position1.setCreateDate(now);
        position1.setId(position.getId());
        position1.setEnabled(position.getEnabled());
        System.out.println(position1);
        return positionMapper.updatePosition(position1);

    }

}
