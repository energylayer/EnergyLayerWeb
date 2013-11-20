package com.energylayer.dao.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author rkotelnikov
 */

@Configuration
public class DataSourceConfig {

    @Value("${dataSource.driverClassName}")
    private String driver;
    @Value("${dataSource.url}")
    private String url;
    @Value("${dataSource.username}")
    private String username;
    @Value("${dataSource.password}")
    private String password;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Bean
    public SessionFactory sessionFactory(){
        return new LocalSessionFactoryBuilder(dataSource())
                .scanPackages("com.energylayer.entity")
                .addProperties(hibernateProperties())
                .buildSessionFactory();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public Properties hibernateProperties(){
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(org.hibernate.cfg.Environment.DIALECT, dialect);
        hibernateProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2ddlAuto);
        return hibernateProperties;
    }

}
