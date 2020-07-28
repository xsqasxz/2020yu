package com.small.config;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * 这里是配置数据连接池和 tk.mybatis的位置
 * @since 2020年5月8日 09:58:52
 * @author xueshiqi
 */
@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MapperScannerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //mapper类的包名
        mapperScannerConfigurer.setBasePackage("com.small.mapper");
        Properties properties = new Properties();
        properties.setProperty("mappers", Mapper.class.getName());
        properties.setProperty("notEmpty","false");
        properties.setProperty("IDENTITY", "MYSQL");
        properties.setProperty("ORDER","BEFORE");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}