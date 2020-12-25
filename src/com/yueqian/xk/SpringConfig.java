package com.yueqian.xk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 实际上就是之前spring-bean.xml中的配置
 */
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Controller.class))
@EnableTransactionManagement//启动事务管理
public class SpringConfig {

     @Bean  //<bean name="" class="">
     public PlatformTransactionManager transactionManager(DataSource dataSource){
         DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
         transactionManager.setDataSource(dataSource);
         return transactionManager;
     }
}
