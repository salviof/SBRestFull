/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.view;

import com.super_bits.modulosSB.SBCore.modulos.view.componenteAtributo.ComponenteVisualSBBean;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteBuilder;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfFabTipoComponenteVisual;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualBase;
import org.coletivojava.fw.utilCoreBase.UtilSBFabricaComponenteVisual;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreComponenteSBBeanBuilder implements ItfComponenteBuilder {

    @Override
    public ItfComponenteVisualSB gerarComponenteVisual(ItfFabTipoComponenteVisual pFabrica) {
        ComponenteVisualBase componenteBase = UtilSBFabricaComponenteVisual.getComponenteVisual(pFabrica);
        ComponenteVisualSBBean componente = new ComponenteVisualSBBean(componenteBase);
        return componente;
    }

}
