package top.kenan.week09.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import top.kenan.week09.filter.AuthFilter;
import top.kenan.week09.filter.CORSFilter;
import top.kenan.week09.filter.CustomFilter;


@Configuration
public class FilterConfig {

    // 第一个注册：CustomFilter，顺序 1 → 最先执行
    @Bean
    public FilterRegistrationBean<CustomFilter> customFilterRegistration() {
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1); // 顺序越小越先执行
        return registrationBean;
    }

    // 第二个注册：AuthFilter，顺序 2 → 后执行
    // 必须带 Authorization: admin 才能通过
    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistration() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<CORSFilter> corsFilter() {
        FilterRegistrationBean<CORSFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CORSFilter());
        registrationBean.addUrlPatterns("/api/hello");
        // 数值越小越先执行：必须早于所有的过滤器，否则未认证/预检请求在中间被拦截时根本不会走到本过滤器
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
}
