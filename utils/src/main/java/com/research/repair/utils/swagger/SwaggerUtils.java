package com.research.repair.utils.swagger;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


public class SwaggerUtils {

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("自己搭建的Swagger2 RESTful APIs")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact(new Contact("wenjuan", "https://github.com/swagger-api/swagger-ui", "1010946772@qq.com"))
                .description("这是我自己的swagger-ui生成的接口文档")
                .version("1.0")
                .build();
    }

    public static Docket initDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


}
