/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.componenteObjeto;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.view.ServicoVisualizacaoAbstrato;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SalvioF
 */
public class ContainersVisualizacaoDoObjeto {

    private final Map<String, Map<Integer, ContainerVisualizacaoObjeto>> MAPA_CONTAINERS_DISPONIVEIS = new HashMap<>();
    private ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM tipoContainer;
    private final String tipoVisualizacaoParao;
    private final Class classe;
    private boolean possuiVisualizacoes;
    private boolean processouVisualizacoes;

    private String nomeClasse;
    private List<String> listaVisualizacoesPossiveis;

    public String getContainerDesk1Coluna() {
        return gerarCaminhoRelativoMasProximoAColuna(tipoVisualizacaoParao, 1, false);
    }

    public String getContainerDesk3Colunas() {
        return gerarCaminhoRelativoMasProximoAColuna(tipoVisualizacaoParao, 3, false);
    }

    public String getContainerDesk6Colunas() {
        return gerarCaminhoRelativoMasProximoAColuna(tipoVisualizacaoParao, 6, false);
    }

    public String getContainerDesk9Colunas() {
        return gerarCaminhoRelativoMasProximoAColuna(tipoVisualizacaoParao, 9, false);
    }

    public String getContainerDesk12Colunas() {
        return gerarCaminhoRelativoMasProximoAColuna(tipoVisualizacaoParao, 12, false);
    }

    public String getContainerDeskColunaEsp(Integer pColunas) {
        if (pColunas == null) {
            pColunas = 3;
        }
        return gerarCaminhoRelativoMasProximoAColuna(tipoVisualizacaoParao, pColunas, false);
    }

    public String getContainerMobileColunaEsp(Integer pColunas) {
        if (pColunas == null) {
            pColunas = 3;
        }
        return gerarCaminhoRelativoMasProximoAColuna(tipoVisualizacaoParao, pColunas, false);
    }

    public String getContainerMobile1Colunas() {
        return gerarCaminhoRelativoMasProximoAColuna(tipoVisualizacaoParao, 1, false);
    }

    public String getContainerMobile3Colunas() {
        return gerarCaminhoRelativoMasProximoAColuna(tipoVisualizacaoParao, 3, false);
    }

    public String getContainerMobile6Colunas() {
        return gerarCaminhoRelativoMasProximoAColuna(tipoVisualizacaoParao, 6, false);
    }

    public String getContainerMobile9Colunas() {
        return gerarCaminhoRelativoMasProximoAColuna(tipoVisualizacaoParao, 9, false);
    }

    public String getContainerMobile12Colunas() {
        return gerarCaminhoRelativoMasProximoAColuna(tipoVisualizacaoParao, 12, false);
    }

    public ContainersVisualizacaoDoObjeto(Class pClasse, ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM pTipoVisualizacao) {
        try {
            this.classe = pClasse;
            tipoVisualizacaoParao = ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_PADRAO;
            tipoContainer = pTipoVisualizacao;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando container de visualizacao do obejto" + pClasse, t);
            throw new UnsupportedOperationException("Erro criando container de visualização");
        }
    }

    public ContainersVisualizacaoDoObjeto(Class pClasse) {
        this(pClasse, ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM.PUBLICADO);

    }

    public ContainerVisualizacaoObjeto getContainerAdequado(String pTipoVisualizaca, int pNumeroColunas, boolean especialMobile) {
        if (!processouVisualizacoes) {
            reloadVisualizacoes();

        }

        if (!possuiVisualizacoes) {

            //TODO pesquisar em contaniers de objeto Filho
            return null;
        }
        if (pTipoVisualizaca == null) {
            pTipoVisualizaca = ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_PADRAO;
        }
        Map<Integer, ContainerVisualizacaoObjeto> visualizacoes = MAPA_CONTAINERS_DISPONIVEIS.get(pTipoVisualizaca);
        if (visualizacoes.get(pNumeroColunas) != null) {

            return visualizacoes.get(pNumeroColunas);

        }
        List<Integer> listaOrdenada = Lists.newArrayList(visualizacoes.keySet());

        Collections.sort(listaOrdenada);
        int colunaAnterior = 0;
        ContainerVisualizacaoObjeto ultimaVersaoMobile = null;

        // Encontra o numero de colunas mais proximo existente no sistema.
        // caso seja visualização especial mobile, e a coluna mais próxima posusir uma versão mobile, envia a versão mobile
        for (Integer nColunaAtual : listaOrdenada) {

            if (nColunaAtual > pNumeroColunas) {
                if (!especialMobile) {
                    if (colunaAnterior == 0) {
                        return visualizacoes.get(nColunaAtual);
                    }
                    if ((nColunaAtual - pNumeroColunas) < (pNumeroColunas - colunaAnterior)) {
                        return visualizacoes.get(nColunaAtual);
                    } else {
                        return visualizacoes.get(colunaAnterior);
                    }
                }

            }

            if (especialMobile) {
                if (visualizacoes.get(nColunaAtual).isTemVersaoMobile()) {
                    ultimaVersaoMobile = visualizacoes.get(nColunaAtual);
                }
            }
            colunaAnterior = nColunaAtual;
        }
        if (ultimaVersaoMobile != null) {
            return ultimaVersaoMobile;
        }

        return null;
    }

