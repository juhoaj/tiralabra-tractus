## Mitä olen tehnyt tällä viikolla?

Refaktoroin ohjelman rakennetta yksinkertaisemmaksi. Pohdin pitkään luodako WorldController Engine-pakkaukseen mutta en nähnyt sitä kriittisenä asiana. 

Loin hirviöitä varten controllerin joka kutsuu A* algoritmia hyödyntävää reitinhakijaa hirviöiden liikuttamiseen. Hirviöt pystyvät kaivamaan seinän yhdessä kierroksessa ja menevät seinien läpi mikäli se on nopein reitti. Lisäsin peliin lopetuksen ja aloituksen.


## Miten ohjelma on edistynyt?

A* algoritmissa on ongelma tod.näk. potentiaalisten nodejen syöttämisessä kekoon. Lisäksi se saattaa tarjota liian "suoran" reitin.


## Mitä opin tällä viikolla / tänään?

A* algoritmin askartelua.


## Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.

A* algoritmin askartelu.


## Mitä teen seuraavaksi?

A* kuntoon / vaihtoon. Tämän jälkeen maailman luomisen parantelua, tietorakenteita ja testausta. Mikäli aikaa jää niin teen FOV-algoritmin.