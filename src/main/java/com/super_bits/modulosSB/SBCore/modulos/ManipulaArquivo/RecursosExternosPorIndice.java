/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author SalvioF
 */
public class RecursosExternosPorIndice {

    private final String pastaRepositorio;

    public RecursosExternosPorIndice(Class<? extends ItfFabConfigModulo> pClasse) {

        pastaRepositorio = SBCore.getCentralDeArquivos().getEndrLocalResources() + "/" + pClasse.getSimpleName();
        if (!new File(pastaRepositorio).exists()) {
            new File(pastaRepositorio).mkdirs();
        }
    }

    private String getCaminhoArquivo(String pIndice) {
        return pastaRepositorio + "/" + pIndice;
    }

    public boolean putConteudoRecursoExterno(String pIndice, String pConteudo) {
        try {
            return UtilSBCoreArquivoTexto.escreverEmArquivoSubstituindoArqAnterior(getCaminhoArquivo(pIndice), pConteudo);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro escrevendo em indice", t);
            return false;
        }
    }

    public String getTexto(String indice) {
        String arquivo = pastaRepositorio + "/" + indice;
        if (!new File(pastaRepositorio + "/" + indice).exists()) {
            return null;
        } else {
            return UTilSBCoreInputs.getStringByArquivoLocal(arquivo);
        }
    }

    public JSONObject getJsonObjeto(String pIndice) {
        try {
            String texto = getTexto(pIndice);
            if (UtilSBCoreStringValidador.isNuloOuEmbranco(texto)) {
                return null;
            }
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(texto);
        } catch (ParseException t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo objeto Json" + t.getMessage(), t);
            return null;
        }
    }

    public JSONArray getJsonListaObjeto(String pIndice) {
        try {
            String texto = getTexto(pIndice);
            if (texto == null) {
                return null;
            }
            JSONParser parser = new JSONParser();
            return (JSONArray) parser.parse(texto);
        } catch (ParseException t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo objeto Json" + t.getMessage(), t);
            return null;
        }
    }

}
