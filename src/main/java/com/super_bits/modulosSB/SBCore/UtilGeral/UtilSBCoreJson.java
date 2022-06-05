/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author salviofurbino
 * @since 07/06/2019
 * @version 1.0
 */
public class UtilSBCoreJson {

    private static final String padraoRegexNumeroColchete = "(\\[(\\d*+)\\])";
    private static final Pattern regexNumeroColchete = Pattern.compile(padraoRegexNumeroColchete);

    public static String getValorApartirDoCaminho(String pCaminho, JSONObject pJsonSimple) {
        try {
            if (UtilSBCoreStringValidador.isNuloOuEmbranco(pCaminho) || pJsonSimple == null) {
                return null;
            }

            String[] partes = pCaminho.split("\\.");
            if (partes.length == 0) {
                Object valorAtributo = pJsonSimple.get(partes[0]);
                if (valorAtributo == null) {
                    return null;
                } else {
                    return pJsonSimple.get(partes[0]).toString();
                }

            } else {
                JSONObject objetoJsonAnalize = pJsonSimple;
                for (String parte : partes) {
                    if (parte.contains("[")) {

                        // Now create matcher object.
                        Matcher m = regexNumeroColchete.matcher(parte);
                        m.find();
                        String indiceStr = m.group(2);
                        String propriedade = parte.substring(0, parte.indexOf("["));
                        Object objetoProp = objetoJsonAnalize.get(propriedade);
                        Optional<Object> itemEncontrado = null;

                        AtomicInteger indexDinamico = new AtomicInteger(0);
                        Integer indice = Integer.valueOf(indiceStr);
                        if (objetoProp instanceof JSONObject) {
                            objetoJsonAnalize = (JSONObject) objetoJsonAnalize.get(propriedade);
                            itemEncontrado = objetoJsonAnalize.values().stream().
                                    filter(ob -> indice == indexDinamico.getAndIncrement()).findFirst();
                        }
                        if (objetoProp instanceof JSONArray) {
                            JSONArray jarray = (JSONArray) objetoProp;
                            itemEncontrado = jarray.stream().filter(ob -> indice == indexDinamico.getAndIncrement()).findFirst();
                        }
                        if (itemEncontrado == null) {
                            return null;
                        }
                        if (parte.equals(partes[partes.length - 1])) {

                            if (itemEncontrado.isPresent()) {
                                return itemEncontrado.get().toString();
                            } else {
                                return null;
                            }

                        } else {
                            if (itemEncontrado.isPresent()) {
                                Object item = itemEncontrado.get();
                                objetoJsonAnalize = (JSONObject) item;
                            } else {
                                return null;
                            }
                        }
                    } else {

                        if (parte.equals(partes[partes.length - 1])) {
                            Object valor = objetoJsonAnalize.get(parte);
                            if (valor == null) {
                                return null;
                            } else {
                                return objetoJsonAnalize.get(parte).toString();
                            }
                        } else {
                            objetoJsonAnalize = (JSONObject) objetoJsonAnalize.get(parte);
                        }
                    }
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro interpretando caminho em objeto json" + pCaminho, t);
        }
        return null;
    }
}
