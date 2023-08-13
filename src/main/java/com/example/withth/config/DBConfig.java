package com.example.withth.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public interface DBConfig {
    DataSource dataSource();

    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean();

    PlatformTransactionManager transactionManager();
}
