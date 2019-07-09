package practice.spring.example.project;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/**");
    }

    @Bean
    public FilterRegistrationBean<MainFilter> myFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new MainFilter());
        bean.setUrlPatterns(Arrays.asList("/user/list"));
        return bean;
    }

}
