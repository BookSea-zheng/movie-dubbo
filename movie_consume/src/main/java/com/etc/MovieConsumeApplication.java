package com.etc;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableDubbo(scanBasePackages = "com.etc.service")
public class MovieConsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieConsumeApplication.class, args);
    }

}
