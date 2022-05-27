package com.gameofthrones.client;

import com.gameofthrones.model.Book;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface BookClient {

    @RequestLine("GET /books/{id}")
    @Headers("Content-Type: application/json")
    Book getBook(@Param int id);


    @RequestLine("GET /books")
    @Headers("Content-Type: application/json")
    List<Book> getBooks();

}
