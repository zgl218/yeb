package com.yy.yeb.mapper;

import com.yy.yeb.pojo.Joblevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface JoblevelMapper extends BaseMapper<Joblevel> {

    //    查询职称
    List<Joblevel> findAllJoblevel();

    //添加职称
    int addJoblevel(Joblevel joblevel);
    //    删除单条职称
    int deleteSingleJoblevelById(Integer id);

//    批量删除职称
    int deleteBatchJoblevelByIds(String[] ids);

//   更新职称
    int updateJoblevelById(Joblevel joblevel);

//       查询职称名字查询职称通过名字
    Joblevel getJoblevelByName(String name);

    void continuousKey();
}
