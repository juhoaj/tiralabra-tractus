## Mikä ongelma halutaan ratkaista

Harjoitustyössä harjoitellaan Javalla luolaston algoritmipohjaista rakentamista ja siinä @-pelihahmolla liikkumista. Mikäli tämä ei harjoitustyön aikana tunnu tarpeeksi haastavalta lisätään sovellukseen ö-ölliköitä jotka kulkevat kohti @-pelihahmoa lyhyintä reittiä pitkin.

## Mitä algoritmeja ja tietorakenteita työssä käytetään ja miksi

Tarkoituksena on käyttää cellular automata -algoritmiä luolaston luomiseen mutta mikäli tämä ei vaikuta sovelluksen tarkemman rakenteen suunnittelun jälkeen fiksulta vaihdetaan simplex -algoritmiin. Jos kehitetyllä algoritmilla saadaan luotua suuri yhtenäinen luola käytetään flood fill -algoritmiä pienien luolien täyttämiseen. Muussa tapauksessa hyödynnetään Dijkstran tai A* -algoritmia luolien yhdistämiseen. 

Mikäli kehitetään ö-ölliköitä hyödynnetään niissä Dijkstran tai A* -algoritmia.


## Mitä syötteitä ohjelma saa ja miten näitä käytetään

Syötteenä tulee olemaan käyttäjän liikkuminen. 


## Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)

Sovellus pyritään saamaan toimimaan vähintään Dijkstran algoritmin O(n + m log n) aikavaativuudella. Mikäli ö-ölliköitä luodaan pyritään pitämään vain yhden ö-öllikän reitinhaku muistissa kerralla.


## Lähteet

[Procedural Dungeon Generation: Cellular Automata, jrheard's blog](http://www.roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels) 

[Making maps with noise functions, Red Blob Games](https://www.redblobgames.com/maps/terrain-from-noise/) 