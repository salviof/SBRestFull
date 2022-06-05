/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTILSBCoreDesktopApp;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;

/**
 *
 * @author sfurbino
 */
public class InfoErroSBCoreDeveloperSopMessagem extends InfoErroSBComAcoes {

    @Override
    public void alertarResponsavel() {
        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO || SBCore.getEstadoAPP() == SBCore.ESTADO_APP.HOMOLOGACAO) {
            UTILSBCoreDesktopApp.showMessageStopProcess(FabMensagens.AVISO.getMsgDesenvolvedor(getMensagemGenericaFormatada("Alertando Responsavel \n" + getMsgDesenvolvedorLancou() + "\n")));
        }
    }

    @Override
    public void lancarExcecao() {

        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            System.out.println("Atenão O lançamento de exceção não foi construido para ser utilizado dentro do Relatar Erro!! portando");
            System.out.println("Se você está vendo este erro é porque utilizou um lançamento de exceção, sem especificar o Throw no fim do método, Isto é uma má pratica que desabona a foça Jedi dos programadores Ninjas");
            System.out.println("Ou você lançou a execao dentro de um catch");
            pararSistem();
        }
        throw new UnsupportedOperationException(getErroGerado());
    }

    @Override
    public void lancarPane() {
        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            UTILSBCoreDesktopApp.showMessageStopProcess(FabMensagens.AVISO.getMsgDesenvolvedor(getMensagemGenericaFormatada("VOCÊ GEROU UMA PANE NO SISTEMA! Parabéns ")));
        }
    }

    @Override
    protected void pararSistem() {
        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            UTILSBCoreDesktopApp.showMessageStopProcess(FabMensagens.ERRO_FATAL.getMsgDesenvolvedor(getMensagemGenericaFormatada("PARA TUDO! \n" + getMsgDesenvolvedorLancou() + "\n")));
            System.err.println("_____________________________________________________________________");
            System.err.println(" ESTE ERRO PRECISA SER CORRIGIDO ANTES DE CONTINUAR O DESENVOLVIMENTO");
            System.err.println("_____________________________________________________________________");
            sytemOutDoErro();
            System.exit(0);
        } else {
            super.pararSistem(); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void registrarErro() {
        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            UTILSBCoreDesktopApp.showMessageStopProcess(FabMensagens.AVISO.getMsgDesenvolvedor(getMensagemGenericaFormatada("Atenção um pedido de log foi gerado para este erro:")));
        }
    }

}
