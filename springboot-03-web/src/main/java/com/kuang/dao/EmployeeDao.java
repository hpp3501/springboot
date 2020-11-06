package com.kuang.dao;

import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static Map<Integer,Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;
    static {
        employees = new HashMap<Integer,Employee>();
        employees.put(1001,new Employee(1001,"张三","zhagnsan123@qq.com",1,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"李四","lisi123@qq.com",0,new Department(102,"市场部")));
        employees.put(1003,new Employee(1003,"王五","wangwu123@qq.com",0,new Department(103,"教研部")));
        employees.put(1004,new Employee(1004,"赵六","zhaoliu123@qq.com",1,new Department(104,"运营部")));
    }

    private static Integer initId=1005;

    //增加一个员工
    private void save(Employee employee){
        if(employee.getId()==null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    //获取全部员工
    public Collection<Employee> getEmployees(){
        return employees.values();
    }

    //根据Id获取员工信息
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }
}
