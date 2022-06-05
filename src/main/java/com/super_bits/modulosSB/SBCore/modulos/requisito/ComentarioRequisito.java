/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.requisito;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import java.io.Serializable;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(plural = "Comentários de Requisito", tags = {"Comentário Requisito"})
public class ComentarioRequisito implements Serializable {

    private String comentario;
    private String nomeUnicoAcao;
    private String nomeUsuario;

    public ComentarioRequisito() {
    }

    public ComentarioRequisito(String pComentario, String pNomeUnico) {
        comentario = pComentario;
        nomeUsuario = pNomeUnico;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getAcaoDoSistema() {
        return nomeUnicoAcao;
    }

    public void setAcaoDoSistema(String acaoDoSistema) {
        this.nomeUnicoAcao = acaoDoSistema;
    }

    public String getNomeUnicoAcao() {
        return nomeUnicoAcao;
    }

    public void setNomeUnicoAcao(String nomeUnicoAcao) {
        this.nomeUnicoAcao = nomeUnicoAcao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

}
