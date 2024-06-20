[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/jYc2u8sN)
# Activitat 4: Implementacio d'un Graf No dirigit i Etiquetat.

Aquesta darrera activitat autoavaluable esta dissenyada per a que implementeu una estructura que pugui guardar l'estructura corresponent a un graf.

A continuacio teniu instruccions de com heu de fer la implementacio. Llegiu-les **MOLT detalladament**:

* Per aquesta activitat us proporcionem tambe les interficies de la taula de hashing i la llista de les dues activitats anteriors. Podeu importar el codi de les altres dues activitats de les implementacions de les dues llistes (ordenada i no ordenada) i de la taula de hashing.

* Respecte la taula de hashing, **hem fet una modificacio menor per a eliminar els problemes amb els casts**. Ara la funcio obtenirClaus retorna un objecte de tipus ILllistaGenerica enlloc d'un array estatic []. Haureu de fer una adaptacio del vostre codi de la taula de hash per a que funcioni. Compte que tambe hem canviat la capsalera de la implementacio de la taula, ja que ara tambe es necessita que K implementi Comparable.

* Us donem una nova interficie corresponent a la implementacio d'un graf, amb tots els metodes que caldra implementar. Com en les activitats anteriors, en les capsaleres teniu els detalls que descriuen el comportament del metode.

* Treballarem amb grafs NO dirigits i etiquetats. El graf treballa **amb tres variables generiques**, K que es el valor identificador dels vertexs, V que sera el tipus de dades que es guardara als vertexs, i E que sera el tipus de dades que es guardara a les arestes.

* Per a guardar els vertexs del graf podeu fer servir la vostra taula de hashing de l'activitat anterior.

* En aquest cas, teniu una classe GrafPersones que serveix per a utilitzar tots els metodes del graf per a guardar-hi amistats entre persones. Teniu ara dos tipus de fitxers de dades, el mateix que hem fet servir en les activitats anterior amb les dades de les persones, i un nou fitxer (persones_amistatsX.csv) que guarda relacions d'amistat entre les persones, amb un valor enter que indica la intesitat de l'amistat. Teniu tres fitxers amb diferents relacions que es fan servir als testos.

* En aquesta activitat hi ha **2 metodes que son opcionals i que valen 2 punts**. Si implementeu tota la resta podeu optar a una nota maxima de 8, i si feu aquests metodes podeu optar a tenir fins a un 10.

* Us pot ser util implementar **una nova versio de la llista generica** que implementi la interficie ILlistaGenerica que garanteixi que **la insercio dels elements es produeix al final de la llista i que no hi pugui haver elmements repetits** (en les altres implementacions demanades aquests no eren requisits que es demanessin).

* En el cas de que no vulgueu implementar les parts opcionals, haureu de posar els metodes igualment dins el vostre codi, pero haureu de fer que simplement retorni llistes buides (i no es passaran els testos pero no passa res ja que no optareu als punts d'aquesta part)

* Recordeu que no es poden modificar els fitxers de les interficies ni els de test a no ser que us ho comentem nosaltres si detectem alguna errada.

* Tambe us hem deixat tres fitxers en format graphml per si voleu visualitzar l'estructura dels grafs en algun programa extern que soporti aquest format.

A l'hora de valorar l'activitat, revisarem de nou que el codi passi tots els tests i que el codi sigui el mes optim possible.
