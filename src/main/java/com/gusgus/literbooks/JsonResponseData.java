package com.gusgus.literbooks;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gusgus.literbooks.book.Book;
import com.gusgus.literbooks.book.BookData;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JsonResponseData(
        @JsonAlias("results") List<BookData> results
) {
}
