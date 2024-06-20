package cat.urv.deim;

import java.util.Stack;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PilaBuida;
import cat.urv.deim.exceptions.PilaPlena;

public class PilaStack implements TADPila {

    private Stack<Persona> pila; // Pila con Java para almacenar las personas
    private int maxSize; // Tamaño maximo de la pila

    /** Constructor de la pila
     *
     * @param size tamaño maximo de la pila
     */
    public PilaStack(int size) {
        this.pila = new Stack<Persona>(); // Inicializamos la pila sin especificar un tamaño
        this.maxSize = size; // Guardamos el tamaño máximo de la pila
    }

    @Override
    public void apilar(Persona p) throws PilaPlena {
        if (esPlena()) // Si está llena la pila, damos mensaje de error
            throw new PilaPlena();
        pila.push(p); // Añadimos la persona a la pila
    }

    @Override
    public void desapilar() throws PilaBuida {
        if (esBuida()) // Si está vacía la pila, damos mensaje de error
            throw new PilaBuida();
        pila.pop(); // Eliminamos la persona de la pila
    }

    @Override
    public Persona cim() throws PilaBuida {
        if (esBuida()) // Si está vacía la pila, damos mensaje de error
            throw new PilaBuida();
        return pila.peek(); // Devolvemos la persona que está en la cima de la pila
    }

    @Override
    public int numElem() {
        return pila.size(); // Devolvemos el número de elementos de la pila
    }

    @Override
    public boolean esBuida() {
        return pila.isEmpty(); // Devolvemos si la pila está vacía
    }

    @Override
    public boolean esPlena() {
        return pila.size() == maxSize; // Devolvemos si la pila está llena
    }

    @Override
    public Persona anterior(Persona p) throws ElementNoTrobat {
        if (esBuida()) // Si está vacía la pila, damos mensaje de error
            throw new ElementNoTrobat();

        return buscarPersona(p, true);
    }

    @Override
    public Persona seguent(Persona p) throws ElementNoTrobat {
        if (esBuida()) // Si está vacía la pila, damos mensaje de error
            throw new ElementNoTrobat();

        return buscarPersona(p, false);
    }

    private Persona buscarPersona(Persona p, boolean esAnterior) throws ElementNoTrobat {
        int pos = pila.indexOf(p); // Buscamos la persona en la pila
        if (pos == -1) // Si no la encontramos, devolvemos null
            throw new ElementNoTrobat();

        // Devolvemos la persona segun si es el anterior o siguiente
        // 1a linea: devuelve anterior si esAnterior es true y pos > 0
        // 2a linea: devuelve siguiente si no es la cima
        return (esAnterior && pos > 0) ? pila.get(pos - 1) :
               (pos < numElem() - 1)   ? pila.get(pos + 1) : null;
    }
}
