package ru.gothmog.jaxb.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public javax.sql.DataSource dataSource() throws Exception {
        return DataSourceBuilder.create()
                .build();
    }

    @Bean("jdbcTemplateBatch")
    public JdbcTemplate jdbcTemplateBatch() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.targetdatasource")
    public javax.sql.DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean("jdbcTemplateBase")
    public JdbcTemplate jdbcTemplateBase() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(secondaryDataSource());
        return jdbcTemplate;
    }
}
