/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.telas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaTipoTelas;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.UtilSBCoreFabricaTipoTela;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabColunasTela;
import java.util.TreeMap;

/**
 *
 * @author desenvolvedor
 */
public enum FabTipoTamanhoTelas implements ItfFabricaTipoTelas {
//800
    @InfoTipoTela(Xmin = 0, Xmax = 500, quantidadeDeColunas = FabColunasTela.UM)
    MOBILE_GERACAO1,
    @InfoTipoTela(Xmin = 501, Xmax = 767, quantidadeDeColunas = FabColunasTela.DOIS)
    MOBILE_VERTICAL,
    @InfoTipoTela(Xmin = 768, Xmax = 800, Ymaximo = 0, Yminimo = 0, quantidadeDeColunas = FabColunasTela.TRES)
    MOBILE_HORIZONTAL_OU_TABLET_VERTICAL,
    @InfoTipoTela(Xmin = 801, Xmax = 1000, Ymaximo = 0, Yminimo = 0, quantidadeDeColunas = FabColunasTela.SEIS)
    TABLET_HORIZONTAL_DESKTOP_PEQUENO,
    @InfoTipoTela(Xmin = 1001, Xmax = 1199, Ymaximo = 0, Yminimo = 0, quantidadeDeColunas = FabColunasTela.NOVE)
    TABLET_GRANDE_HORIZONTE_DESKTOP_REDUZIDO,
    @InfoTipoTela(Xmin = 1200, Xmax = 1800, Ymaximo = 0, Yminimo = 0, quantidadeDeColunas = FabColunasTela.DOZE)
    DESKTOP_NORMAL,
    @InfoTipoTela(Xmin = 1801, Xmax = 5000, Ymaximo = 0, Yminimo = 0, quantidadeDeColunas = FabColunasTela.DOZE)
    DESKTOP_GRANDE;

    private static TreeMap<Integer, TipoTela> mapaTipoTela;

    @Override
    public ItfTipoTela getRegistro() {
        return UtilSBCoreFabricaTipoTela.getTipoTela(this);

    }

    public static ItfTipoTela getTipoTelaByX(Integer pTamanhoX) {
        try {
            if (mapaTipoTela == null) {
                buildTiposTela();
            }
            int indice = 0;
            for (Integer indiceTamanhoMaximo : mapaTipoTela.keySet()) {

                indice = indiceTamanhoMaximo;
                if (indiceTamanhoMaximo > pTamanhoX) {

                    break;
                }

            }

            return mapaTipoTela.get(indice);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo tipo tela pela largura do dispositivo", t);
            return null;
        }
    }

    private static void buildTiposTela() {
        mapaTipoTela = new TreeMap<>();

        for (FabTipoTamanhoTelas tamanho : FabTipoTamanhoTelas.values()) {
            TipoTela tipoTela = (TipoTela) tamanho.getRegistro();
            mapaTipoTela.put(tipoTela.getxMaximo(), tipoTela);
        }

    }

}
