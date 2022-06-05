/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.parametroAcaoController;

/**
 *
 * @author desenvolvedor
 */
public class ParametroAcaoControllerSimples implements ItfParametrosAcaoController {

    private String entidadePrincial;
    private int idEntidadePrincipal;

    public ParametroAcaoControllerSimples(String entidadePrincial, int idEntidadePrincipal) {
        this.entidadePrincial = entidadePrincial;
        this.idEntidadePrincipal = idEntidadePrincipal;
    }

    @Override
    public ParametroAcaoControllerSimples setEntidadePrincial(String entidadePrincial) {
        this.entidadePrincial = entidadePrincial;
        return this;
    }

    @Override
    public ParametroAcaoControllerSimples setIdEntidadePrincipal(int idEntidadePrincipal) {
        this.idEntidadePrincipal = idEntidadePrincipal;
        return this;
    }

    @Override
    public String getEntidadePrincial() {
        return entidadePrincial;
    }

    @Override
    public int getIdEntidadePrincipal() {
        return idEntidadePrincipal;
    }

}
