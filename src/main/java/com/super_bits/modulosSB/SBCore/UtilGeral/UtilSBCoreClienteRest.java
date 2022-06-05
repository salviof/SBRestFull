/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author SalvioF
 */
public class UtilSBCoreClienteRest {

    public static JSONObject getObjetoJsonPorUrl(String pUrl) {
        try {
            URL url = new URL(pUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");

            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Falha erro HTTP codigo : " + conn.getResponseCode() + "->" + conn.getResponseMessage());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            String respostaStr = "";
            while ((output = br.readLine()) != null) {
                respostaStr += output;
            }
            //System.out.println(respostaStr);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(respostaStr);
            return json;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro   " + pUrl, t);
            return null;
        }
    }

    public static void getRelatorioTesteDeAcessoGet(String pUrl) {
        try {
            JSONObject json = getObjetoJsonPorUrl(pUrl);

            Collection valores = json.values();

            for (Object teste : valores) {

                try {
                    System.out.println(teste.getClass().getSimpleName());

                    if (teste instanceof JSONArray) {
                        System.out.println("Array de dadados ------------>");
                        JSONArray array = (JSONArray) teste;

                        for (Iterator iterator = array.iterator(); iterator.hasNext();) {
                            Object next = iterator.next();
                            if (next instanceof JSONObject) {
                                Map<String, Object> informacao = (Map<String, Object>) next;
                                for (String key : informacao.keySet()) {
                                    System.out.println("-------------" + key + "(" + informacao.get(key).getClass().getSimpleName() + ")--------");
                                    System.out.println(informacao.get(key));
                                }
                            } else {
                                System.out.println("------------");
                                System.out.println(next.getClass().getSimpleName());
                                System.out.println(next);
                            }

                        }

                    }

                    if (teste instanceof JSONObject) {
                        Map<String, Object> informacao = (Map<String, Object>) teste;
                        for (String key : informacao.keySet()) {
                            System.out.println("-------------" + key + "(" + informacao.get(key).getClass().getSimpleName() + ")--------");
                            System.out.println(informacao.get(key));
                        }
                    } else {
                        System.out.println("------------");
                        System.out.println(teste.getClass().getSimpleName());
                        System.out.println(teste);
                    }
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Lendo valores" + teste, t);
                }

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo relatório de Acesso REste", t);
        }

    }

    public static void getRelatorioTesteDeAcessoPut(String pUrl) {

    }

}
