package fr.afcepf.al32.wspromotemplate.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



@SpringBootApplication(scanBasePackages= {"fr.afcepf.al32.wspromotemplate"})
public class App extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		
	}

}
