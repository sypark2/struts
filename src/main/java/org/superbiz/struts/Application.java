package org.superbiz.struts;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }



    @Bean
    public FilterRegistrationBean filterDispatch(){
        return buildFilterRegistration(2, new StrutsPrepareAndExecuteFilter(),
                Arrays.asList("/", "/addUserForm.action", "/addUser.action", "/findUserForm.action", "/findUser.action", "/listAllUsers.action"));
    }

    @Bean
    public FilterRegistrationBean siteMeshPageFilter(){
        return buildFilterRegistration(1, new SiteMeshFilter(), singletonList("/*"));
    }

    private FilterRegistrationBean buildFilterRegistration(int order, Filter filter, List<String> urlPatterns) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setUrlPatterns(urlPatterns);
        filterRegistrationBean.setOrder(order);
        return filterRegistrationBean;
    }
}
