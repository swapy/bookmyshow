CREATE TABLE theatre
  (
     id                 INT NOT NULL PRIMARY KEY,
     theatre_name       VARCHAR(255),
     city               VARCHAR(255),
     "created_at"       TIMESTAMP WITH time zone NOT NULL,
     "last_modified_at" TIMESTAMP WITH time zone NULL,
     "created_by"       VARCHAR(255) NULL,
     "last_modified_by" VARCHAR(255) NULL
  );

CREATE TABLE movie
  (
     id                 INT NOT NULL PRIMARY KEY,
     mov_name           VARCHAR(255),
     release_date       DATE,
     "created_at"       TIMESTAMP WITH time zone NOT NULL,
     "last_modified_at" TIMESTAMP WITH time zone NULL,
     "created_by"       VARCHAR(255) NULL,
     "last_modified_by" VARCHAR(255) NULL
  );

CREATE TABLE mshow
  (
     id                 INT NOT NULL PRIMARY KEY,
     show_date          DATE,
     show_time          TIME,
     showstatus         VARCHAR(255),
     ishousefull        BOOLEAN,
     movie_id           INT,
     theatre_id         INT,
     "created_at"       TIMESTAMP WITH time zone NOT NULL,
     "last_modified_at" TIMESTAMP WITH time zone NULL,
     "created_by"       VARCHAR(255) NULL,
     "last_modified_by" VARCHAR(255) NULL,
     CONSTRAINT fk_movie FOREIGN KEY(movie_id) REFERENCES movie(id),
     CONSTRAINT fk_theatre FOREIGN KEY(theatre_id) REFERENCES theatre(id)
  );