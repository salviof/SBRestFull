/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class ErroSBCoreCriticoParaTudo extends ErroSBCoreFW {

    public ErroSBCoreCriticoParaTudo(String pMensagem) {
        super(FabErro.PARA_TUDO, pMensagem);

    }

}
