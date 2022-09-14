package com.bookmy.theatres.domain.response;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ShowTheatreResultSetMapping {

    private int showId;
    private LocalDate showDate;
    private LocalTime showTime;
    private Boolean ishousefull;
    private int theatreId;
    private String theatre_name;
    private String city;
    private String movieName;

}
