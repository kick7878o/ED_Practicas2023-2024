package cat.urv.deim;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLlista {

    //Proves de funcionament de l'iterable

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
    public void testNoOrdenadaElements1() {
        LlistaPersones ll = new LlistaPersones(false, "persones.csv");
        Persona p[] = ll.elements();
        assertEquals(100, p.length);
    }


}
