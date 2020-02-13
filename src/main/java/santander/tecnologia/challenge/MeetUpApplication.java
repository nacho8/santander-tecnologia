package santander.tecnologia.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@ComponentScan("santander.tecnologia.challenge.*")
@EntityScan("santander.tecnologia.challenge.*")
@EnableJpaRepositories("santander.tecnologia.challenge.*")
@EnableAutoConfiguration
public class MeetUpApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(MeetUpApplication.class, args);
		
	}
	
	
	
}
	