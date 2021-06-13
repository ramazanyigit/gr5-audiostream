package tr.ege.edu.microservices.gr5.audiostream.aaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AaServiceApplication.class, args);
    }

}
