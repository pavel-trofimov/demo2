package com.example.demo2;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.validation.ConstraintViolationProblemModule;

@SpringBootApplication
public class Demo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@Bean
	public ProjectionFactory projectionFactory() {
		return new SpelAwareProxyProjectionFactory();
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer problemObjectMapperModules() {
		return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.modules(
				new ProblemModule().withStackTraces(false),
				new ConstraintViolationProblemModule(),
				new JavaTimeModule()
		);
	}
}
