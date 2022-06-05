/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.telas;

import com.super_bits.modulosSB.SBCore.modulos.view.telas.layout.ItfColunaTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.layout.ItfLinhaTela;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class LinhaTela implements ItfLinhaTela {

    private final GridLayoutTela gridLayout;
    private final List<ItfColunaTela> colunas = new ArrayList<>();

    private int colunasOcupadas;
    private int colunasRestante;

    public LinhaTela(GridLayoutTela gridLayout) {
        this.gridLayout = gridLayout;
        colunasRestante = this.gridLayout.getMaximoColunas();
    }

    @Override
    public void adicionarColuna(ItfColunaTela pColuna) {
        if (getColunas().size() > 1) {
            if (colunasRestante < pColuna.getComponenteVinculado().getPesoLargura()) {
                throw new UnsupportedOperationException("Erro tentando adicionar coluna maior que o disponível na linha");
            }
        }
        colunasOcupadas += pColuna.getComponenteVinculado().getPesoLargura();
        colunasRestante -= pColuna.getComponenteVinculado().getPesoLargura();
        colunas.add(pColuna);
    }

    @Override
    public List<ItfColunaTela> getColunas() {
        return colunas;
    }

    @Override
    public int getColunasOcupadas() {
        return colunasOcupadas;
    }

    @Override
    public int getColunasRestante() {
        return colunasRestante;
    }

    @Override
    public String getClasseOrganizacao() {
        if (gridLayout.getMaximoColunas() < 4) {
            return "OrganizadorContainer";
        }

        if (gridLayout.getLinhas().size() > 1) {
            if (gridLayout.getLinhas().get(0) == this) {
                return "OrganizadorContainer";
            }

            if (colunasRestante > 3) {
                return "OrganizadorContainerEsquerda";
            } else {
                return "OrganizadorContainerExpandido";
            }

        } else {

            return "OrganizadorContainer";
        }

    }

}
