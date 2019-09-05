package com.company.springConfig;

import com.company.dao.*;
import com.company.dao.plainImpl.CurriculumDaoImpl;
import com.company.dao.plainImpl.StudentDaoImpl;
import com.company.dao.plainImpl.UserDaoImpl;
import com.company.dao.springImpl.CurriculumDaoSpringImpl;
import com.company.dao.springImpl.UserDaoSpringImpl;
import com.company.service.DeaneryService;
import com.company.service.DeaneryServiceImpl;
import com.company.service.UserService;
import com.company.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerMapping;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.company")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public PlatformTransactionManager getPlatformTransactionManager(){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(getDataSource());
        return manager;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/consolebase?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("robocopr");
        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(getDataSource());
        return template;
    }
}
