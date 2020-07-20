package com.yy.yeb.controller;


import com.yy.yeb.pojo.Position;
import com.yy.yeb.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/system/basic")
public class PositionController {
    @Autowired
    private IPositionService positionService;

    /**
     * 获取职位列表
     */
    @ApiOperation(value = "职位查询")
    @GetMapping("/pos")
    public List<Position> selectPositionAll() {
        return positionService.selectPositionAll();
    }


    /**
     * 新增职位
     *
     * @param position
     * @return
     */
    @ApiOperation(value = "新增职位信息")
    @PostMapping("/pos")
    public int insertPosition(@RequestBody Position position) {
//        positionService.insertPosition(position);
        return positionService.insertPosition(position);

    }

    /**
     *根据id删除职位
     * @param id
     * @return
     */
    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/pos/{id}")
    public int deletePositionById(@PathVariable Integer id) {
        return positionService.deletePositionById(id);
    }

    /**
     * 批量删除职位信息
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/pos")
    public int deletePositionsByIds(String[] ids) {
        System.out.println("======================================" + ids);
        return positionService.deletePositionsByIds(ids);
    }


    /**
     * 修改职位信息
     * @param position
     * @return
     */
    @ApiOperation(value = "修改职位信息")
    @PutMapping("/pos")
    public int updatePosition(@RequestBody Position position) {
        return positionService.updatePosition(position);
    }


}
