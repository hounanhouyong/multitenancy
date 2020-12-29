package com.hn.multitenancy.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan("com.hn.multitenancy.service.dao")
@SpringBootApplication(scanBasePackages = {"com.hn.multitenancy"}, exclude = {HibernateJpaAutoConfiguration.class})
@ServletComponentScan(basePackages = {"com.hn.multitenancy.web"})
public class MultitenancyBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultitenancyBootstrapApplication.class, args);
    }

}
