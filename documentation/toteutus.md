## Ohjelman yleisrakenne













 Näistä merkittävin on [AsciiPanel](https://github.com/trystan/AsciiPanel) jota käytetään pelin piirtämiseen.












# Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)

## Maailman luominen

Maailman luomisen kesto on suoraan suhteessa kartain tiilien määrään 18 mittauksen keskiarvoilla. Näin ollen voidaan päätellä että kartan luomisen algoritmin tehokkuus on O(m*n) jossa m on smooth -soluautomaatiometodin toistokerrat ja n kartan tiilien määrä. Satunnaisen hälyn vaativuus on vain n joten se lienee järkevintä jättää algoritmin tehokkuudessa mainitsematta.

![Kartan luomisen kesto eri kokoisilla maailmoilla](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/mittaukset/graafi4.png)

Maailman luomisen vaatiman ajan hajonta on kuitenkin verrattain suuri.

![Kartan luomisen keston hajonta eri kokoisilla maailmoilla](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/mittaukset/graafi2.png)


## Reitinhaku

Kun A* heuristiikkana on matka^2 ja kartta vakioitu saadaan seuraavat mittaustulokset. Vakioidun ja sattumanvaraisesti luodun maailman mittaustulosten vastaavuus kuvattu [Testaus](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/testaus.md)-dokumentissa kappaleessa 'Reitinhaun käyttäytymisen testaus keon valinnan tueksi'.

| Maailman koko       | Reitin pituus     | Nodeja lisätty pinoon    | Pinon maksimikoko    | Nodeja käsitelty | pituus / lisätty    | lisätty / maksimi   |
|---------------------|------------------:|-------------------------:|---------------------:|-----------------:|--------------------:|--------------------:|
| 51x51               | 57                | 150                      | 89                   | 62                             | 0,38	               | 0,59                |
| 500x500             | 241               | 660                      | 418                  | 243                            | 0,37	               | 0,63                | 
| 1000x1000           | 669               | 1848                     | 1093                 | 756                           | 0,36	               | 0,59                |
| 2001x2001           | 1122              | 3149                     | 1918                 | 1232                           | 0,36	               | 0,61                |
| 4001x4001           | 2082              | 5814                     | 3490                 | 2325                           | 0,36	               | 0,60                |












## Keko










# Työn mahdolliset puutteet ja parannusehdotukset

Työn lähtökohtana on ollut "kuinka iso luolasto voidaan ja kuinka monta hirviötä siinä voi olla ennenkuin algoritmit kyykkäävät". 

Näin ollen maailman luomisessa voitaisiin käyttää myös proseduraalista maailman luomista jossa karttaa generoidaan pelaajan liikkeiden mukaan. Lisäksi hirviöt voisivat kauempana pelaajasta ollessaan käyttää vielä nopeampaa algoritmia, esim. vain kulkeutua pelaajan suuntaan. 

Field of view -algoritmia olisi myös ollut kiva harjoitella työssä mutta tämä rajattiin pois ajankäytön järkevyyden nimissä.

Jos harjoitustyötä tarkastellaan pelinä niin suurin osa pelillisistä elementeistä toki loistaa puuttumisellaan. 


## Lähteet
* A* algoritmi [Easy A* (star) Pathfinding, Nicholas Swift](https://medium.com/@nicholas.w.swift/easy-a-star-pathfinding-7e6689c7f7b2)

* A* algoritmi [Java Generics - Implementing the Iterable Interface, Jakob Jenkov](http://tutorials.jenkov.com/java-generics/implementing-iterable.html)

https://codesjava.com/generics-constructor-in-java-example-tutorial

* Heap valinta [From Amit’s Thoughts on Pathfinding: Implementation notes](http://theory.stanford.edu/~amitp/GameProgramming/ImplementationNotes.html)