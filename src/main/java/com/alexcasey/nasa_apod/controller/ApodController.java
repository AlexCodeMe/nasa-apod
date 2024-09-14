package com.alexcasey.nasa_apod.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexcasey.nasa_apod.model.Apod;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/apods")
public class ApodController {

    /**
     * Get the Astronomy Picture of the Day
     * 
     * @return
     */
    @GetMapping
    public ResponseEntity<Apod> getApod() {
        return null;
    }

    /**
     * Get the Astronomy Picture of the Day by Date
     * 
     * @return
     */
    @GetMapping("/by-date")
    public ResponseEntity<Apod> getApodByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return null;
    }

    /**
     * Get the Astronomy Picture of the Day by Date range
     * 
     * @return
     */
    @GetMapping("/by-date-range")
    public ResponseEntity<Apod> getApodByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start_date,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end_date) {
        return null;
    }

    /**
     * Get the count random Astronomy Picture of the Days
     * 
     * @return
     */
    @GetMapping("/random")
    public ResponseEntity<Apod> getRandomApods(
            @RequestParam Integer count,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start_date,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end_date) {
        return null;
    }
}
