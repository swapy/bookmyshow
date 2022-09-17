package com.bookmy.theatres.mapper;

import com.bookmy.theatres.domain.entity.ShowEntity;
import com.bookmy.theatres.spec.model.Show;
import com.bookmy.theatres.spec.model.UpdateShowRequest;
import java.time.LocalTime;

public class ShowsMapper {

    private ShowsMapper() {}

    public static Show mapShowEntityToResponse(ShowEntity savedShow) {
        return Show.builder()
            .showId(savedShow.getId())
            .houseFull(savedShow.getIsHouseFull())
            .showDate(savedShow.getShowDate())
            .showTime(savedShow.getShowTime().toString())
            .showId(savedShow.getId())
            .showStatus(savedShow.getShowStatus())
            .language(savedShow.getLanguage())
            .build();
    }

    public static ShowEntity mapShowForCreateOperation(UpdateShowRequest request) {
        final Show show = request.getShow();
        return ShowEntity.builder()
            .withShowDate(show.getShowDate())
            .withShowStatus(show.getShowStatus())
            .withShowTime(LocalTime.parse(show.getShowTime()))
            .withIsHouseFull(show.getHouseFull())
            .withLanguage(show.getLanguage())
            .withTheatre_id(request.getTheatreId())
            .withMovie_id(request.getMovieId())
            .build();
    }

    public static ShowEntity mapShowForUpdate(UpdateShowRequest request) {
        final Show show = request.getShow();
        return ShowEntity.builder()
            .withShowDate(show.getShowDate())
            .withShowStatus(show.getShowStatus())
            .withShowTime(LocalTime.parse(show.getShowTime()))
            .withIsHouseFull(show.getHouseFull())
            .withTheatre_id(request.getTheatreId())
            .withMovie_id(request.getMovieId())
            .withId(show.getShowId())
            .build();
    }


}
