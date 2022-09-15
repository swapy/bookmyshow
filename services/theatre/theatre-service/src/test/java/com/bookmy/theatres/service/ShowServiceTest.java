package com.bookmy.theatres.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.bookmy.theatres.domain.response.ShowTheatreResultSetMapping;
import com.bookmy.theatres.repository.ShowRepository;
import com.bookmy.theatres.spec.model.Movie;
import com.bookmy.theatres.spec.model.Show;
import com.bookmy.theatres.spec.model.Show.ShowStatusEnum;
import com.bookmy.theatres.spec.model.Theatre;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TheatreServiceTest {

    private TheatreService theatreService;

    @Mock
    private ShowRepository showRepository;

    private static List<ShowTheatreResultSetMapping> dbResponse() {
      /*
      thane
        t1 {
            "pushpa" {
                1
            },
            "swades" {
                2,5
            }
        }

        t2 {
            "pushpa" {
                3,4
            }
        }
        */

        return List.of(
            createSingleMapping("thane", "Pushpa", LocalDate.now(), 1, 1, "t1"),
            createSingleMapping("thane", "Swades", LocalDate.now(), 2, 1, "t1"),
            createSingleMapping("pune", "Pushpa", LocalDate.now(), 3, 2, "t2"),
            createSingleMapping("pune", "Pushpa", LocalDate.now(), 4, 2, "t2"),
            createSingleMapping("thane", "Swades", LocalDate.now(), 5, 1, "t1")
        );
    }

    private static ShowTheatreResultSetMapping createSingleMapping(String city, String movieName,
        LocalDate date, int showId,
        int theatreid, String tname) {
        return
            ShowTheatreResultSetMapping.builder()
                .withCity(city)
                .withIshousefull(false)
                .withMovieName(movieName)
                .withShowDate(date)
                .withShowStatus(ShowStatusEnum.ON_TIME.toString())
                .withShowTime(LocalTime.now())
                .withShowId(showId)
                .withTheatre_name(tname)
                .withTheatreId(theatreid)
                .build();
    }

    @BeforeEach
    void setup() {
        theatreService = new TheatreService(showRepository);
    }

    @Test
    void onNoResultSendEmptyResult() {
        when(showRepository.findShowsWithMetadata(anyString(), any(LocalDate.class),
            anyString())).thenReturn(
            Collections.emptyList());
        final Collection<Theatre> showsByCityDateAndMovie = theatreService.getShowsByCityDateAndMovie(
            anyString(), any(LocalDate.class), anyString());

        assertThat(showsByCityDateAndMovie).isEmpty();
    }

    @Test
    void mapDbResponseCorrectly() {
        String city = "thane";
        String movieName = "Pushpa";

        when(showRepository.findShowsWithMetadata(city, LocalDate.now(), movieName)).thenReturn(
            dbResponse());
        final Collection<Theatre> theatres = theatreService.getShowsByCityDateAndMovie(
            city, LocalDate.now(), movieName);

        assertThat(theatres).hasSize(2);
        Collection<Theatre> expectedTheatres = getExpected(city, movieName);
        assertThat(theatres).hasSameSizeAs(expectedTheatres)
            .extracting(Theatre::getMovies)
            .hasSize(2);
    }

    private Collection<Theatre> getExpected(String city, String movieName) {

        Theatre t1 = Theatre
            .builder()
            .theatreId(1)
            .city("thane")
            .name("t1")
            .build();

        Theatre t2 = Theatre
            .builder()
            .theatreId(2)
            .city("pune")
            .name("t2").build();

        Movie m1 = Movie.builder()
            .name("Pushpa")
            .build();
        Movie m2 = Movie.builder()
            .name("Swades")
            .build();
        Movie m3 = Movie.builder()
            .name("Pushpa")
            .build();

        Show s1 = Show.builder()
            .showDate(LocalDate.now())
            .showTime(LocalTime.now().toString())
            .showId(1)
            .houseFull(false)
            .showStatus(ShowStatusEnum.ON_TIME)
            .build();

        Show s2 = Show.builder()
            .showDate(LocalDate.now())
            .showTime(LocalTime.now().toString())
            .showId(2)
            .houseFull(false)
            .showStatus(ShowStatusEnum.ON_TIME)
            .build();

        Show s3 = Show.builder()
            .showDate(LocalDate.now())
            .showTime(LocalTime.now().toString())
            .showId(3)
            .houseFull(false)
            .showStatus(ShowStatusEnum.ON_TIME)
            .build();


        Show s4 = Show.builder()
            .showDate(LocalDate.now())
            .showTime(LocalTime.now().toString())
            .showId(4)
            .houseFull(false)
            .showStatus(ShowStatusEnum.ON_TIME)
            .build();

        Show s5 = Show.builder()
            .showDate(LocalDate.now())
            .showTime(LocalTime.now().toString())
            .showId(5)
            .houseFull(false)
            .showStatus(ShowStatusEnum.ON_TIME)
            .build();

        m1.shows(List.of(s1));
        m2.shows(List.of(s2,s5));
        m3.shows(List.of(s3,s4));

        t1.setMovies(List.of(m1,m2));
        t2.setMovies(List.of(m3));

        return List.of(t1,t2);
    }

}