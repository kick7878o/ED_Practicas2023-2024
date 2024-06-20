package cat.urv.deim.EstructuraDatos.graph;

import cat.urv.deim.exceptions.ArestaNoTrobada;
import cat.urv.deim.exceptions.VertexNoTrobat;
import cat.urv.deim.EstructuraDatos.linkedList.ILlistaGenerica;

public interface IGraf<K, V, E>  {

    ////////////////////////////////////////////////////////////////////////////////////
    // Operacions per a treballar amb els vertexs

    // Metode per insertar un nou vertex al graf. El valor de K es l'identificador del vertex i V es el valor del vertex
    public void inserirVertex(K key, V value);

    // Metode per a obtenir el valor d'un vertex del graf a partir del seu identificador
    public V consultarVertex(K key) throws VertexNoTrobat;

    // Metode per a esborrar un vertex del graf a partir del seu identificador
    // Aquest metode tambe ha d'esborrar totes les arestes associades a aquest vertex
    public void esborrarVertex(K key) throws VertexNoTrobat;

    // Metode per a comprovar si hi ha algun vertex introduit al graf
    public boolean esBuida();

    // Metode per a comprovar el nombre de vertexs introduits al graf
    public int numVertex();

    // Metode per a obtenir tots els ID de vertex de l'estrucutra
    public ILlistaGenerica<K> obtenirVertexIDs();

    ////////////////////////////////////////////////////////////////////////////////////
    // Operacions per a treballar amb les arestes

    // Metode per a insertar una aresta al graf. Els valors de vertex 1 i vertex 2 son els vertex a connectar i E es el pes de la aresta
    // Si ja existeix l'aresta se li actualitza el seu pes
    public void inserirAresta(K v1, K v2 , E pes) throws VertexNoTrobat;

    // Metode equivalent a l'anterior, afegint com a pes el valor null
    public void inserirAresta(K v1, K v2) throws VertexNoTrobat;

    // Metode per a saber si una aresta existeix a partir dels vertex que connecta
    public boolean existeixAresta(K v1, K v2) throws VertexNoTrobat;

    // Metode per a obtenir el pes d'una aresta a partir dels vertex que connecta
    public E consultarAresta(K v1, K v2) throws VertexNoTrobat, ArestaNoTrobada;

    // Metode per a esborrar una aresta a partir dels vertex que connecta
    public void esborrarAresta(K v1, K v2) throws VertexNoTrobat, ArestaNoTrobada;

    // Metode per a comptar quantes arestes te el graf en total
    public int numArestes();

    ////////////////////////////////////////////////////////////////////////////////////
    // Metodes auxiliars per a treballar amb el graf

    // Metode per a saber si un vertex te veins
    public boolean vertexAillat(K v1) throws VertexNoTrobat;

    // Metode per a saber quants veins te un vertex
    public int numVeins(K v1) throws VertexNoTrobat;

    // Metode per a obtenir tots els ID de vertex veins d'un vertex
    public ILlistaGenerica<K> obtenirVeins(K v1) throws VertexNoTrobat;

    ////////////////////////////////////////////////////////////////////////////////////
    // Metodes OPCIONALS - Si es fa la part obligatoria la nota maxima sera un 8
    // Si s'implementen aquests dos metodes correctament es podra obtenir fins a 2 punts addicionals

    // Metode per a obtenir tots els nodes que estan connectats a un vertex
    // es a dir, nodes als que hi ha un cami directe des del vertex
    // El node que es passa com a parametre tambe es retorna dins de la llista!
    // public ILlistaGenerica<K> obtenirNodesConnectats(K v1) throws VertexNoTrobat;

    // Metode per a obtenir els nodes que composen la Component Connexa mes gran del graf
    // public ILlistaGenerica<K> obtenirComponentConnexaMesGran();

}
