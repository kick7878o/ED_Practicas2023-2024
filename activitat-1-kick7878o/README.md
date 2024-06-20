# Activitat 1: Implementació d'una interfície

En aquesta activitat programareu tres classes que compleixin amb les funcions descrites a una interfície per a implementar una estructura de tipus pila. Les tres implementacions que haureu de fer estaran basades en:

* Una estructura estàtica
* Una estructura dinàmica basada en nodes
* Una implementació que faci servir la pila de la llibreria d'utilitats de java (java.util.Stack)

En tots els casos heu d'implementar els mètodes descrits a la interfície, així com un constructor que rep com a paràmetre el nombre màxim d'elements que es poden emmagatzemar a l'estructura. Tot i que les estructures dinàmiques realment no haurien d'estar sotmeses a aquest valor màxim, per compatibilitat del codi haureu de fer que cumpleixin amb aquesta condició, i controlar-ho adequadament a dins de cada implementació.

Per a poder treballar amb la pila, hem creat una classe Persona que encapsularà les dades que haureu d'emmagatzemar a dins de la pila. Haureu d'implementar aquest mètode programa principal que llegeix el fitxer persones.csv, i carrega totes les dades (creant els objectes de tipus persona corresponent) a una pila del tipus que teniu inicialitzat.

    private void carregarDades(String filename) {}

A part de les funcions bàsiques de la pila, hem afegit dues funcions de control que serveixen per a garantir que l'estructura de la pila és correcta. Aquestes dues funcions son les següents:

	public Persona anterior(Persona p) throws ElementNoTrobat;
Aquest metode retorna l'element que hi ha guardat anterior a la pila, és a dir, el que fa mes temps que hi es que el que passem per parametre. Per exemple, si fessim apilar(p1), apilar(p2), apilar(p3), i cridem anterior(p2) ha de retornar p1. Si cridem anterior(p1) -l'element que fa mes temps que hi és- ha de retornar null.

    public Persona seguent(Persona p) throws ElementNoTrobat;
Aquest metode retorna l'element seguent a la pila, és a dir, el que fa menys temps que hi es que el que passem per parametre. Per exemple, si fessim apilar(p1), apilar(p2), apilar(p3), i cridem seguent(p2) ha de retornar p3. Si cridem el seguent(p3) -l'element mes nou- ha de retornar null.

Recordeu que abans de fer el commit/push del vostre codi heu de comprovar que el vostre codi passa tots els tests de validació (son 3 fitxers de test amb 20 tests cadascun!).

IMPORTANT: Només es pot utilitzar la classe Stack de la llibreria java.util, no es pot utilitzar cap altre de les classes d'aquesta llibreria.

