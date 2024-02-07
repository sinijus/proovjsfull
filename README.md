Tegemist on Jaanus Siniväli java fullstack praktika täiendatud ja parandatud proovitööga.
Veebiteenus kasutab Java SringBoot backendi, Vue.js frontendi ja PostgreSQL andmebaasi.
Lühidalt saab teenuse abil näha kontaktide kirjeid, kus talletatakse kontakti pärisnimi, salajane kood-nimi ja telefoninumber.
Saab nime järgi sorteerida, saab kontakte otsida ning saab lisada uusi kontakti kirjeid. Lahendust saab kasutada läbi veebibrauseri.  

Projekti käivitamine Windows-is kasutades Docker-it ja Windows terminali.
    1) Ava Docker Desktop või käivita Docker engine.
    2) Vajuta Windowskey + r, trüki wt ning vajuta OK.
    3) Kui Windows terminaliga ei avanenud Windows Power Shell siis vajuta Ctrl+Shift+1 ning liigu kausta, kuhu soovid gitHub projekti kloonida.
       - Käsklusi dir - näitab faile ja kaustu praeguses kaustas
       - Käsklus "cd .." liigub ülemkausta
       - Käsklus "cd <kausta-nimi> liigub alamkausta
    4) Sisesta käsklus git clone https://github.com/sinijus/proovjsfull.git ja liigu sinna kausta käsklusega "cd proovjsfull"
    5) Sisesta käsk "docker compose up" misjärel peaks docker projekti konteinerid ehitama ja jooksutama.
    6) Ava otse brauseris localhost:8081 või "Ctrl + vasak-topeltklõps" konsoolis kuvatud lingil frontend rakendusele ning seejärel saab brauseirs rakendust proovida.
    7) Kui oled lõpetanud sulge Docker-i konteinerid Power Shell-is vajutades "Ctrl + c"


Kui eelnev ei õnnestu siis on teenuse kasutamiseks vaja front ja back projektid avada ja jooksutada IDE-s ning samuti seadistada postgreSQL ja jooksutada SQL skriptid.
    1) Ava "banckend" projektikaust IDE-ga
    2) Lugeda backend README faili
    3) Konfigureerida PostgrSQL seaded ning jooksutada Database kaustas olevad SQL skriptid
    4) Run SmitpjsApplication ning selle eduka avanemise järel avada vajadusel http://localhost:8080/swagger-ui/index.html

    5) Ava "frontend" projektikaust IDE-ga
    6) Jookustada IDE abil README failist või terminalis frontend projektikaustas olles npm install, ning seejärel npm run dev
    7) Avada frontend lahendus brauseri abil aadressil http://localhost:8081/