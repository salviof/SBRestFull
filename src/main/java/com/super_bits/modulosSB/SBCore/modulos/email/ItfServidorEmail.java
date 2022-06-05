/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.email;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author SalvioF
 */
public interface ItfServidorEmail extends ItfBeanSimples {

    @Override
    public default int getId() {
        return getEmail().hashCode();
    }

    @Override
    public default String getNome() {
        return getEmail();
    }

    public String getEnderecoServidor();

    public String getEmail();

    public String getSenha();

}
