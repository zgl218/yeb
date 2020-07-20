package com.yy.yeb.service.impl;

import com.yy.yeb.pojo.Position;
import com.yy.yeb.mapper.PositionMapper;
import com.yy.yeb.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    public int insertPosition(Position position) {
        LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
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

}
