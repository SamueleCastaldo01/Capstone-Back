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

## Link al Frontend

Il **frontend** dell'applicazione è sviluppato con **React.js** ed è disponibile su GitHub. Puoi consultare il repository del frontend al seguente link:

[Frontend di Veyro - React.js](https://github.com/SamueleCastaldo01/Capstone-Front)

## Documentazione Completa

Per una documentazione più dettagliata, consulta la documentazione completa del progetto. Essa include informazioni su come configurare, eseguire e testare l'applicazione completa (backend + frontend):

[Documentazione Completa](https://github.com/SamueleCastaldo01/Capstone-Front)

## Come Iniziare

### 1. Clonare il Repository

```bash
git clone https://github.com/tuo-username/veyro-backend.git
cd veyro-backend
