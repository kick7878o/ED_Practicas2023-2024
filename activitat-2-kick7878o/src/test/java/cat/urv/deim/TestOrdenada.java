package cat.urv.deim;

import org.junit.jupiter.api.Test;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PosicioForaRang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestOrdenada {

     @Test
    public void testOrdenadaInserir1() {
        LlistaPersones ll = new LlistaPersones(true);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        ll.inserir(p);
        assertEquals(1, ll.numElements());
    }

    @Test
    public void testOrdenadaInserir2() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        ll.inserir(p);
        assertEquals(101, ll.numElements());
    }

    @Test
    public void testOrdenadaInserirBuscar1() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        ll.inserir(p);
        try {
            int posicio = ll.posicioPersona(p);
            assertEquals(posicio,10);
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testOrdenadaInserirBuscar2() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");
        try {
            Persona p = ll.buscarPerId(9621544);
            int posicio = ll.posicioPersona(p);
            assertEquals(posicio,46);
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testOrdenadaEsborrar1() {
        LlistaPersones ll = new LlistaPersones(true);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        assertThrows(ElementNoTrobat.class, () -> {
            ll.esborrar(p);
        });
    }

    @Test
    public void testOrdenadaEsborrar2() {
        LlistaPersones ll = new LlistaPersones(true);
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
    public void testOrdenadaEsborrar3() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");

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
    public void testOrdenadaExisteix1() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        assertFalse(ll.existeix(p));
    }

    @Test
    public void testOrdenadaExisteix2() {
        LlistaPersones ll = new LlistaPersones(true);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        ll.inserir(p);
        assertTrue(ll.existeix(p));
    }

    @Test
    public void testOrdenadaConsultar1() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");
        assertThrows(PosicioForaRang.class, () -> {
            ll.consultar(200);
        });
    }

    @Test
    public void testOrdenadaConsultar2() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");
        assertThrows(PosicioForaRang.class, () -> {
            ll.consultar(-1);
        });
    }

    @Test
    public void testOrdenadaConsultar3() {
        LlistaPersones ll = new LlistaPersones(true);
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
    public void testOrdenadaPosicioPersona1() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");
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
    public void testOrdenadaBuscarPersona1() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");
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
    public void testOrdenadaEsBuida1() {
        LlistaPersones ll = new LlistaPersones(true);
        assertTrue(ll.esBuida());
    }

    @Test
    public void testOrdenadaEsBuida2() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");
        assertFalse(ll.esBuida());
    }

    @Test
    public void testOrdenadaNumElem() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");
        assertEquals(100, ll.numElements());
    }

    @Test
    public void testOrdenadaElements1() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");
        Persona p[] = ll.elements();
        assertEquals(100, p.length);
    }

    @Test
    public void testOrdenadaElements2() {
        LlistaPersones ll = new LlistaPersones(true);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        ll.inserir(p);
        Persona llp[] = ll.elements();
        assertTrue(p.equals(llp[0]));
    }

    @Test
    public void testOrdenadaPes1() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");
        Persona p[] = ll.personesPesInferior(80);
        assertEquals(59, p.length);
    }

    @Test
    public void testOrdenadaPes2() {
        LlistaPersones ll = new LlistaPersones(true, "persones.csv");
        Persona p[] = ll.personesPesInferior(60);
        assertEquals(9, p.length);
    }
}
