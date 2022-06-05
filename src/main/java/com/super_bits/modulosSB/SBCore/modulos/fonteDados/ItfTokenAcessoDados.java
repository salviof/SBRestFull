/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.fonteDados;

import javax.persistence.EntityManager;

/**
 *
 * @author SalvioF
 */
public interface ItfTokenAcessoDados {

    public TipoAcessosDados getTipo();

    public EntityManager getEntitiManager();

}
