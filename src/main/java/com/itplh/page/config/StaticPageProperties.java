package com.itplh.page.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "static-page")
public class StaticPageProperties {

    private Map<String, String> resourceMappings;

    public Map<String, String> getResourceMappings() {
        return resourceMappings;
    }

    public void setResourceMappings(Map<String, String> resourceMappings) {
        this.resourceMappings = resourceMappings;
    }

    @Override
    public String toString() {
        return "StaticPageProperties{" +
                "resourceMappings=" + resourceMappings +
                '}';
    }
}
