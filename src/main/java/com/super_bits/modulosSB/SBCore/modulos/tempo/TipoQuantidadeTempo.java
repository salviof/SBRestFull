/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.tempo;

/**
 *
 * @author salvioF
 */
public class TipoQuantidadeTempo {

    private FabTipoQuantidadeTempo tipoInformacao;
    private String nomeSingular;
    private String nomePlural;

    public FabTipoQuantidadeTempo getTipoInformacao() {
        return tipoInformacao;
    }

    public void setTipoInformacao(FabTipoQuantidadeTempo tipoInformacao) {
        this.tipoInformacao = tipoInformacao;
    }

    public String getNomeSingular() {
        return nomeSingular;
    }

    public void setNomeSingular(String nomeSingular) {
        this.nomeSingular = nomeSingular;
    }

    public String getNomePlural() {
        return nomePlural;
    }

    public void setNomePlural(String nomePlural) {
        this.nomePlural = nomePlural;
    }

}
