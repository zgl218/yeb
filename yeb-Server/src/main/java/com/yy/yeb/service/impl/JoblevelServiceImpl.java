package com.yy.yeb.service.impl;

import com.yy.yeb.mapper.EmployeeMapper;
import com.yy.yeb.pojo.Joblevel;
import com.yy.yeb.mapper.JoblevelMapper;
import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.service.IJoblevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
public class JoblevelServiceImpl extends ServiceImpl<JoblevelMapper, Joblevel> implements IJoblevelService {

    @Resource
    private JoblevelMapper joblevelMapper;

    @Resource
    private EmployeeMapper employeeMapper;
    //    当前时间
    LocalDateTime now;

    //    查询所有职称
    @Override
    public List<Joblevel> findAllJoblevel() {
        return joblevelMapper.findAllJoblevel();
    }


    /**
     * 职称添加
     * @param joblevel
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean addJoblevel(Joblevel joblevel) {
        joblevelMapper.continuousKey();
        if (joblevel == null) {
            return RespBean.error("请输入要添加的职称");
        } else {
            Joblevel joblevelByName = joblevelMapper.getJoblevelByName(joblevel.getName());
            if (joblevelByName != null) {
                return RespBean.error("此职称已存在");
            } else {
//                  设置添加时间
                now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
                joblevel.setCreateDate(now);
                int i = (int) (Math.random() * 2 + 1);
//              随机生成新增职称是否启用
                if (i == 1) {
                    joblevel.setEnabled(true);
                } else {
                    joblevel.setEnabled(false);
                }
//                执行添加操作
                int i1 = joblevelMapper.addJoblevel(joblevel);
                if (i1 == 1) {
                    return RespBean.success("此职添加成功！");
                } else {
                    return RespBean.error("此职添加失败！");
                }
            }
        }
    }


    /**
     * 单条职称删除
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean deleteSingleJoblevelById(Integer id) {

        if (id == null) {
            return RespBean.error("请选择要删除的职称！！！");
        } else {
            int numByEmpJoblevleId = employeeMapper.getNumByEmpJoblevleId(id);
            if (0 == numByEmpJoblevleId) {
                int i = joblevelMapper.deleteSingleJoblevelById(id);
                if (i == 1) {
                    return RespBean.success("此职称没有关联任何员工已成功删除！！！");
                } else {
                    return RespBean.error("删除失败！！！");
                }
            } else {
                //         处理关联员工   职称删除将相关职位员工职称信息设置为null 并返回成功处理的数量
                int i1 = employeeMapper.updateEmployeeByJobLevelId(id);
                if (i1 == numByEmpJoblevleId) {
                    //        执行删除操作
                    int i = joblevelMapper.deleteSingleJoblevelById(id);
                    if (i == 1) {
                        return RespBean.success("职称删除成功并妥善处理关联员工！！！！！！");
                    } else {
                        return RespBean.error("删除失败！！！");
                    }
                } else {
                    return RespBean.error("由于所关联员工没有妥善处理，删除失败！！！");
                }

            }
        }

    }

    /**
     * 批量删除职称
     * @param ids
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean deleteBatchJoblevelByIds(String[] ids) {
//        如果前台没有传入id，提醒前台传入
        if (ids == null || ids.length == 0) {
            return RespBean.error("请选择要删除的职称！！！");
        }
//        前台传入了id
        else {
//            通过前台传入id 查询所有相关员工的数量
            int numByEmpJoblevleIds = employeeMapper.getNumByEmpJoblevleIds(ids);
            if (0 == numByEmpJoblevleIds) {
//                执行删除操作
                int i = joblevelMapper.deleteBatchJoblevelByIds(ids);
                if (i == ids.length) {
                    return RespBean.success("所选职称中没有关联任何员工已成功删除！！！");
                } else {
                    return RespBean.error("删除失败！！！");
                }
            } else {
                //     处理关联员工   职称删除将相关职位员工职称信息设置为null
                int num= employeeMapper.updateEmployeeByJobLevelIds(ids);
//               如果更处理的员工数量与关林的数量相同执行删除操作
                if (num==numByEmpJoblevleIds){
                    //        执行删除操作
                    int i = joblevelMapper.deleteBatchJoblevelByIds(ids);
//                    如果删除职称的数量与前台传过来的数量一样  删除成功
                    return getRespBean(ids, i);
                }
//                否则返回前台删除失败
                else {
                    return RespBean.error("由于选中的职位中所关联员工没有妥善处理，删除失败！！！");
                }

            }
        }

    }

    private RespBean getRespBean(String[] ids, int i) {
        if (i == ids.length) {
            return RespBean.success("所选职称已成功删除，并妥善处理所关联员工！！！！！！");
        }
//                    否则删除失败
        else {
            return RespBean.error("删除失败！！！");
        }
    }

    /**
     * 更新职称
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RespBean updateJoblevelById(Joblevel joblevel) {
        if (joblevel == null) {
            return RespBean.error("请选择要更新的职称！！");
        }
        Joblevel joblevelByName = joblevelMapper.getJoblevelByName(joblevel.getName());

        now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
        joblevel.setCreateDate(now);
        joblevel.setId(joblevel.getId());
        int i;
//        数据库中没有此职称
        if (joblevelByName == null) {
            i = joblevelMapper.updateJoblevelById(joblevel);
            return getRespBeanWithUpdate(i, "更新成功！！");
        }
//        数据库中有次职称并且不是要更新记录
        else if (joblevelByName.getId() != joblevel.getId() && joblevelByName != null) {

            return RespBean.error("此职称已存在！！！");
        }
//        数据库中有此职称并且是当前职称
        else {
            i = joblevelMapper.updateJoblevelById(joblevel);
//            如果没做任何修改
            if (joblevel.getName().equals(joblevelByName.getName()) &&
                    joblevel.getTitleLevel().equals(joblevelByName.getTitleLevel()) && joblevel.getEnabled().equals(joblevelByName.getEnabled())) {
                return getRespBeanWithUpdate(i, "更新成功，但您并没有做任何修改！！");
            }
//            做了修改
            else {
                return getRespBeanWithUpdate(i, "更新成功！！");
            }
        }
    }

    //    更新结果获取方法抽取
    private RespBean getRespBeanWithUpdate(int i, String s) {
        if (i == 1) {
            return RespBean.success(s);
        } else {
            return RespBean.error("更新失败！！");
        }
    }

}
