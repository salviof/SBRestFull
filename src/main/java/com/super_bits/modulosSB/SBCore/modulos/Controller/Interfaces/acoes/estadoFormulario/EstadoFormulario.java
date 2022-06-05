/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.estadoFormulario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(plural = "Estados Formularios", tags = {"Estado", "Estado Formulario"})
public class EstadoFormulario extends ItemSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID, label = "Código", descricao = "Numero de identificação")
    private int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME, label = "Nome", descricao = "Nome do Estado do Formulario")
    private String nome;

    @Override
    public int getId() {

        return this.id;

    }

    @Override
    public void setId(int pID) {

        this.id = pID;

    }

    @Override
    public String getNome() {

        return this.nome;

    }

    @Override
    public void setNome(String pNome) {

        this.nome = pNome;

    }

}
