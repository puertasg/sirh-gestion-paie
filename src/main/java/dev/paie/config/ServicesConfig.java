package dev.paie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan({ "dev.paie.service", "dev.paie.util" })
@Import({ JpaConfig.class, HerokuDBConfig.class })

@EnableJpaRepositories("dev.paie.repository")
public class ServicesConfig {

	// le bean `passwordEncoder` contient l'algorithme de hashage des mots de
	// passe de l'application.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
