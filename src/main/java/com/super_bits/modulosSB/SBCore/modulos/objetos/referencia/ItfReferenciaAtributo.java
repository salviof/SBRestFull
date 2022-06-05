/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.referencia;

import java.lang.reflect.Field;

/**
 *
 * @author desenvolvedor
 */
public interface ItfReferenciaAtributo {

    public default String getNomeAtributo() {
        try {
            String campo = this.toString().toLowerCase();

            return (String) this.getClass().getDeclaredField(campo).get(null);
        } catch (Throwable t) {
            return null;
        }
    }

}
