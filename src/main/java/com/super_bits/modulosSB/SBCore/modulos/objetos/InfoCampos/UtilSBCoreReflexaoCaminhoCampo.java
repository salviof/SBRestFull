/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfValidacao;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroCaminhoCampoNaoExiste;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoExibicaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoListagemExibicaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_ORIGEM_VALOR_CAMPO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorMultiplo.ItfselecaoListaComOrigem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorUnicoObjeto.ItfSelecaoObjetoDeUmaLista;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoInstanciadoSeparador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoNaoImplementado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemGenerico;
import java.beans.Transient;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Salvio
 */
public class UtilSBCoreReflexaoCaminhoCampo {

    public static CampoNaoImplementado CAMPO_NAO_IMPLEMENTADO = new CampoNaoImplementado();
    private static final Pattern REGEX_REGISTRO_DA_LISTA = Pattern.compile("\\[(\\d+)\\]");
    /**
     *
     * A TAG SEPARADOR É A TAG QUE IDENTIFICA UM NOME DE CAMPO COMO SEPARADOR
     *
     * A SINTAXE PARA UM CAMPO SEPARADOR É [SEPARADOR:Nome do Separador]
     *
     */
    private static final String TAG_SEPARADOR = "[separador";

    /**
     * Caso o caminho contenha o nome da classe, retorna apenas o campo, <br>
     * ->Caso a string não corresponda a um nome de campo, retorna nulo
     *
     * @param pCaminho ex: BeanExemplo.nome
     * @return exemplo: ^ ^ ^-> retornaria: nome
     */
    public static final String getCampoSemNomeClasse(String pCaminho) {
        if (pCaminho == null) {
            return null;
        }
        String[] nomes = pCaminho.split("\\.");
        String resposta = "";
        if (UtilSBCoreStringValidador.isPrimeiraLetraMaiusculaSegundaMinuscula(nomes[0])) {
            resposta = nomes[0];
            return pCaminho.replace(resposta + ".", "");
        } else {
            return pCaminho;
        }

    }

    /**
     *
     * Retorna a classe principal referente ao caminho para um campo
     *
     * A classe principal é a primeira parte de um caminho completo para um
     * campo, exemplo: Cliente.endereco.bairro, (o Cliente é o endereço
     * principal)
     *
     * @param pNome
     * @return
     */
    public static Class getClassePrincipalPorNome(String pNome) {

        String[] nomes = pNome.split("\\.");
        System.out.print(".");
        if (UtilSBCoreStringValidador.isPrimeiraLetraMaiuscula(pNome)) {
            return MapaObjetosProjetoAtual.getClasseDoObjetoByNome(nomes[0]);
        } else {
            throw new UnsupportedOperationException("O nome da classe principal não pode ser descoberto, se não for enviado na string, a string enviada foi" + pNome);
        }

    }

    public static Field getFieldByNomeCompletoCaminhoEClasse(String pCamihoCampo, Class pClasse) throws ErroCaminhoCampoNaoExiste {
        MapaObjetosProjetoAtual.adcionarObjeto(pClasse);

        return getFieldByNomeCompletoCaminho(pCamihoCampo);
    }

    public static Class getClasseByNome(String pNome) {
        Class classe = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pNome);

