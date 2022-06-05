/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorMultiplo.ItfselecaoListaComOrigem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author desenvolvedor
 */
public class CampoInstanciadoSeletorDeItens extends CampoInstanciadoComSelecao implements ItfCampoInstSeletorItens {

    private final CampoInstanciadoGenerico campoInstanciado;
    private final ItfselecaoListaComOrigem listaComListaOrigemDeObjetos;

    private ItfBeanSimples itemSelecionado;

    public CampoInstanciadoSeletorDeItens(CampoInstanciadoGenerico pCampoInstanciado) {
        super(UtilSBCoreReflexaoCaminhoCampo.getSelecaoItens(pCampoInstanciado), pCampoInstanciado);
        this.campoInstanciado = pCampoInstanciado;
        listaComListaOrigemDeObjetos = (ItfselecaoListaComOrigem) getSeletor();

    }

    @Override
    public ItfselecaoListaComOrigem getCampoSeletorItens() {

        return listaComListaOrigemDeObjetos;

    }

    public ItfCampoInstanciado getCampoInstanciado() {
        return campoInstanciado;
    }

}
