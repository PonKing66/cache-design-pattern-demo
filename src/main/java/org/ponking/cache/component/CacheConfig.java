package org.ponking.cache.component;

import cn.hutool.cache.Cache;
import cn.hutool.cache.impl.TimedCache;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author ponking
 * @Date 2021/3/11 16:29
 */
@Component
public class CacheConfig {

    @Bean
    public Cache cache() {
        TimedCache<Object, Object> timedCache = new TimedCache<>(10000);
        return timedCache;
    }
}
