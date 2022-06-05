/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 *
 * @author sfurbino
 */
public class UtilSBCoreErros {

    public static String getSimpleName(String nomeCompleto) {
        String[] caminho = nomeCompleto.split("\\.");
        return caminho[caminho.length - 1];
    }

    public static String getResumoErro(Throwable pErro) {
        String texto = pErro.getMessage() + "\n";

        if (ExceptionUtils.getRootCause(pErro) != null) {
            texto += "\n CAUSA_INICIAL \n" + ExceptionUtils.getRootCause(pErro).getMessage();
        }
        if (pErro.getStackTrace() != null) {
            texto += "\n [LOCAL_PROVAVEL_PARA_CORREÇÃO:] \n";
            for (StackTraceElement etapa : pErro.getStackTrace()) {

                if (etapa.getClassName().contains("super_bits")) {
                    texto += "\n" + getSimpleName(etapa.getClassName()) + "->" + etapa.getMethodName() + "-Linha" + etapa.getLineNumber();
                }
            }
        }
        texto = UtilSBCoreStringFiltros.quebrarStringEmLinhas(texto, 30);
        return texto;
    }

    public static Throwable getCausaRaiz(Throwable pErro) {
        return ExceptionUtils.getRootCause(pErro);
    }

}
