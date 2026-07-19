package com.cfs.bms.service;

import com.cfs.bms.dto.SeatDto;
import com.cfs.bms.dto.ShowSeatDto;
import com.cfs.bms.exception.ResourceNotFoundException;
import com.cfs.bms.model.Seat;
import com.cfs.bms.model.Show;
import com.cfs.bms.model.ShowSeat;
import com.cfs.bms.repository.SeatRepository;
import com.cfs.bms.repository.ShowRepository;
import com.cfs.bms.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowSeatService {

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private SeatRepository seatRepository;

    public List<ShowSeatDto> getSeatsByShow(Long showId) {

        List<ShowSeat> showSeats =
                showSeatRepository.findByShowId(showId);

        return showSeats.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ShowSeatDto> getAvailableSeats(Long showId) {

        List<ShowSeat> showSeats =
                showSeatRepository.findByShowIdAndStatus(
                        showId,
                        "AVAILABLE"
                );

        return showSeats.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ShowSeatDto getShowSeatById(Long id) {

        ShowSeat showSeat = showSeatRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Show Seat Not Found"
                        ));

        return mapToDto(showSeat);
    }

    /**
     * Create ShowSeats for a Show
     */
    public void createShowSeats(Long showId) {

        Show show = showRepository.findById(showId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Show Not Found"
                        ));

        List<Seat> seats =
                seatRepository.findByScreenId(
                        show.getScreen().getId()
                );

        List<ShowSeat> showSeats = seats.stream()
                .map(seat -> {

                    ShowSeat ss = new ShowSeat();

                    ss.setShow(show);
                    ss.setSeat(seat);
                    ss.setStatus("AVAILABLE");
                    ss.setPrice(seat.getBasePrice());

                    return ss;

                }).collect(Collectors.toList());

        showSeatRepository.saveAll(showSeats);
    }

    private ShowSeatDto mapToDto(ShowSeat showSeat) {

        ShowSeatDto dto = new ShowSeatDto();

        dto.setId(showSeat.getId());
        dto.setStatus(showSeat.getStatus());
        dto.setPrice(showSeat.getPrice());

        SeatDto seatDto = new SeatDto();

        seatDto.setId(showSeat.getSeat().getId());
        seatDto.setSeatNumber(
                showSeat.getSeat().getSeatNumber()
        );
        seatDto.setSeatType(
                showSeat.getSeat().getSeatType()
        );
        seatDto.setBasePrice(
                showSeat.getSeat().getBasePrice()
        );

        dto.setSeat(seatDto);

        return dto;
    }
}