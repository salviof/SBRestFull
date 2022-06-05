/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

/**
 *
 * @author desenvolvedor
 */
public class ErroRegraDeNegocio extends Throwable {

    private final String mensagemUsuario;

    public ErroRegraDeNegocio(String pMensagemErroAoUsuario) {
        super(pMensagemErroAoUsuario);

        mensagemUsuario = pMensagemErroAoUsuario;
    }

}
