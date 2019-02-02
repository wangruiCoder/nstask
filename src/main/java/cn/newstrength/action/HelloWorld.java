package cn.newstrength.action;

import cn.newstrength.entity.UserObj;
import cn.newstrength.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class HelloWorld {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public String getHellow(){
        return  "hello";
    }

    @RequestMapping(value = "/addOne",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(UserObj userObj){
        System.out.println(userObj.toString());
        int total = userService.add(userObj);
        return "success"+total;
    }
}
