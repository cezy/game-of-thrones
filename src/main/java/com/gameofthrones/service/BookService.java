package com.gameofthrones.service;

import com.gameofthrones.client.BookClient;
import com.gameofthrones.model.Book;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Value("${app.feign.config.url}")
    private String url;

    public List<Book> findBooks() {
        BookClient resource = Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).target(BookClient.class, url);
        return resource.getBooks();
    }

    public Book findBook(Integer id) {
        BookClient resource = Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).target(BookClient.class, url);
        return resource.getBook(id);
    }
}
