package com.lslnx;

import com.lslnx.mapper.UserMapper;
import com.lslnx.pojo.User;
import com.lslnx.session.Configuration;
import com.lslnx.session.DefaultSqlSession;
import com.lslnx.session.SqlSessionFactory;
import com.lslnx.session.SqlSessionFactoryBuild;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: tulingDemo
 * @description:
 * @author: v-jasperli
 * @create: 2019-02-21 13:46
 **/
public class TIMybatisTest {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = TIMybatisTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        Configuration configuration = new Configuration();
        configuration.setInputStream(inputStream);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuild().build(configuration);
        DefaultSqlSession sqlSession = (DefaultSqlSession) sqlSessionFactory.openSession(configuration);
        UserMapper userMapper = sqlSession.getMappers(UserMapper.class);
        User user = userMapper.getUser(1);
        System.out.println(user.toString());
    }
}
