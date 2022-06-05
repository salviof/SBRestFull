/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;

/**
 *
 * @author sfurbino
 */
public class ValidadorUnitarioLocalizacao extends ValidadorUnitarioCampoInstGenerico {

    private final ItfLocal localAnalizado;

    public ValidadorUnitarioLocalizacao(ItfCampoInstanciado pCampoInstanciado) {
        super(pCampoInstanciado, FabTipoValidacaoUnitaria.LOCALIZACAO);
        localAnalizado = (ItfLocal) campoInstanciado.getValor();
    }

    @Override
    public String gerarMensagemErroValidacao(Object pValor) {
        if (localAnalizado != null) {
            if (localAnalizado.getBairro() != null
                    && UtilSBCoreStringValidador.isNuloOuEmbranco(campoInstanciado.getComoCampoLocalizacao().getBairro().getNome())) {
                campoInstanciado.setValor(null);
                return "Localização inválida";
            }
            if (campoInstanciado.getComoCampoLocalizacao().getBairro() != null
                    && UtilSBCoreStringValidador.isNuloOuEmbranco(campoInstanciado.getComoCampoLocalizacao().getBairro().getNome())) {
                campoInstanciado.setValor(null);
                return "Localização inválida";
            }
            if (campoInstanciado.getComoCampoLocalizacao().getBairro().getCidade() != null
                    && UtilSBCoreStringValidador.isNuloOuEmbranco(campoInstanciado.getComoCampoLocalizacao().getBairro().getCidade().getNome())) {
                campoInstanciado.setValor(null);
                return "Localização inválida";
            }
            if (campoInstanciado.getComoCampoLocalizacao().getBairro().getCidade().getUnidadeFederativa() != null
                    && UtilSBCoreStringValidador.isNuloOuEmbranco(campoInstanciado.getComoCampoLocalizacao().getBairro().getCidade().getUnidadeFederativa().getNome())) {
                campoInstanciado.setValor(null);
                return "Localização inválida";
            }

        }
        return null;
    }

}
