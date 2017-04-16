package com.komix.sample;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.WebApplicationInitializer;

@Configuration
@Import(value = {
    EmbeddedServletContainerAutoConfiguration.class,
    DispatcherServletAutoConfiguration.class,
    ServerPropertiesAutoConfiguration.class
})
@ComponentScan(basePackages = "com.komix.sample")
public class WebApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class)
                .bannerMode(Banner.Mode.OFF);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholder() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .build()
                .run(args);
    }

}
