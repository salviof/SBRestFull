/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import static com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore.RelatarErro;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * Classe de UTILITÀRIOS (Métodos EStáticos commmente Utilizados)____________
 * Função: Ler Dados nos modelos mais comuns: Strings, BufferedStream e Imagem.
 * Suporta URLS, LOCALFILES CONEXOES TCP E UDP
 *
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 19/10/2014
 */
public abstract class UTilSBCoreInputs {

    private static final int timeoutDeConexaoPadrao = 2000;
    private static final int timeoutDeLeituraPadrao = 50000;

    /**
     *
     * Nesta lista cada String é uma linha do arquivo
     *
     * @param pCaminhoArquivoLocal Diretorio do arquivo Local
     * @return Uma lista de Strings,
     */
    public static List<String> getStringsByArquivoLocal(String pCaminhoArquivoLocal) {

        File arquivo = new File(pCaminhoArquivoLocal);
        List<String> conteudo = new ArrayList();
        try (Scanner scanner = new Scanner(arquivo)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                conteudo.add(line);
            }
            scanner.close();
            return conteudo;
        } catch (IOException e) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro lendo arquivo:" + pCaminhoArquivoLocal, e);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "", e);
            return null;
        }

    }

    public static String getStringByArquivoLocal(String pCaminhoArquivoLocal) {
        File arquivo = new File(pCaminhoArquivoLocal);
        String conteudo = "";
        File file = new File(pCaminhoArquivoLocal);
        try (FileInputStream fis = new FileInputStream(file)) {
            int content;
            while ((content = fis.read()) != -1) {
                conteudo += ((char) content);
            }
            fis.close();
        } catch (IOException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro lendo arquivo" + pCaminhoArquivoLocal, e);
        }
        return conteudo;
    }

    /**
     * Obtem um BufferedInputsSream por Caminho Local
     *
     * @param pCaminhoLocal Caminho local do arquivo
     * @return Buffered Stream do Arquivo
     */
    public static BufferedInputStream getStreamBufferedByLocalFile(String pCaminhoLocal) {
        File arq = new File(pCaminhoLocal);
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        try {
            fis = new FileInputStream(arq);
            bis = new BufferedInputStream(fis);
            return bis;

        } catch (IOException e) {
            RelatarErro(FabErro.SOLICITAR_REPARO, "erro lendo o arquivo:" + pCaminhoLocal, e);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                RelatarErro(FabErro.SOLICITAR_REPARO, "erro lendo o arquivo:" + pCaminhoLocal, ex);
            }
        }
        return null;
    }

    /**
     * Obtem um bufferedImage por caminho local
     *
     * @param pCaminho Caminho Local do arquivo
     * @return Buffered Image
     */
    public static BufferedImage getImagemBufferedByLocalFile(String pCaminho) {

        try {
            File arquivo = new File(pCaminho);
            return ImageIO.read(arquivo);
        } catch (Exception e) {
            RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Tentando Gerar BufferedImage apartir de arquivo em disco" + pCaminho, e);
            return null;
        }
    }

    public static Image getImagemByLocalFile(String pCaminho) {
        return getImagemBufferedByLocalFile(pCaminho);

    }

    private static URLConnection getConexao(String pURL, int pTimeOutConexao, int pTimeoutLeitura) {
        try {
            if (pTimeOutConexao == 0) {
                pTimeOutConexao = timeoutDeConexaoPadrao;
            }
            if (pTimeoutLeitura == 0) {
                pTimeoutLeitura = timeoutDeLeituraPadrao;
            }

            URL url = new URL(pURL);
            try {
                URLConnection c;
                c = url.openConnection();
                c.setReadTimeout(timeoutDeLeituraPadrao);
                c.setConnectTimeout(timeoutDeConexaoPadrao);
                c.addRequestProperty("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
                return c;
            } catch (IOException e) {

                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obrendo url da conexao", e);
            }
        } catch (MalformedURLException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "FORMATO DEU URL INVÁLIDO", e);
        }

        return null;
    }

    private static URLConnection getConexao(String pURL) {
        return getConexao(pURL, 0, 0);
    }

    /**
     * Obtem um Imagem Buffered pelo URL
     *
     * @param pUrl Caminho completo do URL
     * @param pTimeoutConexao Timeout de Conexão com o Servidor
     * @param pTimeoutLeitura Timeout de Leitura do arquivo
     * @return
     */
    public static BufferedImage getImagemBufferedbyURL(String pUrl, int pTimeoutConexao, int pTimeoutLeitura) {

        try {

            BufferedInputStream arqFoto = getStreamBuffredByURL(pUrl, pTimeoutConexao, pTimeoutLeitura);
            return ImageIO.read(arqFoto);

        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Tentando Gerar buffered image by URL", e);
            return null;
        }

    }

    /**
     * Obtem Um buffered Imagem por caminho de URL OBS: Utiliza valores padrões
     * de timeout de conexão e Leitura
     *
     * @param pUrl Caminho do URL
     * @return
     */
    public static BufferedImage getImagemBufferedbyURL(String pUrl) {
        return getImagemBufferedbyURL(pUrl, timeoutDeConexaoPadrao, timeoutDeLeituraPadrao);

    }

    /**
     *
     * Lê um arquivo do tipo TEXTO apartir de uma URL e retorna a o conteudo em
     * uma Lista de Strings
     *
     * @param pUrl Url onde o arquivo se Encontra
     * @return Lista de Strings, onde cada linha é um item da lista.
     */
    public static List<String> getStringsByURL(String pUrl) {

        URLConnection conexao = getConexao(pUrl);

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            List<String> resposta = new ArrayList<>();
            try {
                String linha;
                while ((linha = in.readLine()) != null) {
                    resposta.add(linha);
                }
                return resposta;
            } finally {
                in.close();
            }

        } catch (IOException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando criar input por conexao de URL", ex);
        }
        return null;
    }

    public static InputStream getStreamByURL(String pUrl) {
        return getStreamByURL(pUrl, -1);

    }

    public static InputStream getStreamByURL(String pUrl, int pSegundosTimeout) {
        URLConnection conexao;
        if (pSegundosTimeout <= 0) {
            conexao = getConexao(pUrl);
        } else {
            conexao = getConexao(pUrl, 8000, pSegundosTimeout * 1000);
        }

        try {

            return conexao.getInputStream();

        } catch (IOException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando criar input por conexao de URL", ex);
        }
        return null;
    }

    public static InputStream getStreamByLocalFile(String pCaminhoLocal) {

        try {
            File arquivo = new File(pCaminhoLocal);

            FileInputStream input = new FileInputStream(arquivo);
            return input;
        } catch (Throwable t) {
            if (SBCore.isAmbienteCoreConfigurado()) {
                SBCore.RelatarErro(FabErro.LANCAR_EXCECÃO, "Erro obtendo InputStream do arquivo local" + pCaminhoLocal, t);
            }
            return null;
        }
    }

    /**
     * Obtem um BufferedInputStream por URL
     *
     * @param pUrl Caminho URL
     * @param pTimeoutConexao limite de tempo de conexão
     * @param pTimeoutLeitura Limite de tempo de Leitura
     * @return
     */
    public static BufferedInputStream getStreamBuffredByURL(String pUrl, int pTimeoutConexao, int pTimeoutLeitura) {
        URLConnection c = getConexao(pUrl, pTimeoutConexao, pTimeoutLeitura);

        if (c != null) {
            try {
                BufferedInputStream arq = new BufferedInputStream(c.getInputStream());

                return arq;
            } catch (IOException ex) {

                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Frame:" + pUrl, ex);
            }
        }
        return null;
    }

    public static BufferedInputStream getStreamBuffredByURL(String pUrl) {
        return getStreamBuffredByURL(pUrl, 0, 0);
    }

    public static Image getImageByURL(String pUrl) {
        return (Image) getImagemBufferedbyURL(pUrl);

    }

}
