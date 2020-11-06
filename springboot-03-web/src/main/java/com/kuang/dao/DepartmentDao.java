package com.kuang.dao;

import com.kuang.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {
    private static Map<Integer,Department> departments = null;
    static {
        departments = new HashMap<Integer,Department>();
        departments.put(101,new Department(101,"教学部"));
        departments.put(102,new Department(102,"市场部"));
        departments.put(103,new Department(103,"教研部"));
        departments.put(104,new Department(104,"运营部"));
    }


    //获得所有部门信息
    public Collection<Department> getDepartments(){
        return departments.values();
    }

    //根据Id获取部门信息
    public Department getDepartmentById(Integer id){
        return departments.get(id);
    }
}
