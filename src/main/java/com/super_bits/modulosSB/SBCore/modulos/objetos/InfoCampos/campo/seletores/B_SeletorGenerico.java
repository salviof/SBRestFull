/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreListasObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringComparador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.ItemSimilar;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 * @param <T>
 */
public abstract class B_SeletorGenerico<T extends ItfBeanSimplesSomenteLeitura>
        implements ItfSeletorGenerico<T> {

    protected final ItfCampoInstanciado campoInstanciado;

    /**
     * A origem disponível para seleção, caso a lista seja fixa, o valor deve
     * ser null
     */
    protected final List<T> origem;
    protected final List<T> listaCompleta;
    protected int quantidadeExibicaoPesquisa = 10;
    private String nomeListaOrigem;
    private String filtro = "";
    int quantidadeMinimaPesquisa = 4;

    public B_SeletorGenerico(ItfCampoInstanciado pCampoInstanciado) {
        this.campoInstanciado = pCampoInstanciado;
        origem = new ArrayList<>();
        listaCompleta = Collections.synchronizedList(new ArrayList());
    }

    public B_SeletorGenerico(List<T> pOrigem) {
        campoInstanciado = null;
        origem = (List) pOrigem;
        listaCompleta = new ArrayList();
        pOrigem.forEach(listaCompleta::add);
    }

    private void loadOrigemLasyMode() {
        if (origem.isEmpty()) {
            filtrar();
        }
    }

    private List<T> filtroSimilaridadeEmListaCompleta(final String pParamentro) {
        List resp = new ArrayList();
        Map<Integer, ItemSimilar> itens = listaCompleta.stream().parallel()
                .collect(Collectors.toMap(n -> ((ItfBeanSimples) n).getId(),
                        n -> new ItemSimilar((ItfBeanSimples) n, pParamentro)));

//(prodsml->produtosOrdenados.add(new ItemSimilar(prodsml, "coca ")));
        List<ItemSimilar> produtosOrdenados = itens.values().stream().parallel().collect(Collectors.toList());
        Collections.sort(produtosOrdenados);
        //Collections.reverse(produtosOrdenados);

        produtosOrdenados.stream().limit(quantidadeExibicaoPesquisa).forEach(item -> resp.add(item.getObjetoAnalizado()));
        return resp;
    }

    private synchronized List<T> getOrigemSincronized() {
        return origem;
    }

    private void filtrar() {

        origem.clear();
        boolean apenasNumero = UtilSBCoreStringValidador.isContemApenasNumero(filtro);

        if (UtilSBCoreStringValidador.isNuloOuEmbranco(filtro)) {
            getListaCompletaLasyMode().forEach(origem::add);
        } else {
            try {

                if (!apenasNumero) {
                    UtilSBCoreListasObjeto.filtrarOrdenandoMaisParecidos(getListaCompletaLasyMode(), filtro, quantidadeMinimaPesquisa).forEach(origem::add);
                } else {
                    getListaCompletaLasyMode().parallelStream().filter(item
                            -> UtilSBCoreStringComparador.isParecido((ItfBeanSimples) item, campoInstanciado.getGrupoCampoExibicao().getCampos(), filtro, apenasNumero))
                            .forEach(getOrigemSincronized()::add);
                }

            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando Pesquisa completa ", t);
                UtilSBCoreListasObjeto.filtrarOrdenandoMaisParecidos(getListaCompletaLasyMode(), filtro, quantidadeMinimaPesquisa).forEach(origem::add);
            }
        }

    }

    private void atualizarListagens(boolean pAtualizarListaCompleta, String pFiltro) {
        filtro = pFiltro;
        if (pAtualizarListaCompleta || (listaCompleta == null || listaCompleta.isEmpty())) {
            atualizarListaCompleta();
        }
        filtrar();
    }

    protected void adicionarItemListaCompleta(Object it) {
        listaCompleta.add((T) it);
    }

    public List<T> getListaCompletaLasyMode() {
        if (listaCompleta.isEmpty()) {
            SBCore.getServicoFonteDeDadosParaAtributos().getListaOpcoesCampoInstanciado(campoInstanciado).parallelStream()
                    .forEach(this::adicionarItemListaCompleta);
        }
        return listaCompleta;
    }

    @Override
    public List<T> getOrigem() {
        loadOrigemLasyMode();
        return origem;
    }

    @Override
    public String getNomeOrigem() {
        if (nomeListaOrigem == null) {
            if (campoInstanciado != null) {
                nomeListaOrigem = campoInstanciado.getNomeDoObjeto() + " disponíveis ";
            } else {
                nomeListaOrigem = "Opções disponíveis";
            }
        }
        return nomeListaOrigem;

    }

    @Override
    public void setNomeOrigem(String pNomeString) {
        nomeListaOrigem = pNomeString;
    }

    @Override
    public void setFiltro(String pFiltro) {
        int qtdCaracteresAnteriores = filtro.length();
        int qtdCaracteresNovoFiltro = pFiltro.length();
        int qtdDiferencaCaracteresFiltro = qtdCaracteresNovoFiltro - qtdCaracteresAnteriores;
        if (filtro.equals(pFiltro)) {
            return;
        }
        if (pFiltro.contains(filtro) && filtro.length() > getMinimoParaPesquisa()) {
            atualizarListagens(false, pFiltro);
            return;
        }

        if (pFiltro.contains(filtro)) {

            if (qtdDiferencaCaracteresFiltro < 0) {

                atualizarListagens(false, pFiltro);
                return;
            }

            if (qtdDiferencaCaracteresFiltro < getMinimoParaPesquisa()) {
                if (origem.isEmpty()) {
                    //atualizarListagens(true, pFiltro);
                    atualizarListagens(false, pFiltro);
                } else {
                    atualizarListagens(false, pFiltro);
                }
            } else {
                //  atualizarListagens(true, pFiltro);
                atualizarListagens(false, pFiltro);
            }
        } else {
            if (qtdCaracteresNovoFiltro >= getMinimoParaPesquisa()) {
                //atualizarListagens(true, pFiltro);
                atualizarListagens(false, pFiltro);

            }

        }
    }

    @Override
    public void atualizaOrigemPelaSelecao() {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    @Override
    public void reloadOrigem() {
        getOrigem().clear();
        getOrigem();
    }

    @Override
    public void limparSelecao() {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    @Override
    public void atualizarListaCompleta() {
        if (campoInstanciado != null) {
            listaCompleta.clear();
            getListaCompletaLasyMode();
        }
    }

    @Override
    public List filtrarPorAutoComplet(String pParametro) {

        setFiltro(filtro);
        return getOrigem();

    }

    @Override
    public String getFiltro() {
        return filtro;
    }

    @Override
    public void listarPrimeiros50() {
        getOrigem().clear();
        getListaCompletaLasyMode().stream().limit(50).forEach(getOrigem()::add);
    }

    @Override
    public void limparFiltro() {
        filtro = "";
    }

    @Override
    public int getMinimoParaPesquisa() {
        return quantidadeMinimaPesquisa;
    }

    @Override
    public void setMinimoParaPesquisa(int minimoParaPesquisa) {
        quantidadeMinimaPesquisa = minimoParaPesquisa;
    }

}
