package com.komix.sample.config;

import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.springmvc.ResteasyHandlerAdapter;
import org.jboss.resteasy.springmvc.ResteasyHandlerMapping;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created by krepela on 15.4.2017.
 */
@Configuration
public class RestConfig extends WebMvcConfigurerAdapter {

    @ConditionalOnMissingBean(ResteasyDeployment.class)
    @Bean(initMethod="start", destroyMethod="stop")
    public ResteasyDeployment resteasyDeployment(final SpringBeanProcessor springBeanProcessor) {
        ResteasyDeployment resteasyDeployment = new ResteasyDeployment() {
            public void start() {
                super.start();
                if (springBeanProcessor.getRegistry() == null) {
                    springBeanProcessor.setRegistry(this.getRegistry());
                }
            }
        };
        resteasyDeployment.setProviderFactory(springBeanProcessor.getProviderFactory());
        return resteasyDeployment;
    }

    @Bean
    @ConditionalOnMissingBean(SpringBeanProcessor.class)
    public static SpringBeanProcessor springBeanProcessor() {
        SpringBeanProcessor springBeanProcessor = new SpringBeanProcessor();
        springBeanProcessor.setProviderFactory(new ResteasyProviderFactory());
        return springBeanProcessor;
    }

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping(ApplicationContext applicationContext) {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        mapping.setApplicationContext(applicationContext);
        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return mapping;
    }

    @Bean
    @ConditionalOnMissingBean(ResteasyHandlerMapping.class)
    public ResteasyHandlerMapping resteasyHandlerMapper(ResteasyDeployment deployment) {
        ResteasyHandlerMapping handlerMapping = new ResteasyHandlerMapping(deployment);
        handlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        // we want underlying server 404 on missing resource
        // otherwise javax.ws.rs.NotFoundException must be mapped
        handlerMapping.setThrowNotFound(false);
        return handlerMapping;
    }

    @Bean
    @ConditionalOnMissingBean(ResteasyHandlerAdapter.class)
    public ResteasyHandlerAdapter resteasyHandlerAdapter(ResteasyDeployment deployment) {
        return new ResteasyHandlerAdapter(deployment);
    }

}
