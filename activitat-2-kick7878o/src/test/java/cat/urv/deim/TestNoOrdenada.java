package cat.urv.deim;

import org.junit.jupiter.api.Test;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PosicioForaRang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestNoOrdenada {

     @Test
    public void testNoOrdenadaInserir1() {
        LlistaPersones ll = new LlistaPersones(false);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        ll.inserir(p);
        assertEquals(1, ll.numElements());
    }

    @Test
    public void testNoOrdenadaInserir2() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        ll.inserir(p);
        assertEquals(101, ll.numElements());
    }

    @Test
    public void testNoOrdenadaInserirBuscar() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        ll.inserir(p);
        try {
            int posicio = ll.posicioPersona(p);
            System.out.println(posicio);
            assertTrue(posicio >= 0);
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testNoOrdenadaEsborrar1() {
        LlistaPersones ll = new LlistaPersones(false);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        assertThrows(ElementNoTrobat.class, () -> {
            ll.esborrar(p);
        });
    }

    @Test
    public void testNoOrdenadaEsborrar2() {
        LlistaPersones ll = new LlistaPersones(false);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        ll.inserir(p);
        try {
            ll.esborrar(p);
        } catch (ElementNoTrobat e) {
            fail();
        }
        assertThrows(ElementNoTrobat.class, () -> {
            ll.esborrar(p);
        });
    }

    @Test
    public void testNoOrdenadaEsborrar3() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");

        try {
            Persona p = ll.consultar(25);
            ll.esborrar(p);

            assertThrows(ElementNoTrobat.class, () -> {
                ll.esborrar(p);
            });
        } catch (ElementNoTrobat e) {
            fail();
        } catch (PosicioForaRang e) {
            fail();
        }
    }

    @Test
    public void testNoOrdenadaExisteix1() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        assertFalse(ll.existeix(p));
    }

    @Test
    public void testNoOrdenadaExisteix2() {
        LlistaPersones ll = new LlistaPersones(false);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        ll.inserir(p);
        assertTrue(ll.existeix(p));
    }

    @Test
    public void testNoOrdenadaConsultar1() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");
        assertThrows(PosicioForaRang.class, () -> {
            ll.consultar(200);
        });
    }

    @Test
    public void testNoOrdenadaConsultar2() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");
        assertThrows(PosicioForaRang.class, () -> {
            ll.consultar(-1);
        });
    }

    @Test
    public void testNoOrdenadaConsultar3() {
        LlistaPersones ll = new LlistaPersones(false);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        ll.inserir(p);
        try {
            Persona p2 = ll.consultar(0);
            assertEquals(p, p2);
        } catch (PosicioForaRang e) {
            fail();
        }
    }

    @Test
    public void testNoOrdenadaPosicioPersona1() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");
        try {
            Persona p2 = ll.consultar(25);
            int posicio = ll.posicioPersona(p2);
            assertEquals(posicio, 25);
        } catch (PosicioForaRang e) {
            fail();
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testNoOrdenadaBuscarPersona1() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");
        try {
            Persona p = ll.consultar(25);
            int idP = p.getId_persona();
            Persona p2 = ll.buscarPerId(idP);
            assertEquals(p, p2);
        } catch (PosicioForaRang e) {
            fail();
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testNoOrdenadaEsBuida1() {
        LlistaPersones ll = new LlistaPersones(false);
        assertTrue(ll.esBuida());
    }

    @Test
    public void testNoOrdenadaEsBuida2() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");
        assertFalse(ll.esBuida());
    }

    @Test
    public void testNoOrdenadaNumElem() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");
        assertEquals(100, ll.numElements());
    }

    @Test
    public void testNoOrdenadaElements1() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");
        Persona p[] = ll.elements();
        assertEquals(100, p.length);
    }

    @Test
    public void testNoOrdenadaElements2() {
        LlistaPersones ll = new LlistaPersones(false);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        ll.inserir(p);
        Persona llp[] = ll.elements();
        assertTrue(p.equals(llp[0]));
    }

    @Test
    public void testNoOrdenadaPes1() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");
        Persona p[] = ll.personesPesInferior(80);
        assertEquals(59, p.length);
    }

    @Test
    public void testNoOrdenadaPes2() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");
        Persona p[] = ll.personesPesInferior(60);
        assertEquals(9, p.length);
    }
}
