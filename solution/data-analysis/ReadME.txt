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
- Releases: C'è bisogno di un analisi più approfondita per rating in molti casi.
  Ad esempio molte righe riportano il valore 0, è un null o un valore valido?
  In un film rilasciato in Francia c'è il valore rating 10, ma cercando su internet non c'è nessun rating 10 in Francia. è un valore valido?
- Releases: si potrebbe scrivere una riga sul perché abbiamo lasciato le righe con rating null
- Studios: bisogna separare la stampa dei nulli e dei duplicati dalla rimozione altrimenti non si capisce. Si dovrebbe
  mettere anche una riga che spiega perché si fa drop o no.
- Valutare una funzione da utilizzare per ogni dataset per stampare i duplicati in maniera più leggibile (da inserire in utils)
- Cercare di specificare la funzionalità di ogni casella di codice attraverso il markdown piuttosto che usare i commenti
- Utilizzare stampe più leggibili per visualizzare i dati
- Forse è il caso di suddivere meglio la fase di data cleaning




INFO:
[Analisi] Ci possono essere più vincitori per la stessa categoria nella stessa annata di oscar (guarda 1968 best actress)
[Pulizia] Possono esserci nomine anche senza un film specificato (guarda 1929 prima cerimonia)
[Pulizia] Possono esserci nomine duplicate per quanto riguarda la categoria "MUSIC (Original Song)" poiché non è specificato il titolo
[Pulizia] Per il JEAN HERSHOLT HUMANITARIAN AWARD nella cerimonia del 2021 ci sono stati due vincitori
[Pulizia] Reset indexing quando vengono rimmosse delle righe
[Pulizia] Ripetere deep clean fatta in movies description in altri campi
[Pulizia] Pulizia dei caratteri NBSP se causano problemi: df = df.apply(lambda x: x.str.replace('\u00A0', ' ', regex=False) if x.dtype == "object" else x)

