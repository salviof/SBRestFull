/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import java.util.Properties;

/**
 *
 * @author SalvioF
 */
public class SBModulo {

    private static final String PASTACONFIGURACAO_PADRAO = "/home/git/modulos";
    private static String PATACONFIGURACAO = PASTACONFIGURACAO_PADRAO;
    private static boolean foiConfigurado;

    public static String getConfiguracao() {
        if (!foiConfigurado) {
            System.out.println("A PASTA DE CONFIGURAÇÃO DO MÓDULO PERMANECE NO DIRETORIO PADRÃO+" + PASTACONFIGURACAO_PADRAO);
        }
        return PATACONFIGURACAO;

    }

    public static Properties getPropriedadesModulo(Class pProjeto) {

        return null;
    }

}
