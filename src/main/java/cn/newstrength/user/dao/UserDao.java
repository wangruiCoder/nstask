package cn.newstrength.user.dao;

import cn.newstrength.core.dao.BaseDao;
import cn.newstrength.user.entity.UserBO;

import java.util.Map;

/**
 * 用户Dao
 * @author wangrui
 * @date 2019-1-25 15:33:05
 *
 */
public interface UserDao extends BaseDao<UserBO> {
    UserBO queryByUserName(Map<String,String> queryMap);
}
