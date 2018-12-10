package com.springboot.springbootdemo.shrio;
import com.springboot.springbootdemo.common.util.ShiroUtils;
import com.springboot.springbootdemo.dao.UserInfoMapper;
import com.springboot.springbootdemo.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Slf4j
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    RedisSessionDAO redisSessionDAO;
    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("=============身份认证方法=================");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserInfo userInfo= userInfoMapper.getName(token.getUsername());
        if(userInfo == null){
            throw new AuthenticationException("用户名错误或不存在");
        }/*else if(!userInfo.getUserPassword().equals(new String((char[]) token.getCredentials()))){
            throw new AccountException("密码不正确");
        }*/
        String pass = ShiroUtils.encryptPassword("MD5", new String((char[]) token.getCredentials()),userInfo.getUserSalt());
        if(!userInfo.getUserPassword().equals(pass)){
            throw new AccountException("密码不正确");
        }

        //如果当前用户已登入踢掉以登入的用户。
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        for (Session session: sessions) {

            //结合redis 验证用户不可以重复登入（Session数据缓存到redis）
            Object obj = session.getAttribute("USER_SESSION");
            if(obj != null){
                JSONObject jsonObject = JSONObject.fromObject(obj.toString());
                String name = jsonObject.getString("userPhone");
                if(name.equals(token.getUsername())){
                    redisSessionDAO.delete(session);
                    log.info("=======用户{}被挤下线=========",name);
                }
            }

            //一般验证用户不可以重复登入session保存在本地
           /* UserInfo sysUser = (UserInfo)session.getAttribute("USER_SESSION");
            // 如果session里面有当前登陆的，则证明是重复登陆的，则将其剔除
            if( sysUser!=null ){
                if( token.getUsername().equals( sysUser.getUserPhone() ) ){
                    session.setTimeout(0);
                }
            }*/
        }
        log.info("{}=============================={}",token.getPrincipal(),this.getName());
        //盐值
        String salt = userInfo.getUserSalt();
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, userInfo.getUserPassword(), credentialsSalt,this.getName());
        //保存到session
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("USER_SESSION", userInfo);
        return authenticationInfo;
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

}
