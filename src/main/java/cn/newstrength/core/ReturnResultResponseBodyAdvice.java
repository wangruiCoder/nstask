package cn.newstrength.core;

import cn.newstrength.common.result.CommonHasDataResult;
import cn.newstrength.core.constant.SystemBusinessExceptionCodeEnum;
import cn.newstrength.core.result.Result;
import cn.newstrength.core.service.AbstractLog4j2Service;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.File;

@ControllerAdvice
@Component
public class ReturnResultResponseBodyAdvice
        extends AbstractLog4j2Service<ReturnResultResponseBodyAdvice>
        implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        boolean converterResult = MappingJackson2HttpMessageConverter.class.isAssignableFrom(aClass);
        logger.debug("ReturnResultResponseBodyAdvice support method "+
                converterResult);

        return converterResult;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (body == null
                || (body instanceof File)
                || (body instanceof Result)) {
            return body;
        } else {
            CommonHasDataResult result = new CommonHasDataResult();
            result.setData(body);
            result.setErrorCode(SystemBusinessExceptionCodeEnum.SUCCESS_CODE.getBusinessCode());
            body = (Object) result;
            return body;
        }
    }
}
