package cn.itcast.usermanager.mapper;

import cn.itcast.usermanager.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 根据id获取User信息
     * @return
     */
    public User queryUserById(Long id);

    /**
     * 分页查询用户想信息
     * @param start
     * @param pageSize
     * @return
     */
    List<User> queryUsersByPage(@Param("start") int start, @Param("pageSize") Integer pageSize);

    /**
     * 查询所有用户
     * @return
     */
    List<User> queryUserAll();

    public int addUser(User user) throws Exception;

    public int deleteUsers(@Param("ids")String[] ids);
}
