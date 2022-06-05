/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.regex;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Salvio Furbino
 */
public class ProcessadorRegexTexto {

    public final List<RegraReconhecimentoRegex> regrasDeReconhecimento;

    public ProcessadorRegexTexto(FabRegraReconhecimentoRegex... regras) {
        regrasDeReconhecimento = new ArrayList<>();

        for (FabRegraReconhecimentoRegex regra : regras) {
            regrasDeReconhecimento.add(regra.getRegistro());
        }

    }

    public String getResultado(String pTexto) {

        for (RegraReconhecimentoRegex regra : regrasDeReconhecimento) {
            try {
                String resposta = regra.getResultado(pTexto);
                if (resposta != null && !resposta.isEmpty()) {
                    return resposta;
                }
            } catch (Throwable t) {
                System.out.println("Erro processando regra" + t.getMessage());
                return "";
            }
        }
        return "";
    }

    public List<String> getResultados(String pTexto) {

        for (RegraReconhecimentoRegex regra : regrasDeReconhecimento) {
            try {
                List<String> resposta = regra.getResultados(pTexto);
                if (!resposta.isEmpty()) {
                    if (resposta.size() > 2) {
                        return resposta;
                    }
                    System.out.println("Resposta" + resposta);
                    return resposta;
                }

            } catch (Throwable t) {
                System.out.println("Erro processando regra" + regra);
                return null;
            }
        }
        return new ArrayList<>();
    }

}
