## Mitä olen tehnyt tällä viikolla?

Valmistelin ensiksi skeletonin kuntoon ja tämän jälkeen toteutin loput KeyListeneristä ja pelaaja-oliota ohjaavasta oliosta. Tämän jälkeen kopioin melko suoraan Trystanin blogista [soluautomaatio-algoritmin](http://trystans.blogspot.com.br/2011/08/roguelike-tutorial-03-scrolling-through.html) jotta näen millaisia karttoja sellainen tuottaa. Tarkoituksena keksiä ensi viikolla jotain ovelampaa tilalle.

JUnit testejä varten otin käyttöön Mockito ja tein ensimmäisiä yksikkötestejä. Testikattavuus vielä heikko sillä en tehnyt testejä koodille jota saatan lähipäivinä päätyö refaktoroimaan rankemman kautta.

Aikaa käytetty tähän mennessä ~29t.


## Miten ohjelma on edistynyt?

Kartalla pystyy vihdoin liikkumaan ja ensimmäiset yksikkö/testit tehty. 


## Mitä opin tällä viikolla / tänään?

Event driven -malli tullut tutuksi. 

Riippuvuuksien injektointia käyttänyt aikaisemmin harjoituksissa mutta nyt "omassa koodissa" huomaa kuinka se tekee testauksesta kivempaa kun pääsee tekemään mock -olioita.


## Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.

Swingin event-driven malli tuottaa harmaita hiuksia. Haluaisin että kartta piirretään pelaajan tai hirviön liikkeen jälkeen mutta nykyisessä sovellusrakenteessa tuntuu hankalalle. Vaihtoehtoisesti saatan siirtyä kartanpiirtoon jossa kartta pelaajineen ja hiriviöineen renderöidään ennen piirtämistä jotta @ ei vilku..


## Mitä teen seuraavaksi?

Ensisijaisesti kartanpiirtelyn algoritmia / algoritmeja.

Jos aikaa jää:

* hirviöiden AI-reitinhakualgoritmin
* ArrayListin (ainakin) hirviöitä varten
* refaktorointia jotta vuoropohjaisuus ja piirtäminen toimii paremmin
* refaktorointia jossa kartan luonti siirretään oliotehtaaseen  