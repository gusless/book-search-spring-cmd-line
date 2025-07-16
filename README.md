# LiterBooks

Sistema Java com Spring Data JPA que permite consultar livros do [Projeto Gutenberg](https://gutendex.com/) via API, registrar em banco de dados e visualizar autores e livros com diversas opções de filtragem.

---

## Funcionalidades

-  Buscar livro pelo título (via [Gutendex API](https://gutendex.com/))
-  Listar livros cadastrados
-  Listar autores cadastrados
-  Ver autores vivos em determinado ano
-  Filtrar livros por idioma (🇧🇷 PT, 🇬🇧 EN, 🇪🇸 ES, 🇫🇷 FR)
-  Armazenamento em banco de dados (via Spring JPA)

---

##  Tecnologias Utilizadas

- Java 17+
- Spring Boot / Spring Data JPA
- Hibernate
- API Rest (HTTP Client)
- JPA (com H2, PostgreSQL ou outro)
- Maven

---

## Interface de Console

O programa roda em modo terminal com o seguinte menu:

1 - buscar livro pelo título

2 - listar livros registrados

3 - listar autores registrados

4 - listar autores vivos em um determinado ano

5 - listar livros em um determinado idioma

0 - sair
