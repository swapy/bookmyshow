package com.bookmy.theatres.domain.response;

import com.bookmy.theatres.spec.model.Movie;
import com.bookmy.theatres.spec.model.Show;
import com.bookmy.theatres.spec.model.Show.ShowStatusEnum;
import com.bookmy.theatres.spec.model.Theatre;
import java.util.*;

public class ShowTheatreResponseMapper {
    private ShowTheatreResponseMapper() {}

    public static Collection<Theatre> mapResponse(
        List<ShowTheatreResultSetMapping> resultSetMappings) {
        Map<Integer, Theatre> map = new LinkedHashMap<>();

        for (ShowTheatreResultSetMapping mapping : resultSetMappings) {
            if (!map.containsKey(mapping.getTheatreId())) {
                Theatre theatre = createTheatre(mapping);
                map.put(mapping.getTheatreId(), theatre);
            }
            mapMovies(map, mapping);
        }

        mapShows(resultSetMappings, map);
        return map.values();
    }

    private static void mapShows(List<ShowTheatreResultSetMapping> resultSetMappings,
        Map<Integer, Theatre> map) {
        for (ShowTheatreResultSetMapping mapping : resultSetMappings) {
            final List<Movie> movies = map.get(mapping.getTheatreId())
                .getMovies();

            Show show = Show.builder()
                .showId(mapping.getShowId())
                .showTime(mapping.getShowTime().toString())
                .showDate(mapping.getShowDate())
                .showStatus(ShowStatusEnum.fromValue(mapping.getShowStatus()))
                .build();

            movies.forEach(m -> {
                if (m.getName().equals(mapping.getMovieName())) {
                    if (m.getShows() == null) {
                        List<Show> shows = new ArrayList<>();
                        m.setShows(shows);
                    }
                    m.getShows().add(show);
                }
            });
        }
    }

    private static void mapMovies(Map<Integer, Theatre> map, ShowTheatreResultSetMapping mapping) {
        Movie movie = Movie.builder()
            .name(mapping.getMovieName())
            .build();

        final Theatre theatre = map.get(mapping.getTheatreId());
        List<Movie> movies = map.get(mapping.getTheatreId())
            .getMovies();
        if (movies == null) {
            movies = new ArrayList<>();
            movies.add(movie);
            theatre
                .setMovies(movies);
        }
    }

    private static Theatre createTheatre(ShowTheatreResultSetMapping mapping) {
        Theatre theatre = Theatre.builder()
            .theatreId(mapping.getTheatreId())
            .city(mapping.getCity())
            .name(mapping.getTheatre_name())
            .build();
        return theatre;
    }
}

