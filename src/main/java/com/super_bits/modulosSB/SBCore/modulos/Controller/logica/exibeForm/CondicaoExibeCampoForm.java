/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.logica.exibeForm;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import org.coletivojava.fw.api.testes.logica.FabLogicaSimples;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(plural = "Condições", tags = {"Condição exibição formulario"})
public class CondicaoExibeCampoForm extends ItemSimples {

    private final FabLogicaSimples tipoLogica;
    private final String campoReferencia;
    private final String campoExibicao;

    public CondicaoExibeCampoForm(FabLogicaSimples tipoLogica, String campoReferencia, String campoExibicao) {
        this.tipoLogica = tipoLogica;
        this.campoReferencia = campoReferencia;
        this.campoExibicao = campoExibicao;
    }

    public FabLogicaSimples getTipoLogica() {
        return tipoLogica;
    }

    public String getCampoReferencia() {
        return campoReferencia;
    }

    @Override
    public String getNome() {
        return "caso " + campoReferencia + "atenda " + tipoLogica.name() + "exibe" + campoExibicao;
    }

    public String getCampoExibicao() {
        return campoExibicao;
    }

}
