package com.yy.yeb.service.impl;

import com.yy.yeb.expecitions.ParamsException;
import com.yy.yeb.pojo.Joblevel;
import com.yy.yeb.mapper.JoblevelMapper;
import com.yy.yeb.service.IJoblevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.yeb.utils.AssertUtil;
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

    //    当前时间
    LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));

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
    public int addJoblevel(Joblevel joblevel) {
        System.out.println(joblevel);
        AssertUtil.isTrue(joblevel == null, "请传入要添加的职称！！");
        Joblevel joblevelByName = joblevelMapper.getJoblevelByName(joblevel.getName());
        System.out.println(joblevelByName);
        if (joblevelByName != null) {
            throw new ParamsException("此职称已存在");
        } else {

            joblevel.setCreateDate(now);

            int i = (int) (Math.random() * 2 + 1);
//        随机生成新增职称是否启动
            if (i == 1) {
                joblevel.setEnabled(true);
            } else {
                joblevel.setEnabled(false);
            }
            int i1 = joblevelMapper.addJoblevel(joblevel);
            AssertUtil.isTrue(i1 < 1, "添加职称失败！！");
            return i1;
        }
    }

    /**
     * 单条职称删除
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteSingleJoblevelById(Integer id) {
        AssertUtil.isTrue(id == null, "请传入要删除的职称！！！");
//        执行删除操作
        int i = joblevelMapper.deleteSingleJoblevelById(id);
        System.out.println("======================================================+" + i);
        AssertUtil.isTrue(i < 1, "删除失败！！！");
        return i;

    }

    /**
     * 批量删除职称
     * @param ids
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteBatchJoblevelByIds(String[] ids) {
        AssertUtil.isTrue(ids == null || ids.length == 0, "请传入要删除的职称！！！");
        int i = joblevelMapper.deleteBatchJoblevelByIds(ids);
//        执行删除操作
        AssertUtil.isTrue(i < ids.length, "删除失败！！！");
        return i;
    }

    /**
     * 更新职称
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateJoblevelById(Joblevel joblevel) {
        Joblevel joblevel1 = new Joblevel();
        AssertUtil.isTrue(joblevel == null, "请选择要更新的职称！！");
        Joblevel joblevelByName = joblevelMapper.getJoblevelByName(joblevel.getName());

        joblevel1.setTitleLevel(joblevel.getTitleLevel());
        joblevel1.setName(joblevel.getName());
        joblevel1.setEnabled(joblevel.getEnabled());
        joblevel1.setCreateDate(now);
        joblevel1.setId(joblevel.getId());
        System.out.println(joblevel1);
        System.out.println(joblevelByName);
        if (joblevelByName == null) {
            int i = joblevelMapper.updateJoblevelById(joblevel1);
            AssertUtil.isTrue(i < 1, "更新失败！！");
            return i;
        } else if (joblevelByName.getId() != joblevel.getId() && joblevelByName != null) {

            AssertUtil.isTrue(0 < 1, "职称已存在");
            return 0;
        } else {
            int i = joblevelMapper.updateJoblevelById(joblevel1);
            AssertUtil.isTrue(i < 1, "更新失败！！");
            return i;
        }
    }

}
