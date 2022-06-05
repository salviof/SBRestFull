/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.localizacao;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep.ItemUnidadeFederativa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public enum FabUnidadesFederativasSemPersistencia implements ItfFabrica {
    MG, AC, AL, AM, AP, BA, CE, DF, ES, GO, MA, MS, MT, PA, PB, PE, PI, PR, RJ, RN, RO, RR, RS, SC, SE, SP, TO;

    @Override
    public ItemUnidadeFederativa getRegistro() {
        ItemUnidadeFederativa uf = new ItemUnidadeFederativa();
        uf.setSigla(this.toString());
        uf.setId(this.ordinal() + 1);
        uf.setNome(this.name());
        switch (this) {
            case AC:

                break;
            case AL:
                break;
            case AM:
                break;
            case AP:
                break;
            case BA:
                break;
            case CE:
                break;
            case DF:
                break;
            case ES:
                break;
            case GO:
                break;
            case MA:
                break;
            case MG:
                uf.setId(1);
                uf.setNome("Minas Gerais");

                break;
            case MS:
                break;
            case MT:
                break;
            case PA:
                break;
            case PB:
                break;
            case PE:
                break;
            case PI:
                break;
            case PR:
                break;
            case RJ:
                break;
            case RN:
                break;
            case RO:
                break;
            case RR:
                break;
            case RS:
                break;
            case SC:
                break;
            case SE:
                break;
            case SP:
                break;
            case TO:
                break;
            default:
                throw new AssertionError(this.name());

        }

        return uf;
    }

    public static List<ItemUnidadeFederativa> getTodos() {
        List<ItemUnidadeFederativa> listaTodos = new ArrayList<>();
        for (ItfFabrica estFab : FabUnidadesFederativasSemPersistencia.values()) {
            listaTodos.add((ItemUnidadeFederativa) estFab.getRegistro());
        }
        return listaTodos;
    }

}
