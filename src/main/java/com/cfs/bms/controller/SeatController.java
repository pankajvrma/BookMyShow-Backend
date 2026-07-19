package com.cfs.bms.controller;

import com.cfs.bms.dto.SeatDto;
import com.cfs.bms.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping
    public ResponseEntity<SeatDto> createSeat(
            @RequestBody SeatDto dto) {

        return new ResponseEntity<>(
                seatService.createSeat(dto),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatDto> getSeat(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                seatService.getSeatById(id));
    }

    @GetMapping("/screen/{screenId}")
    public ResponseEntity<List<SeatDto>> getSeatsByScreen(
            @PathVariable Long screenId) {

        return ResponseEntity.ok(
                seatService.getSeatsByScreen(screenId));
    }
}
