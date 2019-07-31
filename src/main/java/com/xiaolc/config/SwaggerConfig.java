package com.xiaolc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/***
 * @author Created by wyx
 * @date 2018/8/8 16:39
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .paths(PathSelectors.regex("/api.*|/test.*|/service.*|/*.*")).build();
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("RESTful APIs").description("just do it.")
                .termsOfServiceUrl("http://www.sample.com/").version("1.0").build();
    }
}
