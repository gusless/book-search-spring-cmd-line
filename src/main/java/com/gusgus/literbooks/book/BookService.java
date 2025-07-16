package com.gusgus.literbooks.book;

import com.gusgus.literbooks.author.Author;
import com.gusgus.literbooks.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public Book createBookFromData(BookData data){
        String authorName = data.authors().getFirst().name();

        Author existingAuthor = authorRepository.findAuthorByName(authorName)
                .orElseGet(() -> {
                    Author newAuthor = new Author(data.authors().getFirst());
                    return authorRepository.save(newAuthor);
                });

        Book book = new Book();
        book.setTitle(data.title());
        book.setAuthor(existingAuthor);
        book.setDownloads(data.downloads());
        book.setLanguages(data.languages());

        return book;
    }
}
