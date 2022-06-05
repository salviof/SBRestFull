package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import java.io.Serializable;
import java.lang.reflect.Field;

public class CampoEsperado implements Serializable {

    private Boolean foiAnotado;
    private Boolean anotacaoObrigatoria = false;
    private FabTipoAtributoObjeto tipoCampo;
    private Object valorPadrao;
    private Field campoReflex;
    private int acessoPadrao;

    public CampoEsperado(FabTipoAtributoObjeto pTipoCampo, Object pvalorPadrao) {
        tipoCampo = pTipoCampo;
        valorPadrao = pvalorPadrao;
    }

    public CampoEsperado(FabTipoAtributoObjeto pTipoCampo) {
        tipoCampo = pTipoCampo;
        valorPadrao = "";
    }

    public Boolean getFoiAnotado() {
        return foiAnotado;
    }

    public void setFoiAnotado(Boolean foiAnotado) {
        this.foiAnotado = foiAnotado;
    }

    public Boolean getAnotacaoObrigatoria() {
        return anotacaoObrigatoria;
    }

    public void setAnotacaoObrigatoria(Boolean anotacaoObrigatoria) {
        this.anotacaoObrigatoria = anotacaoObrigatoria;
    }

    public FabTipoAtributoObjeto getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(FabTipoAtributoObjeto tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    public Object getValorPadrao() {
        return valorPadrao;
    }

    public void setValorPadrao(Object valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    public int getAcessoPadrao() {
        return acessoPadrao;
    }

    public void setAcessoPadrao(int acessoPadrao) {
        this.acessoPadrao = acessoPadrao;
    }

    public Field getCampoReflex() {
        return campoReflex;
    }

    public void setCampoReflex(Field campoReflex) {
        this.campoReflex = campoReflex;
    }

}
