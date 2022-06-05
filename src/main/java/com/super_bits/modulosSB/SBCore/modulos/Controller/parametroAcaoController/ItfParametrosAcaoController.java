/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.parametroAcaoController;

/**
 *
 * @author desenvolvedor
 */
public interface ItfParametrosAcaoController {

    public String getEntidadePrincial();

    public int getIdEntidadePrincipal();

    public ParametroAcaoControllerSimples setEntidadePrincial(String entidadePrincial);

    public ParametroAcaoControllerSimples setIdEntidadePrincipal(int idEntidadePrincipal);

}
