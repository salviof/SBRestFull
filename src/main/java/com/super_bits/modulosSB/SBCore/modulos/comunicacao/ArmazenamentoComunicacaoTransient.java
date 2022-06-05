/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ItfArmazenamentoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author SalvioF
 */
public class ArmazenamentoComunicacaoTransient implements ItfArmazenamentoComunicacao {

    private final Map<String, ItfComunicacao> comunicacoesAtivas = new HashMap<>();

    @Override
    public boolean registrarInicioComunicacao(ItfComunicacao pComunicacao) {
        try {
            pComunicacao.selarComunicacao();
            comunicacoesAtivas.put(pComunicacao.getCodigoSelo(), pComunicacao);
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Armazenando comunicação " + t.getMessage(), t);
            return false;
        }
    }

    @Override
    public boolean regsitrarRespostaComunicacao(String codigoComunicacao, ItfRespostaComunicacao pResposta) {
        try {
            ItfComunicacao comunicacao = comunicacoesAtivas.get(codigoComunicacao);
            if (codigoComunicacao == null) {
                throw new UnsupportedOperationException("Comunicação não encontrada no sistema");
            }
            comunicacao.setRespostaEscolhida(pResposta);
            comunicacoesAtivas.remove(codigoComunicacao);
            return true;
        } catch (Throwable t) {
            return false;
        }

    }

    @Override
    public boolean limparComunicacaoExpirada() {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class OrdemComunicacaoMaisNovoPrimeiro implements Comparator<ItfComunicacao> {

        @Override
        public int compare(ItfComunicacao o1, ItfComunicacao o2) {

            return (o1.getDataHoraDisparo().getTime() < o2.getDataHoraDisparo().getTime() ? 1 : -1);

        }

    }

    private boolean isComunicacaoEdousuario(ItfUsuario pUsuario, ItfComunicacao pComunicacao) {
        switch (pComunicacao.getDestinatario().getTipoDestinatario()) {
            case USUARIO:
                if (pUsuario.equals(pComunicacao.getDestinatario().getUsuario())) {
                    return true;
                }
                break;
            case USUARIOS:
                if (pComunicacao.getDestinatario().getUsuarios().contains(pUsuario)) {
                    return true;
                }
                break;
            case GRUPO:
                if (pComunicacao.getDestinatario().getGrupoUsuario().equals(pUsuario.getGrupo())) {
                    return true;
                }
                break;
            case GRUPOS:
                if (pComunicacao.getDestinatario().getGruposUsuario().contains(pUsuario.getGrupo())) {
                    return true;
                }
            default:
                throw new AssertionError(pComunicacao.getDestinatario().getTipoDestinatario().name());

        }
        return false;
    }

    @Override
    public List<ItfComunicacao> getComunicacoesAguardandoRespostaDoDestinatario(ItfUsuario pDestinatario) {
        List<ItfComunicacao> comunicacoes = new ArrayList<>();

        comunicacoesAtivas.values().stream().
                filter(cm -> isComunicacaoEdousuario(pDestinatario, cm)).
                sorted(new OrdemComunicacaoMaisNovoPrimeiro()).forEach(comunicacoes::add);

        return comunicacoes;
    }

    @Override
    public List<ItfComunicacao> getComunicacoesAguardandoRespostaDoRemetente(ItfUsuario pRemetente) {
        List<ItfComunicacao> comunicacoesEncontradas = new ArrayList<>();
        comunicacoesAtivas.values().stream().filter((cm) -> (cm.getUsuarioRemetente().equals(pRemetente))).forEachOrdered((cm) -> {
            comunicacoesEncontradas.add(cm);
        });
        return comunicacoesEncontradas;

    }

    @Override
    public ItfComunicacao getComunicacaoByCodigoSelo(String pCodigoSelo) {
        return comunicacoesAtivas.get(pCodigoSelo);
    }

}
