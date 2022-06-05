/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaCampo;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salvioF
 */
public class MapaObjetosProjetoAtual {

    // Tabela contendo nome da classe, e classe
    private static final Map<String, Class> CLASSE_ENTIDADE_BY_NOME = new HashMap<>();
    private static boolean TODAS_CLASSES_CONFIGURADAS = false;
    private static final Map<Class, EstruturaDeEntidade> ESTRUTURA_BY_CLASSE = new HashMap<>();
    private static final Map<Class, String> VIEW_BY_CLASSE = new HashMap<>();
    private static final Map<Class, InfoSatusObjetoDoSistemaAtual> STATUS_OBJETO_BY_CLASSE = new HashMap<>();

    /**
     * Adiciona um objeto em mapa de Objetos
     *
     * @param pObjeto
     */
    public static final void adcionarObjeto(Class pObjeto) {
        if (!CLASSE_ENTIDADE_BY_NOME.containsKey(pObjeto.getSimpleName())) {
            CLASSE_ENTIDADE_BY_NOME.put(pObjeto.getSimpleName(), pObjeto);
        }
        //throw new UnsupportedOperationException("TODO Erro adiionando objeto novamente para melhorar qualidade do código");
    }

    /**
     *
     * Adiciona um objeto instanciado ao mapa de Objetos
     *
     * @param pObjeto
     */
    public static final void adcionarObjetoInstanciado(ItfBeanSimples pObjeto) {
        CLASSE_ENTIDADE_BY_NOME.put(pObjeto.getClass().getSimpleName(), pObjeto.getClass());
        //throw new UnsupportedOperationException("TODO Erro adiionando objeto novamente para melhorar qualidade do código");
    }

    private static Class getClasseDoObjetoByNome(String pNome, boolean pIgnorarErro) {
        if (pNome.contains(".")) {
            String[] lista = pNome.split("\\.");
            pNome = lista[lista.length - 1];
        }

        Class classe = CLASSE_ENTIDADE_BY_NOME.get(pNome);

        if (classe == null) {
            if (pIgnorarErro) {
                return null;
            }

            if (pNome.startsWith("Itf")) {
                return null;
            }
            throw new UnsupportedOperationException("O Objeto do sistema, representado pela classe" + pNome + "nao foi encontrada, certifique que o Objeto esteja devidamente cadastrado no sistema");
        }
        return classe;
    }

    /**
     *
     *
     *
     * @param pNome
     * @return
     */
    public static final Class getClasseDoObjetoByNome(String pNome) {
        return getClasseDoObjetoByNome(pNome, false);

    }

    public static final Class getClasseDoObjetoByNomeIgnorandoErro(String pNome) {
        return getClasseDoObjetoByNome(pNome, true);
    }

