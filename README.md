# RELAZIONE PROGETTO POMPA INSULINICA
FONDAMENTI DI INGEGNERIA DEL SOFTWARE 2022/2023

Marco Massagrande \
Alessio Tonelli \
Alessia Canelli

## INTRODUZIONE
L'obbiettivo dell'elaborato è quello di creare
un sistema che simula l'utilizzo di una pompa insulinica
sottoforma di applicazione Web.
Bisogna considerare che la pompa insulinica di
base è un sistema embedded, per cui sono stati fatti dei
trade-off sulle funzionalità che la pompa dovesse avere
ed è stato scelto di ricreare la misurazione della
glicemia e la eventuale memorizzazione su di una cronologia.

## REQUISITI
Si vuole progettare un sistema informatico per gestire
la cronologia delle misurazioni di glicemia e insulina
che vengono effettuate da una pompa insulinica.
Per ogni utente si registra: un nome, un cognome,
un'email, un username, una password.
Il sistema memorizza misurazioni della pompa insulinica:
l'id dell'utente che effettua la misurazionele,
il valore di glicemia, il valore di insulina iniettata,
un commento che l'utente può rilasciare al momento
dell'inserimento della misurzione e infine l'orario
e la data dell'avvenuta misurazione.
Il sistema mantiene una sola lista con tutte le misurazione
degli utenti e tramite l'id dell'utente si
collega una misurazione all'utente.

L'utente se non è registrato nel sistema può crearsi un
nuovo account.
L'utente può accedere al sistema inserendo il proprio
username e la password, se le credenziali risultano
essere corrette l'utente può accedere alla schermata
home dell'applicazione.
Una volta che l'utente si trova nella schermata home
può decidere se visualizzare le proprie credenziali
e se vuole modificarle, può decidere se inserire una
nuova misurazione. Quando l'utente avrà inserito almeno
una misurazione potrà nella schermata home visualizzare
la cronologia delle sue misurazioni.

## SCENARI

### 1
~~inserire~~

### 2
~~inserire~~

### 3
~~inserire~~

### 4
~~inserire~~

### 5
~~inserire~~

### 6
~~inserire~~

### 7
~~inserire~~

### 8
~~inserire~~

### 9
~~inserire~~

## ASSUNZIONI
Per poter svolgere l’elaborato abbiamo dovuto effettuare
delle scelte progettuali necessarie per chiarire i
diversi flussi di esecuzione.

Le nostre assunzioni sono state:
* Si suppone che non esistano utenti con lo stesso username,
  di fatto l'username funge da identificativo univoco dell'utente.
* Un utente non può accedere al sistema senza avere un
  account.
* E' possibile visualizzare la cronologia delle misurazioni
  solo se si ha effettuato almeno una misurazione.

## DOCUMENTAZIONE
Di seguito vengono presentati tutti i documenti creati
in fase di progettazione e sviluppo.

### USE CASE

#### USE CASE UTENTE
~~inserire~~

### ACTIVITY DIAGRAMS
I seguenti Activity Diagrams illustrano il
flusso di esecuzione dei più importanti metodi presenti nel
prototipo.

#### ACTIVITY DIAGRAM CREAZIONE ACCOUNT
~~inserire~~

#### ACTIVITY DIAGRAM LOGIN
~~inserire~~

#### ACTIVITY DIAGRAM VISUALIZZA PROFILO
~~inserire~~

#### ACTIVITY DIAGRAM MODIFICA PROFILO
~~inserire~~

#### ACTIVITY DIAGRAM INSERIMENTO MISURAZIONE
~~inserire~~

#### ACTIVITY DIAGRAM VISUALIZZAZIONE MISURAZIONE
~~inserire~~

### SEQUENCE DIAGRAMS
I seguenti Sequence Diagrams illustrano gli scambi
di messaggi negli use case principali.
Abbiamo ritenuto più interessante rispetto agli
altri focalizzare l’attenzione sui compiti del magazziniere.
Gli altri use case sono molto simili a questi come dinamiche.

#### SEQUENCE DIAGRAM CREAZIONE ACCOUNT
~~inserire~~

