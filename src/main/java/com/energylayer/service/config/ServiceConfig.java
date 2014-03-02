package com.energylayer.service.config;

import com.energylayer.dao.config.DaoConfig;
import com.energylayer.utils.service.ServiceUtilsConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: rkotelnikov
 */
@Configuration
@Import({DaoConfig.class, ServiceUtilsConfig.class})
@ComponentScan(basePackages = {"com.energylayer.service"})
public class ServiceConfig {
}
