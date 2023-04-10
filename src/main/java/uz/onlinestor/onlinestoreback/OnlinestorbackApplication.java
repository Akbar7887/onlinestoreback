package uz.onlinestor.onlinestoreback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.onlinestor.onlinestoreback.fileupload.FileStorageProperties;


@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class OnlinestorbackApplication {

	public static void main(String[] args) {

		SpringApplication.run(OnlinestorbackApplication.class, args);
	}
}
