package cn.itcast.usermanager.service;

import cn.itcast.usermanager.mapper.NewUserMapper;
import cn.itcast.usermanager.mapper.UserMapper;
import cn.itcast.usermanager.pojo.EasyUIResult;
import cn.itcast.usermanager.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.StartDocument;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private NewUserMapper userMapper;

    @Override
    public EasyUIResult queryEasyUIResult(Integer pageNum, Integer pageSize) {
        //第一个参数是从哪条开始，第二个参数是查询多少条
        // List<User> userList=this.userMapper.queryUsersByPage((pageNum-1)*pageSize,pageSize);//自定义的传参分页查询

        // 在查询方法调用之前，调用分页插件的静态方法，中间最好不要隔任何代码
        PageHelper.startPage(pageNum,pageSize);
        User record = new User();
        List<User> userList = this.userMapper.select(record);
        PageInfo<User> pageInfo=new PageInfo(userList);
        EasyUIResult easyUIResult = new EasyUIResult();
        easyUIResult.setTotal(pageInfo.getTotal());
        easyUIResult.setRows(pageInfo.getList());

        return easyUIResult;
    }

    @Override
    public void addUser(User user1, User user2) throws Exception {
        this.userMapper.insert(user1);
        this.userMapper.insert(user2);
    }

    @Override
    public Boolean saveUser(User user) throws Exception{
        int count=this.userMapper.insert(user);
        if(count>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteUsers(String[] ids) throws Exception {
        int count=this.userMapper.deleteByPrimaryKey(ids);
        if (count>0){
            return true;
        }

        return false;
    }

    @Override
    public EasyUIResult queryUserListByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        User user = new User();
        List<User> userList = this.userMapper.select(user);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        EasyUIResult easyUIResult = new EasyUIResult();
        easyUIResult.setTotal(pageInfo.getTotal());
        easyUIResult.setRows(pageInfo.getList());

        return easyUIResult;
    }

    @Override
    public User queryUserById(Long id) {

        User user = this.userMapper.selectByPrimaryKey(id);
        return user;
    }


}
