package cn.newstrength.core.exception.resolver;

import cn.newstrength.core.constant.SystemBusinessExceptionCodeEnum;
import cn.newstrength.core.exception.BusinessException;
import cn.newstrength.core.result.Result;
import cn.newstrength.core.service.AbstractLog4j2Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BusinessExceptionResolver extends AbstractLog4j2Service<BusinessExceptionResolver>
        implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse, Object o, Exception e) {

        ModelAndView mv = new ModelAndView();
        Result result = new Result();
        ObjectMapper jsonObjectMapper = new ObjectMapper();

        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        httpServletResponse.setCharacterEncoding("UTF-8"); //避免乱码
        httpServletResponse.setHeader("Cache-Control", "no-cache, must-revalidate");

        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            result.setMessage(businessException.getMessage());
            result.setErrorCode(businessException.getExceptionCode());
        }else {
            result.setErrorCode(SystemBusinessExceptionCodeEnum.ERROR_CODE.getBusinessCode());
            result.setMessage(e.getMessage());
        }
        try {
            httpServletResponse.getWriter().write(jsonObjectMapper.writeValueAsString(result));
        } catch (IOException e1) {
            logger.error("与客户端通讯异常!");
        }
        return mv;
    }
}
