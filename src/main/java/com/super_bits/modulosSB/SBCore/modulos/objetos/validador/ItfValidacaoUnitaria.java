/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador;

/**
 *
 * @author desenvolvedor
 */
public interface ItfValidacaoUnitaria {

    public String gerarMensagemErroValidacao(Object pValor);

    public default boolean isValorValido(Object pValor) {
        return gerarMensagemErroValidacao(pValor) == null;
    }

}
