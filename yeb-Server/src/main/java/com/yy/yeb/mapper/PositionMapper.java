package com.yy.yeb.mapper;

import com.yy.yeb.pojo.Position;
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
public interface PositionMapper extends BaseMapper<Position> {

    /**
     * 查询所有职位
     * @return 职位列表
     */
    public List<Position> selectPositionAll();

    /**
     * 新增职位
     *
     * @param position
     * @return
     */
    public int insertPosition(Position position);


    /**
     * 根据id删除职位
     *
     * @param id
     * @return
     */
    public int deletePositionById(Integer id);

    //    批量删除职位信息
    int deletePositionsByIds(String[] ids);

//    修改职位信息
    int updatePosition(Position position);
}
