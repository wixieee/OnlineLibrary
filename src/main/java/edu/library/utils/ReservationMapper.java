package edu.library.utils;

import edu.library.dto.ReservationResponse;
import edu.library.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    ReservationResponse toDTO(Reservation reservation);
}
