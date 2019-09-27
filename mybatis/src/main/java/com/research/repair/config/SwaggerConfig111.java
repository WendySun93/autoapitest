package com.research.repair.config;

import com.research.repair.utils.swagger.SwaggerUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig111 {
    @Bean
    public Docket docket() {
        return  SwaggerUtils.initDocket();
    }

}
