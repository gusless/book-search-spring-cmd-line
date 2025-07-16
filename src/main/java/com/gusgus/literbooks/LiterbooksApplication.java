package com.gusgus.literbooks;

import com.gusgus.literbooks.author.AuthorRepository;
import com.gusgus.literbooks.book.BookRepository;
import com.gusgus.literbooks.main.MainClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterbooksApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterbooksApplication.class, args);
	}

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Override
	public void run(String... args) throws Exception {
		MainClass mainClass = new MainClass(authorRepository, bookRepository);
		mainClass.menu();
	}
}
