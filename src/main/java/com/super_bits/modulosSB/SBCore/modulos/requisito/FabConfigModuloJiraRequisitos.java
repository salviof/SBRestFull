/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.requisito;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;

/**
 *
 * @author SalvioF
 */
public enum FabConfigModuloJiraRequisitos implements ItfFabConfigModulo {

    URL_JIRA,
    PORTA_WEBSERVICE,
    URL_WEBSERVICE,
    USUARIO_COMENTARIO_PADRAO,
    SENHA_USUARIO_COMENTARIO_PADRAO;

    @Override
    public String getValorPadrao() {
        switch (this) {

            case URL_JIRA:
                return "http://meuJiraexemplo.com.br";

            case PORTA_WEBSERVICE:
                return "666";

            case URL_WEBSERVICE:
                return "http://meuwebServiceComunicaJira.com.br";
            case USUARIO_COMENTARIO_PADRAO:
                return "usuarioComentario@jira";
            case SENHA_USUARIO_COMENTARIO_PADRAO:
                return "senhaUsuarioPadrao";

            default:
                throw new AssertionError(this.name());

        }
    }

}
