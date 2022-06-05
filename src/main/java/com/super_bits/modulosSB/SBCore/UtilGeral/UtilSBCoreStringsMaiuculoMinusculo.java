/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreStringsMaiuculoMinusculoSimples;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreStringsMaiuculoMinusculo extends UtilSBCoreStringsMaiuculoMinusculoSimples {

    /**
     *
     * Separa palavras que iniciam com maisuculas emmm uma lista de Strings
     *
     * @param pParametros "palavraContendoMaiuculo
     * @return List[palavra,Contendo,Maiusculo]
     */
    public static List<String> splitMaiuscula(String pParametros) {
        List<String> resp = new ArrayList<String>();
        String nomeTabela = "";
        int i = 0;
        for (char car : pParametros.toCharArray()) {
            if (Character.isUpperCase(car)) {
                if (nomeTabela.equals("") || nomeTabela == null) {
                    nomeTabela = "" + car;
                } else {
                    resp.add(nomeTabela);
                    nomeTabela = "" + car;
                }
            } else {
                nomeTabela = nomeTabela + car;
            }

            if (pParametros.length() == i + 1) {
                resp.add(nomeTabela);
            }
            i++;
        }
        return resp;
    }

    /**
     *
     * Retorna a String convertendo a primeira letra para minusculo
     *
     * @param parametro
     * @return
     */
    public static String getPrimeiraLetraMinuscula(String parametro) {
        return String.valueOf(parametro.charAt(0)).toLowerCase() + parametro.substring(1);
    }

    public static String getPrimeiraLetraMaiusculaRestanteMinusculo(String parametro) {
        return String.valueOf(parametro.charAt(0)).toUpperCase() + parametro.substring(1).toLowerCase();
    }

}
