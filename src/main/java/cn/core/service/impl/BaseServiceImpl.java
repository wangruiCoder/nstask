package cn.core.service.impl;

import cn.core.dao.BaseDao;
import cn.core.service.BaseService;

import java.util.List;

/**
 * 基础的service实现类
 * @author wangrui
 * @date 2019-1-25 15:52:20
 * @param <T> 任何实体类
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao<T> baseDao;

    protected void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public int add(T t) {
        return baseDao.addOne(t);
    }

    @Override
    public T getOneById(int id) {
        return null;
    }

    @Override
    public int putByObject(T t) {
        return 1;
    }

    @Override
    public int delOneById(int id) {
        return 1;
    }

    @Override
    public List<T> getList(T t) {
        return null;
    }

    @Override
    public List<T> getAll(T t) {
        return null;
    }
}
