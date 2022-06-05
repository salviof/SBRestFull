/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.List;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreComunicacao;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(plural = "Comunicações", tags = "Comunicação")
public class ComunicacaoTransient extends ComunicacaoAbstrata implements ItfComunicacao {

    private final ItfDestinatario destinatario;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String descricao;
    private final ItfUsuario usuarioRemetente;
    private final List<ItfRespostaComunicacao> respostasPossiveis;
    private final List<ItfTipoTransporteComunicacao> transportes;
    private final ItfTipoComunicacao tipoComunicacao;
    private String mensgem;
    private String assunto;
    private ItfRespostaComunicacao respostaEscolhida;

    private long tempoAceitavelResposta = -1;

    public ComunicacaoTransient(ItfUsuario usuarioRemetente,
            ItfUsuario destinatario, ItfTipoComunicacao tipoComunicacao,
            List<ItfTipoTransporteComunicacao> transportes) {
        this.destinatario = new DestinatarioTransiente(destinatario);
        this.usuarioRemetente = usuarioRemetente;

        this.transportes = transportes;
        this.tipoComunicacao = tipoComunicacao;
        this.respostasPossiveis = UtilSBCoreComunicacao.getRespostaCOmunicacao(this);
        defineMensagem();

    }

    public final void defineMensagem() {
        mensgem = UtilSBCoreComunicacao.gerarMensagem(this);
        assunto = UtilSBCoreComunicacao.gerarAssunto(this);

    }

    public ComunicacaoTransient(ItfUsuario usuarioRemetente, ItfDestinatario destinatario, ItfTipoComunicacao tipoComunicacao,
            List<ItfTipoTransporteComunicacao> transportes) {
        this.destinatario = destinatario;
        this.usuarioRemetente = usuarioRemetente;
        this.tipoComunicacao = tipoComunicacao;
        this.respostasPossiveis = UtilSBCoreComunicacao.getRespostaCOmunicacao(this);
        this.transportes = transportes;

        defineMensagem();

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
    public ItfTipoComunicacao getTipoComunicacao() {
        return tipoComunicacao;
    }

    @Override
    public List<ItfRespostaComunicacao> getRepostasPossiveis() {
        return respostasPossiveis;
    }

    @Override
    public List<ItfTipoTransporteComunicacao> getTransportes() {
        return transportes;
    }

    @Override
    public String getMensagem() {
        if (mensgem == null) {
            UtilSBCoreComunicacao.gerarMensagem(this);
        }
        return mensgem;
    }

    @Override
    public long getTempoAceitavelResposta() {
        return tempoAceitavelResposta;
    }

    @Override
    public ItfRespostaComunicacao getRespostaEscolhida() {
        return respostaEscolhida;
    }

    @Override
    public void setRespostaEscolhida(ItfRespostaComunicacao pResposta) {
        respostaEscolhida = pResposta;
    }

    public String getDescricao() {
        if (descricao == null) {
            descricao = "cm" + getUsuarioRemetente().getEmail() + "às ";
        }
        return descricao;
    }

    @Override
    public void setTempoAceitavelResposta(long pTempo) {
        tempoAceitavelResposta = pTempo;
    }

    public String getMensgem() {
        return mensgem;
    }

    @Override
    public String getAssunto() {
        return assunto;
    }

    @Override
    public void setMensagem(String pMensagem) {
        mensgem = pMensagem;
    }

    @Override
    public void setNome(String pAssunto) {
        assunto = pAssunto;
    }

    @Override
    public void setAssunto(String pAssunto) {
        assunto = pAssunto;
    }

    @Override
    public boolean isAssuntoIgualMensagem() {
        if (getMensagem() == null) {
            return false;
        }
        return getMensagem().equals(getNome());
    }

}
