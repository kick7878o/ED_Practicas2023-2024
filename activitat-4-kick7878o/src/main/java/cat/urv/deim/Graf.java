package cat.urv.deim;

import cat.urv.deim.exceptions.VertexNoTrobat;
import cat.urv.deim.exceptions.ArestaNoTrobada;
import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PosicioForaRang;

public class Graf<K extends Comparable<K>, V, E> implements IGraf<K, V, E> {
    private IHashMap<K, NodoVertices> grafo;
    private int nAristas;

    private class NodoVertices { // Nodo de la tabla de hash
        V valor; // Valor del vertice
        LlistaNoOrdenada<NodoAristas> aristas; // Lista de aristas

        public NodoVertices(V valor) {
            this.valor = valor;
            this.aristas = new LlistaNoOrdenada<NodoAristas>();
        }
    }

    private class NodoAristas { // Nodo de la lista de aristas
        K verticeAdyacente; // Vertice adyacente a la arista
        E pes; // Peso de la arista

        public NodoAristas(K vDest, E pes) {
            this.verticeAdyacente = vDest;
            this.pes = pes;
        }
    }

    /** Constructor del grafo
     *
     * @param size Tamaño de la tabla de hash para el grafo
     */
    public Graf(int size) {
        grafo = new HashMapIndirecte<>(size);
        nAristas = 0;
    }


////////////////////////////////////////////////////////////////////////////////////
// Operaciones para trabajar con los vertices

    @Override
    public void inserirVertex(K key, V value) {
        grafo.inserir(key, new NodoVertices(value)); // Insertar en el grafo
    }

    @Override
    public V consultarVertex(K key) throws VertexNoTrobat {
        try { // Obtener el valor del vertice
            return grafo.consultar(key).valor;
        } catch (ElementNoTrobat e) {
            throw new VertexNoTrobat();
        }
    }

    @Override
    public void esborrarVertex(K key) throws VertexNoTrobat {
        try {
            // Miramos si existe el vertice a borrar
            NodoVertices vertice = grafo.consultar(key);

            // Obtenemos el numero de elementos de la lista de aristas del vertice a eliminar
            int indice = vertice.aristas.numElements()-1;

            // Iteramos de fin a principio eliminando las aristas
            for (int i = indice; i >= 0; i--) {
                esborrarAresta(key, vertice.aristas.consultar(i).verticeAdyacente);
            }

            grafo.esborrar(key); // Borramos el vertice de la arista al eliminar todas las aristas
        } catch (ElementNoTrobat | ArestaNoTrobada | PosicioForaRang e) {
            throw new VertexNoTrobat();
        }
    }

    @Override
    public boolean esBuida() { return grafo.esBuida();}

    @Override
    public int numVertex() { return grafo.numElements(); }

    @Override
    public ILlistaGenerica<K> obtenirVertexIDs() {
        return grafo.obtenirClaus();
    }


////////////////////////////////////////////////////////////////////////////////////
// Operaciones para trabajar con las aristas

    @Override
    public void inserirAresta(K v1, K v2, E pes) throws VertexNoTrobat {
        try {
            NodoVertices vertice1 = grafo.consultar(v1);
            NodoVertices vertice2 = grafo.consultar(v2); // Obtenemos los vertices

            NodoAristas arista1 = new NodoAristas(v2, pes);
            NodoAristas arista2 = new NodoAristas(v1, pes); // Creamos las aristas

            if (existeixAresta(v1, v2)) { // Si la arista ya existe, actualizamos el peso

                // Buscle para buscar la arista en el vertice 1
                for (int i = 0; i < vertice1.aristas.numElements(); i++) {
                    if (vertice1.aristas.consultar(i).verticeAdyacente.equals(v2))
                        vertice1.aristas.consultar(i).pes = pes; // Actualizamos el peso
                }

                // Buscle para buscar la arista en el vertice 2
                for (int i = 0; i < vertice2.aristas.numElements(); i++) {
                    if (vertice2.aristas.consultar(i).verticeAdyacente.equals(v1))
                        vertice2.aristas.consultar(i).pes = pes; // Actualizamos el peso
                }

            } else { // Si no existe, la añadimos
                vertice1.aristas.inserir(arista1); // Arista 1 de v1 a v2
                vertice2.aristas.inserir(arista2); // Arista 2 de v2 a v1
                nAristas++;
            }
        } catch (ElementNoTrobat | PosicioForaRang e) {
            throw new VertexNoTrobat();
        }
    }

    @Override
    public void inserirAresta(K v1, K v2) throws VertexNoTrobat {
        // No hace falta hacer un duplicado osea que simplemente llamaremos el
        //   otro método y acabamos rapido
        inserirAresta(v1, v2, null);
    }

