package com.hardware.store.ejb.config;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.inject.Singleton;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

@Singleton
public class RedisConnectionFactory {

    @Resource(lookup = "java:global/properties/redis")
    private Properties redisProperties;

    private JedisPool jedisPool;

    @PostConstruct
    public void init() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(Integer.parseInt(redisProperties.getProperty("redis.maxTotal")));
        poolConfig.setMaxIdle(Integer.parseInt(redisProperties.getProperty("redis.maxIdle")));
        poolConfig.setMinIdle(Integer.parseInt(redisProperties.getProperty("redis.minIdle")));
        poolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(poolConfig, redisProperties.getProperty("redis.host"), Integer.parseInt(redisProperties.getProperty("redis.port")));
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    @PreDestroy
    public void destroy() {
        jedisPool.destroy();
    }
}
