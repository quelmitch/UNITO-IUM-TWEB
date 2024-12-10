-- Dialect: PostgreSQL

-- Populate "movies" table
INSERT INTO movie (id, title, release_year, duration_in_minutes, tagline, description, rating)
VALUES (1, 'Inception', 2010, 148, 'Your mind is the scene of the crime', 'A thief who enters the dreams of others to steal secrets from their subconscious is given the task of planting an idea into the mind of a CEO.', 8.8);

INSERT INTO movie (id, title, release_year, duration_in_minutes, tagline, description, rating)
VALUES (2, 'The Dark Knight', 2008, 152, 'Why so serious?', 'When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham, forcing Batman to come to terms with one of the greatest psychological and moral dilemmas he has ever faced.', 9.0);

INSERT INTO movie (id, title, release_year, duration_in_minutes, tagline, description, rating)
VALUES (3, 'Interstellar', 2014, 169, 'Mankind was born on Earth. It was never meant to die here.', 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity survival.', 8.6);

INSERT INTO oscar_ceremony (number, year)
VALUES
    (1, 2021),
    (2, 2022);

INSERT INTO oscar_nomination (id, ceremony_number, category, movie_year, movie, person, winner)
VALUES
    (1, 1, 'Best Picture', 2020, 'Nomadland', NULL, true),
    (2, 1, 'Best Director', 2020, NULL, 'Chloé Zhao', true),
    (3, 1, 'Best Actor', 2020, NULL, 'Anthony Hopkins', true),
    (4, 1, 'Best Actress', 2020, NULL, 'Frances McDormand', true),
    (5, 2, 'Best Picture', 2021, 'CODA', NULL, true);
