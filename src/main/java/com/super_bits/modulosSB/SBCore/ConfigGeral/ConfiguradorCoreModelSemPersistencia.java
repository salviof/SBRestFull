/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

/**
 *
 * @author desenvolvedor
 */
public abstract class ConfiguradorCoreModelSemPersistencia extends ConfiguradorCoreDeProjetoJarAbstrato {

    @Override
    public void defineClassesBasicas(ItfConfiguracaoCoreCustomizavel pConfiguracao) {
        super.defineClassesBasicas(pConfiguracao); //To change body of generated methods, choose Tools | Templates.
        pConfiguracao.setTipoProjeto(FabTipoProjeto.MODEL_E_CONTROLLER);

    }
}
