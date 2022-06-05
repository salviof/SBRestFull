/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.io.Serializable;

/**
 *
 * @author desenvolvedor
 */
public class ValidacaoGenerica<T> implements ItfValidacao<T>, Serializable {

    private final ItfCampoInstanciado campoInst;

    @Override
    public Object validar(Object pValor) throws ErroValidacao {

        throw new ErroValidacao("O validador do  campo" + getCampoInstanciado().getObjetoDoAtributo().getClass().getSimpleName() + "." + getCampoInstanciado().getLabel() + " não foi impplementado em " + this.getClass().getSimpleName());

    }

    public ValidacaoGenerica(ItfCampoInstanciado pCampo) {
        campoInst = pCampo;
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciado() {
        return campoInst;
    }

}
