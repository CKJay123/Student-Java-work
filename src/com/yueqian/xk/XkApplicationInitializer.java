package com.yueqian.xk;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 将我们平时在web.xml中的配置放到这里
 */
public class XkApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 用来配置ContextLoaderListener创建的应用上下文的bean
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        //Spring的相关配置
        return new Class[]{SpringConfig.class};
    }

    /**
     * 用来定义DispatcherServlet应用上下文中的bean
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        //Springmvc相关的配置
        return new Class[]{SpringMvcConfig.class};
    }

    /**
     * 配置拦截器
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        //返回映射到DispatcherServlet的请求路径
        return new String[]{"/"};
    }
}
