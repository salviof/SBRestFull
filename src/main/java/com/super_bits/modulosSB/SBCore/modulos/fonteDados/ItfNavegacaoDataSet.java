/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.fonteDados;

import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfNavegacaoDataSet<T> {

    public T getItemAtual();

    public int quantidadeRegistroPorPagina();

    public void setQuantidadeRegsitroPorPagina(int pQuantidade);

    public T proximoRegistro();

    public T registroAterior();

    public String getTermoPesquisa();

    public void setTermoPesquisa(String pTermo);

    public void listarProximaPagina();

    public void listarPaginaAnterior();

    public List<T> getListaRegistrosPaginaAtual();

    public List<T> pesquisarNaPagina();

    public List<T> pesquisarNaFonte();

    public List<T> pesquisarNaPagina(String pTermo);

    public List<T> pesquisarNaFonte(String pTermo);

}
