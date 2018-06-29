package com.lss.mybatis.dao;

import com.lss.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnnotation {

    @Select("select * from tb1_employee where id = #{id}")
    public Employee getEmpById(Integer id);
}
