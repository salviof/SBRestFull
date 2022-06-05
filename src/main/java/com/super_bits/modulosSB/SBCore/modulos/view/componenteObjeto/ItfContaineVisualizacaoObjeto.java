/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.componenteObjeto;

import com.super_bits.modulosSB.SBCore.modulos.view.telas.FabTipoTamanhoTelas;

/**
 *
 * @author SalvioF
 */
public interface ItfContaineVisualizacaoObjeto {

    public int getColunas();

    public String getNomeEntidade();

    public String getNomeTipoVisualizacao();

    public FabTipoTamanhoTelas getpTipoTela();

    public boolean isTemCSS();

    public boolean isTemJavaScript();

    public boolean isTemVersaoMobile();

    public String getClasseContainer();

    public String getClasseContainerRelativoColunaSuperior(int pColunaSuperior);

    public String getCaminhoRelativo();

    public String getCaminhoRelativoMobile();

    public String getCaminhoRelativoProducao();

    public String getCaminhoRelativoMobileProducao();

    public String getCaminhoRelativoLaboratorio();

    public String getCaminhoRelativoMobileLaboratorio();

    public boolean isExisteVersaoProducao();

    public boolean isVersaoProducaoResponsivoAtualizazada();

    public boolean isVersaoMobileResponsivoAtualizazada();

}
