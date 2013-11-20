package com.energylayer.dao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: rkotelnikov
 */
@Configuration
@Import({DataSourceConfig.class,
        TransactionConfig.class})
@ComponentScan("com.energylayer.dao")
public class DaoConfig {
}
