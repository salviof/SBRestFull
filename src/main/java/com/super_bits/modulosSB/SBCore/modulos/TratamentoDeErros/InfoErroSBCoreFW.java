package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

import org.coletivojava.fw.api.tratamentoErros.FabErro;

public class InfoErroSBCoreFW extends InfoErroSBComAcoes {

    public InfoErroSBCoreFW() {
    }

    public InfoErroSBCoreFW(String pMensagem, Throwable t) {
        super(pMensagem, t);
    }

    public InfoErroSBCoreFW(String pMensagem, FabErro pTipoErrp, Throwable pErroGerado) {
        super(pMensagem, pTipoErrp, pErroGerado);
    }

    @Override
    public void alertarResponsavel() {
        System.out.println("TODO Alerta de usuario");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void lancarExcecao() {
        throw new UnsupportedOperationException("Lançar execacao de SBCOreFW não foi implementado."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void lancarPane() {
        System.out.println("Pane lançada no sistema");

    }

    @Override
    public void registrarErro() {

        System.out.println("Este erro deveria ser persistido de alguma forma, mas não será poruqe o método registrar erro não foi implementado");

    }

}
