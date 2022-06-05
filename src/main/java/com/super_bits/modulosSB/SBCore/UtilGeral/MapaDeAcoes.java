/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilFabricaDeAcoesBasico;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salvioF
 */
public class MapaDeAcoes {

    private final Map<String, ItfAcaoDoSistema> ACAO_BY_NOME_UNICO = new HashMap<>();
    private final Map<Integer, ItfAcaoDoSistema> ACAO_BY_ID = new HashMap<>();
    private final Map<ItfFabricaAcoes, ItfAcaoDoSistema> ACAO_BY_ENUM = new HashMap<>();
    private final Map<Class, List<ItfAcaoDoSistema>> ACOES_BY_CLASSE = new HashMap<>();
    private final Map<ItfModuloAcaoSistema, List<ItfAcaoDoSistema>> ACOES_BY_MODULO = new HashMap<>();
    private final Map<ItfModuloAcaoSistema, List<ItfAcaoDoSistema>> ACOES_MANAGED_BEAN_BY_MODULO = new HashMap<>();
    private final Map<ItfAcaoGerenciarEntidade, List<ItfAcaoSecundaria>> SUBACOES_BY_ACAO_GERENCIAR_MB = new HashMap<>();
    private final Map<String, List<ItfAcaoDoSistema>> ACOES_BY_DOMINIO = new HashMap<>();
    private final Map<String, ItfAcaoFormulario> ACAO_BY_XHTML_FORM = new HashMap<>();

    /// cria mapeamentos relacionais dos Objetos todos os (OneToMany) Ex: adiciona as açoes do modulo no objeto Modulo
    private void buildRelacoes() {

        ACOES_BY_MODULO.keySet().stream().filter((modulo)
                -> (modulo.getAcoes().isEmpty()))//Todos os modulos encontrados que a ação for vazia
                .forEach((moduloEncontradoSemAcao) -> {
                    //Adiciona todas as ações Do Modulo
                    moduloEncontradoSemAcao.getAcoes().addAll(ACOES_BY_MODULO.get(moduloEncontradoSemAcao));
                });

        SUBACOES_BY_ACAO_GERENCIAR_MB.keySet().stream().filter((acao) -> (acao.getAcoesVinculadas().isEmpty())).forEach((acao) -> {
            acao.setAcoesVinculadas(SUBACOES_BY_ACAO_GERENCIAR_MB.get(acao));
        });

    }

