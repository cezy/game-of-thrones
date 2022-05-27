package com.gameofthrones.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofthrones.client.BookClient;
import com.gameofthrones.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookClient bookClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void findBooks() throws IOException {
        //given
        List<Book> books = objectMapper.readValue(Paths.get("src/test/resources/fixtures/gotBooks.json").toFile(), new TypeReference<List<Book>>() {});
        when(bookClient.getBooks()).thenReturn(books);

        //when
        List<Book> result = bookService.findBooks();

        //then
        assertThat((Objects.requireNonNull(result)).size()).isEqualTo(3);

        assertThat(result.get(0).getName())
                .isEqualTo(books.get(0).getName());
    }

}
