package com.gameofthrones.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofthrones.model.Book;
import com.gameofthrones.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.cloud.config.enabled=false")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testFindBooks() throws Exception {
        //given
        List<Book> books = objectMapper.readValue(Paths.get("src/test/resources/fixtures/gotBooks.json").toFile(), new TypeReference<List<Book>>() {});

        when(bookService.findBooks()).thenReturn(books);

        //when
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/books")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        //then
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        List<Book> resultBooks = objectMapper.readValue(content, new TypeReference<List<Book>>() {});

        assertThat((Objects.requireNonNull(resultBooks)).size()).isEqualTo(3);

        assertThat(resultBooks.get(0).getName())
                .isEqualTo(books.get(0).getName());
    }

    @Test
    public void testFindBook() throws Exception {
        Book book = objectMapper.readValue(Paths.get("src/test/resources/fixtures/gotBooks.json").toFile(), new TypeReference<List<Book>>() {}).get(0);
        when(bookService.findBook(Mockito.any())).thenReturn(book);

        //when
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/books/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        //then
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        Book resultBook = objectMapper.readValue(content, new TypeReference<Book>() {});

        Assertions.assertNotNull(resultBook);

        assertThat(resultBook.getName()).isEqualTo(book.getName());
    }

}
