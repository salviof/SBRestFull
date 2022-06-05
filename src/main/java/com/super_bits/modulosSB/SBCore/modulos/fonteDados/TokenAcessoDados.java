/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.fonteDados;

import javax.persistence.EntityManager;

/**
 *
 * @author desenvolvedor
 */
public class TokenAcessoDados implements ItfTokenAcessoDados {

    private final TipoAcessosDados tipoAcesso;
    private final EntityManager emtityManager;

    public TokenAcessoDados(EntityManager pEm) {
        tipoAcesso = TipoAcessosDados.JPA;
        emtityManager = pEm;
    }

    @Override
    public TipoAcessosDados getTipo() {
        return tipoAcesso;
    }

    @Override
    public EntityManager getEntitiManager() {
        if (!tipoAcesso.equals(TipoAcessosDados.JPA)) {
            throw new UnsupportedOperationException("O acesso a dados deste contexto não é do tipo EntityManger, tipo->" + tipoAcesso);
        }
        return emtityManager;
    }

}
