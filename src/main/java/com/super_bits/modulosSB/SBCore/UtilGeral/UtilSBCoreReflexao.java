/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemGenerico;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Transient;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreReflexaoSimples;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;

/**
 *
 * Métodos Staticos para auxiliar na Reflexão de Classes
 *
 * @author Salvio
 */
public abstract class UtilSBCoreReflexao extends UtilSBCoreReflexaoSimples {

    /**
     *
     * Retorna as instancias encontradas em uma classe que forem de determinado
     * tipo
     *
     * @param instancia
     * @param tipoProcurado
     * @return
     */
    public static List<?> procuraInstanciasDeCamposPorTipo(Object instancia, Class tipoProcurado) {
        Class classe = instancia.getClass();
        Field[] fields = classe.getDeclaredFields();
        List<Object> resposta = new ArrayList<>();

        try {
            for (Field campo : fields) {
                if (campo.getType().getName().equals(tipoProcurado.getName())) {
                    campo.setAccessible(true);
                    try {
                        resposta.add(campo.get(instancia));
                    } catch (IllegalArgumentException | IllegalAccessException ex) {

                    }
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo instancias no objeto " + instancia.getClass().getSimpleName() + " por tipo " + tipoProcurado.getSimpleName(), t);
        }
        if (resposta.isEmpty()) {
            System.out.println("Nenhum " + tipoProcurado.getClass().getSimpleName() + " foi instanciado  em " + instancia.getClass().getSimpleName());
        }
        return resposta;
    }

    public static List<Field> procuraCamposPorTipo(Class pClasse, Class tipoProcurado) {
        Field[] fields = pClasse.getDeclaredFields();

        List<Field> resposta = new ArrayList<>();
        for (Field campo : fields) {
            if (campo.getType().getName().equals(tipoProcurado.getName())) {
                campo.setAccessible(true);
                try {
                    resposta.add(campo);
                } catch (Throwable ex) {
                    Logger.getLogger(UtilSBCoreReflexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return resposta;
    }

    /**
     *
     * Retorna todos os Filds de uma classe que forem de um tipo determinado
     *
     * @param instancia
     * @param tipoProcurado
     * @return
     */
    public static List<Field> procuraCamposPorTipo(Object instancia, Class tipoProcurado) {
        Class classe = instancia.getClass();
        return procuraCamposPorTipo(classe, tipoProcurado);

    }

    public static List<Field> procuraCamposQueImplementemIsto(Object instancia, Class intefaceDesejada) {

        List<Field> camposEncontrados = new ArrayList<>();
        try {
            if (instancia == null) {
                throw new UnsupportedOperationException("enviou instancia nula");
            }
            Class classe = instancia.getClass();
            Arrays.stream(classe.getDeclaredFields()).forEach(
                    campo -> {
                        try {
                            Arrays.stream(campo.getDeclaringClass().getInterfaces()).forEach(itf -> {
                                if (itf == intefaceDesejada) {
                                    camposEncontrados.add(campo);
                                }
                            });

                        } catch (Throwable t) {
                            throw new UnsupportedOperationException("Erro interpretando " + campo.getName());
                        }
                    }
            );
        } catch (Throwable t) {
            SBCore.RelatarErroAoUsuario(FabErro.SOLICITAR_REPARO, "Erro Localizando interface em objeto obj=" + instancia + " tipo procurado=" + intefaceDesejada, t);
        }
        return camposEncontrados;

    }

    /**
     *
     * Percorre todos os atributos da classe, e instancia todos os List, com
     * newArrayList facilitando o constructor de classes e evitando nullpoint
     * exceptions
     *
     * @param instancia
     * @return
     */
    public static List<Field> instanciarListas(Object instancia) {

        List<Class> classes = UtilSBCoreReflexao.getClassesComHierarquiaAteNomeObjetoFinalConterNoInicio(instancia.getClass(), "Entidade", "ItemGenerico");
        List<Field> resposta = new ArrayList<>();
        for (Class classe : classes) {
            Field[] fields = classe.getDeclaredFields();
            for (Field campo : fields) {
                if (campo.getType().getName().equals(List.class.getName())) {
                    campo.setAccessible(true);
                    try {
                        campo.set(instancia, new ArrayList<>());
                        resposta.add(campo);
                        //System.out.println("Lista Auto Instanciada" + campo.getName());
                    } catch (Throwable ex) {
                        Logger.getLogger(UtilSBCoreReflexao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
        return resposta;
    }

    /**
     * Retorna o Siple-name da classe (Nome dela sem o caminho completo)
     *
     * @param pNomeCompleto
     * @return
     */
    public static String getSimpleNamebyString(String pNomeCompleto) {
        return getCaminhoParcialFinalByFullName(pNomeCompleto, 1);
    }

    /**
     *
     * * Retorna final do Camino da Classe segundo o numero de camadas
     * solicitada
     *
     * @param nomecompleto
     * @param pNCasas
     * @return
     */
    public static String getCaminhoParcialFinalByFullName(String nomecompleto, int pNCasas) {
        String[] casas = nomecompleto.split("\\.");
        int ultimacasa = casas.length - 1;
        String resposta = "";
        for (int i = ultimacasa; i > ultimacasa - pNCasas; i--) {
            if (i == ultimacasa) {
                resposta = casas[i];
            } else {
                resposta = casas[i] + "." + resposta;
            }
        }
        return resposta;

    }

    /**
     *
     * Retorna inicio do Camino da Classe segundo o numero de camadas solicitada
     *
     * @param nomecompleto
     * @param pNCasas
     * @return
     */
    public static String getCaminhoParcialInicialByFullName(String nomecompleto, int pNCasas) {

        String[] casas = nomecompleto.split("\\.");
        int ultimacasa = casas.length - 1;
        String resposta = "";
        for (int i = 0; i <= pNCasas - 1; i++) {
            if (i == 0) {
                resposta = casas[i];
            } else {
                resposta = resposta + "." + casas[i];
            }
        }
        return resposta;

    }

    /**
     * Retorna todas as classes do Pacote Utilizando a Biblioteca
     * org.Reflections. <br>
     * !!! Alerta !!!!!!!!!!!!! cuidado ao Utilizar este comando pois a <br>
     * performance não é imediata, Utilize intefaces com services para carregar
     * <br>
     * classes dinamicas de nameira rápida, o ideal é utilizar este código<br>
     * apenas em inicializações de sistemas
     *
     * @param CaminhoPacote
     * @return
     */
    public static List<Class> getClassesDoPacote(String pCaminhoPacote) {

        /**
         * Informações úteis sobre a biblioteca: // O parâmetro new
         * SubTypesScanner(false) permite a listagem de classes através
         * deObject.class // `, isto é, classes que não tem herança
         * explícita.Caso contrário essas classes seriam ignoradas // .
         *
         *
         * parâmetro ClasspathHelper.forClassLoader() lista todas as classes do
         * Class Loader atual, caso contrário seriam listadas apenas as classes
         * do projeto/jar atual.
         *
         *
         * Somente tenha cuidado se alguma classe que não puder ser carregada
         * (talvez por falta de uma dependência), senão você acaba com uma
         * exceção como essa:
         *
         * at org.reflections.ReflectionUtils.forName(ReflectionUtils.java:378)
         * at org.reflections.ReflectionUtils.forNames(ReflectionUtils.java:387)
         * at org.reflections.Reflections.getSubTypesOf(Reflections.java:338) at
         * snippet.ListClasses.main(ListClasses.java:15)
         */
        Reflections r = new Reflections(
                pCaminhoPacote,
                new SubTypesScanner(false),
                ClasspathHelper.forClassLoader()
        );
        Set<Class<?>> classes = r.getSubTypesOf(Object.class);
        List<Class> resposta = new ArrayList<>();
        resposta.addAll(classes);
        //exibe a lista classes
        for (Class<?> c : classes) {
            System.out.println(c.getName());
        }
        return resposta;
    }

    /**
     *
     *
     * Retorna a classe que extende determinado tipo de classe
     *
     * TODO: DISPARAR ERRO QUANDO ENCONTRAR MAIS DE UMA
     *
     * @param pTipo
     * @param pCaminhoPacote
     * @return
     */
    public static Class getClasseQueEstendeIsto(Class pTipo, String pCaminhoPacote) {

        /**
         * Informações úteis sobre a biblioteca: // O parâmetro new
         * SubTypesScanner(false) permite a listagem de classes através
         * deObject.class // `, isto é, classes que não tem herança
         * explícita.Caso contrário essas classes seriam ignoradas // .
         *
         *
         * parâmetro ClasspathHelper.forClassLoader() lista todas as classes do
         * Class Loader atual, caso contrário seriam listadas apenas as classes
         * do projeto/jar atual.
         *
         *
         * Somente tenha cuidado se alguma classe que não puder ser carregada
         * (talvez por falta de uma dependência), senão você acaba com uma
         * exceção como essa:
         *
         * at org.reflections.ReflectionUtils.forName(ReflectionUtils.java:378)
         * at org.reflections.ReflectionUtils.forNames(ReflectionUtils.java:387)
         * at org.reflections.Reflections.getSubTypesOf(Reflections.java:338) at
         * snippet.ListClasses.main(ListClasses.java:15)
         */
        Reflections r = new Reflections(
                pCaminhoPacote,
                new SubTypesScanner(true),
                ClasspathHelper.forClassLoader()
        );

        Set<Class<?>> classes = r.getSubTypesOf(pTipo);

        //exibe a lista classes
        for (Class<?> c : classes) {
            if (!Modifier.isAbstract(c.getModifiers())) {
                return c;
            }
        }
        return null;

    }

    /**
     *
     *
     * Retorna a classe que extende determinado tipo de classe
     *
     * TODO: DISPARAR ERRO QUANDO ENCONTRAR MAIS DE UMA
     *
     * @param pTipo
     * @param pCaminhoPacote
     * @return
     */
    public static Class getClasseQueImplementeIsto(Class pTipo, String pCaminhoPacote) {

        /**
         * Informações úteis sobre a biblioteca: // O parâmetro new
         * SubTypesScanner(false) permite a listagem de classes através
         * deObject.class // `, isto é, classes que não tem herança
         * explícita.Caso contrário essas classes seriam ignoradas // .
         *
         *
         * parâmetro ClasspathHelper.forClassLoader() lista todas as classes do
         * Class Loader atual, caso contrário seriam listadas apenas as classes
         * do projeto/jar atual.
         *
         *
         * Somente tenha cuidado se alguma classe que não puder ser carregada
         * (talvez por falta de uma dependência), senão você acaba com uma
         * exceção como essa:
         *
         * at org.reflections.ReflectionUtils.forName(ReflectionUtils.java:378)
         * at org.reflections.ReflectionUtils.forNames(ReflectionUtils.java:387)
         * at org.reflections.Reflections.getSubTypesOf(Reflections.java:338) at
         * snippet.ListClasses.main(ListClasses.java:15)
         */
        Reflections r = new Reflections(
                pCaminhoPacote,
                new SubTypesScanner(true),
                ClasspathHelper.forClassLoader()
        );
        //TODO

        throw new UnsupportedOperationException("Aind anão foi implemtado");

    }

    /**
     *
     * Verifica se classe ou algum superType da classe implementa uma interface
     *
     *
     * @param pClass Classe onde a interface é implementada
     * @param pInterfaceOuClasse Interface que está sendo procurada
     * @return
     */
    @Transient
    public static boolean isInterfaceImplementadaNaClasse(Class pClass, Class pInterfaceOuClasse) {
        /// NÃO FUNCIONA, PRECISA DE MELHORIAS

        boolean temMaisClasse = true;
        if (pClass == null || pInterfaceOuClasse == null) {
            return false;
        }
        if (pClass.isAssignableFrom(pInterfaceOuClasse)) {
            return true;
        }
        /**
         * if (!pClass.isInterface() && pInterfaceOuClasse.isInterface()) { try
         * {
         *
         * if (pInterfaceOuClasse.isInstance(pClass.newInstance())) { return
         * true; } } catch (IllegalAccessException ilegal) {
         *
         * } catch (InstantiationException instancia) {
         *
         * } catch (Throwable t) {
         *
         * }
         * }
         */

        try {
            Class classe = pClass;
            while (temMaisClasse) {
                if (classe.getSimpleName().equals(Object.class.getSimpleName())) {
                    return false;
                }

                if (pInterfaceOuClasse.getSimpleName().equals(classe.getSimpleName())) {
                    return true;
                }

                Class<?>[] impls = pClass.getInterfaces();
                for (Class interFace : impls) {

                    if (pInterfaceOuClasse.getSimpleName().equals(interFace.getSimpleName())) {
                        return true;
                    }
                    for (Class classeDaInterface : getClassesComHierarquiaAteNomeObjetoFinalConter(interFace, "ItemGenerico", "ItfBeanGenerico")) {
                        if (pInterfaceOuClasse.getSimpleName().equals(classeDaInterface.getSimpleName())) {
                            return true;
                        }
                    }

                }

                if (classe.getSimpleName().equals(ItemGenerico.class.getSimpleName())
                        || classe.getSimpleName().equals(Object.class.getSimpleName())) {
                    temMaisClasse = false;
                }
                classe = classe.getSuperclass();
                if (classe == null) {
                    return false;
                }
            }
            return false;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando se a classe implementa" + pInterfaceOuClasse, t);
            return false;
        }
    }

    public static Method getMetodoByAcao(ItfAcaoController pAcaoDoSistema) {

        try {
            ItfAcaoController acaocontroller = (ItfAcaoController) pAcaoDoSistema;
            Method metodo = SBCore.getCentralPermissao().getMetodoByAcao(pAcaoDoSistema);
            if (metodo == null) {
                throw new Throwable("Método não foi encontrado para a ação" + pAcaoDoSistema.getNomeUnico());
            }
            return metodo;
        } catch (Throwable t) {
            String nomeAcao = "Acao não enviada";
            if (pAcaoDoSistema != null) {
                nomeAcao = pAcaoDoSistema.getNomeUnico();
            }

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obterMetodo por ação, Verifique se existe o método implementado para a ação " + nomeAcao, t);
        }
        return null;

    }

    /**
     *
     *
     *
     * Retorna uma fabrica, de acordo com o nome do parametro da anotação.
     *
     * Exemplo: uma classe anotada com
     *
     * @InfoModulo(modulo=MinhaFabricaDeModulos.moduloPrincipal)
     *
     * Retornaria o fabrica do moduloPrincipal, quando pesquisado
     * pNomemetodoAnotacao=modulo
     *
     *
     * @param pClasse A classe onde a anotação será pesquisada
     * @param pNomeMetodoAnotacao O nome do atributo que será pesquisado
     * @param pararSistemaCasoNaoEncontre boolean informando se um paratudo deve
     * ser gerado em caso de erro
     * @return
     */
    public static ItfFabrica getFabricaDaClasseByAnotacao(Class pClasse, String pNomeMetodoAnotacao, boolean pararSistemaCasoNaoEncontre) {
        try {

            Annotation[] anotacoes = pClasse.getAnnotations();
            try {
                if (anotacoes != null) {

                    for (Annotation a : anotacoes) {

                        try {

                            Method metodo = a.getClass().getMethod(pNomeMetodoAnotacao);
                            try {

                                ItfFabrica fabrica = (ItfFabrica) metodo.invoke(a);
                                return fabrica;

                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {

                            }
                        } catch (NoSuchMethodException | SecurityException ex) {

                        }
                    }

                    throw new UnsupportedOperationException("Anotação do tipo fabrica não foi encontrada no método" + pNomeMetodoAnotacao + " na classe " + pClasse.getName()
                    );

                }
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro tentando obeter a Fabrica a partir da classe certifique que a classe tenha uma anotação informando o parametro " + pNomeMetodoAnotacao + " vinculado a" + pClasse.getName(), t);
            }
            return null;

        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Ação por Método", t);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Ação para o  Método" + pClasse.getName() + " em " + pClasse.getDeclaringClass().getSimpleName(), t);
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro Para Tudo obtendo ação pelo id do metodo" + pClasse.getName(), t);
            return null;
        }
    }

    /**
     *
     *
     *
     * Retorna uma fabrica, de acordo com o nome do parametro da anotação.
     *
     * Exemplo: um metodo anotado com
     *
     * @InfoModulo(modulo=MinhaFabricaDeModulos.moduloPrincipal)
     *
     * Retornaria o fabrica do moduloPrincipal, quando pesquisado
     * pNomemetodoAnotacao=modulo
     *
     *
     * @param pMetodo O Método onde a anotação será pesquisada
     * @param pNomeMetodoAnotacao O nome do atributo que será pesquisado
     * @param pararSistemaCasoNaoEncontre boolean informando se um paratudo deve
     * ser gerado em caso de erro
     * @return
     */
    public static ItfFabrica getFabricaDoMetodoByAnotacao(Method pMetodo, String pNomeMetodoAnotacao, boolean pararSistemaCasoNaoEncontre) {

        Annotation anotacao = getAnotacaoComEsteMetodo(pMetodo.getAnnotations(), pNomeMetodoAnotacao);

        try {
            Method metodo = anotacao.getClass().getMethod(pNomeMetodoAnotacao);
            ItfFabrica fabrica = (ItfFabrica) metodo.invoke(anotacao);
            return fabrica;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Ação por Método", t);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Ação para o  Método" + pMetodo.getName() + " em " + pMetodo.getDeclaringClass().getSimpleName(), t);

            if (pararSistemaCasoNaoEncontre) {
                SBCore.RelatarErro(FabErro.PARA_TUDO, pNomeMetodoAnotacao, t);
            } else {
                SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro Para Tudo obtendo ação pelo id do metodo" + pMetodo.getName(), t);
            }
            return null;

        }

    }

    public static Annotation getAnotacaoComEsteMetodo(Annotation[] anotacoes, String pNomeMetodoAnotacao) {

        for (Annotation anotacao : anotacoes) {

            try {

                Method metodo = anotacao.getClass().getMethod(pNomeMetodoAnotacao);
                if (metodo != null) {
                    return anotacao;
                }
            } catch (NoSuchMethodException | SecurityException ex) {

            }

        }
        throw new UnsupportedOperationException("Anotação com o metodo (propriedade de anotação ) " + pNomeMetodoAnotacao + "não encontrada em " + anotacoes);

    }

    /**
     *
     * Retorna todas as classes por hierarquia até encontrar um objeto final
     *
     * @param pClasse
     * @param objetosFinal
     * @return
     */
    protected static List<Class> getClassesComHierarquiaAteObjetoFinal(Class pClasse, Class... objetosFinal) {

        List<Class> classes = new ArrayList<>();

        Class classeAtual = pClasse;
        boolean encontrou = false;
        while (!encontrou) {
            if (classeAtual == ItemGenerico.class
                    || classeAtual == Object.class) {
                return classes;
            }
            for (Class objtoFinal : objetosFinal) {
                if (pClasse == objtoFinal) {
                    return classes;
                }

            }
            classes.add(classeAtual);
            classeAtual = classeAtual.getSuperclass();

        }
        return classes;
    }

    /**
     *
     * Retorna todas as classes por hierarquia até encontrar a string de um
     * objeto final
     *
     * @param pClasse
     * @param nomesObjetosFinal
     * @return
     */
    protected static List<Class> getClassesComHierarquiaAteNomeObjetoFinalConter(Class pClasse, String... nomesObjetosFinal) {

        List<Class> classes = new ArrayList<>();

        Class classeAtual = pClasse;
        boolean encontrou = false;
        classes.add(classeAtual);
        while (!encontrou) {
            if (classeAtual == ItemGenerico.class
                    || classeAtual == Object.class) {
                return classes;
            }
            for (String objtoFinal : nomesObjetosFinal) {
                if (classeAtual.getSimpleName().startsWith(objtoFinal)) {
                    return classes;
                }

            }
            if (classeAtual != pClasse) {
                classes.add(classeAtual);
            }
            if (!classeAtual.isInterface()) {
                classeAtual = classeAtual.getSuperclass();
            } else {
                if (classeAtual.getInterfaces().length > 0) {
                    classeAtual = classeAtual.getInterfaces()[0];
                } else {
                    classeAtual = null;
                }
            }
            if (classeAtual == null) {
                return classes;
            }

        }
        return classes;
    }

    public static List<Class> getClassesComHierarquiaAteCotendoEstaAnotacao(Class pClasse, Class pAnotacao) {
        List<Class> classes = new ArrayList<>();

        Class classeAtual = pClasse;
        boolean encontrou = false;
        while (!encontrou) {
            if (classeAtual == ItemGenerico.class
                    || classeAtual == Object.class) {
                return classes;
            }

            if (pClasse.getAnnotation(pAnotacao) == null) {
                return classes;
            }

            classes.add(classeAtual);
            classeAtual = classeAtual.getSuperclass();

        }
        return classes;
    }

    /**
     *
     * Retorna todas as classes por hierarquia até encontrar a string de um
     * objeto final
     *
     * @param pClasse
     * @param nomesObjetosFinal
     * @return
     */
    protected static List<Class> getClassesComHierarquiaAteNomeObjetoFinalConterNoInicio(Class pClasse, String... nomesObjetosFinal) {

        List<Class> classes = new ArrayList<>();

        Class classeAtual = pClasse;
        boolean encontrou = false;
        while (!encontrou) {
            if (classeAtual == ItemGenerico.class
                    || classeAtual == Object.class) {
                return classes;
            }
            for (String objtoFinal : nomesObjetosFinal) {
                if (pClasse.getSimpleName().startsWith(objtoFinal)) {
                    return classes;
                }

            }
            classes.add(classeAtual);
            classeAtual = classeAtual.getSuperclass();

        }
        return classes;
    }

    /**
     *
     * Retrona uma lista de objetos Fileds, via reflection contendo todos os
     * campos declarados na classe , ou interface sendo publico ou privado eté
     * encontrar uma classe objeto final, caso o objeto final não seja
     * especificado, ele vai até o ItemGenerico, ou até Object
     *
     *
     * @param pclasse
     *
     * @param objetosFinal Indica até qual classe a recursividade deve
     * acontecer, (em qual classe deve parar de coletar campos)
     * @return
     */
    public static List<Field> getCamposRecursivodaClasseAteObjetoFinal(Class pclasse, Class... objetosFinal) {
        List<Field> camposEncontrados = new ArrayList<>();

        for (Class classe : getClassesComHierarquiaAteObjetoFinal(pclasse, objetosFinal)) {
            camposEncontrados.addAll(Arrays.asList(classe.getDeclaredFields()));
        }

        return camposEncontrados;

    }

    /**
     *
     * Retorna uma lista de classes e seus supers até encontrar uma classe que
     * comece com entidade, Item ou Objeto
     *
     * @param pClasse
     * @return
     */
    public static List<Class> getClasseESubclassesAteClasseBaseDeEntidade(Class pClasse) {
        return getClassesComHierarquiaAteNomeObjetoFinalConter(pClasse, "Entidade", "Item", "Object");
    }

    /**
     *
     * Retrona uma lista de objetos Fileds, via reflection contendo todos os
     * campos declarados na classe , ou interface sendo publico ou privado eté
     * encontrar uma classe que contenha parte dos nomes enviados como objeto
     * final, caso o objeto final não seja especificado, ele vai até o
     * ItemGenerico, ou até Object
     *
     *
     * @param pclasse
     *
     * @param objetosFinal Indica até qual classe a recursividade deve
     * acontecer, (em qual classe deve parar de coletar campos)
     * @return
     */
    public static List<Field> getCamposRecursivodaClasseAteConterNomeObjetoFinal(Class pclasse, String... objetosFinal) {
        List<Field> camposEncontrados = new ArrayList<>();

        for (Class classe : getClassesComHierarquiaAteNomeObjetoFinalConter(pclasse, objetosFinal)) {
            camposEncontrados.addAll(Arrays.asList(classe.getDeclaredFields()));
        }

        return camposEncontrados;

    }

    /**
     * Retrona uma lista de objetos Fileds, via reflection contendo todos os
     * campos declarados na classe que são do tipo enviado no parametro
     * pTipoCampo, ou interface sendo publico ou privado eté encontrar uma
     * classe objeto final, caso o objeto final não seja especificado, ele vai
     * até o ItemGenerico, ou até Object
     *
     * @param pclasse
     * @param pTipoCampo
     * @param objetosFinal
     * @return
     */
    public static List<Field> getCamposRecursivoPorTipo(Class pclasse, Class pTipoCampo, Class... objetosFinal) {
        List<Field> camposEncontrados = new ArrayList<>();

        for (Class classe : getClassesComHierarquiaAteObjetoFinal(pclasse, objetosFinal)) {
            for (Field cp : classe.getDeclaredFields()) {

                if (cp.getType().equals(pTipoCampo)) {
                    camposEncontrados.add(cp);
                }
            }
        }

        return camposEncontrados;

    }

    /**
     * Retrona uma lista de objetos Fileds, via reflection contendo todos os
     * campos declarados na classe ou interface que contenha a anotação enviada
     * no parametro, sendo publico ou privado eté encontrar uma classe objeto
     * final, caso o objeto final não seja especificado, ele vai até o
     * ItemGenerico, ou até Object
     *
     *
     * @param pclasse
     * @param anotacao
     * @param objetosFinal
     * @return
     */
    public static List<Field> getCamposRecursivoPorAnotacao(Class pclasse, Class anotacao, Class... objetosFinal) {

        List<Field> camposEncontrados = new ArrayList<>();

        for (Class classe : getClassesComHierarquiaAteObjetoFinal(pclasse, objetosFinal)) {
            for (Field cp : classe.getDeclaredFields()) {
                if (cp.getAnnotation(anotacao) != null) {
                    camposEncontrados.add(cp);
                }
            }
        }

        return camposEncontrados;

    }

    /**
     * Retrona uma lista de objetos Fileds, via reflection contendo todos os
     * campos declarados na classe ou interface que implementem a interface
     * enviada no parametro, sendo publico ou privado eté encontrar uma classe
     * objeto final, caso o objeto final não seja especificado, ele vai até o
     * ItemGenerico, ou até Object
     *
     * @param pclasse
     * @param pItf
     * @param pObjetosFinal
     * @return
     */
    public static List<Field> getCamposRecursivoPorInterface(Class pclasse, Class pItf, Class... pObjetosFinal) {
        List<Field> camposEncontrados = new ArrayList<>();
        for (Class classe : getClassesComHierarquiaAteObjetoFinal(pclasse, pObjetosFinal)) {
            for (Field cp : classe.getDeclaredFields()) {
                for (Class itf : cp.getType().getInterfaces()) {
                    if (itf.equals(pItf)) {
                        camposEncontrados.add(cp);
                    }
                }
            }
        }
        return camposEncontrados;
    }

    public static Class getClasseGenericaDaClasse(Class pObjetoPrincipal) {
        if (pObjetoPrincipal.getGenericSuperclass() instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) pObjetoPrincipal.getGenericSuperclass();
            Class<?> subClass = (Class<?>) pt.getActualTypeArguments()[0];
            return subClass;

        }
        return null;
    }

    public static Class getClasseGenericaDaClasseDoCampo(Field pObjetoPrincipal) {
        if (pObjetoPrincipal.getGenericType() instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) pObjetoPrincipal.getGenericType();
            Class<?> subClass = (Class<?>) pt.getActualTypeArguments()[0];
            return subClass;

        }
        return null;
    }

    public static InfoCampo getInfoCampo(Field pCampo) {
        return pCampo.getAnnotation(InfoCampo.class);
    }

    public static <T> T getAnotacaoEmEnum(Class pTipoAnotacao, Enum pEnum) {

        Class classe = pEnum.getClass();
        try {
            Field campo = classe.getDeclaredField(pEnum.toString());
            T infoObjeto = (T) campo.getAnnotation(pTipoAnotacao);
            return infoObjeto;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Impossível obter anotação de campo em enum", t);
            return null;
        }

    }

}