    public static final void configuraraTodasAsClasses(List<Class> entidades) {
        try {
            if (!TODAS_CLASSES_CONFIGURADAS) {
                for (Class entidade : entidades) {
                    System.out.println("Configurando campos de:" + entidade.getSimpleName());

                    CLASSE_ENTIDADE_BY_NOME.put(entidade.getSimpleName(), entidade);
                    MapaObjetosProjetoAtual.adcionarObjeto(entidade);

                }
            } else {
                throw new UnsupportedOperationException("As classes já foram configuras");
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ouve um erro configurando todas as classes", t);
            TODAS_CLASSES_CONFIGURADAS = false;
        }

        TODAS_CLASSES_CONFIGURADAS = true;
    }

    private static EstruturaDeEntidade makeEstrutura(Class pClasse) {
        EstruturaDeEntidade novaEstrutura = new EstruturaDeEntidade();
        Class classeEtrutura = pClasse;
        InfoObjetoSB infoClasse = UtilSBCoreReflexaoObjeto.getInfoClasseObjeto(classeEtrutura);

        novaEstrutura.setNomeEntidade(classeEtrutura.getSimpleName());
        novaEstrutura.setIcone(infoClasse.icone());
        novaEstrutura.setPlural(infoClasse.plural());
        novaEstrutura.setDescricao(infoClasse.descricao());
        novaEstrutura.setTags(Lists.newArrayList(infoClasse.tags()));

        UtilSBCoreReflexao.getCamposRecursivodaClasseAteConterNomeObjetoFinal(pClasse, "Entidade", "ItemGenerico").forEach((campo) -> {
            try {
                novaEstrutura.adicionarCampo(campo);
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro validando estrutura de campo de entidade" + campo.getName() + " em " + novaEstrutura.getNomeEntidade(), t);
            }
        });
        return novaEstrutura;
    }

    public static final EstruturaDeEntidade getEstruturaObjeto(Class pClasse) {
        return getEstruturaObjeto(pClasse, false);
    }

    public static final EstruturaDeEntidade getEstruturaObjeto(Class pClasse, boolean pIgnorarEstruturaEmMemoria) {
        EstruturaDeEntidade estrutura = ESTRUTURA_BY_CLASSE.get(pClasse);
        if (pIgnorarEstruturaEmMemoria) {
            estrutura = makeEstrutura(pClasse);
            return estrutura;
        }

        if (estrutura == null) {
            estrutura = makeEstrutura(pClasse);
            ESTRUTURA_BY_CLASSE.put(pClasse, estrutura);
        }
        return estrutura;
    }

    public static final List<EstruturaDeEntidade> getListaTodosEstruturaObjeto() {
        for (Class classe : CLASSE_ENTIDADE_BY_NOME.values()) {
            if (ESTRUTURA_BY_CLASSE.get(classe) == null) {
                getEstruturaObjeto(classe);
            }
        }
        return Lists.newArrayList(ESTRUTURA_BY_CLASSE.values());
    }

    public static final EstruturaCampo getEstruturaCampoPorCaminhoCompleto(String pCaminho) {

        try {
            String[] partes = pCaminho.split("\\.");
            EstruturaDeEntidade estruturaObj = null;
            final int ULTIMOINDICE = partes.length - 1;
            for (int i = 0; i < partes.length; i++) {

                switch (i) {
                    case 0:
                        if (UtilSBCoreStringValidador.isPrimeiraLetraMaiusculaSegundaMinuscula(partes[i])) {
                            estruturaObj = MapaObjetosProjetoAtual.getEstruturaObjeto(partes[i]);
                        } else {
                            throw new UnsupportedOperationException("Você precisa especificar um caminho completo incluindo a classe com a primeira letra maiúscula para obter as propriedades do campo");
                        }
                        break;
                    default:
                        String proximoCampo = partes[i].replace("[]", "");
                        if (i == ULTIMOINDICE) {
                            return estruturaObj.getCampoByNomeDeclarado(proximoCampo);
                        } else {

                            EstruturaCampo cpTransient = estruturaObj.getCampoByNomeDeclarado(proximoCampo);
                            String nomeObjCampo = cpTransient.getClasseCampoDeclaradoOuTipoLista();
                            estruturaObj = MapaObjetosProjetoAtual.getEstruturaObjeto(nomeObjCampo);
                            if (cpTransient == null) {
                                throw new UnsupportedOperationException("subCampo do Atributo não encontrado:" + partes[i]);

                            }
                        }
                }
            }

            return null;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo estrutura de campo por caminho completo:" + pCaminho, t);
            return null;
        }

    }

    public static final EstruturaDeEntidade getEstruturaObjeto(String pNomeClasse) {
        Class classe = getClasseDoObjetoByNome(pNomeClasse);
        EstruturaDeEntidade estrutura = ESTRUTURA_BY_CLASSE.get(classe);
        if (estrutura == null) {
            estrutura = makeEstrutura(classe);
            ESTRUTURA_BY_CLASSE.put(classe, estrutura);
        }
        return estrutura;
    }

    public static final String getVisualizacaoDoObjeto(Class pClasseDoObjeto) {
        try {
            String formulario = VIEW_BY_CLASSE.get(pClasseDoObjeto);
            if (formulario == null) {
                formulario = SBCore.getCentralVisualizacao().getCaminhoXhtmlItemCard(pClasseDoObjeto);
                VIEW_BY_CLASSE.put(pClasseDoObjeto, formulario);
                if (formulario == null) {
                    throw new UnsupportedOperationException("View do objeto não pode ser determinada para" + pClasseDoObjeto);
                }
            }
            return formulario;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro definindo visualização do objeto", t);
            return "ErroAoGerarVisualizacaoDoObjeto";
        }

    }

    public List<String> getOpcoesDeObjetos(String pParamentro) {
        List<String> lista = new ArrayList<>();
        if (pParamentro == null || pParamentro.length() < 3) {
            return lista;
        }
        for (String nome : CLASSE_ENTIDADE_BY_NOME.keySet()) {
            if (nome.startsWith(nome)) {
                lista.add(nome);
            }
        }
        return lista;
    }

    public static InfoSatusObjetoDoSistemaAtual getStatusObjeto(Class pClasse) {
        try {
            InfoSatusObjetoDoSistemaAtual status = STATUS_OBJETO_BY_CLASSE.get(pClasse);

            if (status == null) {
                EstruturaDeEntidade etrutura = getEstruturaObjeto(pClasse);
                if (etrutura != null) {
                    STATUS_OBJETO_BY_CLASSE.put(pClasse, new InfoSatusObjetoDoSistemaAtual(etrutura, pClasse));
                    status = STATUS_OBJETO_BY_CLASSE.get(pClasse);
                    return status;
                }

            }
            if (status == null) {
                throw new UnsupportedOperationException("Impossível oter o Status do Objeto" + pClasse.getSimpleName());
            }
            if (status.getDataUltimaAtualizacao() == null || (status.getDataUltimaAtualizacao().getTime() + 60L * 1000 * 24 > new Date().getTime())) {
                SBCore.getCentralFonteDeDados().atualizarInformacoesDeObjeto(status);
            }
            return status;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao obter Status do Objeto" + pClasse.getSimpleName(), t);
            return null;
        }

    }

    public static boolean isObjetosConfigurados() {
        return TODAS_CLASSES_CONFIGURADAS;
    }
}
