package com.gameofthrones.service;

import com.gameofthrones.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookServiceIT {

   @Autowired
   private BookService bookService;

    @Test
    public void testFindBooks() {
        //when
        List<Book> result = bookService.findBooks();

        //then
        Assertions.assertNotNull(result);
        assertThat((Objects.requireNonNull(result)).size()).isEqualTo(10);
        Assertions.assertNotNull(result.get(0).getName());
    }

    @Test
    public void testFindBook() {
        //when
        Book result = bookService.findBook(1);

        //then
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getName());
    }


}
