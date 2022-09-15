package com.bookmy.theatres.repository;

import com.bookmy.theatres.domain.entity.ShowEntity;
import com.bookmy.theatres.domain.response.ShowTheatreResultSetMapping;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {

    List<ShowEntity> findShows(LocalDate date, String movieName, String city);

    @Query(nativeQuery = true)
    List<ShowTheatreResultSetMapping> findShowsWithMetadata(String city,LocalDate date, String movieName);

    @Query("from ShowEntity s where s.showDate=?1 and s.movie_id=?2 and s.theatre_id=?3")
    List<ShowEntity> findAllShows(LocalDate date, int movieId, int theatreId);


}
