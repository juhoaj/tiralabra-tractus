# Mitä sovelluksessa on testattu ja miten testit ovat toistettavissa

Sovelluksessa on luotu walking skeleton jonka jälkeen on testattu erilaisia luolaston luomistapoja. Tästä enemmän kappaleessa 'Ohjelman toiminnan empiirisen testauksen tulokset'.

Testiautomaatiossa on käytetty JUnitia ja Mockitoa jotta jokaisen luokan toiminta voidaan mahdolisimman pitkälle testata käyttämättä muista luokista luotuja olioita. Tästä on muutamassa kohdassa tosin joustettu sillä harjoitustyötä ei ole nähty ensisijaisesti sovellusrakenteen harjoitustyönä.

Reitinhakualgoritmia on testattu ensin tulostamalla käyttöliittymään tietoa reitinhaun etenemisestä reitinhakualgoritmin debuggauksen yhteydessä. Myöhemmässä vaiheessa reintinhaun heuristiikan ja keon kehittämisen tueksi on tulostettu reitinhaun käyttäytymistä konsoliin. Tämän tuloksista enemmän kappaleessa 'Reitinhaun algoritmin toiminnan testaus'.

Algoritmien toimintaa on testattu kahdella tavasalla. Vakioidusti, antamalla satunnaislukugeneraattoreille vakioitu seed sekä sattumanvaraisesti useammalla otoksella. Näin on haluttu varmistaa että vakioitu tilanne ei vahingossa ole poikkeistapaus.


# Ohjelman toiminnan empiirisen testauksen tulokset

