## Ohjelman suorittaminen 

Ohjelma on helpointa suorittaa käännetystä Tractus.jar tiedostosta. Joko komentorivillä menemällä kansioon `/Tractus/build/libs`ja antamalla käsky `java -jar Tractus.jar` tai avaamalla tuo tiedosto työpöydällä.

Peli on vuoropohjainen. Siinä pelaajan hahmo `@` liikkuu kartalla `W`, `A`, `S` ja `D`-näppäimillä ja pyrkii välttämään `*` hirviöitä. Peli loppuu kun hirviö päätyy pelaajan ruutuun. Uusi peli alkaa painamalla `A`-näppäintä.


## Kehittämisohje

Projekti on Gradlella tehty ja kehittäminen edellyttää Gradlen [asentamista](https://gradle.org/install/).  

Projekti on reposition `Tractus`-kansiossa ja loitsimalla sen juuressa `gradle` asentuvat projektin riippuvuudet.

Testit voi ajaa Gradlella loitsulla `gradle test` ja projektin voi buildata loitsulla `gradle build`.


## Projektin rakenne

Projekti noudattelee MVC-mallia. Rakenne on tarkemmin kuvattu [toteutus](https://github.com/juhoaj/tiralabra-tractus/blob/master/documentation/toteutus.md) -dokumentissa.


