/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.comunicacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author SalvioF
 */
public class RespostaSimples implements ItfResposta, Serializable {

    private ItfResposta.Resultado resultado = ItfResposta.Resultado.SUCESSO;

    private final List<ItfMensagem> mensagens = new ArrayList<>();

    private Class tipoRetorno;

    private Object retorno;

    private String urlDestinoFalha;

    private String urlDestinoSucesso;

    private String urlDestino;

    protected boolean mensagensDisparada = false;

    public RespostaSimples(Class pTipoRetorno) {
        if (pTipoRetorno != Void.class) {
            tipoRetorno = pTipoRetorno;
        }
    }

    protected void calculaResultados() {
        setResultadoWS(ItfResposta.Resultado.SUCESSO);
        for (ItfMensagem msg : mensagens) {
            switch (msg.getTipoDeMensagem()) {
                case AVISO:
                    break;
                case ALERTA:
                    if (getResultado() != ItfResposta.Resultado.FALHOU) {
                        setResultadoWS(ItfResposta.Resultado.ALERTA);
                    }
                    break;
                case ERRO:
                    setResultadoWS(ItfResposta.Resultado.FALHOU);
                    break;
                case ERRO_FATAL:
                    setResultadoWS(ItfResposta.Resultado.FALHOU);
                    break;
                default:
                    throw new AssertionError(msg.getTipoDeMensagem().name());

            }
        }

    }

    @Override
    public boolean isTemAlerta() {
        for (ItfMensagem msg : mensagens) {
            if (msg.getTipoDeMensagem() == FabMensagens.ALERTA) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ItfResposta setRetorno(Object pObjetoResultante) {
        if (pObjetoResultante == null) {
            retorno = null;
            if (!(getMensagens().stream().filter(msg -> msg.getTipoDeMensagem().equals(FabMensagens.ERRO))).findFirst().isPresent()) {
                addMensagemAlertaDisparaERetorna("A ação não produziu o resultado esperado");
            }
            dispararMensagens();
            return this;
        }

        if (pObjetoResultante.getClass().getSimpleName().equals(tipoRetorno.getClass().getSimpleName())) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "O tipo de retorno diverge do tipo Especificado", null);
        }
        retorno = pObjetoResultante;
        return this;
    }

    protected void setResultadoWS(Resultado resultado) {
        this.resultado = resultado;
    }

    @Override
    public ItfResposta dispararMensagens() {
        calculaResultados();
        if (!mensagensDisparada) {
            if (getMensagens().isEmpty()) {

                addAviso("Operação realizada com sucesso");

            }

            if ((getMensagens().stream().filter(msg -> msg.getTipoDeMensagem().equals(FabMensagens.ERRO_FATAL))).findFirst().isPresent()) {

                List<ItfMensagem> mensagemErroFatal = new ArrayList<>();
                getMensagens().stream().filter(msgg -> (msgg.getTipoDeMensagem().equals(FabMensagens.ERRO) || msgg.getTipoDeMensagem().equals(FabMensagens.ERRO_FATAL))).forEach(mensagemErroFatal::add);
                mensagens.clear();
                mensagens.addAll(mensagemErroFatal);
            } else if ((getMensagens().stream().filter(msg -> msg.getTipoDeMensagem().equals(FabMensagens.ERRO))).findFirst().isPresent()) {
                List<ItfMensagem> mensagensDeErro = new ArrayList<>();
                getMensagens().stream().filter(msgg -> msgg.getTipoDeMensagem().equals(FabMensagens.ERRO)).forEach(mensagensDeErro::add);
                mensagens.clear();
                mensagens.addAll(mensagensDeErro);
            }

            getMensagens().forEach((msg) -> {
                SBCore.getCentralDeMensagens().enviaMensagem(msg);
            });
            mensagensDisparada = true;
        }
        return this;
    }

    @Override
    public boolean isTemUrlDestino() {
        return UtilSBCoreStringValidador.isNAO_NuloNemBranco(getUrlDestino());
    }

    @Override
    public ItfResposta addMensagemAvisoDisparaERetorna(String pMensagem) {
        ItfMensagem msg = FabMensagens.AVISO.getMsgUsuario(pMensagem);
        mensagens.add(msg);
        dispararMensagens();
        return this;
    }

