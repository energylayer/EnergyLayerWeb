package com.energylayer.web.config;

import com.energylayer.service.config.ServiceConfig;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author rkotelnikov
 */

@Configuration
@Import({ServiceConfig.class})
//@PropertySource({"classpath:/persistence.properties"})
public class RootConfig {

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocations(new Resource[]{
                new ClassPathResource("/persistence.properties"),
                new ClassPathResource("/application.properties")});
        return ppc;
    }
}
