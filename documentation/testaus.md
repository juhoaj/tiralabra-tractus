## Mitä sovelluksessa on testattu

Erilaisia generointitapoja empiirisesti.

Yksikkötestaus mockeilla.

Algoritmien testaus kehitysvaiheessa tulostamalla käyttöliittymään.

Algoritmien tehokkuuden kehitys tulostamalla käyttöliittymään ja konsoliin.



## Miten testit voidaan toistaa

Suorituskykytestit kolmen ajon keskiarvosta.

Reitinhakualgoritmin testaus antamalla satunnaisluvulle seed jotta kartta ja hirviöiden sijainnit vakioitu.


## Ohjelman toiminnan empiirisen testauksen tulokset



### Maailman luominen

![Kartan luomisen kesto eri kokoisilla maailmoilla](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/mittaukset/graafi4.png)

Maailman luomisen vaatiman ajan hajonta on verrattain suuri.

![Kartan luomisen keston hajonta eri kokoisilla maailmoilla](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/mittaukset/graafi2.png)



### Reitinhaun algoritmin toiminta

A* algoritmi tehtiin ensiksi siten että sen heuristiikka oli alku- ja loppupisteen välinen välimatka. Tämä havaittiin tehottomaksi.

Kun pelialueen koko on 51x51 ja kartta on vakioitu saatiin seuraava (toistettavat) mittaustulokset:

Heuristiikka        Nodeja käyty läpi       Reitin pituus
välimatka * 1       614                     57
välimatka * 1.25    528                     57
välimatka * 1.5     347                     57
välimatka * 1.75    236                     57
välimatka * 2       198                     57
välimatka * 3       82                      57
välimatka * 4       70                      57
välimatka * 5       60                      57
välimatka * 6       60                      57
välimatka * 7       60                      57
välimatka ^2        62                      57                      

Alkutilanne:
![Heuristiikka: välimatka * 1](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/mittaukset/reitinhaku/51x51-alku.png)

Reitinhaku pelaajan liikuttua vasemmalle, heuristiikka: välimatka * 1
![Heuristiikka: välimatka * 1](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/mittaukset/reitinhaku/51x51-1.png)

Reitinhaku pelaajan liikuttua vasemmalle, heuristiikka: välimatka ^ 2
![Heuristiikka: välimatka ^ 2](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/mittaukset/reitinhaku/51x51-potenssi.png)

