- Data Cleaning Notebook link: http://127.0.0.1:8888/notebooks/data_cleaning.ipynb

Python Version: 3.13.1

Procedura Pulizia
import
1. Data Understanding
 head
 shape
 dtypes
2. Data Cleaning
 Rename columns
 Check for null values
 Check for duplicate rows
 typing columns
 reset index if rows are dropped
 Deep Clean (optional)
3. Final Result
 head
 shape
 print to csv & set df to null

TODO:
- Actors, Crew: ci sono alcuni nomi che hanno escape codes che forse andrebbero ripuliti
  (controllare quindi tutti i caratteri illegali). Es: \tCirilo Fernández
- Crew: io aggiungerei la stampa dei valori tipo Unknown più o meno come è stato fatto per Self nel ruolo dell'attore
- Ricontrollare che ci sia il reset index ovunque sono state cancellate delle righe


INFO:
[Analisi] Ci possono essere più vincitori per la stessa categoria nella stessa annata di oscar (guarda 1968 best actress)
[Pulizia] Possono esserci nomine anche senza un film specificato (guarda 1929 prima cerimonia)
[Pulizia] Possono esserci nomine duplicate per quanto riguarda la categoria "MUSIC (Original Song)" poiché non è specificato il titolo
[Pulizia] Per il JEAN HERSHOLT HUMANITARIAN AWARD nella cerimonia del 2021 ci sono stati due vincitori
[Pulizia] Reset indexing quando vengono rimmosse delle righe
[Pulizia] Ripetere deep clean fatta in movies description in altri campi
[Pulizia] Pulizia dei caratteri NBSP se causano problemi: df = df.apply(lambda x: x.str.replace('\u00A0', ' ', regex=False) if x.dtype == "object" else x)