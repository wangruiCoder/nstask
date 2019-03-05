package cn.newstrength.common.action;

import cn.newstrength.core.constant.SystemBusinessExceptionCodeEnum;
import cn.newstrength.core.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorAction {

    @RequestMapping(value = "/error")
    @ResponseBody
    public Result getError(){

        Result result = new Result();
        result.setErrorCode(SystemBusinessExceptionCodeEnum.ERROR_CODE.getBusinessCode());
        result.setMessage(SystemBusinessExceptionCodeEnum.ERROR_CODE.getDesc());

        return result;
    }
}
