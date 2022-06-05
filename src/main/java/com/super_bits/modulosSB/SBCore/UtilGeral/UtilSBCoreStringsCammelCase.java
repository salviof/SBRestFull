/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.coletivojava.fw.utilCoreBase.UtilSBCoreStringsCammelCaseSimples;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilSBCoreStringsCammelCase extends UtilSBCoreStringsCammelCaseSimples {

    public static String getCammelByTextoSemCaractersEspeciais(String pString) {
        return getCammelByTexto(UtilSBCoreStringFiltros.removeCaracteresEspeciais(pString));
    }

    /**
     * Mantido por compatibilidade
     *
     * @param pString
     * @return
     */
    public static String getCammelByTexto(String pString) {
        String[] parts = pString.split("[\\s?_?-]+");
        for (int i = 0; i < parts.length; ++i) {
            if (parts[i].length() > 1) {
                parts[i] = Character.toUpperCase(parts[i].charAt(0)) + parts[i].substring(1).toLowerCase();
            } else {
                parts[i] = parts[i].toUpperCase();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; ++i) {
            sb.append(parts[i]);
        }
        return sb.toString();

    }

    public static String getTextoByCammelMinusculo(String pString) {
        if (pString == null) {
            return null;
        }
        return pString.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );

    }

    public static String getCamelByTextoPrimeiraLetraMaiuscula(String pString) {
        return UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMaiusculo(getCammelByTexto(pString));
    }

    public static String getCamelByTextoPrimeiraLetraMinuscula(String pString) {
        return UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(getCammelByTexto(pString));
    }

    public static String getTextoByCammelPrimeiraLetraMaiuscula(String pString) {
        return UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMaiusculo(getTextoByCammel(pString));
    }

    public static String getCamelByTextoPrimeiraLetraMaiusculaSemCaracterEspecial(String pString) {
        return UtilSBCoreStringSlugs.gerarSlugSimples(UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMaiusculo(getCammelByTexto(pString)));
    }

}
