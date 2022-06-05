/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.beanExecucao;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfParametroAcaoController;

/**
 *
 * @author desenvolvedor
 */
public class ExecucaoAcaoControllerBean {

    private String acao;
    private ItfParametroAcaoController parametro;
    private String chaveAcesso;

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public ItfParametroAcaoController getParametro() {
        return parametro;
    }

    public void setParametro(ItfParametroAcaoController parametro) {
        this.parametro = parametro;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

}
