/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.listas;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.listas.ItfListas;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class ListaDinamicaGenerica implements ItfListas {

    private final ItfCampoInstanciado campoInst;

    public ListaDinamicaGenerica(ItfCampoInstanciado pCampo) {
        campoInst = pCampo;
    }

    @Override
    public List getLista(Object... pObjeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class getClasse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ItfCampoInstanciado getCampoInst() {
        return campoInst;
    }

}
