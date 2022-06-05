/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.modulos.tratamentoErros.ItfInfoErroSB;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringListas;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import org.coletivojava.fw.api.objetoNativo.mensagem.MensagemProgramador;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 *
 * @author desenvolvedor
 */
public class InfoErroSBBasico implements ItfInfoErroSB {

    protected ItfMensagem mensagemDoDesenvolvedor;
    protected FabErro tipoErro;
    private Throwable erroJavaGerado;
    private boolean configurado;

    public InfoErroSBBasico(String mensagem, FabErro pTipoErrp, Throwable pErroGerado) {
        configurar(new MensagemProgramador(mensagem), pTipoErrp, pErroGerado);
    }

    public InfoErroSBBasico() {
    }

    protected void checarConfiguracao() {
        if (!configurado) {
            System.out.println("O que você está fazendo ? Vai com calma amiguinho "
                    + "os erros devem ser Lançados utilizando a FabricaDeErros, "
                    + "ou SBCore, não é possícel acessr uma informação do erro sem configura-lo ;) ");
            pararSistem();
        }
    }

    @Override
    public String getMsgDesenvolvedorLancou() {
        checarConfiguracao();
        return mensagemDoDesenvolvedor.getMenssagem();
    }

    @Override
    public List<String> getCaminhoStackTraceCompleto() {
        checarConfiguracao();
        return getCaminhoStackTraceContendoPalavrasNoPacote();
    }

    @Override
    public List<String> getCaminhoStackTraceResumido() {
        checarConfiguracao();
        return getCaminhoStackTraceContendoPalavrasNoPacote("super_bits", "coletivo", SBCore.getNomeProjeto());

    }

    @Override
    public String getCaminhoStackTraceCompletoStr() {
        checarConfiguracao();
        return UtilSBCoreStringListas.getStringDaListaComBarraN(getCaminhoStackTraceCompleto());
    }

    @Override
    public String getCaminhoStackTraceResumidoStr() {
        checarConfiguracao();
        return UtilSBCoreStringListas.getStringDaListaComBarraN(getCaminhoStackTraceResumido());
    }

    @Override
    public List<String> getCaminhoStackTraceContendoPalavrasNoPacote(String... pPalavras) {
        checarConfiguracao();
        List<String> stackTraceResumido = new ArrayList<>();
        boolean enviarTudo = false;
        if (pPalavras == null) {
            enviarTudo = true;
        }

        if (erroJavaGerado.getStackTrace() != null) {

            for (StackTraceElement etapa : erroJavaGerado.getStackTrace()) {
                if (enviarTudo) {
                    stackTraceResumido.add(etapa.getClassName());
                } else {
                    for (String palavra : pPalavras) {
                        if (!UtilSBCoreStringValidador.isNuloOuEmbranco(palavra)) {
                            if (etapa.getClassName().contains(palavra)) {
                                stackTraceResumido.add(UtilSBCoreErros.getSimpleName(etapa.getClassName()) + "->" + etapa.getMethodName() + "-Linha" + etapa.getLineNumber());
                            }
                        }
                    }
                }

            }
        }
        return stackTraceResumido;

    }

    @Override
    public Throwable getErroGerado() {
        checarConfiguracao();
        return erroJavaGerado;
    }

    @Override
    public List<String> causas() {
        checarConfiguracao();
        List<String> causas = new ArrayList<>();
        for (String causa : ExceptionUtils.getRootCauseStackTrace(erroJavaGerado)) {
            causas.add(causa);
        }
        return causas;

    }

    @Override
    public String getCausaInicial() {
        checarConfiguracao();
        String mensagem = "Causa inicial não encontrada";
        Throwable causa = ExceptionUtils.getRootCause(erroJavaGerado);
        if (causa != null) {
            mensagem = ExceptionUtils.getRootCause(erroJavaGerado).getMessage();
        }
        return mensagem;
    }

    @Override
    public String getCausaFinal() {
        checarConfiguracao();
        if (erroJavaGerado == null) {
            return null;
        }
        if (erroJavaGerado.getCause() == null) {
            return null;
        }
        return erroJavaGerado.getCause().getMessage();
    }

    @Override
    public final void configurar(ItfMensagem pMensagemDoDesenvolvedor, FabErro pTipoErrp, Throwable pErroGerado) {
        this.mensagemDoDesenvolvedor = pMensagemDoDesenvolvedor;
        this.tipoErro = pTipoErrp;
        this.erroJavaGerado = pErroGerado;
        configurado = true;
    }

