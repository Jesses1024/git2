package cn.itcast.usermanager.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_user")//指定（匹配）表名
public class User {

    @Id //指定（匹配）主键
    @GeneratedValue(generator = "JDBC")//令 MyBatis 使用 JDBC 的 getGeneratedKeys 方法来取出由数据库内部生成的主键（比如：像 MySQL 和 SQL Server 这样的关系数据库管理系统的自动递增字段）。
    private Long id;

    // 用户名
    @Column(name = "user_name")//指定（匹配）字段名
    private String userName;

    // 密码
    @JsonIgnore//当对象转换为json时，忽略该属性
    private String password;

    // 姓名
    private String name;

    // 年龄
    private Integer age;

    // 性别，1男性，2女性
    private Integer sex;

    // 出生日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    // 创建时间
    private Date created;

    // 更新时间
    private Date updated;

    @Transient  //设置忽略字段，添加Transient的字段不作为表字段使用
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", name=" + name
                + ", age=" + age + ", sex=" + sex + ", birthday=" + birthday + ", created=" + created
                + ", updated=" + updated + "]";
    }

}
