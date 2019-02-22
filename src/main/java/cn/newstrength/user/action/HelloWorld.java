package cn.newstrength.user.action;

import cn.newstrength.core.service.AbstractLog4j2Service;
import cn.newstrength.core.service.BaseInsertOneObjectService;
import cn.newstrength.user.entity.UserObj;
import cn.newstrength.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class HelloWorld extends AbstractLog4j2Service<HelloWorld> {

    @Autowired
    @Qualifier("userService")
    private BaseInsertOneObjectService userService ;

    @RequestMapping("/hello")
    @ResponseBody
    public String getHellow(){
        logger.info("hello ddfdfdfd");
        return  "hello";
    }

    @RequestMapping(value = "/addOne",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(UserObj userObj){
        System.out.println(userObj.toString());
        int total = userService.insertOne(userObj);
        return "success"+total;
    }
}
