/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.localizacao;

import org.coletivojava.fw.utilCoreBase.UtilSBCoreFabrica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep.ItemBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep.ItemCidade;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public enum FabCidadesSemPersistencia implements ItfFabrica {

    BELO_HORIZONTE, CONTAGEM, Baldim,
    Betim, Brumadinho,
    Caeté,
    Capim_Branco,
    Confins,
    Esmeraldas,
    Florestal,
    Ibirité,
    Igarapé,
    Itaguara,
    Itatiaiuçu,
    Jaboticatubas,
    Juatuba,
    Lagoa_Santa,
    MArio_Campos,
    Mateus_Leme,
    Matozinhos,
    Nova_Lima,
    Nova_União,
    Pedro_Leopoldo,
    Raposos,
    Ribeirao_das_Neves,
    Rio_Acima,
    Rio_Manso,
    Sabara,
    Santa_Luzia,
    Sao_Joaquim_de_Bicas,
    São_Jose_da_Lapa,
    Sarzedo,
    Taquaracu_de_Minas,
    Vespasiano;

    @Override
    public ItemCidade getRegistro() {
        ItemCidade novaCidade = new ItemCidade();
        novaCidade.setUnidadeFederativa(FabUnidadesFederativasSemPersistencia.MG.getRegistro());
        novaCidade.setNome(this.toString());
        novaCidade.setId(1);

        switch (this) {
            case BELO_HORIZONTE:
                novaCidade.setNome("Belo Horizonte");
                ItemBairro bairro1 = new ItemBairro();
                bairro1.setCidade(novaCidade);
                bairro1.setNome("Nova Suissa");
                bairro1.setId(1);
                ItemBairro bairro2 = new ItemBairro();
                bairro2.setId(2);
                bairro2.setCidade(novaCidade);
                bairro2.setNome("Centro");
                ItemBairro bairro3 = new ItemBairro();
                bairro1.setId(3);
                bairro3.setCidade(novaCidade);
                bairro3.setNome("Teste 1");

                novaCidade.getBairros().add(bairro1);
                novaCidade.getBairros().add(bairro2);
                novaCidade.getBairros().add(bairro3);
                break;
            case CONTAGEM:
                break;
            case Baldim:
                break;
            case Betim:
                break;
            case Brumadinho:
                break;
            case Caeté:
                break;
            case Capim_Branco:
                break;
            case Confins:
                break;
            case Esmeraldas:
                break;
            case Florestal:
                break;
            case Ibirité:
                break;
            case Igarapé:
                break;
            case Itaguara:
                break;
            case Itatiaiuçu:
                break;
            case Jaboticatubas:
                break;
            case Juatuba:
                break;
            case Lagoa_Santa:
                break;
            case MArio_Campos:
                break;
            case Mateus_Leme:
                break;
            case Matozinhos:
                break;
            case Nova_Lima:
                break;
            case Nova_União:
                break;
            case Pedro_Leopoldo:
                break;
            case Raposos:
                break;
            case Ribeirao_das_Neves:
                break;
            case Rio_Acima:
                break;
            case Rio_Manso:
                break;
            case Sabara:
                break;
            case Santa_Luzia:
                break;
            case Sao_Joaquim_de_Bicas:
                break;
            case São_Jose_da_Lapa:
                break;
            case Sarzedo:
                break;
            case Taquaracu_de_Minas:
                break;
            case Vespasiano:
                break;
            default:
                throw new AssertionError(this.name());

        }

        novaCidade.configIDPeloNome();
        return novaCidade;
    }

    public static List<ItemCidade> getCidadesPorEstado(FabUnidadesFederativasSemPersistencia pUnidade) {
        List<ItemCidade> cidadesListadas = new ArrayList<>();
        List<ItemCidade> todasCidades = (List) UtilSBCoreFabrica.listaRegistros(FabCidadesSemPersistencia.class);
        for (Iterator<ItemCidade> iterator = todasCidades.iterator(); iterator.hasNext();) {
            ItemCidade next = iterator.next();
            if (next.getUnidadeFederativa().getSigla().toLowerCase().contains(pUnidade.getRegistro().getSigla().toLowerCase())) {
                cidadesListadas.add(next);
            }
        }
        return cidadesListadas;

    }

}