    @Override
    public ItfResposta addMensagemErroDisparaERetorna(String pMensagem) {
        ItfMensagem msg = FabMensagens.ERRO.getMsgUsuario(pMensagem);
        mensagens.add(msg);
        dispararMensagens();
        return this;

    }

    @Override
    public ItfResposta addMensagemAlertaDisparaERetorna(String pMensagem) {
        ItfMensagem msg = FabMensagens.ALERTA.getMsgUsuario(pMensagem);
        mensagens.add(msg);
        dispararMensagens();
        return this;
    }

    @Override
    public ItfResposta setRetornoDisparaERetorna(Object pObjetoResultante) {
        setRetorno(pObjetoResultante);
        retorno = pObjetoResultante;
        dispararMensagens();
        return this;

    }

    @Override
    public ItfResposta addAlerta(String pMensagem) {
        ItfMensagem msg = FabMensagens.ALERTA.getMsgUsuario(pMensagem);
        mensagens.add(msg);
        return this;
    }

    @Override
    public ItfResposta addAviso(String pMensagem) {
        ItfMensagem msg = FabMensagens.AVISO.getMsgUsuario(pMensagem);
        mensagens.add(msg);
        return this;
    }

    @Override
    public ItfResposta addErro(String pMensagem) {
        ItfMensagem msg = FabMensagens.ERRO.getMsgUsuario(pMensagem);
        mensagens.add(msg);

        List<ItfMensagem> mensagensPositivas = new ArrayList<>();
        mensagens.stream().filter((mensgem) -> (mensgem.getTipoDeMensagem().equals(FabMensagens.AVISO))).forEachOrdered((mensgem) -> {
            mensagensPositivas.add(mensgem);
        });
        if (!mensagensPositivas.isEmpty()) {
            if (!SBCore.isEmModoProducao()) {
                System.out.println("As mensgens de aviso foram removidas, pois mensgens de erro foram adicionadas na resposta");
            }
            mensagens.removeAll(mensagensPositivas);
        }
        return this;
    }

    @Override
    public boolean isSucesso() {
        calculaResultados();
        if (getResultado().equals(Resultado.FALHOU)) {
            return false;
        } else {
            return true;
        }

        /**
         * Verificando se tem Retorno esperado, e se um retorno foi Configurado
         * if (tipoRetorno != null) { if (!resultado.equals(Resultado.FALHOU)) {
         * if (retorno == null) { resultado = Resultado.FALHOU;
         *
         * }
         * }
         * }
         *
         * return (!resultado.equals(Resultado.FALHOU));
         */
    }

    public void setTipoRetorno(Class tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

    @Override
    public Resultado getResultado() {
        return resultado;
    }

    @Override
    public Class getTipoRetorno() {
        return tipoRetorno;
    }

    @Override
    public Object getRetorno() {
        return retorno;
    }

    @Override
    public List<ItfMensagem> getMensagens() {
        return mensagens;
    }

    @Override
    public ItfResposta addMensagemDisparaERetorna(ItfMensagem pMensagem) {
        mensagens.add(pMensagem);
        dispararMensagens();
        return this;
    }

    @Override
    public ItfResposta addMensagem(ItfMensagem pMensagem) {
        mensagens.add(pMensagem);
        return this;
    }

    @Override
    public String getUrlDestino() {
        if (isSucesso()) {
            if (UtilSBCoreStringValidador.isNAO_NuloNemBranco(urlDestinoSucesso)) {
                return urlDestinoSucesso;
            }
        } else {
            if (UtilSBCoreStringValidador.isNAO_NuloNemBranco(urlDestinoFalha)) {
                return urlDestinoFalha;
            }
        }

        return urlDestino;
    }

    @Override
    public void setUrlDestino(String urlDestino) {
        this.urlDestino = urlDestino;
    }

    @Override
    public void setUrlDestinoFalha(String urlDestinoFalha) {
        this.urlDestinoFalha = urlDestinoFalha;
    }

    @Override
    public void setUrlDestinoSucesso(String urlDestinoSucesso) {
        this.urlDestinoSucesso = urlDestinoSucesso;
    }

}
