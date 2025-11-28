package com.example;

import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.domain.repository")
@EntityScan(basePackages = "com.example.domain.model")
@ComponentScan(basePackages = "com.example.domain")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        @NotNull String url = System.getProperty("APP_JDBC_URL");
        @NotNull String user = System.getProperty("APP_DB_USER");
        @NotNull String pass =  System.getProperty("APP_DB_PASS");

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pass);
        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(ds);
        emf.setPackagesToScan("com.example.domain.model");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> props = new HashMap<>();
        // Dialect selection based on JDBC URL
        String url = ds instanceof HikariDataSource h ? h.getJdbcUrl() : "";

        if (url.contains("postgresql")) {
            props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        } else if (url.contains("mysql")) {
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        }

        emf.setJpaPropertyMap(props);
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) {
        if (emf.getObject() == null) {
            throw new IllegalStateException("EntityManagerFactory failed to initialize");
        }
        return new JpaTransactionManager(emf.getObject());
    }

}
