package com.bookmy.theatres.controller;

import com.bookmy.theatres.service.ShowService;
import com.bookmy.theatres.spec.api.ShowsApi;
import com.bookmy.theatres.spec.api.TheatresApi;
import com.bookmy.theatres.spec.model.Show;
import com.bookmy.theatres.spec.model.Theatre;
import com.bookmy.theatres.spec.model.UpdateShowRequest;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
public class ShowsController implements ShowsApi, TheatresApi {

    private final ShowService showService;

    @Override
    public ResponseEntity<Show> createShows(UpdateShowRequest updateShowRequest) {
        Show show = showService.createShow(updateShowRequest);
        return ResponseEntity.ok(show);
    }

    @Override
    public ResponseEntity<Void> deleteShow(Integer showId) {
        showService.deleteShow(showId);
        Void voidval=null;
        return ResponseEntity.ok(voidval);
    }

    @Override
    public ResponseEntity<List<Theatre>> getShowsByTheatre(
        @PathVariable("movieName") String movieName,
        @Valid @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        @Valid @RequestParam(value = "city", required = false) String city) {
        final Collection<Theatre> showsByCityDateAndMovie = showService.getShowsByCityDateAndMovie(
            city, date, movieName);
        return ResponseEntity.ok(List.copyOf(showsByCityDateAndMovie));
    }

    @Override
    public ResponseEntity<Show> updateShow(UpdateShowRequest updateShowRequest) {
        Show show = showService.updateShow(updateShowRequest);
        return ResponseEntity.ok(show);
    }
}
