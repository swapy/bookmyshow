package com.bookmy.theatres.service;

import com.bookmy.theatres.domain.response.ShowTheatreResponse;
import com.bookmy.theatres.domain.response.ShowTheatreResultSetMapping;
import com.bookmy.theatres.repository.ShowRepository;
import com.bookmy.theatres.spec.model.Theatre;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TheatreService {

    private final ShowRepository showRepository;

    public Collection<Theatre> getShowsByCityDateAndMovie(String city, LocalDate date,String movieName) {
        final List<ShowTheatreResultSetMapping> resultSetMappings = showRepository.findShowsWithMetadata(city,date,
            movieName);
      log.info("fetching shows for city {} movie name {} date {}",city,movieName,date);
      log.info("shows fetched are {}",resultSetMappings);
        ShowTheatreResponse response = new ShowTheatreResponse();
        final Collection<Theatre> theatres = response.mapResponse(resultSetMappings);
        return theatres;
    }

}
