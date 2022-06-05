/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidadorGoverno;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ItfValidacaoUnitaria;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author desenvolvedor
 */
public class ValidarUnitarioREGEX extends ValidadorUnitarioCampoInstGenerico implements ItfValidacaoUnitaria {

    private final Pattern pattern;

    public ValidarUnitarioREGEX(ItfCampoInstanciado pCampoInst) {
        super(pCampoInst, FabTipoValidacaoUnitaria.REGEX);
        if (campoInstanciado.isTemValidacaoRegex()) {
            pattern = Pattern.compile(campoInstanciado.getValidacaoRegex());
        } else {
            pattern = null;
        }
    }

    @Override
    public String gerarMensagemErroValidacao(Object pValor) {
        if (!campoInstanciado.isTemValidacaoRegex()) {
            return null;
        } else {
            if (UtilSBCoreStringValidador.isNuloOuEmbranco(pValor) || !pValor.getClass().getSimpleName().equals(String.class.getSimpleName())) {
                return null;
            } else {
                try {
                    if (!pattern.matcher(pValor.toString()).find()) {
                        if (campoInstanciado.getFabricaTipoAtributo().equals(FabTipoAtributoObjeto.URL)) {
                            if (!pValor.toString().startsWith("http://") || !pValor.toString().startsWith("https://")) {
                                if (campoInstanciado instanceof ItfCampoInstanciado) {
                                    ItfCampoInstanciado cp = (ItfCampoInstanciado) campoInstanciado;
                                    try {
                                        cp.setValorSeValido("http://" + pValor);
                                        cp.bloquearProximaAlteracao();
                                        return null;
                                    } catch (ErroValidacao p) {
                                        return p.getMensagemAoUsuario();
                                    }

                                }
                            }
                            return "Valor inválido para " + campoInstanciado.getLabel();
                        }
                        return "Valor inválido para " + campoInstanciado.getLabel();
                    }
                } catch (Throwable t) {
                    return "Falha validando com" + pattern.pattern();
                }

            }

        }
        return null;

    }

}
