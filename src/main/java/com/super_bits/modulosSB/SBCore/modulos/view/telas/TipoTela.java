/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.telas;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabColunasTela;

/**
 *
 * @author SalvioF
 */
public class TipoTela extends ItemSimples implements ItfTipoTela {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private int xMinimo;
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private int xMaximo;
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private int yMaximo;
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private int yMinimo;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA)
    private FabColunasTela colunas;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int getxMinimo() {
        return xMinimo;
    }

    @Override
    public void setxMinimo(int xMinimo) {
        this.xMinimo = xMinimo;
    }

    @Override
    public int getxMaximo() {
        return xMaximo;
    }

    @Override
    public void setxMaximo(int xMaximo) {
        this.xMaximo = xMaximo;
    }

    @Override
    public int getyMaximo() {
        return yMaximo;
    }

    @Override
    public void setyMaximo(int yMaximo) {
        this.yMaximo = yMaximo;
    }

    @Override
    public int getyMinimo() {
        return yMinimo;
    }

    @Override
    public void setyMinimo(int yMinimo) {
        this.yMinimo = yMinimo;
    }

    @Override
    public FabColunasTela getColunas() {
        return colunas;
    }

    @Override
    public void setColunas(FabColunasTela colunas) {
        this.colunas = colunas;
    }

}
