package com.gusgus.literbooks.author;

import com.gusgus.literbooks.book.AuthorData;
import com.gusgus.literbooks.book.Book;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer yearBirth;

    private Integer yearDeath;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Book> books;

    @Override
    public String toString() {
        return String.format("""
                Autor: %s
                Ano de nascimento: %d
                Ano de falecimento: %d
                Livros: %s
                """, name, yearBirth, yearDeath, books.stream().map(Book::getTitle).collect(Collectors.toList()));
    }

    public Author(){}

    public Author (AuthorData data){
        this.name = data.name();
        this.yearBirth = data.yearBirth();
        this.yearDeath = data.yearDeath();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(Integer yearBirth) {
        this.yearBirth = yearBirth;
    }

    public Integer getYearDeath() {
        return yearDeath;
    }

    public void setYearDeath(Integer yearDeath) {
        this.yearDeath = yearDeath;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
