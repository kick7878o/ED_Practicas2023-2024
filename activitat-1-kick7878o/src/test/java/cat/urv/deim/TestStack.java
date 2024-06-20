package cat.urv.deim;

import org.junit.jupiter.api.Test;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PilaPlena;
import cat.urv.deim.exceptions.PilaBuida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestStack {

    @Test
    public void testStackNumElem() {
        var main = new Main();
        TADPila pila = main.carregarFitxerStack("persones.csv", 200);
        assertEquals(100, pila.numElem());
    }

    @Test
    public void testStackPlena1() {
        var main = new Main();
        TADPila pila = main.carregarFitxerStack("persones.csv", 100);
        assertTrue(pila.esPlena());
    }

    @Test
    public void testStackPlena2() {
        var main = new Main();
        TADPila pila = main.carregarFitxerStack("persones.csv", 200);
        assertFalse(pila.esPlena());
    }

    @Test
    public void testStackBuida1() {
        TADPila pila = new PilaStack(200);
        assertTrue(pila.esBuida());
    }

    @Test
    public void testStackBuida2() {
        var main = new Main();
        TADPila pila = main.carregarFitxerStack("persones.csv", 200);
        assertFalse(pila.esBuida());
    }


    @Test
    public void testStackApilar1() {
        var main = new Main();
        TADPila pila = main.carregarFitxerStack("persones.csv", 200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        try {
            pila.apilar(p);
        } catch (Exception e) {
            fail();
        }
        assertEquals(101, pila.numElem());
    }

    @Test
    public void testStackApilar2() {
        var main = new Main();
        TADPila pila = main.carregarFitxerStack("persones.csv", 100);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        assertThrows(PilaPlena.class, () -> {
            pila.apilar(p);
        });
    }

    @Test
    public void testStackApilarCim() {
        var main = new Main();

        TADPila pila = main.carregarFitxerStack("persones.csv", 200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        try {
            pila.apilar(p);
        } catch (Exception e) {
            fail();
        }
        try {
            Persona cim = pila.cim();
            assertEquals("Guardiola", cim.getCognom());
        }   catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testStackCim1() {
        TADPila pila = new PilaStack(200);
        assertThrows(PilaBuida.class, () -> {
            pila.cim();
        });
    }

    @Test
    public void testStackCim2() {
        var main = new Main();
        TADPila pila = main.carregarFitxerStack("persones.csv", 200);
        try {
            Persona cim = pila.cim();
            assertEquals("Jones", cim.getCognom());
        }   catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testStackDesapilar1() {
        var main = new Main();
        TADPila pila = main.carregarFitxerStack("persones.csv", 200);
        try {
            pila.desapilar();
        } catch (Exception e) {
            fail();
        }
        assertEquals(99, pila.numElem());
    }

    @Test
    public void testStackDesapilar2() {
        TADPila pila = new PilaStack(200);
        assertThrows(PilaBuida.class, () -> {
            pila.desapilar();
        });
    }

    @Test
    public void testStackDesapilarCim() {
        var main = new Main();
        TADPila pila = main.carregarFitxerStack("persones.csv", 200);
        try {
            pila.desapilar();
        } catch (Exception e) {
            fail();
        }
        try {
            Persona cim = pila.cim();
            assertEquals("Cummings", cim.getCognom());
        }   catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testStackApilarDesapilar() {
        var main = new Main();
        TADPila pila = main.carregarFitxerStack("persones.csv", 200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        try {
            pila.apilar(p);
        } catch (Exception e) {
            fail();
        }
        try {
            pila.desapilar();
        } catch (Exception e) {
            fail();
        }
        try {
            Persona cim = pila.cim();
            assertEquals("Jones", cim.getCognom());
        }   catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testStackAnterior1() {
        TADPila pila = new PilaStack(200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        assertThrows(ElementNoTrobat.class, () -> {
            pila.anterior(p);
        });
    }

    @Test
    public void testStackAnterior2() {
        TADPila pila = new PilaStack(200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
         try {
            pila.apilar(p);
            assertEquals(null, pila.anterior(p));
        }   catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testStackAnterior3() {
        var main = new Main();
        TADPila pila = main.carregarFitxerStack("persones.csv", 200);
        try {
            Persona p1 = pila.cim();
            Persona p2 = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
            pila.apilar(p2);
            assertEquals("Cummings", pila.anterior(p1).getCognom());
        }   catch (Exception e) {
            fail();
        }
    }


    @Test
    public void testStackSeguent1() {
        var main = new Main();
        TADPila pila = main.carregarFitxerStack("persones.csv", 200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        assertThrows(ElementNoTrobat.class, () -> {
            pila.seguent(p);
        });
    }

    @Test
    public void testStackSeguent2() {
        TADPila pila = new PilaStack(200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        try {
            pila.apilar(p);
            assertEquals(null, pila.seguent(p));
        }   catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testStackSeguent3() {
        var main = new Main();
        TADPila pila = main.carregarFitxerStack("persones.csv", 200);
        try {
            Persona p1 = pila.cim();
            Persona p2 = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
            pila.apilar(p2);
            assertEquals("Guardiola", pila.seguent(p1).getCognom());
        }   catch (Exception e) {
            fail();
        }
    }






}
