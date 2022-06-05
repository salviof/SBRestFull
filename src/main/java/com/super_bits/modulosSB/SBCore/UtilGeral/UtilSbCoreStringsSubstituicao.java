/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

/**
 *
 * @author desenvolvedor
 */
public class UtilSbCoreStringsSubstituicao {

    /**
     *
     * Util para consultas de sql, onde os paramentros estão nomeados como:
     * :nomedoParametro ele substitui este parametro por ?
     *
     * @param parametro
     * @return
     */
    public static String substituiParametrosNomeadosPorInterroga(String parametro) {
        return UtilSBCoreParametrosEmString.substituiParametrosNomeadosPorInterroga(parametro);
    }

}
