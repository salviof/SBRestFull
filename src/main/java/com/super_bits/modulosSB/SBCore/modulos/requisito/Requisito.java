/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.requisito;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = {"Requisito do projeto"}, plural = "Requisitos")
public class Requisito {

    private String urlRequisito;
    private String nome;
    private String descricao;
    private List<ComentarioRequisito> comentarios;
    private String statusDescricao;

    public String getUrlRequisito() {
        return urlRequisito;
    }

    public void setUrlRequisito(String urlRequisito) {
        this.urlRequisito = urlRequisito;
        comentarios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<ComentarioRequisito> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioRequisito> comentarios) {
        this.comentarios = comentarios;
    }

    public String getStatusDescricao() {
        return statusDescricao;
    }

    public void setStatusDescricao(String statusDescricao) {
        this.statusDescricao = statusDescricao;
    }

}
