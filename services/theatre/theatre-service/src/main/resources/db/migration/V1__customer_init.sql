CREATE SEQUENCE theatre_seq;

CREATE SEQUENCE movie_seq;

CREATE SEQUENCE mshow_seq;

CREATE TABLE theatre
  (
     id                 INT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('theatre_seq'),
     theatre_name       VARCHAR(255),
     city               VARCHAR(255),
     "created_at"       TIMESTAMP WITH TIME zone NOT NULL,
     "last_modified_at" TIMESTAMP WITH TIME zone NULL,
     "created_by"       VARCHAR(255) NULL,
     "last_modified_by" VARCHAR(255) NULL
  );

CREATE TABLE movie
  (
     id                 INT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('movie_seq'),
     mov_name           VARCHAR(255),
     release_date       DATE,
     "created_at"       TIMESTAMP WITH TIME zone NOT NULL,
     "last_modified_at" TIMESTAMP WITH TIME zone NULL,
     "created_by"       VARCHAR(255) NULL,
     "last_modified_by" VARCHAR(255) NULL
  );

CREATE TABLE mshow
  (
     id                 INT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('mshow_seq'),
     show_date          DATE,
     show_time          TIME,
     showstatus         VARCHAR(255),
     ishousefull        BOOLEAN,
     movie_id           INT,
     theatre_id         INT,
     inlanguage           varchar(255),
     "created_at"       TIMESTAMP WITH TIME zone NOT NULL,
     "last_modified_at" TIMESTAMP WITH TIME zone NULL,
     "created_by"       VARCHAR(255) NULL,
     "last_modified_by" VARCHAR(255) NULL,
     CONSTRAINT fk_movie FOREIGN KEY(movie_id) REFERENCES movie(id),
     CONSTRAINT fk_theatre FOREIGN KEY(theatre_id) REFERENCES theatre(id)
  );

ALTER SEQUENCE theatre_seq owned BY theatre.id;

ALTER SEQUENCE movie_seq owned BY movie.id;

ALTER SEQUENCE mshow_seq owned BY mshow.id;