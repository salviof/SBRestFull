/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.testes.TestesCore;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class FabTipoAtributoObjetoTest extends TestesCore {

    @Test
    public void testValues() {
        System.out.println("values");

        for (FabTipoAtributoObjeto tipo : FabTipoAtributoObjeto.values()) {
            System.out.println(new TipoAtributoMetodosBase(tipo).getRegistro().getNome());
        }

        // TODO review the generated test code and remove the default call to fail.
    }
}
