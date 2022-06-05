/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.logeventos;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreNumeros;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivoTexto;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import java.util.Date;
import java.util.Map;

/**
 *
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÓ SÃO OBRIGATÓRIOS QUANDO NÃO EXISTIR UMA INTERFACE DOCUMENTADA, DESCREVA DE
 * FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE UMA EQUIPE.
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 17/03/2015
 * @version 1.0
 */
public class CentralLogEventosArqTextoGenerica implements ItfCentralEventos {

    public CentralLogEventosArqTextoGenerica() {

    }

    private String diretorioArquivoLog() {
        String diretorio = SBCore.getCentralDeArquivos().getEndrLocalResourcesObjeto() + "/logs";
        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            diretorio = SBCore.getCaminhoDesenvolvimento();
        }
        return diretorio;
    }

    @Override
    public void registrarLogDeEvento(FabMensagens pTipoEvento, String mensagem) {
        UtilSBCoreArquivoTexto.escreverEmArquivo(diretorioArquivoLog() + "/logAplicacao" + pTipoEvento + ".txt", "0000|" + new Date().toString() + "..|" + mensagem);
        System.out.println("log " + pTipoEvento + "gerado em " + diretorioArquivoLog());
    }

    @Override
    public void registrarLogDeEvento(FabMensagens pTipoEvento, String mensagem, int pCodErro) {
        String codigoErro = "não informado";
        try {
            codigoErro = UtilSBCoreNumeros.getLpadZero(pCodErro, 4).toString();
        } catch (Throwable t) {

        }
        String diretorio = diretorioArquivoLog();
        UtilSBCoreArquivoTexto.escreverEmArquivo(diretorio
                + "/logAplicacao" + pTipoEvento + ".txt", codigoErro + "|" + new Date().toString() + "..|" + mensagem
        );
    }

    @Override
    public void registrarLogDeEvento(FabMensagens pTipoEvento, String mensagem, int pCodErro, Map<String, String> propriedades) {
        UtilSBCoreArquivoTexto.escreverEmArquivo(diretorioArquivoLog() + "/logAplicacao" + pTipoEvento + ".txt", "0000|" + new Date().toString() + "..|" + mensagem);
    }

}
