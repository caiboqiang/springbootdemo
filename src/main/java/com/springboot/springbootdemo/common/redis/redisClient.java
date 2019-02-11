package com.springboot.springbootdemo.common.redis;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collection;

/**
 * redis工具类
 */
public class redisClient {
    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private RedisTemplate redisTemplate;// Redis操作类，对这个使用不熟悉的，可以参考前面的博客
    @Autowired
    private SessionDAO sessionDAO;
    //set数据
    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            //jedis.expire(key,TimeUnit.SECONDS);
            //jedis.expir
        } finally {
            //返还到连接池
            jedis.close();
        }
    }
    //获取数据
    public String get(String key) throws Exception  {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            //返还到连接池
            jedis.close();
        }
    }
    //@Override// 获取活跃的session，可以用来统计在线人数，如果要实现这个功能，可以在将session加入redis时指定一个session前缀，统计的时候则使用keys("session-prefix*")的方式来模糊查找redis中所有的session集合
    public Collection<Session> getActiveSessions() {
        System.out.println("==============getActiveSessions=================");
        //return redisTemplate.keys("*");
        return sessionDAO.getActiveSessions();
    }


    /*
     *具体应用
     *redisClient.set("redis_key", "abc");
     *String value=redisClient.get("redis_key");
     *logger.info("redis_key value:{}", value);
     */
}