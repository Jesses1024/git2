package cn.itcast.usermanager.mapper;

import cn.itcast.usermanager.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMapperTest {

    private UserMapper userMapper;

    @Before
    public void setup(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/applicationContext-mybatis.xml");
        this.userMapper = context.getBean(UserMapper.class);
    }

    @Test
    public void testQueryUserById() {
        System.out.println(this.userMapper.queryUserById(6l));
    }


}
