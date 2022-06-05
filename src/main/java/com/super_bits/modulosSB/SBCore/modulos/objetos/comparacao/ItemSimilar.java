/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author desenvolvedorninja01
 * @since 05/09/2019
 * @version 1.0
 */
public class ItemSimilar<T> extends ItemSimilarGenerico<T> {

    public ItemSimilar(ItfBeanSimples pObjetoAnalizado, String parametro) {
        super(pObjetoAnalizado, parametro);
    }

    @Override
    public String getTextoReferencia() {
        return objetoAnalizado.getNome();
    }

}
