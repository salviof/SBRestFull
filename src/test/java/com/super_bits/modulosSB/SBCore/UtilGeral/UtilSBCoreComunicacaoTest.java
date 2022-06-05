/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import br.org.coletivojava.erp.comunicacao.transporte.ERPTransporteComunicacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacao;
import com.super_bits.modulosSB.SBCore.testes.TestesCore;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreComunicacao;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreComunicacaoTest extends TestesCore {

    public UtilSBCoreComunicacaoTest() {
    }

    public void setUp() {
    }

    @Test
    public void testGetSaudacao() {
        System.out.println(UtilSBCoreComunicacao.getSaudacao());
        ItfComunicacao cm = SBCore.getCentralDeComunicacao().gerarComunicacaoSistema_UsuairoLogado(FabTipoComunicacao.CONFIRMAR_CANCELAR, "Teste", "asfasdf", ERPTransporteComunicacao.AUTOMATICO);
        cm.getRepostasPossiveis().forEach(rp -> System.out.println(rp.getTipoResposta().getNome()));
    }

}
