/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.offiline;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorMultiplo.*;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
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
public class B_listaComOrigemOffiline<T extends ItfBeanSimplesSomenteLeitura>
        extends B_listaComOrigemAbs<T>
        implements ItfselecaoListaComOrigem {

    public B_listaComOrigemOffiline(ItfCampoInstanciado pCampoInstanciado) {
        super(pCampoInstanciado);

    }

    public B_listaComOrigemOffiline(List<T> pLista, List<T> pOrigem) {
        super(pLista, pOrigem);
    }

    @Override
    public void atualizaPickListViewContexto() {
        System.out.println("Atulizando exibição da lista para:");

    }

    @Override
    public int getIndiceItemSelecionado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIndiceItemSelecionado(int pItem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
