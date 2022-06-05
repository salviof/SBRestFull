/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfLigacaoMuitosParaMuitos;

/**
 *
 * @author desenvolvedor
 */
public class LigacaoMuitosParaMuitos extends EstruturaDeEntidade implements ItfLigacaoMuitosParaMuitos {

    private String joinTableName, joinColumName, inverseJoinColumName, nomeDeclarado;

    @Override
    public String getJoinTableName() {
        return joinTableName;
    }

    @Override
    public void setJoinTableName(String joinTableName) {
        this.joinTableName = joinTableName;
    }

    @Override
    public String getJoinColumName() {
        return joinColumName;
    }

    @Override
    public void setJoinColumName(String joinColumName) {
        this.joinColumName = joinColumName;
    }

    @Override
    public String getInverseJoinColumName() {
        return inverseJoinColumName;
    }

    @Override
    public void setInverseJoinColumName(String inverseJoinColumName) {
        this.inverseJoinColumName = inverseJoinColumName;
    }

    @Override
    public String getNomeDeclarado() {
        return nomeDeclarado;
    }

    @Override
    public void setNomeDeclarado(String nomeDeclarado) {
        this.nomeDeclarado = nomeDeclarado;
    }

}
