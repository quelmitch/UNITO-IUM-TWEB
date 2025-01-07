import os
import numpy as np
from PIL import Image
from collections import Counter
import pandas as pd
from multiprocessing import Pool, cpu_count
import time


# Retrieve the mathematical average color
def get_average_color(image_path):
    try:
        with Image.open(image_path) as img:
            pixels = list(img.getdata())

            # Average Color
            avg_red = 0
            avg_green = 0
            avg_blue = 0
            for pixel in pixels:
                avg_red += pixel[0]
                avg_green += pixel[1]
                avg_blue += pixel[2]
            avg_red //= len(pixels)
            avg_green //= len(pixels)
            avg_blue //= len(pixels)

            return f"{avg_red},{avg_green},{avg_blue}"
    except Exception:
        return None


# Elaborate single row
def process_image_row(row):
    image_path = row['poster_path']
    try:
        avg_rgb = get_average_color(image_path)
    except Exception as e:
        print(f"Error processing image {image_path}: {e}")
        avg_rgb = None
    return {
        "id": row.name,
        "average_color": avg_rgb if avg_rgb else np.nan,
    }


# Iterates dataset using thread pool (default all CPU cores)
def iterate_dataset_with_thread_pool(movie_df, max_workers=cpu_count()):
    with Pool(processes=max_workers) as pool:
        results = pool.map(process_image_row, [row for _, row in movie_df.iterrows()])

    # Convert results to a DataFrame and merge with the original DataFrame
    results_df = pd.DataFrame(results).set_index("id")
    movie_df = movie_df.join(results_df)
    return movie_df


def main():
    # Load the movie dataset
    movie_df = pd.read_csv('../clean_datasets/movies.csv')
    movie_df = movie_df[['id', 'title', 'poster_link']].set_index('id')

    # Prepare poster paths
    image_path = '../datasets/posters'
    poster_paths = [os.path.join(image_path, str(i) + ".jpg") for i in movie_df.index]
    movie_df["poster_path"] = poster_paths

    # TEST SUBSET FOR BENCHMARKING
    # movie_df = movie_df.head(<<INSERT HERE SUBSET SIZE>>).copy()
    # Measure processing time
    # start = time.time()
    # try:
    #     movie_df = add_dominant_colors_to_dataframe(movie_df, max_workers=cpu_count())
    # except Exception as e:
    #     print(f"Error processing data: {e}")
    # end = time.time()
    # print(f"Time to process: {end - start:.2f} seconds")
    # print(movie_df.head())  # Display the first few rows of the updated DataFrame

    # Full processing (uncomment for production use)
    start = time.time()
    movie_df = iterate_dataset_with_thread_pool(movie_df, max_workers=cpu_count())
    end = time.time()
    print(f"Full processing time: {end - start:.2f} seconds")

    # Save the results
    output_csv = "../clean_datasets/posters_colors.csv"
    movie_df.to_csv(output_csv, index=True)
    print(f"Results saved to {output_csv}")


if __name__ == "__main__":
    main()

## BENCHMARKS RESULTS
# 100 - 1 sec
# 1000 - 2.5
# 10000 - 19.29 seconds
# 50000 - 99 sec
# 100000 - 184 sec
# All - 2000 sec (30 min)
