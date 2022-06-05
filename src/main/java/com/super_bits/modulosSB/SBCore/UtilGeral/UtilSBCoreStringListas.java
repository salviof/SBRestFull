/* 
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90 

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salviofurbino
 */
public class UtilSBCoreStringListas {
       /**
     *
     * Gera uma única string adicionando um /n entre cada uma
     *
     * @param lista Lista de Strings
     * @return Rrtorna uma única string adicionando um /n entre cada uma
     */
    public static String getStringDaListaComBarraN(List<String> lista) {
        return getStringDaListaComSeparador(lista, "\n");

    }

    /**
     *
     *
     *
     * Gera uma única string adicionando um Caracter especifico entre cada uma
     *
     * @param lista
     * @param separador
     * @return
     */
    public static String getStringDaListaComSeparador(List<String> lista, String separador) {
        String resultado = "";
        if (lista == null) {
            return null;
        }
        for (String iten : lista) {
            resultado = resultado + separador + iten;
        }
        return resultado;

    }
 public static List<String> getlistadeLinhas(String pString) {
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pString)) {
            return new ArrayList<>();
        }
        String[] linhas = pString.split("[\\r\\n]+");
        List<String> listaLinhas = new ArrayList<>();

        for (String ln : linhas) {
            listaLinhas.add(ln);
        }
        return listaLinhas;

    }
}
