package cn.itcast.usermanager.controller;

import cn.itcast.usermanager.pojo.EasyUIResult;
import cn.itcast.usermanager.pojo.User;
import cn.itcast.usermanager.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("user")
@Controller
public class UserController {

@Autowired
    private UserService userService;

    @RequestMapping("users")
    public String toUsers(){
        return "users";
    }

    @RequestMapping("list")
    @ResponseBody
    public EasyUIResult queryUserByPage(@RequestParam(value = "page") Integer pageNum, @RequestParam(value = "rows") Integer pageSize){
        return this.userService.queryEasyUIResult(pageNum,pageSize);
    }

    @RequestMapping("page/{pageName}")
    public String toUserAdd(@PathVariable("pageName")String pageName){
        return pageName;
    }

    /**
     * 请求路径：/save
     * 方法返回值：{status：200}
     * 参数：User对象
     */
    @RequestMapping("save")
    @ResponseBody
    public Map<String,Object> addUser(User user){
        Map<String, Object> map = new HashMap<>();
        try {
            Boolean b=this.userService.saveUser(user);
            if (b){
                map.put("status",200);
            }else{
                map.put("status",500);
            }
        } catch (Exception e) {
            map.put("status",500);
            e.printStackTrace();
        }
        return  map;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String,Object> deleteUser(@RequestParam("ids") String[] ids){
        Map<String, Object> map = new HashMap<>();
        try {
            Boolean b = this.userService.deleteUsers(ids);
            if(b){
                map.put("status",200);
            }else{
                map.put("status",500);
            }
        } catch (Exception e) {
            map.put("status",500);
            e.printStackTrace();
        }
        return map;
    }


    @RequestMapping("export/excel")
    public String exportExcel(@RequestParam("page")Integer pageNum,
                              @RequestParam("rows")Integer pageSize, Model model){
        EasyUIResult easyUIResult=this.userService.queryUserListByPage(pageNum,pageSize);
        model.addAttribute("userList",easyUIResult.getRows());

        return "userExcelView";
    }


}
