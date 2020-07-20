package com.yy.yeb.service.impl;

import com.yy.yeb.pojo.Joblevel;
import com.yy.yeb.mapper.JoblevelMapper;
import com.yy.yeb.service.IJoblevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    //        职称添加
    @Override
    public int addJoblevel(Joblevel joblevel) {

        int num = joblevelMapper.findJoblevelByName(joblevel.getName());
        System.out.println("数据库中此名：======================================"+num);

        if (num>=1){
            System.out.println("通知前端职称名已存在！！！");
        }
//        LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));

        joblevel.setCreateDate(now);

        int i = (int) (Math.random() * 2 + 1);
//        随机生成新增职称是否启动
        if (i == 1) {
            joblevel.setEnabled(true);
        } else {
            joblevel.setEnabled(false);
        }

        return joblevelMapper.addJoblevel(joblevel);

    }

    //    删除单条职称
    @Override
    public int deleteSingleJoblevelById(Integer id) {

//        if (id != null) {
            return joblevelMapper.deleteSingleJoblevelById(id);
//        } else {
//            return ;
//        }

    }

    /**
     * 批量删除职称
     * @param ids
     */
    @Override
    public int deleteBatchJoblevelByIds(String[] ids) {

       return joblevelMapper.deleteBatchJoblevelByIds(ids);


    }

    /**
     * 更新职称
     */
    @Override
    public int updateJoblevelById(Joblevel joblevel) {


        Joblevel joblevel1 = new Joblevel();

        joblevel1.setTitleLevel(joblevel.getTitleLevel());
        joblevel1.setName(joblevel.getName());
        joblevel1.setEnabled(joblevel.getEnabled());
        joblevel1.setCreateDate(now);
        joblevel1.setId(joblevel.getId());
        return joblevelMapper.updateJoblevelById(joblevel1);
//            joblevelMapper.updateById(id);
    }
}
