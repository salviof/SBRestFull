/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.Threads;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Salvio
 */
public class MonitorDeTarefaBasico extends Thread {

    private boolean terimou;

    public MonitorDeTarefaBasico() {
        terimou = false;
    }

    public synchronized void setFInalizado() {
        terimou = true;
        notifyAll();
    }

    @Override
    public synchronized void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        while (!terimou) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorDeTarefaBasico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public synchronized void aguardarTerminoTarefa() {

        while (!terimou) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorDeTarefaBasico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
