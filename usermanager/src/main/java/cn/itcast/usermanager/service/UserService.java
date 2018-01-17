package cn.itcast.usermanager.service;

import cn.itcast.usermanager.pojo.EasyUIResult;
import cn.itcast.usermanager.pojo.User;

public interface UserService {
    /**
     * 分页获取用户信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    EasyUIResult queryEasyUIResult(Integer pageNum,Integer pageSize);

    public void addUser(User user1,User user2) throws Exception;

    Boolean saveUser(User user) throws Exception;

    Boolean deleteUsers(String[] ids) throws Exception;

    EasyUIResult queryUserListByPage(Integer pageNum, Integer pageSize);

    User queryUserById(Long id);
}
