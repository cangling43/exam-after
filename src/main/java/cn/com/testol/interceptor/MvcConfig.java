package cn.com.testol.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.com.testol.interceptor.AccessInterceptor;
/** SpringBoot2使用这种写法 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {


    /* 拦截器配置 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
    }

}