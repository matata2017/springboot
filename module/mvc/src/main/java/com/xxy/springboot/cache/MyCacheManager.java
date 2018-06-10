package com.xxy.springboot.cache;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

/**
 * Created by xxy on 2018/4/20.
 */
public class MyCacheManager extends AbstractCacheManager {
    @Override
    protected Cache createCache(String name) throws CacheException {
        return null;
    }
}
