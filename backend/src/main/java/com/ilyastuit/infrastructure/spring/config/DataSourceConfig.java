package com.ilyastuit.infrastructure.spring.config;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${persistence.entity-scan}")
    private String persistenceEntityScan;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;
    @Value("${hibernate.hikari.connectionTimeout}")
    private String hibernateHikariConnectionTimeout;
    @Value("${hibernate.hikari.minimumIdle}")
    private String hibernateHikariMinimumIdle;
    @Value("${hibernate.hikari.maximumPoolSize}")
    private String hibernateHikariMaximumPoolSize;
    @Value("${hibernate.hikari.idleTimeout}")
    private String hibernateHikariIdleTimeout;


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(persistenceEntityScan);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        PersistenceProvider provider = new HibernatePersistenceProvider();
        em.setPersistenceProvider(provider);

        return em;
    }

    @Bean
    public DataSource dataSource() {
        final HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDataSourceJNDI(jdbcUrl);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty(HBM2DDL_AUTO, hibernateHbm2ddlAuto);
        properties.setProperty(SHOW_SQL, hibernateShowSql);
        properties.setProperty(DIALECT, hibernateDialect);

        properties.setProperty("hibernate.hikari.connectionTimeout", hibernateHikariConnectionTimeout);
        properties.setProperty("hibernate.hikari.minimumIdle", hibernateHikariMinimumIdle);
        properties.setProperty("hibernate.hikari.maximumPoolSize", hibernateHikariMaximumPoolSize);
        properties.setProperty("hibernate.hikari.idleTimeout", hibernateHikariIdleTimeout);

        return properties;
    }
}