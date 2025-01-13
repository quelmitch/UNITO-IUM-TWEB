

# Data Analysis

## How to run
### Requirements
**Python Version**: The project uses Python **3.13**

The project relies on several Python libraries that can be installed with the following command:
```
pip install pandas matplotlib seaborn plotly wordcloud networkx Pillow scipy
```
### Step-by-Step guide
**Data Cleaning Notebook**
  1. Be sure the variable `PRINT_CSV` in the first code cell is set to `True` 
  2. Put the raw datasets in the `datasets` directory under the project folder `data-analysis`
  3. Run all

**Data Visualization**
1. The `poster_color.csv` dataset used in the **Poster Color Brightness Across Genres** visualization 
is generated through the `poster_average_color.py` script. As written in its notebook sections the running time to generate
the dataset is quite long, and it also requires to download approximately 23 GB of movie posters from this
[link](https://www.kaggle.com/datasets/gsimonx37/letterboxd?select=posters). <br>
We recommend to download directly the dataset from this shared Drive:
    - [Shared Google Drive Folder (clean datasets)](https://drive.google.com/drive/folders/14OxhhOg3TxA2dzB_y8f4NYJa3RL1wGee)
2. Put the `poster_color.csv` in the `clean_datasets` directory
3. Run all

---

## Overview

This project focuses on analyzing and visualizing datasets related to movies. It is structured into two main Jupyter Notebooks:

1. **Data Cleaning**: Prepares raw data for analysis by cleaning, validating, and formatting datasets.  
2. **Data Visualization**: Explores and interprets cleaned data using various visualization techniques to uncover insights.

---

## Notebooks

### 1. Data Cleaning
The **Data Cleaning** notebook processes several `.csv` files, each corresponding to a distinct dataset. For each dataset, the cleaning workflow includes:  
- **Data Understanding**: Initial exploration to understand the dataset's structure and contents.  
- **Data Cleaning**: Addressing missing values, removing duplicates, validating foreign keys, ensuring correct data types, and renaming columns for clarity.  
- *(Optional)* **Deep Clean**: Applying dataset-specific cleaning operations.  
- **Final Result**: Outputting cleaned datasets to new `.csv` files for downstream analysis.  

This step ensures high data quality, enabling reliable and meaningful analysis in subsequent stages.


### 2. Data Visualization
The **Data Visualization** notebook examines three datasets:  
1. **Movie Dataset**: Detailed information on individual films, fragmented into multiple linked tables.  
2. **Oscar Awards Dataset**: Historical records of nominations and winners since the inception of the Oscars.  
3. **Rotten Tomatoes Review Dataset**: Aggregated reviews and scores from critics.

Since these datasets lack a common unique identifier, correlating data across them poses a challenge. The visualization notebook addresses this by employing advanced exploratory techniques to analyze trends, patterns, and relationships.

#### Methodology:
- **Introduction**: Setting the context and objectives of the analysis.  
- **Prediction**: Hypotheses are developed to guide exploration and uncover biases.  
- **Analysis and Visualization**: Combining simple and advanced techniques to extract insights.  
- **Conclusion**: Summarizing findings and comparing results with predictions.
