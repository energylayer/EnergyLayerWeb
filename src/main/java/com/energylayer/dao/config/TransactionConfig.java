package com.energylayer.dao.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * @author rkotelnikov
 */

@Configuration
@EnableTransactionManagement
public class TransactionConfig implements TransactionManagementConfigurer{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new HibernateTransactionManager(sessionFactory);
    }
}
