package cn.newstrength.user.action;

import cn.newstrength.core.constant.SystemBusinessExceptionCodeEnum;
import cn.newstrength.core.exception.BusinessException;
import cn.newstrength.core.service.AbstractLog4j2Service;
import cn.newstrength.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequestMapping("user/")
public class LoginAction extends AbstractLog4j2Service<LoginAction> {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login/{userName}/{passWord}/{code}", method = RequestMethod.POST)
    public String userLogin(@PathVariable("userName") String userName,
                            @PathVariable("passWord") String passWord,
                            @PathVariable("code") String code) throws BusinessException {
        if (userName == null || "".equals(userName)) {
            throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE.getBusinessCode(),"用户名或密码错误");
        } else if (passWord == null || "".equals(userName)) {
            throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE.getBusinessCode(),"用户名或密码错误");
        } else if (code == null || "null".equals(code)) {
            throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE.getBusinessCode(),"验证码缺失");
        }

        String result = userService.queryByUserName(userName,passWord,code);

        return result;
    }
}
