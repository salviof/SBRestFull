/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciadoDInamico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoInstanciadoGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 *
 * @author desenvolvedor
 */
public abstract class CampoInstanciadoDinamico extends CampoInstanciadoGenerico {

    private final ItfAtributoObjetoSB campoDinamico;

    public CampoInstanciadoDinamico(Field pCampoValor,
            ItfAtributoObjetoEditavel pCampoDinamico
    ) {

        super(pCampoValor, pCampoDinamico);

        campoDinamico = pCampoDinamico;
    }

    @Override
    public List<ItfBeanSimples> getListaDeOpcoes() {
        return campoDinamico.getListaDeOpcoes();
    }

    public abstract ItfAtributoObjetoSB getAtributosCampoDinamico();

    @Override
    public ItfComponenteVisualSB getComponenteVisualPadrao() {
        return super.getComponenteVisualPadrao(); //chamada super do metodo (implementação classe pai)
    }

}
