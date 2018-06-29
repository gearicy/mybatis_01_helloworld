package com.lss.mybatis.test;

import com.lss.mybatis.bean.Employee;
import com.lss.mybatis.dao.EmployeeMapper;
import com.lss.mybatis.dao.EmployeeMapperAnnotation;
import com.lss.mybatis.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {

    @Test
    public void test() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //2.获取sqlSession实例，能直接执行已经映射的sql语句
        SqlSession openSession;
        openSession = sqlSessionFactory.openSession();
        try {
            Employee employee = openSession.selectOne("com.lss.mybatis.EmployeeMapper.selectEmp", 1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }

    }

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test01() throws IOException {
        //1.得到sqlSessionFanctory
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2.获取sqlSession
        SqlSession openSession = sqlSessionFactory.openSession();
        //3.
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            Employee employee = mapper.getEmpById(1);
            System.out.println(employee + "------haha");
        } finally {
            openSession.close();
        }


    }

    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
            Employee empById = mapper.getEmpById(1);
            System.out.println(empById);
        } finally {
            openSession.close();
        }

    }

    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            //添加
            Employee employee = new Employee(null, "jerry", "jerry@qq.com", "1");
            mapper.addEmp(employee);
            //修改
            /*Employee employee = new Employee(1, "jerry", "jerry@qq.com", "1");
            mapper.updateEmp(employee);*/
            //删除
//            mapper.deleteEmpById(7);
            openSession.commit();
            System.out.println(employee.getId());
        } finally {
            openSession.close();
        }

    }

    @Test
    public void test04() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee empById = mapper.getEmpById(8);
            System.out.println(empById);
        } finally {
            openSession.close();
        }

    }
}
