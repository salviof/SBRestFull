/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class MapaAcoesSistemaTest {

    public MapaAcoesSistemaTest() {
    }

    @Test
    public void testMakeMapaAcoesSistema() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        System.out.println(SBCore.getCaminhoDesenvolvimento());
        MapaAcoesSistema.makeMapaAcoesSistema();

    }

}
