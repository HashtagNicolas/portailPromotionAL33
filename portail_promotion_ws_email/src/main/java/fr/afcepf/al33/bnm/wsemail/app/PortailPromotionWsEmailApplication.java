package fr.afcepf.al33.bnm.wsemail.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages= {"fr.afcepf.al33.bnm"})
public class PortailPromotionWsEmailApplication  {

    public static void main(String[] args) {
        SpringApplication.run(PortailPromotionWsEmailApplication.class, args);
    }
}