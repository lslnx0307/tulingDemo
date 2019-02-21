package com.lslnx.test;

import com.lslnx.mybatis.mapper.UserMapper;
import com.lslnx.mybatis.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class MybatisTest {

    @Test
    public void test(){
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectUser(1);
//          User user = sqlSession.selectOne("com.lslnx.mybatis.mapper.UserMapper.selectUser", 3);
            log.info("user:{}",user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void annontionTest(){
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectUser(3);
            log.info("user:{}",user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
