package com.boniu.account.server.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by ZZF on 18/06/12.
 */
@Configuration
@EnableSwagger2
public class Swagger2Conf {
    @Value("${spring.profiles.active}")
    private String active;
    @Bean
    public Docket createRestApi() {
        if ("production".equals(active)) {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .paths(PathSelectors.regex("/*"))
                    .build();
        } else {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.boniu.account.server.controller"))
                    .build();
        }
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("账户系统 API [v1.0]")
                .description("佛曰: 程序人员写程序，又拿程序换酒钱。 奔驰宝马贵者趣，公交自行程序员。")
                .version("1.0")
                .build();
    }

}