    public MapaDeAcoes(Class<? extends ItfFabricaAcoes>[] fabricas) {
        if (SBCore.getFabricasDeAcaoDoSistema() == null) {
            throw new UnsupportedOperationException("O mapa de ações não pode ser definido, pos as Fabricas de ações do core não foram definidas");
        }
        try {
            for (Class fabrica : fabricas) {
                try {
                    for (Object objAcao : fabrica.getEnumConstants()) {
                        ItfFabricaAcoes fabricaAcao = (ItfFabricaAcoes) objAcao;

                        try {
                            ItfAcaoDoSistema acao = fabricaAcao.getRegistro();

                            List<ItfAcaoDoSistema> acoesDoModulo = ACOES_BY_MODULO.get(acao.getModulo());
                            List<ItfAcaoDoSistema> acoesManagedBeansDoModulo = ACOES_MANAGED_BEAN_BY_MODULO.get(acao.getModulo());
                            UtilFabricaDeAcoesBasico.validaIntegridadeAcaoDoSistema(acao);
                            /// ADICIONANDO MAPAS SIMPLES
                            ACAO_BY_NOME_UNICO.put(acao.getNomeUnico(), acao);
                            ACAO_BY_ENUM.put(fabricaAcao, acao);
                            ACAO_BY_ID.put(UtilSBController.gerarIDAcaoDoSistema(acao.getEnumAcaoDoSistema()), acao);
                            if (acao.isUmaAcaoFormulario()) {
                                ACAO_BY_XHTML_FORM.put(acao.getComoFormulario().getXhtml(), acao.getComoFormulario());
                            }
                            if (acoesDoModulo == null) {
                                acoesDoModulo = new ArrayList();
                                acoesManagedBeansDoModulo = new ArrayList<>();
                                ACOES_BY_MODULO.put(acao.getModulo(), acoesDoModulo);
                                ACOES_MANAGED_BEAN_BY_MODULO.put(acao.getModulo(), acoesManagedBeansDoModulo);
                            }

                            acoesDoModulo.add(acao);
                            if (acao.isUmaAcaoGestaoDominio()) {
                                acoesManagedBeansDoModulo.add(acao);

                            } else if (acao.isTemAcaoPrincipal()) {
                                ItfAcaoGerenciarEntidade acaoprincipal = (ItfAcaoGerenciarEntidade) ACAO_BY_ENUM.get(((ItfAcaoSecundaria) acao).getAcaoPrincipal().getEnumAcaoDoSistema());
                                if (acaoprincipal == null) {
                                    throw new UnsupportedOperationException(
                                            "A acaoPrincipal: " + ((ItfAcaoSecundaria) acao).getAcaoPrincipal().getNomeUnico() + "deve ser declarada antes de " + acao.getNomeUnico() + " no enum (Coloque as açoes _MB na frente das subAções, "
                                            + "e certifique que o dominio e o inicio do nome da ação estão sendo coincidentes entre ações e subações)");
                                }
                                if (SUBACOES_BY_ACAO_GERENCIAR_MB.get(((ItfAcaoSecundaria) acao).getAcaoPrincipal()) == null) {
                                    List<ItfAcaoSecundaria> subacoesDomodulo = new ArrayList();
                                    SUBACOES_BY_ACAO_GERENCIAR_MB.put(acaoprincipal, subacoesDomodulo);

                                }

                                SUBACOES_BY_ACAO_GERENCIAR_MB.get(acaoprincipal).add((ItfAcaoSecundaria) acao);
                            }

                            if (ACOES_BY_DOMINIO.get(acao.getNomeDominio()) == null) {
                                List<ItfAcaoDoSistema> acoesporDominio = new ArrayList<>();
                                ACOES_BY_DOMINIO.put(acao.getNomeDominio(), acoesporDominio);

                            }
                            ACOES_BY_DOMINIO.get(acao.getNomeDominio()).add(acao);

                            if (acao.isUmaAcaoDeEntidade()) {
                                ItfAcaoEntidade acaodeEntidade = (ItfAcaoEntidade) acao;
                                Class classeRelacionada = acaodeEntidade.getClasseRelacionada();
                                List<ItfAcaoDoSistema> acoesDaEntidade = ACOES_BY_CLASSE.get(classeRelacionada);
                                if (acoesDaEntidade == null) {
                                    acoesDaEntidade = new ArrayList<>();
                                    ACOES_BY_CLASSE.put(classeRelacionada, acoesDaEntidade);

                                }
                                acoesDaEntidade.add(acao);

                            }

                        } catch (Throwable t) {
                            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro configurando ação do sistema:" + fabrica, t);
                        }

                    }
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro configurando ação do sistema, erro na fabrica" + fabrica, t);
                }

            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro configurando ação do sistema:" + fabricas, t);
        }
        buildRelacoes();
    }

    private void limparTudo() {
        ACAO_BY_NOME_UNICO.clear();
        ACAO_BY_ENUM.clear();
        ACOES_BY_CLASSE.clear();
        ACOES_BY_MODULO.clear();
        ACOES_MANAGED_BEAN_BY_MODULO.clear();
        SUBACOES_BY_ACAO_GERENCIAR_MB.clear();
        ACOES_BY_DOMINIO.clear();
    }

    /**
     *
     * Retorna todas as ações Referentes a Entidade
     *
     *
     * @param pEntidade Entidade referencia
     * @return Todas as ações da Entidade
     */
    public List<ItfAcaoDoSistema> getAcoesByEntidade(Class pEntidade) {
        return ACOES_BY_CLASSE.get(pEntidade);
    }

    /**
     *
     * Retorna todas as ações Referentes a Entidade
     *
     *
     * @param pEntidade Entidade referencia
     * @return Todas as ações da Entidade
     */
    public List<ItfAcaoGerenciarEntidade> getAcoesDeGestaoByEntidade(Class pEntidade) {
        List<ItfAcaoDoSistema> acoes = ACOES_BY_CLASSE.get(pEntidade);
        List<ItfAcaoGerenciarEntidade> acoesDeGestao = new ArrayList<>();
        for (ItfAcaoDoSistema acao : acoes) {
            if (acao.isUmaAcaoGestaoDominio()) {
                acoesDeGestao.add((ItfAcaoGerenciarEntidade) acao);
            }
        }
        return acoesDeGestao;
    }

    /**
     *
     * Um domínio corresponde a primeira parte do enum da ação antes das chaves
     * principais de identificação
     *
     *
     * @param pDominio O domimio referencia para seleção
     * @param modulo O módulo referencia para seleção
     * @return Todas as ações que possuem o dominio enviado
     */
    public List<ItfAcaoDoSistema> getAcoesByDominioEModulo(String pDominio, ItfModuloAcaoSistema modulo) {
        List<ItfAcaoDoSistema> lista = new ArrayList<>();
        for (ItfAcaoDoSistema acao : ACOES_BY_DOMINIO.get(pDominio)) {
            if (acao.getModulo().equals(acao.getModulo())) {
                lista.add(acao);
            }
        }
        return lista;

    }

    public List<ItfAcaoGerenciarEntidade> getAcoesGestaoByModulo(ItfModuloAcaoSistema pModulo) {
        return (List) ACOES_MANAGED_BEAN_BY_MODULO.get(pModulo);
    }

    /**
     *
     * Retorna todas as ações de Controller a partir da referencia de uma
     * entidade (que implementam alguma alteração no sistema)
     *
     * @param pEntidade A entidade referenciada
     * @return todas as ações controller da entidade
     */
    public List<ItfAcaoController> getAcoesControllersByEntidade(Class pEntidade) {
        List<ItfAcaoController> lista = new ArrayList<>();
        for (ItfAcaoDoSistema acao : ACOES_BY_CLASSE.get(pEntidade)) {
            if (acao.isUmaAcaoController()) {
                lista.add((ItfAcaoController) acao);
            }
        }
        return lista;
    }

    /**
     *
     * Retorna todas as ações referentes a uma entidade que são de determinado
     * módulo
     *
     * @param pEntidade Entidade Referenciada
     * @param pModulo T Modulo Referencia
     * @return Todas as ações do tipo controller que são da entidade e do modulo
     * referenciados
     */
    public List<ItfAcaoController> getAcoesControllerByEntidadeEModulo(Class pEntidade, ItfModuloAcaoSistema pModulo) {
        List<ItfAcaoController> lista = new ArrayList<>();
        for (ItfAcaoDoSistema acao : ACOES_BY_CLASSE.get(pEntidade)) {
            if (acao.getModulo().equals(pModulo)) {
                lista.add((ItfAcaoController) acao);
            }
        }
        return lista;
    }

    /**
     *
     * Retorna todos os formulários de listagem da entidade por modulo
     *
     * @param pEntidade A Entidade referenciada
     * @param pModulo Modulo Referenciado
     * @return Todos foromularios de listagem referenciados pela entidade e
     * modulo
     */
    public List<ItfAcaoFormulario> getAcoesListagemByEntidadeEModulo(Class pEntidade, ItfModuloAcaoSistema pModulo) {
        List<ItfAcaoFormulario> lista = new ArrayList<>();
        for (ItfAcaoDoSistema acao : ACOES_BY_CLASSE.get(pEntidade)) {
            if (acao.isUmaAcaoFormulario()) {
                if (acao.getTipoAcaoGenerica() == FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR) {
                    lista.add((ItfAcaoFormulario) acao);
                }
            }
        }
        return lista;
    }

    /**
     *
     * Retorna a ação a partir de uma Fabrica
     *
     * @param pFabAcao Fabrica refencia
     * @return Ação do Sistema instanciada
     */
    public ItfAcaoDoSistema getAcaoDoSistema(ItfFabricaAcoes pFabAcao) {
        return ACAO_BY_ENUM.get(pFabAcao);
    }

    /**
     *
     * Retorna a ação a partir do nome unico
     *
     * @param pFabAcao Fabrica refencia
     * @return Ação do Sistema instanciada
     */
    public ItfAcaoDoSistema getAcaoDoSistemaByNomeUnico(String pFabAcao) {
        return ACAO_BY_NOME_UNICO.get(pFabAcao);
    }

    /**
     *
     * REtorna uma ação de gestão de entidade a partir de uma fabrica <br>
     * caso uma ação
     *
     * @param pFabAcao
     * @return Ação de
     */
    public ItfAcaoEntidade getAcaoDeEntidade(ItfFabricaAcoes pFabAcao) {

        try {
            return (ItfAcaoEntidade) ACAO_BY_ENUM.get(pFabAcao);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação de entidade", t);
        }
        return null;

    }

    public ItfAcaoFormularioEntidade getAcaoEntidadeFormulario(ItfFabricaAcoes pFabAcao) {
        try {
            return (ItfAcaoFormularioEntidade) ACAO_BY_ENUM.get(pFabAcao);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação de Entidade Formulario", t);
        }
        return null;
    }

    public ItfAcaoControllerEntidade getAcaoEntidadeController(ItfFabricaAcoes pFabAcao) {
        try {
            return (ItfAcaoControllerEntidade) ACAO_BY_ENUM.get(pFabAcao);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação de entidade", t);
        }
        return null;
    }

    public ItfAcaoController getAcaoController(ItfFabricaAcoes pFabAcao) {
        try {
            return (ItfAcaoController) ACAO_BY_ENUM.get(pFabAcao);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação de entidade", t);
        }
        return null;
    }

    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade(ItfFabricaAcoes pFabAcao) {
        try {
            return (ItfAcaoGerenciarEntidade) ACAO_BY_ENUM.get(pFabAcao);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo ação de entidade", t);
        }
        return null;
    }

    public ItfAcaoFormulario getAcaoFormularioPorXhtml(String pForm) {
        return ACAO_BY_XHTML_FORM.get(pForm);
    }

    public List<ItfModuloAcaoSistema> getModulos() {

        List<ItfModuloAcaoSistema> modulosEncontrados = new ArrayList<>();
        modulosEncontrados.addAll(ACOES_BY_MODULO.keySet());

        return modulosEncontrados;

    }

    public List<ItfAcaoGerenciarEntidade> getAcoesDeGestao() {
        return new ArrayList<>(SUBACOES_BY_ACAO_GERENCIAR_MB.keySet());
    }

    public List<ItfAcaoDoSistema> getListaTodasAcoes() {

        return new ArrayList<>(ACAO_BY_ENUM.values());
    }

    public ItfAcaoDoSistema getAcaoByIdAcao(int id) {
        return ACAO_BY_ID.get(id);
    }

}
