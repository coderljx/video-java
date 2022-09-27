package com.example.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class Redis {
    private final RedisTemplate<String,Object> redisTemplate;

    @Value("${redis.timeout}")
    private long timeout;
    @Autowired
    public Redis(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void changeDB(int db){
        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory)  this.redisTemplate.getConnectionFactory();
        if (connectionFactory != null && connectionFactory.getDatabase() != db) {
            connectionFactory.setDatabase(db);
            this.redisTemplate.setConnectionFactory(connectionFactory);
            connectionFactory.afterPropertiesSet();
            connectionFactory.resetConnection();
        }
    }

    public void setString(String userid,String value) {
        this.redisTemplate.opsForValue().set(userid ,value, Duration.ofSeconds(timeout));
    }

    @SuppressWarnings("unchecked")
    public <T> T getKey(String key, T type){
        Object res = this.redisTemplate.opsForValue().get(key);
        return (T) res;
    }




}
