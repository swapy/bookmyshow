package com.bookmy.theatres.domain.entity;

import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Getter
@Setter
@Table(name = "theatre")
public class TheatreEntity extends AbstractEntity {

    @NotNull
    @Column(name = "theatre_name")
    private String name;
    @NotNull
    private String city;

    @OneToMany
    @JoinColumn(name = "id")
    private Set<MovieEntity> movieEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TheatreEntity that = (TheatreEntity) o;
        return id == that.id && name.equals(that.name) && city.equals(that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city);
    }

    @Override
    public String toString() {
        return "TheatreEntity{" +
            "name='" + name + '\'' +
            ", city='" + city + '\'' +
            '}';
    }

    @Override
    public Integer getId() {
        return id;
    }
}
