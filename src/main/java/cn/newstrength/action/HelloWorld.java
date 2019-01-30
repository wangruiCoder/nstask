package cn.newstrength.action;

import cn.newstrength.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorld {

    @RequestMapping("/hello")
    @ResponseBody
    public String getHellow(){
        return  "hello";
    }
}
