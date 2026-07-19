package com.cfs.bms.service;
import com.cfs.bms.dto.SeatDto;
import com.cfs.bms.dto.TheaterDto;
import com.cfs.bms.dto.ScreenDto;
import com.cfs.bms.exception.ResourceNotFoundException;
import com.cfs.bms.model.Screen;
import com.cfs.bms.model.Seat;
import com.cfs.bms.model.Theater;
import com.cfs.bms.repository.ScreenRepository;
import com.cfs.bms.repository.SeatRepository;
import com.cfs.bms.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private SeatRepository seatRepository;

    public ScreenDto createScreen(ScreenDto screenDto) {

        Theater theater = theaterRepository.findById(
                        screenDto.getTheater().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Theater Not Found"));

        Screen screen = new Screen();
        screen.setName(screenDto.getName());
        screen.setTotalSeats(screenDto.getTotalSeats());
        screen.setTheater(theater);

        Screen savedScreen = screenRepository.save(screen);

        List<Seat> seats = seatRepository.findByScreenId(savedScreen.getId());

        return mapToDto(savedScreen, seats);
    }

    public ScreenDto getScreenById(Long id) {

        Screen screen = screenRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Screen Not Found with id : " + id));

        List<Seat> seats =
                seatRepository.findByScreenId(screen.getId());

        return mapToDto(screen, seats);
    }

    public List<ScreenDto> getAllScreens() {

        List<Screen> screens = screenRepository.findAll();

        return screens.stream()
                .map(screen -> {

                    List<Seat> seats =
                            seatRepository.findByScreenId(screen.getId());

                    return mapToDto(screen, seats);

                }).collect(Collectors.toList());
    }

    public List<ScreenDto> getScreensByTheater(Long theaterId) {

        List<Screen> screens =
                screenRepository.findByTheaterId(theaterId);

        return screens.stream()
                .map(screen -> {

                    List<Seat> seats =
                            seatRepository.findByScreenId(screen.getId());

                    return mapToDto(screen, seats);

                }).collect(Collectors.toList());
    }

    private ScreenDto mapToDto(Screen screen,
                               List<Seat> seats) {

        TheaterDto theaterDto = new TheaterDto(
                screen.getTheater().getId(),
                screen.getTheater().getName(),
                screen.getTheater().getAddress(),
                screen.getTheater().getCity(),
                screen.getTheater().getTotalScreen()
        );

        ScreenDto screenDto = new ScreenDto();

        screenDto.setId(screen.getId());
        screenDto.setName(screen.getName());
        screenDto.setTotalSeats(screen.getTotalSeats());
        screenDto.setTheater(theaterDto);

        List<SeatDto> seatDtos = seats.stream()
                .map(seat -> {

                    SeatDto seatDto = new SeatDto();

                    seatDto.setId(seat.getId());
                    seatDto.setSeatNumber(seat.getSeatNumber());
                    seatDto.setSeatType(seat.getSeatType());
                    seatDto.setBasePrice(seat.getBasePrice());

                    return seatDto;

                }).collect(Collectors.toList());

        screenDto.setSeats(seatDtos);

        return screenDto;
    }
}