package com.suzhou.cabinet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author leo
 * @since 2018/7/27 8:45
 */
@Configuration
@Import({SwaggerConfig.class})
public class CommonConfig {

}
