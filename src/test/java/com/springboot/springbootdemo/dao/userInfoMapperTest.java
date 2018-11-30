package com.springboot.springbootdemo.dao;

import com.springboot.springbootdemo.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
public class userInfoMapperTest {

    @Autowired
    private UserInfoMapper use;

    @Test
    public void getAll() {
        List<UserInfo> u  = use.getAll();
      for (UserInfo f :u){
          System.out.println(f.getId());

      }
    }
}