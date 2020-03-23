package com.suzhou.cabinet.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class LocalCacheUtil {

    private static Cache<String, String> cacheFast = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.SECONDS)  //30秒过期
            .initialCapacity(100)
            .maximumSize(500)
            .build();

    private static Cache<String, String> cacheMidium = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)  //5分钟过期，主要使用它
            .initialCapacity(500)
            .maximumSize(2000)
            .build();

    private static Cache<String, String> cacheSlow = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)  //1小时过期
            .initialCapacity(100)
            .maximumSize(500)
            .build();

    public static String getCacheFast(String key){
        return (String)cacheFast.getIfPresent(key);
    }

    public static void setCacheFast(String key, String obj){
        cacheFast.put(key, obj);
    }

    public static void removeCacheFast(String key){
        cacheFast.invalidate(key);
    }

    public static String getCacheMidium(String key){
        return (String)cacheMidium.getIfPresent(key);
    }

    public static void setCacheMidium(String key, String obj){
        cacheMidium.put(key, obj);
    }

    public static void removeCacheMidium(String key){
        cacheMidium.invalidate(key);
    }

    public static String getCacheSlow(String key){
        return (String)cacheSlow.getIfPresent(key);
    }

    public static void setCacheSlow(String key, String obj){
        cacheSlow.put(key, obj);
    }

    public static void removeCacheSlow(String key){
        cacheSlow.invalidate(key);
    }

}
