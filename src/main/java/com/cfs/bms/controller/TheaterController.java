package com.cfs.bms.controller;

import com.cfs.bms.dto.TheaterDto;
import com.cfs.bms.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping
    public ResponseEntity<TheaterDto> createTheater(
            @RequestBody TheaterDto dto) {

        return new ResponseEntity<>(
                theaterService.createTheater(dto),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheaterDto> getTheater(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                theaterService.getTheaterById(id));
    }

    @GetMapping
    public ResponseEntity<List<TheaterDto>> getAllTheaters() {

        return ResponseEntity.ok(
                theaterService.getAllTheaters());
    }
}
