package com.gusgus.literbooks.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByTitle(String title);

    @Query("SELECT b FROM Book b WHERE :lang MEMBER OF b.languages")
    List<Book> bookByLanguage(String lang);
}
