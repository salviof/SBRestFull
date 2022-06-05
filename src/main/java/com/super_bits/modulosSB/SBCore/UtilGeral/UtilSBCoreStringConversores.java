/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreStringConversores {

    /**
     *
     * Gera uma lista de Strings sendo cada uma referente a uma linha do arquivo
     *
     * @param pInputStream Origem do arquivo para leitura
     * @return Lista de strings, cada linha em uma String
     */
    public static List<String> getStringsByInputStream(InputStream pInputStream) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        List<String> resp = new ArrayList<>();
        String line;
        try {

            br = new BufferedReader(new InputStreamReader(pInputStream));
            while ((line = br.readLine()) != null) {
                resp.add(line);

            }

        } catch (IOException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo string a partir de arquivo", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro fechando arquivo", e);
                }
            }
        }

        return resp;
    }

    /**
     *
     * Retorna uma String a partir de uma fonte de InputStream
     *
     * @param pInputStream
     * @return
     */
    public static String getStringByInputStream(InputStream pInputStream) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(pInputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (IOException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo string a partir de arquivo", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro fechando arquivo", e);
                }
            }
        }

        return sb.toString();
    }

    /**
     *
     * Retorna uma String apartir de um outrput
     *
     * @param pOutputStream
     * @return
     */
    public static String getStringByOutputStream(OutputStream pOutputStream) {
        ByteArrayOutputStream byte1 = new ByteArrayOutputStream();
        try {
            pOutputStream.write(byte1.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(UtilSBCoreStringValidador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return byte1.toString();
    }

}
