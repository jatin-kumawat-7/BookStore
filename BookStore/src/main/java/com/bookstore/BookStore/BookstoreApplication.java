package com.bookstore.BookStore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.BookStore.model.Author;
import com.bookstore.BookStore.model.Book;
import com.bookstore.BookStore.repository.AuthorRepository;
import com.bookstore.BookStore.repository.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    // Sample data for quick testing
    @Bean
    CommandLineRunner initData(AuthorRepository authorRepo, BookRepository bookRepo) {
        return args -> {
            Author a1 = new Author(null, "Joshua Bloch", "jb@example.com");
            Author a2 = new Author(null, "Robert C. Martin", "unclebob@example.com");
            authorRepo.save(a1);
            authorRepo.save(a2);
            bookRepo.save(new Book(null, "Effective Java", "Programming", 45.0, a1));
            bookRepo.save(new Book(null, "Clean Code", "Programming", 40.0, a2));
            bookRepo.save(new Book(null, "More Effective Java", "Programming", 50.0, a1));
        };
    }
}
