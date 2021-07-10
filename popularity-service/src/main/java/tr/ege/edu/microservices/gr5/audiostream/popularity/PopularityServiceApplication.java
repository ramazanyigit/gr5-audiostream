package tr.ege.edu.microservices.gr5.audiostream.popularity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PopularityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopularityServiceApplication.class, args);
	}

}
