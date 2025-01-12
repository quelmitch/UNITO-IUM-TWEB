

# README for Data Analysis Project

## Overview

This project focuses on analyzing and visualizing datasets related to movies. It is structured into two main Jupyter Notebooks:

1. **Data Cleaning**: Prepares raw data for analysis by cleaning, validating, and formatting datasets.  
2. **Data Visualization**: Explores and interprets cleaned data using various visualization techniques to uncover insights.

The cleaned datasets can be found on this shared drive:
- [Shared Google Drive Folder](https://drive.google.com/drive/folders/14OxhhOg3TxA2dzB_y8f4NYJa3RL1wGee)

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

#### Visualization Tools:
A diverse set of Python libraries is used to create static, interactive, and geographic visualizations:  
- **Plotly**: Interactive plots and dashboards.  
- **Seaborn**: Aesthetic statistical graphics.  
- **Matplotlib**: Foundational static visualizations.  
- **Geopandas**: Geographic data handling and mapping.  
- **Folium**: Interactive geographic visualizations. TODO

These tools facilitate a comprehensive and engaging analysis of the datasets.

#### Visualizations:
The visualizations done include:
- TODO

---

## Requirements
The project relies on the following Python libraries:
- **Pandas** and **NumPy** for data manipulation.
- **Plotly**, **Matplotlib**, **Seaborn**, **Geopandas**, and **Folium** for visualizations. TODO 
- **Jupyter Notebook** for running and documenting the analysis.

**Python Version**: The project uses Python **3.13**

Install dependencies using `pip` or `conda`.