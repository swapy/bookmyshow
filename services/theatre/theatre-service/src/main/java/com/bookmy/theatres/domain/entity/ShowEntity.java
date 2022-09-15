package com.bookmy.theatres.domain.entity;

import com.bookmy.theatres.domain.response.ShowTheatreResultSetMapping;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Getter
@Setter
@Table(name = "mshow")
//•	Browse theatres currently running the show (movie selected) in the town, including show timing by a chosen date

@NamedNativeQueries({
    @NamedNativeQuery(name = "ShowEntity.findShows", query = """
            SELECT *
            FROM   mshow m
            WHERE  m.show_date =? 1
                   AND m.movie_id = (SELECT id
                                     FROM   movie mm
                                     WHERE  mm.mov_name =? 2)
                   AND m.theatre_id IN(SELECT id
                                       FROM   theatre t
                                       WHERE  t.city =? 3)
        """
        , resultClass = ShowEntity.class),
    @NamedNativeQuery(name = "ShowEntity.findShowsWithMetadata", query = """
        SELECT s.id           AS showId,
               s.show_date    AS showDate,
               s.show_time    AS showTime,
               s.showstatus   AS showstatus,
               s.ishousefull  AS ishousefull,
               s.theatre_id   AS theatreId,
               t.theatre_name AS theatre_name,
               t.city         AS city,
               m.mov_name     AS movieName
        FROM   mshow s
               join theatre t
                 ON s.movie_id = (SELECT id
                                  FROM   movie mm
                                  WHERE  mm.mov_name = ?3)
                    AND s.theatre_id IN(SELECT id
                                        FROM   theatre tt
                                        WHERE  tt.city = ?1)
                    AND s.show_date = ?2
                    AND t.id = s.theatre_id
               join movie m
                 ON m.id = s.movie_id;
        """
        , resultSetMapping = "showTheatreMapping"),
})
@SqlResultSetMapping(
    name = "showTheatreMapping",  // same as resultSetMapping above in NativeQuery
    classes = {
        @ConstructorResult(
            targetClass = ShowTheatreResultSetMapping.class,
            columns = {
                @ColumnResult(name = "showId", type = Integer.class),
                @ColumnResult(name = "showDate", type = LocalDate.class),
                @ColumnResult(name = "showTime", type = LocalTime.class),
                @ColumnResult(name = "showstatus", type = String.class),
                @ColumnResult(name = "ishousefull", type = Boolean.class),
                @ColumnResult(name = "theatreId", type = Integer.class),
                @ColumnResult(name = "theatre_name", type = String.class),
                @ColumnResult(name = "city", type = String.class),
                @ColumnResult(name = "movieName", type = String.class)
            }
        )
    }
)

public class ShowEntity extends AbstractEntity {

    @NotNull
    private LocalDate showDate;
    @NotNull
    private LocalTime showTime;

    private int movie_id;
    private int theatre_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "showstatus")
    private ShowStatus showStatus;

    @Column(name = "ishousefull")
    private Boolean isHouseFull = false;

    //    @OneToMany
//    @JoinColumn(name = "showId")
//    @Transient
//    private List<SeatingArrangement> seatingArrangement;

    @Override
    public String toString() {
        return "ShowEntity{" +
            "showDate=" + showDate +
            ", showTime=" + showTime +
            ", movie_id=" + movie_id +
            ", theatre_id=" + theatre_id +
            ", showStatus=" + showStatus +
            ", isHouseFull=" + isHouseFull +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShowEntity that = (ShowEntity) o;
        return isHouseFull == that.isHouseFull && Objects.equals(showDate, that.showDate)
            && Objects.equals(showTime, that.showTime) && showStatus == that.showStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(showDate, showTime, showStatus, isHouseFull);
    }

    @Override
    public Integer getId() {
        return id;
    }
}
