/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.testes.TestesCore;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreRandomicoTest extends TestesCore {

    /**
     * Test of getValorStringRandomico method, of class UtilSBCoreRandomico.
     */
    @Test
    public void testGetValorStringRandomico() {
        try {
            String pvalor = UtilSBCoreRandomico.getValorStringRandomico(UtilSBCoreRandomico.TIPO_VALOR_RANDON.NUMERO, 5);
            System.out.println(UtilSBCoreRandomico.getValorRandomicoDaMaskara('#'));
            System.out.println(pvalor);
            System.out.println(UtilSBCoreRandomico.getValorStringRandomico(UtilSBCoreRandomico.TIPO_VALOR_RANDON.LETRAS, 5));
        } catch (Throwable t) {
            //lancarErroJUnit(t);
        }
    }

}
