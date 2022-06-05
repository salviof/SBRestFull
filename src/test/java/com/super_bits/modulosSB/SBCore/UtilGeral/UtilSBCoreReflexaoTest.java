/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TipoAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class UtilSBCoreReflexaoTest {

    public UtilSBCoreReflexaoTest() {
    }

    @Before
    public void setUp() {
        configAmbienteDesevolvimento();
    }

    /**
     * Test of getClassesComEstaAnotacao method, of class UtilSBCoreReflexao.
     */
    //@Test
    public void testGetClassesComEstaAnotacao() {

        try {

            List<Class> classes = UtilSBCoreReflexao.getClassesComEstaAnotacao(InfoObjetoSB.class, "com.super_bits");
            assertTrue("nenhuma classe foi encontrada", classes.size() > 0);

        } catch (Throwable t) {
            //      lancarErroJUnit(t);
        }

    }

    @Test
    public void testClasseImplementaEstaInterface() {
        System.out.println(UtilSBCoreReflexao.isInterfaceImplementadaNaClasse(TipoAtributoObjetoSB.class, ItfBeanSimplesSomenteLeitura.class));
    }

    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

}
