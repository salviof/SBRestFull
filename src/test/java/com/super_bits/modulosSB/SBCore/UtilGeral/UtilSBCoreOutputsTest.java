/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.io.BufferedInputStream;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class UtilSBCoreOutputsTest {

    public UtilSBCoreOutputsTest() {
    }

    @Before
    public void setUp() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

    /**
     * Test of salvarArquivoInput method, of class UtilSBCoreOutputs.
     */
    @Test
    public void testSalvarArquivoInput() {
        BufferedInputStream imagem = UTilSBCoreInputs.getStreamBuffredByURL("http://www.cadastroindustrialmg.com.br/img/logos/ImagemLogo15885.jpg");
        UtilSBCoreOutputs.salvarArquivoBfInput(imagem, "/home/superBits/dados/importador/logos/log/log/logoTeste.jpg");

    }

    /**
     * Test of salvarArquivoBfInput method, of class UtilSBCoreOutputs.
     */
    @Test
    public void testSalvarArquivoBfInput() {
    }

    /**
     * Test of copiarArquivo method, of class UtilSBCoreOutputs.
     */
    @Test
    public void testCopiarArquivo() {
    }

}
