package cat.urv.deim;

import org.junit.jupiter.api.Test;

import cat.urv.deim.exceptions.ElementNoTrobat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Random;

public class TestHash {

    Random rand = new Random(1);

    // Test if the hash map correctly handles negative size
    @Test
    public void testHashNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HashMapPersones(-200);
        });
    }

    @Test
    public void testHashInserir1() {
        HashMapPersones hash = new HashMapPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        hash.inserir(p);
        assertEquals(1, hash.numElements());
    }

    @Test
    public void testHashInserir2() {
        HashMapPersones hash = new HashMapPersones(200, "persones.csv");
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        hash.inserir(p);
        assertEquals(101, hash.numElements());
    }

    @Test
    public void testHashInserir3() {
        HashMapPersones hash = new HashMapPersones(200, "persones.csv");
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        hash.inserir(p);
        Persona p2 = new Persona(9999999, 52, "Jaume", "Oliva", 95, 190);
        hash.inserir(p2);  //Controlem que actualitzi, i que no afegexi un altre cop
        assertEquals(101, hash.numElements());
    }

    @Test
    public void testHashEsborrar1() {
        HashMapPersones hash = new HashMapPersones(200);
        assertThrows(ElementNoTrobat.class, () -> {
            hash.esborrar(1064190);
        });
    }

    @Test
    public void testHashEsborrar2() {
        HashMapPersones hash = new HashMapPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        hash.inserir(p);
        try {
            hash.esborrar(p.getId_persona());
        } catch (ElementNoTrobat e) {
            fail();
        }
        assertThrows(ElementNoTrobat.class, () -> {
            hash.esborrar(p.getId_persona());
        });
    }

    @Test
    public void testHashEsborrar3() {
        HashMapPersones hash = new HashMapPersones(200, "persones.csv");

        try {
            hash.esborrar(1064190);

            assertThrows(ElementNoTrobat.class, () -> {
                hash.esborrar(1064190);
            });
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testHashEsborrar4() {
        HashMapPersones hash = new HashMapPersones(50);
        for (int i = 0; i < 305; i++) {
            int edat = rand.nextInt(80)+10;
            int alsada = rand.nextInt(50)+150;
            int pes = rand.nextInt(100)+50;
            Persona p = new Persona(1000+i, edat, "James", "Bond", pes, alsada);
            hash.inserir(p);
        }
        try {
            hash.esborrar(1100);
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testHashConsultar1() {
        HashMapPersones hash = new HashMapPersones(200);
        assertThrows(ElementNoTrobat.class, () -> {
            hash.consultar(1064190);
        });
    }

    @Test
    public void testHashConsultar2() {
        HashMapPersones hash = new HashMapPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        hash.inserir(p);
        try {
            assertTrue(p.equals(hash.consultar(9999999)));
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testHashConsultar3() {
        HashMapPersones hash = new HashMapPersones(200, "persones.csv");

        try {
            hash.esborrar(1064190);

            assertThrows(ElementNoTrobat.class, () -> {
                hash.consultar(1064190);
            });
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testHashBuscar1() {
        HashMapPersones hash = new HashMapPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        assertFalse(hash.buscar(p));
    }

    @Test
    public void testHashBuscar2() {
        HashMapPersones hash = new HashMapPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        hash.inserir(p);
        assertTrue(hash.buscar(p));
    }

    @Test
    public void testHashEsBuida1() {
        HashMapPersones hash = new HashMapPersones(200, "persones.csv");
        assertFalse(hash.esBuida());
    }

    @Test
    public void testHashEsBuida2() {
        HashMapPersones hash = new HashMapPersones(200);
        assertTrue(hash.esBuida());
    }

    @Test
    public void testHashMida1() {
        HashMapPersones hash = new HashMapPersones(200, "persones.csv");
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        hash.inserir(p);

        assertEquals(200, hash.mida());
    }

    @Test
    public void testHashMida2() {
        HashMapPersones hash = new HashMapPersones(200);
        assertEquals(200, hash.mida());
    }

    @Test
    public void testHashMidaRedimensionament1() {
        HashMapPersones hash = new HashMapPersones(50, "persones.csv");
        //La mida s'ha de duplicar 2 cops - 2 redimensionaments
        assertEquals(200, hash.mida());
    }

    @Test
    public void testHashMidaRedimensionament2() {
        HashMapPersones hash = new HashMapPersones(50);
        for (int i = 0; i < 305; i++) {
            int edat = rand.nextInt(80)+10;
            int alsada = rand.nextInt(50)+150;
            int pes = rand.nextInt(100)+50;
            Persona p = new Persona(1000+i, edat, "James", "Bond", pes, alsada);
            hash.inserir(p);
        }
        assertEquals(800, hash.mida());
    }

    @Test
    public void testHashFactorCarrega1() {
        HashMapPersones hash = new HashMapPersones(400, "persones.csv");
        assertEquals(0.25, hash.factorCarrega());
    }

    @Test
    public void testHashElements1() {
        HashMapPersones hash = new HashMapPersones(400, "persones.csv");
        Persona p[] = hash.elements();
        assertEquals(100, p.length);
    }

    @Test
    public void testHashElements2() {
        HashMapPersones hash = new HashMapPersones(400);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        hash.inserir(p);
        Persona llp[] = hash.elements();
        assertTrue(p.equals(llp[0]));
    }

    @Test
    public void testHashPes1() {
        HashMapPersones hash = new HashMapPersones(400, "persones.csv");
        Persona p[] = hash.personesPesInferior(80);
        assertEquals(59, p.length);
    }

    @Test
    public void testHashPes2() {
        HashMapPersones hash = new HashMapPersones(400, "persones.csv");
        Persona p[] = hash.personesPesInferior(60);
        assertEquals(9, p.length);
    }


    @Test
    public void testHashClaus1() {
        HashMapPersones hash = new HashMapPersones(400, "persones.csv");
        int ids[] = hash.obtenirIDs();
        assertEquals(100, ids.length);
    }


    @Test
    public void testHashOrdrePersona1() {
        HashMapPersones hash = new HashMapPersones(400, "persones.csv");
        Persona llp[] = hash.elements();
        assertEquals(llp[3].getId_persona(), 1161890);
    }


@Test
    public void testHashOrdrePersona2() {
        HashMapPersones hash = new HashMapPersones(400, "persones.csv");
        Persona p = new Persona(1, 47, "James", "Bond", 80, 188);
        hash.inserir(p);
        Persona llp[] = hash.elements();
        assertEquals(llp[4].getId_persona(), 1161890);
    }


}

