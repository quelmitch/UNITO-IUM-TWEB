-- Dialect: PostgreSQL

-- Aggiunge l'estensione
CREATE EXTENSION IF NOT EXISTS pg_trgm;

-- Crea l'indice B-tree sulla colonna 'title'
CREATE INDEX idx_movie_title ON movie (title);

-- Crea l'indice GIN per la ricerca full-text sulla colonna 'title'
CREATE INDEX idx_movie_title_fulltext ON movie USING GIN (to_tsvector('english', title));


-- Inserimento di cerimonie degli Oscar
INSERT INTO oscar_ceremony (year, number) VALUES
(2023, 1),
(2022, 2);

-- Inserimento di nomination collegate alla cerimonia del 2023
INSERT INTO oscar_nomination (id, category, movie, person, winner, ceremony_number) VALUES
(1, 'Best Picture', 'Everything Everywhere All At Once', '', true, 1),
(2, 'Best Actor', '', 'Brendan Fraser', true, 1),
(3, 'Best Actress', '', 'Michelle Yeoh', false, 1),
(4, 'Best Director', 'Everything Everywhere All At Once', 'Daniel Kwan & Daniel Scheinert', true, 1);

-- Inserimento di nomination collegate alla cerimonia del 2022
INSERT INTO oscar_nomination (id, category, movie, person, winner, ceremony_number) VALUES
(5, 'Best Picture', 'CODA', '', false, 2),
(6, 'Best Actor', '', 'Will Smith', true, 2),
(7, 'Best Actress', '', 'Jessica Chastain', true, 2),
(8, 'Best Director', 'The Power of the Dog', 'Jane Campion', true, 2);
