package com.jkdev.placeRest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.logging.Logger;

@Configuration
@EnableJpaRepositories("com.jkdev.placeRest.repository")
public class DatabaseConfig implements WebMvcConfigurer {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Bean
    DataSource dataSource() {

        /*return DataSourceBuilder.create().url("jdbc:mysql://us-cdbr-iron-east-05.cleardb.net/heroku_b60a08d9c1a257a?useSSL=false&serverTimezone=UTC").
                driverClassName("com.mysql.cj.jdbc.Driver").
                username("bd468378fdca6e").
                password("4ad2bbad").build();*/
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://us-cdbr-iron-east-05.cleardb.net/heroku_b60a08d9c1a257a?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("bd468378fdca6e");
        dataSource.setPassword("4ad2bbad");
        return dataSource;

    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        // create session factorys
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set the properties
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.jkdev.placeRest.entity");

        return sessionFactory;
    }



}
