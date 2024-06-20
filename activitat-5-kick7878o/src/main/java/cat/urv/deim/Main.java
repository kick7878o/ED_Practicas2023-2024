package cat.urv.deim;

import cat.urv.deim.EstructuraDatos.Persona;
import cat.urv.deim.EstructuraDatos.graph.Graf;
import cat.urv.deim.EstructuraDatos.hashMap.HashMapIndirecte;
import cat.urv.deim.EstructuraDatos.hashMap.IHashMap;
import cat.urv.deim.EstructuraDatos.linkedList.*;
import cat.urv.deim.exceptions.*;

import java.io.*;
public class Main {
    public Main() {}

    // Lectura des de teclado
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String cabeceraVertice; // Cabecera de los vertices
    private static Graf<Integer, Persona, Integer> grafo; // Grafo a cargar
    private static IHashMap<Integer, Integer> comunidadesExistentes; // Comunidades nuevas
    private static ILlistaGenerica<Integer> idVertices = new LlistaNoOrdenada<>();
    private static double umbralMod = 0.0015; // Umbral de mejora de la modularidad

    public static void main(String[] args) throws ElementNoTrobat {
        System.out.println("======= DETECTOR DE COMUNIDADES =======");

        try {
            System.out.print("Indica el fichero de la red a cargar: ");
            String netFilePath = reader.readLine(); // Obtenemos la ruta del archivo

            loadNetPajekFile(netFilePath); // Cargamos el grafo

            // Empezamos a contar
            long startTime = System.currentTimeMillis(); // Tiempo de inicio
            System.out.println("-- Calculando comunidades... --");
            double modularidadFinal = algoritmoLouvain(); // Aplicamos el algoritmo de Louvain
            long endTime = System.currentTimeMillis(); // Tiempo final

            storeFinalGraph(netFilePath, modularidadFinal); // Almacenamos el grafo final
            System.out.println("\n\n<<" + netFilePath + " ha tardado >> " + (endTime - startTime) + " ms\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PosicioForaRang e) {
            e.printStackTrace();
        } catch (VertexNoTrobat e) {
            e.printStackTrace();
        }
    }

///////////////////////// Carga y almacenamiento de archivos ///////////////////////////

    /** Carga des de un archivo un grafo
     * básico para realizar la detección de comunidades
     *
     * Formato fichero .net:
     *  *Vertices N
     *  IDPersona1
     *  IDPersona2
     *  ...
     *  *Edges
     *  PersonaX PersonaY
     *  PersonaX PersonaZ
     *  ...
     *
     * @param netFilePath ruta del archivo de la red
     * @param graf grafo a cargar
     */
    private static void loadNetPajekFile(String netFilePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(netFilePath));

            // Obtenemos el numero maximo de nodos del grafo
            cabeceraVertice = br.readLine().trim();
            int numNodos = Integer.parseInt(cabeceraVertice.split(" ")[1]);

            grafo = new Graf<>(numNodos); // Creamos el grafo con el numero de nodos
            comunidadesExistentes = new HashMapIndirecte<>(numNodos); // Creamos el mapa de comunidades

            String line; // Variable para leer las lineas del fichero
            boolean seccionAristas = false; // Variable para saber si estamos en las aristas

            // Bucle para recorrer el fichero
            int comunidad = 1; // Comunidad inicial
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Eliminamos espacios en blanco al final

                // Miramos si empezamos las aristas
                if (line.startsWith("*Edges")) {
                    seccionAristas = true;
                    line = br.readLine().trim(); // Saltamos la linea de cabecera
                }

                if (seccionAristas) { // Aqui estamos en las aristas
                    String[] aristas = line.split("\\s+"); // Separamos las aristas
                    int v1 = Integer.parseInt(aristas[0]); // Obtenemos el vertice 1
                    int v2 = Integer.parseInt(aristas[1]); // Obtenemos el vertice 2
                    grafo.inserirAresta(v1, v2); // Insertamos la arista
                } else { // Aquí seguimos en los vertices
                    String[] vertices = line.split("\\s+"); // Separamos los vertices
                    int id = Integer.parseInt(vertices[0].trim()); // Obtenemos el identificador
                    Persona newVertice = new Persona(id, comunidad); // Creamos un nuevo vertice
                    grafo.inserirVertex(id, newVertice); // Insertamos el vertice
                    //comunidadesExistentes.inserir(comunidad); // Insertamos la comunidad
                    comunidadesExistentes.inserir(comunidad, comunidad);
                    comunidad++; // Aumentamos la comunidad
                }
            }
            idVertices = grafo.obtenirVertexIDs(); // Obtenemos los vertices
            br.close(); // Cerramos el fichero
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Almacena el grafo final en un archivo
     * con la extensión .clu en la que se indicará
     * sólo la comunidad a la que pertenece cada vertice.
     *
     * Formato fichero .clu:
     * *Vertices N
     *  Comunidad de la persona 1
     *  Comunidad de la persona 2
     *  ...
     *
     * @param storeFile ruta del archivo a almacenar
     * @param modularidad valor de la modularidad
     * @throws PosicioForaRang
     */
    private static void storeFinalGraph(String storeFile, double modularidad) throws PosicioForaRang {
        try {
            // Obtenemos el nombre del archivo sin la extensión
            String baseName = storeFile.substring(0, storeFile.lastIndexOf("."));

            // Creamos el nuevo nombre del archivo. Formato: nombreBase_modularidad.clu
            String newFileName = baseName + "_" + String.format("%.6f", modularidad) + ".clu";

            BufferedWriter bw = new BufferedWriter(new FileWriter(newFileName));
            bw.write(cabeceraVertice); // Escribimos la cabecera *Vertices N
            bw.newLine(); // Salto de linea

            // Iteramos sobre los vertices
            for (int i = 0; i < idVertices.numElements(); i++) {
                Integer nodo = idVertices.consultar(i); // Obtenemos el identificador del nodo
                Persona nodoPersona = grafo.consultarVertex(nodo); // Obtenemos el objeto Persona
                bw.write(String.valueOf(nodoPersona.getComunidad())); // Escribimos la comunidad
                bw.newLine(); // Salto de linea
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (VertexNoTrobat e) {
            e.printStackTrace();
        }
    }

///////////////////////// Algoritmo de Louvain ///////////////////////////

    /** Metodo que aplica el algoritmo de Louvain
     *
     * Maximizamos el número de enlaces dentro de una comunidad y
     * minimizamos el número de enlaces entre comunidades.
     *
     * Dos fases:
     *  - Fase de Agrupamiento:
     *      · Se asigna a su propia comunidad
     *      · Se mueve a la comunidad que maximiza la modularidad
     *  - Fase de refinamiento: se guarda la partición y se repite el proceso
     *      · Construir nueva red cuyos nodos son las encontradas en la primera fase
     * @throws ElementNoTrobat
     */
    private static double algoritmoLouvain() throws PosicioForaRang, VertexNoTrobat, ElementNoTrobat {
        double modularidadActual = calcularModularidad(); // Calculamos la modularidad actual
        double modularidadAnterior = Double.NEGATIVE_INFINITY; // Inicializamos la modularidad anterior
        boolean haMejorado = true; // Variable para saber si ha mejorado la modularidad

        do {
            haMejorado = false;

            // Fase de Agrupamiento
            for (int i = 0; i < idVertices.numElements(); i++) {
                long startTime = System.currentTimeMillis(); // Tiempo de inicio
                Integer nodoID = idVertices.consultar(i); // Obtenemos el identificador del grafo
                System.out.println("\n- Fase de cambio de la persona " + nodoID + " de " +idVertices.numElements());
                Persona comunidadActual = grafo.consultarVertex(nodoID); // Obtenemos el objeto Persona

                // Iteramos sobre las comunidades existentes actualmente [1..N]
                for (int j = 0; j < comunidadesExistentes.numElements(); j++) {
                    Integer nuevaComunidad = comunidadesExistentes.consultar(j+1);

                    // Si la nueva comunidad es distinta a la actual
                    if (nuevaComunidad != comunidadActual.getComunidad()) {
                        // Miramos si al cambiar de comunidad se mejora la modularidad
                        double gananciaModNueva = calcularGananciaModularidad(nodoID, nuevaComunidad);

                        // Si se mejora la modularidad le cambiamos de comunidad
                        if (gananciaModNueva > umbralMod) {
                            moverNodoAComunidad(nodoID, nuevaComunidad);
                            haMejorado = true;
                        }
                    }
                }
                long endTime = System.currentTimeMillis(); // Tiempo final
                System.out.println("<< Persona " +nodoID+ " ha tardado >> " + (endTime - startTime) + " ms");
            }

            // Fase de Refinamiento
            if (haMejorado) { // Si hay mejora en la modularidad
                modularidadAnterior = modularidadActual; // Guardamos la modularidad anterior
                modularidadActual = calcularModularidad(); // Calculamos la modularidad actual

                // Miramos si se ha superado el umbral de mejora
                if ((modularidadActual - modularidadAnterior < umbralMod))
                    haMejorado = false;
                else
                    System.out.println("\n\n\n====== EMPEZAMOS DE NUEVO ======\n");
            }
        } while (haMejorado == true); // Repetimos el proceso mientras haya mejora en la modularidad

        return modularidadActual;
    }

///////////////////////// Cálculo de la modularidad ///////////////////////////

    /**
     * Calcular la modularidad actual del grafo
     * en función de las comunidades actuales
     *
     * Modularidad: diferencia entre el número de enlaces dentro de una comunidad.
     *
     * Formula (la primera formula que aparece en el enlace):
     *  M = (1/2L) * sumatoria [ (Aij - (ki*kj/2L)) * delta(ci, cj) ]
     *      L: número total de enlaces del grafo.
     *      Sumatoria Aij: numero de enlaces existentes dentro de una comunidad.
     *      ki*kj/2L: probabilidad de que un enlace exista entre dos nodos.
     *      delta(ci, cj): 1 si los nodos pertenecen a la misma comunidad, 0 en caso contrario
     *
     * @return valor de la modularidad
     * @throws PosicioForaRang error de posición fuera de rango
     * @throws VertexNoTrobat  error de vertice no encontrado
     * @throws ElementNoTrobat
     */
    private static double calcularModularidad() throws PosicioForaRang, VertexNoTrobat, ElementNoTrobat {
        double modularidad = 0.0;
        double totalAristas = grafo.numArestes();

        // Iteramos sobre las diferentes comunidades
        for (int i = 0; i < comunidadesExistentes.numElements(); i++) {
            Integer comunidad = comunidadesExistentes.consultar(i+1); // Obtenemos una comunidad en particular para analizarla
            double sumaIntraComunidad = 0.0; // Suma de aristas dentro de la comunidad
            double sumaGradosComunidad = 0.0; // Suma de grados de las Personas de la comunidad

            // Obtenemos las Personas de una comunidad en concreto
            ILlistaGenerica<Persona> nodosComunidad = obtenerNodosComunidad(comunidad);

            // Iteramos sobre las Personas de una comunidad concreta
            for (int j = 0; j < nodosComunidad.numElements(); j++) {
                Integer nodo = nodosComunidad.consultar(j).getIdentificador(); // Obtenemos la Persona
                // Obtenemos los vecinos de la Persona
                ILlistaGenerica<Integer> listavecinos = grafo.obtenirVeins(nodo);

                // Iteramos sobre los vecinos de una Persona en concreto
                for (int k = 0; k < listavecinos.numElements(); k++) {
                    Integer vecino = listavecinos.consultar(k); // Obtenemos el identificador del vecino
                    Persona nodoVecino = grafo.consultarVertex(vecino); // Obtenemos el objeto Persona del vecino
                    if (nodosComunidad.existeix(nodoVecino) == true)
                        sumaIntraComunidad += 1; // Si el vecino pertenece a la comunidad sumamos 1
                }
            }

            // Calculamos la suma de aristas dentro de la comunidad
            // (dividimos entre 2 porque se cuentan dos veces)
            sumaIntraComunidad = sumaIntraComunidad / 2;

            // Calculamos la suma de grados de los nodos de la comunidad
            for (int j = 0; j < nodosComunidad.numElements(); j++) {
                // Obtenemos el identificador de la Persona
                Integer idPersona = nodosComunidad.consultar(j).getIdentificador();
                // Sumamos los grados de la Persona
                sumaGradosComunidad += grafo.numVeins(idPersona);
            }

            // Aplicamos la formula
            modularidad += (sumaIntraComunidad / totalAristas) -
                    Math.pow((sumaGradosComunidad / (2 * totalAristas)), 2);
        }
        return modularidad;
    }

    /** Método que calcula la ganancia de modularidad
     * al mover un nodo a una nueva comunidad.
     *
     * @param nodoID identificador del nodo
     * @param nuevaComunidad nueva comunidad a la que mover el nodo
     * @return ganancia de modularidad
     * @throws ElementNoTrobat
     */
    private static double calcularGananciaModularidad(Integer nodoID, Integer nuevaComunidad) throws VertexNoTrobat, PosicioForaRang, ElementNoTrobat {
        double gananciaMod = 0.0;

        // Obtenemos la comunidad actual del nodo
        Persona comActual = grafo.consultarVertex(nodoID);

        // Calculamos la modularidad actual
        double modActual = calcularModularidad();

        // Movemos el nodo a la nueva comunidad
        moverNodoAComunidad(nodoID, nuevaComunidad);

        // Calculamos la modularidad con el nodo en la nueva comunidad
        double modNueva = calcularModularidad();

        // Calculamos la ganancia de modularidad
        gananciaMod = modNueva - modActual;

        // Movemos el nodo a la comunidad original
        moverNodoAComunidad(nodoID, comActual.getComunidad());

        return gananciaMod;
    }


///////////////////////// Obtención de las comunidades ///////////////////////////

    /** Obtenemos las personas
     * que pertenecen a una comunidad
     *
     * @param comunidad comunidad a obtener
     * @return lista con las personas de la comunidad
     */
    private static ILlistaGenerica<Persona> obtenerNodosComunidad(Integer comunidad)
            throws PosicioForaRang, VertexNoTrobat {
        ILlistaGenerica<Persona> nodosComunidad = new LlistaNoOrdenada<>();

        // Iteramos sobre cada vertice del grafo
        for (int i = 0; i < idVertices.numElements(); i++) {
            Integer nodo = idVertices.consultar(i); // Obtenemos el identificador del nodo
            Persona comunidadNodo = grafo.consultarVertex(nodo); // Obtenemos el objeto Persona
            if (comunidadNodo.getComunidad() == comunidad)
                nodosComunidad.inserir(comunidadNodo); // Si pertenece a la comunidad la añadimos
        }

        return nodosComunidad;
    }

    /** Metodo que cambia la comunidad de un nodo en particular
     *
     * @param nodoID identificador del nodo a cambiar
     * @param comActual comunidad a pasar
     */
    private static void moverNodoAComunidad(Integer nodoID, Integer comActual) {
        grafo.actualitzarValor(nodoID, new Persona(nodoID, comActual));
    }
}
