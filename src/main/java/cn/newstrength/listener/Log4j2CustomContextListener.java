package cn.newstrength.listener;

import org.apache.logging.log4j.core.config.Configurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Enumeration;

public class Log4j2CustomContextListener implements ServletContextListener {

    private final String KEY = "log4jConfiguration";
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        String configuration = getContextParam(servletContextEvent);
        Configurator.initialize("Log4j2", configuration);
    }

    private String getContextParam(ServletContextEvent event) {
        Enumeration<String> names = event.getServletContext().getInitParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            String value = event.getServletContext().getInitParameter(name);
            if(name.trim().equals(KEY)){
                return value;
            }
        }
        return null;
    }
}
