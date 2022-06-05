/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

/**
 *
 * @author novy
 */
public class UtilSBCoreStringPosicaoLocalizar {

    public static int getUltimaLetraMaiuscula(String pReferencia) {
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pReferencia)) {
            return -1;
        }

        for (int i = pReferencia.toCharArray().length - 1; i > -1; i--) {
            char c = pReferencia.charAt(i);
            if (Character.isUpperCase(c)) {
                return i;
            }
        }
        return -1;
    }

}
