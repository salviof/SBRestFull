/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.regex;

import com.google.common.primitives.Ints;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 *
 *
 *
 *
 * @author Salvio Furbino
 */
public class RegraReconhecimentoRegex {

    private final Pattern regraRegex;
    private final String nomeRegra;
    private final String padraoRegex;
    private final Integer idGrupo;
    private final int[] idGrupos;
    private Matcher encontrador;
    private String textoCompleto;
    private String resposta;
    private List<String> respostas;
    private FabRegexTipoProcessamento tipoProcessamento;

    public RegraReconhecimentoRegex(String pnomeRegra, String pPadrao, int idGrupo) {
        this.padraoRegex = pPadrao;
        this.idGrupo = idGrupo;
        regraRegex = Pattern.compile(pPadrao);
        idGrupos = null;
        nomeRegra = pnomeRegra;
        defineTipoProcessamento();
    }

    public RegraReconhecimentoRegex(String pNomeRegra, String pPadrao, FabRegexTipoProcessamento pTipoProcessamento) {
        defineTipoProcessamento(pTipoProcessamento);
        idGrupos = null;
        this.idGrupo = null;
        padraoRegex = pPadrao;
        nomeRegra = pNomeRegra;
        regraRegex = Pattern.compile(pPadrao);
        defineTipoProcessamento(pTipoProcessamento);
    }

    public RegraReconhecimentoRegex(String pNomeRegra, String pPadrao, List<Integer> pIdGrupos) {
        this.padraoRegex = pPadrao;
        this.idGrupo = null;
        regraRegex = Pattern.compile(pPadrao);
        idGrupos = Ints.toArray(pIdGrupos);
        nomeRegra = pNomeRegra;
        defineTipoProcessamento();
    }

    public RegraReconhecimentoRegex(String pNomeRegra, String pPadrao, int... pIdGrupos) {
        this.padraoRegex = pPadrao;
        this.idGrupo = null;
        regraRegex = Pattern.compile(pPadrao);
        idGrupos = pIdGrupos;
        nomeRegra = pNomeRegra;
        defineTipoProcessamento();
    }

    private void defineTipoProcessamento(FabRegexTipoProcessamento pTipoProcessamento) {
        if (pTipoProcessamento != null) {
            tipoProcessamento = pTipoProcessamento;
            return;
        }
        if (idGrupo != null) {
            tipoProcessamento = FabRegexTipoProcessamento.GRUPO_ESPECIFICO;
        } else {
            tipoProcessamento = FabRegexTipoProcessamento.LISTAGEM_DE_GRUPOS;
        }

    }

    private void defineTipoProcessamento() {
        defineTipoProcessamento(null);
    }

    public List<String> getResultados(String pValor) {
        processarRegex(pValor);
        return respostas;
    }

    public String getResultado(String pValor) {
        processarRegex(pValor);

        return resposta;
    }

    private void processarRegex(String pValor) {
        textoCompleto = pValor;
        encontrador = regraRegex.matcher(textoCompleto);

        encontrador.find();

        respostas = new ArrayList<>();
        switch (tipoProcessamento) {
            case GRUPO_ESPECIFICO:
                resposta = encontrador.group(idGrupo);
                respostas.add(resposta);
                break;
            case LISTAGEM_DE_GRUPOS:
                for (int i : idGrupos) {
                    respostas.add(encontrador.group(i));
                }
                resposta = encontrador.group();

                break;
            case NUMEROS_ENCONTRADOS:
            case PRIMEIRO_NUMERO_ENCONTRADO:
                int quantidadeEncontrada = encontrador.groupCount();
                for (int i = 0; i <= quantidadeEncontrada; i++) {

                    try {
                        String valor = encontrador.group(i);

                        if (UtilSBCoreStringValidador.isContemApenasNumeroOuDecimalSeparador(valor)) {
                            respostas.add(valor);
                            switch (tipoProcessamento) {
                                case PRIMEIRO_NUMERO_ENCONTRADO:
                                    resposta = respostas.get(0);
                                    return;
                            }
                        }
                    } catch (Throwable t) {
                        System.out.println("Erro Localizando grupo" + i);
                    }
                }
                if (!respostas.isEmpty()) {
                    resposta = respostas.get(0);
                }

                break;

            default:
                throw new AssertionError(tipoProcessamento.name());

        }

    }

    public List<String> getResultadosComNumeros() {

        return null;

    }

    public String getGrupoCompleto(String pValor) {
        return regraRegex.matcher(padraoRegex).group();
    }

    public String getPadraoRegex() {
        return padraoRegex;
    }

}
