/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.objetoNativo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author salviofurbino
 * @since 23/04/2019
 * @version 1.0
 */
public class ObjetoNativoComoItemSimples extends ItemSimples {

    public ObjetoNativoComoItemSimples(ItfBeanSimples pInstancia) {
        super(pInstancia);

    }

    @Override
    protected Object getInstancia() {
        return super.getInstancia(); //chamada super do metodo (implementação classe pai)
    }

    @Override
    public String getNome() {
        return super.getNome(); //chamada super do metodo (implementação classe pai)
    }

}
