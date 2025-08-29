package com.example.hiringsqlbot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private User user = new User();
    private Endpoints endpoints = new Endpoints();
    private SqlFiles sql = new SqlFiles();

    @Getter @Setter public static class User { private String name; private String regNo; private String email; }
    @Getter @Setter public static class Endpoints { private String base; private String generatePath; private String fallbackSubmitPath; }
    @Getter @Setter public static class SqlFiles { private String oddFile; private String evenFile; private String outputFile; }
}
