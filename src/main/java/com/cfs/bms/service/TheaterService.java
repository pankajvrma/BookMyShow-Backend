package com.cfs.bms.service;

import com.cfs.bms.dto.TheaterDto;
import com.cfs.bms.exception.ResourceNotFoundException;
import com.cfs.bms.model.Theater;
import com.cfs.bms.repository.TheaterRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public TheaterDto createTheater(TheaterDto theaterDto)
    {
        Theater theater = mapToEntity(theaterDto);
        Theater savedTheater = theaterRepository.save(theater);
        return  mapToDto(savedTheater);
    }
    public TheaterDto getTheaterById(Long id)
    {
        Theater theater = theaterRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Theater Not Found with id: "+id));
        return mapToDto(theater);
    }
    private List<TheaterDto> getAllTheater()
    {
        List<Theater> theaters = theaterRepository.findAll();
        return theaters.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    private List<TheaterDto> getAllTheaterByCity(String city)
    {
        List<Theater> theaters = theaterRepository.findByCity(city);
        return theaters.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // home work update and delete theater



    private TheaterDto mapToDto(Theater theater) {
        TheaterDto theaterDto = new TheaterDto();
        theaterDto.setId(theater.getId());
        theaterDto.setName(theater.getName());
        theaterDto.setAddress(theater.getAddress());
        theaterDto.setCity(theater.getCity());
        theaterDto.setTotalScreens(theater.getTotalScreen());
        return theaterDto;
    }

    private Theater mapToEntity(TheaterDto theaterDto) {
        Theater theater = new Theater();
        theater.setName(theaterDto.getName());
        theater.setId(theaterDto.getId());
        theater.setAddress(theaterDto.getAddress());
        theater.setCity(theaterDto.getCity());
        theater.setTotalScreen(theaterDto.getTotalScreens());
        return theater;
    }

    public @Nullable List<TheaterDto> getAllTheaters() {
        return List.of();
    }
}
