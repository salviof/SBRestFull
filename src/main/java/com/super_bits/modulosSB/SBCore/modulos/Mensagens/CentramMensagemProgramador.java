/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Mensagens;

import com.super_bits.modulosSB.SBCore.UtilGeral.UTILSBCoreDesktopApp;

/**
 *
 * @author Salvio
 */
public class CentramMensagemProgramador extends CentralDeMensagemAbstrata {

    @Override
    public void enviaMensagem(ItfMensagem pMensagem) {

        UTILSBCoreDesktopApp.showMessageNovaThread(pMensagem);
    }

}
