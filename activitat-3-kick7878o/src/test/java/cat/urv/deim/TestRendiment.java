package cat.urv.deim;

import org.junit.jupiter.api.Test;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PosicioForaRang;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class TestRendiment {


    @Test
    public void testRendiment1() {

        //Creem 1 milio de persones
        ArrayList<Persona> persones = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            Persona p = new Persona(i, 47, "James", "Bond", 80, 188);
            persones.add(p);
        }


        HashMapPersones hash = new HashMapPersones(20000);
        //capturem el temps d'inici
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            hash.inserir(persones.get(i));
        }
        //capturem el temps final
        long endTime1 = System.currentTimeMillis();
        System.out.println("Temps d'inserció de 10000 de persones (HASH): " + (endTime1 - startTime1) + "ms");


        LlistaPersones persones2 = new LlistaPersones(true);
        //capturem el temps d'inici
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            persones2.inserir(persones.get(i));
        }
        //capturem el temps final
        long endTime2 = System.currentTimeMillis();
        System.out.println("Temps d'inserció de 10000 de persones (LLISTA): " + (endTime2 - startTime2) + "ms");

        assertTrue((endTime1 - startTime1) < (endTime2 - startTime2));


        //capturem el temps d'inici
        long startTime3 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            try {
                hash.consultar(i);
            } catch (ElementNoTrobat e) {
                e.printStackTrace();
            }
        }
        //capturem el temps final
        long endTime3 = System.currentTimeMillis();
        System.out.println("Temps de consulta de 10000 persones (HASH): " + (endTime3 - startTime3) + "ms");


        //capturem el temps d'inici
        long startTime4 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            try {
                persones2.consultar(i);
            } catch (PosicioForaRang e) {
                e.printStackTrace();
            }
        }
        //capturem el temps final
        long endTime4 = System.currentTimeMillis();
        System.out.println("Temps de consulta de 10000 persones (LLISTA): " + (endTime4 - startTime4) + "ms");

        assertTrue((endTime3 - startTime3) < (endTime4 - startTime4));



        //capturem el temps d'inici
        long startTime5 = System.currentTimeMillis();
        Persona p[] = hash.elements();
        //capturem el temps final
        long endTime5 = System.currentTimeMillis();
        System.out.println("Temps de recuperació de 10000 persones (HASH): " + (endTime5 - startTime5) + "ms");


        //capturem el temps d'inici
        long startTime6 = System.currentTimeMillis();
        Persona p2[] = persones2.elements();
        //capturem el temps final
        long endTime6 = System.currentTimeMillis();
        System.out.println("Temps de recuperació de 10000 persones (LLISTA): " + (endTime6 - startTime6) + "ms");

        assertTrue((endTime5 - startTime5) > (endTime6 - startTime6));

    }


}
