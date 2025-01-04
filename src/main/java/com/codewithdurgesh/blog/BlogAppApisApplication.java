package com.codewithdurgesh.blog;

//import org.apache.log4j.Appender;
//import org.apache.log4j.ConsoleAppender;
//import org.apache.log4j.Layout;
//import org.apache.log4j.Logger;
//import org.apache.log4j.SimpleLayout;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApisApplication {
	
//	private static Logger logger = Logger.getLogger(BlogAppApisApplication.class);//step1: Create object of logger using various impl

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
		
		//Step2: Creating the object os Layout[How to show the logg's]
//		Layout layout = new SimpleLayout();
		
		//Step3: Where to store logs
//		Appender appender = new ConsoleAppender(layout);
//		logger.addAppender(appender);
		
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
