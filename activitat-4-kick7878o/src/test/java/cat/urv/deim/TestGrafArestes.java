package cat.urv.deim;

import org.junit.jupiter.api.Test;

import cat.urv.deim.exceptions.ElementNoTrobat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Random;

public class TestGrafArestes {

    Random rand = new Random(1);


    @Test
    public void testGrafInserirAresta1() {
        GrafPersones graf = new GrafPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        Persona p2 = new Persona(88888888, 52, "Jaume", "Oliva", 95, 190);
        graf.inserirPersona(p2);
        try {
            graf.inserirAmistat(p, p2, 1);
            assertEquals(1, graf.numAmistats());
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testGrafInserirAresta2() {
        GrafPersones graf = new GrafPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        Persona p2 = new Persona(88888888, 52, "Jaume", "Oliva", 95, 190);
        graf.inserirPersona(p2);
        Persona p3 = new Persona(777777777 , 52, "Jaime", "Sanchez", 90, 160);
        graf.inserirPersona(p3);
        try {
            graf.inserirAmistat(p, p2, 1);
            graf.inserirAmistat(p, p3, 4);
            graf.inserirAmistat(p2, p3, 8);
            graf.inserirAmistat(p, p, 2);
            assertEquals(4, graf.numAmistats());
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testGrafInserirAresta3() {
        GrafPersones graf = new GrafPersones(200);
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        Persona p2 = new Persona(88888888, 52, "Jaume", "Oliva", 95, 190);
        graf.inserirPersona(p2);
        Persona p3 = new Persona(777777777 , 52, "Jaime", "Sanchez", 90, 160);
        graf.inserirPersona(p3);
        try {
            graf.inserirAmistat(p, p2, 1);
            graf.inserirAmistat(p, p2, 2);
            graf.inserirAmistat(p, p2, 3);
            assertEquals(1, graf.numAmistats());
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testGrafInserirAresta4() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats1.csv");
        assertEquals(100, graf.numAmistats());
    }
    @Test
    public void testGrafInserirAresta5() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        assertEquals(300, graf.numAmistats());
    }

    @Test
    public void testGrafExisteixAresta1() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            Persona p1 = graf.consultarPersona(7091235);
            Persona p2 = graf.consultarPersona(4596988);
            assertTrue(graf.existeixAmistat(p1, p2));
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testGrafExisteixAresta2() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            Persona p1 = graf.consultarPersona(7091235);
            Persona p2 = new Persona(88888888, 52, "Jaume", "Oliva", 95, 190);
            graf.inserirPersona(p2);
            assertFalse(graf.existeixAmistat(p1, p2));
        } catch (ElementNoTrobat e) {
            fail();
        }
    }


    @Test
    public void testGrafExisteixAresta3() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
            graf.inserirPersona(p);
            Persona p2 = new Persona(88888888, 52, "Jaume", "Oliva", 95, 190);
            graf.inserirPersona(p2);
            graf.inserirAmistat(p, p2, 1);

            assertTrue(graf.existeixAmistat(p2, p));
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testGrafConsultarAresta1() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            Persona p1 = graf.consultarPersona(7091235);
            Persona p2 = graf.consultarPersona(4596988);
            assertEquals(7, graf.intensitatAmistat(p1, p2));
        } catch (ElementNoTrobat e) {
            fail();
        }
    }


    @Test
    public void testGrafConsultarAresta2() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            Persona p1 = graf.consultarPersona(7091235);
            Persona p2 = graf.consultarPersona(8923747);

            assertThrows(ElementNoTrobat.class, () -> {
                graf.intensitatAmistat(p1, p2);
            });
        } catch (ElementNoTrobat e) {
            fail();
        }
    }


    @Test
    public void testGrafNumAmistats() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            Persona p1 = graf.consultarPersona(7091235);

            assertEquals(12, graf.numAmistats(p1));
        } catch (ElementNoTrobat e) {
            fail();
        }
    }


    @Test
    public void testGrafEsborrarAresta1() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            Persona p1 = graf.consultarPersona(7091235);
            Persona p2 = graf.consultarPersona(8923747);

            assertThrows(ElementNoTrobat.class, () -> {
                graf.esborrarAmistat(p1, p2);
            });
        } catch (ElementNoTrobat e) {
            fail();
        }
    }



    @Test
    public void testGrafEsborrarAresta2() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            Persona p1 = graf.consultarPersona(7091235);
            Persona p2 = graf.consultarPersona(4596988);

            graf.esborrarAmistat(p1, p2);
            assertEquals(11, graf.numAmistats(p1));

        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testGrafEsborrarAresta3() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            Persona p1 = graf.consultarPersona(7091235);
            Persona p2 = graf.consultarPersona(4596988);

            graf.esborrarAmistat(p1, p2);
            assertEquals(6, graf.numAmistats(p2));

        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testGrafEsborrarAresta4() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            Persona p1 = graf.consultarPersona(7091235);
            Persona p2 = graf.consultarPersona(4596988);

            graf.esborrarAmistat(p1, p2);

            assertThrows(ElementNoTrobat.class, () -> {
                graf.esborrarAmistat(p1, p2);
            });
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testEsborrarNodeArestes1() {
        //Per validar que a l'esborrar un node tambe s'esborren les seves arestes
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            Persona p2 = graf.consultarPersona(4596988);

            graf.esborrarPersona(7091235);
            assertEquals(6, graf.numAmistats(p2));

        } catch (ElementNoTrobat e) {
            fail();
        }
    }


    @Test
    public void testEsborrarNodeArestes2() {
        //Per validar que a l'esborrar un node tambe s'esborren les seves arestes
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            graf.esborrarPersona(7091235);
            assertEquals(288, graf.numAmistats());
        } catch (ElementNoTrobat e) {
            fail();
        }
    }



    @Test
    public void testNodeAillat1() {
        //Per validar que a l'esborrar un node tambe s'esborren les seves arestes
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        assertFalse(graf.teAmistats(p));
    }

    @Test
    public void testNodeAillat2() {
        //Per validar que a l'esborrar un node tambe s'esborren les seves arestes
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        Persona p = new Persona(9999999, 47, "James", "Bond", 80, 188);
        graf.inserirPersona(p);
        try {
            Persona p2 = graf.consultarPersona(4596988);
            graf.inserirAmistat(p,p2);
            assertTrue(graf.teAmistats(p));
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testLlistaAmistats1() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            Persona p = graf.consultarPersona(4596988);
            ILlistaGenerica<Integer> llista = graf.obtenirAmistats(p);
            assertEquals(7, llista.numElements());
        } catch (ElementNoTrobat e) {
            fail();
        }
    }

    @Test
    public void testLlistaAmistats2() {
        GrafPersones graf = new GrafPersones(200, "persones.csv", "persones_amistats2.csv");
        try {
            Persona p = graf.consultarPersona(4596988);
            ILlistaGenerica<Integer> llista = graf.obtenirAmistats(p);
            assertTrue(llista.existeix(6113191));
        } catch (ElementNoTrobat e) {
            fail();
        }
    }




}
