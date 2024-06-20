package cat.urv.deim;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PilaBuida;
import cat.urv.deim.exceptions.PilaPlena;

public class PilaDinamica implements TADPila {
    private Node_Pila cima; // Ultimo nodo añadido
    private int numElem, maxSize; // Numero de elementos y tamaño maximo de la pila

    /** Constructor de la Pila Dinamica
     *
     * @param size tamaño maximo de la pila
     */
    public PilaDinamica(int size) {
        this.cima = null; // Inicializar la cima a null ya que no hay datos
        this.numElem = 0; // Elemenots a 0 ya que no tiene datos
        this.maxSize = size; // Marcamos un maximo de elementos en la pila
    }

    @Override
    public void apilar(Persona p) throws PilaPlena {
        if (esPlena()) throw new PilaPlena(); // Comprobamos si esta la pila llena

        // Nuevo nodo que contiene la persona y la cima actual
        Node_Pila newNode = new Node_Pila(p, cima);
        this.cima = newNode; // La cima sera el nuevo nodo creado
        numElem++;
    }

    @Override
    public void desapilar() throws PilaBuida {
        if (esBuida()) throw new PilaBuida(); // Comprobamos si esta la pila vacia

        // Simplemente movemos la cima al siguiente nodo mas reciente
        this.cima = this.cima.getSeguent();
        numElem--;
    }

    @Override
    public Persona cim() throws PilaBuida {
        if (esBuida()) throw new PilaBuida(); // Comprobamos si esta la pila vacia
        return this.cima.getPersona(); // Devolver la persona de la cima actual
    }

    @Override
    public int numElem() { return numElem; }

    @Override
    public boolean esBuida() { return this.cima == null; }

    @Override
    public boolean esPlena() { return numElem == maxSize; }

    @Override
    public Persona anterior(Persona p) throws ElementNoTrobat {
        if (esBuida())
            throw new ElementNoTrobat(); // Comprobamos si esta la pila vacia

        if (numElem == 1)
            return null; // Si solo hay un nodo, no puede haber un anterior

        return buscarPersona(p, true); // Buscamos la persona anterior
    }

    @Override
    public Persona seguent(Persona p) throws ElementNoTrobat {
        if (esBuida())
            throw new ElementNoTrobat(); // Comprobamos si esta la pila vacia

        if (this.cima.getPersona().equals(p))
            return null; // Si el buscado esta en la cima, no puede haber un siguiente

        return buscarPersona(p, false); // Buscamos la persona siguiente
    }

    /** Metodo que busca la persona en la pila y
     * devuelve el anterior o el siguiente dependiendo de lo que buscamos
     *
     * @param p persona buscada
     * @param esAnterior controlamos si buscamos el anterior o el siguiente
     * @return la persona anterior o siguiente
     * @throws ElementNoTrobat si no esta la persona en la pila
     */
    private Persona buscarPersona(Persona p, boolean esAnterior) throws ElementNoTrobat {
        Node_Pila act = this.cima; // Empezamos a mirar des de la cima
        Node_Pila ant = null; // El siguiente nodo de la cima no hay datos

        // Bucle que itera sobre los nodos de la pila
        while (act != null) {
            if (act.getPersona().equals(p)) // Comprobamos si la persona actual es la buscada
                // Devolvemos el anterior o el actual dependiendo de lo que buscamos
                return esAnterior ? act.getSeguent().getPersona() : ant.getPersona();

            // Actualizamos los nodos y miramos el siguiente
            ant = act;
            act = act.getSeguent();
        }
        throw new ElementNoTrobat(); // Error si no encuentra la persona
    }
}
