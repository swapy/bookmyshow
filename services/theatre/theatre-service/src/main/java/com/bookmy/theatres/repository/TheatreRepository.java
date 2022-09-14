package com.bookmy.theatres.repository;

import com.bookmy.theatres.domain.entity.TheatreEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<TheatreEntity, Integer> {

    //    @Query("from TheatreEntity t where t.city=?1")
    List<TheatreEntity> findAllByCity(String city);
}
