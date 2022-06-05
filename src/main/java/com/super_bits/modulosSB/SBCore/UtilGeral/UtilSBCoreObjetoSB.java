/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author SalvioF
 */
public class UtilSBCoreObjetoSB {

    @Deprecated
    public static boolean compararObjetos(ItfBeanSimples pReferencia, ItfBeanSimples pObjetoComparado) {

        return isObjetosIguais(pReferencia, pObjetoComparado);
    }

    public static boolean isObjetosIguais(ItfBeanSimples pReferencia, ItfBeanSimples pObjetoComparado) {
        if (pObjetoComparado == null && pReferencia != null) {
            return false;
        }
        if (pReferencia == null && pObjetoComparado != null) {
            return false;
        }
        if (pReferencia == null && pObjetoComparado == null) {
            return true;
        }

        return pObjetoComparado.equals(pReferencia);
    }

    public static boolean isItemSimplesExistETemNome(ItfBeanSimples pItemSimples) {
        if (pItemSimples == null) {
            return false;
        }
        return UtilSBCoreStringValidador.isNAO_NuloNemBranco(pItemSimples.getNome());
    }

}
