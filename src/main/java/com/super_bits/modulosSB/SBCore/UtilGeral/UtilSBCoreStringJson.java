/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author novy
 */
public class UtilSBCoreStringJson {

    public static JSONObject gerarObjetoJsonByString(String pString) {
        try {
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(pString);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando objeto Json a partir de string" + t.getMessage(), t);
            return null;
        }
    }

}
