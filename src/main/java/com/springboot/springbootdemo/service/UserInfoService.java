package com.springboot.springbootdemo.service;

import com.springboot.springbootdemo.dao.UserInfoMapper;
import com.springboot.springbootdemo.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper user;
    public List<UserInfo> get(){
        return user.getAll();
    }
}
