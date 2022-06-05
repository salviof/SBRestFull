/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.tratamentoErros.ItfErroSBServico;

/**
 *
 * Classe erro básica que extende todos os erros dos projetos Super-Bits
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 24/05/2014
 *
 */
public abstract class InfoErroSBComAcoes extends InfoErroSBBasico implements ItfErroSBServico {

    public InfoErroSBComAcoes(String pMensagem, Throwable t) {
        super(pMensagem, FabErro.SOLICITAR_REPARO, t);
    }

    public InfoErroSBComAcoes(String pMensagem, FabErro pTipoErrp, Throwable pErroGerado) {
        super(pMensagem, pTipoErrp, pErroGerado);
    }

    public InfoErroSBComAcoes(Throwable t) {
        super(t.getMessage(), FabErro.SOLICITAR_REPARO, t);
    }

    public InfoErroSBComAcoes() {

    }

    @Override
    public void executarErro() {
        checarConfiguracao();
        sytemOutDoErro();

        switch (tipoErro) {
            case LANCAR_EXCECÃO:
                switch (SBCore.getEstadoAPP()) {
                    case DESENVOLVIMENTO:
                        lancarExcecao();

                        break;
                    case PRODUCAO:
                        lancarExcecao();

                        break;
                    case HOMOLOGACAO:
                        registrarErro();
                        lancarExcecao();
                        break;
                    default:
                        throw new AssertionError(SBCore.getEstadoAPP().name());
                }

                break;

            case ARQUIVAR_LOG:
                switch (SBCore.getEstadoAPP()) {
                    case DESENVOLVIMENTO:
                        registrarErro();
                        alertarResponsavel();
                        break;
                    case PRODUCAO:
                        registrarErro();
                        break;
                    case HOMOLOGACAO:
                        registrarErro();
                        break;
                    default:
                        throw new AssertionError(SBCore.getEstadoAPP().name());
                }

                SBCore.getCentralDeEventos().registrarLogDeEvento(FabMensagens.ERRO, mensagemDoDesenvolvedor.getMenssagem(), -1);
                break;
            case SOLICITAR_REPARO:
                switch (SBCore.getEstadoAPP()) {
                    case DESENVOLVIMENTO:
                        alertarResponsavel();
                        break;
                    case PRODUCAO:
                        alertarResponsavel();
                        break;
                    case HOMOLOGACAO:
                        alertarResponsavel();
                        break;
                    default:
                        throw new AssertionError(SBCore.getEstadoAPP().name());
                }
                break;
            case PARA_TUDO:
                switch (SBCore.getEstadoAPP()) {
                    case DESENVOLVIMENTO:
                        pararSistem();
                        break;
                    case PRODUCAO:
                        registrarErro();
                        break;
                    case HOMOLOGACAO:
                        pararSistem();
                        break;
                    default:
                        throw new AssertionError(SBCore.getEstadoAPP().name());
                }

                break;
            default:
                throw new AssertionError(tipoErro.name());

        }

    }

}
