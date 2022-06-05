/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilSBCoreStringSlugs {

    public static String gerarSlugCaixaAlta(String pValor) {
        return gerarSlugSimples(pValor).toUpperCase();
    }

    public static String gerarSlugCaixaAltaByCammelCase(String pValorCammelCase) {
        String stringNormalizada = gerarSlugSimples(pValorCammelCase);
        StringBuilder slugBulder = new StringBuilder();
        stringNormalizada.chars().forEachOrdered(caracter -> {
            if (Character.isUpperCase(caracter) && !slugBulder.toString().isEmpty()) {
                slugBulder.append("_");
            }
            slugBulder.append(Character.toChars(caracter));

        });
        return slugBulder.toString().toUpperCase();
    }

    public static String gerarSlugSimples(String pValor) {
        return UtilSBCoreStringFiltros.gerarUrlAmigavel(pValor);
    }

    public static String gerarSlugSimplesForcandoMaiusculoAposEspaco(String pValor) {
        throw new UnsupportedOperationException("Ainda não implementado, Forca, e PullRequesta Isso! coletivojava.com.br ");
    }

    public static String gerarSlugCaixaBaixa(String pValor) {
        return UtilSBCoreStringFiltros.gerarUrlAmigavel(pValor).toLowerCase();
    }

    @Deprecated
    public static String formatarSlugDeConteudo(String pDominio, String pSlug) {
        pSlug = pSlug.replace("  ", " ");
        pSlug = pSlug.replace(" ", "_");
        if (!pDominio.contains(".")) {
            throw new UnsupportedOperationException(pDominio + " não parece ser um domínio");
        }
        if (!pSlug.startsWith(pDominio)) {
            String prefixo = "";
            if (pSlug.charAt(0) != '/') {
                prefixo = "/";

            }
            pSlug = pDominio + prefixo + pSlug;
        }

        String slugConteudo = UtilSBCoreStringFiltros.removeEspacamentoDuploEUltimoEspacaomento(pSlug);

        return slugConteudo.replaceAll("\\/\\/+", "/");

    }

    @Deprecated
    public static String getSlugPagina(String pSlug) {

        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pSlug)) {
            return null;
        }
        if (pSlug.endsWith("/")) {
            return pSlug;
        }
        if (!pSlug.contains("/")) {
            return "/";
        }

        int fim = pSlug.lastIndexOf("/");

        return (pSlug.substring(0, fim + 1));

    }

}
