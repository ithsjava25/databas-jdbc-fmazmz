package com.example;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        String url = System.getProperty("APP_JDBC_URL");
        String user = System.getProperty("APP_DB_USER");
        String pass =  System.getProperty("APP_DB_PASS");

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pass);
        return dataSource;
    }
}
