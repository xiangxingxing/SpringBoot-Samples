package me.levi.mall.tiny.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan({"me.levi.mall.tiny.mbg.mapper", "me.levi.mall.tiny.dao"})
public class MyBatisConfig {
}
