package com.suzhou.cabinet.config;

import com.suzhou.cabinet.interceptor.LogAspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @author leo
 * @since 2018/7/27 8:45
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({SwaggerConfig.class})
public class CommonConfig {

}
