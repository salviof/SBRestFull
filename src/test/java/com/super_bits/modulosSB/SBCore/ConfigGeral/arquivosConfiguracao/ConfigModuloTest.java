/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.FabConfigModuloTeste;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.testes.TestesCore;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class ConfigModuloTest extends TestesCore {

    public ConfigModuloTest() {
    }

    /**
     * Test of getPropriedade method, of class ConfigModulo.
     */
    @Test
    public void testGetPropriedade() {
        ConfigModulo config = SBCore.getConfigModulo(FabConfigModuloTeste.class);
        System.out.println(config.toString());
    }

}
