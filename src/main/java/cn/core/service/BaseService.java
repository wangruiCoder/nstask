package cn.core.service;

import java.util.List;

/**
 * 基础service类
 * @author wangrui
 * @date 2019-1-25 15:51:16
 * @param <T> 任何实体类
 */
public interface BaseService<T> {

    int add(T t);

    T getOneById(int id);

    int putByObject(T t);

    int delOneById(int id);

    List<T> getList(T t);

    List<T> getAll(T t);

}
