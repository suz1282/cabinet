package com.suzhou.cabinet;

import com.suzhou.cabinet.config.CommonConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CommonConfig.class})
@ComponentScan("com.suzhou")
@MapperScan(value = "com.suzhou.cabinet.mapper")
public class CabinetApplication {

    public static void main(String[] args) {
        SpringApplication.run(CabinetApplication.class, args);
    }

}
