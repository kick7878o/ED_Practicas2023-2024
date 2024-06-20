package cat.urv.deim;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PosicioForaRang;
import java.io.*;

public class LlistaPersones {
    private ILlistaGenerica<Persona> llista;

    // Constructor que crea una llista de persones buida del tipus especificat en el boolea (ordenada o no)
    public LlistaPersones(boolean ordenada) {
        if (ordenada) // Caso ORDENADO
            llista = new LlistaOrdenada<Persona>();
        else // Caso NO ORDENADO
            llista = new LlistaNoOrdenada<Persona>();
    }

    //Constructor que crea una llista del tipus especificat i hi carrega totes les dades del fitxer
    public LlistaPersones(boolean ordenada, String filename) {
        if (ordenada) // Caso ORDENADO
            llista = new LlistaOrdenada<Persona>();
        else // Caso NO ORDENADO
            llista = new LlistaNoOrdenada<Persona>();

        carregarDades(filename); // Cargamos la lista
    }

    //Afegeix una nova persona a la llista que tenim inicialitzada
    public void inserir(Persona p) { llista.inserir(p); }

    // Metode per a esborrar una persona de l'estructura
    public void esborrar(Persona e) throws ElementNoTrobat {
        llista.esborrar(e);
    }

    //Metode per a consultar una persona a partir de la seva posicio
    public Persona consultar(int pos) throws PosicioForaRang {
        return llista.consultar(pos);
    }

    //Metode per a saber si una persona existeix a l'estructura
    public boolean existeix(Persona p) { return llista.existeix(p); }

    //Metode que ens indica en quina posicio de la llista hi ha la persona que es passa per parametre
    public int posicioPersona(Persona persona) throws ElementNoTrobat {
        return llista.buscar(persona);
    }

    //Metode per a saber si la llista esta buida
    public boolean esBuida() { return llista.esBuida(); }

    //Metode per a saber el nombre d'elements de la llista
    public int numElements() { return llista.numElements(); }

    //Metode per a obtenir un array amb tots els elements de la llista
    public Persona[] elements() {
        // Obtenemos el array de elementos de la lista
        Object[] elements = llista.elements();
        // Creamos el array de personas con el tamaño del Object
        Persona[] persones = new Persona[elements.length];

        // Pasamos de Object a Persona
        for (int i=0; i < elements.length; i++) {
            persones[i] = (Persona)elements[i];
        }
        return persones;
    }

    //Metode per a obtenir una persona a partir del seu id
    public Persona buscarPerId(int id) throws ElementNoTrobat {
        Persona[] pers = elements(); // Obtenemos el array de personas

        // Iteramos sobre el array
        for (int i=0; i < pers.length; i++) {
            if (pers[i].getId_persona() == id)
                return pers[i]; // Si encontramos devolvemos la persona
        }
        throw new ElementNoTrobat(); // Caso no encontrado
    }

    // Metode per a obtenir un array amb totes les persones que tenen un pes
    //  inferior al valor que es passa per parametre
    public Persona[] personesPesInferior(int pes) {
        Persona[] pers = elements(); // Obtenemos el array de personas

        // Obtenemos el tamaño del array de personas que cumplen la condición
        int count = 0;
        for (int i = 0; i < pers.length; i++) {
            if (pers[i].getPes() < pes)
                count++;
        }

        // Creamos un array de persona con el tamaño correcto
        Persona[] personesPesInferior = new Persona[count];

        // Llenamos el array con las personas correspondientes
        int j = 0; // Indice para el array de personas
        for (int i = 0; i < pers.length; i++) {
            if (pers[i].getPes() < pes) { // Si cumple la condición
                personesPesInferior[j] = pers[i]; // Añadimos la persona al array
                j++;
            }
        }
        return personesPesInferior;
    }

// METODOS ADICIONALES

    /**
     * Metodo que carga los datos del fichero en la lista
     * Estructura del fichero:
     *  - IDPersona,Edat,Nom,Cognom,Alçada,Pes
     *
     * @param filename nombre del fichero
     */
    private void carregarDades(String filename) {
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

                inserir(pers); // Añadimos la persona a la lista
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
