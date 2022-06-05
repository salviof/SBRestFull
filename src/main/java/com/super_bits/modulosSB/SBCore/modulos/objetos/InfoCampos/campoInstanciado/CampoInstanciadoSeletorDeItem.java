/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorUnicoObjeto.ItfSelecaoObjetoDeUmaLista;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class CampoInstanciadoSeletorDeItem extends CampoInstanciadoComSelecao implements ItfCampoInstSeletorItem {

    private final ItfSelecaoObjetoDeUmaLista objetoAPartirDeLista;

    public CampoInstanciadoSeletorDeItem(CampoInstanciadoGenerico pCampoInstanciado) {
        super(UtilSBCoreReflexaoCaminhoCampo.getSelecaoItem(pCampoInstanciado), pCampoInstanciado);
        objetoAPartirDeLista = (ItfSelecaoObjetoDeUmaLista) getSeletor();
    }

    @Override
    public ItfSelecaoObjetoDeUmaLista getObjetoDeUmaLista() {
        return objetoAPartirDeLista;
    }

    @Override
    public List filtrarPorAutoComplet(String pParametro) {
        return super.filtrarPorAutoComplet(pParametro); //chamada super do metodo (implementação classe pai)
    }

}
