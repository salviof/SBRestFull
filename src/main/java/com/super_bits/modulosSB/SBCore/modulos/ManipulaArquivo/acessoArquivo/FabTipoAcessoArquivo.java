/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;

/**
 *
 * @author desenvolvedor
 */
public enum FabTipoAcessoArquivo implements ItfFabrica {

    VISUALIZAR, BAIXAR;
    private static String slugVisualizar;
    private static String slugBaixar;

    @Override
    public TipoAcessoArquivo getRegistro() {
        TipoAcessoArquivo tipoAcesso = new TipoAcessoArquivo();
        tipoAcesso.setEnumVinculado(this);
        switch (this) {
            case VISUALIZAR:
                tipoAcesso.setId(1);
                tipoAcesso.setNome("Visualizar Arquivo");

                break;
            case BAIXAR:
                tipoAcesso.setId(2);
                tipoAcesso.setNome("Baixar Arquivo");

                break;
            default:
                throw new AssertionError(this.name());

        }
        return tipoAcesso;

    }

    public String getSlugUrl() {

        switch (this) {
            case VISUALIZAR:
                if (slugVisualizar == null) {
                    slugVisualizar = getRegistro().getSlugURL();
                }
                return slugVisualizar;

            case BAIXAR:
                if (slugBaixar == null) {
                    slugBaixar = getRegistro().getSlugURL();
                }
                return slugBaixar;
            default:
                throw new AssertionError(this.name());

        }

    }

}
