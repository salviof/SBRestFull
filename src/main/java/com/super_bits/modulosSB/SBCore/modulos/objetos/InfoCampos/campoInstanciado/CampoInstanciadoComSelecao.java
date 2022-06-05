/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.ItfSeletorGenerico;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class CampoInstanciadoComSelecao implements ItfCampoInstSeletor {

    private final ItfSeletorGenerico seletor;

    private final ItfCampoInstanciado campoInstanciado;

    public CampoInstanciadoComSelecao(ItfSeletorGenerico pSeletor, ItfCampoInstanciado pCampoInstanciado) {

        if (pCampoInstanciado == null) {
            throw new UnsupportedOperationException("Erro instanciando campo com selecao, o campo instanciado não foi especificado");
        }
        this.seletor = pSeletor;
        campoInstanciado = pCampoInstanciado;

    }

    @Override
    public void atualizarListaCompleta() {
        limparFiltro();
        seletor.atualizarListaCompleta();
        seletor.reloadOrigem();

    }

    @Override
    public List filtrarPorAutoComplet(String pParametro) {
        setFiltro(pParametro);

        return seletor.getOrigem();
    }

    @Override
    public String getFiltro() {
        return seletor.getFiltro();
    }

    @Override
    public void setFiltro(String pFiltro) {
        seletor.setFiltro(pFiltro);

    }

    @Override
    public void listarPrimeiros50() {
        //  atualizarListaCompleta();
        seletor.listarPrimeiros50();
    }

    @Override
    public int getMinimoParaPesquisa() {
        return seletor.getMinimoParaPesquisa();
    }

    @Override
    public void setMinimoParaPesquisa(int minimoParaPesquisa) {
        seletor.setMinimoParaPesquisa(minimoParaPesquisa);
    }

    @Override
    public final ItfSeletorGenerico getSeletor() {
        return seletor;
    }

    @Override
    public GrupoCampos getGrupoCampoExibicao() {
        return (GrupoCampos) campoInstanciado.getGrupoCampoExibicao();
    }

    @Override
    public void limparFiltro() {
        getSeletor().limparFiltro();
    }

    @Override
    public void atualizaOrigemPelaSelecao() {
        seletor.atualizaOrigemPelaSelecao();
    }

    @Override
    public String getNomeOrigem() {
        return seletor.getNomeOrigem();
    }

    @Override
    public void setNomeOrigem(String pNomeString) {
        seletor.setNomeOrigem(pNomeString);
    }

    @Override
    public void reloadOrigem() {
        seletor.reloadOrigem();
    }

    @Override
    public void limparSelecao() {
        seletor.limparSelecao();
    }

    @Override
    public void selecionarTudo() {
        seletor.selecionarTudo();
    }

    @Override
    public List getOrigem() {
        return seletor.getOrigem();
    }

}
