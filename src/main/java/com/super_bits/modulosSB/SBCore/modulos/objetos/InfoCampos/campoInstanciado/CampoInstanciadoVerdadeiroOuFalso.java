/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.io.Serializable;

/**
 *
 * @author SalvioF
 */
public class CampoInstanciadoVerdadeiroOuFalso implements Serializable, ItfCampoInstanciadoVerdadeiroOuFalso {

    private final ItfCampoInstanciado campoInstanciado;

    public CampoInstanciadoVerdadeiroOuFalso(ItfCampoInstanciado campoInstanciado) {
        this.campoInstanciado = campoInstanciado;

    }

    @Override
    public String getTextoPositivo() {
        return campoInstanciado.getTextoPositivo();
    }

    @Override
    public String getTextoNegativo() {
        return campoInstanciado.getTextoNegativo();
    }

    @Override
    public String getIconePositivo() {
        return campoInstanciado.getIconePositivo();
    }

    @Override
    public String getIconeNegativo() {

        return campoInstanciado.getIconeNegativo();
    }

    @Override
    public String getIconeVigente() {
        if (campoInstanciado.getValorComoBoolean()) {
            return getIconePositivo();
        } else {
            return getIconeNegativo();
        }
    }

}
