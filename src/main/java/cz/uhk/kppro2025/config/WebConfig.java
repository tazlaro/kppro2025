// Class for adding the HttpServletRequest to the Thymeleaf context
// This class is used to add the HttpServletRequest to the Thymeleaf context so that it can be accessed in Thymeleaf templates.

// Created for URL redirection in the AddressController
// - which is not working as expected => not used in the project

package cz.uhk.kppro2025.config;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import java.util.Map;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ThymeleafViewResolver thymeleafViewResolver;
    private final HttpServletRequest httpServletRequest;

    public WebConfig(ThymeleafViewResolver thymeleafViewResolver, HttpServletRequest httpServletRequest) {
        this.thymeleafViewResolver = thymeleafViewResolver;
        this.httpServletRequest = httpServletRequest;
    }

    @PostConstruct
    public void addThymeleafContextVariables() {
        thymeleafViewResolver.setStaticVariables(Map.of("httpServletRequest", httpServletRequest));
    }
}