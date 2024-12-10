-- Dialect: PostgreSQL

-- Populate "movies" table
INSERT INTO movie (id, title, release_year, duration_in_minutes, tagline, description, rating)
VALUES (1, 'Inception', 2010, 148, 'Your mind is the scene of the crime', 'A thief who enters the dreams of others to steal secrets from their subconscious is given the task of planting an idea into the mind of a CEO.', 8.8);

INSERT INTO movie (id, title, release_year, duration_in_minutes, tagline, description, rating)
VALUES (2, 'The Dark Knight', 2008, 152, 'Why so serious?', 'When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham, forcing Batman to come to terms with one of the greatest psychological and moral dilemmas he has ever faced.', 9.0);

INSERT INTO movie (id, title, release_year, duration_in_minutes, tagline, description, rating)
VALUES (3, 'Interstellar', 2014, 169, 'Mankind was born on Earth. It was never meant to die here.', 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity survival.', 8.6);

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
