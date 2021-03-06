package fr.afcepf.al32.groupe2.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "fr.afcepf.al32.groupe2")
@EntityScan(basePackages = "fr.afcepf.al32.groupe2.entity")
public class TestConfig {

}
