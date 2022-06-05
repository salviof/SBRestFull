/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

/**
 *
 * @author desenvolvedor
 */
public class ErroSBCoreFW extends Throwable {

    @SuppressWarnings("LeakingThisInConstructor")
    public ErroSBCoreFW(String pMensagem) {
        super(pMensagem);
        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, pMensagem, this);
    }

    @SuppressWarnings("LeakingThisInConstructor")
    public ErroSBCoreFW(FabErro pTipoErro, String pMensagem) {
        super(pMensagem);
        SBCore.RelatarErro(pTipoErro, pMensagem, this);
    }

    @SuppressWarnings("LeakingThisInConstructor")
    public ErroSBCoreFW(Throwable pCausa) {
        super(pCausa);
        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, this.getMessage(), this);
    }

    public ErroSBCoreFW(String pMensagem, Throwable pCausa) {
        super(pMensagem, pCausa);

    }

    @Override
    public final String getMessage() {
        return super.getMessage(); //To change body of generated methods, choose Tools | Templates.
    }

}
