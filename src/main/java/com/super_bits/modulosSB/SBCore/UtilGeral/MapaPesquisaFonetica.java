/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import mtfn.MetaphonePtBr;

/**
 *
 * @author novy
 */
public class MapaPesquisaFonetica {

    private static final Map<String, String> DICIONARIO = new ConcurrentHashMap();

    public static boolean isValorFoneticoCompativel(ItfCampoInstanciado pCampo, String chaveFonetica) {
        String identificadorCampo = getIdentificacaoCampo(pCampo);
        try {
            String valor = DICIONARIO.get(identificadorCampo);

            if (valor == null) {
                valor = getChaveFonetica(pCampo.getValor().toString());
                DICIONARIO.put(identificadorCampo, valor);
            }
            return valor.contains(chaveFonetica);
        } catch (NullPointerException nul) {
            return false;
        }

    }

    public static String getIdentificacaoCampo(ItfCampoInstanciado pCampo) {
        return pCampo.getObjetoDoAtributo().getClass().getSimpleName() + "." + pCampo.getNomeCamponaClasse() + pCampo.getObjetoDoAtributo().getId();
    }

    public static String getChaveFonetica(String palavra) {
        return new MetaphonePtBr(palavra).toString();
    }

}
