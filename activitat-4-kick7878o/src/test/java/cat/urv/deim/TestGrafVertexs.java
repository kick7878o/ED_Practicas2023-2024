package cat.urv.deim;

import org.junit.jupiter.api.Test;

import cat.urv.deim.exceptions.ElementNoTrobat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestGrafVertexs {

    @Test
    public void testGrafBuida1() {
        GrafPersones graf = new GrafPersones(200);
        assertTrue(graf.esBuida());
    }

    @Test
    public void testGrafBuida2() {
        GrafPersones graf = new GrafPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        assertFalse(graf.esBuida());
    }

    @Test
    public void testGrafInserirVertex1() {
        GrafPersones graf = new GrafPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        assertEquals(1, graf.numPersones());
    }


    @Test
    public void testGrafInserirVertex2() {
        GrafPersones graf = new GrafPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        Persona p2 = new Persona(9999999, 52, "Jaume", "Oliva", 95, 190);
        graf.inserirPersona(p2);
        assertEquals(1, graf.numPersones());
    }


    @Test
    public void testGrafInserirVertex3() {
        GrafPersones graf = new GrafPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        Persona p2 = new Persona(88888888, 52, "Jaume", "Oliva", 95, 190);
        graf.inserirPersona(p2);
        assertEquals(2, graf.numPersones());
    }

    @Test
    public void testGrafInserirVertex4() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", null);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        Persona p2 = new Persona(88888888, 52, "Jaume", "Oliva", 95, 190);
        graf.inserirPersona(p2);
        assertEquals(102, graf.numPersones());
    }

    @Test
    public void testGrafEsborrarVertex1() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", null);
        try {
            graf.esborrarPersona(5059905);
            assertEquals(99, graf.numPersones());
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testGrafEsborrarVertex2() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", null);
        try {
            graf.esborrarPersona(5059905);
        } catch (ElementNoTrobat e) {
            fail();
        }

        assertThrows(ElementNoTrobat.class, () -> {
            graf.esborrarPersona(5059905);
        });
    }

    @Test
    public void testGrafEsborrarVertex3() {
        GrafPersones graf = new GrafPersones(200);
        assertThrows(ElementNoTrobat.class, () -> {
            graf.esborrarPersona(5059905);
        });
    }


    @Test
    public void testGrafInserirEsborrarVertex1() {
        GrafPersones graf = new GrafPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        try {
            graf.esborrarPersona(9999999);
        } catch (ElementNoTrobat e) {
            fail();
        }
        Persona p2 = new Persona(9999999, 52, "Jaume", "Oliva", 95, 190);
        graf.inserirPersona(p2);
        assertEquals(1, graf.numPersones());
    }

    @Test
    public void testConsultarVertex1() {
        GrafPersones graf = new GrafPersones(200);
        assertThrows(ElementNoTrobat.class, () -> {
            graf.esborrarPersona(5059905);
        });
    }



    @Test
    public void testConsultarVertex2() {
        GrafPersones graf = new GrafPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        Persona p2 = new Persona(9999999, 52, "Jaume", "Oliva", 95, 190);
        graf.inserirPersona(p2);
        try {
            Persona p3 = graf.consultarPersona(9999999);
            assertEquals(52, p3.getEdat());
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testConsultarVertex3() {
        GrafPersones graf = new GrafPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        Persona p2 = new Persona(88888888, 52, "Jaume", "Oliva", 95, 190);
        graf.inserirPersona(p2);
        try {
            Persona p3 = graf.consultarPersona(9999999);
            assertEquals(47, p3.getEdat());
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testConsultarIDs1() {
        GrafPersones graf = new GrafPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        Persona p2 = new Persona(88888888, 52, "Jaume", "Oliva", 95, 190);
        graf.inserirPersona(p2);
        ILlistaGenerica<Integer> llista = graf.obtenirPersonesIDs();
        assertEquals(2, llista.numElements());
    }
}

