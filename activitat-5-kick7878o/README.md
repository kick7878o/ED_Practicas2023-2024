[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/ATjmnE9P)
# Activitat 5: Implementació d'un algoritme de detecció de comunitats

Aquesta darrera activitat es el projecte final de l'assignatura i te un pes del **50% de la nota**. En aquesta activitat haureu de fer servir la majoria de les estructures de dades desenvolupades a les altres activitats. En aquesta activitat no hi ha un codi base ni un conjunt de tests a passar, heu de construir vosaltres el projecte des de 0, i pujar el resultat final a l'activitat 5.

Aquest any us demanem que implementeu un algorisme que permeti **identificar les comunitats en un graf**. Les comunitats son grups de nodes del graf que estan densament connectats entre si, mentre que estan poc connectats amb nodes d'altres grups/comunitats. 

![exemple_comunitats](https://upload.wikimedia.org/wikipedia/commons/f/f4/Network_Community_Structure.svg)

Podeu trobar més informació sobre que es l'estructura de comunitats a la pagina de la wikipedia: https://en.wikipedia.org/wiki/Community_structure

L'objectiu de l'activitat es crear un mètode que, donada una xarxa que haureu de carregar d'un fitxer, us retorni la millor subdivisió en comunitats possible. Per a fer això caldrà seguir els següents passos:

* Haureu de ser capaços de carregar un graf a partir d'un fitxer. Actualment hi ha varis formats per a descriure l'estructura d'un graf, nosaltres us demanem que sigueu capaços de llegir un d'aquests dos formats i que els carregueu a la vostra estructura de tipus graf. Els dos formats que us proposem son el GraphML (https://en.wikipedia.org/wiki/GraphML) i el Pajek NET (https://gephi.org/users/supported-graph-formats/pajek-net-format/). IMPORTANT: Per a tractar el fitxer GraphML (que es una versió de XML) us deixem que feu servir alguna llibreria externa de Java, com Java XML per a facilitar-vos la lectura de les dades.

* Nomes heu de considerar el problema per a grafs NO dirigits i NO etiquetats.

* Hi ha varies pàgines per Internet de les quals podeu trobar varis conjunts de grafs. Us deixem la del professor Alex Arenas (https://webs-deim.urv.cat/~alexandre.arenas/data/welcome.htm), on hi ha enllaços a altres pagines amb molts datasets que representen grafs. Si trobeu que algun dataset esta en un format que no es cap dels dos anteriors, podeu fer servir eines com Gephi (https://gephi.org) o Pajek (http://mrvar.fdv.uni-lj.si/pajek/) per a convertir-los i/o visualitzar-los.

* Per començar us recomanem que treballeu l'estructura de comunitats d'un graf que es coneix per Zachary Karate Club. Es tracta d'un dataset àmpliament estudiat en l’anàlisi de comunitats, del qual es coneix la separació optima en grups.
  
* En aquesta activitat, cada vèrtex tindrà un identificador que li servirà de clau, i el valor que es guardi en el vèrtex serà la comunitat a la qual pertany aquest vèrtex (que pot ser un numero enter en el rang 0 a N, on N seria el nombre de nodes).

* Per a mesurar la qualitat d'una separació en comunitats, es fa servir una mètrica que es coneix per Modularitat (https://en.wikipedia.org/wiki/Modularity_(networks)). Aquesta mètrica mesura la densitat de links que hi ha entre els nodes de la comunitat, i amb els nodes d'altres comunitats. Quan mes gran es el valor de la modularitat, millor es la subdivisió en comunitats. Teniu varies versions de la formula, però potser la més senzilla es la d'aquesta pagina on es suma la contribucio a la modularitat de cada comunitat (mireu-vos la segona equació que esta reduïda): https://networkx.org/documentation/stable/reference/algorithms/generated/networkx.algorithms.community.quality.modularity.html. (Si feu servir aquesta formula podeu suposar que el paràmetre de resolució val 1).

* Com us hem comentat, l'objectiu de l'activitat es que busqueu de totes les possibles subdivisions de nodes en comunitats, quina es la millor possible que podeu obtenir. Aquest es un problema d’optimització NP-hard, amb la qual cosa no podreu explorar totes les opcions possibles, i en la majoria de casos us quedareu amb un resultat sub-òptim.

* Teniu varies formes d'implementar aquesta optimització. La idea es que començareu amb un repartiment inicial de nodes en comunitats (que li direm la partició inicial), i a partir d’aquí l'algorisme a cada pas d’optimització ha de fer algun canvi de node (per exemple, canviar un node de la comunitat A a la B) i mirar si aquest canvi millora el valor de la modularitat. Aixi podeu anar fent canvis i anar-vos guardant la solució que tingueu que us dona la millor modularitat en cada moment. Aquest optimització es pot fer fent servir diferents criteris, a la plana de la wikipedia teniu moltes variacions, però jo us recomanaria que intenteu de fer-ne alguna de simple basada en la optimització de modularitat, que és la mesura que volem maximitzar (no son les mes eficients però son mes senzilles d'implementar).

* Aquest procés d’optimització s'ha d'aturar un cop l'algorisme hagi fet un cert nombre total de passos, o be podeu comprovar si durant un cert nombre de passos els resultats que us dona l'algorisme no milloren i llavors aturar-lo. Aneu amb compte de que l'algorisme no es quedi en un bucle infinit durant la optimització.

* Al final de l’execució haureu de retornar el valor màxim de modularitat obtingut, i la classificació de nodes en grups en algun fitxer de sortida (En el cas del Pajek el fitxer de sortida te el format .clu i en Graphml es pot retornar el fitxer amb un atribut que descriu la comunitat. Per exemple, si com a entrada rebeu la xarxa zachary.net, podríeu retornar com a sortida un fitxer anomenat zachary_0.41880.clu, on el nom hi afegiu el valor de la modularitat obtinguda, i a dins del fitxer hi guardeu les particions en comunitats dels vèrtexs.

A l'hora de valorar aquesta activitat tindrem en compte els següents punts:

* Que es facin servir de forma adequada les estructures dissenyades en les activitats prèvies de la classe

* Que s'hagi implementat correctament el càlcul de la modularitat

* Que el codi sigui capaç de llegir els formats d'entrada explicats anteriorment, i carregui les dades a les vostres estructures de dades.

* Que el codi implementi un algorisme que intenti optimitzar la modularitat, i donada una xarxa obtenir un resultat en un temps raonable (no hauríeu executar processos d’optimització que tardin mes de 10 minuts).

*  Us demanem que ens presenteu dins del vostre projecte els resultats obtinguts per almenys 5 xarxes diferents, tot i que es valorarà que proveu amb xarxes diverses. Es valorarà la capacitat de l'algorisme de tractar amb xarxes grans (1000 nodes o mes) i de tenir els resultats el mes òptims possibles.


