package com.cfs.bms.controller;

import com.cfs.bms.dto.ShowDto;
import com.cfs.bms.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping
    public ResponseEntity<ShowDto> createShow(
            @RequestBody ShowDto dto) {

        return new ResponseEntity<>(
                showService.createShow(dto),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowDto> getShow(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                showService.getShowById(id));
    }

    @GetMapping
    public ResponseEntity<List<ShowDto>> getAllShows() {

        return ResponseEntity.ok(
                showService.getAllShows());
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowDto>> getShowsByMovie(
            @PathVariable Long movieId) {

        return ResponseEntity.ok(
                showService.getShowsByMovie(movieId));
    }
}