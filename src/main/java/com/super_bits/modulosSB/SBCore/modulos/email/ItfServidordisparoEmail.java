/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.email;

/**
 *
 * @author SalvioF
 */
public interface ItfServidordisparoEmail extends ItfServidorEmail {

    public default int getPortaSMTP() {
        return 587;

    }

    public default String getFromEmail() {
        return getUsuarioSMTP();
    }

    public default String getSenhaSMTP() {
        return getSenha();
    }

    public default String getUsuarioSMTP() {
        return getEmail();
    }

    public default boolean isUsarSSLSMTP() {
        return true;
    }

    public default boolean isUsarTSLSMTP() {
        return true;
    }

}
