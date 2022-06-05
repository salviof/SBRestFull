/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanComEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanContatoPessoa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author sfurbino
 */
public class ItemSimilarContato extends ItemSimilarGenerico {

    public ItemSimilarContato(ItfBeanSimples pObjetoAnalizado, String parametro) {

        super(pObjetoAnalizado, parametro);

    }

    @Override
    public String getTextoReferencia() {
        try {
            if (termoPesquisa.contains("@")) {
                return ((ItfBeanComEmail) objetoAnalizado).getEmail();
            } else {
                return objetoAnalizado.getNome().toLowerCase();
            }
        } catch (Throwable t) {
            return null;
        }

    }

}
