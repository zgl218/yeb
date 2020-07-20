package com.yy.yeb.service;

import com.yy.yeb.pojo.Joblevel;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface IJoblevelService extends IService<Joblevel> {

//    查询所有职称
    List<Joblevel> findAllJoblevel();
//    职称添加
    int addJoblevel(Joblevel joblevel);

//    删除单条职称
    int deleteSingleJoblevelById(Integer id);

//    批量删除职称
    int deleteBatchJoblevelByIds(String []ids);
//    更新职称
    int updateJoblevelById(Joblevel joblevel);

}
