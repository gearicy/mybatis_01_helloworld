package com.lss.mybatis.dao;

import com.lss.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapperPlus {

    public Employee getEmpById(Integer id);
}
