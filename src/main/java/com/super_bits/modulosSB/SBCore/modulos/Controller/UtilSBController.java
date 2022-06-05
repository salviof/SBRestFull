/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreGeradorDeID;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfControlerAPP;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.coletivojava.fw.utilCoreBase.UtilSBControllerSimples;

/**
 *
 * Metodos estaticos de suporte a camada Controler da aplicação
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 19/12/2015
 * @version 1.0
 *
 */
public class UtilSBController extends UtilSBControllerSimples {

    /**
     * public static ItfAcaoDoSistema obterAcaoByMethodo(Method pMetodo) {
     * Annotation[] anotacoes = pMetodo.getAnnotations();
     *
     * for (Annotation an : anotacoes) {
     *
     * Method[] metodos = an.annotationType().getDeclaredMethods(); for (Method
     * fld : metodos) { if (fld.getName().equals("acao")) {
     *
     * try { ItfFabricaModulos fabrica = (ItfFabricaModulos) fld.invoke(an);
     * ItfAcaoDoSistema acao = fabrica.getAcaoDoSistema();
     * acao.setId(UtilSBController.gerarIDMetodoAcaoDoSistema(pMetodo));
     * acao.setIdMetodo(UtilSBController.gerarIDMetodoAcaoDoSistema(pMetodo));
     *
     * return acao;
     *
     * } catch (Throwable t) { SBCore.RelatarErro(FabErro.PARA_TUDO,"A acao do
     * metodo" + pMetodo.getName() + " não contem uma anotação extendendo
     * Itffabrica, contendo o campo acao, desta forma não é possível determinar
     * o nível de permissão deste método", t);
     *
     * }
     * }
     * }
     * }
     * return null; }
     */
    public static ItfAcaoController getAcaoDoContexto() {
        int inicioPesquisaMetodo = 3;

        final Thread t = Thread.currentThread();
        final StackTraceElement[] stackTraceTodosMetodos = t.getStackTrace();
        int idfinalStacktrace = stackTraceTodosMetodos.length - 1;
        if (idfinalStacktrace > 20) {
            idfinalStacktrace = 20;
        }
        if (inicioPesquisaMetodo >= stackTraceTodosMetodos.length) {
            inicioPesquisaMetodo = stackTraceTodosMetodos.length - 1;
        }
        for (int i = inicioPesquisaMetodo; (i <= idfinalStacktrace); i++) {
            final StackTraceElement stackTrhaceMetodoAtual = stackTraceTodosMetodos[i];
            final String methodName = stackTrhaceMetodoAtual.getMethodName();
            final String className = stackTrhaceMetodoAtual.getClassName();

            Class<?> classedoMetodo;
            try {
                classedoMetodo = Class.forName(className);
                if (ItfControlerAPP.class.isAssignableFrom(classedoMetodo)) {
                    do {
                        for (final Method metodoController : classedoMetodo.getDeclaredMethods()) {
                            if (metodoController.getName().equals(methodName)) {

                                if (UtilSBController.possuiMetodoDeAcao(metodoController)) {
                                    return getEnumAcaoVinculadoEmMetodo(metodoController).getRegistro().getComoController();
                                }

                            }
                        }
                        classedoMetodo = classedoMetodo.getSuperclass();

                    } while (classedoMetodo != null);
                }
            } catch (ClassNotFoundException nf) {

            } catch (Throwable tt) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obter Método vinculado", tt);
            }

        }

