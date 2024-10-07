package com.hardware.store.ejb.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Startup
@Singleton
public class RedisConfig {

    private RedisClient redisClient;
    private StatefulRedisConnection<String, String> connection;

    @PostConstruct
    public void init() {
        redisClient = RedisClient.create("redis://localhost:6379");
        connection = redisClient.connect();
    }

    @PreDestroy
    public void destroy() {
        connection.close();
        redisClient.shutdown();
    }

    public RedisCommands<String, String> getCommands() {
        return connection.sync();
    }

}
