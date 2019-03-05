package cn.newstrength.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


public class SessionInterceptor implements HandlerInterceptor {
    private Logger logger = LogManager.getLogger(SessionInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("session拦截器开始执行");
        HttpSession session = request.getSession();
        ServletContext application = session.getServletContext();
        if (application.getAttribute(session.getId()) == null){
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            out.write("no login1222");
            out.close();
            return true;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("ex = " + ex.getMessage()+"88888888888888888");
    }
}
