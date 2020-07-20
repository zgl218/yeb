package com.yy.yeb.controller;


import com.yy.yeb.pojo.Joblevel;
import com.yy.yeb.service.impl.JoblevelServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Update;
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
public class JoblevelController {

    @Autowired
    private JoblevelServiceImpl joblevelService;

    /**
     * 查询所有职称
     * @return
     */
    @ApiOperation(value = "职称查询")
    @GetMapping("/joblevel")
    public List<Joblevel> findAllJoblevel() {
        return joblevelService.findAllJoblevel();
    }

    /**
     * 职称添加
     */
    @ApiOperation(value = "职称添加")
    @PostMapping("/joblevel")
    public int addJoblevel(@RequestBody Joblevel joblevel) {
        System.out.println("======================================="+joblevel);
            return  joblevelService.addJoblevel(joblevel);
    }


    /**
     * 删除单条职称
     * @param id
     */
    @ApiOperation(value = "删除职称")
    @DeleteMapping("/joblevel/{id}")
    public int deleteSingleJoblevelById(@PathVariable Integer id){

        return joblevelService.deleteSingleJoblevelById(id);
    }


    /**
     * 批量删除职称
     * @param ids
     */
    @ApiOperation(value = "批量删除职称")
    @DeleteMapping(value = "/joblevel")
    public int deleteBatchJoblevelByIds(String[] ids){
        System.out.println("======================================"+ids);

        return joblevelService.deleteBatchJoblevelByIds(ids);
    }

    /**
     * 更新职称
     * @param joblevel
     */
    @ApiOperation(value = "更新职称")
    @PutMapping("/joblevel")
    public int updateJoblevelById(@RequestBody Joblevel joblevel){
        return joblevelService.updateJoblevelById(joblevel);
    }




}
