/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ItfValidacaoUnitaria;

/**
 *
 * @author desenvolvedor
 */
public abstract class ValidadorUnitarioCampoInstGenerico implements ItfValidacaoUnitaria {

    protected final ItfCampoInstanciado campoInstanciado;
    protected final FabTipoValidacaoUnitaria tipoValidacao;

    public ValidadorUnitarioCampoInstGenerico(ItfCampoInstanciado pCampoInstanciado, FabTipoValidacaoUnitaria pTipoValidacao) {
        this.campoInstanciado = pCampoInstanciado;
        this.tipoValidacao = pTipoValidacao;
    }

}
