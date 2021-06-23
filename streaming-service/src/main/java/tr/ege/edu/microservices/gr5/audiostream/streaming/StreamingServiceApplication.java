package tr.ege.edu.microservices.gr5.audiostream.streaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StreamingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamingServiceApplication.class, args);
	}

}
