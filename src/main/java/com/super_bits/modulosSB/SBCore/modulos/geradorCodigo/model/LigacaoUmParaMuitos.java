/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfLigacaoUmParaMuitos;

/**
 *
 * @author desenvolvedor
 */
public class LigacaoUmParaMuitos extends EstruturaDeEntidade implements ItfLigacaoUmParaMuitos {

    private String orphanRemove = "true";

    private String label, descricao, nomeDeclarado;

    @Override
    public String getNomeDeclarado() {
        return nomeDeclarado;
    }

    @Override
    public void setNomeDeclarado(String nomeDeclarado) {
        this.nomeDeclarado = nomeDeclarado;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getOrphanRemove() {
        return orphanRemove;
    }

    @Override
    public void setOrphanRemove(String orphanRemove) {
        this.orphanRemove = orphanRemove;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

}
