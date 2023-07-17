package dev.rasoul.pgadmin;

import dev.rasoul.pgadmin.event.Event;
import dev.rasoul.pgadmin.event.EventRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EventRepository repository) {
		return args -> {
			// persist 1 event
			if(repository.count() == 0) {
				var event = new Event(1,
						"SpringOne 2023",
						"test some description",
						LocalDate.of(2023,8,21),
						LocalDate.of(2023,8,24),
						LocalDate.now().minusDays(180),
						LocalDate.now().minusDays(90),
						"Las Vegas, NV",
						"https://springone.io/");

				repository.save(event);
				log.info("Event created: " + event.getName());
			}
		};
	}

}
