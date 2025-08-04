package edu.library.service;

import edu.library.exception.ResourceNotFoundException;
import edu.library.model.Book;
import edu.library.model.Reservation;
import edu.library.model.User;
import edu.library.repository.BookRepository;
import edu.library.repository.ReservationRepository;
import edu.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public Reservation createReservation(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!book.isAvailable()) throw new IllegalStateException("Book is not available for reservation");

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setBook(book);
        reservation.setReservationDate(LocalDate.now());

        book.setAvailable(false);
        bookRepository.save(book);

        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).
                orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        Book book = reservation.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        reservationRepository.delete(reservation);
    }

    @Override
    public List<Reservation> getUserReservations(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return reservationRepository.findByUser(user);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
    }
}
