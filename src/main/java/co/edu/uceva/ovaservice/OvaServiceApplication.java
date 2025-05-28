package co.edu.uceva.ovaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OvaServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(OvaServiceApplication.class, args);
    }

}
