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
un'email, un username e una password.
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

- Descrizione di cosa gli utenti si aspettano quando inizia uno scenario
- Descrizione normale conseguirsi degli eventi
- Cosa può andare storto e come possono essere gestiti i vari problemi
- Attività che si svolgono contemporaneamente
- Descrizione fine scenario

### 1 L'UTENTE CREA UN NUOVO ACCOUNT 
- Un nuovo utente che soffre di diabete vuole accedere alla pagina per tenere monitorato le sue iniezioni di insulina,
essendo che è il primo suo accesso dovrà crearsi un nuovo account.
- L'utente per crearsi il suo account personale dovrà schiacciare sul bottone "crea nuovo account", una volta fatto ciò
verrà indirizzato su una pagina in cui gli verrà rischiesto di inserire il suo nome, cognome, email, username e password.
Ognuna di queste credenziali avrà delle specifiche condizioni che devono essere rispettate per far si che l'account venga creato
correttamente.
- Se, durante la creazione dell'account, non vengono soddisfatti i requisiti necessari delle varie credenziali
non sarà possibile creare l'account e verranno visualizzati dei messaggi di errore.
- Una volta che la creazione dell'account è avvenuta correttamente, l'utente verrà indirizzato alla pagina di login.

### 2 L'UTENTE EFFETTUA IL LOGIN AL SUO ACCOUNT
- L'utente dopo aver creato l'account si aspetta che utilizzando le credenziali scelte in precedenza riesca ad accedere al suo account personale.
- Una volta che l'account è stato creato verrà visualizzata la pagina login. In questa pagina è presente un form, in cui
  viene richiesto username e password. Sono inoltre presenti due pulsanti, uno per creare un nuovo account e uno per accedere
  alla pagina utente. A questo punto una volta che l'utente ha inserito le proprie credenziali in modo corretto e negli appositi 
spazi schiacciando sul pulsante "Accedi" riuscirà ad accedere alla home.
- Nel caso in cui l'utente si sia dimenticato le sue crdenziali, o seplicemente le inserisce in modo sbagliato, verrà visualizzato 
un messaggio di errore a seconda che sia stato sbagliato il nome utente o la password. L'utente a questo punto rimarrà sulla
pagina login e potrà reinserire nuovamente le proprie credenziali. 
- Una volta inserite le credenziali corrette l'utente verrà indirizzato alla pagina home all'interno del proprio account. 
A questo punto potrà eseguire tutte le azioni necessarie per monitorare al meglio il suo diabete.


### NO
Pagina utente
- Dopo aver fatto il login si giunge alla pagina utente. In questa pagina sono presenti tre pulsanti.
Il primo servirà a visualizzare il proprio profilo, il secondo per visualizzare la cronologia delle misurazioni e
l’ultimo è quello relativo alla pompa insulinica, che permette di fare le iniezioni di insulina. Alla fine della
pagina sarà presente anche un tasto logout che permetterà all’utente di uscire dal proprio profilo e tornare alla
pagina login.

### 3 PROFILO
- L'utente vuole visualizzare le sue credenziali create la prima volta in cui ha avuto accesso alla pagina, in questa
pagina sarà possibile anche modificare i propri dati.
- L'utente vuole modificare le proprie credenziali, per fare questo, una volta all'interno della sua pagina home, dovrà 
schiacciare il bottone "Profilo" e verrà indirizzato in una pagina contente tutte le credenziali in modalità solo lettura.
Schiacciando sul tasto "Modifica", l'utente avrà la possibilità di modificare tutti i campi presenti nella pagina, sempre
rispettanto gli stessi requisiti della creazione di un nuovo utente. Per salvare le modifiche l'utente dovrà schiacciare
sul bottone "Salva".esso verrà indirizzato sulla pagina del suo profilo in cui si potranno vedere le modifiche appena fatte.
- Se i dati che vengono modificati non rispettano i vincoli stabiliti non sarà possibile effettuare la modifica e verrano
visualizzati dei messaggi di errore.
- Dopo aver premuto il pulsante "Salva" l'utente verrà indirizzato sulla pagina del suo profilo in cui si potranno vedere
le modifiche appena fatte.

### 4 L'UTENTE SI DEVE FARE UN INIEZIONE DI INSULINA
- Nel momento in cui l'utente ha il bisogno di fare un iniezione di inulina si aspetta di poter inserire nel sistema: 
  - la sua attuale glicemia 
  - l'insulina fatta 
  - un eventuale commento 

  e che successivamente questi dati vengano salvati in modo da poter essere consultati in seguito
assieme a un medico in caso di necessità.
- Quando dalla pagina utente si seleziona il tasto “Pompa insulinica” si apre una pagina in cui l'utente può inserire
  il suo valore attuale della glicemia e l'insulina da fare. Inoltre sarà possibile inserire un commento prima di premere il tasto "Salva",
il quale salverà i dati appena inseriti nella cronologia.
- Nel momento in cui l'utente va a inserire la sua glicemia attuale e la quantità di insulina, queste devono rientrare in un determinato range di valori.
Nel caso in cui non fosse così verrà visualizzato un errore e l'iniezione non verrà salvata in cronologia fino a che il valore della 
glicemia non viene modificato.
- Una volta che l'iniezione è stata inserita i valori appena inseriti saranno presenti all'interno della cronologia.

