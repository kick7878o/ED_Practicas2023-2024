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

public class TestEstatica {

    @Test
    public void testEstaticaNumElem() {
        var main = new Main();
        TADPila pila = main.carregarFitxerEstatica("persones.csv", 200);
        assertEquals(100, pila.numElem());
    }

    @Test
    public void testEstaticaPlena1() {
        var main = new Main();
        TADPila pila = main.carregarFitxerEstatica("persones.csv", 100);
        assertTrue(pila.esPlena());
    }

    @Test
    public void testEstaticaPlena2() {
        var main = new Main();
        TADPila pila = main.carregarFitxerEstatica("persones.csv", 200);
        assertFalse(pila.esPlena());
    }

    @Test
    public void testEstaticaBuida1() {
        TADPila pila = new PilaEstatica(200);
        assertTrue(pila.esBuida());
    }

    @Test
    public void testEstaticaBuida2() {
        var main = new Main();
        TADPila pila = main.carregarFitxerEstatica("persones.csv", 200);
        assertFalse(pila.esBuida());
    }


    @Test
    public void testEstaticaApilar1() {
        var main = new Main();
        TADPila pila = main.carregarFitxerEstatica("persones.csv", 200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        try {
            pila.apilar(p);
        } catch (Exception e) {
            fail();
        }
        assertEquals(101, pila.numElem());
    }

    @Test
    public void testEstaticaApilar2() {
        var main = new Main();
        TADPila pila = main.carregarFitxerEstatica("persones.csv", 100);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        assertThrows(PilaPlena.class, () -> {
            pila.apilar(p);
        });
    }

    @Test
    public void testEstaticaApilarCim() {
        var main = new Main();

        TADPila pila = main.carregarFitxerEstatica("persones.csv", 200);
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
    public void testEstaticaCim1() {
        TADPila pila = new PilaEstatica(200);
        assertThrows(PilaBuida.class, () -> {
            pila.cim();
        });
    }

    @Test
    public void testEstaticaCim2() {
        var main = new Main();
        TADPila pila = main.carregarFitxerEstatica("persones.csv", 200);
        try {
            Persona cim = pila.cim();
            assertEquals("Jones", cim.getCognom());
        }   catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEstaticaDesapilar1() {
        var main = new Main();
        TADPila pila = main.carregarFitxerEstatica("persones.csv", 200);
        try {
            pila.desapilar();
        } catch (Exception e) {
            fail();
        }
        assertEquals(99, pila.numElem());
    }

    @Test
    public void testEstaticaDesapilar2() {
        TADPila pila = new PilaEstatica(200);
        assertThrows(PilaBuida.class, () -> {
            pila.desapilar();
        });
    }

    @Test
    public void testEstaticaDesapilarCim() {
        var main = new Main();
        TADPila pila = main.carregarFitxerEstatica("persones.csv", 200);
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
    public void testEstaticaApilarDesapilar() {
        var main = new Main();
        TADPila pila = main.carregarFitxerEstatica("persones.csv", 200);
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
    public void testEstaticaAnterior1() {
        TADPila pila = new PilaEstatica(200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        assertThrows(ElementNoTrobat.class, () -> {
            pila.anterior(p);
        });
    }

    @Test
    public void testEstaticaAnterior2() {
        TADPila pila = new PilaEstatica(200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
         try {
            pila.apilar(p);
            assertEquals(null, pila.anterior(p));
        }   catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEstaticaAnterior3() {
        var main = new Main();
        TADPila pila = main.carregarFitxerEstatica("persones.csv", 200);
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
    public void testEstaticaSeguent1() {
        var main = new Main();
        TADPila pila = main.carregarFitxerEstatica("persones.csv", 200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        assertThrows(ElementNoTrobat.class, () -> {
            pila.seguent(p);
        });
    }

    @Test
    public void testEstaticaSeguent2() {
        TADPila pila = new PilaEstatica(200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        try {
            pila.apilar(p);
            assertEquals(null, pila.seguent(p));
        }   catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testEstaticaSeguent3() {
        var main = new Main();
        TADPila pila = main.carregarFitxerEstatica("persones.csv", 200);
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
