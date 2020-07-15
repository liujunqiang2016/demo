package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.TestBean;

/**
 * test
 * @author liujq-h
 *
 */
//@Configuration
public class TestConfig {
	
	@Value("${timeout}")
    private String timeout;
	
	public TestConfig() {
		System.out.println(timeout);
	}
	
   @Bean
   public TestBean testBean() {
	   TestBean tb = new TestBean();
	   tb.setTimeout(timeout);
	   return tb;
   }
}
