package com.small;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@EnableRedisHttpSession//开启redis托管session
@SpringBootApplication
@MapperScan({"com.small.mapper"})
@EnableTransactionManagement //开启事务支持
public class SmallLoanH5Application {
    public static void main(String[] args) {
        SpringApplication.run(SmallLoanH5Application.class, args);
    }
}
