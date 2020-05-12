package com.example.Note;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Note.domain.Category;
import com.example.Note.domain.CategoryRepository;
import com.example.Note.domain.Note;
import com.example.Note.domain.NoteRepository;
import com.example.Note.domain.User;
import com.example.Note.domain.UserRepository;

@SpringBootApplication
public class NoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteApplication.class, args);
	}

	@Bean
	public CommandLineRunner DemoDatabase(NoteRepository NRepository, CategoryRepository CRepository,
			UserRepository URepository) {
		return (args) -> {
			/**
			 * insert data to database
			 *
			 */
//			CRepository.save(new Category("Gym"));
//			CRepository.save(new Category("Work"));
//			CRepository.save(new Category("Recipes"));
//			CRepository.save(new Category("Study"));
//			CRepository.save(new Category("Music"));
//
//			URepository.save(new User("user", "$2a$10$3Mxh28oBkGMSCxRJTNRrBu1Nn7J1blfr1oymc9qoXv.ORt3y0b/hm", "USER"));
//			URepository
//					.save(new User("admin", "$2a$10$PudDFrTamJg2m5vJYZEmCOWm6gLhS7KSTwvmidiIfr3tKHI5Iw7XW", "ADMIN"));
//
//			NRepository.save(new Note("Keep your body fit", "Blank in 3 ms every day",
//					URepository.findByUsername("user"), CRepository.findByName("Gym").get(0)));
//			NRepository.save(new Note("Routine security check", "Sercurity check every Wenesday and logout every Friday",
//					URepository.findByUsername("user"), CRepository.findByName("Work").get(0)));
//			NRepository.save(new Note("how to cook good egg noodle", "only cook egg noodle when water is hot",
//					URepository.findByUsername("user"), CRepository.findByName("Recipes").get(0)));
//			NRepository.save(new Note("The best chord for male", "C-G-Am-F",
//					URepository.findByUsername("user"), CRepository.findByName("Music").get(0)));
//			NRepository.save(new Note("the important thing for you to study", "relax before study or taking shower",
//					URepository.findByUsername("admin"), CRepository.findByName("Study").get(0)));
//			NRepository.save(new Note("how to cook instance bread from S-market", "wash with cold water quickly after that put into oven(200C) for 20 minutes",
//					URepository.findByUsername("admin"), CRepository.findByName("Recipes").get(0)));
//			NRepository.save(new Note("how to gain weight easily", "push-up every day 100 reps",
//					URepository.findByUsername("admin"), CRepository.findByName("Gym").get(0)));
//			NRepository.save(new Note("sharing network password by ios", "same iphones can be share password wifi each other",
//					URepository.findByUsername("admin"), CRepository.findByName("Recipes").get(0)));

		};
	}

}
