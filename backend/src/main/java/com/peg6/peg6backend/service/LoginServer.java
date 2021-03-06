package com.peg6.peg6backend.service;

import com.peg6.peg6backend.entity.User;
import com.peg6.peg6backend.mapper.UserMapper;
import com.peg6.peg6backend.resp.CommonResp;
import com.peg6.peg6backend.resp.LoginUserResp;
import com.peg6.peg6backend.resp.UserResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServer {
    @Resource
    private UserMapper userMapper;

    public CommonResp getUserByUsernameAndPassword(String username, String password) {
        CommonResp<LoginUserResp> resp = new CommonResp<>();
        User user = userMapper.getUserByUsernameAndPassword(username, password);
        LoginUserResp loginUserResp = null;
        if (user != null) {
            loginUserResp = new LoginUserResp();
            UserResp userResp = new UserResp();
            BeanUtils.copyProperties(user, userResp);
            loginUserResp.setUserResp(userResp);
        }

        if (loginUserResp == null) {
            resp.setSuccess(false);
            resp.setMessage("Username or Password is Wrong!");
        } else {
            resp.setContent(loginUserResp);
        }
        return resp;
    }
}
