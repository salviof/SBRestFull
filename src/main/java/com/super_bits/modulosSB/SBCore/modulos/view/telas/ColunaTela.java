/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.telas;

import com.super_bits.modulosSB.SBCore.modulos.view.telas.layout.ItfColunaTela;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreLayoutComponenteEmTelaBasico;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualEmLayout;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSBEmLayout;

/**
 *
 * @author desenvolvedor
 */
public class ColunaTela implements ItfColunaTela {

    private int id;
    private ComponenteVisualEmLayout componenteVinculado;
    private double unidadePesoLayoutAtual;
    private int pesoLarguraIdeal;
    private int pesoLarguraCalculado;
    private double porcentagemIdeal;
    private double porcentagemCalculada;
    private String nome;
    private boolean umComponenteAtributoObjeto;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public ComponenteVisualEmLayout getComponenteVinculado() {
        return componenteVinculado;
    }

    @Override
    public void setComponenteVinculado(ItfComponenteVisualSBEmLayout componenteVinculado) {
        this.componenteVinculado = (ComponenteVisualEmLayout) componenteVinculado;
        switch (componenteVinculado.getFamilia()) {
            case INPUT:
            case ENDERECO:
            case SELETOR_ITEM:
            case SELETOR_ITENS:
            case ENUM_SELETOR:
            case SUB_ITENS:
            case ITEM_BEAN_SIMPLES:
            case ITENS_BEAN_SIMPLES:
                umComponenteAtributoObjeto = true;
                break;
            default:
                umComponenteAtributoObjeto = false;

        }
    }

    @Override
    public int getPesoLarguraIdeal() {
        return pesoLarguraIdeal;
    }

    @Override
    public void setPesoLarguraIdeal(int pesoLarguraIdeal) {
        this.pesoLarguraIdeal = pesoLarguraIdeal;
        pesoLarguraCalculado = pesoLarguraIdeal;
        calcularPorcentagemCalculada();
    }

    @Override
    public int getPesoLarguraCalculado() {
        return pesoLarguraCalculado;
    }

    @Override
    public void setPesoLarguraCalculado(int pesoLarguraCalculado) {
        this.pesoLarguraCalculado = pesoLarguraCalculado;

    }

    @Override
    public double getPorcentagemIdeal() {
        return porcentagemIdeal;
    }

    @Override
    public void setPorcentagemIdeal(double porcentagemIdeal) {
        this.porcentagemIdeal = porcentagemIdeal;
        calcularPorcentagemCalculada();
    }

    @Override
    public double getPorcentagemCalculada() {
        return porcentagemCalculada;
    }

    @Override
    public void setPorcentagemCalculada(int porcentagemCalculada) {
        this.porcentagemCalculada = porcentagemCalculada;
    }

    private void calcularPorcentagemCalculada() {
        if (unidadePesoLayoutAtual > 0) {
            porcentagemCalculada = pesoLarguraIdeal * unidadePesoLayoutAtual;
        } else {
            porcentagemCalculada = pesoLarguraIdeal * UtilSBCoreLayoutComponenteEmTelaBasico.VALOR_UNIDADE_COLUNA_PORCENTAGEM;
        }
    }

    @Override
    public void incrementarTamanhoColuna() {
        pesoLarguraCalculado++;
        calcularPorcentagemCalculada();
        //porcentagemCalculada = porcentagemCalculada + (UtilSBCoreLayoutComponenteEmTelaBasico.VALOR_UNIDADE_COLUNA_PORCENTAGEM);

    }

    @Override
    public double getUnidadePesoLayoutAtual() {
        return unidadePesoLayoutAtual;
    }

    @Override
    public void setUnidadePesoLayoutAtual(double unidadePesoLayoutAtual) {
        this.unidadePesoLayoutAtual = unidadePesoLayoutAtual;
    }

    @Override
    public boolean isUmComponenteAtributoObjeto() {
        return umComponenteAtributoObjeto;
    }

}
