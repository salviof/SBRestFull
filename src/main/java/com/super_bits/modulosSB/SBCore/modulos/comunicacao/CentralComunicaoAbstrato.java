/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ItfCentralComunicacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAplicacaoEmExecucao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.coletivojava.fw.api.objetoNativo.log.LogPadraoSB;

/**
 *
 * @author SalvioF
 */
public abstract class CentralComunicaoAbstrato implements ItfCentralComunicacao {

    @Override
    public List<ItfComunicacao> getComunicacoesAguardandoRespostaDoDestinatario(ItfUsuario pDestinatario) {
        return getAramazenamento().getComunicacoesAguardandoRespostaDoDestinatario(pDestinatario);
    }

    @Override
    public List<ItfComunicacao> getComunicacoesAguardandoRespostaDoRemetente(ItfUsuario pRemetente) {
        return getAramazenamento().getComunicacoesAguardandoRespostaDoRemetente(pRemetente);
    }

    @Override
    public boolean responderComunicacao(ItfComunicacao pComunicacao, ItfRespostaComunicacao pResposta) {
        return getAramazenamento().regsitrarRespostaComunicacao(pComunicacao.getCodigoSelo(), pResposta);
    }

    @Override
    public ItfComunicacao iniciarComunicacaoSistema_Usuairo(FabTipoComunicacao tipocomunicacao, ItfUsuario pUsuario, String pAssunto, String mensagem, ItffabricaTrasporteComunicacao... pTiposTransporte) {
        ItfComunicacao comunicacao = new ComunicacaoTransient(new UsuarioAplicacaoEmExecucao(), pUsuario, tipocomunicacao.getRegistro(), gerarListaTransportes(pTiposTransporte));
        comunicacao.setMensagem(mensagem);
        comunicacao.setAssunto(pAssunto);
        comunicacao.setAssunto(pAssunto);

        if (getAramazenamento().registrarInicioComunicacao(comunicacao)) {
            for (ItffabricaTrasporteComunicacao<ItfDisparoComunicacao> transp : pTiposTransporte) {
                try {
                    transp.getImplementacaoDoContexto().dispararInicioComunicacao(comunicacao);
                } catch (Throwable t) {
                    LogManager.getLogger(LogPadraoSB.class).error("A tentativa de Comunicação foi registrada, mas houve um erro em um dos transportes" + transp, t);
                }
            }
            return comunicacao;
        } else {
            return null;
        }
    }

    @Override
    public ItfComunicacao gerarComunicacaoSistema_UsuairoLogado(FabTipoComunicacao tipocomunicacao, String pAssunto, String mensagem, ItffabricaTrasporteComunicacao... pTiposTransporte) {
        return iniciarComunicacaoSistema_Usuairo(tipocomunicacao, SBCore.getUsuarioLogado(), UtilSBCoreStringFiltros.getPrimeirasXLetrasDaString(mensagem, 140), mensagem, pTiposTransporte);
    }

    @Override
    public ItfComunicacao iniciarComunicacaoSistema_Usuairo(FabTipoComunicacao tipocomunicacao, ItfUsuario pUsuario, String mensagem, ItffabricaTrasporteComunicacao... pTiposTransporte) {
        return iniciarComunicacaoSistema_Usuairo(tipocomunicacao, pUsuario, UtilSBCoreStringFiltros.getPrimeirasXLetrasDaString(mensagem, 140), mensagem, pTiposTransporte);
    }

    @Override
    public ItfComunicacao getComnunicacaoRegistrada(String codigoComunicacao) {
        return getAramazenamento().getComunicacaoByCodigoSelo(codigoComunicacao);
    }

    public List<ItfTipoTransporteComunicacao> gerarListaTransportes(ItffabricaTrasporteComunicacao... fabricas) {
        List<ItfTipoTransporteComunicacao> transportes = new ArrayList<>();
        for (ItffabricaTrasporteComunicacao fab : fabricas) {
            transportes.add((ItfTipoTransporteComunicacao) fab.getRegistro());
        }
        return transportes;
    }

}
