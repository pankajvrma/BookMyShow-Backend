package com.cfs.bms.service;

import com.cfs.bms.dto.ScreenDto;
import com.cfs.bms.dto.SeatDto;
import com.cfs.bms.dto.TheaterDto;
import com.cfs.bms.exception.ResourceNotFoundException;
import com.cfs.bms.model.Screen;
import com.cfs.bms.model.Seat;
import com.cfs.bms.repository.ScreenRepository;
import com.cfs.bms.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScreenRepository screenRepository;

    public SeatDto createSeat(SeatDto seatDto) {

        Screen screen = screenRepository.findById(
                        seatDto.getScreen().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Screen Not Found"));

        Seat seat = new Seat();

        seat.setSeatNumber(seatDto.getSeatNumber());
        seat.setSeatType(seatDto.getSeatType());
        seat.setBasePrice(seatDto.getBasePrice());
        seat.setScreen(screen);

        Seat savedSeat = seatRepository.save(seat);

        return mapToDto(savedSeat);
    }

    public SeatDto getSeatById(Long id) {

        Seat seat = seatRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Seat Not Found"));

        return mapToDto(seat);
    }

    public List<SeatDto> getAllSeats() {

        return seatRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<SeatDto> getSeatsByScreen(Long screenId) {

        return seatRepository.findByScreenId(screenId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private SeatDto mapToDto(Seat seat) {

        SeatDto dto = new SeatDto();

        dto.setId(seat.getId());
        dto.setSeatNumber(seat.getSeatNumber());
        dto.setSeatType(seat.getSeatType());
        dto.setBasePrice(seat.getBasePrice());

        if (seat.getScreen() != null) {

            TheaterDto theaterDto = new TheaterDto(
                    seat.getScreen().getTheater().getId(),
                    seat.getScreen().getTheater().getName(),
                    seat.getScreen().getTheater().getAddress(),
                    seat.getScreen().getTheater().getCity(),
                    seat.getScreen().getTheater().getTotalScreen()
            );

            ScreenDto screenDto = new ScreenDto(
                    seat.getScreen().getId(),
                    seat.getScreen().getName(),
                    seat.getScreen().getTotalSeats(),
                    theaterDto
            );

            dto.setScreen(screenDto);
        }

        return dto;
    }
}