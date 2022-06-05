/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author sfurbino
 */
public class UtilSBCoreParametrosEmString {

    public static String substituiParametrosNomeadosPorInterroga(String parametro) {
        String texto = parametro;
        texto = texto + " ";
        int i = 1;
        while (texto.indexOf(":") != -1) {
            int idxFimParametro = getIndiceFimParametro(texto);
            int idxInicioParametro = texto.indexOf(":");
            String parametrosql = texto.substring(idxInicioParametro, idxFimParametro);
            texto = texto.replace(parametrosql, "?" + String.valueOf(i));
            i++;
        }
        return texto;
    }

    private static int getIndiceFimParametro(String pTexto) {
        List<Integer> indice = new ArrayList<>();

        indice.add(pTexto.indexOf("/", pTexto.indexOf(":")));
        indice.add(pTexto.indexOf("'", pTexto.indexOf(":")));
        indice.add(pTexto.indexOf("\"", pTexto.indexOf(":")));
        indice.add(pTexto.indexOf(" ", pTexto.indexOf(":")));
        indice.add(pTexto.indexOf(")", pTexto.indexOf(":")));
        indice.add(pTexto.indexOf("\\", pTexto.indexOf(":")));
        indice.add(pTexto.indexOf("/n", pTexto.indexOf(":")));
        indice.add(pTexto.indexOf("#", pTexto.indexOf(":")));
        indice.add(pTexto.indexOf("$", pTexto.indexOf(":")));
        Collections.sort(indice);

        for (int i : indice) {
            if (i != -1) {
                return i;
            }
        }
        //
        return pTexto.length() - 1;
    }

    public static List<String> retornaParamentrosNomeadosEmLista(String pTexto) {
        List<String> listaDeParametros = new ArrayList<>();

        String texto = pTexto + " ";

        int i = 1;
        while (texto.indexOf(":") != -1) {

            int idxFimParametro = getIndiceFimParametro(texto);
            int idxInicioParametro = texto.indexOf(":");
            String parametrosql = texto.substring(idxInicioParametro, idxFimParametro);
            listaDeParametros.add(parametrosql);
            texto = texto.replace(parametrosql, "?" + String.valueOf(i));
            i++;
        }
        return listaDeParametros;

    }

    public static List<String> retornaParamentrosNomeadosEmLista(List<String> pTexto) {
        List<String> listaDeParametros = new ArrayList<>();

        for (String texto : pTexto) {

            List<String> parametrosNaLinha = retornaParamentrosNomeadosEmLista(texto);

            for (String prEncontrado : parametrosNaLinha) {
                listaDeParametros.add(prEncontrado);
            }
        }
        return listaDeParametros;

    }

}
