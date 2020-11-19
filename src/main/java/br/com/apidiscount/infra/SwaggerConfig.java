package br.com.apidiscount.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.apidiscount"))
                .build()
                .apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Serviço de Desconto Por Usuários")
                .description("Serviço criado para analisar desconto do usuário de acordo com a quantidade de pedidos efetuados.")
                .version("1.0.0")
                .contact(new Contact("Marcos Santos", "https://www.linkedin.com/in/marcosebsantos/", "marcosebsantos@gmail.com"))
                .build();
    }
}