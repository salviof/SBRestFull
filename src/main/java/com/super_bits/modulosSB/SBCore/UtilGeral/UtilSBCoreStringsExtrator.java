/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author salviofurbino
 * @since 19/04/2019
 * @version 1.0
 */
public class UtilSBCoreStringsExtrator extends UtilSBCoreStringFiltros {

    public static String extrairEmail(String pString) {
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pString)) {
            return null;
        } else {
            Pattern pattern = Pattern.compile("([a-zA-Z0-9_.-]+)@([a-zA-Z0-9_.-]+[a-z])");
            Matcher matcher = pattern.matcher(pString);

            while (matcher.find()) {
                return matcher.group();

            }
        }
        return null;
    }

    public static List<String> extrairEmails(String pString) {
        List<String> resp = new ArrayList();

        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pString)) {
            return resp;
        } else {
            Pattern pattern = Pattern.compile("([a-z0-9_.-]+)@([a-z0-9_.-]+[a-z])");
            Matcher matcher = pattern.matcher(pString);

            while (matcher.find()) {
                resp.add(matcher.group());

            }
        }
        return resp;
    }

}
