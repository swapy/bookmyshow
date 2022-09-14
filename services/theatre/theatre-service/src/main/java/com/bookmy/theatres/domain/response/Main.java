//package com.bookmy.theatres.domain.response;
//
//import com.bookmy.theatres.domain.entity.ShowStatus;
//import com.bookmy.theatres.spec.model.Movie;
//import com.bookmy.theatres.spec.model.Show;
//import com.bookmy.theatres.spec.model.Theatre;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
//import java.io.File;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.*;
//
//public class Main {
//
//    public static void mainz(
//        Collection<Theatre> ts) throws IOException {
//        // Up to Jackson 2.9: (but not with 3.0)
//        ObjectMapper mapper = new ObjectMapper()
//            .registerModule(new ParameterNamesModule())
//            .registerModule(new Jdk8Module())
//            .registerModule(new JavaTimeModule()); // new module, NOT JSR310Module
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//
//        File f = new File("theatres.json");
//        List<Theatre> theatres = new ArrayList<>();
//
//        List<Seat> seats = new ArrayList<>();
//        seats.add(createSeat("A1"));
//        seats.add(createSeat("A2"));
//        seats.add(createSeat("A3"));
//
//        SeatingArrangement seatingArrangement = SeatingArrangement.builder()
//            .withId(500)
//            .build();
//
//        Show show1 = getShow(List.of(seatingArrangement), ShowStatus.ON_TIME, 400);
//        Show show2 = getShow(List.of(seatingArrangement), ShowStatus.DELAYED, 401);
//
//        Movie mov1 = getMovie(Set.of(show1, show2), 200, "Pushpa");
//
//        Movie mov2 = getMovie(Set.of(show1, show2), 201, "Rocky");
//
//        Theatre thaneTheatre = Theatre.builder()
//            .withTheatreId(100)
//            .withCity("thane")
//            .withName("PVR Thane")
//            .withMovies(Set.of(mov1, mov2))
//            .build();
//
//        Theatre puneTheatre = Theatre.builder()
//            .withTheatreId(100)
//            .withCity("pune")
//            .withName("Inox Pune")
//            .withMovies(Set.of(mov1, mov2))
//            .build();
//        theatres.add(thaneTheatre);
//        theatres.add(puneTheatre);
//
//        //filter
//        //Browse theatres currently running the show (movie selected) in the town,
//        //including show timing by a chosen date
//        List<Show> filteredShows = theatres.stream()
//            .filter(t -> t.getCity().equals("thane"))
//            .flatMap(t -> t.getMovies().stream())
//            .filter(movie -> movie.getName().equals("Pushpa"))
//            .flatMap(movie -> movie.getShows().stream())
////            .filter(show -> show.getStartTime().isBefore(LocalDateTime.now()))
//            .toList();
////        mapper.writer().withDefaultPrettyPrinter().writeValue(f, theatres);
//        mapper.writer().withDefaultPrettyPrinter().writeValue(f, ts);
//
////        mapper.writer().withDefaultPrettyPrinter().writeValue(f, filteredShows);
//    }
//
//    private static Movie getMovie(Set<Show> shows, int i, String name) {
//        return Movie.builder()
//            .withMovieId(i)
//            .withName(name)
//            .withShows(shows)
//            .withReleaseDate(LocalDate.now())
//            .build();
//    }
//
//    private static Show getShow(List<SeatingArrangement> arrangement, ShowStatus showStatus,
//        int i) {
//        return Show.builder()
//            .withShowStatus(showStatus)
//            .withShowId(i)
////            .withStartTime(LocalDateTime.now())
//            .withIsHouseFull(false)
//            .withSeatingArrangement(arrangement)
//            .build();
//    }
//
//    private static Seat createSeat(String a1) {
//        return Seat.builder().withSeatId(a1).withIsAllocated(false).build();
//    }
//
//}
