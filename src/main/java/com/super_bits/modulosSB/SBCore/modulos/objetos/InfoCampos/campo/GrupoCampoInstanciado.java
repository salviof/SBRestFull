/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sfurbino
 * @since 27/12/2019
 * @version 1.0
 */
@InfoObjetoSB(tags = "Grupo  de campo instanciado", plural = "Grupos campo instanciado")
public class GrupoCampoInstanciado extends ItemSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    private final List<ItfCampoInstanciado> campos = new ArrayList<>();
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nomeCampo;

    public GrupoCampoInstanciado(String nomeCampo) {
        setNomeCampo(nomeCampo);
    }

    public void adicionarCampo(ItfCampoInstanciado pCampo) {
        campos.add(pCampo);
    }

    public List<ItfCampoInstanciado> getCampos() {
        return campos;
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    public final void setNomeCampo(String nomeCampo) {
        if (nomeCampo == null) {
            return;
        }
        this.nomeCampo = nomeCampo;
        id = nomeCampo.hashCode();
    }

}
