CREATE TABLE theatre (
    id int not null primary key,
    theatre_name varchar(255),
    city varchar(255)
);

CREATE TABLE movie (
    id int not null primary key,
    mov_name varchar(255),
    release_date date
);


CREATE TABLE mshow (
    id int not null primary key,
    show_date date,
    show_time time,
    showstatus varchar(255),
    ishouseFull boolean,
    movie_id int,
    theatre_id int,
    CONSTRAINT fk_movie FOREIGN KEY(movie_id) REFERENCES movie(id),
    CONSTRAINT fk_theatre FOREIGN KEY(theatre_id) REFERENCES theatre(id)
);