    public String gerarCaminhoRelativoMasProximoAColuna(String pTipoVisualizaca, int pNumeroColunas, boolean especialMobile) {
        ContainerVisualizacaoObjeto containerAdequado = getContainerAdequado(pTipoVisualizaca, pNumeroColunas, especialMobile);
        if (containerAdequado == null) {
            return SBCore.getCentralVisualizacao().buildCaminhoRelativoItemSimples();
        }
        if (especialMobile || containerAdequado.isTemVersaoMobile()) {
            return containerAdequado.getCaminhoRelativoMobile();
        } else {
            return containerAdequado.getCaminhoRelativo();
        }

    }

    public void reloadVisualizacoes() {
        MAPA_CONTAINERS_DISPONIVEIS.clear();
        String caminhoRelativo = null;
        switch (tipoContainer) {
            case LABORATORIO:
                caminhoRelativo = SBCore.getCentralVisualizacao().getCaminhoPastaContainerLaboratorio(classe);
                break;
            case PUBLICADO:
                caminhoRelativo = SBCore.getCentralVisualizacao().getCaminhoPastaContainerPublicado(classe);
                break;
            default:
                throw new AssertionError(tipoContainer.name());

        }

        String caminhoResource = SBCore.getCentralDeArquivos().getEntrLocalArquivosFormulario() + "/" + caminhoRelativo;
        File pastaContainers = new File(caminhoResource);
        listaVisualizacoesPossiveis = new ArrayList<>();
        if (!pastaContainers.exists()) {
            System.out.println("Nenhum arquivo encontrado em " + caminhoResource);
            possuiVisualizacoes = false;
            return;
        }
        for (File pastaTipoVisualizacao : pastaContainers.listFiles()) {
            if (pastaTipoVisualizacao.isDirectory()) {
                if (!UtilSBCoreStringValidador.isContemApenasNumero(pastaTipoVisualizacao.getName())) {
                    listaVisualizacoesPossiveis.add(pastaTipoVisualizacao.getName());
                    MAPA_CONTAINERS_DISPONIVEIS.put(pastaTipoVisualizacao.getName(), new HashMap<>());
                    boolean temJavascript = false;
                    boolean temFolhaDeEstilo = false;

                    for (File arquivoPastaVisualizacao : pastaTipoVisualizacao.listFiles()) {
                        if (pastaContainers.isFile()) {
                            if (arquivoPastaVisualizacao.getName().equals(SBCore.getCentralVisualizacao().buildArqJavaScript(classe, pastaTipoVisualizacao.getName()))) {
                                temJavascript = true;
                            }
                            if (arquivoPastaVisualizacao.getName().equals(SBCore.getCentralVisualizacao().buildArqCSSFolhaDeEstilo(classe, pastaTipoVisualizacao.getName()))) {
                                temFolhaDeEstilo = true;
                            }
                        }
                    }
                    for (File pataQuantidadeColunas : pastaTipoVisualizacao.listFiles()) {
                        if (pastaContainers.isDirectory()) {
                            String nomePataColunas = pataQuantidadeColunas.getName();
                            if (UtilSBCoreStringValidador.isContemApenasNumero(nomePataColunas)) {
                                int coluna = Integer.valueOf(nomePataColunas);
                                boolean temVersaoMobile = false;
                                boolean arquivoEncontrado = false;
                                for (File arquivo : pataQuantidadeColunas.listFiles()) {

                                    if (arquivo.getName().equals(SBCore.getCentralVisualizacao().buildArqXHTML(classe, pastaTipoVisualizacao.getName(), false))) {
                                        arquivoEncontrado = true;
                                    }
                                    if (arquivo.getName().equals(SBCore.getCentralVisualizacao().buildArqXHTML(classe, pastaTipoVisualizacao.getName(), true))) {
                                        temVersaoMobile = true;
                                    }
                                }
                                if (arquivoEncontrado) {
                                    MAPA_CONTAINERS_DISPONIVEIS.get(pastaTipoVisualizacao.getName()).put(coluna,
                                            new ContainerVisualizacaoObjeto(temJavascript, temFolhaDeEstilo, temVersaoMobile, coluna, pastaTipoVisualizacao.getName(), classe, tipoContainer));

                                }

                            }
                        }
                    }
                    if (MAPA_CONTAINERS_DISPONIVEIS.get(pastaTipoVisualizacao.getName()).isEmpty()) {
                        MAPA_CONTAINERS_DISPONIVEIS.remove(pastaTipoVisualizacao.getName());
                    }
                }
            }
        }
        processouVisualizacoes = true;
        possuiVisualizacoes = !MAPA_CONTAINERS_DISPONIVEIS.isEmpty();

    }

    public boolean isPossuiVisualizacoes() {
        if (!processouVisualizacoes) {
            reloadVisualizacoes();
        }
        return possuiVisualizacoes;
    }

    public List<String> getListaVisualizacoesPossiveis() {
        return listaVisualizacoesPossiveis;
    }

    public Map<Integer, ContainerVisualizacaoObjeto> getContainerPorTipoVisualizacao(String pTipo) {
        return MAPA_CONTAINERS_DISPONIVEIS.get(pTipo);
    }

    public List<ContainerVisualizacaoObjeto> getContainersEncontrados() {
        if (!processouVisualizacoes) {
            reloadVisualizacoes();
        }
        if (!isPossuiVisualizacoes()) {
            return new ArrayList<>();
        }
        List<ContainerVisualizacaoObjeto> contaniersEncontrados = new ArrayList<>();
        for (Map<Integer, ContainerVisualizacaoObjeto> listas : MAPA_CONTAINERS_DISPONIVEIS.values()) {
            contaniersEncontrados.addAll(Lists.newArrayList(listas.values()));
        }
        return contaniersEncontrados;

    }

}
