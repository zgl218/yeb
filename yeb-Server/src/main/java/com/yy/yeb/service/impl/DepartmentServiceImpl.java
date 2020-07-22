package com.yy.yeb.service.impl;

import com.yy.yeb.pojo.Department;
import com.yy.yeb.mapper.DepartmentMapper;
import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {


    @Resource
    private DepartmentMapper departmentMapper;


    public List<Department> queryAllDepartment() {

        // 查询所有部门
        List<Department> deptMenus = departmentMapper.queryAllDepartment();

        // 所有的一级部门
        List<Department> resultList = new ArrayList<>();

//        创建一个map容器
        Map<Integer, Object> tree = new HashMap<>();

//        声明树结构
//        Object itemTree;
        System.out.println(deptMenus.size());
        // 将部门存储在map中,key为部门id
        for (int i = 0; i < deptMenus.size() && !deptMenus.isEmpty(); i++) {
//            itemTree = deptMenus.get(i);
            System.out.println(deptMenus.get(i).getId()+"============================"+ deptMenus.get(i));
            // 把所有的数据都放到map中
            tree.put(deptMenus.get(i).getId(), deptMenus.get(i));
        }

        // 遍历map得到顶层节点
        for (int i = 0; i < deptMenus.size(); i++) {
            // 优点1：整个方法，只查询了一次数据库
            // 优点2：不用知道顶层节点的id
            System.out.println("父id：：："+deptMenus.get(i).getParentId());
            System.out.println("父id是否在树map里：：："+tree.containsKey(deptMenus.get(i).getParentId()));
            if (!tree.containsKey(deptMenus.get(i).getParentId())) {
                // 我们在存储的时候就是将元素的id为键，元素本身为值存入的
                // 以元素的父id为键，在map里取值，若取不到则，对应的元素不存在，即没有父节点，为顶层节点或游离节点
                // 将顶层节点放入list集合
                resultList.add(deptMenus.get(i));
            }
        }

        for (Department dep :resultList) {
            System.out.println("resultList:::::::::"+dep);
        }

        // 循环数据，将数据放到该节点的父节点的children属性中
        System.out.println("========="+!deptMenus.isEmpty());
        for (int i = 0; i < deptMenus.size() && !deptMenus.isEmpty(); i++) {
            // 数据库中，若一个元素有子节点，那么，该元素的id为子节点的父id
            //treeMap.get(tbCategories.get(i).getParentId()); // 从map集合中找到父节点
            Department department = (Department) tree.get(deptMenus.get(i).getParentId());
            if (department != null) { // 不等于null，也就意味着有父节点
                // 有了父节点，要判断父节点下存贮字节点的集合是否存在，然后将子节点放入
                if (department.getChildren() == null) {
                    // 判断一个集合是否被创建用null：表示结合还没有被分配内存空间(即还没有被创建)，内存大小自然为null
                    // 用集合的size判断集合中是否有元素，为0，没有元素（集合已经被创建），
                    department.setChildren(new ArrayList<Department>());
                }
                // 添加到父节点的ChildList集合下
                department.getChildren().add(deptMenus.get(i));
            }

        }

        return resultList;
    }

    @Override
    public RespBean addDepartment(Department department) {
        System.out.println(department);

        int i = departmentMapper.addDepartment(department);
        if (i==1){
            return RespBean.success("添加成功！!!!!!!!!");
        }
        else {
            return RespBean.error("添加失败！!!!!!!!!");
        }

    }


}
