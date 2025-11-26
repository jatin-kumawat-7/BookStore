package com.bookstore. BookStore.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookstore. BookStore.model.Author;
import com.bookstore. BookStore.model.Book;
import com.bookstore. BookStore.repository.AuthorRepository;
import com.bookstore. BookStore.repository.BookRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;

    public BookController(BookRepository bookRepo, AuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    @GetMapping
    public Page<Book> list(@RequestParam(required = false) String title,
                           @RequestParam(required = false) String category,
                           @PageableDefault(size = 10) Pageable pageable) {
        if (title != null && !title.isBlank()) {
            return bookRepo.findByTitleContainingIgnoreCase(title, pageable);
        } else if (category != null && !category.isBlank()) {
            return bookRepo.findByCategoryIgnoreCase(category, pageable);
        } else {
            return bookRepo.findAll(pageable);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable Long id) {
        return bookRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Book book) {
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            Optional<Author> author = authorRepo.findById(book.getAuthor().getId());
            if (author.isEmpty()) {
                return ResponseEntity.badRequest().body("Author id not found");
            }
            book.setAuthor(author.get());
        }
        Book saved = bookRepo.save(book);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book input) {
        return bookRepo.findById(id).map(b -> {
            b.setTitle(input.getTitle());
            b.setCategory(input.getCategory());
            b.setPrice(input.getPrice());
            if (input.getAuthor() != null && input.getAuthor().getId() != null) {
                authorRepo.findById(input.getAuthor().getId()).ifPresent(b::setAuthor);
            }
            bookRepo.save(b);
            return ResponseEntity.ok(b);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Book> optionalBook = bookRepo.findById(id);
        if (optionalBook.isPresent()) {
            bookRepo.delete(optionalBook.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
