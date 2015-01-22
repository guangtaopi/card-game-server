package com.silu.game.controller;

import com.silu.game.bean.HttpResp;
import com.silu.game.bean.User;
import com.silu.game.exception.ServerException;
import com.silu.game.service.UserService;
import com.silu.game.util.ObjectMapperFactoryBean;
import com.sun.istack.internal.NotNull;
import net.sf.oval.constraint.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by silu on 15-1-15.
 */
@Controller
@RequestMapping(value = "/dinner", produces = "application/json")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private ObjectMapperFactoryBean objectMapperFactoryBean;

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    @ResponseBody
    public HttpResp register(HttpServletRequest request, @NotNull @NotEmpty @RequestBody User user){
        try {
            user = userService.createUser(user);
        }catch (ServerException e){
            LOGGER.warn("fails to crete user.",e);
            throw e;
        }
        HttpResp resp = HttpResp.newSuccessResp();
        Map<String,String> value = new HashMap<>();
        value.put("user_id",user.getUserId());
        value.put("token_id",user.getTokenId());
        resp.setBody(value);
        return resp;
    }
}
