package com.gusgus.literbooks.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByName(String name);

    @Query("SELECT a FROM Author a WHERE a.yearDeath >= :year AND a.yearBirth <= :year")
    List<Author> authorLiveInYear(Integer year);
}
