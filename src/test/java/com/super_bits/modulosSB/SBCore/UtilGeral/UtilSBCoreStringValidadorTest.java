/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedorninja01
 */
public class UtilSBCoreStringValidadorTest {

    public UtilSBCoreStringValidadorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getListaStringEntreCaracter method, of class
     * UtilSBCoreStringValidador.
     */
    /**
     * Test of isUmaUrlYoutubeEmbed method, of class UtilSBCoreStringValidador.
     */
    @Test
    public void testIsUmaUrlYoutubeEmbed() {
        assertTrue("Naão aceitou url válida", UtilSBCoreStringValidador.isUmaUrlYoutubeEmbed("https://www.youtube.com/embed/ALAFuwC2qls"));
        assertFalse("Aceitou url inválida", UtilSBCoreStringValidador.isUmaUrlYoutubeEmbed("https://youtube.com.br"));
    }

    public class UtilSBCoreStringValidadorImpl extends UtilSBCoreStringValidador {
    }

}
