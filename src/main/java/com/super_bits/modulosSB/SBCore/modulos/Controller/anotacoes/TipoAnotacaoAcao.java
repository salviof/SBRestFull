/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;

/**
 *
 * @author SalvioF
 */
public enum TipoAnotacaoAcao {

    ACAO_CONTROLLER,
    ACAO_SELECAO_ACAO,
    ACAO_FORMULARIO,
    ACAO_PAGINA_MANAGED_BEANS,
    ACAO_FORMULARIO_CAMPOS_ATUALIZA_FORM,
    ACAO_FORMULARIO_CAMPOS_ATUALIZA_GRUPO_CAMPOS,
    ACAO_FORMULARIO_CAMPOS_ATUALIZA_GRUPO_ESPECIFICO,
    ACAO_FORMULARIO_CAMPOS_SOMENTE_LEITURA;

    public void aplicarAnotacaoFormulario(InfoAnotacaoAcao pAnotacao, ItfAcaoDoSistema pAcao) {

        switch (this) {
            case ACAO_CONTROLLER:
                break;
            case ACAO_FORMULARIO:
                break;
            case ACAO_SELECAO_ACAO:
                break;
            case ACAO_FORMULARIO_CAMPOS_ATUALIZA_FORM:
                break;
            case ACAO_FORMULARIO_CAMPOS_ATUALIZA_GRUPO_CAMPOS:
                break;
            case ACAO_FORMULARIO_CAMPOS_ATUALIZA_GRUPO_ESPECIFICO:
                break;
            case ACAO_FORMULARIO_CAMPOS_SOMENTE_LEITURA:
                break;
            case ACAO_PAGINA_MANAGED_BEANS:
                break;
            default:
                throw new AssertionError(this.name());

        }

    }

}