    @Override
    public boolean existeixAresta(K v1, K v2) throws VertexNoTrobat {
        try {
            // Obtenemos el vertice de v1
            NodoVertices vertice1 = grafo.consultar(v1);

            // Buscle para buscar la arista des de solo uno de los vertices
            //   ya que si existe en un vertice en la otra seguro que también
            for (int i = 0; i < vertice1.aristas.numElements(); i++) {
                if (vertice1.aristas.consultar(i).verticeAdyacente.equals(v2))
                    return true;
            }
            return false;
        } catch (ElementNoTrobat | PosicioForaRang e) {
            throw new VertexNoTrobat();
        }
    }

    @Override
    public E consultarAresta(K v1, K v2) throws VertexNoTrobat, ArestaNoTrobada {
        try {
            NodoVertices vert1 = grafo.consultar(v1); // Obtenemos el vertice

            // Buscle para buscar la arista
            for (int i = 0; i < vert1.aristas.numElements(); i++) {
                if (vert1.aristas.consultar(i).verticeAdyacente.equals(v2))
                    return vert1.aristas.consultar(i).pes; // Si la encontramos, devolvemos el peso
            }
            throw new ArestaNoTrobada();
        } catch (ElementNoTrobat e) {
            throw new VertexNoTrobat();
        } catch (PosicioForaRang e) {
            throw new ArestaNoTrobada();
        }
    }

    @Override
    public void esborrarAresta(K v1, K v2) throws VertexNoTrobat, ArestaNoTrobada {
        try {
            NodoVertices vert1 = grafo.consultar(v1);
            NodoVertices vert2 = grafo.consultar(v2); // Obtenemos los vertices

            // Buscamos y eliminamos la arista des de v1 a v2
            boolean aristaElim = false; // Controlamos si existe la arista a eliminar
            NodoAristas aristaEliminar = null; // Obtenemos la arista

            // Recorremoms cada arista del vertice buscando v2
            for (int i = 0; i < vert1.aristas.numElements(); i++) {
                if (vert1.aristas.consultar(i).verticeAdyacente.equals(v2)) {
                    aristaEliminar = vert1.aristas.consultar(i);
                    aristaElim = true;
                    break;
                }
            }

            // Si no se ha eliminado la arista, lanzamos una excepcion
            if (!aristaElim)  throw new ArestaNoTrobada();
            vert1.aristas.esborrar(aristaEliminar); // Eliminamos la arista: v1 -> v2

            // Buscamos y eliminamos la arista des de v2 a v1
            aristaElim = false; // reiniciamos el controlador
            // Recorremos cada arista del vertice buscando v1
            for (int i = 0; i < vert2.aristas.numElements(); i++) {
                if (vert2.aristas.consultar(i).verticeAdyacente.equals(v1)) {
                    aristaEliminar = vert2.aristas.consultar(i);
                    aristaElim = true;
                    break;
                }
            }

            // Si no se ha eliminado la arista, lanzamos una excepcion
            if (!aristaElim)  throw new ArestaNoTrobada();
            vert2.aristas.esborrar(aristaEliminar); // Eliminamos la arista: v2 -> v1

            nAristas--;
        } catch (ElementNoTrobat e) {
            throw new VertexNoTrobat();
        } catch (PosicioForaRang e) {
            throw new ArestaNoTrobada();
        }
    }

    @Override
    public boolean vertexAillat(K v1) throws VertexNoTrobat {
        try {
            return grafo.consultar(v1).aristas.numElements() == 0;
        } catch (ElementNoTrobat e) {
            throw new VertexNoTrobat();
        }
    }

    @Override
    public int numVeins(K v1) throws VertexNoTrobat {
        try {
            return grafo.consultar(v1).aristas.numElements();
        } catch (ElementNoTrobat e) {
            throw new VertexNoTrobat();
        }
    }

    @Override
    public ILlistaGenerica<K> obtenirVeins(K v1) throws VertexNoTrobat {
        try {
            NodoVertices vert1 = grafo.consultar(v1); // Obtenemos el vertice
            LlistaNoOrdenada<K> veins = new LlistaNoOrdenada<>(); // Creamos la lista de vertices

            // Añadimos los vertices adyacentes a la lista
            for (int i = 0; i < vert1.aristas.numElements(); i++) {
                veins.inserir(vert1.aristas.consultar(i).verticeAdyacente);
            }
            return veins;
        } catch (ElementNoTrobat | PosicioForaRang e) {
            throw new VertexNoTrobat();
        }
    }

    @Override
    public int numArestes() { return nAristas; }


////////////////////////////////////////////////////////////////////////////////////
// Metodos OPCIONALES

    @Override
    public ILlistaGenerica<K> obtenirNodesConnectats(K v1) throws VertexNoTrobat {
        return null;
    }

    @Override
    public ILlistaGenerica<K> obtenirComponentConnexaMesGran() {
        return null;
    }
}