    protected String getMensagemGenericaFormatada(String cabecalho) {
        String texto = "";
        if (cabecalho == null) {
            texto = "";
        } else {
            texto += (cabecalho) + "\n";
        }

        if (mensagemDoDesenvolvedor.getMenssagem() != null) {

        }

        if (erroJavaGerado == null) {
            return "ocorreu um erro, a exception não foi gerada para realizar o StackTrace, e a mensagem enviada foi " + mensagemDoDesenvolvedor.getMenssagem();
        }

        String mensagem = "";
        String causa = "";
        if (erroJavaGerado.getMessage() == null) {
            mensagem = "MENSAGEM: \n" + getMsgDesenvolvedorLancou();
        } else {
            mensagem = "MENSAGEM: \n" + getErroGerado().getMessage();
        }
        if (erroJavaGerado.getCause() != null) {
            causa = "CAUSA_FINAL: \n" + getErroGerado().getCause();

        }
        texto += "\n" + mensagem + "\n" + causa;

        texto += "\n CAUSA_INICIAL \n" + getCausaInicial();

        texto += "\n [LOCAL_PROVAVEL_PARA_CORREÇÃO:] \n";

        texto += "\n" + getCaminhoStackTraceResumidoStr();

        texto = UtilSBCoreStringFiltros.quebrarStringEmLinhas(texto, 100);
        return texto;

    }

    /**
     *
     * O Conceito Geral para notificação de Erros é dividido entre:
     *
     *
     * ALERTA_SISTEMA: CRIA UM LOG DO ERRO --------------------------------
     * ALERTA USUARIO: EXIBE A MENSAGEM PARA O USUARIO------------------------
     * ALERTA SOLICITAR_REPARO: EXIBE NO SYSOUT UM ALERTA QUANDO EM
     * DESENVOLVIMENTO, SALVA EM LOG QUANDO EM TESTE, E NÃO FAZ NADA QUANDO EM
     * PRODUÇÃO PARA TUDO: EXECUTA O SYSTEM.OUT(0) CAUSANDO A PARALIZAÇÃO DO
     * ARQUIVAR_LOG QUANDO EM DESENVOLVIMENTO
     *
     * A forma de exibir a mensagem para o usuário ou para o programador, ou
     * arquivar um log de sistemas pode ser alterado de acordo com a aplicação
     * alterando a centralDeMensagens
     *
     */
    protected void sytemOutDoErro() {

        try {

            if (erroJavaGerado == null) {
                try {
                    System.out.println("Erro não enviado gerando nova exceção para" + mensagemDoDesenvolvedor.getMenssagem());
                    throw new Exception("Erro não enviado gerando nova exceção para:" + mensagemDoDesenvolvedor.getMenssagem());
                } catch (Throwable e) {
                    erroJavaGerado = e;
                }
            }

            System.err.println();
            System.err.println();
            System.err.println();
            System.err.println("_______*Relato de erro gerenciada por____" + this.getClass().getSimpleName() + "_________________________");
            System.err.println("________________________________________________________________________________________________________________________________________________________");
            System.err.println("ERRO[" + tipoErro + "]:" + getMsgDesenvolvedorLancou());
            System.err.println("[MENSAGEM JAVA]");
            System.err.println(erroJavaGerado.getMessage());
            System.err.println("[CAUSA JAVA]");
            System.err.print(erroJavaGerado.getCause());
            System.err.println();
            System.err.println("[Mensagem Localizada]");
            System.err.println(erroJavaGerado.getLocalizedMessage());
            System.err.println("[CAMINHO]");
            erroJavaGerado.printStackTrace();
            System.err.println("________________________________________________________________________________________________________________________________________________________");
            System.err.println("________________________________________________________________________________________________________________________________________________________");
            System.err.println();
            System.err.println();
            System.err.println();

        } catch (Throwable erro) {
            System.out.println("Ouve um Inception,Aconteceu um erro dentro do lançamento de erros, isto precisa ser corrigido, que a força esteja com você.");
            pararSistem();
        }

    }

    protected void pararSistem() {
        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            System.err.println("_____________________________________________________________________");
            System.err.println(" ESTE ERRO PRECISA SER CORRIGIDO ANTES DE CONTINUAR O DESENVOLVIMENTO");
            System.err.println("_____________________________________________________________________");
            sytemOutDoErro();
            System.exit(0);
        } else {

            System.err.println("_____________________________________________________________________");
            System.err.println(" ESTE ERRO PRECISA SER CORRIGIDO ANTES DE CONTINUAR O DESENVOLVIMENTO");
            System.err.println(" ***************** O SISTEMA SÓ NÃO FOI PARADO POR NÃO ESTAR NO MODO DE DESENVOLVIMENTO ");
            System.err.println("_____________________________________________________________________");
            sytemOutDoErro();
        }
    }

    public List<String> getMensagensContendoIsto(String pIsto) {
        List<String> mensagens = new ArrayList<>();

        Throwable erro = erroJavaGerado;
        while (erro != null) {
            if (erro.getMessage() != null && erro.getMessage().contains(pIsto)) {

                mensagens.add(pIsto);
            } else if (erro.getLocalizedMessage() != null && erro.getLocalizedMessage().contains(pIsto)) {
                mensagens.add(pIsto);
            }
            erro = erro.getCause();

        }
        return mensagens;
    }
}
