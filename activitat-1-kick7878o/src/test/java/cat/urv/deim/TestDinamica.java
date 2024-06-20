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

public class TestDinamica {

    @Test
    public void testDinamicaNumElem() {
        var main = new Main();
        TADPila pila = main.carregarFitxerDinamica("persones.csv", 200);
        assertEquals(100, pila.numElem());
    }

    @Test
    public void testDinamicaPlena1() {
        var main = new Main();
        TADPila pila = main.carregarFitxerDinamica("persones.csv", 100);
        assertTrue(pila.esPlena());
    }

    @Test
    public void testDinamicaPlena2() {
        var main = new Main();
        TADPila pila = main.carregarFitxerDinamica("persones.csv", 200);
        assertFalse(pila.esPlena());
    }

    @Test
    public void testDinamicaBuida1() {
        TADPila pila = new PilaDinamica(200);
        assertTrue(pila.esBuida());
    }

    @Test
    public void testDinamicaBuida2() {
        var main = new Main();
        TADPila pila = main.carregarFitxerDinamica("persones.csv", 200);
        assertFalse(pila.esBuida());
    }


    @Test
    public void testDinamicaApilar1() {
        var main = new Main();
        TADPila pila = main.carregarFitxerDinamica("persones.csv", 200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        try {
            pila.apilar(p);
        } catch (Exception e) {
            fail();
        }
        assertEquals(101, pila.numElem());
    }

    @Test
    public void testDinamicaApilar2() {
        var main = new Main();
        TADPila pila = main.carregarFitxerDinamica("persones.csv", 100);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        assertThrows(PilaPlena.class, () -> {
            pila.apilar(p);
        });
    }

    @Test
    public void testDinamicaApilarCim() {
        var main = new Main();

        TADPila pila = main.carregarFitxerDinamica("persones.csv", 200);
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
    public void testDinamicaCim1() {
        TADPila pila = new PilaDinamica(200);
        assertThrows(PilaBuida.class, () -> {
            pila.cim();
        });
    }

    @Test
    public void testDinamicaCim2() {
        var main = new Main();
        TADPila pila = main.carregarFitxerDinamica("persones.csv", 200);
        try {
            Persona cim = pila.cim();
            assertEquals("Jones", cim.getCognom());
        }   catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDinamicaDesapilar1() {
        var main = new Main();
        TADPila pila = main.carregarFitxerDinamica("persones.csv", 200);
        try {
            pila.desapilar();
        } catch (Exception e) {
            fail();
        }
        assertEquals(99, pila.numElem());
    }

    @Test
    public void testDinamicaDesapilar2() {
        TADPila pila = new PilaDinamica(200);
        assertThrows(PilaBuida.class, () -> {
            pila.desapilar();
        });
    }

    @Test
    public void testDinamicaDesapilarCim() {
        var main = new Main();
        TADPila pila = main.carregarFitxerDinamica("persones.csv", 200);
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
    public void testDinamicaApilarDesapilar() {
        var main = new Main();
        TADPila pila = main.carregarFitxerDinamica("persones.csv", 200);
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
    public void testDinamicaAnterior1() {
        TADPila pila = new PilaDinamica(200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        assertThrows(ElementNoTrobat.class, () -> {
            pila.anterior(p);
        });
    }

    @Test
    public void testDinamicaAnterior2() {
        TADPila pila = new PilaDinamica(200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
         try {
            pila.apilar(p);
            assertEquals(null, pila.anterior(p));
        }   catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDinamicaAnterior3() {
        var main = new Main();
        TADPila pila = main.carregarFitxerDinamica("persones.csv", 200);
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
    public void testDinamicaSeguent1() {
        var main = new Main();
        TADPila pila = main.carregarFitxerDinamica("persones.csv", 2);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        assertThrows(ElementNoTrobat.class, () -> {
            pila.seguent(p);
        });
    }

    @Test
    public void testDinamicaSeguent2() {
        TADPila pila = new PilaDinamica(200);
        Persona p = new Persona(9999999, 52, "Pep", "Guardiola", 80, 188);
        try {
            pila.apilar(p);
            assertEquals(null, pila.seguent(p));
        }   catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testDinamicaSeguent3() {
        var main = new Main();
        TADPila pila = main.carregarFitxerDinamica("persones.csv", 200);
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
