package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Salvio
 */
public abstract class UtilSBCoreStringValidador {

    public enum TIPO_SINALIZADOR {

        COLCHETE, TAG, ASPAS
    }

    /**
     *
     * @param ptexto
     * @param pTipoBusca
     * @return Uma lista com as palavras encontradas entre os Sinalizadores ex:
     * [teste1] 123 [teste2] retorna uma lista com as palavras teste1 e teste 2
     */
    public static List<String> getListaStringEntreCaracter(String ptexto, TIPO_SINALIZADOR pTipoBusca) {
        switch (pTipoBusca) {
            case ASPAS:
                return getListaStringEntreCaracter(ptexto, '"', '"');
            case TAG:
                return getListaStringEntreCaracter(ptexto, '<', '>');
            case COLCHETE:
                return getListaStringEntreCaracter(ptexto, '[', ']');

        }
        return getListaStringEntreCaracter(ptexto, '"', '"');
    }

    /**
     *
     *
     * Retorna a String entre 2 caracters, exemplo: "nomestringTeste[31]"
     * retornaria 31
     *
     *
     * @param pReferencia String onde o caracter será procurado
     * @param pCaracter1 Caracter chave indicando inicio
     * @param pCaracter2 Carácter chave indicando fim
     * @return
     */
    public static String getStringEntreCaracters(String pReferencia, String pCaracter1, String pCaracter2) {

        return pReferencia.substring(pReferencia.indexOf(pCaracter1) + 1, pReferencia.indexOf(pCaracter2));

    }

    /**
     *
     * Retorna String entre determinados caracteres exemplo:
     *
     * texto= dfkjklaskdjf[parametroum] [parametro2] inicial=[ final= ]
     * retornaria uma lista com parametroum,parametro2
     *
     * @param texto texto onde as strings serão encontradas
     * @param pInicial caracter que indica o inicio da string
     * @param pFinal caracter que indica o fim da string
     * @return
     */
    public static List<String> getListaStringEntreCaracter(String texto, char pInicial, char pFinal) {

        char cInicial = pInicial;
        char cFinal = pFinal;
        List<String> resposta = new ArrayList<>();
        if (texto == null) {
            return resposta;
        }

        String regex = "([\\" + cInicial + "](.*?)[\\" + cFinal + "])";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(texto);

        while (m.find()) {
            resposta.add(m.group());
        }
        return resposta;
    }

    /**
     *
     * @see UtilSBCoreStringsMaiuculoMinusculo#splitMaiuscula(java.lang.String)
     * @param pParametros
     * @return
     */
    @Deprecated
    public static List<String> splitMaiuscula(String pParametros) {
        return UtilSBCoreStringsMaiuculoMinusculo.splitMaiuscula(pParametros);
    }

    /**
     * @see UtilSBCoreStringBuscaTrecho#strValorEntreParenteses
     */
    @Deprecated
    public static String strValorEntreParenteses(String parametro) {
        return UtilSBCoreStringBuscaTrecho.strValorEntreParenteses(parametro);

    }

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

    @Deprecated
    public static Date dtMesBarraAno(String parametro) throws ParseException {
        SimpleDateFormat frm = new SimpleDateFormat("DD/mm/yy");

        return frm.parse(parametro);

    }

