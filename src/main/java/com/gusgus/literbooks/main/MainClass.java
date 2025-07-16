package com.gusgus.literbooks.main;

import com.gusgus.literbooks.JsonResponseData;
import com.gusgus.literbooks.author.Author;
import com.gusgus.literbooks.author.AuthorRepository;
import com.gusgus.literbooks.book.Book;
import com.gusgus.literbooks.book.BookRepository;
import com.gusgus.literbooks.service.ApiRequest;
import com.gusgus.literbooks.service.Convert;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainClass {

    private ApiRequest request = new ApiRequest();
    private Convert converter = new Convert();
    private String apiUrl = "https://gutendex.com/books?search=";
    private Scanner scan = new Scanner(System.in);

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public MainClass(AuthorRepository authorRepository, BookRepository bookRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    public void menu() throws IOException, InterruptedException {
        var choice = -1;
        while(choice != 0){
            System.out.println("""
                    1 - buscar livro pelo título
                    2 - listar livros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos em um determinado ano
                    5 - listar livros em um determinado idioma
                    
                    0 - sair
                    """);
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice){
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    showBooks();
                    break;
                case 3:
                    showAuthors();
                    break;
                case 4:
                    showLiveAuthorsByYear();
                    break;
                case 5:
                    showBooksByLanguage();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private void searchBookByTitle() throws IOException, InterruptedException {
        try {
            System.out.println("Digite o nome do livro:");
            var name = scan.nextLine();

            String json = request.apiRequest(apiUrl + name.replace(" ", "+"));
            JsonResponseData jrd = converter.getData(json, JsonResponseData.class);

            Book book = new Book(jrd.results().getFirst());

            Author authorApi = book.getAuthor();
            String authorName = authorApi.getName();

            Author author;
            Optional<Author> authorOptional = authorRepository.findAuthorByName(authorName);

            if (authorOptional.isPresent()){
                author = authorOptional.get();
            } else {
                author = authorRepository.save(authorApi);
            }

            book.setAuthor(author);

            if (bookRepository.findBookByTitle(book.getTitle()).isEmpty()) {
                bookRepository.save(book);
                System.out.println(book);
            } else {
                System.out.println("Esse livro já foi adicionado");
            }
        } catch (Exception e){
            System.out.println("Livro não encontrado");
        }
    }

    private void showBooks() {
        System.out.println("Todos os livros do banco de dados:");
        bookRepository.findAll().forEach(System.out::println);
    }

    private void showAuthors() {
        System.out.println("Todos os autores do banco de dados:");
        authorRepository.findAll().forEach(System.out::println);
    }

    private void showLiveAuthorsByYear() {
        System.out.println("Digite um ano");
        Integer year = scan.nextInt();
        scan.nextLine();
        if (authorRepository.authorLiveInYear(year).isEmpty()){
            System.out.println("Nenhum autor vivo nesse ano");
        } else {
            System.out.printf("Autores vivos em %d%n", year);
            authorRepository.authorLiveInYear(year).forEach(System.out::println);
        }
    }

    private void showBooksByLanguage() {
        System.out.println("""
                Insira um idioma:
                1 - Espanhol
                2 - Inglês
                3 - Francês
                4 - Português
                """);
        int lang = scan.nextInt();
        scan.nextLine();

        switch (lang){
            case 1:
                showByLang("es");
                break;
            case 2:
                showByLang("en");
                break;
            case 3:
                showByLang("fr");
                break;
            case 4:
                showByLang("pt");
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private void showByLang(String lang){
        if (!bookRepository.bookByLanguage(lang).isEmpty()){
            bookRepository.bookByLanguage(lang).forEach(System.out::println);
        } else {
            System.out.println("Ainda não há livros com essa linguagem");
        }
    }
}
