package cn.newstrength.user.action;

import cn.newstrength.core.service.AbstractLog4j2Service;
import cn.newstrength.core.service.BaseInsertOneService;
import cn.newstrength.user.entity.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class RegistUserAction extends AbstractLog4j2Service<RegistUserAction> {

    @Autowired
    @Qualifier(value = "userService")
    private BaseInsertOneService insertOneObjectService;

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    @ResponseBody
    public int registUser(UserBO userObj) throws RuntimeException {
        return insertOneObjectService.insertOne(userObj);
    }
}