### 5 L'UTENTE DOPO AVER INSERITO IN CRONOLOGIA UN'INIEZIONE SBAGLIATA VUOLE CANCELLARLA
- Nel caso in cui l'utente in precedenza abbia sbagliato ad inserire un'iniezione, ha la necessita di poter eliminare quest'ultima in modo
da non salvare informazioni sbagliate.
- Quando dalla pagina Home si seleziona il tasto “Cronologia” appare la lista di tutte le iniezioni che sono state
  fatte con relativo giorno, orario, glicemia, commento e quantità di insulina che è stata fatta. Selezionando il tasto "Cancella"
  l'utente ha la possibilità di cancellare l’ultima riga della cronologia.
- Nel caso in cui l'utente erroneamente cancelli l'ultima riga,per ripristiarla dovrà reinserirla manualmente accedendo alla sezione "Pompa Insulinica"
- Contemporaneamente a questo scenario non ci sarà nessun altro scenario
- Alla fine di questo scenario ci si aspetta di visualizzare tutta la cronologia meno che l'ultima riga inserita che sarà stata eliminata.

### 5
Cronologia: elimina tutto
- Quando dalla pagina utente si seleziona il tasto “cronologia” appare la lista di tutte le iniezioni che sono state
  fatte con relativo giorno, orario e quantità di insulina che è stata fatta. Inoltre l’utente, nel caso avesse necessità, deve avere
  la possibilità di cancellare tutta la cronologia.
- Una volta visualizzata la cronologia tramite il pulsante "Cancella tutto" l'utente avrà la possibilità di cancellare tutta la cronologia presente.
- Nel caso in cui l'utente elimina erroneamente tutta la cronologia non sarà possinbile ripristinarla ma sarà necessario ricominciare da capo a inserire i valori.
- Contemporaneamente a questo scenario non ci sarà nessun altro scenario
- Alla fine di questo scenario ci si aspetta di visualizzare tutta la pagina della cronologia vuota senza nessuna riga.

### 7
Logout
- L'utente una volta nella pagina home si aspetta di fare il logout e di uscire quindi dal proprio account, vnendo reindirizzato alla pagina di login.
- Nel momento in cui l'utente, che si trova nella home, schiacia il tasto "Logout", verrà disconnesso dal proprio account, tornando alla pagina di login.
- Nel caso in cui l'utente effettua per sbaglio il logout potrà effettuare nuovaente l'accesso con le sue credenziali attraverso la pagina di login.
- Contemporaneamente a questo scenario non ci sarà nessun altro scenario
- Alla fine di questo scenario l'utente si aspetta di trovarsi nuovamente alla pagina di login in cui potrà accedere nuovamente al proprio account nel caso lo volesse.


### 8
Indietro

- L'utente quando si trova nelle pagine "Profilo", "Pompa insulinica" o "Cronologia", si aspetta che schiacciando il tasto "Indietro" venga reindirizzato alla schermata home.
- Nel momento in cui l'utente schiaccia il pulsante "Indietro" verrà reindirizzato alla pagina precedente a quella in sui si trova. Nel nostro caso sarà la pagina Home.
- Nel caso in cui l'utente selezioni per sbaglio il tasto "Indietro" potrà tornare alla pagina in cui si trovava selezionandola nuovamnte dalla home.
- Contemporaneamente a questo scenario non ci sarà nessun altro scenario
- Alla finedi questo scenario, dopo cheil tasto "Indietro" è stato selezionato mi aspetto di trovarmi nella Home.

### 9
Cancella utente
- In questo scenario ci si aspetta che sia possibile eliminare definitivamente un account e tutti i dati in esso contenuti.
- Quando l'utente si trova nella schermata Home potrà selezionare il tasto "Elimina account" tramite il quale potrà eliminare l'account etutti i dati presenti in esso definitivamente.
- Nel caso in cui l'utente eliminasse l'account e poi volesse ripristinarlo non sarebbe possibile. Per poter riavere l'account l'utente dovrà crearne uno nuovo.
- Contemporaneamente a questo scenario non ci sarà nessun altro scenario
- Alla fine di questo scenario l'utente si aspetta  di essere reindirizzato alla pagina di login e che l'account non esista più e quindi non sia più possibile accedervi.


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

##### Test crea utente
1. Viene creato un utente con le credenziali corrette (i valori rispettano i requisiti stabiliti). Verifico che l'account sia stato effetivamente creato.
2. Viene creato un utente dove è presente una credenziale errata ( una delle credenziali non rispetta i requisiti stabiliti). Verifico che l'account non sia stato creato.

##### Test Login
1. Eseguo il login con le credenziali corrette. Verifico che il login è avvenuto con successo e sono nella pagina utente.
2. Eseguo il login con le credenziali errate. Verifico di non aver fatto il login.

##### Test Profilo
1. Vado a modificare dei valori presenti nella pagina profilo. Verifico che la modifica sia avvenuta con successo.
2. Se l'username è già in uso oppure se non vengono rispettati i requisini verifico che la modifica non vada a buon fine.
3. Cancello l'utente e verifico di essere tornato alla pagina di login, provo ad entrare con le stesse credenziali di prima e verifico che non entri

##### Test Pompa Insulinica
1. Inserisco misurazione e verifico che sia stata salvata.
2. inserisco una misurazione che non rispetta i requisiti e verifico che la modifica non sia avvenuta.

##### Test Cronologia
1. Se elimino una riga questa viene eliminata.
2. Se elimino tutta la cronologia verifico che venga effettivamente elimiata e torno nella home.

#### Test Logout
1. Premendo logout l'utente viene reinderizzato alla pagina di login

#### Test indietro
1. Premendo il tasto indietro l'utente deve andare alla pagina precedente

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
