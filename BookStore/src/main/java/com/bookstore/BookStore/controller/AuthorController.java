package com.bookstore.BookStore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookstore.BookStore.model.Author;
import com.bookstore.BookStore.repository.AuthorRepository;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorRepository repo;

    public AuthorController(AuthorRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Author> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author author) {
        Author saved = repo.save(author);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody Author input) {
        return repo.findById(id).map(a -> {
            a.setName(input.getName());
            a.setEmail(input.getEmail());
            repo.save(a);
            return ResponseEntity.ok(a);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Author> optionalAuthor = repo.findById(id);
        if (optionalAuthor.isPresent()) {
            repo.delete(optionalAuthor.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
