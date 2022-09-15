package com.bookmy.theatres.repository;

import com.bookmy.theatres.domain.entity.ShowEntity;
import com.bookmy.theatres.mapper.ShowTheatreResultSetMapping;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
    @Query(nativeQuery = true)
    List<ShowTheatreResultSetMapping> findShowsWithMetadata(String city,LocalDate date, String movieName);
}
