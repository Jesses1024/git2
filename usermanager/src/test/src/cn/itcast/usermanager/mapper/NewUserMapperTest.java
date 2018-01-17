package cn.itcast.usermanager.mapper;

import cn.itcast.usermanager.pojo.User;
import com.github.abel533.entity.Example;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NewUserMapperTest {
    private NewUserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "spring/applicationContext.xml",
                        "spring/applicationContext-mybatis.xml" });
        this.userMapper = context.getBean(NewUserMapper.class);
    }

    @Test
    public void testSelectOne() {
        User user = new User();
        user.setUserName("zhangsan");
        user.setPassword("123456");
        System.out.println(this.userMapper.selectOne(user));
    }

    @Test
    public void testSelect() {
        User user = new User();
        user.setPassword("123456");
        List<User> userList = this.userMapper.select(user);
        for (User user1 : userList) {
            System.out.println("user1 = " + user1);
        }
    }

    @Test
    public void testSelectCount() {
        User user = new User();
        user.setPassword("123456");
        System.out.println(this.userMapper.selectCount(user));
    }

    @Test
    public void testSelectByPrimaryKey() {
        System.out.println(this.userMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("马龙");
        user.setName("malong");
        user.setAge(18);
        this.userMapper.insert(user);
    }

    @Test
    public void testInsertSelective() {
        User user = new User();
        user.setUserName("李小撸");
        user.setName("lixiaolu");
        user.setAge(19);
        this.userMapper.insertSelective(user);
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setUserName("李小路");
        this.userMapper.delete(user);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        int i = this.userMapper.deleteByPrimaryKey(8L);
        if (i>=0){
            System.out.println(true);
        }else {
            System.out.println(false);
        }
    }

    @Test
    public void testUpdateByPrimaryKey() {
        User user = new User();
        user.setId(11L);
        user.setUserName("掰掰合");
        user.setName("bitch");
        this.userMapper.updateByPrimaryKey(user);
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        User user = new User();
        user.setId(12L);
        user.setUserName("李小撸");
        user.setName("fuck she");
        this.userMapper.updateByPrimaryKeySelective(user);
    }


    @Test
    public void testSelectCountByExample() {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("age","20","30");
        System.out.println(this.userMapper.selectCountByExample(example));
    }

    @Test
    public void testDeleteByExample() {
    }

    @Test
    public void testSelectByExample() {
        //创建Example对象，创建Example的内部类Criteria添加查询条件
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThan("age", 15);
        List<Object> ids = new ArrayList<Object>();
        ids.add(11);
        ids.add(12);
        criteria.andIn("id",ids);

        Example.Criteria criteria2 = example.createCriteria();
        criteria2.andLike("userName","%撸%");
        example.or(criteria2);
        example.setOrderByClause(" age asc,id desc");


        List<User> userList = this.userMapper.selectByExample(example);
        for (User user : userList) {
            System.out.println("user = " + user);
        }

    }

    @Test
    public void testUpdateByExampleSelective() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateByExample() {
        fail("Not yet implemented");
    }

}