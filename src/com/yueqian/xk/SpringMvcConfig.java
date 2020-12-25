package com.yueqian.xk;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 之前在springmvc.xml中配置H
 */
@Configuration
@EnableWebMvc
@ComponentScan(includeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Controller.class))
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 添加视图控制器，如果没有指定，就访问这里配置的路径
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       registry.addViewController("/").setViewName("login");//默认访问/login.jsp
    }

    /**
     * 配置视图解析器
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //定义了一个内部资源视图解析器（）
        registry.jsp("/",".jsp");
    }

    /**
     * 配置默认的servlet访问（释放静态资源）
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
       configurer.enable();
    }
}
