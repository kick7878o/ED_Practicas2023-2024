package cat.urv.deim;

import cat.urv.deim.exceptions.PilaPlena;
import java.io.*;

public class Main {
    TADPila persones;

    public Main() {}

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
                persones.apilar(pers); // Añadimos la persona a la pila
            }
            br.close(); // Cerramos el fichero
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer " +filename);
        } catch (IOException e) {
            System.out.println("Error en la lectura del fitxer " +filename);
        } catch (PilaPlena e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Metode per carregar les dades en una pila estatica
    public TADPila carregarFitxerEstatica(String filename, int maxElem) {
        persones = new PilaEstatica(maxElem);
        carregarDades(filename);
        return persones;
    }

    // Metode per carregar les dades en una pila dinamica
    public TADPila carregarFitxerDinamica(String filename, int maxElem) {
        persones = new PilaDinamica(maxElem);
        carregarDades(filename);
        return persones;
    }

    // Metode per carregar les dades en una pila java.util.stack
    public TADPila carregarFitxerStack(String filename, int maxElem) {
        persones = new PilaStack(maxElem);
        carregarDades(filename);
        return persones;
    }

    public static void main(String[] args) {
        System.out.println("No cal que executis aixo, mira els tests!");
    }
}
