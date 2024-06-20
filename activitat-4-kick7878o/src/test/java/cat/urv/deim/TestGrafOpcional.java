package cat.urv.deim;

import org.junit.jupiter.api.Test;

import cat.urv.deim.exceptions.ElementNoTrobat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestGrafOpcional {

    @Test
    public void testComponentConnexa1() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats1.csv");
        try {
            Persona p = graf.consultarPersona(5950090);
            ILlistaGenerica<Integer> llista = graf.obtenirGrupAmistats(p);
            assertEquals(79, llista.numElements());
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testComponentConnexa2() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats3.csv");
        try {
            Persona p = graf.consultarPersona(5950090);
            ILlistaGenerica<Integer> llista = graf.obtenirGrupAmistats(p);
            assertEquals(6, llista.numElements());
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testComponentConnexa3() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats3.csv");
        try {
            Persona p = graf.consultarPersona(8162119);
            ILlistaGenerica<Integer> llista = graf.obtenirGrupAmistats(p);
            assertEquals(10, llista.numElements());
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testComponentConnexa4() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats3.csv");
        try {
            Persona p = graf.consultarPersona(7300564);
            ILlistaGenerica<Integer> llista = graf.obtenirGrupAmistats(p);
            assertEquals(2, llista.numElements());
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testComponentConnexa5() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats3.csv");
        try {
            Persona p = graf.consultarPersona(7300564);
            ILlistaGenerica<Integer> llista = graf.obtenirGrupAmistats(p);
            assertTrue(llista.existeix(6921422));
        } catch (ElementNoTrobat e) {
            fail();
        }
    }


    @Test
    public void testGiantComponent1() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats1.csv");
        ILlistaGenerica<Integer> llista = graf.obtenirGrupAmistatsMesGran();
        assertEquals(79, llista.numElements());
    }

    @Test
    public void testGiantComponent2() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        ILlistaGenerica<Integer> llista = graf.obtenirGrupAmistatsMesGran();
        assertEquals(100, llista.numElements());
    }

    @Test
    public void testGiantComponent3() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats3.csv");
        ILlistaGenerica<Integer> llista = graf.obtenirGrupAmistatsMesGran();
        assertEquals(16, llista.numElements());
    }

}

