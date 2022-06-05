/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ItfValidacaoUnitaria;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class ValidarUnitarioMaximo extends ValidadorUnitarioCampoInstGenerico implements ItfValidacaoUnitaria {

    public ValidarUnitarioMaximo(ItfCampoInstanciado pCampoInst) {
        super(pCampoInst, FabTipoValidacaoUnitaria.MAXIMO);

    }

    @Override
    public String gerarMensagemErroValidacao(Object pValor) {
        try {
            boolean superouMaximo = false;
            if (!campoInstanciado.isTemValidacaoMaximo()) {
                return null;
            }
            if (UtilSBCoreStringValidador.isNuloOuEmbranco(pValor)) {
                return null;
            }

            if (campoInstanciado.isNumeral()) {
                campoInstanciado.isNumeral();
                if (pValor == null || Double.parseDouble(pValor.toString()) > campoInstanciado.getValorMaximo()) {
                    return "O valor máximo permitido para " + campoInstanciado.getLabel() + " é " + campoInstanciado.getValorMaximo();
                }
            }
            switch (campoInstanciado.getFabricaTipoAtributo()) {
                case AAA_NOME:
                case NOME_COMPLETO:
                case AAA_DESCRITIVO:
                case TEXTO_SIMPLES:
                    if (pValor.toString().length() > campoInstanciado.getValorMaximo()) {
                        return campoInstanciado.getLabel()
                                + " possui " + pValor.toString().length()
                                + " caractéres, o máximo permitido é " + campoInstanciado.getValorMaximo();
                    }
                    break;

                default:
                    return null;
            }
            return null;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Validando " + campoInstanciado.getLabel(), t);
            return null;

        }
    }

}
