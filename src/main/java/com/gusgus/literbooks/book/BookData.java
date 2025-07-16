package com.gusgus.literbooks.book;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gusgus.literbooks.JsonResponseData;
import com.gusgus.literbooks.author.Author;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("title") String title,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("authors") List<AuthorData> authors,
        @JsonAlias("download_count") Integer downloads
) {
}
