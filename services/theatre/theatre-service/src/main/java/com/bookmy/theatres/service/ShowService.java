package com.bookmy.theatres.service;

import com.bookmy.theatres.domain.response.ShowTheatreResponseMapper;
import com.bookmy.theatres.domain.response.ShowTheatreResultSetMapping;
import com.bookmy.theatres.repository.ShowRepository;
import com.bookmy.theatres.spec.model.Theatre;
import java.time.LocalDate;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TheatreService {

    private final ShowRepository showRepository;

    @Transactional(readOnly = true)
    public Collection<Theatre> getShowsByCityDateAndMovie(String city, LocalDate date,
        String movieName) {
        final Params params = new Params(city, date, movieName);
        validateInput(params);
        log.info("fetching shows for city {} movie name {} date {}", params.getCity(),
            params.getMovieName(), params.getDate());

        final List<ShowTheatreResultSetMapping> resultSetMappings = showRepository.findShowsWithMetadata(
            params.getCity(), params.getDate(),
            params.getMovieName());

        log.info("shows fetched are {}", resultSetMappings);
        if (resultSetMappings.isEmpty()) {
            log.info("No results found in db");
            return Collections.emptyList();
        }

        return ShowTheatreResponseMapper.mapResponse(
            resultSetMappings);
    }

    private void validateInput(Params params) {
        if (Objects.isNull(params.getCity())) {
            params.setCity("thane");
        }
        if (Objects.isNull(params.getDate())) {
            params.setDate(LocalDate.now());
        }
        if (Objects.isNull(params.getMovieName())) {
            params.setMovieName("Forest Gump");
        }
    }

    @AllArgsConstructor
    @Data
    class Params {

        private String city;
        private LocalDate date;
        private String movieName;
    }

}
