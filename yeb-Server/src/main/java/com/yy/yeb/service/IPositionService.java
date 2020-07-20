package com.yy.yeb.service;

import com.yy.yeb.pojo.Position;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface IPositionService extends IService<Position> {
    /**
     * 查询所有职位
     *
     * @return 职位列表
     */
    public List<Position> selectPositionAll();
    /**
     * 新增职位
     *
     * @param position 职位信息
     * @return
     */
    public int insertPosition(Position position);

    /**
     * 根据id删除职位
     *
     * @param id 职位ID
     * @return
     */
    public int deletePositionById(Integer id);

}
