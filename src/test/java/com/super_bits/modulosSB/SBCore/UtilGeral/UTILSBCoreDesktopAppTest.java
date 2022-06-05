/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabTipoAgenteDoSistema;
import org.coletivojava.fw.api.objetoNativo.mensagem.Mensagem;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UTILSBCoreDesktopAppTest {

    public UTILSBCoreDesktopAppTest() {
    }

    @Test
    public void testShowMessage() {
        UTILSBCoreDesktopApp.showMessageNovaThread(new Mensagem(FabTipoAgenteDoSistema.USUARIO, FabMensagens.ALERTA, "teste"));
    }

    @Test
    public void testShowMessageStopProcess() {
        UTILSBCoreDesktopApp.showMessageStopProcess(new Mensagem(FabTipoAgenteDoSistema.USUARIO, FabMensagens.ALERTA, "teste"));
        UTILSBCoreDesktopApp.showMessageStopProcess(new Mensagem(FabTipoAgenteDoSistema.USUARIO, FabMensagens.AVISO, "teste"));
        UTILSBCoreDesktopApp.showMessageStopProcess(new Mensagem(FabTipoAgenteDoSistema.USUARIO, FabMensagens.ERRO, "teste"));
        UTILSBCoreDesktopApp.showMessageStopProcess(new Mensagem(FabTipoAgenteDoSistema.DESENVOLVEDOR, FabMensagens.ALERTA, "teste"));
        UTILSBCoreDesktopApp.showMessageStopProcess(new Mensagem(FabTipoAgenteDoSistema.DESENVOLVEDOR, FabMensagens.AVISO, "teste"));
        UTILSBCoreDesktopApp.showMessageStopProcess(new Mensagem(FabTipoAgenteDoSistema.DESENVOLVEDOR, FabMensagens.ERRO, "teste"));
    }

}
