package com.cfs.bms.controller;

import com.cfs.bms.dto.ScreenDto;
import com.cfs.bms.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/screens")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    @PostMapping
    public ResponseEntity<ScreenDto> createScreen(
            @RequestBody ScreenDto dto) {

        return new ResponseEntity<>(
                screenService.createScreen(dto),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreenDto> getScreen(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                screenService.getScreenById(id));
    }

    @GetMapping
    public ResponseEntity<List<ScreenDto>> getAllScreens() {

        return ResponseEntity.ok(
                screenService.getAllScreens());
    }

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<ScreenDto>> getScreensByTheater(
            @PathVariable Long theaterId) {

        return ResponseEntity.ok(
                screenService.getScreensByTheater(theaterId));
    }
}