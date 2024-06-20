package cat.urv.deim;

import java.io.*;
import java.util.Iterator;

import cat.urv.deim.exceptions.ElementNoTrobat;

public class HashMapPersones {
    private IHashMap<Integer, Persona> hashPersonas;

    /** Constructor 1: HashMap vacio con un tamaño
     * indicado por parametro
     *
     * @param size tamaño HashMap
     */
    public HashMapPersones(int size) {
        if (size <= 0) // Comprobamos que el tamaño sea positivo
            throw new IllegalArgumentException("Ha de ser de tamaño positivo mayor que cero");

        hashPersonas = new HashMapIndirecte<Integer, Persona>(size);
    }

    /** Constructor 2: HashMap con un tamaño
     * indicado por parametro que carga
     * los datos dentro de ella
     *
     * @param size tamaño HashMap
     * @param filename nombre del fichero a cargar
     */
    public HashMapPersones(int size, String filename) {
        if (size <= 0) // Comprobamos que el tamaño sea positivo
            throw new IllegalArgumentException("Ha de ser de tamaño positivo mayor que cero");

        hashPersonas = new HashMapIndirecte<Integer, Persona>(size);
        cargarDatos(filename);
    }

    /** Metodo que inserta
     * en el HashMap una persona.
     *
     * @param p persona a insertar
     */
    public void inserir(Persona p) { hashPersonas.inserir(p.getId_persona(), p); }

    /** Metodo que borra del HashMap
     * el ID de una persona
     *
     * @param id ID de la persona a borrar
     * @throws ElementNoTrobat excepcion si no se encuentra el ID
     */
    public void esborrar(int id) throws ElementNoTrobat {
        hashPersonas.esborrar(id);
    }

    /** Metodo que devuelve
     * el numero de elementos ocupados.
     */
    public int numElements() { return hashPersonas.numElements(); }

    /** Metodo que indica si esta
     * vacio el HashMap.
     */
    public boolean esBuida() { return hashPersonas.esBuida(); }

    /** Metodo que indica el tamaño
     * total del HashMap.
     */
    public int mida() { return hashPersonas.midaTaula(); }

    /** Metodo que devuelve la persona
     * indicada por el ID.
     *
     * @param id ID de la persona a consultar
     * @throws ElementNoTrobat excepcion si no se encuentra el ID
     */
    public Persona consultar(int id) throws ElementNoTrobat {
        return hashPersonas.consultar(id);
    }

    /** Metodo que indica
     * si la persona esta en el HashMap.
     *
     * @param p persona a buscar
     * @return true -> existe || false -> no existe
     */
    public boolean buscar(Persona p) {
        return hashPersonas.buscar(p.getId_persona());
    }

    /** Metodo que obtiene el factor
     * de carga actual del HashMap.
     */
    public float factorCarrega() { return hashPersonas.factorCarrega(); }

    /** Metodo que devuelve un array
     * de personas con los elementos
     * del HashMap.
     */
    public Persona[] elements() {
        Iterator<Persona> it = hashPersonas.iterator();
        Persona[] pers = new Persona[numElements()];

        // Vamos iterando e insertando en el array
        int i = 0;
        while (it.hasNext()) {
            pers[i] = it.next(); // Guardamos el elemento
            i++;
        }
        return pers;
    }

    /** Metodo que devuelve un array
     * de personas segun si pesan menos
     * que el parametro indicado.
     *
     * NOTA: se considera que -> x < pes
     *
     * @param pes peso a comparar
     */
    public Persona[] personesPesInferior(int pes) {
        Persona[] pers = elements();

        // Obtenemos el tamaño del array con x < pes
        int contadorPesoInferior = 0;
        for (Persona p : pers) {
            if (p.getPes() < pes) contadorPesoInferior++;
        }

        // Creamos el array con el tamaño obtenido
        Persona[] listaPers = new Persona[contadorPesoInferior];

        contadorPesoInferior = 0; // Reiniciamos el contador
        // Rellenamos el array con las personas que pesan menos que x
        for (Persona p : pers) {
            if (p.getPes() < pes) {
                listaPers[contadorPesoInferior] = p;
                contadorPesoInferior++;
            }
        }
        return listaPers;
    }

    /** Metodo que devuelve un array
     * de enteros con los IDs de las personas
     * del HashMap ordenados de menor a mayor.
     */
    public int[] obtenirIDs() {
        Persona[] personas = elements(); // Obtenemos las personas del HashMap
        int[] ids = new int[personas.length]; // Creamos un array de enteros

        // Rellenamos el array con los IDs de las personas
        for (int i = 0; i < personas.length; i++)
            ids[i] = personas[i].getId_persona();

        return ids;
    }

// METODOS ADICIONALES

    /**
     * Metodo que carga los datos del fichero en la lista
     * Estructura del fichero:
     *  - IDPersona,Edat,Nom,Cognom,Alçada,Pes
     *
     * @param filename nombre del fichero
     */
    private void cargarDatos(String filename) {
        try {
            // Abrimos fichero para leer
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line; // Variable para leer cada línea del fichero

            // Bucle para tratar cada línea del fichero
            while ((line = br.readLine()) != null) {
                String[] lSplit = line.split(","); // Separamos los campos de la línea
                // Creamos un objeto persona con los campos de la línea
                Persona pers = new Persona(Integer.parseInt(lSplit[0]), Integer.parseInt(lSplit[1]),
                        lSplit[2], lSplit[3], Integer.parseInt(lSplit[4]), Integer.parseInt(lSplit[5]));

                inserir(pers);// Añadimos la persona a la lista
            }
            br.close(); // Cerramos el fichero
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer " +filename);
        } catch (IOException e) {
            System.out.println("Error en la lectura del fitxer " +filename);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
