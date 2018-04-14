package com.example;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SpringDataSampleApplication {

	@Bean
	CommandLineRunner initData(BookRepository bookRepository){
		return args -> {
			bookRepository.save(new Book("Spring Microservices", "Learn how to efficiently build and implement microservices in Spring," +
					"and how to use Docker and Mesos to push the boundaries. Examine a number of real-world use cases and hands-on code examples." +
					"Distribute your microservices in a completely new way"));
			bookRepository.save(new Book("Pro Spring Boot", "A no-nonsense guide containing case studies and best practise for Spring Boot"));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataSampleApplication.class, args);
	}
}

@Data
@Entity
@NoArgsConstructor
class Book {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String description;

	Book(String title, String description) {
		this.title = title;
		this.description = description;
	}
}

@RepositoryRestResource
interface  BookRepository extends CrudRepository<Book, Long> {
	
}
