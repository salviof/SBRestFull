/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidadorGoverno;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ItfValidacaoUnitaria;

/**
 *
 * @author desenvolvedor
 */
public class ValidarUnitarioCNPJ extends ValidadorUnitarioCampoInstGenerico implements ItfValidacaoUnitaria {

    public ValidarUnitarioCNPJ(ItfCampoInstanciado pCampoInst) {
        super(pCampoInst, FabTipoValidacaoUnitaria.MAXIMO);

    }

    @Override
    public String gerarMensagemErroValidacao(Object pValor) {
        switch (campoInstanciado.getFabricaTipoAtributo()) {
            case CNPJ:

                if (UtilSBCoreStringValidador.isNuloOuEmbranco(pValor)) {
                    return null;
                }
                if (pValor.toString().contains("_")) {
                    String numeros = UtilSBCoreStringFiltros.getNumericosDaString(pValor.toString());
                    if (numeros.length() == 0) {
                        campoInstanciado.setValor(null);
                        campoInstanciado.bloquearProximaAlteracao();
                        return null;
                    }
                }
                if (!UtilSBCoreValidadorGoverno.validaCNPJ(pValor.toString())) {
                    return "Cnpj Inválido";
                } else {
                    return null;
                }
            default:
                return null;
        }

    }

}
