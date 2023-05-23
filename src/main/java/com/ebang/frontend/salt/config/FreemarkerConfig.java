package com.ebang.frontend.salt.config;

import freemarker.template.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class FreemarkerConfig {
    private static Configuration configuration;

    @Bean
    public Configuration config() {
        configuration = new Configuration(Configuration.VERSION_2_3_32);
        configuration.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "/templates/");
        return configuration;
    }
}
