/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreCEPTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testConfiguraEndereco() {

        // ItemLocalizacao local = new ItemLocalizacao();
        //    UtilSBCoreCEP.configuraEndereco("31110600", local);
        //    System.out.println("Loca=" + local.getNome());
        //   System.out.println("Estado" + local.getBairro().getCidade().getUnidadeFederativa().getNome());
        //    System.out.println("Bairro" + local.getBairro().getNome());
        //    System.out.println("Cidade" + local.getBairro().getCidade().getNome());
    }

    @Test
    public void testCepsEncontrados() {

    }

    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

}
