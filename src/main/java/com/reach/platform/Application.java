package com.reach.platform;

import java.util.Arrays;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.AwsSyncClientParams;
import com.amazonaws.services.workmail.AmazonWorkMail;
import com.amazonaws.services.workmail.AmazonWorkMailAsyncClient;
import com.amazonaws.services.workmail.AmazonWorkMailClient;
import com.amazonaws.services.workmail.AmazonWorkMailClientBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
			public AmazonWorkMail amazonWorkMailClient(){
		return  AmazonWorkMailClientBuilder.standard().withRegion("us-west-2").defaultClient();
//		AWSCredentialsProvider
	}


	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

}
