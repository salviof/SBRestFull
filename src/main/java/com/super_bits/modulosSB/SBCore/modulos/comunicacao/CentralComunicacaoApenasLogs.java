/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ItfArmazenamentoComunicacao;
import br.org.coletivojava.erp.comunicacao.transporte.ERPTransporteComunicacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAplicacaoEmExecucao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class CentralComunicacaoApenasLogs extends CentralComunicaoAbstrato {

    private final ArmazenamentoComunicacaoTransient aramazenamento;

    public CentralComunicacaoApenasLogs() {
        aramazenamento = new ArmazenamentoComunicacaoTransient();
    }

    @Override
    public ItfComunicacao gerarComunicacaoEntre_Usuairos(FabTipoComunicacao tipocomunicacao,
            ItfUsuario pRemetente, ItfDestinatario pDestinatario,
            String mensagem,
            ItffabricaTrasporteComunicacao... tiposTransporte
    ) {

        try {
            ItfComunicacao comunicacao
                    = new ComunicacaoTransient(pRemetente, pDestinatario, tipocomunicacao.getRegistro(), gerarListaTransportes(tiposTransporte));

            comunicacao.setMensagem(mensagem);
            comunicacao.setNome(mensagem);
            if (getAramazenamento().registrarInicioComunicacao(comunicacao)) {
                return comunicacao;
            } else {
                return null;
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando comunicação entre usuários", t);
            return null;
        }
    }

    @Override
    public ItfComunicacao iniciarComunicacaoEntre_Usuairos(FabTipoComunicacao tipocomunicacao, ItfUsuario pRemetente, ItfDestinatario pDestinatario, ItfModeloMensagem mensagem, ItffabricaTrasporteComunicacao... tiposTransporte) {
        ItfComunicacao comunicacao = new ComunicacaoTransient(pRemetente, pDestinatario, tipocomunicacao.getRegistro(), gerarListaTransportes(tiposTransporte));

        getAramazenamento().registrarInicioComunicacao(comunicacao);

        for (ItffabricaTrasporteComunicacao tipoTranposporte : tiposTransporte) {
            try {

                ItfDisparoComunicacao disparo = (ItfDisparoComunicacao) tipoTranposporte.getImplementacaoDoContexto();
                disparo.dispararInicioComunicacao(comunicacao);
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando mensagem de comunicação", t);
            }
        }
        throw new UnsupportedOperationException("Processador de mensagem ainda não implementado;");

    }

    @Override
    public FabTipoRespostaComunicacao aguardarRespostaComunicacao(ItfTipoTransporteComunicacao pTransporte,
            ItfComunicacao pComunicacao, int tempoAguardar, FabTipoRespostaComunicacao pTipoRespostaTempoFinal) {
        FabTipoComunicacao tipocomunicacao = pComunicacao.getTipoComunicacao().getFabTipoComunicacao();

        return FabTipoRespostaComunicacao.NAO;

    }

    @Override
    public ItfComunicacao gerarComunicacaoSistema_UsuairoLogado(FabTipoComunicacao tipocomunicacao, String mensagem, ItffabricaTrasporteComunicacao... tiposTransporte) {
        ItfComunicacao comunicacao
                = new ComunicacaoTransient(new UsuarioAplicacaoEmExecucao(), SBCore.getUsuarioLogado(),
                        tipocomunicacao.getRegistro(), gerarListaTransportes(tiposTransporte));
        comunicacao.setMensagem(mensagem);
        comunicacao.setNome(mensagem);
        if (getAramazenamento().registrarInicioComunicacao(comunicacao)) {

            return comunicacao;
        } else {
            return null;
        }
    }

    @Override
    public ItfArmazenamentoComunicacao getAramazenamento() {
        return aramazenamento;
    }

    @Override
    public ItffabricaTrasporteComunicacao getFabricaTransportePadrao() {
        return ERPTransporteComunicacao.INTRANET_MENU;
    }
}
