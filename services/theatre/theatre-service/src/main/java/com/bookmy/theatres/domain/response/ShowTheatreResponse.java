package com.bookmy.theatres.domain.response;

import com.bookmy.theatres.spec.model.Movie;
import com.bookmy.theatres.spec.model.Show;
import com.bookmy.theatres.spec.model.Theatre;
import java.util.*;

public class ShowTheatreResponse {

    public Collection<Theatre> mapResponse(List<ShowTheatreResultSetMapping> resultSetMappings) {
        Map<Integer, Theatre> map = new LinkedHashMap<>();

        for (ShowTheatreResultSetMapping mapping : resultSetMappings) {
            if (!map.containsKey(mapping.getTheatreId())) {
                Theatre theatre = Theatre.builder()
                    .theatreId(mapping.getTheatreId())
                    .city(mapping.getCity())
                    .name(mapping.getTheatre_name())
                    .build();
                map.put(mapping.getTheatreId(), theatre);
            }
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

        for (ShowTheatreResultSetMapping mapping : resultSetMappings) {
            final List<Movie> movies = map.get(mapping.getTheatreId())
                .getMovies();

            Show show = Show.builder()
                .showId(mapping.getShowId())
                .showTime(mapping.getShowTime().toString())
                .showDate(mapping.getShowDate())
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
        return map.values();
    }
}

