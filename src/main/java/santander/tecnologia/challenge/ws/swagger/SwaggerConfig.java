package santander.tecnologia.challenge.ws.swagger;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
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
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
        	.useDefaultResponseMessages(false)
        	.select()
        	.apis(RequestHandlerSelectors.any())
        	.paths(Predicates.not(PathSelectors.regex("/error.*")))
        	.build()
        	.apiInfo(getMetadata());
    }
    
    private ApiInfo getMetadata() {
        ApiInfo apiInfo = new ApiInfo(
                "Challenge Santander",
                "Proyecto encargado de gestionar las meetUps de santander tecnologia.",
                "1.0.0",
                "",
                new Contact("Repositorio", "", ""),
                "",
                "", new ArrayList<>());
        return apiInfo;
    }


}
