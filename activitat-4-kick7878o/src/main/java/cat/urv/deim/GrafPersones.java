package cat.urv.deim;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.VertexNoTrobat;
import cat.urv.deim.exceptions.ArestaNoTrobada;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GrafPersones {
    private IGraf<Integer, Persona, Integer> grafPersonas;

    /** Constructor 1:
     * Crea un grafo de personas vacio con un
     *  tamaño maximo de personas
     *
     * @param size tamaño maximo de personas
     */
    public GrafPersones(int size) {
        grafPersonas = new Graf<>(size);
    }

    /** Constructor 2:
     * Crea un grafo de personas con un tamaño maximo de personas
     * y carga las personas y amistades desde los ficheros
     *
     * @param size tamaño maximo de personas
     * @param filePers fichero de personas
     * @param fileAmist fichero de amistades
     */
    public GrafPersones(int size, String filePers, String fileAmist) {
        grafPersonas = new Graf<>(size);
        cargarPersonas(filePers);
        cargarAmistades(fileAmist);
    }

    // Metodes per a guardar persones
    public void inserirPersona(Persona p) {
        grafPersonas.inserirVertex(p.getId_persona(), p);
    }

    public Persona consultarPersona(int id) throws ElementNoTrobat {
        try {
            return grafPersonas.consultarVertex(id);
        } catch (VertexNoTrobat e) {
            throw new ElementNoTrobat();
        }
    }

    public void esborrarPersona(int id) throws ElementNoTrobat {
        try {
            grafPersonas.esborrarVertex(id);
        } catch (VertexNoTrobat e) {
            throw new ElementNoTrobat();
        }
    }

    public int numPersones() { return grafPersonas.numVertex(); }

    public boolean esBuida() { return grafPersonas.esBuida(); }

    public ILlistaGenerica<Integer> obtenirPersonesIDs() {
        return grafPersonas.obtenirVertexIDs();
    }

    // Metodes per a guardar amistats

    public void inserirAmistat(Persona p1, Persona p2) throws ElementNoTrobat {
        try {
            grafPersonas.inserirAresta(p1.getId_persona(), p2.getId_persona());
        } catch (VertexNoTrobat e) {
            throw new ElementNoTrobat();
        }
    }


    public void inserirAmistat(Persona p1, Persona p2, int intensitat) throws ElementNoTrobat {
        try {
            grafPersonas.inserirAresta(p1.getId_persona(), p2.getId_persona(), intensitat);
        } catch (VertexNoTrobat e) {
            throw new ElementNoTrobat();
        }
    }

    public void esborrarAmistat(Persona p1, Persona p2) throws ElementNoTrobat {
        try {
            grafPersonas.esborrarAresta(p1.getId_persona(), p2.getId_persona());
        } catch (VertexNoTrobat | ArestaNoTrobada e) {
            throw new ElementNoTrobat();
        }
    }

    public boolean existeixAmistat(Persona p1, Persona p2) throws ElementNoTrobat {
        try {
            return grafPersonas.existeixAresta(p1.getId_persona(), p2.getId_persona());
        } catch (VertexNoTrobat e) {
            throw new ElementNoTrobat();
        }
    }

    public int intensitatAmistat(Persona p1, Persona p2) throws ElementNoTrobat {
        try {
            return grafPersonas.consultarAresta(p1.getId_persona(), p2.getId_persona());
        } catch (VertexNoTrobat | ArestaNoTrobada e) {
            throw new ElementNoTrobat();
        }
    }

    public int numAmistats() { return grafPersonas.numArestes(); }

    public int numAmistats(Persona p) throws ElementNoTrobat {
        try {
            return grafPersonas.numVeins(p.getId_persona());
        } catch (VertexNoTrobat e) {
            throw new ElementNoTrobat();
        }
    }

    public boolean teAmistats(Persona p) {
        try {
            return !grafPersonas.vertexAillat(p.getId_persona());
        } catch (VertexNoTrobat e) {
            return false;
        }
    }


    public ILlistaGenerica<Integer> obtenirAmistats(Persona p) throws ElementNoTrobat {
        try {
            return grafPersonas.obtenirVeins(p.getId_persona());
        } catch (VertexNoTrobat e) {
            throw new ElementNoTrobat();
        }
    }


////////////////////////////////////////////////////////////////////////////////////
// METODOS OPCIONALES

    // Aquest metode busca totes les persones del grup d'amistats de p que tenen alguna connexio amb p
    // ja sigui directament, o be perque son amics d'amics, o amics de amics de amics, etc.
    // Retorna una llista amb els ID de les persones del grup
    public ILlistaGenerica<Integer> obtenirGrupAmistats(Persona p) throws ElementNoTrobat {
        try {
            return grafPersonas.obtenirNodesConnectats(p.getId_persona());
        } catch (VertexNoTrobat e) {
            throw new ElementNoTrobat();
        }
    }

    // Aquest metode busca el grup d'amistats mes gran del graf, es a dir, el que te major nombre
    // de persones que estan connectades entre si. Retorna una llista amb els ID de les persones del grup
    public ILlistaGenerica<Integer> obtenirGrupAmistatsMesGran() {
        return grafPersonas.obtenirComponentConnexaMesGran();
    }


////////////////////////////////////////////////////////////////////////////////////
// METODOS ADICIONALES

    /**
     * Metodo que carga los datos de personas en la lista
     *
     * Estructura del fichero:
     *  · IDPersona,Edat,Nom,Cognom,Alçada,Pes
     *
     * @param fileName nombre del fichero
     */
    private void cargarPersonas(String fileName) {
        try {
            // Abrimos fichero para leer
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line; // Variable para leer cada línea del fichero

            // Bucle para tratar cada línea del fichero
            while ((line = br.readLine()) != null) {
                String[] lSplit = line.split(","); // Separamos los campos de la línea
                // Creamos un objeto persona con los campos de la línea
                Persona pers = new Persona(Integer.parseInt(lSplit[0]), Integer.parseInt(lSplit[1]),
                        lSplit[2], lSplit[3], Integer.parseInt(lSplit[4]), Integer.parseInt(lSplit[5]));

                inserirPersona(pers); // Añadimos la persona a la lista
            }
            br.close(); // Cerramos el fichero
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer " +fileName);
        } catch (IOException e) {
            System.out.println("Error en la lectura del fitxer " +fileName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Metodo que carga los datos del amistades en el grafo
     *
     * Estructura del fichero:
     *  · IDPersona1, IDPersona2, Intensitat
     *
     * @param fileName nombre del fichero
     */
    private void cargarAmistades(String fileName) {
        try {
            // Abrimos fichero para leer
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line; // Variable para leer cada línea del fichero

            // Bucle para tratar cada línea del fichero
            while ((line = br.readLine()) != null) {
                String[] lSplit = line.split(","); // Separamos los campos de la línea
                int ID1 = Integer.parseInt(lSplit[0]);
                int ID2 = Integer.parseInt(lSplit[1]);
                int intensitat = Integer.parseInt(lSplit[2]);

                Persona p1 = consultarPersona(ID1);
                Persona p2 = consultarPersona(ID2); // Buscamos la persona en el grafo
                inserirAmistat(p1, p2, intensitat);; // Añadimos la persona a la lista
            }
            br.close(); // Cerramos el fichero
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer " + fileName);
        } catch (IOException e) {
            System.out.println("Error en la lectura del fitxer " + fileName);
        } catch (Exception e) {
            System.out.println(e);
        }    }
}
