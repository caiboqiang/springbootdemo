package com.springboot.springbootdemo.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis工具类
 */
public class redisClient {
    @Autowired
    private JedisPool jedisPool;
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

    /*
     *具体应用
     *redisClient.set("redis_key", "abc");
     *String value=redisClient.get("redis_key");
     *logger.info("redis_key value:{}", value);
     */
}