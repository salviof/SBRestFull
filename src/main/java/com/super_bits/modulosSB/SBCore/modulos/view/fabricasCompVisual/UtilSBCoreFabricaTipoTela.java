/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaTipoTelas;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.UtilSBCoreReflexaoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.InfoTipoTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.TipoTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfTelaUsuario;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfTipoTela;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreFabricaTipoTela {

    public static ItfTipoTela getTipoTela(ItfFabricaTipoTelas pENumFabrica) {
        InfoTipoTela infoTipo = UtilSBCoreReflexaoFabrica.getAnotacaoDoEnum(pENumFabrica, InfoTipoTela.class);
        TipoTela novoTipo = new TipoTela();
        novoTipo.setNome(infoTipo.descricao());
        novoTipo.setxMaximo(infoTipo.Xmax());
        novoTipo.setxMinimo(infoTipo.Xmin());
        novoTipo.setyMaximo(infoTipo.Yminimo());
        novoTipo.setyMinimo(infoTipo.Yminimo());
        novoTipo.setColunas(infoTipo.quantidadeDeColunas());

        return (ItfTipoTela) novoTipo;
    }

}
