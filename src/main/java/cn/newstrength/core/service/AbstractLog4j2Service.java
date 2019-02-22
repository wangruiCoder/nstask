package cn.newstrength.core.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractLog4j2Service<T> {
    private Class<T> logClass;
    public Logger logger = LogManager.getLogger(logClass);
}
