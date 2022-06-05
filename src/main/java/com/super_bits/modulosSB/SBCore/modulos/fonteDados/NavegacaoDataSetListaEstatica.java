/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.fonteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author desenvolvedor
 */
public class NavegacaoDataSetListaEstatica<T> implements ItfNavegacaoDataSet<T> {

    private final List<T> listaCompleta;
    private T itemSelecionado;
    private final ListIterator<T> iterador;
    private final List<T> listaComFiltro;
    private String termoPesquisa;

    public NavegacaoDataSetListaEstatica(List<T> listaCompleta) {
        this.listaCompleta = listaCompleta;
        listaComFiltro = new ArrayList<>();
        iterador = listaCompleta.listIterator();
    }

    @Override
    public T getItemAtual() {
        return itemSelecionado;
    }

    @Override
    public int quantidadeRegistroPorPagina() {
        return Integer.MAX_VALUE;
    }

    @Override
    public T proximoRegistro() {
        if (iterador.hasNext()) {
            itemSelecionado = iterador.next();
        } else {

            itemSelecionado = listaCompleta.get(0);
            iterador.set(itemSelecionado);
        }
        return itemSelecionado;
    }

    @Override
    public T registroAterior() {
        if (iterador.hasPrevious()) {
            itemSelecionado = iterador.previous();
        }
        return itemSelecionado;
    }

    @Override
    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    @Override
    public void setTermoPesquisa(String pTermo) {
        termoPesquisa = pTermo;
    }

    @Override
    public void listarProximaPagina() {

    }

    @Override
    public void listarPaginaAnterior() {

    }

    @Override
    public List<T> getListaRegistrosPaginaAtual() {
        return (List) listaCompleta;
    }

    @Override
    public List<T> pesquisarNaPagina() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> pesquisarNaFonte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> pesquisarNaPagina(String pTermo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> pesquisarNaFonte(String pTermo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setQuantidadeRegsitroPorPagina(int pQuantidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
