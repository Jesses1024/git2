package cn.itcast.usermanager.mapper;

import cn.itcast.usermanager.pojo.User;
import cn.itcast.usermanager.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

    private UserService userService;
    @Before
    public void setUp()throws Exception{
        ApplicationContext cpx = new ClassPathXmlApplicationContext("spring/applicationContext.xml",
                "spring/applicationContext-mybatis.xml", "spring/applicationContext-transaction.xml");
        this.userService = cpx.getBean(UserService.class);
    }

    @Test
    public void test() {
        User user1 = new User();
        user1.setUserName("dengdeng3");
        user1.setPassword("123456");
        User user2 = new User();
        user2.setUserName("dengdeng4");
        user2.setPassword("123456");
//        this.userService.addUser(user1, user2);
    }
}
