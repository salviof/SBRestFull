/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsMaiuculoMinusculo;

/**
 *
 * @author salvioF
 */
public abstract class ServicoVisualizacaoAbstrato implements ItfServicoVisualizacao {

    public final static String TIPO_VISUALIZACAO_PADRAO = "CardPadrao";
    private final TIPOS_INTERFACES_COMUM_VISUALIZACAO tipoVisualizacao;

    public enum TIPOS_INTERFACES_COMUM_VISUALIZACAO {
        MOBILE_ANDROID, WEB_RESPONSIVO, DESKTOP_SWING;

        public String getPastaRecursosVisualizacao() {
            switch (this) {
                case MOBILE_ANDROID:
                    return "/AindNãoImplementado";
                case WEB_RESPONSIVO:
                    return "/resources/";
                case DESKTOP_SWING:
                    return "/AindNãoImplementado";
                default:
                    throw new AssertionError(this.name());

            }
        }

    }

    public enum TIPO_VISUALIZACAO_ITEM {
        LABORATORIO, PUBLICADO
    }

    public ServicoVisualizacaoAbstrato(TIPOS_INTERFACES_COMUM_VISUALIZACAO pTipoVisualizacao) {
        tipoVisualizacao = pTipoVisualizacao;
    }

    @Override
    public String buildArqXHTML(Class pEntidade, String ptipoVisualizacao, boolean versaoMobile) {
        if (!versaoMobile) {
            return UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(pEntidade.getSimpleName()) + ptipoVisualizacao + ".xhtml";
        } else {
            return UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(pEntidade.getSimpleName()) + ptipoVisualizacao + "Mobile.xhtml";
        }
    }

    @Override
    public String buildArqJavaScript(Class pEntidade, String ptipoVisualizacao) {
        return UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(pEntidade.getSimpleName()) + ptipoVisualizacao + ".js";
    }

    @Override
    public String buildArqCSSFolhaDeEstilo(Class pEntidade, String ptipoVisualizacao) {
        return UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(pEntidade.getSimpleName()) + ptipoVisualizacao + ".css";
    }

    private String buildCaminhoPastaView(Class pEntidade) {
        return "/resources/objeto/" + pEntidade.getSimpleName();
    }

    @Override
    public String buildCaminhoXHTMLComponente(TIPO_VISUALIZACAO_ITEM pTipoVisualizacao, Class pEntidade, String pTipoEspecial, Integer pQuantidadeColunas, boolean visualizacaoMobile) {
        if (pQuantidadeColunas == null || pQuantidadeColunas == 0) {
            pQuantidadeColunas = 3;
        }
        return buildPastaContainers(pTipoVisualizacao, pEntidade) + pTipoEspecial + "/" + pQuantidadeColunas + "/" + buildArqXHTML(pEntidade, pTipoEspecial, visualizacaoMobile);
    }

    @Override
    public String buildPastaContainers(TIPO_VISUALIZACAO_ITEM pTipoVisualizacao, Class pClasse) {
        if (pTipoVisualizacao == null) {
            throw new UnsupportedOperationException("O tipo  de visualização não foi enviado");
        }
        if (pClasse == null) {
            throw new UnsupportedOperationException("A classe não foi enviada para contrução da pasta de container de objeto");
        }

        switch (pTipoVisualizacao) {
            case LABORATORIO:

                return buildCaminhoPastaView(pClasse) + "/laboratorio/";

            case PUBLICADO:
                return buildCaminhoPastaView(pClasse) + "/publicado/";
            default:
                throw new AssertionError(pTipoVisualizacao.name());

        }
    }
}
