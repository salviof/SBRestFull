/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.comunicacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author sfurbino
 */
public class RespostaAcaoDoSistema extends RespostaSimples implements ItfRespostaAcaoDoSistema {

    private final ItfAcaoDoSistema acaoDosistema;

    private boolean temFormulario = false;
    private ItfAcaoFormulario acaoFormularioResposta;

    @Deprecated
    public RespostaAcaoDoSistema() {
        super(null);
        this.acaoDosistema = null;
    }

    public RespostaAcaoDoSistema(ItfAcaoDoSistema pAcaoDoSistema) {
        this(Void.class, pAcaoDoSistema);
    }

    public RespostaAcaoDoSistema(ItfBeanSimples pItemPrincipal, ItfAcaoDoSistema pAcaoDosistema) {
        this((pItemPrincipal == null) ? null : pItemPrincipal.getClass(), pAcaoDosistema);
    }

    @SuppressWarnings("LeakingThisInConstructor")
    public RespostaAcaoDoSistema(Class pTipoRetorno, ItfAcaoDoSistema pAcaoDoSistema) {
        super(pTipoRetorno);
        UtilSBCoreReflexao.instanciarListas(this);
        acaoDosistema = pAcaoDoSistema;

    }

    @Override
    public ItfRespostaAcaoDoSistema dispararMensagens() {
        calculaResultados();

        if (!mensagensDisparada) {
            if (getMensagens().isEmpty()) {
                if (acaoDosistema != null) {
                    addAviso(" A ação: " + acaoDosistema.getNomeAcao() + " foi realizada com sucesso!");
                } else {
                    addAviso("Operação realizada com sucesso");
                }
            }

            getMensagens().forEach((msg) -> {
                SBCore.getCentralDeMensagens().enviaMensagem(msg);
            });
            mensagensDisparada = true;
        }
        return this;

    }

    @Override
    public boolean isTemProximoFormulario() {
        return temFormulario;
    }

    @Override
    public ItfRespostaAcaoDoSistema setProximoFormulario(ItfAcaoFormulario pFormulario) {
        if (pFormulario != null) {
            temFormulario = true;
        }
        acaoFormularioResposta = pFormulario;
        return this;
    }

    @Override
    public ItfAcaoFormulario getAcaoProximoFormulario() {
        return acaoFormularioResposta;
    }

    @Override
    public ItfAcaoDoSistema getAcaoVinculada() {
        return acaoDosistema;
    }

    @Override
    public ItfRespostaAcaoDoSistema setRetornoDisparaERetorna(Object pObjetoResultante) {
        return (ItfRespostaAcaoDoSistema) super.setRetorno(pObjetoResultante);
    }

    @Override
    public ItfRespostaAcaoDoSistema setRetorno(Object pObjetoResultante) {
        return (ItfRespostaAcaoDoSistema) super.setRetorno(pObjetoResultante);
    }

    @Override
    public ItfRespostaAcaoDoSistema addMensagemAvisoDisparaERetorna(String pMensagem) {
        return (ItfRespostaAcaoDoSistema) super.addMensagemAvisoDisparaERetorna(pMensagem);
    }

    @Override
    public ItfRespostaAcaoDoSistema addMensagemDisparaERetorna(ItfMensagem pMensagem) {
        return (ItfRespostaAcaoDoSistema) super.addMensagem(pMensagem);
    }

    @Override
    public ItfRespostaAcaoDoSistema addMensagemErroDisparaERetorna(String pMensagem) {
        return (ItfRespostaAcaoDoSistema) super.addMensagemErroDisparaERetorna(pMensagem);
    }

    @Override
    public ItfRespostaAcaoDoSistema addMensagemAlertaDisparaERetorna(String pMensagem) {
        return (ItfRespostaAcaoDoSistema) super.addMensagemAlertaDisparaERetorna(pMensagem);
    }

    @Override
    public ItfRespostaAcaoDoSistema addMensagem(ItfMensagem pMensagem) {
        return (ItfRespostaAcaoDoSistema) super.addMensagem(pMensagem);
    }

    @Override
    public ItfRespostaAcaoDoSistema addAlerta(String Pmensagem) {
        return (ItfRespostaAcaoDoSistema) super.addAlerta(Pmensagem);
    }

    @Override
    public ItfRespostaAcaoDoSistema addAviso(String Pmensagem) {
        return (ItfRespostaAcaoDoSistema) super.addAviso(Pmensagem);
    }

    @Override
    public ItfRespostaAcaoDoSistema addErro(String Pmensagem) {
        return (ItfRespostaAcaoDoSistema) super.addErro(Pmensagem);
    }

}
