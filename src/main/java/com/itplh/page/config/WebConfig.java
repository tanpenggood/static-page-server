package com.itplh.page.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private StaticPageProperties staticPageProperties;

    public WebConfig(StaticPageProperties staticPageProperties) {
        this.staticPageProperties = staticPageProperties;
        System.out.println(this.staticPageProperties);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        staticPageProperties.getResourceMappings().forEach((k, v) -> {
            registry.addResourceHandler(String.format("/%s/**", k)).addResourceLocations(String.format("file:%s", v));
        });
        // 系统静态资源访问路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/templates/");
    }
}
