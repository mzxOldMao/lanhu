package cn.suancloud.api.postgresqltest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@EnableSwagger2
public class Swagger2Config {

//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors
//                .any()).paths(PathSelectors.regex("/(users).*")).build();
//    }
	/*
	 * @Bean public Docket createRestApi() { return new
	 * Docket(DocumentationType.SWAGGER_2) .apiInfo(apiInfo()) .select()
	 * .apis(RequestHandlerSelectors.basePackage("cn.suancloud.pdfjk.api"))//
	 * 填写自己项目的服务包路径 .paths(PathSelectors.any()) .build(); }
	 * 
	 * 
	 * 
	 * private ApiInfo apiInfo() { // ApiInfo apiInfo = new ApiInfo("大标题",//大标题 //
	 * "小标题",//小标题 // "1.0",//版本 // "没有服务", // "bdapp.com",//作者 // "链接显示文字",//链接显示文字
	 * // "网站链接"//网站链接 // ); // // return apiInfo; return new ApiInfoBuilder()
	 * .title("Spring Boot中使用Swagger2构建RESTful APIs")
	 * .description("美至简网址：http://mzjmedia.com/")
	 * .termsOfServiceUrl("http://mzjmedia.com/") // .contact("mzj") .version("1.0")
	 * .build(); }
	 */
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).
                useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.suancloud.api"))
                .paths(PathSelectors.regex("^(?!auth).*$"))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                ;
    }
 
    private List<ApiKey> securitySchemes() {
        return new ArrayList(Arrays.asList(new ApiKey("testAuthorization", "testAuthorization", "header"))
                );
    }
 
    private List<SecurityContext> securityContexts() {
        return new ArrayList(Arrays.asList(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build())
                
        );
    }
 
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return new ArrayList(Arrays.asList(new SecurityReference("testAuthorization", authorizationScopes))
                );
    }

//muluAuthorization


}

