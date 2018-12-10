package com.springboot.springbootdemo.redisConfiguration;

import com.springboot.springbootdemo.common.util.ShiroUtils;
import com.springboot.springbootdemo.shrio.CustomRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @CalssName RedisShiroConfig
 * @Author caiboqiang
 * @Data 2018/12/7 15:48
 * @Version 1.0
 **/
@Slf4j
@Configuration
public class RedisShiroConfig {
    @Value("${spring.redis.host}")
    private String IP;
    @Value("${spring.redis.port}")
    private int HOST;
    /**
     * 4. 配置LifecycleBeanPostProcessor，可以来自动的调用配置在Spring IOC容器中 Shiro Bean 的生命周期方法
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        log.info("=====lifecycleBeanPostProcessor成功:1======");
        return new LifecycleBeanPostProcessor();
    }
    /**
     * 2 启用IOC容器中使用Shiro的注解，但是必须配置第四步才可以使用
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        log.info("=================defaultAdvisorAutoProxyCreator成功:2==================");
        return new DefaultAdvisorAutoProxyCreator();
    }
    @Bean
    public SecurityManager securityManager(){
        log.info("=====lifecycleBeanPostProcessor成功:3======");
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(realm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());

        return securityManager;
    }
    /**
     * 3. 配置Realm
     * @return
     */
    @Bean
    public CustomRealm realm(){
        log.info("=====realm成功:4======");
        CustomRealm realm = new CustomRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 指定加密算法
        matcher.setHashAlgorithmName(ShiroUtils.algorithmName);
        // 指定加密次数
        matcher.setHashIterations(ShiroUtils.hashIterations);
        // 指定这个就不会报错
        matcher.setStoredCredentialsHexEncoded(true);
        realm.setCredentialsMatcher(matcher);
        return realm;
    }
    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis 继承了RedisSessionDAO类
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        log.info("=====redisSessionDAO成功:5======");
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }
    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisManager redisManager() {
        log.info("=====redisManager成功:6======");
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(IP);
        redisManager.setPort(HOST);
        redisManager.setTimeout(1800000);
        redisManager.setExpire(1800);// 配置缓存过期时间
        // redisManager.setPassword(password);
        return redisManager;
    }
    /**
     * shiro session的管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        log.info("=====sessionManager成功:7======");
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());     //设置redisSessionDAO
        sessionManager.setSessionIdCookie(cookie());        // 设置JSESSIONID
        //sessionManager.setDeleteInvalidSessions(true);      // 删除无效session
        return sessionManager;
    }
    @Bean
    public SimpleCookie cookie(){
        log.info("=====cookie成功:8======");
        SimpleCookie cookie = new SimpleCookie("SHAREJSESSIONID"); //  cookie的name,对应的默认是 JSESSIONID
        cookie.setHttpOnly(true);
        cookie.setPath("/");        //  path为 / 用于多个系统共享JSESSIONID
        return cookie;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {

        log.info("=================shirFilter成功:9==================");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/notLogin");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

        // 设置拦截器（配置访问权限）
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //游客，开发权限
        filterChainDefinitionMap.put("/guest/**", "anon");
        //用户，需要角色权限 “user”
        filterChainDefinitionMap.put("/user/**", "roles[user]");
        //管理员，需要角色权限 “admin”
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        //开放登陆接口
        filterChainDefinitionMap.put("/login", "anon");
        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println();
        log.info("=================Shiro拦截器工厂类注入成功:9==================");
        return shiroFilterFactoryBean;
    }
    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        log.info("=====authorizationAttributeSourceAdvisor成功:10======");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator(){
        log.info("=====sessionIdGenerator成功:11======");
        return new JavaUuidSessionIdGenerator();
    }
}
