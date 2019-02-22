package cn.newstrength.core.service;

import java.util.List;
import java.util.Map;

public interface BaseQuaryListByMapService<T> {
    List<T> quaryList(Map<String,Object> map);
}