        if (classe == null) {
            throw new UnsupportedOperationException("A classe não pode ser encontrada pelo nome:" + pNome + " não foi encontrada o caminho completo enviado foi ");
        }
        return classe;
    }

    public static Field getFieldByNomeCompletoCaminho(String pCamihoCampo) throws ErroCaminhoCampoNaoExiste {

        if (pCamihoCampo == null) {
            throw new UnsupportedOperationException("Tentativa de obter field Por String com parametro nulo");
        }

        if (isUmCampoSeparador(pCamihoCampo)) {
            return null;
        }

        String[] partes = pCamihoCampo.split("\\.");

        String nomeClassePrincipal = partes[0];
        Class classe = null;
        try {
            classe = getClasseByNome(nomeClassePrincipal);
        } catch (Throwable t) {
            throw new UnsupportedOperationException("A classe não pode ser encontrada pelo caminho completo " + pCamihoCampo);
        }
        int i = 0;
        for (String parte : partes) {

            if (i > 0) {

                Field campo = null;
                List<Class> classesDaEntidade = UtilSBCoreReflexao.getClasseESubclassesAteClasseBaseDeEntidade(classe);
                for (Class classeAtual : classesDaEntidade) {
                    try {
                        campo = classeAtual.getDeclaredField(UtilSBCoreReflexaoCaminhoCampo.getListaSemColchete(parte));
                        if (campo != null) {
                            break;
                        }
                    } catch (Throwable t) {

                    }

                }
                if (campo == null) {
                    throw new ErroCaminhoCampoNaoExiste(classe, parte);
                }
                if (i == partes.length - 1) {
                    return campo;
                } else {
                    classe = UtilSBCoreReflexaoAtributoDeObjeto.getClassePrincipalDoCampo(campo);

                }

            }
            i++;
        }
        throw new UnsupportedOperationException("Erro obtendo field a partir do caminho " + pCamihoCampo);

    }

    public static Field getFieldByCaminho(CaminhoCampoReflexao pCaminho) throws ErroCaminhoCampoNaoExiste {
        return getFieldByNomeCompletoCaminho(pCaminho.getCaminhoCompletoString());
    }

    public static boolean isUmCampoSeparador(String pNomeSeparador) {
        return pNomeSeparador.contains(TAG_SEPARADOR);
    }

    /**
     *
     *
     * REtorna o campo Pelo tipo de campo na anotação (Verificar importancia de
     * retornar campo do hibernate relacionadas a campos do framework)
     *
     * @param pClasse Classe referenciada
     * @param pTipoCampo Tipo de campo refereniado
     * @return Primeiro Field (pacote Reflection) que tenha a anotação deste
     * tipo de campo
     */
    public static Field getSBCampobyTipoCampo(Class pClasse, FabTipoAtributoObjeto pTipoCampo) {
        try {
            Class classe = pClasse;

            while (!isClasseBasicaSB(classe)) {

                Field[] fields = classe.getDeclaredFields();

                for (Field field : fields) {
                    InfoCampo annotationField = field.getAnnotation(InfoCampo.class);
                    if (annotationField != null) {

                        if (annotationField.tipo().equals(pTipoCampo)) {
                            return field;
                        }

                    }
                    try {
                        if (pTipoCampo.equals(FabTipoAtributoObjeto.ID)) {
                            if (field.getAnnotation(Id.class) != null) {
                                return field;
                            }
                        }
                    } catch (Throwable t) {
                        //TODO ignora erro caso o pacote JPA não faça parte do projeoo
                    }

                }
                classe = classe.getSuperclass();
            }
            //   throw new UnsupportedOperationException("O campo " + pTipoCampo + " não foi encontrado na classe" + pClasse.getSimpleName());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro localizando campo do tipo" + pTipoCampo + "na classe" + pClasse, t);
        }

        return null;
    }

    public static String getNomeCampobyTipoCampo(Class pClasse, FabTipoAtributoObjeto pTipoCampo) {

        Field campo = getSBCampobyTipoCampo(pClasse, pTipoCampo);
        if (campo == null) {
            String nomeClasse = "Classe nula";
            if (pClasse != null) {
                nomeClasse = pClasse.getSimpleName();
            }
            throw new UnsupportedOperationException("Não foi possível encontrar um campo do tipo " + pTipoCampo + " na classe " + nomeClasse);
        }
        return campo.getName();

    }

    public static boolean isTemTipoCampoNoObjeto(Class pClasse, FabTipoAtributoObjeto pTipoCampo) {
        try {
            Field campo = getSBCampobyTipoCampo(pClasse, pTipoCampo);
            return campo != null;
        } catch (Throwable t) {
            return false;
        }

    }

    /**
     *
     * Retorna a quantidade de subcampos até atingir o campo pelo caminho
     *
     * ex: Cliente.localizacao.bairro, retornaria 2 (2 caminhos percorridos para
     * chegar ao valor)
     *
     *
     * @param pCaminhoCompleto
     * @return
     */
    public static int getQuantidadeSubCampos(String pCaminhoCompleto) {

        String[] partes = pCaminhoCompleto.split("\\.");

        boolean temNomeDaClasse = UtilSBCoreStringValidador.isPrimeiraLetraMaiusculaSegundaMinuscula(partes[0]);

        if (temNomeDaClasse) {

            return partes.length - 1;
        } else {
            return partes.length;
        }

    }

    /**
     *
     * Retorna um campo do tipo Separador
     *
     *
     * @deprecated Utilize GetCaminho por
     * @see
     * UtilSBCoreReflexaoCaminhoCampo#getCaminhoCAmpoByString(java.lang.String)
     *
     * @param pSeparador Uma string no padrão separador: [separador:nome
     * separador]
     * @return Um campo Instanciado, do tipo separador
     */
    public static CampoInstanciadoSeparador getCampoSeparador(String pSeparador) {
        if (!isUmCampoSeparador(pSeparador)) {
            throw new UnsupportedOperationException("A tag" + TAG_SEPARADOR + " não foi encontrada");
        }

        return new CampoInstanciadoSeparador(getNomeGrupoPorStrSeparador(pSeparador));

    }

    public static String getSEgundoCampoDoCaminho(String pCaminhoCompleto) {
        String[] partes = pCaminhoCompleto.split("\\.");

        try {
            int i = 0;
            for (String parte : partes) {
                if (!UtilSBCoreStringValidador.isPrimeiraLetraMaiusculaSegundaMinuscula(parte)) {
                    if (i >= 1) {
                        return parte;
                    }
                    i++;
                }
            }
            throw new UnsupportedOperationException("Não foi possível determinar a primeira parte do caminho para o campo " + pCaminhoCompleto);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "", t);
        }
        return null;

    }

    /**
     *
     *
     *
     * @param pCaminhoCompleto
     * @return
     */
    public static String getStrPrimeiroCampoDoCaminhoCampo(String pCaminhoCompleto) {

        String[] partes = pCaminhoCompleto.split("\\.");

        try {
            for (String parte : partes) {
                if (!UtilSBCoreStringValidador.isPrimeiraLetraMaiusculaSegundaMinuscula(parte)) {
                    return parte;
                }
            }
            throw new UnsupportedOperationException("Não foi possível determinar a primeira parte do caminho para o campo " + pCaminhoCompleto);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "", t);
        }
        return null;
    }

    /**
     *
     *
     * (Utilize exte método enviando apenas o caminho completo, ex:
     * BeanExemplo.novo)
     *
     * @param pCaminhoCompleto
     * @return
     */
    public static String getStrCaminhoCampoSemPrimeiroCampo(String pCaminhoCompleto) {
        String[] partes = pCaminhoCompleto.split("\\.");
        String caminhoParcial = "";
        int i = 0;
        try {
            for (String parte : partes) {
                if (!UtilSBCoreStringValidador.isPrimeiraLetraMaiusculaSegundaMinuscula(parte)) {
                    //(TODO: Verificação descesessária devido a verificação superior (Validar isto) )
                    if (i > 0) {
                        // caso não seja a primeira parte do caminho
                        if (caminhoParcial.length() > 0) {
                            caminhoParcial += "." + parte;
                            //caso seja a primeira parte do caminho
                        } else {
                            caminhoParcial = parte;

                        }
                    }
                    i++;
                } else {

                }

            }

            if (caminhoParcial.length() < 1) {
                throw new UnsupportedOperationException("Não foi possível determinar a primeira parte do caminho para o campo " + pCaminhoCompleto);
            }

            return caminhoParcial;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "", t);
        }
        return null;
    }

    public static String getStrCaminhoCampoAteCampoLista(String pCaminhoCompleto) {
        String[] partes = pCaminhoCompleto.split("\\.");
        if (!UtilSBCoreStringValidador.isPrimeiraLetraMaiusculaSegundaMinuscula(partes[0])) {
            throw new UnsupportedOperationException("O Metodo GetStrSemUltimoCampo foi projetado para funcionar com o nome do Bean inicial");
        }

        String caminhoParcial = "";
        int i = 0;
        try {
            for (String parte : partes) {

                // caso não seja a primeira parte do caminho
                if (caminhoParcial.length() > 0) {
                    caminhoParcial += "." + parte;
                    //caso seja a primeira parte do caminho
                } else {
                    caminhoParcial = parte;

                }
                if (caminhoParcial.contains("[]")) {
                    return caminhoParcial;
                }
                i++;

            }

            if (caminhoParcial.length() < 1) {
                throw new UnsupportedOperationException("Não foi possível determinar a primeira parte do caminho para o campo " + pCaminhoCompleto);
            }

            return caminhoParcial;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "", t);
        }
        return null;

    }

    /**
     *
     *
     *
     * @param pCaminhoCompleto
     * @return O caminho do campo sem o bean
     */
    public static String getStrCaminhoCampoSemUltimoCampo(String pCaminhoCompleto) {
        String[] partes = pCaminhoCompleto.split("\\.");
        String caminhoParcial = "";
        if (!UtilSBCoreStringValidador.isPrimeiraLetraMaiusculaSegundaMinuscula(partes[0])) {
            throw new UnsupportedOperationException("O Metodo GetStrSemUltimoCampo foi projetado para funcionar com o nome do Bean inicial, o valor " + partes[0] + "é incompatível");
        }
        int i = 0;
        try {
            for (String parte : partes) {
                if (!UtilSBCoreStringValidador.isPrimeiraLetraMaiusculaSegundaMinuscula(parte)) {

                    if (i < partes.length - 2) {

                        if (caminhoParcial.length() > 0) {

                            caminhoParcial += "." + parte;
                            //caso seja a primeira parte do caminho
                        } else {
                            caminhoParcial = parte;

                        }
                    }
                    i++;
                } else {

                }

            }

            return caminhoParcial;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "", t);
        }
        return null;
    }

    /**
     *
     * Com uma entidade instanciada, ele percorre todos os campos filhos de
     * entidade, até o nivel maximo informado
     *
     * ex: pNivelMaximo 3 de uma entidade ficticia chamada avó, poderia retornar
     * seguintes caminhos: avó.mãe.filho. e avó.mãe.amante <br>
     * No pNivelMaximo 4 retornaria todos os acimas mais:
     * avó.mãe.amante.filho_do_amante;
     *
     * @param entidade A entidade instanciada (os campos nulos não serão
     * enviados)
     * @param pNivelAtual O nível atual de pesquisa, este método se auto
     * executa, incrementando o nivel a cada momento adequado
     * @param pNivelMaximo O nivel máximo, (não pode passar de 7)
     * @param listarAnterior A lista que será preenxida utilizando o formidacel
     * recurso de passagem por referencia do Java
     * @param caminhoAnterior
     * @return True caso não aconteçam erros
     */
    public static boolean buildListaSubEntidadesPersistiveis(ItfBeanSimples entidade, int pNivelAtual, int pNivelMaximo, List<CaminhoCampoReflexao> listarAnterior, String caminhoAnterior) {
        try {
            if (pNivelMaximo > 7) {
                pNivelMaximo = 7;
            }
            if (pNivelAtual >= pNivelMaximo) {
                return true;
            }
            if (caminhoAnterior == null) {
                caminhoAnterior = entidade.getClass().getSimpleName();
            }
            List<ItfCaminhoCampo> entidadesVinculadas = entidade.getEntidadesVinculadas();
            for (ItfCaminhoCampo caminho : entidadesVinculadas) {

                //confirmação que o item foi instanciado
                Object itemEncontrado = entidade.getValorCampoByCaminhoCampo(caminho);
                if (itemEncontrado != null) {
                    if (caminho.isUmCampoListavel()) {
                        List<ItfBeanSimples> lista = (List) itemEncontrado;
                        int ii = 0;
                        // construindo a string do caminho, como este

                        for (ItfBeanSimples item : lista) {
                            String caminhoNovoCampo = caminho.getCaminhoSemNomeClasse().replaceAll("\\[]", "[" + ii + "]");
                            CaminhoCampoReflexao novoCaminho = new CaminhoCampoReflexao(caminhoAnterior + "." + caminhoNovoCampo);
                            ii++;
                            listarAnterior.add(novoCaminho);

                            buildListaSubEntidadesPersistiveis((ItfBeanSimples) item, pNivelAtual + 1, pNivelMaximo, listarAnterior, caminhoAnterior + "." + novoCaminho.getCaminhoSemNomeClasse());
                        }
                    } else {

                        CaminhoCampoReflexao novoCaminho = new CaminhoCampoReflexao(caminhoAnterior + "." + caminho.getCaminhoSemNomeClasse());
                        listarAnterior.add(novoCaminho);
                        buildListaSubEntidadesPersistiveis((ItfBeanSimples) itemEncontrado, pNivelAtual + 1, pNivelMaximo, listarAnterior, caminhoAnterior + "." + caminho.getCaminhoSemNomeClasse());
                    }
                }

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo lista de entidades persistiveis", t);
            return false;
        }
        return true;

    }

    public static List<CaminhoCampoReflexao> getCamposDeEntidadeInstanciado(ItfBeanSimples pEntidade, int pQuantidadeSubniveis) {

        pEntidade.getEntidadesVinculadas();
        List<CaminhoCampoReflexao> lista = new ArrayList<>();
        buildListaSubEntidadesPersistiveis(pEntidade, 0, pQuantidadeSubniveis, lista, null);
        lista = Lists.reverse(lista);
        return lista;

    }

    public static TIPO_ORIGEM_VALOR_CAMPO getTipoCampoLista(String pNomeCampo) {
        if (pNomeCampo.contains("[]")) {
            return TIPO_ORIGEM_VALOR_CAMPO.VALORES_COM_LISTA;
        }

        Matcher m = REGEX_REGISTRO_DA_LISTA.matcher(pNomeCampo);
        if (m.find()) {
            return TIPO_ORIGEM_VALOR_CAMPO.REGISTRO_ESTATICO_DA_LISTA;
        }
        return TIPO_ORIGEM_VALOR_CAMPO.VALOR_COM_LISTA;

    }

    public static boolean isUmaStringNomeadaComoLista(String pLista) {

        String[] campos = pLista.split("\\.");
        String campo = campos[campos.length - 1];
        if (campo.contains("[]")) {
            return true;
        } else {
            final Matcher matcher = REGEX_REGISTRO_DA_LISTA.matcher(campo);
            return matcher.find();
        }
    }

    public static int getIdCampoDaLista(String pNomeDaLista) {
        String[] campos = pNomeDaLista.split("\\.");
        String campo = campos[campos.length - 1];
        final Matcher matcher = REGEX_REGISTRO_DA_LISTA.matcher(campo);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return -1;

    }

    public static String getListaSemColchete(String parteNome) {
        return getListaSemIndice(parteNome).replaceAll("\\[]", "");
    }

    public static String getListaSemIndice(String parteNome) {
        final Matcher matcher = REGEX_REGISTRO_DA_LISTA.matcher(parteNome);
        String[] campos = parteNome.split("\\.");
        if (campos.length > 1) {
            throw new UnsupportedOperationException("Este método não suporta subCampos o valor enviado foi" + parteNome);
        }
        if (matcher.find()) {

            String conteudoComColchete = "\\[" + matcher.group(1) + "\\]";
            return parteNome.replaceAll(conteudoComColchete, "[]");
        } else {
            return parteNome;
            // throw new UnsupportedOperationException("O campo não parece ser uma lista com indice" + parteNome);
        }

    }

    /**
     *
     * Retorna todas as entidades diretamente declaradas na entidade,<br>
     * que não seja nula es estaja anotadas com @ManyToOne, @OneToMany, ou
     *
     * @ManyToMany
     *
     * @param pClasse
     * @return
     */
    public static Map<String, CaminhoCampoReflexao> getCamposComSubCamposDaClasse(Class pClasse) {

        if (pClasse == null) {
            throw new UnsupportedOperationException("Tentativa de obter campos Anotados com manyToOne sem enviar a classe de referencia nula");
        }
        // TODO???? este método não alcança listas e calculos (Lembrando que ele é utilizado no persistir filhos, então deve existir as 2 opções
        Map<String, CaminhoCampoReflexao> lista = new HashMap<>();
        Class classeAtual = pClasse;

        while (!isClasseBasicaSB(classeAtual)) {

            if (classeAtual == null) {
                throw new UnsupportedOperationException("Impossível percorrrer classes superiores de" + pClasse.getSimpleName());
            }

            Field[] todoscampos = classeAtual.getDeclaredFields();

            List<Field> camposEntidade = Lists.newArrayList(todoscampos);

            //  Se chegou até aqui, você deve estar pensando, porque instanciar duas vezes esta lista? <br>
            //   O que é mais importante, um código legivel e debugavel ou um código rápido? o trabalho em equipe? ou o processaodor?
            // Separando lista de campos de entidade do restante
            for (Field campoEntidade : todoscampos) {
                if (campoEntidade.isAnnotationPresent(ManyToOne.class
                ) || campoEntidade.isAnnotationPresent(OneToMany.class)
                        || campoEntidade.isAnnotationPresent(ManyToMany.class)) {
                    if (campoEntidade.isAnnotationPresent(Transient.class)) {
                        // caso seja transiente, ignora
                        camposEntidade.remove(campoEntidade);
                    }

                } else {
                    //caso não contenha as anotações de chave extrangeira remove da lista
                    camposEntidade.remove(campoEntidade);
                }
            }
            // para cada campo do tipo entidade persistivel retorna o valor
            for (Field campoEntidade : camposEntidade) {
                String caminhoCampo = null;
                try {
                    caminhoCampo = pClasse.getSimpleName() + "." + campoEntidade.getName();
                    CaminhoCampoReflexao caminhoCR = new CaminhoCampoReflexao(caminhoCampo);
                    lista.put(caminhoCR.getCaminhoCompletoString(), caminhoCR);
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando Campo: " + caminhoCampo, t);
                    throw new UnsupportedOperationException("Impossivel determinar os campos com chave extrangeira da classe");
                }
            }
            classeAtual = classeAtual.getSuperclass();
        }

        return lista;
    }

    public static boolean isClasseBasicaSB(Class pClasse) {

        if (pClasse == null) {
            throw new UnsupportedOperationException("enviado null para verificação de classe Basica");

        }

        // todo Tratar EntidadeGenerica gerar trhow se Chegar no Object
        return (pClasse == ItemGenerico.class
                || pClasse == Object.class
                || pClasse.getSimpleName().startsWith("Entidade"));

    }

    /**
     *
     *
     *
     *
     * @param pClasse Classe onde a anotação será procurada
     * @param anotacao Anotação Referenciada
     * @return O primeiro Field( pacote Reflection) encontrado com esta anotação
     */
    public static Field getFieldByClasseAnotacao(Class pClasse, Class anotacao) {
        Class classe = pClasse;

        while (!isClasseBasicaSB(classe)) {
            Field[] fields = classe.getDeclaredFields();
            for (Field field : fields) {
                Object campoAnotado = field.getAnnotation(anotacao);
                if (campoAnotado != null) {
                    return field;
                }
            }
            classe = classe.getSuperclass();
        }
        return null;
    }

    /**
     *
     * Cria um mapeamento entre nome da classe de entidade, e a classe.
     *
     *
     * @deprecated Utilize GetCaminho por
     * @see
     * UtilSBCoreReflexaoCaminhoCampo#getCaminhoCAmpoByString(java.lang.String)
     * @param entidades a lista de entidades que a lista deve ser criada
     */
    public static void configurarTodasAsClasses(List<Class> entidades) {

        MapaObjetosProjetoAtual.configuraraTodasAsClasses(entidades);
    }

    /**
     *
     * Retorna uma lista de grupo de campos, separando os grupos a cada campo do
     * tipo separador, caso não tenha nenhum separador, cria um úníco grupo
     *
     *
     * @deprecated Utilize GetCaminho por
     * @see
     * UtilSBCoreReflexaoCaminhoCampo#getCaminhoCAmpoByString(java.lang.String)
     *
     * @param pCampos Campos listados
     * @return Grupo Uma lista de grupos com os campos
     */
    public static List<GrupoCampos> buildAgrupamentoCampos(List<CaminhoCampoReflexao> pCampos) {
        List<GrupoCampos> grupocampos = new ArrayList<>();
        if (pCampos != null) {
            if (!pCampos.isEmpty()) {
                GrupoCampos grupoatual = null;

                if (pCampos.get(0).isUmCampoSeparador()) {
                    grupoatual = new GrupoCampos(getNomeGrupoPorStrSeparador(pCampos.get(0).getCaminhoCompletoString()));
                } else {
                    grupoatual = new GrupoCampos("Informações Básicas");
                }
                int i = 0;
                for (CaminhoCampoReflexao campo : pCampos) {

                    if (UtilSBCoreReflexaoCaminhoCampo.isUmCampoSeparador(campo.getCaminhoCompletoString())
                            & i != 0) {

                        grupocampos.add(grupoatual);
                        grupoatual = new GrupoCampos(getNomeGrupoPorStrSeparador(campo.getCaminhoCompletoString()));
                    } else if (!isUmCampoSeparador(campo.getCaminhoCompletoString())) {

                        if (campo.isUmCampoListavel()) {
                            grupoatual.adicionarCampo(new CaminhoCampoListagemExibicaoFormulario(new CaminhoCampoExibicaoFormulario(campo, grupoatual.getNomeIdentificadorSlug())));
                        } else {
                            grupoatual.adicionarCampo(new CaminhoCampoExibicaoFormulario(campo, grupoatual.getNomeIdentificadorSlug()));
                        }

                    }

                    if (i == pCampos.size() - 1) {
                        grupocampos.add(grupoatual);
                    }
                    i++;

                }
            }
        }
        return grupocampos;
    }

    public static CaminhoCampoReflexao getCaminhoByStringRelativaEClasse(String pCaminho, Class pClase) throws ErroCaminhoCampoNaoExiste {

        try {
            if (isUmCampoSeparador(pCaminho)) {
                String caminhoCompleto = pClase.getSimpleName() + "." + pCaminho;
                return new CaminhoCampoReflexao(caminhoCompleto);
            }
            MapaObjetosProjetoAtual.adcionarObjeto(pClase);

            //Confgurando caminho completo
            String caminhoCompleto = pCaminho;
            if (!UtilSBCoreStringValidador.isPrimeiraLetraMaiusculaSegundaMinuscula(pCaminho)) {
                caminhoCompleto = pClase.getSimpleName() + "." + pCaminho;
            }

            return new CaminhoCampoReflexao(caminhoCompleto, pClase);

        } catch (ErroCaminhoCampoNaoExiste caminhoNaoExiste) {
            throw new ErroCaminhoCampoNaoExiste(pClase, pCaminho, caminhoNaoExiste);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo caminho do campo a partir de caminho relativo:-->" + pCaminho + "<-- na classe:-->" + pClase.getSimpleName(), t);
            return null;

        }

    }

    /**
     *
     *
     * Obteem o nome do grupo pela String do tipo Separador, ou seja:
     *
     * uma string com este valor [separador:Dados Simples], retorna Dados
     * Simmples
     *
     *
     * @deprecated Utilize GetCaminho por
     * @see
     * UtilSBCoreReflexaoCaminhoCampo#getCaminhoCAmpoByString(java.lang.String)
     *
     *
     * @param pSeparador String contendo campo Separador
     * @return
     */
    public static String getNomeGrupoPorStrSeparador(String pSeparador) {
        if (!isUmCampoSeparador(pSeparador)) {
            throw new UnsupportedOperationException("A string não parece ser referente a um separador, ->" + pSeparador);
        }

        String[] partes = pSeparador.split(":");
        if (partes.length < 2) {
            return null;
        }
        return partes[1].replace("]", "");

    }

    public static ItfselecaoListaComOrigem getSelecaoItens(final ItfCampoInstanciado pCampoInstanciado) {
        Class classeSelecaoObjeto = SBCore.getCentralFonteDeDados().getClasseSelecaoItens();
        try {
            Constructor c = classeSelecaoObjeto.getConstructor(ItfCampoInstanciado.class);
            ItfselecaoListaComOrigem objetoAPartirDeLista = (ItfselecaoListaComOrigem) c.newInstance(pCampoInstanciado);
            return objetoAPartirDeLista;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando Selecao de objeto de uma Lista", t);
            throw new UnsupportedOperationException("Erro ao instanciar campoSeltor de Item");
        }
    }

    public static ItfSelecaoObjetoDeUmaLista getSelecaoItem(final ItfCampoInstanciado pCampoInstanciado) {
        Class classeSelecaoObjeto = SBCore.getCentralFonteDeDados().getClasseSelecaoItem();
        try {
            Constructor c = classeSelecaoObjeto.getConstructor(ItfCampoInstanciado.class);
            ItfSelecaoObjetoDeUmaLista objetoAPartirDeLista = (ItfSelecaoObjetoDeUmaLista) c.newInstance(pCampoInstanciado);
            return objetoAPartirDeLista;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando Selecao de objeto de uma Lista", t);
            throw new UnsupportedOperationException("Erro ao instanciar campoSeltor de Item");
        }
    }

    public static List<String> gerarNomeCamposPorOrdemDeAcesso(String pNomeConcatenado) {
        try {
            String caminho = getCampoSemNomeClasse(pNomeConcatenado);
            String[] caminhos = caminho.split("\\.");
            return Lists.newArrayList(caminhos);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando lista de subcampos, exemplo: string 'Entidade.campoAvo.CamploPai.campoFilho' retornaria uma lista contendo [campoAvo, campoPai , campoFilho]", t);
            return null;
        }

    }

    public static ItfValidacao getValidadorDoCampoInstanciado(ItfCampoInstanciado pCampo) {
        try {

            EstruturaCampo est = MapaObjetosProjetoAtual.getEstruturaCampoPorCaminhoCompleto(pCampo.getObjetoDoAtributo().getClass().getSimpleName() + "." + pCampo.getNomeCamponaClasse());
            Class classeValidacao = est.getClasseValidacao();
            if (est.getClasseValidacao() == null) {

                return null;
            } else {
                return (ItfValidacao) classeValidacao.getConstructor(ItfCampoInstanciado.class).newInstance(pCampo);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo validador do campo" + pCampo, t);
            return null;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo validador do campo" + pCampo, t);
            return null;
        }
    }

}
