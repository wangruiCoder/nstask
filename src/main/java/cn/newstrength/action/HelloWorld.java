package cn.newstrength.action;

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
