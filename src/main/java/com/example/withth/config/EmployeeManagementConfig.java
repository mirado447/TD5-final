package com.example.withth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "employeeManagementEntityManagerFactory",
        basePackages = {"com.example.withth.repository.employeeManagement"},
        transactionManagerRef = "employeeManagementTransactionManager"
)
public class EmployeeManagementConfig implements DBConfig{
    private final Environment env;

    public EmployeeManagementConfig(Environment env) {
        this.env = env;
    }

    @Bean(name = "employeeManagementDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("employeeManagement.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("employeeManagement.datasource.url"));
        dataSource.setUsername(env.getProperty("employeeManagement.datasource.username"));
        dataSource.setPassword(env.getProperty("employeeManagement.datasource.password"));
        return dataSource;
    }

    @Bean("employeeManagementEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        var entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("employeeManagement.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("spring.jpa.show-sql", env.getProperty("employeeManagement.jpa.show-sql"));

        entityManager.setJpaPropertyMap(properties);
        entityManager.setPackagesToScan("com.example.withth.models.employeeManagement");
        return entityManager;
    }

    @Bean("employeeManagementTransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
