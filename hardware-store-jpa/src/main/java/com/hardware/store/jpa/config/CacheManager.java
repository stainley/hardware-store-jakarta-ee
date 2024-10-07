package com.hardware.store.jpa.config;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import redis.clients.jedis.Jedis;

@Singleton
public class CacheManager {

    @Inject
    private RedisConnectionFactory redisConnectionFactory;

    public void put(String key, String value) {
        Jedis jedis = redisConnectionFactory.getJedis();
        jedis.set(key, value);
        jedis.close();
    }

    public String get(String key) {
        Jedis jedis = redisConnectionFactory.getJedis();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    public void delete(String key) {
        Jedis jedis = redisConnectionFactory.getJedis();
        jedis.del(key);
        jedis.close();
    }
}
