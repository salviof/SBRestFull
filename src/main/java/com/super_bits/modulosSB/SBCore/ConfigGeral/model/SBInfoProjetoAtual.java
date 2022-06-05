/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.model;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author salvioF
 */
@InfoObjetoSB(plural = "Informações do projeto atual", tags = {"Projeto Atual"})
public class SBInfoProjetoAtual extends ItemSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;

    @Override
    public int getId() {
        if (id == 0) {
            return SBCore.getNomeProjeto().hashCode();
        }
        return id;
    }

    @Override
    public String getNome() {
        if (nome == null) {
            nome = SBCore.getNomeProjeto();
        }
        return nome;
    }

}
