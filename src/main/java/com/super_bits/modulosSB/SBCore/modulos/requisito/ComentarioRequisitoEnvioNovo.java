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
@InfoObjetoSB(plural = "Novos Comentários", tags = {"Novo Comentário"})
public class ComentarioRequisitoEnvioNovo extends ComentarioRequisito implements Serializable {

    private String chaveDeAcesso;
    private String urlJira;
    private String usuario;
    private String senha;

    public String getChaveDeAcesso() {
        return chaveDeAcesso;
    }

    public void setChaveDeAcesso(String chaveDeAcesso) {
        this.chaveDeAcesso = chaveDeAcesso;
    }

    public String getUrlJira() {
        return urlJira;
    }

    public void setUrlJira(String urlJira) {
        this.urlJira = urlJira;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
