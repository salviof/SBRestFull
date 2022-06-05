/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.admin;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import java.io.Serializable;

/**
 *
 * @author desenvolvedor
 */
public class CentralAdministrativaPadrao implements ItfCentralAdministrativa, Serializable {

    private final ItfFabricaAcoes paginaInicialAnonimo;
    private final ItfFabricaAcoes paginaInicialAdmin;

    public CentralAdministrativaPadrao(ItfFabricaAcoes pPaginaInicialAnon, ItfFabricaAcoes pPaginaAdministrativa) {
        paginaInicialAnonimo = pPaginaInicialAnon;
        paginaInicialAdmin = pPaginaAdministrativa;
        // ATENCAO-> Nao instanciar ações aqui, o mapa de ações pode estar vazio, nesta etapa..
        if (!paginaInicialAdmin.toString().contains("_MB_")) {
            throw new UnsupportedOperationException("A pagina adminstrativa precisa ser do tipo gestão.");
        }
    }

    @Override
    public ItfFabricaAcoes getFormularioPainelAdministrativoDev() {
        return paginaInicialAdmin;
    }

    @Override
    public ItfFabricaAcoes getFormularioHomePadrao() {
        return paginaInicialAnonimo;
    }

}
