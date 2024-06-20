package cat.urv.deim;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import cat.urv.deim.exceptions.PosicioForaRang;

public class LlistaPersones {

    private ILlistaGenerica<Persona> llista;
    /** Constructor 1: lista dinamica
     * de personas vacia
     *
     * @param ordenada true -> ordenada || false -> no ordenada
     */
    public LlistaPersones(boolean ordenada) {
        if(ordenada)
            llista = new LlistaOrdenada<Persona>();
        else
            llista = new LlistaNoOrdenada<Persona>();
    }

    /** Constructor 2: lista dinamica
     * de personas con datos cargados
     *
     * @param ordenada true -> ordenada || false -> no ordenada
     * @param filename nombre del fichero a cargar
     */
    public LlistaPersones(boolean ordenada, String filename) {
        if(ordenada)
            llista = new LlistaOrdenada<Persona>();
        else
            llista = new LlistaNoOrdenada<Persona>();

        cargarDatos(filename);
    }

    /** Metodo que inserta
     * en la lista una persona.
     *
     * @param p persona a insertar
     */
    public void inserir(Persona p) { llista.inserir(p); }

    /** Metodo que consulta la
     * posicion de una persona por parametro.
     *
     * @param pos posicion de la persona
     */
    public void consultar(int pos) throws PosicioForaRang {
        llista.consultar(pos);
    }

    /** Metodo que obtiene un array
     * de personas de la lista
     */
    public Persona[] elements() {
        // Iterador de la lista de personas
        Iterator<Persona> iter = llista.iterator();
        // Array de personas
        Persona[] array = new Persona[llista.numElements()];

        // Bucle para recorrer la lista
        int i = 0;
        while (iter.hasNext()) {
            array[i] = iter.next(); // Añadimos la persona al array
            i++;
        }
        return array;
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
                // Creamos la persona con los campos de la línea
                int id = Integer.parseInt(lSplit[0]);
                int edat = Integer.parseInt(lSplit[1]);
                String nom = lSplit[2];
                String cognom = lSplit[3];
                int altura = Integer.parseInt(lSplit[4]);
                int pes = Integer.parseInt(lSplit[5]);
                Persona pers = new Persona(id, edat, nom, cognom, altura, pes);

                inserir(pers); // Añadimos la persona a la lista
            }
            br.close(); // Cerramos el fichero

        // Control de errores
        } catch (FileNotFoundException e) { // Error en la apertura del fichero
            e.printStackTrace();
        } catch (IOException e) { // Error en la lectura del fichero
            e.printStackTrace();
        } catch (Exception e) { // Algun otro error no esperado
            e.printStackTrace();
        }
    }
}
