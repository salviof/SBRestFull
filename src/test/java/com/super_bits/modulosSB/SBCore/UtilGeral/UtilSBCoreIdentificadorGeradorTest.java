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
 * @author salviofurbino
 */
public class UtilSBCoreIdentificadorGeradorTest {

    public UtilSBCoreIdentificadorGeradorTest() {
    }

    /**
     * Test of getIdentificadorUnico method, of class
     * UtilSBCoreIdentificadorGerador.
     */
    @Test
    public void testGetIdentificadorUnico() {
        System.out.println(UtilSBCoreGeradorDeID.getIdentificadorUnico());

    }

    @Test
    public void testItentificadorUnicoNumeroLetras() {
        System.out.println(UtilSBCoreGeradorDeID.getIdentificadorUnicoNumerosLetras());
    }

}
