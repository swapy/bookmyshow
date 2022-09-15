package com.bookmy.theatres.mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder(setterPrefix = "with")
public class ShowTheatreResultSetMapping {

    private int showId;
    private LocalDate showDate;
    private LocalTime showTime;
    private String showStatus;
    private Boolean ishousefull;
    private int theatreId;
    private String theatre_name;
    private String city;
    private String movieName;

}
