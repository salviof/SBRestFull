/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.telas;

import com.super_bits.modulosSB.SBCore.modulos.view.telas.layout.ItfColunaTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.layout.ItfGridLayoutTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.layout.ItfLinhaTela;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class GridLayoutTela implements ItfGridLayoutTela {

    private final List<ItfLinhaTela> linhas = new ArrayList<>();
    private final int maximoColunas;
    private final ItfTipoTela tipoTela;

    public GridLayoutTela(ItfTipoTela pTipoTela) {
        maximoColunas = pTipoTela.getColunas().getQuantidade();
        tipoTela = pTipoTela;
    }

    public ItfLinhaTela getUltimaLinha() {
        if (linhas.isEmpty()) {
            return null;
        } else {
            return linhas.get(linhas.size() - 1);
        }

    }

    @Override
    public void adicionarColuna(ItfColunaTela pColuna) {
        ItfLinhaTela linha = getUltimaLinha();
        if (linha == null || linha.getColunasRestante() < pColuna.getComponenteVinculado().getPesoLargura()) {
            linha = new LinhaTela(this);
            linha.adicionarColuna(pColuna);
            linhas.add(linha);
        } else {

            linha.adicionarColuna(pColuna);
        }

    }

    @Override
    public int getMaximoColunas() {
        return maximoColunas;
    }

    @Override
    public List<ItfLinhaTela> getLinhas() {
        return linhas;
    }

    @Override
    public ItfTipoTela getTipoTela() {
        return tipoTela;
    }

}
