package cn.newstrength.core.dao;

import java.util.List;
import java.util.Map;


/**
 * 基本的Dao层接口，提供基本的增删改查方法
 * @author wangrui 2019-1-25 15:35:25
 * @param <T> 任意类型的实体类
 */
public interface BaseDao<T> {

    int insertOne(T t);

    T quaryOneById(int id);

    int updateOneByObject(T t);

    int deleteOneById(int id);

    List<T> getListPage(Map<String,Object> map);

    List<T> getList(Map<String,Object> map);
}
