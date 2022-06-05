/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import javax.swing.JOptionPane;

/**
 *
 * @author Salvio
 */
public class UTILSBCoreDesktopApp {

    static class EnviaMensagemThread implements Runnable {

        ItfMensagem mensagem;

        public EnviaMensagemThread(ItfMensagem pMensagem) {
            mensagem = pMensagem;
        }

        @Override
        public void run() {
            showMessageStopProcess(mensagem);
        }

    }

    /**
     *
     * Envia uma mensagem utilizando Jpanel em uma Thread Separada, para
     * permitir o fluxo continuo da execução do sistema.
     *
     * @param pMensagem
     */
    public static void showMessageNovaThread(ItfMensagem pMensagem) {
        new Thread(new EnviaMensagemThread(pMensagem)).start();
    }

    /**
     *
     * Envia a mensagem, e continua a execução do código depois do OK
     *
     * @param pMensagem
     */
    public static void showMessageStopProcess(ItfMensagem pMensagem) {

        String titulo = pMensagem.getTipoDeMensagem().toString() + " para " + pMensagem.getTipoDestinatario();

        int tipoDeMensagem = JOptionPane.INFORMATION_MESSAGE;
        switch (pMensagem.getTipoDeMensagem()) {
            case AVISO:
                tipoDeMensagem = JOptionPane.INFORMATION_MESSAGE;
                break;
            case ALERTA:
                tipoDeMensagem = JOptionPane.WARNING_MESSAGE;
                break;
            case ERRO:
                tipoDeMensagem = JOptionPane.ERROR_MESSAGE;
                break;
            case ERRO_FATAL:
                tipoDeMensagem = JOptionPane.ERROR_MESSAGE;
                break;
            default:
                throw new AssertionError(pMensagem.getTipoDeMensagem().name());

        }

        JOptionPane.showMessageDialog(null, pMensagem.getMenssagem(), titulo, tipoDeMensagem);

    }

}
