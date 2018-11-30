package com.springboot.springbootdemo.shrio;

import com.springboot.springbootdemo.common.err.helloException;
import com.springboot.springbootdemo.dao.UserInfoMapper;
import com.springboot.springbootdemo.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRealm.class);
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        LOGGER.info("=============身份认证方法=================");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserInfo userInfo= userInfoMapper.getName(token.getUsername());
        if(userInfo == null){
            throw new AuthenticationException("用户名错误或不存在");
        }/*else if(!userInfo.getUserPassword().equals(new String((char[]) token.getCredentials()))){
            throw new AccountException("密码不正确");
        }*/
        LOGGER.info("{}=============================={}",token.getPrincipal(),this.getName());
        //盐值
       // ByteSource credentialsSalt = ByteSource.Util.bytes("");
        String salt = "3bc8026e0b6264010815";
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        //SimpleAuthenticationInfo authenticationInfo =new SimpleAuthenticationInfo(user, user.getPassWord(), ByteSource.Util.bytes(user.getSalt()),getName());
        return new SimpleAuthenticationInfo(userInfo, token.getCredentials(), credentialsSalt,this.getName());
    }
    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        //String role = userMapper.getRole(username);
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        //set.add(role);
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;
    }

    /**
     * 密码验证规则
     */
   /* @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName("MD5");//散列算法:MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512等。
        shaCredentialsMatcher.setHashIterations(1);//散列的次数，默认1次， 设置两次相当于 md5(md5(""));
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }*/

}
