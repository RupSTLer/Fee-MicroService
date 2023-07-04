package com.stl.rupam.SchoolWebApp.fee.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {
	
	@Bean
	Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("School Management System")
				.apiInfo(getInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
				
	}

	private ApiInfo getInfo() {
		return new ApiInfo(
				"Fee Microservice", 
				"Microservice that handles Fee service request URLs", 
				"1.0", 
				"Terms of service", 
				new Contact("Rupam Chakraborty", "", "rupam.chakraborty@stl.tech"), 
				"license of APIs", 
				"API license URLs", 
				Collections.emptyList());
	}

}
