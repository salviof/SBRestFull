/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;

/**
 *
 * @author desenvolvedor
 */
public class ValidadorUnitarioNulo extends ValidadorUnitarioCampoInstGenerico {

    public ValidadorUnitarioNulo(ItfCampoInstanciado pCampoInst) {
        super(pCampoInst, FabTipoValidacaoUnitaria.NULO);
    }

    @Override
    public String gerarMensagemErroValidacao(Object pValor) {
        if (!campoInstanciado.isObrigatorio()) {
            return null;
        }
        switch (campoInstanciado.getFabricaTipoAtributo()) {
            case ARQUIVO_DE_ENTIDADE:
                if (pValor != null) {
                    if (!campoInstanciado.getComoArquivoDeEntidade().isExisteArquivo()) {
                        return "O arquivo para " + campoInstanciado.getLabel() + " não foi encontrado";
                    }
                    return null;
                }
            default:
                if (campoInstanciado.isObrigatorio()) {
                    if (!campoInstanciado.isNumeral()) {

                        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pValor)) {
                            return campoInstanciado.getLabel() + " não pode ser nulo";
                        }

                    }
                }

        }

        return null;
    }

}
