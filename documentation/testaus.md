## Mitä sovelluksessa on testattu

Erilaisia generointitapoja empiirisesti.

Yksikkötestaus mockeilla.

Algoritmien testaus kehitysvaiheessa tulostamalla käyttöliittymään.

Algoritmien tehokkuuden kehitys tulostamalla käyttöliittymään ja konsoliin.



## Miten testit voidaan toistaa

Suorituskykytestit kolmen ajon keskiarvosta.

Reitinhakualgoritmin testaus antamalla satunnaisluvulle seed jotta kartta ja hirviöiden sijainnit vakioitu.


## Ohjelman toiminnan empiirisen testauksen tulokset

Empiirinen testaus kohdistui ensiksi maailman luomiseen. Lähtökohdaksi otettiin soluautomaatio [roguelike tutorial, Trystan](http://trystans.blogspot.com) pohjasta jotta walking skeleton saatiin nopeammin valmiiksi ja päästin testaamaan hirviöiden reitinhakua ja kaivautumista. 

Luolaston luomiseen kokeiltiin myös random walk algoritmia joka lisäsi huoneita mutta tämä huomattiin tylsäksi pelata. Myös soluautomaation, huoneiden luomisen ja random walk -algoritmien yhdistelmiä kokeiltiin mutta parhaimman lopputuloksen antoi soluautomaatio joten päädyttiin jatkokehittämään aiemmin valittua pohjaa. 



### Maailman luominen

Maailman luomisen kesto on suoraan suhteessa kartain tiilien määrään 18 mittauksen keskiarvoilla. Näin ollen voidaan päätellä että kartan luomisen algoritmin tehokkuus on O(m*n) jossa m on smooth -soluautomaatiometodin toistokerrat ja n kartan tiilien määrä. Satunnaisen hälyn vaativuus on vain n joten se lienee järkevintä jättää algoritmin tehokkuudessa mainitsematta.

![Kartan luomisen kesto eri kokoisilla maailmoilla](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/mittaukset/graafi4.png)

Maailman luomisen vaatiman ajan hajonta on kuitenkin verrattain suuri.

![Kartan luomisen keston hajonta eri kokoisilla maailmoilla](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/mittaukset/graafi2.png)



### Reitinhaun algoritmin toiminta

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
| välimatka ^3       | 756                  | 669m              |
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





Alkutilanne:
![Heuristiikka: välimatka * 1](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/mittaukset/reitinhaku/51x51-alku.png)

Reitinhaku pelaajan liikuttua vasemmalle, heuristiikka: välimatka * 1
![Heuristiikka: välimatka * 1](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/mittaukset/reitinhaku/51x51-1.png)

Reitinhaku pelaajan liikuttua vasemmalle, heuristiikka: välimatka ^ 2
![Heuristiikka: välimatka ^ 2](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/mittaukset/reitinhaku/51x51-potenssi.png)


### Reitinhaun käyttämän pinon toiminta

Kun A* heuristiikkana on matka^2 ja kartta vakioitu tehdään kymmentä add -kutsua kohden pinoon tehdään neljä poll -kutsua

| Maailman koko       | Reitin pituus     | Nodeja lisätty pinoon    | Pinon maksimikoko    | Pinon koko reitinhaun lopussa  | pituus / lisätty    | lisätty / maksimi   |
|---------------------|------------------:|-------------------------:|---------------------:|--------------------------------|--------------------:|--------------------:|
| 51x51               | 57                | 150                      | 89                   | 88                             | 0,38	               | 0,59                |
| 500x500             | 241               | 660                      | 418                  | 417                            | 0,37	               | 0,63                | 
| 1000x1000           | 669               | 1848                     | 1093                 | 1092                           | 0,36	               | 0,59                |
| 2001x2001           | 1122              | 3149                     | 1918                 | 1917                           | 0,36	               | 0,61                |
| 4001x4001           | 2082              | 5814                     | 3490                 | 3489                           | 0,36	               | 0,60                |

Jos maailman koko on 500x500 ja karttaa ei ole vakioitu havaitaan myös sama suhde.

| Reitin pituus     | Nodeja lisätty pinoon    | Pinon maksimikoko    | Pinon koko reitinhaun lopussa  | pituus / lisätty    | lisätty / maksimi   |
|------------------:|-------------------------:|---------------------:|--------------------------------|--------------------:|--------------------:|
| 233	            | 636	                   | 378	              |377	                           |0,37                 |0,59                 |
| 209	            | 548	                   | 319	              |318	                           |0,38                 |0,58                 |
| 386	            | 1055	                   | 619	              |618	                           |0,37                 |0,59                 |
| 293	            | 810	                   | 473	              |472                             |0,36                 |0,58                 |

Fibonacci-keot sopisivat tälläiseen tilanteeseen hyvin sillä sen insert -metodin aikavaativuus on vain O(1). Se on kuitenkin verrattain vaikea toteuttaa saadaan pairing heap keolla myös käytännössä yhtä hyvä tehokkuus sillä sen insert -metodin aikavaativuus on myyös O(1) ja niiden molempien delete -metodin aikavaativuus on O(log n).


