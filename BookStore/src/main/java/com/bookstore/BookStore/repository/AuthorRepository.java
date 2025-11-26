package com.bookstore.BookStore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.BookStore.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
