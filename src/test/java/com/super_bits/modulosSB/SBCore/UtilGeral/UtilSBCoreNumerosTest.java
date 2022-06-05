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
 * @author desenvolvedor
 */
public class UtilSBCoreNumerosTest {

    /**
     * Test of getLpadZero method, of class UtilSBCoreNumeros.
     */
    public void testGetLpadZero() {
        System.out.println("getLpadZero");
        Integer pValor = null;
        int pCasas = 0;
        Integer expResult = null;
        Integer result = UtilSBCoreNumeros.getLpadZero(pValor, pCasas);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConcatenados method, of class UtilSBCoreNumeros.
     */
    public void testGetConcatenados() {
        System.out.println("getConcatenados");
        int[] pNumeros = null;
        Integer expResult = null;
        Integer result = UtilSBCoreNumeros.getConcatenados(pNumeros);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumeroRandomico method, of class UtilSBCoreNumeros.
     */
    public void testGetNumeroRandomico() {
        System.out.println("getNumeroRandomico");
        int pMinimo = 0;
        int pMaximo = 0;
        Integer expResult = null;
        Integer result = UtilSBCoreNumeros.getNumeroRandomico(pMinimo, pMaximo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converterMoeda method, of class UtilSBCoreNumeros.
     */
    @Test
    public void testConverterMoeda() {

        System.out.println(UtilSBCoreNumeros.converterMoeda(10.30));

    }

    @Test
    public void converterInteiro() {
        System.out.println("inteiro" + UtilSBCoreNumeros.formatarNumeroInteiro(12334));
    }

}
