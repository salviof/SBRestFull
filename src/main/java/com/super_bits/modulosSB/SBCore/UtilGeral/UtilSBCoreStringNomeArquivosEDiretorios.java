/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.coletivojava.fw.utilCoreBase.UtilSBCoreDiretoriosSimples;
import java.io.File;

/**
 *
 * @author desenvolvedor
 *
 */
public class UtilSBCoreStringNomeArquivosEDiretorios {

    /**
     *
     * Ex: /home/superBits/superBitsDevops numero de casas = 1 retornaria
     * /home/superBits/
     *
     * @param pDiretorio
     * @param numeroCasas
     * @return
     */
    public static String getDiretorioMenosXCasas(String pDiretorio, int numeroCasas) {
        return UtilSBCoreDiretoriosSimples.getDiretorioMenosXCasas(pDiretorio, numeroCasas);
    }

    /**
     * *(este método <b>não </b> certifica se o ultimo nome é um arquivo ou
     * diretorio, simplismente <b>retorna a ultima parte do diretorio</b>)
     *
     * A ideia do metodo é ser simples e rápido, você pode utilizar o objeto
     * File do java para executar processamentos mais complexos
     *
     * @param pDiretorio
     * @return O nome do arquivo sem o diretorio
     */
    public static String getNomeArquivo(String pDiretorio) {
        return UtilSBCoreDiretoriosSimples.getNomeArquivo(pDiretorio);
    }

    /**
     * este método <b>não</b> certifica se o ultimo nome é um arquivo ou
     * diretorio, simplismente retorna o diretorio sem o nome do arquivo)
     *
     * @param pCaminhoCompleto
     * @return O diretorio do arquivo Sem o nome do arquivo
     */
    public static String getDiretorioArquivo(String pCaminhoCompleto) {
        return pCaminhoCompleto.replace("/" + UtilSBCoreDiretoriosSimples.getNomeArquivo(pCaminhoCompleto), "");
    }

    /**
     *
     * Retira a extenção do nome do arquivo exmplo: meusSegredos.txt retornaria
     * meusSegredos
     *
     * @param pNomeArquivo nome completo do arquivo
     * @return nome do arquivo sem extenção
     */
    public static String getNomeDoArquivoSemExtencao(String pNomeArquivo) {
        return pNomeArquivo.lastIndexOf('.') > pNomeArquivo.lastIndexOf(File.separatorChar)
                ? pNomeArquivo.substring(0, pNomeArquivo.lastIndexOf('.'))
                : pNomeArquivo;
    }

    /**
     *
     * Retorna a exetenção de um nome de arquivo, exemplo: senhaDoBanco.txt
     * retorna .txt
     *
     *
     * @param pNomeArquivo Nome do arquivo parametrizado
     * @return exetenção do arquivo
     */
    public static String getExtencaoNomeArquivo(String pNomeArquivo) {
        return pNomeArquivo.lastIndexOf('.') > pNomeArquivo.lastIndexOf(File.separatorChar)
                ? pNomeArquivo.substring(pNomeArquivo.lastIndexOf('.'), pNomeArquivo.length())
                : "";
    }

    /**
     *
     * Retorna a exetenção de um nome de arquivo, exemplo: senhaDoBanco.txt
     * retorna txt
     *
     *
     * @param pNomeArquivo Nome do arquivo parametrizado
     * @return exetenção do arquivo
     */
    public static String getExtencaoNomeArquivoSemPonto(String pNomeArquivo) {
        if (pNomeArquivo == null || pNomeArquivo.length() < 3) {
            return null;
        }
        if (!pNomeArquivo.contains(".")) {
            if (pNomeArquivo.endsWith("docx")) {
                return "docx";
            }
            return pNomeArquivo.substring(pNomeArquivo.length() - 3);
        }
        return pNomeArquivo.lastIndexOf('.') > pNomeArquivo.lastIndexOf(File.separatorChar)
                ? pNomeArquivo.substring(pNomeArquivo.lastIndexOf('.') + 1, pNomeArquivo.length())
                : "";
    }

}
