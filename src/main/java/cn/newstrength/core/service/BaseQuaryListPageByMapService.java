package cn.newstrength.core.service;

import java.util.List;
import java.util.Map;

public interface BaseQuaryListPageByMapService<T> {
    List<T> quaryListPage(Map<String,Object> map);
}
