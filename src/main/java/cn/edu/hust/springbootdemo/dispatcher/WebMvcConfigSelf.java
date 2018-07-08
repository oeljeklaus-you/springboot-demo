package cn.edu.hust.springbootdemo.dispatcher;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Component
public class WebMvcConfigSelf extends WebMvcConfigurationSupport {
    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //这里排除登录的请求路径
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns("/user/login").addPathPatterns("/**");
    }

    /**
     * 添加静态资源
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/")
        .addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }


}
