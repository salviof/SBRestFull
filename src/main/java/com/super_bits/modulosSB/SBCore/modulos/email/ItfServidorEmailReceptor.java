/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.email;

import java.util.Date;

/**
 *
 * @author SalvioF
 */
public interface ItfServidorEmailReceptor extends ItfServidorEmail {

    public default String getUsuarioRecepcao() {
        return getEmail();
    }

    public default String getSenhaRecepcao() {
        return getSenha();
    }

    public default int portaRecepcao() {
        return 995;
    }

    public default boolean isUsarSSLRecepcao() {

        return (portaRecepcao() != 110);
    }

    public Date getDataHoraUltimoEmailRecebido();

    public void setDataHoraUltimoEmailRecebido(Date pDataHora);
}
