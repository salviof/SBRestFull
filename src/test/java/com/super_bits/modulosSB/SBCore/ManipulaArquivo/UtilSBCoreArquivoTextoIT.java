/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivoTexto;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author cristopher
 */
public class UtilSBCoreArquivoTextoIT {

    public UtilSBCoreArquivoTextoIT() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testSomeMethod() {
    }

    @Test
    public void isTemPalavraNoArquivo() {

        String pArquivo = "/home/superBits/dados/teste";
        String pPalavra = "0";

        boolean encontrou = UtilSBCoreArquivoTexto.isTemPalavraNoArquivo(pArquivo, pPalavra);

        assertTrue("A palavra não foi encontrada no arquivo ", encontrou);
    }

}
