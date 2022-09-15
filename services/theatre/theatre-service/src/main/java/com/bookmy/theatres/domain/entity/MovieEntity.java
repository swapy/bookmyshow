package com.bookmy.theatres.domain.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@SuperBuilder(setterPrefix = "with")
@Getter
@Setter
@Table(name = "movie")
public class MovieEntity extends AbstractEntity {

    @NotNull
    @Column(name = "mov_name")
    private String name;

    @NotNull
    private LocalDate releaseDate;
    @OneToMany
    @JoinColumn(name = "movie_id")
    private Set<ShowEntity> showEntities;

    @Override
    public String toString() {
        return "MovieEntity{" +
            "name='" + name + '\'' +
            ", releaseDate=" + releaseDate +
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
        MovieEntity that = (MovieEntity) o;
        return id == that.id && name.equals(that.name) && releaseDate.equals(
            that.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate);
    }

    @Override
    public Integer getId() {
        return id;
    }
}
