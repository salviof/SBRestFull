/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.logeventos;

import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import java.util.Map;

/**
 * ############################################################# <br/>
 * Esta Interface assina um dos serviços do núcleo SBCore
 * <br/>
 * Veja todos os serviços disponíveis digitando SBCore.getServico...
 * ############################################################# <br/>
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 17/03/2015
 * @version 1.0
 */
public interface ItfCentralEventos {

    public void registrarLogDeEvento(FabMensagens pTipoEvento, String mensagem);

    public void registrarLogDeEvento(FabMensagens pTipoEvento, String mensagem, int pCodErro);

    public void registrarLogDeEvento(FabMensagens pTipoEvento, String mensagem, int pCodErro, Map<String, String> propriedades);

}
