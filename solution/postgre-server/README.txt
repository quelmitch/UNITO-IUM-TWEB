Java version: 23
PostgreSQL Server version: 17.2


Dependencies:
- Java JPA
- PostgreSQL Driver
- Spring Web
- Lombok

API Enterprise:

Deciso di passare i parametri nella maggior parte dei casi come parametro per sfruttare la flessibilità
di Hibernate di ignorare i parametri non necessari e quindi fare meno elaborazione sia sul server principale
sia su questo.
Esempio: movie/title?title=Inception anziché movie/title/:{title_id}


TODO:
- Aggiungere metodi to lower per pulire gli input e il database deve avere anche tutti le stringhe in lower case