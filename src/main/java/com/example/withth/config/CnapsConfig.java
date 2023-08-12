package com.example.withth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "cnapsEntityManagerFactory",
        basePackages = {"com.example.withth.repository.cnaps"},
        transactionManagerRef = "cnapsTransactionManager"
)
public class CnapsConfig {
    private final Environment env;

    public CnapsConfig(Environment env) {
        this.env = env;
    }

    @Primary
    @Bean(name = "cnapsDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setPassword("com.example.withth.repository.cnaps");
        dataSource.setUsername(env.getProperty("cnaps.datasource.username"));
        dataSource.setPassword(env.getProperty("cnaps.datasource.password"));
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("cnaps.datasource.driver-class-name")));
        return dataSource;
    }

    @Primary
    @Bean("cnapsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        var entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("cnaps.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

        entityManager.setJpaPropertyMap(properties);
        entityManager.setPackagesToScan("com.example.withth.models.cnaps");
        return entityManager;
    }

    @Primary
    @Bean("cnapsTransactionManager")
    public PlatformTransactionManager userTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
