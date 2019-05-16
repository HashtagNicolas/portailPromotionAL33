package fr.afcepf.al32.wsrecherche.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages= {"fr.afcepf.al32.wsrecherche"}, exclude= {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EntityScan(basePackages= {"fr.afcepf.al32.wsrecherche.entity"})
public class App extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("http://localhost:8083/portail_promotion_ws_recherche");
	}
}
