/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorMultiplo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.B_SeletorGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Classe para ser utilizada onde uma lista será carregada á partir de outra
 * Muito util em formulários de cadastro do Estilo PickList
 *
 * Para utilizar junto com o pickList do primefaces utilize a classe BP_pickList
 *
 *
 * @author Salvio Furbino
 * @param <T>
 */
public abstract class B_listaComOrigemAbs<T extends ItfBeanSimplesSomenteLeitura>
        extends B_SeletorGenerico
        implements ItfselecaoListaComOrigem {

    protected final List<T> lista;

    public B_listaComOrigemAbs(ItfCampoInstanciado pCampoInstanciado) {
        super(pCampoInstanciado);
        lista = (List) pCampoInstanciado.getValor();

    }

    public B_listaComOrigemAbs(List<T> pLista, List<T> pOrigem) {
        super(pOrigem);
        lista = (List) pLista;
        ajuste(true);
    }

    protected void carregaOrigemFromDataBase() {
        //   carregaOrigemCompleta((List) UtilSBPersistencia.getListaTodos(classeOrigem));
        ajuste(true);
    }

    protected final void ajuste(boolean pRenovar) {
        if (pRenovar) {
            origem.clear();
            origem.addAll(listaCompleta);
        }
        origem.removeAll(getListaObjetosSelecionados());

    }

    @Override
    public List<T> getListaObjetosSelecionados() {
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;

    }

    @Override
    public void setListaObjetosSelecionados(List pLista) {

        ajuste(true);
        atualizaPickListViewContexto();
    }

    @Override
    public void reloadOrigem() {
        super.reloadOrigem();
        ajuste(true);
        atualizaPickListViewContexto();
    }

    @Override
    public void selecionarTudo() {
        throw new UnsupportedOperationException("Selecionar tudo Ainda não foi Implementado, se você precisou disso, tavez este seja o momento, colabore, coletivojava.com.br");
    }

    @Override
    public void limparSelecao() {
        lista.clear();
    }

    @Override
    public void atualizaOrigemPelaSelecao() {
        ajuste(false);
    }

}
