/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.regex;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Esta fábrica almeja cadastrar os Regex mais utilizados no sistema
 *
 *
 *
 *
 * @author desenvolvedor
 */
public enum FabRegraReconhecimentoRegex implements ItfFabrica {

    PESO_DOIS_PONTOS_VALOR,
    ALTURA_DOIS_PONTOS_VALOR,
    LARGURA_DOIS_PONTOS_VALOR,
    PROFUNDIDADE_DOIS_PONTOS_VALOR,
    PARMETRO_DOIS_PONTOS_VALOR,
    DIMENSOES,
    DIMENSOES_AVANCADO,
    MEDIDAS;

    /**
     *
     * \d -> Qualquer dígito numérioco, <br>
     * * -> podendo ter mais de um caracter [.,] Um ponto e uma virgula,
     * interrogação tonar opcional <br>
     *
     *
     */
    public final static String NUMERO_DECIMAL_OPCIONAL = "\\d*[,.]?\\d*";

    /**
     *
     */
    public final static String QUALQUERCOISA_DOIS_PONTOS = "[A-Za-z\\s]*[:]";

    /**
     *
     */
    public final static String ESPACO_OPCIONAL = "\\s?";
    /**
     *
     *
     * @see #ESPACO_OPCIONAL
     */
    public final static String XIS_ESPACO_OPCIONAL = ESPACO_OPCIONAL + "[xX]" + ESPACO_OPCIONAL;

    /**
     *
     * Regex para encontrar o valor em uma string que utilize a sintax:
     * [NomeDoMenCampo] [qualquerCoisa] [:doispontos] [qualquerCoisa] [um número
     * com ou sem separador decimal] <-não suporta decimal+milhar
     *
     * Cada um dos componentes deste regex está dividido em grupos
     *
     *
     * @see Match
     * @see #QUALQUERCOISA_DOIS_PONTOS
     * @param pParametroNomeCampo
     * @return
     */
    public static String getPadraoCampoDoisPontoUmValorNumerico(String pParametroNomeCampo) {
        String VARCAMPOENVIADO = "CAMPO_ENVIADO";
        String valor = "(" + VARCAMPOENVIADO + QUALQUERCOISA_DOIS_PONTOS + ")(" + ESPACO_OPCIONAL + ")(" + NUMERO_DECIMAL_OPCIONAL + ")";
        return valor.replace(VARCAMPOENVIADO, pParametroNomeCampo);
    }

    public RegraReconhecimentoRegex getRegraComParametro(String pParametro) {
        throw new UnsupportedOperationException("ainda não implementado");
    }

    @Override
    public RegraReconhecimentoRegex getRegistro() {
        String regra = "Não definido";
        List<Integer> grupos = new ArrayList<>();
        FabRegexTipoProcessamento pTipoProcessamento = null;
        switch (this) {
            case PESO_DOIS_PONTOS_VALOR:
                regra = getPadraoCampoDoisPontoUmValorNumerico("Peso");
                pTipoProcessamento = FabRegexTipoProcessamento.PRIMEIRO_NUMERO_ENCONTRADO;
                break;
            case PARMETRO_DOIS_PONTOS_VALOR:
                throw new UnsupportedOperationException("Utilize Regra com Parametro para criar uma regra deste tipo");
            case DIMENSOES:
                regra = "([Dimensões][A-Za-z\\s]*[:])([A-Za-z\\s]*\\s?)(\\d*[,.]?\\d*)(\\s?[x]\\s?)([A-Za-z\\s]*\\s?)(\\d*[,.]?\\d*)(\\d*[,.]?\\d*)(\\s?[x]\\s?)?([A-Za-z\\s]*\\s?)(\\s?[x]\\s?)?(\\d*[,.]?\\d*)";
                pTipoProcessamento = FabRegexTipoProcessamento.NUMEROS_ENCONTRADOS;
                break;
            case MEDIDAS:
                regra = "(Medidas[A-Za-z\\s]*[:])(\\s?)(\\d*[,.]?\\d*)(\\s?[x]\\s?)(\\d*[,.]?\\d*)(\\d*[,.]?\\d*)(\\s?[x]\\s?)?(\\s?[x]\\s?)?(\\d*[,.]?\\d*)";
                pTipoProcessamento = FabRegexTipoProcessamento.NUMEROS_ENCONTRADOS;
                break;
            case ALTURA_DOIS_PONTOS_VALOR:
                regra = getPadraoCampoDoisPontoUmValorNumerico("ltura");
                pTipoProcessamento = FabRegexTipoProcessamento.PRIMEIRO_NUMERO_ENCONTRADO;
                break;
            case LARGURA_DOIS_PONTOS_VALOR:
                regra = getPadraoCampoDoisPontoUmValorNumerico("argura");
                pTipoProcessamento = FabRegexTipoProcessamento.PRIMEIRO_NUMERO_ENCONTRADO;
                break;
            case PROFUNDIDADE_DOIS_PONTOS_VALOR:
                regra = getPadraoCampoDoisPontoUmValorNumerico("rofundidade");
                pTipoProcessamento = FabRegexTipoProcessamento.PRIMEIRO_NUMERO_ENCONTRADO;
                break;
            case DIMENSOES_AVANCADO:
                regra = "([imensões][A-Za-z\\s?][:?])([A-Za-z\\s?])([A-Za-z\\s?])(\\s?)(\\d*[,.]?\\d*)([A-Za-z\\s?])[xX?]([A-Za-z\\s?])([A-Za-z\\s?])([A-Za-z\\s?])(\\d*[,.]?\\d*)([A-Za-z\\s?])[xX?]([A-Za-z\\s?])([A-Za-z\\s?])([A-Za-z\\s?])(\\d*[,.]?\\d*)";
                pTipoProcessamento = FabRegexTipoProcessamento.NUMEROS_ENCONTRADOS;
                break;

            default:
                throw new AssertionError(this.name());

        }
        if (pTipoProcessamento == null) {
            throw new UnsupportedOperationException("O tipo de processamento não foi definido");
        }
        switch (pTipoProcessamento) {
            case GRUPO_ESPECIFICO:
                return new RegraReconhecimentoRegex(this.toString(), regra, grupos.get(0));

            case LISTAGEM_DE_GRUPOS:
                return new RegraReconhecimentoRegex(this.toString(), regra, grupos);

            case NUMEROS_ENCONTRADOS:
                return new RegraReconhecimentoRegex(this.toString(), regra, FabRegexTipoProcessamento.NUMEROS_ENCONTRADOS);
            case PRIMEIRO_NUMERO_ENCONTRADO:
                return new RegraReconhecimentoRegex(this.toString(), regra, FabRegexTipoProcessamento.PRIMEIRO_NUMERO_ENCONTRADO);
            default:
                throw new AssertionError(pTipoProcessamento.name());

        }

    }

}
