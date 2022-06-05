/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.componenteObjeto;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.view.ServicoVisualizacaoAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.FabTipoTamanhoTelas;
import java.io.File;

/**
 *
 * @author SalvioF
 */
public class ContainerVisualizacaoObjeto implements ItfContaineVisualizacaoObjeto {

    private final ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM tipoContainer;
    protected int colunas;
    protected FabTipoTamanhoTelas pTipoTela;
    protected boolean temJavaScript;
    protected boolean temCSS;
    protected boolean temVersaoMobile;
    protected String caminhoRelativo;
    protected String caminhoRelativoMobile;
    protected String nomeTipoVisualizacao;
    protected String nomeEntidade;
    private final Class classe;

    public ContainerVisualizacaoObjeto(boolean pTemJavaScript, boolean pTemCSS, boolean pTemVersaoMobile, int pQtdConlunas, String pTipoVisualizacao, Class pClasse, ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM pTipoVisualizacaoContainer) {
        nomeTipoVisualizacao = pTipoVisualizacao;
        nomeEntidade = pClasse.getSimpleName();
        classe = pClasse;
        temCSS = pTemCSS;
        temVersaoMobile = pTemVersaoMobile;
        temJavaScript = pTemJavaScript;
        colunas = pQtdConlunas;
        tipoContainer = pTipoVisualizacaoContainer;
        atualizarCaminhos();

    }

    protected final void atualizarCaminhos() {
        caminhoRelativo = SBCore.getCentralVisualizacao().buildCaminhoXHTMLComponente(tipoContainer, classe, nomeTipoVisualizacao, colunas, false);
        if (!temVersaoMobile) {
            caminhoRelativoMobile = null;
        } else {
            caminhoRelativoMobile = SBCore.getCentralVisualizacao().buildCaminhoXHTMLComponente(tipoContainer, classe, nomeTipoVisualizacao, colunas, true);
        }
    }

    @Override
    public int getColunas() {
        return colunas;
    }

    @Override
    public FabTipoTamanhoTelas getpTipoTela() {
        return pTipoTela;
    }

    @Override
    public boolean isTemJavaScript() {
        return temJavaScript;
    }

    @Override
    public boolean isTemCSS() {
        return temCSS;
    }

    @Override
    public boolean isTemVersaoMobile() {
        return temVersaoMobile;
    }

    @Override
    public String getCaminhoRelativo() {
        return caminhoRelativo;
    }

    @Override
    public String getCaminhoRelativoMobile() {
        return caminhoRelativoMobile;
    }

    @Override
    public String getNomeTipoVisualizacao() {
        return nomeTipoVisualizacao;
    }

    @Override
    public String getNomeEntidade() {
        return nomeEntidade;
    }

    @Override
    public String getClasseContainer() {
        switch (colunas) {
            case 1:
                return "Container10";
            case 2:
                return "Container20";
            case 3:
                return "Container30";
            case 4:
                return "Container40";
            case 5:
                return "Container50";
            case 6:
                return "Container50";
            case 7:
                return "Container60";
            case 8:
                return "Container60";
            case 9:
                return "Container70";
            case 10:
                return "Container80";
            case 11:
                return "Container90";
            case 12:
                return "Container100";
            default:
                return "Container30";
        }
    }

    @Override
    public String getClasseContainerRelativoColunaSuperior(int pColunaSuperior) {
        if (pColunaSuperior == 12) {
            return getClasseContainer();
        }

        if (pColunaSuperior > 12 || pColunaSuperior < 1) {
            throw new UnsupportedOperationException("Utilize valores de 1 a 12");
        }
        double valorEnviadoEmPorcentagemTEla = pColunaSuperior * 8.333;
        double valorColunaSuperior = 10 * (Math.ceil(Math.abs(valorEnviadoEmPorcentagemTEla / 10)));
        double valorContainerEmPorcentagemTela = colunas * 8.333;
        double valorColunaConteiner = 10 * (Math.ceil(Math.abs(valorContainerEmPorcentagemTela / 10)));
        if (valorColunaSuperior <= valorColunaConteiner) {
            return "Container100";
        } else {
            double diferenca = valorColunaSuperior - valorColunaConteiner;
            double tamanhoContainer = valorColunaConteiner + diferenca;
            if (tamanhoContainer > 100) {
                return "Container100";
            }
            return "Container" + tamanhoContainer;
        }

    }

    @Override
    public String getCaminhoRelativoProducao() {
        return SBCore.getCentralVisualizacao().buildCaminhoXHTMLComponente(ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM.PUBLICADO, classe, nomeTipoVisualizacao, colunas, false);
    }

    @Override
    public String getCaminhoRelativoMobileProducao() {
        return SBCore.getCentralVisualizacao().buildCaminhoXHTMLComponente(ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM.PUBLICADO, classe, nomeTipoVisualizacao, colunas, false);
    }

    @Override
    public String getCaminhoRelativoLaboratorio() {
        return SBCore.getCentralVisualizacao().buildCaminhoXHTMLComponente(ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM.LABORATORIO, classe, nomeTipoVisualizacao, colunas, false);
    }

    @Override
    public String getCaminhoRelativoMobileLaboratorio() {
        return SBCore.getCentralVisualizacao().buildCaminhoXHTMLComponente(ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM.LABORATORIO, classe, nomeTipoVisualizacao, colunas, false);
    }

    @Override
    public boolean isExisteVersaoProducao() {
        return new File(SBCore.getCentralVisualizacao().buildCaminhoXHTMLComponente(tipoContainer, classe, nomeTipoVisualizacao, colunas, false)).exists();
    }

    @Override
    public boolean isVersaoProducaoResponsivoAtualizazada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isVersaoMobileResponsivoAtualizazada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
