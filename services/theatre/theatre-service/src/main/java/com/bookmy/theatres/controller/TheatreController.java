package com.bookmy.theatres.controller;

import com.bookmy.theatres.service.TheatreService;
import com.bookmy.theatres.spec.api.TheatresApi;
import com.bookmy.theatres.spec.model.Theatre;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class TheatreController implements TheatresApi {

    private final TheatreService theatreService;

    @Override
    public ResponseEntity<List<Theatre>> getShowsByTheatre(
        @PathVariable("movieName") String movieName,
        @Valid @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        @Valid @RequestParam(value = "city", required = false) String city) {
        final Collection<Theatre> showsByCityDateAndMovie = theatreService.getShowsByCityDateAndMovie(
            city, date, movieName);
        return ResponseEntity.ok(List.copyOf(showsByCityDateAndMovie));
    }
}
