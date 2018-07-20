package zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class ZipkinApp {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinApp.class, args);
	}
	
}
