package com.cfs.bms.repository;

import com.cfs.bms.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByScreenId(Long screenId);

    List<Seat> findByScreenIdAndSeatType(
            Long screenId,
            String seatType
    );

    Optional<Seat> findByScreenIdAndSeatNumber(
            Long screenId,
            String seatNumber
    );
}
