package cn.core.dao;

import java.util.List;


/**
 * 基本的Dao层接口，提供基本的增删改查方法
 * @author wangrui 2019-1-25 15:35:25
 * @param <T> 任意类型的实体类
 */
public interface BaseDao<T> {

    int addOne(T t);

/*    T getOneById(int id);

    int putByObject(T t);

    int delOneById(int id);

    List<T> getList(T t);

    List<T> getAll(T t);*/
}
