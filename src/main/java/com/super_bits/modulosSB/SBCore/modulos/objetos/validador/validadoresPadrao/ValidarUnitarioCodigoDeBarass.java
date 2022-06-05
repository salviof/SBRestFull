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
 * @author novy
 */
public class ValidarUnitarioCodigoDeBarass extends ValidadorUnitarioCampoInstGenerico implements ItfValidacaoUnitaria {

    public ValidarUnitarioCodigoDeBarass(ItfCampoInstanciado pCampoInstanciado) {

        super(pCampoInstanciado, FabTipoValidacaoUnitaria.CODIGO_DE_BARRAS);

    }

    @Override
    public String gerarMensagemErroValidacao(Object pValor) {
        try {
            int digit;
            int calculated;
            String barCode = pValor.toString();
            String ean;
            String checkSum = "131313131313";
            int sum = 0;
            if (barCode.length() == 8 || barCode.length() == 13) {
                digit = Integer.parseInt("" + barCode.charAt(barCode.length() - 1));
                ean = barCode.substring(0, barCode.length() - 1);
                for (int i = 0; i <= ean.length() - 1; i++) {
                    sum += (Integer.parseInt("" + ean.charAt(i))) * (Integer.parseInt("" + checkSum.charAt(i)));
                }
                calculated = 10 - (sum % 10);
                if (digit == calculated) {
                    return null;
                } else {
                    return "Código de barras Inválido";
                }
            } else {
                return "Código de barras Inválido";
            }
        } catch (Throwable t) {
            return "Código de barras Inválido";
        }

    }

}
