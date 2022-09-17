package com.bookmy.theatres.service;

import static com.bookmy.theatres.exception.TheatreErrors.INVALID_SHOWID;

import com.bookmy.errors.errors.BadRequestProblem;
import com.bookmy.theatres.domain.entity.ShowEntity;
import com.bookmy.theatres.mapper.ShowTheatreResponseMapper;
import com.bookmy.theatres.mapper.ShowTheatreResultSetMapping;
import com.bookmy.theatres.mapper.ShowsMapper;
import com.bookmy.theatres.repository.ShowRepository;
import com.bookmy.theatres.spec.model.Show;
import com.bookmy.theatres.spec.model.Theatre;
import com.bookmy.theatres.spec.model.UpdateShowRequest;
import java.time.LocalDate;
import java.time.LocalTime;
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
public class ShowService {

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

    @Transactional
    public Show createShow(UpdateShowRequest updateShowRequest) {
        ShowEntity show = ShowsMapper.mapShowForCreateOperation(updateShowRequest);
        final ShowEntity savedShow = showRepository.save(show);
        return ShowsMapper.mapShowEntityToResponse(savedShow);
    }


    @Transactional
    public Show updateShow(UpdateShowRequest updateShowRequest) {
        final Integer showId = updateShowRequest.getShow().getShowId();
        if (Objects.isNull(showId)) {
            throw new BadRequestProblem(INVALID_SHOWID.getCode(), INVALID_SHOWID.getMessage());
        }

//        ShowEntity show = ShowsMapper.mapShowForUpdate(updateShowRequest);
        final Optional<ShowEntity> showEntity = showRepository.findById(showId);
        if (showEntity.isEmpty()) {
            throw new BadRequestProblem(INVALID_SHOWID.getCode(), INVALID_SHOWID.getMessage());
        }

        final ShowEntity show = showEntity.get();
        ShowEntity updated = updateShowValues(show, updateShowRequest);
        return ShowsMapper.mapShowEntityToResponse(updated);

    }

    private ShowEntity updateShowValues(ShowEntity show, UpdateShowRequest updateShowRequest) {
        Show request = updateShowRequest.getShow();
        show.setShowDate(request.getShowDate());
        show.setShowTime(LocalTime.parse(request.getShowTime()));
        show.setLanguage(request.getLanguage());
        show.setIsHouseFull(request.getHouseFull());
        show.setIsHouseFull(request.getHouseFull());
        show.setShowStatus(request.getShowStatus());
        show.setTheatre_id(updateShowRequest.getTheatreId());

        return show;
    }

    public void deleteShow(Integer showId) {
        showRepository.deleteById(showId);
    }


    @AllArgsConstructor
    @Data
    class Params {

        private String city;
        private LocalDate date;
        private String movieName;
    }

}
