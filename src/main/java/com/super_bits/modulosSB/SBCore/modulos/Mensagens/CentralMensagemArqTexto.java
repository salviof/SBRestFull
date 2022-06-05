/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.Mensagens;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivoTexto;
import java.util.Date;

/**
 *
 * Classe para gerar mensagens em Arquivo Texto
 *
 * os Arquivos são sblogSystem.txt e sbLogUser.txt na raiz do sistema
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 25/05/2014
 *
 */
public class CentralMensagemArqTexto extends CentralDeMensagemAbstrata {

    public static int maximoErroLog = 5000;
    public static final String ARQLOGSYSTEM = "/home/superBits/sbLogSystem.txt";
    public static final String ARQLOGUSUARIO = "/home/superBits/sbLogUser.txt";
    private FabMensagens tipoMensagem;

    private String makeCabecalho(FabTipoAgenteDoSistema destinatario) {
        String linha = " -[";
        if (destinatario == FabTipoAgenteDoSistema.SISTEMA) {
            linha = linha + UtilSBCoreDataHora.datahoraSistemaFr.format(new Date());
        }
        if (destinatario == FabTipoAgenteDoSistema.USUARIO) {
            linha = linha + UtilSBCoreDataHora.getAgoraString(UtilSBCoreDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO);

        }

        return linha + "] " + tipoMensagem;
    }

    public void enviaMensagem(ItfMensagem pMensagem) {

        // TODO UM arquivo para cada destinatario
        UtilSBCoreArquivoTexto.escreverEmArquivo(ARQLOGUSUARIO + "_" + tipoMensagem, makeCabecalho(pMensagem.getTipoDestinatario()) + pMensagem.getMenssagem());
    }

}
