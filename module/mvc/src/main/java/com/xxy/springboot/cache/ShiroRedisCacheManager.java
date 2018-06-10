package com.xxy.springboot.cache;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by xxy on 2017/4/25.
 */
public class ShiroRedisCacheManager extends AbstractCacheManager {
    private RedisTemplate<byte[],byte[]> redisTemplate;
    /**
     * 缓存有效期
     */
    private int expireMinutes = 30;
    public ShiroRedisCacheManager(RedisTemplate<byte[], byte[]> shiroRedisTemplate) {
        this.redisTemplate = shiroRedisTemplate;
    }
    public ShiroRedisCacheManager(RedisTemplate<byte[], byte[]> shiroRedisTemplate, int expireMinutes) {
        this.redisTemplate = shiroRedisTemplate;
        this.expireMinutes = expireMinutes;
    }
    @Override
    protected Cache<byte[], byte[]> createCache(String name) throws CacheException {
        return new ShiroRedisCache<byte[], byte[]>(redisTemplate, name, expireMinutes);
    }

    /**
     * Sets new 缓存有效期.
     *
     * @param expireMinutes New value of 缓存有效期.
     */
    public void setExpireMinutes(int expireMinutes) {
        this.expireMinutes = expireMinutes;
    }

    /**
     * Gets 缓存有效期.
     *
     * @return Value of 缓存有效期.
     */
    public int getExpireMinutes() {
        return expireMinutes;
    }
}
