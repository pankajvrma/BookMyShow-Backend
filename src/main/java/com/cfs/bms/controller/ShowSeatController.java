package com.cfs.bms.controller;

import com.cfs.bms.dto.ShowSeatDto;
import com.cfs.bms.service.ShowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/show-seats")
public class ShowSeatController {

    @Autowired
    private ShowSeatService showSeatService;

    @GetMapping("/show/{showId}")
    public ResponseEntity<List<ShowSeatDto>> getSeatsForShow(
            @PathVariable Long showId) {

        return ResponseEntity.ok(
                showSeatService.getSeatsByShow(showId));
    }

    @GetMapping("/show/{showId}/available")
    public ResponseEntity<List<ShowSeatDto>> getAvailableSeats(
            @PathVariable Long showId) {

        return ResponseEntity.ok(
                showSeatService.getAvailableSeats(showId));
    }
}

