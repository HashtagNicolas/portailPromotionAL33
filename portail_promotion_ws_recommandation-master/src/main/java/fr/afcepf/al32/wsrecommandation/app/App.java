package fr.afcepf.al32.wsrecommandation.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = { "fr.afcepf.al32.wsrecommandation" })
public class App extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
		// System.out.println("http://localhost:8086/client");
	}

}