/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoNaoImplementado;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class CampoNaoImplementadoTest {

    public CampoNaoImplementadoTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testValidarCampo() {

        CampoNaoImplementado teste = new CampoNaoImplementado();
        UsuarioAnonimo teste2 = new UsuarioAnonimo();

        UsuarioAnonimo user = new UsuarioAnonimo();

        String valor = (String) user.getCampoByNomeOuAnotacao("nome").getValor();

        user.getCampoByNomeOuAnotacao("nome").setValor("teste");

        valor = (String) user.getCampoByNomeOuAnotacao("nome").getValor();

        valor = valor;

    }

    @Test
    public void testGetParent() {
    }

    @Test
    public void testGetValor() {
    }

    @Test
    public void testSetValor() {
    }

}
