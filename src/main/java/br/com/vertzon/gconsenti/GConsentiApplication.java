package br.com.vertzon.gconsenti;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GConsentiApplication extends SpringBootServletInitializer
		implements WebMvcConfigurer {
	
	public static void main(String[] args) {
		SpringApplication GConsenti = new SpringApplication(GConsentiApplication.class);
		GConsenti.setBannerMode(Banner.Mode.OFF);
		GConsenti.run(args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/javax.faces.resource/**").addResourceLocations("/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("").setViewName("/secure/dashboard.xhtml");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
}
