-- Dialect: PostgreSQL

-- Aggiunge l'estensione se non esiste già
CREATE EXTENSION IF NOT EXISTS pg_trgm;

-- Crea l'indice B-tree sulla colonna 'title' se non esiste già
CREATE INDEX IF NOT EXISTS idx_movie_title ON movie (title);

-- Crea l'indice GIN per la ricerca full-text sulla colonna 'title' se non esiste già
CREATE INDEX IF NOT EXISTS idx_movie_title_fulltext ON movie USING GIN (to_tsvector('english', title));
