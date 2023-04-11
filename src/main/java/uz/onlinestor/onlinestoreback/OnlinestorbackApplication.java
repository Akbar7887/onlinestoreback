package uz.onlinestor.onlinestoreback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
