/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.offiline;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorUnicoObjeto.*;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;

/**
 *
 * Classe para ser utilizada onde uma objetoSelecionado será carregada á partir
 * de outra Muito util em formulários de cadastro do Estilo PickList
 *
 * Para utilizar junto com o pickList do primefaces utilize a classe BP_pickList
 *
 *
 * @author Salvio Furbino
 * @param <T>
 */
public class B_ObjetoDeUmaListaOffilinecpInst<T extends ItfBeanSimplesSomenteLeitura>
        extends B_ObjetoDeUmaListaAbs implements ItfSelecaoObjetoDeUmaLista {

    /**
     *
     * IMPORTANTE, CASO EXTENDA ESTE OBJETO, MANTER O CONSTRUCTOR COM ESTES
     * PARAMETROS
     *
     * Os campos instanciados com seleção de itens utiliza este constructor via
     * Reflextion
     *
     * @param pCampoInstanciado
     * @param pOrigem
     */
    public B_ObjetoDeUmaListaOffilinecpInst(ItfCampoInstanciado pCampoInstanciado) {
        super(pCampoInstanciado);

    }

    @Override
    public void selecionarTudo() {
        if (getOrigem().isEmpty()) {
            setObjetoSelecionado((ItfBeanSimplesSomenteLeitura) getOrigem().get(0));
        }
    }

    @Override
    public void limparSelecao() {
        setObjetoSelecionado(null);
    }

}
