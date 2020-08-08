package cn.itweknow.sbswagger.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                //下面这几行用于指定swagger扫描的包和路径，只针对接口，和接口上面的@ApiIgnore是一个作用
                //.apis(RequestHandlerSelectors.basePackage("cn.itweknow.sbswagger.controller"))
                //.paths(Predicates.or(PathSelectors.ant("/user/add"),
                //        PathSelectors.ant("/user/find/*")))
                .build()
                .apiInfo(apiInfo())    //这里是自定义文档信息，下面是自定义响应信息
                .useDefaultResponseMessages(false)  //告诉swagger不使用默认的http响应信息
                .globalResponseMessage(RequestMethod.GET, newArrayList(   //下面是对所有get方法500错误和403错误的返回响应
                        new ResponseMessageBuilder()
                                .code(500)
                                .message("服务器发生异常")
                                .responseModel(new ModelRef("Error"))
                                .build(),
                        new ResponseMessageBuilder()
                                .code(403)
                                .message("资源不可用")
                                .build()
                ));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Spring Boot项目集成Swagger实例文档",
                "我的博客网站：https://github.com/Gaoffrey，欢迎大家访问。",
                "API V1.0",
                "Terms of service",
                new Contact("Gaoffrey", "https://github.com/Gaoffrey", "Gaoffrey@foxmail.com"),
                "Apache", "http://www.apache.org/", Collections.emptyList());
    }
}
