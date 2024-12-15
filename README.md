# Veyro - Backend (Java Spring Boot)

Il backend di **Veyro** è sviluppato utilizzando **Java Spring Boot**. Questo backend gestisce la logica di business, l'autenticazione, e l'interazione con il database per supportare il front-end basato su React.

## Tecnologie Utilizzate

- **Spring Boot**: Framework per creare applicazioni Java robuste e scalabili.
- **Spring Security**: Gestione dell'autenticazione e dell'autorizzazione.
- **JPA/Hibernate**: Gestione della persistenza dei dati con il database.
- **H2 Database**: Database in-memory per lo sviluppo, configurabile per produzione.
- **Maven**: Gestore delle dipendenze e build.
- **JWT (JSON Web Token)**: Autenticazione basata su token per la gestione sicura delle sessioni utente.

## Funzionalità

Il backend di **Veyro** gestisce le seguenti funzionalità principali:

1. **Autenticazione e Autorizzazione**:
   - Registrazione e login utente tramite JWT.
   - Protezione delle rotte con **Spring Security**.
   - Gestione dei ruoli (utente, amministratore).

2. **Gestione Materie, Capitoli e Appunti**:
   - Crea, leggi, aggiorna ed elimina materie e capitoli.
   - Aggiungi, modifica e rimuovi appunti per ogni capitolo.
   - Collegamento tra flashcards e capitoli/materie.

3. **Gestione Flashcards**:
   - Creazione di flashcards associate a materie e capitoli.
   - Gestione della difficoltà delle domande.
   - Visualizzazione e test delle flashcards in base alla difficoltà selezionata.

## Struttura del Progetto