Empiirinen testaus kohdistui ensiksi maailman luomiseen. Lähtökohdaksi otettiin soluautomaatio [roguelike tutorial, Trystan](http://trystans.blogspot.com) pohjasta jotta walking skeleton saatiin nopeammin valmiiksi ja päästin testaamaan hirviöiden reitinhakua ja kaivautumista. 

Luolaston luomiseen kokeiltiin myös random walk algoritmia joka lisäsi huoneita mutta tämä huomattiin tylsäksi pelata. Myös soluautomaation, huoneiden luomisen ja random walk -algoritmien yhdistelmiä kokeiltiin mutta parhaimman lopputuloksen antoi soluautomaatio joten päädyttiin jatkokehittämään aiemmin valittua pohjaa. Nämä on poistettu koodista mutta niihin pääsee tutustumaan esim. [tästä vanhasta commitista](https://github.com/juhoaj/tiralabra-tractus/tree/f096ce4f0dfb0fa937996bdffa01ea6a542780a4).


# Reitinhaun algoritmin toiminnan testaus

A* algoritmi tehtiin ensiksi siten että sen heuristiikka oli alku- ja loppupisteen välinen välimatka. Toimintanopeuden testaamisen alettua havaittiin se hyvin tehottomaksi. Alla testitulokset (pinona PriorityQue).

Kun pelialueen koko on 51x51 ja kartta on vakioitu saatiin seuraava (toistettavat) mittaustulokset:

| Heuristiikka       | Nodeja käyty läpi    |  Reitin pituus    |
|--------------------|---------------------:|------------------:|
| välimatka * 1      | 614                  | 57                |
| välimatka * 1.25   | 528                  | 57                |
| välimatka * 1.5    | 347                  | 57                |
| välimatka * 1.75   | 236                  | 57                |
| välimatka * 2      | 198                  | 57                |
| välimatka * 3      | 82                   | 57                |
| välimatka * 4      | 70                   | 57                |
| välimatka * 5      | 60                   | 57                |
| välimatka * 6      | 60                   | 57                |
| välimatka ^2       | 62                   | 57                |          
| välimatka ^3       | 61                   | 57                |
| välimatka ^4       | 61                   | 57                |


Kun pelialueen koko on 500x500 ja kartta on vakioitu saatiin seuraava (toistettavat) mittaustulokset:

| Heuristiikka       | Nodeja käsitelty     |  Reitin pituus    |
|--------------------|---------------------:|------------------:|
| välimatka *1       | 20821                | 259               |
| välimatka *2       | 368                  | 241               |
| välimatka *3       | 251                  | 241               |
| välimatka *4       | 248                  | 241               |
| välimatka ^2       | 243                  | 241               | 
| välimatka ^3       | 243                  | 241               |   
| välimatka ^4       | 243                  | 241               |


Kun pelialueen koko on 1000x1000 ja kartta on vakioitu saatiin seuraava (toistettavat) mittaustulokset:

| Heuristiikka       | Nodeja käsitelty     |  Reitin pituus    |
|--------------------|---------------------:|------------------:|
| välimatka *1       | 151666               | 687               |
| välimatka *2       | 1579                 | 671               |
| välimatka *3       | 796                  | 669               |
| välimatka *4       | 774                  | 669               |
| välimatka ^2       | 756                  | 669               |
| välimatka ^3       | 756                  | 669               |
| välimatka ^4       | ∞                    | ?                 |


Kun pelialueen koko on 2001x2001 ja kartta on vakioitu saatiin seuraava (toistettavat) mittaustulokset:

| Heuristiikka       | Nodeja käsitelty     |  Reitin pituus    |
|--------------------|---------------------:|------------------:|
| välimatka *1       | 437619               | 1130              |
| välimatka *2       | 4009                 | 1128              |
| välimatka *3       | 1540                 | 1122              |
| välimatka *4       | 1308                 | 1122              |
| välimatka ^2       | 1232                 | 1122              |
| välimatka ^3       | 1232                 | 1122              |
| välimatka ^4       | ∞                    | ?                 |


Kun pelialueen koko on 4001x4001 ja kartta on vakioitu saatiin seuraava (toistettavat) mittaustulokset:

| Heuristiikka       | Nodeja käsitelty     |  Reitin pituus    |
|--------------------|---------------------:|------------------:|
| välimatka *1       | 1619360              | 2086              |
| välimatka *2       | 6183                 | 2086              |
| välimatka *3       | 2706                 | 2082              |
| välimatka *4       | 2508                 | 2082              |
| välimatka ^2       | 2325                 | 2082              |
| välimatka ^3       | ∞                    | ?                 |
| välimatka ^4       | ∞                    | ?                 |

Näin ollen A* algoritmin heuristiikaksi valittiin välimatka ^2.



Alkutilanne:
![Heuristiikka: välimatka * 1](mittaukset/reitinhaku/51x51-alku.png)

Reitinhaku pelaajan liikuttua vasemmalle, heuristiikka: välimatka * 1
![Heuristiikka: välimatka * 1](mittaukset/reitinhaku/51x51-1.png)

Reitinhaku pelaajan liikuttua vasemmalle, heuristiikka: välimatka ^ 2
![Heuristiikka: välimatka ^ 2](mittaukset/reitinhaku/51x51-potenssi.png)


# Reitinhaun käyttäytymisen testaus keon valinnan tueksi

Kun A* heuristiikkana on matka^2 ja kartta vakioitu tehdään kekoon kymmentä add -kutsua kohden ~neljä poll ja isEmpty -kutsua:

| Maailman koko       | Reitin pituus     | Nodeja lisätty pinoon    | Pinon maksimikoko    |  pituus / lisätty    |
|---------------------|------------------:|-------------------------:|---------------------:|---------------------:|
| 51x51               | 57                | 150                      | 89                   | 0,38	               |
| 500x500             | 241               | 660                      | 418                  | 0,37	               |
| 1000x1000           | 669               | 1848                     | 1093                 | 0,36	               |
| 2001x2001           | 1122              | 3149                     | 1918                 | 0,36	               |
| 4001x4001           | 2082              | 5814                     | 3490                 | 0,36	               |

Jos maailman koko on 500x500 ja karttaa ei ole vakioitu havaitaan myös sama suhde.

| Reitin pituus     | Nodeja lisätty pinoon    | Pinon maksimikoko    |  pituus / lisätty    | lisätty / maksimi   |
|------------------:|-------------------------:|---------------------:|---------------------:|--------------------:|
| 233	            | 636	                   | 378	              | 0,37                 | 0,59                |
| 209	            | 548	                   | 319	              | 318	                 | 0,38                |
| 386	            | 1055	                   | 619	              | 618	                 | 0,37                |
| 293	            | 810	                   | 473	              | 472                  | 0,36                |

Näin ollen valitun tietorakenteessa on tärkeätä isEmpty kutsun tehokkuuden lisäksi ensisijaisesti insert ja tämän jälkeen poll -kyselyn tehokkuus.

Lähdeaineistosta määrittelyvaiheessa luettu Fibonacci-kekojen sopivuudesta tälläiseen tilanteeseen on validi sillä lähteiden mukaan Fibonacci-keon insert -metodin käytännön aikavaativuus on huomattavasti sen teoreettista O(log n) aikavaativuutta nopeampi. 

Fibonacci-keko on kuitenkin verrattain vaikea toteuttaa. Myöhemmin kerätystä lähdeaineistosta tutuksi tullut pairing heap mainitaan käytännössä yhtä tehokkaaksi ja helpommaksi toteuttaa. Molemmat keot suoriutuvat A* algoritmin vaatimuksista tehokkaasti. 


# Keon testaus

Kekoa toteutettiin erityisesti delete-min metodin kohdalla naivimmin kuin löydetyissä java-implementoinneissa, joita ei haluttu orjallisesti kopioida. Reitinhaun käyttäytymisen tastauksen pojalta on pääteltävissä että puu on leveyssuunnassa varsin kapea ja pystysuunnassa korkea. Näin ollen delete-min -metodilla oletetaan olevan verrattain vähän työtä. 

Ensiksi kokeiltiin reitinhakua ja varmistettiin että reittien pituudet vakioidulla kartanluomisella pysyvät samana.

| Maailman koko      | PriorityQue     |  PairingHeap    |
|--------------------|----------------:|----------------:|
| 51x51              | 57              | 57              |
| 500x500            | 241             | 241             |
| 1000x1000          | 669             | 669             |
| 2001x2001          | 1122            | 1122            |
| 4001x4001          | 2082            | 2082            |

Voidaan todeta että PairingHeap ei vaikuta reitinhakualgoritmin toimintaan negatiivisesti.

Toisaalta verrattaessa naivisti toteutettua PairingHeap ja PriorityQue nopeautta huomattiin että nopeusero oli vain marginaalinen. Näinollen päädyttiin refaktoroimaan PairingHeap epänaivimmaksi ja saatiin seuraavat huomattavasti paremmat mittaustulokset kun kekoon lisätään n toinen toistaan pienempää Nodea ja poistetaan n/2 Nodea joka vastaa aikaisemman testauksen mukaan keon käyttöä reitinhakualgoritmilla.

![Hirviöiden vuoron kesto viiden siirron keskiarvosta, ms. 500 hirviötä, 500x500 maailma](mittaukset/graafi5.png)

| Nodea        | Priority Queue      | refaktoroitu Pairing Heap      | 
|--------------|---------------------|-------------------------------:|
| 1000         | 2                   | 0                              |
| 10000        | 7                   | 3                              |
| 100000       | 31                  | 17                             |
| 1000000      | 122                 | 49                             |


# CustomArrayListin vertaus

Mitattaessa CustomArrayListin ja ArrayListin toimintaa huomataan että ArrayList on marginaalisesti nopeampi.

| Lisätty ja poistettu Integer:tä     | ArrayList     |  CustomArrayList |
|-------------------------------------|--------------:|-----------------:|
| 1000                                | 1             | 0                |
| 10000                               | 3             | 3                |
| 100000                              | 9             | 11               |
| 1000000                             | 52            | 56               |




