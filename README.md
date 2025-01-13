# UNITO-IUM-TWEB

#### Assingment
- https://docs.google.com/document/d/1UiGNTrXqE2CHvWk38tJIYUxOovCEOiOkyIpSw4pMR8o

# Data Analysis

## How to run
### Requirements
- **Python Version**: The project uses Python **3.13**

The project relies on several Python libraries that can be installed with the following command:
```bash
pip install pandas matplotlib seaborn plotly wordcloud networkx Pillow scipy
```
### Step-by-Step guide
**Data Cleaning Notebook**
  1. Be sure the variable `PRINT_CSV` in the first code cell is set to `True` 
  2. Put the raw datasets in the `datasets` directory under the project folder `data-analysis`
  3. Create the `clean_datasets` directory under the project folder `data-analysis`
  4. Run all

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

# Web Service
## How to run
### Requirements
- **Java Version**: The project uses Java **23**
- **PostreSQL**: The project uses PostreSQL **17**
- **MongoDB**: The project uses MongoDB **8**

### Step-by-Step guide
**postgre-server**
1. Install the needed dependencies with gradle
2. In the application.properties file under src/main/resources put the database credential into the configuration field. The url is set to default to localhost and the username default to postgres.
3. Run the program to build the database with JPA
4. We created a python script to import the dataset in one command into the PostgreSQL database. The script relies on several Python libraries that can be installed with the following command:
```bash
pip install psycopg2 argparse  
```
5. Run the following command:
```bash
py .\load_csv_into_db.py --host localhost --database <<DB_NAME>> --user postgres --password <<DB_PASSWORD>>
```
**mongo-server**
1. Install the needed node modules with npm
2. Run the server
3. Import the dataset with Compass or the following command using Mongo Database Tools:
```bash
mongoimport --uri="mongodb://localhost:27017" --db=<<DB_NAME>> --collection=reviews --type=json --file=<<path/to/reviews.json>>  
```
**main-server**
1. Install the needed node modules with npm
2. Run the server

You can access the web service on the following page [localhost:3000](http://localhost:3000/)