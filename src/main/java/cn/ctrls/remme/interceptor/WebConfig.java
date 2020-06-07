package cn.ctrls.remme.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource(name="loginInterceptor")
    private LoginInterceptor loginInterceptor;

    @Resource(name = "managerInterceptor")
    private ManagerInterceptor managerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //所有页面都进行状态更新
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        //对后台管理页面进行权限拦截
        registry.addInterceptor(managerInterceptor).addPathPatterns("/manager/**");
    }
}
