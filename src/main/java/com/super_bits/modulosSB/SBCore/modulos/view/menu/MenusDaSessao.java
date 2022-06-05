/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.menu;

import org.coletivojava.fw.api.objetoNativo.view.menu.MenuSBFW;

/**
 *
 * @author desenvolvedor
 */
public class MenusDaSessao implements ItfMenusDeSessao {

    private ItfMenuSB menuPrincipal;

    private ItfMenuSB menuAvancado;

    /**
     *
     * @param menuPrincipal Menu com atalhos principais
     * @param menuSecundario Menu avançado
     */
    public MenusDaSessao(ItfMenuSB menuPrincipal, ItfMenuSB menuSecundario) {

        this.menuPrincipal = menuPrincipal;
        this.menuAvancado = menuSecundario;

    }

    public MenusDaSessao(ItfFabricaMenu pFabrica) {
        this.menuPrincipal = (MenuSBFW) pFabrica.getMenuPrincipal();
        MenuSBFW avancado = (MenuSBFW) pFabrica.getMenuSecundario();
        if (avancado != null && avancado.isTemMenu()) {
            this.menuAvancado = (MenuSBFW) pFabrica.getMenuSecundario();
        }
    }

    public MenusDaSessao(MenuSBFW menuPrincipal) {

        this.menuPrincipal = menuPrincipal;

    }

    @Override
    public boolean isTemMenuAvancado() {
        return menuAvancado != null;
    }

    @Override
    public MenuSBFW getMenuPrincipal() {
        return (MenuSBFW) menuPrincipal;
    }

    public void setMenuPrincipal(MenuSBFW menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }

    @Override
    public MenuSBFW getMenuAvancado() {

        return (MenuSBFW) menuAvancado;
    }

    public void setMenuAvancado(MenuSBFW menuAvancado) {
        this.menuAvancado = menuAvancado;
    }

}
