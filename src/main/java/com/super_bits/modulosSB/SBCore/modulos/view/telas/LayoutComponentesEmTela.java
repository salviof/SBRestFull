/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.telas;

import com.super_bits.modulosSB.SBCore.modulos.view.telas.layout.ItfLayoutComponentesEmTela;
import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreLayoutComponenteEmTelaBasico;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSBEmLayout;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.layout.ItfColunaTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.layout.ItfLinhaTela;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public class LayoutComponentesEmTela implements ItfLayoutComponentesEmTela {

    private Map<Integer, ItfComponenteVisualSBEmLayout> componentesOrdenados;
    private Map<String, ColunaTela> mapaColunas;
    private Map<Integer, String> mapaColunaOrdenada;
    private Map<Integer, List<String>> colunasPorPeso;
    private final String nomeIdentificadoLayout;

    private final int colunasMaximo;

    private final ItfTipoTela tipoTelaDoUsuario;
    private final boolean permitirEspremer;
    private boolean colunasGeradas = false;
    private GridLayoutTela layoutModoGrid;

    public LayoutComponentesEmTela(ItfTipoTela pTipoTela, String pNomeIdentificador, boolean pEspremerSeNaoCouber) {
        tipoTelaDoUsuario = pTipoTela;
        permitirEspremer = pEspremerSeNaoCouber;
        colunasMaximo = pTipoTela.getColunas().getQuantidade();
        nomeIdentificadoLayout = pNomeIdentificador;

    }

    @Override
    public ColunaTela getUltimaColuna() {
        if (!colunasGeradas) {
            gerarColunas();
        }
        String ultimoCampo = mapaColunaOrdenada.get(mapaColunaOrdenada.size() - 1);

        return mapaColunas.get(ultimoCampo);
    }

    @Override
    public void adicionarComponente(ItfComponenteVisualSBEmLayout pComponente, String identificacaoColuna) {
        try {
            if (colunasGeradas) {
                throw new UnsupportedOperationException("Impossível adicionar novo componente, as colunas já foram geradas");
            }

            if (componentesOrdenados == null) {
                componentesOrdenados = new HashMap<>();
            }
            componentesOrdenados.put(componentesOrdenados.size(), pComponente);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Adicionando novo componente no layout", t);
        }
    }

    protected void gerarColunas() {
        try {
            colunasGeradas = true;
            mapaColunas = new HashMap<>();
            colunasPorPeso = new HashMap<>();
            mapaColunaOrdenada = new HashMap<>();
            int somaPeso = 0;
            int ordem = 0;
            for (ItfComponenteVisualSBEmLayout cp : componentesOrdenados.values()) {
                int peso = cp.getPesoLargura();
                somaPeso = somaPeso + peso;
                if (colunasPorPeso.get(cp.getPesoLargura()) == null) {
                    colunasPorPeso.put(peso, new ArrayList<>());
                }
                colunasPorPeso.get(peso).add(cp.getNomeIdentificador());
                ColunaTela novaColuna = new ColunaTela();
                novaColuna.setComponenteVinculado(cp);
                novaColuna.setPesoLarguraIdeal(cp.getPesoLargura());

                novaColuna.setPorcentagemIdeal(cp.getPesoLargura() * UtilSBCoreLayoutComponenteEmTelaBasico.VALOR_UNIDADE_COLUNA_PORCENTAGEM);
                mapaColunas.put(cp.getNomeIdentificador(), novaColuna);
                mapaColunas.put(nomeIdentificadoLayout + "." + cp.getNomeIdentificador(), novaColuna);

                mapaColunaOrdenada.put(ordem, cp.getNomeIdentificador());
                ordem++;
            }

//            System.out.println("pesoTotal=" + somaPeso);
            int restoColuna = 12 - somaPeso;

            double VALOR_UNIDADE_COLUNA_PORCENTAGEM_DO_LAYOUT = 100 / somaPeso;
            mapaColunas.values().forEach(coluna -> coluna.setUnidadePesoLayoutAtual(VALOR_UNIDADE_COLUNA_PORCENTAGEM_DO_LAYOUT));
            if (somaPeso > colunasMaximo) {
                if (!permitirEspremer) {
                    throw new UnsupportedOperationException("A largura dos componentes ultrapassa o tamanho da tela do usuário" + somaPeso);
                }
            }
            List<Integer> listaOrdemMaiorMenor = Lists.reverse(Lists.newArrayList(colunasPorPeso.keySet()));
            int idTipoColunaAtual = 0;
            int idColunaAtual = 0;
            List<String> colunasDotipo = null;
            while (restoColuna != 0) {
                if (colunasDotipo == null || colunasDotipo.size() - 1 < idColunaAtual) {
                    if (idTipoColunaAtual != 0 || idColunaAtual != 0) {
                        idTipoColunaAtual++;
                        idColunaAtual = 0;
                    }
                    if (idTipoColunaAtual == listaOrdemMaiorMenor.size()) {
                        idTipoColunaAtual = 0;
                    }
                    int tamanho = listaOrdemMaiorMenor.get(idTipoColunaAtual);
                    colunasDotipo = colunasPorPeso.get(tamanho);

                }
                String pNomeColuna = "nomeNaoDefinido";
                try {

                    List<String> listaCamposTamanhoAtual = colunasPorPeso.get(listaOrdemMaiorMenor.get(idTipoColunaAtual));
                    pNomeColuna = listaCamposTamanhoAtual.get(listaCamposTamanhoAtual.size() - 1 - idColunaAtual);
                } catch (Throwable t) {
                    throw new UnsupportedOperationException("Erro localizando a o nome da coluna via cordenada, tamanho+idCOluna, idTipoColunaAtual=" + idTipoColunaAtual + "IdColuna=" + idColunaAtual);
                }

                ColunaTela coluna = mapaColunas.get(pNomeColuna);
                if (coluna == null) {
                    throw new UnsupportedOperationException("a coluna " + pNomeColuna + " não foi encontrada no mapada de colunas");
                } else {

                    coluna.incrementarTamanhoColuna();
                    idColunaAtual++;
                    if (restoColuna > 0) {
                        restoColuna--;
                    }
                    if (restoColuna < 0) {
                        restoColuna++;
                    }
                }
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando colunas do layout de componentes", t);
        }
    }

    @Override
    public List<ItfColunaTela> getColunas() {
        if (mapaColunas == null) {
            gerarColunas();
        }

        List colunaOrdenada = new ArrayList();
        mapaColunaOrdenada.keySet().forEach((id) -> {
            colunaOrdenada.add(mapaColunas.get(mapaColunaOrdenada.get(id)));
        });

        return colunaOrdenada;
    }

    @Override
    public ColunaTela getColunaByNomeCompleto(String pNomeCampoCompleto) {
        try {

            return getColunaByNome(pNomeCampoCompleto);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo coluna por nome do campo" + pNomeCampoCompleto, t);
            return null;
        }
    }

    @Override
    public ColunaTela getColunaByNome(String pNome) {
        try {
            if (!colunasGeradas) {
                gerarColunas();
            }
            return mapaColunas.get(pNome);
        } finally {
            try {
                if (mapaColunas.get(pNome) == null) {
                    throw new UnsupportedOperationException("Coluna [" + pNome + "] não foi  encontrada no grupo campo: " + nomeIdentificadoLayout);
                }
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo Layout de coluna" + pNome, t);
            }
        }
    }

    @Override
    public GridLayoutTela getGridLayoutTela() {
        if (layoutModoGrid == null) {
            layoutModoGrid = new GridLayoutTela(tipoTelaDoUsuario);

            getColunas().forEach(col -> layoutModoGrid.adicionarColuna(col));

        }
        return layoutModoGrid;
    }

    @Override
    public List<ItfLinhaTela> getLinhas() {
        return getGridLayoutTela().getLinhas();

    }

}
