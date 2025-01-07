import psycopg2

def import_csv(conn, table_name, file_path):
    """Imports a CSV file into a PostgreSQL table."""
    with conn.cursor() as cur:
        with open(file_path, 'r', encoding='utf-8') as f:
            cur.copy_expert(f"COPY {table_name} FROM STDIN WITH (FORMAT CSV, HEADER)", f)
        conn.commit()

if __name__ == "__main__":
    # PostgreSQL connection details
    conn_params = {
        "host": "localhost",
        "database": "moviesdb",
        "user": "postgres",
        "password": "admin" 
    }

    try:
        conn = psycopg2.connect(**conn_params)
        print("Connected to PostgreSQL successfully")

        directory_path = "../data-analysis/clean_datasets/"
        # List of CSV files and corresponding table names
        csv_files = [
            ("movies.csv", "movie(id, title, release_year, tagline, description, runtime, rating, poster_link)"),
            ("actors.csv", "actor(movie_id, name, role)"),
            ("countries.csv", "country(movie_id, country)"),
            ("crew.csv", "crew(movie_id, role, name)"),
            ("genres.csv", "genre(movie_id, genre)"),
            ("languages.csv", "language(movie_id, type, language)"),
            ("oscars.csv", "oscar(year_movie, year_ceremony, number_ceremony, category, nominee_name, nominee_movie, is_winner)"),
            ("releases.csv", "release(movie_id, country, date, distribution_format, rating)"),
            ("genres.csv", "genre(movie_id, genre)"),
            ("studios.csv", "studio(movie_id, studio)"),
            ("themes.csv", "theme(movie_id, theme)"),
        ]

        for file_path, table_name in csv_files:
            import_csv(conn, table_name, directory_path + file_path)
            print(f"Imported {directory_path + file_path} into {table_name}")

    except (Exception, psycopg2.Error) as error:
        print("Error while connecting to PostgreSQL", error)

    finally:
        if conn:
            conn.close()
            print("PostgreSQL connection is closed")