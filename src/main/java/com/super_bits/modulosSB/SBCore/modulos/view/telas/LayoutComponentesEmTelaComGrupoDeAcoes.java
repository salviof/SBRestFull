/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.telas;

import com.super_bits.modulosSB.SBCore.modulos.view.telas.layout.ItfLayoutComponentesEmTelaComGrupoDeAcoes;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualBotaoAcao;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public class LayoutComponentesEmTelaComGrupoDeAcoes extends LayoutComponentesEmTela implements ItfLayoutComponentesEmTelaComGrupoDeAcoes {

    public static final String NOME_IDENTIFICADO_GRUPO_CAMPO = "acoesGrupoCampo";
    private Map<Integer, ItfAcaoDoSistema> grupoDeAcoes;
    private String nomeGrupoDeAcoes;
    private final FabCompVisualBotaoAcao tipoVisualizacaoBotao;
    private final int numeroMaximoDeAcoes;

    public LayoutComponentesEmTelaComGrupoDeAcoes(FabCompVisualBotaoAcao pFabrica,
            ItfTipoTela pTipoTela, String pNomeIdentificador, boolean pEspremerSeNaoCouber, List<ItfAcaoDoSistema> pAcoes) {
        super(pTipoTela, pNomeIdentificador, pEspremerSeNaoCouber);
        tipoVisualizacaoBotao = pFabrica;

        numeroMaximoDeAcoes = pAcoes.size();
    }

    @Override
    public Map<Integer, ItfAcaoDoSistema> getGrupoDeAcoes() {
        return grupoDeAcoes;
    }

    @Override
    public String getNomeGrupoDeAcoes() {
        return nomeGrupoDeAcoes;
    }

    @Override
    public int getPixelPorNumeroDeAcoes() {
        if (numeroMaximoDeAcoes == 1) {
            return 48;
        }
        if (numeroMaximoDeAcoes == 2) {
            return 95;
        }
        if (numeroMaximoDeAcoes == 4) {
            return 95;
        }
        if (numeroMaximoDeAcoes == 3) {
            return 132;
        }
        if (numeroMaximoDeAcoes >= 5 && numeroMaximoDeAcoes <= 6) {
            return 132;
        }
        return 132;
    }

}
