/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfListaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author desenvolvedor
 */
public class ListaDeEntidade extends ItemSimples implements ItfListaDeEntidade {

    public ListaDeEntidade(String nomeEnum, String nomeDeclaracao, String nomeEntidade, String nomeObjetoListado, String javaDoc, EstruturaDeEntidade pEstrutura) {
        this.nomeEnum = nomeEnum;
        id = nomeEnum.hashCode();
        this.nomeDeclaracao = nomeDeclaracao;
        this.nomeEntidade = nomeEntidade;
        this.nomeObjetoListado = nomeObjetoListado;
        this.javaDoc = javaDoc;
        estrutura = pEstrutura;
    }
    private int id;
    private String javaDoc;
    private String nomeEntidade;
    private String nomeEnum;
    private String nomeDeclaracao;
    private String nomeObjetoListado;
    private ItfEstruturaDeEntidade estrutura;

    @Override
    public String getNomeObjetoListado() {
        return nomeObjetoListado;
    }

    @Override
    public void setNomeObjetoListado(String nomeObjetoListado) {
        this.nomeObjetoListado = nomeObjetoListado;
    }

    @Override
    public String getNomeEntidade() {
        return nomeEntidade;
    }

    @Override
    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
    }

    @Override
    public String getNomeEnum() {
        return nomeEnum;
    }

    @Override
    public void setNomeEnum(String nomeEnum) {
        this.nomeEnum = nomeEnum;
    }

    @Override
    public String getNomeDeclaracao() {
        return nomeDeclaracao;
    }

    @Override
    public void setNomeDeclaracao(String nomeDeclaracao) {
        this.nomeDeclaracao = nomeDeclaracao;
    }

    @Override
    public String getJavaDoc() {
        return javaDoc;
    }

    @Override
    public void setJavaDoc(String javaDoc) {
        this.javaDoc = javaDoc;
    }

    @Override
    public ItfEstruturaDeEntidade getEstrutura() {
        return estrutura;
    }

    @Override
    public void setEstrutura(ItfEstruturaDeEntidade estrutura) {
        this.estrutura = estrutura;
    }

    @Override
    public int getId() {
        return id;
    }

}
