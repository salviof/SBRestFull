/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.constraints.NotNull;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreStringBuscaTrecho {

    /**
     *
     * Procura e retorna o conteúdo que estiver entre parenteses
     *
     * @param parametro "texto com (conteudo) entre parentes";
     * @return "conteudo"
     */
    public static String strValorEntreParenteses(String parametro) {

        int inicioIndex = parametro.lastIndexOf("(");

        if (inicioIndex != -1) {

            int fimIndex = parametro.lastIndexOf(")");
            if (fimIndex != -1) {
                return parametro.substring(inicioIndex + 1, fimIndex);
            }

        }

        return null;
    }

    /**
     *
     *
     *
     * @param linha String onde o valor vai ser encontrado
     * @param posicaoInicial Início posição esperada
     * @param posicaoFinal Fim posição esperada
     * @param pQuantidaCaracteres Tamanho esperado (em caso de usar Regex para
     * localizar parte)
     * @param descarte Caso encontre este valor descarte
     * @param confirma Caso não possua esste valor descarte
     * @param regex Caso não passe pela string regex descarte
     * @return
     */
    public static String localizarParte(String linha, int posicaoInicial, int posicaoFinal, Integer pQuantidaCaracteres, String descarte, String confirma, String regex) {

        boolean registroValido = true;
        if (pQuantidaCaracteres == null || pQuantidaCaracteres == 0) {
            pQuantidaCaracteres = posicaoFinal - posicaoInicial;
        }

        String retorno = "";
        if (linha.length() >= posicaoInicial + (posicaoFinal - posicaoInicial)) {
            retorno = linha.substring(posicaoInicial, posicaoFinal);
        }
        if (descarte != null) {
            if (retorno.contains(descarte)) {
                registroValido = false;
            }
        }
        if (confirma != null) {
            if (!retorno.contains(confirma)) {
                registroValido = false;
            }
        }
        if (regex != null) {
            Pattern padrao = Pattern.compile(regex);
            Matcher comp = padrao.matcher(retorno);

            if (!comp.find()) {

                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Leitura de SBsubstringPLus ignorada por regex=" + regex + " valor:" + retorno, null);
                registroValido = false;
            } else {
                if (pQuantidaCaracteres > retorno.length()) {
                    pQuantidaCaracteres = retorno.length();
                }
                try {
                    retorno = retorno.substring(comp.start(), pQuantidaCaracteres);

                } catch (Exception e) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Tamanho do caractér incorreto", e);
                    registroValido = false;
                }
            }

        }
        if (!registroValido) {
            retorno = "";
        }
        return retorno;

    }

    private static final String REGEX_EMAIL
            = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public static String getEmailEmTexto(String pTexto) {
        Pattern pattern;
        pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher;

        matcher = pattern.matcher(pTexto);
        while (matcher.find()) {
            return matcher.group();
        }

        return null;
    }

    public static List<String> getEmailsEmTexto(String pTexto) {
        Pattern pattern;
        pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher;

        matcher = pattern.matcher(pTexto);
        List<String> listar = new ArrayList<>();
        while (matcher.find()) {
            listar.add(matcher.group());

        }

        return listar;
    }

    public static List<String> getPartesEntreMaiorMenor(String pTexto) {

        List<String> resposta = new ArrayList<String>();
        Pattern p = Pattern.compile("\\[(<^\\}]+?)\\>"); // coloquei o trecho que quero entre parênteses
        Matcher matcher = p.matcher(pTexto);
        while (matcher.find()) {
            String result = matcher.group(1); // pego o primeiro grupo de captura
            resposta.add(result);
        }

        return resposta;
    }

    public static List<String> getPartesEntreColchete(String pTexto) {

        List<String> resposta = new ArrayList<String>();
        Pattern p = Pattern.compile("\\[([^\\}]+?)\\]"); // coloquei o trecho que quero entre parênteses
        Matcher matcher = p.matcher(pTexto);
        while (matcher.find()) {
            String result = matcher.group(1); // pego o primeiro grupo de captura
            resposta.add(result);
        }

        return resposta;
    }

    public static List<String> getPartesEntreChaves(@NotNull String pTexto) {

        List<String> resposta = new ArrayList<>();
        Pattern p = Pattern.compile("\\{([^\\}]+?)\\}"); // coloquei o trecho que quero entre parênteses
        Matcher matcher = p.matcher(pTexto);
        while (matcher.find()) {
            String result = matcher.group(1); // pego o primeiro grupo de captura
            resposta.add(result);
        }

        return resposta;
    }

    /**
     *
     * @param pValor O valor onde o subValordeString será localizado
     * @param pIsto O caracter chave que indica o fim da cópia
     * @return A String até encontar isto, ou <b>null se não encontrar</b>
     */
    public static String getStringAteEncontrarIsto(String pValor, String pIsto) {
        if (pValor == null || !pValor.contains(pIsto)) {
            return null;
        }
        return pValor.substring(0, pValor.indexOf(pIsto));
    }

    /**
     *
     * @param pValor Valor onde a sbustring será extraída
     * @param pDisto Caracteres chave a partir de onde você deseja copiar
     * @return String apartir de caracter{es} chave
     */
    public static String getStringAPartirDisto(String pValor, String pDisto) {
        if (pValor == null || !pValor.contains(pDisto)) {
            return null;
        }
        return pValor.substring(pValor.indexOf(pDisto) + 1, pValor.length());
    }

}
