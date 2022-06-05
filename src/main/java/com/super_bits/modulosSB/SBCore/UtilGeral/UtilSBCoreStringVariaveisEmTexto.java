/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SalvioF
 */
public class UtilSBCoreStringVariaveisEmTexto {

    public static List<String> extrairVariaveisEntreColchete(String pValor) {
        return UtilSBCoreStringValidador.getListaStringEntreCaracter(pValor, UtilSBCoreStringValidador.TIPO_SINALIZADOR.COLCHETE);
    }

    public static List<String> extrairVariaveisEntreAspas(String pValor) {
        return UtilSBCoreStringValidador.getListaStringEntreCaracter(pValor, UtilSBCoreStringValidador.TIPO_SINALIZADOR.ASPAS);
    }

    public static List<String> extrairVariaveisEntreNenorMaior(String pValor) {
        return UtilSBCoreStringValidador.getListaStringEntreCaracter(pValor, UtilSBCoreStringValidador.TIPO_SINALIZADOR.TAG);
    }

    public static String gerarTextoSubstituindoAtributosSimples(String pTexto, ItfBeanSimples pObjeto) {
        if (pTexto == null || pObjeto == null) {
            return pTexto;
        }
        List<String> variaveis = extrairVariaveisEntreColchete(pTexto);
        try {
            if (variaveis.isEmpty()) {
                return pTexto;
            }
            Map<String, String> mapaSubstituicao = new HashMap<>();

            variaveis.stream().forEach(variavel -> {
                try {
                    CaminhoCampoReflexao caminhoCampo = new CaminhoCampoReflexao(variavel.replace("[", "").replace("]", ""), pObjeto.getClass());
                    String valorVariavel = pObjeto.getCampoInstanciadoByNomeOuAnotacao(caminhoCampo.getCaminhoSemNomeClasse()).getValorTextoFormatado();
                    mapaSubstituicao.put(variavel, valorVariavel);
                } catch (Throwable t) {
                    mapaSubstituicao.put(variavel, "ERRO_OBTENDO_VALOR");
                }
            });
            for (String variavel : mapaSubstituicao.keySet()) {
                pTexto = pTexto.replace(variavel, mapaSubstituicao.get(variavel));
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro definindo mensagem em " + pTexto + " com " + pObjeto, t);
        }
        return pTexto;
    }

}
