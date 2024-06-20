[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/Ah1mLC4h)
# Activitat 2: Implementacio d'una llista generica

En aquesta activitat programareu dues classes que implementaran dues llistes generiques, per tant, aquestes llistes cal que puguin guardar qualsevol tipus de dada que li especifiquem en la seva definicio.

En una de les llistes la informacio no ha d'estar ordenada, mentre que a la segona llista si que ha d'estar-ho. Alguns detalls sobre les dues implementacions:

* Ambdues llistes han d'estar implementades fent servir memoria dinamica, sense capacitat maxima prefixada.
* Les llistes han d'incorporar l'element fantasma que hem vist a teoria, i tenir-ho en compte en els algorismes de la llista per a poder-los optimitzar.
* Per a implementar les llistes caldra que es pugui comparar la informacio dels objectes que es guarden per a poder-ne saber si un es mes gran que un altre. Per tant, qualsevol classe que es vulgui guardar en aquesta llista haura d'implementar la interficie comparable.

Per a poder testejar la classe, farem us de dues altres classes que haureu d'implementar:

* La classe Persona que ja vam utilitzar a l'activitat anterior, pero aquest cop haureu d'implementar-ne el comportament de la interficie comparable. Tal i com podreu veure en el codi, a l'hora de comparar dues persones primer mirarem quina te el valor mes petit de cognom, i en el cas de que fossin iguals, mirarem qui te me petit el valor de nom. Per altra banda, per a comprovar si dues persones son iguals, el que mirarem es si el seu valor d'id_persona es igual.
* Tenim una nova classe LlistaPersona, que ha d'implementar els metodes per a guardar una llista de persones, fent servir les dues implementacions de llista generica que haureu programat. Aquesta classe te dos constructors, en ambdos cal especificar amb una variable booleana si es vol fer servir una implementacio estatica o una de dinamica, i en una de les dues classes es rep com a parametre un string amb un nom de fitxer per a carregar-ne totes les dades, mentre que a l'altre constructor nomes te el parametre del boolea, i ens crea una estructura buida.

A l'hora de valorar l'activitat, a part de revisar que el codi passi tots els testos i que sigui el mes optim possible, es tindran en compte dos elements en particular:
* Que el codi de la implementacio ordenada s'optimitzi tenint en compte que sabem que la informacio esta ordenada.
* Que s'implementi una classe abstracta addicional que permeti simplificar la redundacia de codi que hi haura a les dues implementacions de tipus llista.


