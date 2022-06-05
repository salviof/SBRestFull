/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.registro.validacaoRegistro;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampoInvalido;

/**
 *
 * Um campo inválido é um Objeto utilizado para exibição de mensagens de erro de
 * validação;
 *
 * @author desenvolvedor
 */
public class CampoInvalido implements ItfCaminhoCampoInvalido {

    private String nomeCampo;
    private String problemaInvalidou;

    @Override
    public String getNomeCampo() {
        return nomeCampo;
    }

    @Override
    public void setNomeCampo(String nomeCampo) {
        this.nomeCampo = nomeCampo;
    }

    @Override
    public String getProblemaInvalidou() {
        return problemaInvalidou;
    }

    @Override
    public void setProblemaInvalidou(String problemaInvalidou) {
        this.problemaInvalidou = problemaInvalidou;
    }

    @Override
    public String getMensagemCampoInvalido() {
        return "O campo " + nomeCampo + " " + problemaInvalidou;
    }

}
