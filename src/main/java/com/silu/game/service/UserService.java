package com.silu.game.service;

import com.silu.game.bean.User;
import com.silu.game.constant.StatusCode;
import com.silu.game.exception.ServerException;
import com.silu.game.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by silu on 15-1-15.
 */
@Service
public class UserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    
//    @Autowired
//    private UserDAO userDAO;
    
    @Autowired
    private SessionUtil sessionUtil;

    public User createUser(User user) throws ServerException{
        user.setUserId(sessionUtil.newUserId());
        user.setTokenId(sessionUtil.newTokenId());
        try {
//            userDAO.create(user);
        }catch (Exception e){
            LOGGER.warn("fails to create user",e);
            throw new ServerException(StatusCode.USER_CREATE_FAILED,"fails to create user");
        }
        return user;
    }



}
