/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorUnicoObjeto;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.B_SeletorGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.util.List;

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
public abstract class B_ObjetoDeUmaListaAbs<T extends ItfBeanSimplesSomenteLeitura> extends
        B_SeletorGenerico
        implements ItfSelecaoObjetoDeUmaLista<T> {

    /**
     *
     * IMPORTANTE, CASO EXTENDA ESTE OBJETO, MANTER O CONSTRUCTOR COM ESTES
     * PARAMETROS
     *
     * Os campos instanciados com seleção de itens utiliza este constructor via
     * Reflextion
     *
     * @param pCampoInstanciado
     * @param tObjeto
     * @param pOrigem
     */
    public B_ObjetoDeUmaListaAbs(ItfCampoInstanciado pCampoInstanciado) {

        super(pCampoInstanciado);

    }

    public B_ObjetoDeUmaListaAbs(List pLista, List<T> pOrigem) {
        super(pOrigem);

    }

    @Override
    public T getObjetoSelecionado() {
        return (T) campoInstanciado.getValor();
    }

    @Override
    public void atualizaOrigemPelaSelecao() {
        // nada a fazer
    }

    @Override
    public void setObjetoSelecionado(T pObjeto) {
        campoInstanciado.setValor(pObjeto);
    }

}