    /**
     *
     * Compara 2 arrays de String e retorna true caso os arrays sejam iguais
     *
     * @param ar1 array 1 para comparaçõa
     * @param ar2 array 2 para comparaçõa
     * @return
     */
    public static boolean compara2StrArrays(String[] ar1, String[] ar2) {
        if (ar1.length != ar2.length) {
            return false;
        }
        int i = 0;
        for (String comp1 : ar1) {
            if (!comp1.equals(ar2[i])) {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     *
     * @see UtilSBCoreStringBuscaTrecho#localizarParte(java.lang.String, int,
     * int, java.lang.Integer, java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Deprecated
    public static String localizarParte(String linha, int posicaoInicial, int posicaoFinal, Integer pQuantidaCaracteres, String descarte, String confirma, String regex) {
        return UtilSBCoreStringBuscaTrecho.localizarParte(linha, 0, 0, pQuantidaCaracteres, descarte, confirma, regex);

    }

    /**
     *
     * @param pParametro String a ser analizada
     * @return Verdadeiro se contiver apenas números
     */
    public static boolean isContemApenasNumero(String pParametro) {
        if (pParametro == null) {
            return false;
        }
        if (pParametro.isEmpty()) {
            return false;
        }
        for (Character ch : pParametro.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param pParametro String a ser analizada
     * @return Verdadeiro se contiver apenas números
     */
    public static boolean isContemApenasNumeroOuDecimalSeparador(String pParametro) {
        if (pParametro.isEmpty()) {
            return false;
        }
        for (Character ch : pParametro.toCharArray()) {
            if (!Character.isDigit(ch)) {
                if (!('.' == ch) && !(ch == ',')) {
                    return false;
                }

            }
        }

        return true;
    }

    /**
     *
     * @param pString
     * @return True se a primeira letra for maiscula
     */
    public static boolean isPrimeiraLetraMaiuscula(String pString) {
        if (pString == null) {
            return false;
        }
        if (pString.length() < 1) {
            return false;
        }
        return Character.isUpperCase(pString.charAt(0));
    }

    public static boolean isTudoMaiusculo(String pString) {
        return StringUtils.isAllLowerCase(pString);

    }

    /**
     *
     * @param pString
     * @return True se a APENAS a primeira letra for maiuscula
     */
    public static boolean isPrimeiraLetraMaiusculaApenas(String pString) {
        if (pString == null) {
            return false;
        }
        if (pString.length() < 1) {
            return false;
        }

        if (!Character.isUpperCase(pString.charAt(0))) {
            return false;
        } else if (pString.length() > 1) {

            return pString.substring(1).toLowerCase().equals(pString.substring(1));
        }
        return true;

    }

    public static boolean isPrimeiraLetraMaiusculaSegundaMinuscula(String pString) {
        if (pString == null) {
            return false;
        }
        if (pString.length() < 1) {
            return false;
        }

        if (!Character.isUpperCase(pString.charAt(0))) {
            return false;
        } else if (pString.length() > 1) {
            return Character.isLowerCase(pString.charAt(1));
        }
        return true;

    }

    /**
     *
     * @param pString
     * @return True se uma das strings for igual a nula ou vazia
     */
    public static boolean isNuloOuEmbranco(String... pString) {

        for (String valor : pString) {
            if (valor == null) {
                return true;
            } else if (valor.isEmpty()) {
                return true;
            }

        }
        return false;
    }

    public static boolean isNuloOuEmbranco(Object... pString) {

        for (Object valor : pString) {
            if (valor == null) {
                return true;
            } else if (valor.toString().isEmpty()) {
                return true;
            }

        }
        return false;
    }

    /**
     *
     * @param pString
     * @return true Se não for igual a nulo e nem string vazia
     * @deprecated "Desnecessário criar funções negativas para cada função
     * alternativa, utilize -> [!]"
     */
    @Deprecated()
    public static boolean isNAO_NuloNemBranco(String pString) {
        if (pString == null) {
            return false;
        }
        return !pString.isEmpty();
    }

    public String gerarSlugDoTexto(String pTexto) {
        return UtilSBCoreStringFiltros.gerarUrlAmigavel(pTexto);
    }

    public static boolean isUmaUrlYoutubeEmbed(String pUrl) {
        String pattern = "(https:\\/\\/www.youtube.com\\/embed\\/\\w+)";
        Pattern compiledPattern = Pattern.compile(pattern);
        //url is youtube url for which you want to extract the id.
        Matcher matcher = compiledPattern.matcher(pUrl);
        if (matcher.find()) {
            if (pUrl.contains("/embed/")) {
                return true;
            }
            return true;
        }
        return false;
    }

}