        return null;
    }

    /**
     *
     * Utiliza o Factury da constante de acao anotada para obter a Ação do
     * sistema
     *
     * Explicação tecnica: A função localiza anotacoes que contenham um campo do
     * tipo enum chamdo acao, e supoe que este campo é um enum implementando
     * ItffabricaModulos de ações do sistema, tendando obter assim a Ação de
     * sistema a partir deste enum
     *
     * @param pMetodo O Metodo estático da ação
     * @param pararSistemaCasoNaoEncontre :P
     * @return A ação vinculada ao método estático
     */
    public static ItfAcaoController getAcaoByMetodo(Method pMetodo, boolean pararSistemaCasoNaoEncontre) {
        try {

            if (pMetodo == null) {
                throw new UnsupportedOperationException("Envio de metodo nulo para ação GetAcaoByMetodo");
            }
            ItfFabricaAcoes acao = getFabricaAcaoByMetodo(pMetodo);

            if (acao == null) {
                throw new UnsupportedOperationException("En GetAcaoByMetodo");
            }

            ItfAcaoController acaoSisTema = (ItfAcaoController) acao.getRegistro().getComoController();
            acaoSisTema.setIdMetodo(pMetodo);

            return acaoSisTema;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Ação por Método", t);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Ação para o  Método" + pMetodo.getName() + " em " + pMetodo.getDeclaringClass().getSimpleName(), t);
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro Para Tudo obtendo ação pelo id do metodo" + pMetodo.getName(), t);
            return null;
        }
    }

    public static boolean possuiMetodoDeAcao(Method pMetodo) {

        Annotation[] anotacoes = pMetodo.getAnnotations();
        if (anotacoes != null) {
            for (Annotation a : anotacoes) {

                try {

                    Method metodo = a.getClass().getMethod("acao");
                    return true;

                } catch (Throwable t) {

                }

            }
        }

        return false;
    }

    public static ItfFabricaAcoes getEnumAcaoVinculadoEmMetodo(Method pMetodo) {
        try {
            Annotation[] anotacoes = pMetodo.getAnnotations();
            if (anotacoes != null) {
                for (Annotation a : anotacoes) {
                    try {
                        Method metodo = a.getClass().getMethod("acao");
                        if (metodo != null) {
                            ItfFabricaAcoes acao = (ItfFabricaAcoes) metodo.invoke(a);
                            return acao;
                        }

                    } catch (Throwable t) {

                    }

                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo ação em método, você pode utilizar  o método possuiMetodoDeAcao antes desta chamada, para contornar este problema. ", t);
            return null;
        }

        return null;
    }

    public static ItfFabricaAcoes getFabricaAcaoByMetodo(Method pMetodo) {
        try {
            return (ItfFabricaAcoes) UtilSBCoreReflexao.getFabricaDoMetodoByAnotacao(pMetodo, "acao", true);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro tentando obeter a Fabrica de acao a partir do metodo certifique que os metodos da classe de controler tenha uma anotação informando a ação vinculada" + pMetodo.getName(), null);
        }
        return null;

    }

    public static ItfFabricaAcoes getFabricaAcaoByClasse(Class pClasse) {
        try {
            Annotation[] anotacoes = pClasse.getAnnotations();
            if (anotacoes != null) {
                for (Annotation a : anotacoes) {

                    try {

                        Method metodo = a.getClass().getMethod("acao");
                        if (metodo == null) {
                            System.out.println("A classe" + pClasse + "Não possui uma anotação de ação vinculada ");

                        } else {

                            ItfFabricaAcoes acao;
                            try {
                                acao = (ItfFabricaAcoes) metodo.invoke(a);
                                return acao;
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                                SBCore.RelatarErro(FabErro.PARA_TUDO, "O nome [acao] em uma anotação está reservado para enuns que extendam " + ItfFabricaAcoes.class.getSimpleName(), ex);
                            }

                        }

                    } catch (NoSuchMethodException | SecurityException ex) {

                        //   FabErro. .paraSistema("MÉTODO AÇÃO NÃO ENCONTADO NA ANOTAÇÃO DE METODO DE AÇÃO DO SISTEMA", ex);
                    }
                }

            }
            throw new UnsupportedOperationException("A classe " + pClasse.getSimpleName() + " não está anotada com uma anotação contendo uma ação");
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "A anotação de ação não foi encontrada na classe", t);
        }

        return null;

    }

    /**
     *
     * Procura um metodo de anotação com o nome Acao, que seja uma fabrica de
     * ações cria uma ação atravez da fabrica e retorna
     *
     * @param pClasse Classe referenciada
     * @return o Objeto AcaodoSistema configurado
     */
    public static ItfAcaoDoSistema getAcaoByClasse(Class pClasse) {

        ItfFabricaAcoes acao = (ItfFabricaAcoes) getFabricaAcaoByClasse(pClasse);

        return (ItfAcaoDoSistema) acao.getRegistro();

        //   SBCore.RelatarErro(FabErro.PARA_TUDO,"Erro tentando obeter a Fabrica de acao a partir do metodo", null);
    }

    public static Method getMetodoByAcaoController(ItfAcaoController pAcaoController) {
        try {
            if (pAcaoController == null) {
                throw new UnsupportedOperationException("Erro tentando obter o metodo de execução da ação enviando ação nula como parametro");
            }
            if (!SBCore.isControleDeAcessoDefinido()) {
                throw new UnsupportedOperationException("O recurso getMetodo by ação Controoler só pode ser chamado se a classe de Controle de ações for definida.");
            }
            return ConfigPermissaoSBCoreAbstrato.getMetodoByAcaoController(pAcaoController);

        } catch (Throwable t) {
            SBCore.RelatarErroAoUsuario(FabErro.SOLICITAR_REPARO, "Erro ao obter método por ação", t);
            return null;
        }

    }

}
