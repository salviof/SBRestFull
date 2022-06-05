/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringVariaveisEmTexto;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanNormal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.objetoNativo.comunicacao.RespostaComunicacao;
import org.coletivojava.fw.api.objetoNativo.comunicacao.TipoComunicacao;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreComunicacao;

/**
 *
 *
 *
 * @author desenvolvedor
 */
public class ComunicacaoAcaoSistema extends ComunicacaoAbstrata implements ItfComunicacaoAcaoVinculada {

    private final ItfAcaoController acaoVinculada;
    private final ItfTipoComunicacao tipoComunicacao;
    private final List<ItfRespostaComunicacao> repostas;
    private final ItfBeanSimples beanVinculado;
    private final ItfDestinatario destinatario;
    private final ItfUsuario usuarioRemetente;
    private final List<ItfTipoTransporteComunicacao> transportes;
    private long tempoAceitavelResposta;

    private FabStatusComunicacao status;

    private ItfRespostaComunicacao respostaEscolhida;
    private String mensagem;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String assunto;

    public String gerarMensagemPAdrao() {
        assunto = acaoVinculada.getNomeAcao();
        String artigoNome = "o";
        if (UtilSBCoreReflexaoObjeto.getInfoClasseObjeto(beanVinculado.getClass()).generoFeminino()) {
            artigoNome = "a";
        }
        switch (acaoVinculada.getTipoAcaoGenerica()) {

            case CONTROLLER_DESATIVAR:
                return "Deseja DESATIVAR " + artigoNome + " " + beanVinculado.getNomeDoObjeto() + " " + beanVinculado.getNome() + " ?";
            case CONTROLLER_ATIVAR:
                return "Deseja ATIVAR " + artigoNome + " " + beanVinculado.getNomeDoObjeto() + " " + beanVinculado.getNome() + " ?";
            case CONTROLLER_ATIVAR_DESATIVAR:
                if (beanVinculado instanceof ItfBeanNormal) {

                    ItfBeanNormal beanComStatus = (ItfBeanNormal) beanVinculado;
                    if (beanComStatus.isAtivo()) {
                        return "Deseja DESATIVAR " + artigoNome + " " + beanVinculado.getNomeDoObjeto() + " " + beanVinculado.getNome() + " ?";
                    } else {
                        return "Deseja ATIVAR " + artigoNome + " " + beanVinculado.getNomeDoObjeto() + " " + beanVinculado.getNome() + " ?";
                    }
                }

        }
        if (UtilSBCoreReflexaoObjeto.getInfoClasseObjeto(beanVinculado.getClass()).generoFeminino()) {
            mensagem = "Deseja " + acaoVinculada.getNomeAcao() + "-" + beanVinculado.getNome() + " ";
        } else {
            mensagem = "Deseja " + acaoVinculada.getNomeAcao() + "-" + beanVinculado.getNome() + " ";
        }
        return mensagem;
    }

    public ComunicacaoAcaoSistema(ItfAcaoController pAcaoVinculada, ItfBeanSimples pBeanVinculado) {
        this.acaoVinculada = pAcaoVinculada;
        this.tipoComunicacao = pAcaoVinculada.getTipoComunicacao();

        this.beanVinculado = pBeanVinculado;
        this.destinatario = new DestinatarioTransiente(SBCore.getUsuarioLogado());
        this.usuarioRemetente = new UsuarioSistemaRoot();

        this.repostas = UtilSBCoreComunicacao.getRespostaCOmunicacao(this);

        if (this.repostas.isEmpty()) {

            repostas.addAll(UtilSBCoreComunicacao.getRespostaCOmunicacao(FabTipoComunicacao.CONFIRMAR_CANCELAR, this));

        }
        String mensagemModeloPredefinida = pAcaoVinculada.getTipoComunicacao().getMensagemModeloPredefinida();

        transportes = new ArrayList<>();
        transportes.add(SBCore.getCentralComunicacao().getFabricaTransportePadrao().getRegistro());

    }

    @Override
    public String getMensagem() {
        InfoTipoAcaoController anotacao = UtilSBCoreReflexao.getAnotacaoEmEnum(InfoTipoAcaoController.class, (Enum) acaoVinculada.getEnumAcaoDoSistema());

        if (anotacao != null && UtilSBCoreStringValidador.isNAO_NuloNemBranco(anotacao.fraseComunicação())) {
            return UtilSBCoreStringVariaveisEmTexto.gerarTextoSubstituindoAtributosSimples(anotacao.fraseComunicação(), beanVinculado);
        }
        if (tipoComunicacao.getMensagemModeloPredefinida() == null
                || tipoComunicacao.getMensagemModeloPredefinida().isEmpty()) {

            return gerarMensagemPAdrao();
        }
        if (tipoComunicacao.getMensagemModeloPredefinida().equals(TipoComunicacao.MENSAGEM_PADRAO_COMUNICACAO)) {
            return gerarMensagemPAdrao();
        }

        return tipoComunicacao.getMensagemModeloPredefinida();
    }

    /**
     *
     * @return
     */
    @Override
    public ItfAcaoController getAcaoVinculada() {
        return acaoVinculada;
    }

    @Override
    public ItfTipoComunicacao getTipoComunicacao() {
        return tipoComunicacao;
    }

    public ItfBeanSimples getBeanVinculado() {
        return beanVinculado;
    }

    @Override
    public ItfDestinatario getDestinatario() {
        return destinatario;
    }

    @Override
    public ItfUsuario getUsuarioRemetente() {
        return usuarioRemetente;
    }

    @Override
    public List<ItfRespostaComunicacao> getRepostasPossiveis() {
        return repostas;
    }

    @Override
    public List<ItfTipoTransporteComunicacao> getTransportes() {
        return transportes;
    }

    @Override
    public ItfRespostaComunicacao getRespostaEscolhida() {
        return respostaEscolhida;
    }

    @Override
    public void setRespostaEscolhida(ItfRespostaComunicacao pResposta) {
        respostaEscolhida = pResposta;
    }

    @Override
    public long getTempoAceitavelResposta() {
        return tempoAceitavelResposta;
    }

    @Override
    public void setTempoAceitavelResposta(long pTempo) {
        tempoAceitavelResposta = pTempo;
    }

    @Override
    public String getAssunto() {
        return getMensagem();
    }

    @Override
    public void setNome(String pAssunto) {
        assunto = pAssunto;
    }

    @Override
    public void setMensagem(String pMensagem) {
        mensagem = pMensagem;
    }

    @Override
    public FabStatusComunicacao getStatusComunicacao() {
        if (status == null && isFoiSelado()) {
            status = FabStatusComunicacao.SELADO;
        }

        return status;
    }

    @Override
    public void setStatusComunicacao(FabStatusComunicacao pStatus) {
        status = pStatus;
    }

    @Override
    public void setAssunto(String pAssunto) {
        assunto = pAssunto;
    }

}
