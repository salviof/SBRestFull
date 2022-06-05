/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.lang.reflect.Method;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreReflexaoMetodoEmContextoDeExecucao {

    public static <T> T getAnotacaoNesteMetodo(Class<T> classeAnotacao, int pInicioPesquisaMetodo, int maximoPesquisa) {

        final Thread t = Thread.currentThread();
        final StackTraceElement[] stackTraceTodosMetodos = t.getStackTrace();
        int idfinalStacktrace = stackTraceTodosMetodos.length - 1;
        if (pInicioPesquisaMetodo >= stackTraceTodosMetodos.length) {
            pInicioPesquisaMetodo = stackTraceTodosMetodos.length - 1;
        }
        for (int i = pInicioPesquisaMetodo; (i <= idfinalStacktrace); i++) {
            final StackTraceElement stackTrhaceMetodoAtual = stackTraceTodosMetodos[i];
            final String methodName = stackTrhaceMetodoAtual.getMethodName();
            final String className = stackTrhaceMetodoAtual.getClassName();
            Class<?> classedoMetodo;
            try {
                classedoMetodo = Class.forName(className);
                do {
                    for (final Method metodoCadidato : classedoMetodo.getDeclaredMethods()) {
                        if (metodoCadidato.getName().equals(methodName)) {

                            Class antclass = classeAnotacao;
                            T anotacao = (T) metodoCadidato.getAnnotation(antclass);
                            if (anotacao != null) {
                                return anotacao;
                            }
                        }
                    }
                    classedoMetodo = classedoMetodo.getSuperclass();
                } while (classedoMetodo != null);
            } catch (Throwable tt) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obter Método vinculado", tt);
            }

        }
        return null;

    }

    public static boolean isContextoOriginouDestaClasse(Class pClasse, int pInicioPesquisaMetodo, int maximoPesquisa) {

        final Thread t = Thread.currentThread();
        final StackTraceElement[] stackTraceTodosMetodos = t.getStackTrace();
        String classePesquisa = pClasse.getSimpleName();

        int idfinalStacktrace = stackTraceTodosMetodos.length - 1;
        if (pInicioPesquisaMetodo >= stackTraceTodosMetodos.length) {
            pInicioPesquisaMetodo = stackTraceTodosMetodos.length - 1;
        }

        for (int i = pInicioPesquisaMetodo; (i <= idfinalStacktrace); i++) {
            final StackTraceElement stackTrhaceMetodoAtual = stackTraceTodosMetodos[i];
            final String className = stackTrhaceMetodoAtual.getClassName();

            if (classePesquisa.equals(className)) {
                return true;
            } else {
                try {
                    Class classedoMetodo = Class.forName(className);
                    if (classedoMetodo.isAssignableFrom(pClasse)) {
                        return true;
                    }
                } catch (Throwable T) {

                }
            }
        }
        return false;

    }

    public static <T> T getAnotacaoNesteMetodo(Class<T> classeAnotacao) {
        return getAnotacaoNesteMetodo(classeAnotacao, 3, 5);
    }

}
