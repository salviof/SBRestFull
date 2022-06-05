/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ItfValidacaoUnitaria;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class ValidarUnitarioMinimo extends ValidadorUnitarioCampoInstGenerico implements ItfValidacaoUnitaria {

    public ValidarUnitarioMinimo(ItfCampoInstanciado pCampoInst) {
        super(pCampoInst, FabTipoValidacaoUnitaria.MAXIMO);

    }

    @Override
    public String gerarMensagemErroValidacao(Object pValor) {
        try {

            if (!campoInstanciado.isTemValidacaoMinimo()) {
                return null;
            }
            if (UtilSBCoreStringValidador.isNuloOuEmbranco(pValor)) {
                return null;
            }

            if (campoInstanciado.isNumeral()) {
                if (pValor == null || Double.parseDouble(pValor.toString()) < new Double(campoInstanciado.getValorMinimo())) {
                    throw new ErroValidacao("O valor mínimo permitido para " + campoInstanciado.getLabel() + " é " + campoInstanciado.getValorMinimo());
                }
            }

            switch (campoInstanciado.getFabricaTipoAtributo()) {
                case AAA_NOME:
                case NOME_COMPLETO:
                case AAA_DESCRITIVO:
                case TEXTO_SIMPLES:
                    if (pValor.toString().length() < campoInstanciado.getValorMinimo()) {
                        throw new ErroValidacao(campoInstanciado.getLabel()
                                + " O mínimo de caractéres permitido para " + campoInstanciado.getLabel() + " é " + campoInstanciado.getValorMinimo());

                    }
                    break;
                case QUANTIDADE:
                    if (pValor == null || Double.parseDouble(pValor.toString()) < campoInstanciado.getValorMinimo()) {
                        throw new ErroValidacao("O valor mínimo permitido para " + campoInstanciado.getLabel() + " é " + campoInstanciado.getValorMinimo());
                    }
                    return null;

                default:
                    return null;

            }
            return null;
        } catch (ErroValidacao e) {
            return e.getMensagemAoUsuario();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Validando" + campoInstanciado.getNomeClasseOrigemAtributo() + "" + campoInstanciado.getLabel(), t);
            return null;
        }
    }

}