#### SEQUENCE DIAGRAM LOGIN
~~inserire~~

#### SEQUENCE DIAGRAM VISUALIZZA PROFILO
~~inserire~~

#### SEQUENCE DIAGRAM MODIFICA PROFILO
~~inserire~~

#### SEQUENCE DIAGRAM INSERIMENTO MISURAZIONE
~~inserire~~

#### SEQUENCE DIAGRAM VISUALIZZAZIONE MISURAZIONE
~~inserire~~

### CLASS DIAGRAM
Il seguente Class Diagram rappresenta le
classi e le relazioni fra esse che utilizzeremo
nel nostro progetto Java per rappresentare le informazioni.
~~inserire~~

### METODOLOGIA DI SVILUPPO
Abbiamo utilizzato una metodologia di sviluppo Agile
di tipo incrementale per poter avere una consegna
del progetto e uno sviluppo rapido del software
andando ad interfogliare le fasi di progettazione,
specifica e implementazione.
Abbiamo fatto brevi iterazioni e prodotto diverse versioni
del progetto in modo tale da avere una continua visione
del prodotto finale. Essendo di tipo incrementale
lo sviluppo ci ha consentito di apportare modifiche al codice
senza particolari difficoltà, inoltre seguendo questo tipo
di approccio la documentazione risulta essere minima
e non presenta ogni versione del codice che è stata realizzata.
Ci siamo comunque quotidianamente confrontati sulle
attività svolte e sulle attività in elaborazione oltre
che su vari problemi di sviluppo.
Abbiamo usato Git come sistema di versioning e GitHb come piattaforma
per la condivisione del codice, di fatto il nostro gruppo ha
sempre lavorato contemporaneamente al codice
garantendo una compresione totale da parte dei partecipanti
di ogni modifica che è stata fatta sul codice.

### PATTERN ARCHITETTURALE
Per l’architettura del software abbiamo utilizzato
il pattern MVC (Model View Control) suddividendo così la
parte grafica da quella di gestione delle informazioni
e da quella di controllo.
La motivazione principale per cui è stato utilizzato
questo pattern è la possibilità di realizzare sequenzialmente
le componenti, questa indipendenza tra le componenti
ha garantito la flessibilità nella realizzazione
dell’interfaccia d’’accesso del sistema
dell’utente. In una fase iniziale infatti la grafica
dei dati era molto scarna, sufficiente
per verificare che la componente di gestione dei dati
fosse funzionante, poi in una
fase finale il layout della componente View è stato
raffinato e reso semplice e chiaro.

I package solitamente sono :
*Modello: sottoparte del sistema che si occupa di
gestire i dati del sistema e le
operazioni associate ai dati
*Vista: sottoparte del sistema che definisce e
gestisce come i dati sono
rappresentati all’utente
*Controllore: sottoparte del sistema che si
occupa di gestire le interazioni tra
l’utente e i dati del sistema, notificando tali
interazioni alle componenti Modello e Vista

![Modello MVC](MVC.png)

### VERIFICA E VALIDAZIONE
~~inserire~~

#### CHECKING
Il codice è stato costantemente rivisto, si è cercato
di identificare le componenti di codice incorretto o che
può essere migliorato. Inoltre sono stati fatti molteplici
controlli sull'indentazione e sulla nomenclatura del codice
andando eventualmente a eseguire refactoring.

#### PROGRAM TESTING
~~inserire~~

##### QUALITY ASSURANCE
~~inserire~~

#### TESTING DI ACCETTAZIONE
Il progetto è stato testato da individui che non
appartengono al mondo dell’informatica, in
modo da avere l’impressione della persona più vicina
all’effettivo utilizzatore di
questo prodotto una volta distribuito.
L’utente è stato libera di utilizzare il software, in
modo da verificare che il sistema sia chiaro e intuitivo,
grazie a questo passaggio
siamo stati in grado di migliorare alcuni aspetti relativi
alla fruizione dei dati e anche
ad aggiungere delle funzionalità che anche se sottili sono
sufficienti a rendere
l’utente a proprio agio con il prodotto.
