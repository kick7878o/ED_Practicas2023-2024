[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/qmVvkFMl)
# Activitat 3: Implementació d'una Taula de Hashing (HashMap) i comparació del rendiment entre una Taula de Hashing i una Llista.

Aquesta activitat esta dissenyada per a que implementeu una taula de hashing encadenada indirecta des de zero, i que comproveu el seu rendiment respecte el funcionament d'una llista.

A continuacio teniu instruccions de com heu de fer la implementació. Llegiu-les **MOLT detalladament**:

* Us proporcionarem una interficie de la taula de hashing i una interficie equivalent de la llista. En ambdues interficies veureu que les funcions son molt similars.

* Pel que fa a la llista, **podeu fer servir les implementacions ordenada i no ordenada sobre memoria dinamica i amb element fantasma de l'activitat anterior**. La principal novetat en aquesta estructura es un metode que permet retornar un **objecte iterable** per a fer un recorregut per damunt dels objectes de la llista de forma ordenada, **eliminant el metode que retornava tots els elements**. Caldra que implementeu aquest metode en les dues llistes i que esborreu l'altre metode.

* Pel que fa a la taula de hashing ha d'implementar els metodes descrits a la interficie **IHashMap**. Haureu d'implementar una **taula de hashing encadenada indirecta**. Aquesta taula rebra per parametre la mida inicial de la taula, i implementara les funcions descrites al codi, tenint en compte la logica de funcionament que es comenta a les capçaleres (control d'elements repetits, etc..).

* A l'hora de guardar els elements de la taula de hash, si feu servir un array de java (indicat pels caracters []) us donara problemes. Per aquesta rao, en aquesta practica us deixem que la part estatica de la taula la guardeu amb una estructura del tipus ArrayList (java.util.ArrayList).

* S'ha de controlar el factor de carrega de la taula. **Si aquest valor supera el 75% de la mida de la taula, al fer una insercio el sistema haura de duplicar la mida de la taula** abans de deixar insertar el nou valor. Recordeu de com es fa aquest redimensionament, tal i com s'ha explicat a la classe de teoria.

* La taula ha d'implementar un metode per a poder iterar sobre la informacio que hi ha guardada **de forma ordenada**, retornant un **objecte Iterable** que ens permeti recorrer tota la informacio.

* Heu de canviar el metode **compareTo** de la classe Persona es la mateixa per a **comparar per ID enlloc de Nom i Cognom**.

* Heu d'implementar tots els metodes de la classe HashMapPersones.

A l'hora de valorar l'activitat, revisarem de nou que el codi passi tots els tests i que sigui el mes optim possible.



