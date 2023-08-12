package com.example.withth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "cnapsEntityManagerFactory",
        basePackages = {"com.example.withth.repository.cnaps"},
        transactionManagerRef = "cnapsTransactionManager"
)
public class CnapsConfig {
    private final Environment environment;

    public CnapsConfig(Environment environment) {
        this.environment = environment;
    }

    @Primary
    @Bean(name = "cnapsDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setPassword("com.example.withth.repository.cnaps");
        dataSource.setUsername(environment.getProperty("cnaps.datasource.username"));
        dataSource.setPassword(environment.getProperty("cnaps.datasource.password"));
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("cnaps.datasource.driver-class-name")));
        return dataSource;
    }

}
