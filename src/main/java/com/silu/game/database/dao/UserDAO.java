package com.silu.game.database.dao;

import com.silu.game.bean.User;
import com.silu.game.database.DataSource;
import org.apache.ibatis.annotations.Param;

/**
 * Created by silu on 15-1-15.
 */
@DataSource("mysql001.game")
public interface UserDAO {
    public int create(@Param("user") User user);

    public User get(@Param("userId") String userId);

    public int delete(@Param("userId") String id);

}
