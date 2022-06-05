/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.integracao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

/**
 *
 * @author desenvolvedor
 */
@Deprecated
public enum FabSMSIntegracao {

    ENVIARMENSAGEM;

    public String getUrlServidor() {
        return "sns.us-east-1.amazonaws.com";
    }

}
