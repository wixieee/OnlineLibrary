package edu.library.utils;

import edu.library.dto.BookRequest;
import edu.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "available", constant = "true")
    Book toEntity(BookRequest dto);

    void updateEntityFromDto(BookRequest dto, @MappingTarget Book entity);
}
