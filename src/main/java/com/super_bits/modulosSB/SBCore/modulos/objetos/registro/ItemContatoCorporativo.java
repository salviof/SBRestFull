package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanContatoCorporativo;

public class ItemContatoCorporativo extends ItemEndereco implements
        ItfBeanContatoCorporativo {

    public ItemContatoCorporativo() {
        super();

    }

    @Override
    public String getSite() {
        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.SITE);
    }

    @Override
    public String telefone() {
        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.TELEFONE_FIXO_NACIONAL);
    }

    @Override
    public String responsavel() {
        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.RESPONSAVEL);
    }

}
