package com.gusgus.literbooks.book;

import com.gusgus.literbooks.author.Author;
import com.gusgus.literbooks.author.AuthorRepository;
import jakarta.persistence.*;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages;

    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;

    private Integer downloads;

    @Override
    public String toString() {
        return String.format("""
                -------LIVRO-------
                Título: %s
                Autor: %s
                Idioma: %s
                Downloads: %d
                -------------------""", title, author.getName(), languages.toString(), downloads);
    }

    public Book(){}

    public Book(BookData data){
        this.title = data.title();
        this.author = new Author(data.authors().getFirst());
        this.languages = data.languages();
        this.downloads = data.downloads();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }
}
