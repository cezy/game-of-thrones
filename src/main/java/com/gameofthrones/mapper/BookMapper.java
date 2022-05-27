package com.gameofthrones.mapper;

import com.gameofthrones.dto.BookDto;
import com.gameofthrones.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto to(Book book);

    Book from(BookDto bookDto);
}