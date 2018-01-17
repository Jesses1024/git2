package cn.itcast.usermanager.controller;

import cn.itcast.usermanager.pojo.User;
import cn.itcast.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class RestfulController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<User> queryUserById(@PathVariable("id")Long id){
        try {
            if (id.longValue()<1){
                //400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            User user=this.userService.queryUserById(id);
            //404 找不到资源 资源不存在
            if(null==user){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //500 系统内部错误
    }
}
