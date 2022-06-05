/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import java.util.List;
import java.util.stream.Collectors;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreReflexaoObjetoSimples;

/**
 *
 * @author desenvolvedor
 */
public abstract class MapaAcoesSistema {

    private static MapaDeAcoes mapaAcoes;

    private static boolean mapaCriado = false;

    public static void makeMapaAcoesSistema() {
        if (mapaCriado) {
            return;
        }

        if (SBCore.getFabricasDeAcaoDoSistema() == null) {
            throw new UnsupportedOperationException("O mapa de ações não pode ser definido, pos as Fabricas de ações do core não foram definidas");
        }
        try {

            mapaAcoes = new MapaDeAcoes(SBCore.getFabricasDeAcaoDoSistema());
            mapaCriado = true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando mapa de ações do sistema atual", t);
        }
    }

    /**
     *
     * Retorna todas as ações Referentes a Entidade
     *
     *
     * @param pEntidade Entidade referencia
     * @return Todas as ações da Entidade
     */
    public static List<ItfAcaoDoSistema> getAcoesByEntidade(Class pEntidade) {
        return mapaAcoes.getAcoesByEntidade(pEntidade);
    }

    /**
     *
     * Retorna todas as ações Referentes a Entidade
     *
     *
     * @param pEntidade Entidade referencia
     * @return Todas as ações da Entidade
     */
    public static List<ItfAcaoGerenciarEntidade> getAcoesDeGestaoByEntidade(Class pEntidade) {

        return mapaAcoes.getAcoesDeGestaoByEntidade(pEntidade);
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
    public static List<ItfAcaoDoSistema> getAcoesByDominioEModulo(String pDominio, ItfModuloAcaoSistema modulo) {

        return mapaAcoes.getAcoesByDominioEModulo(pDominio, modulo);

    }

    public static List<ItfAcaoGerenciarEntidade> getAcoesGestaoByModulo(ItfModuloAcaoSistema pModulo) {
        return mapaAcoes.getAcoesGestaoByModulo(pModulo);

    }

    /**
     *
     * Retorna todas as ações de Controller a partir da referencia de uma
     * entidade (que implementam alguma alteração no sistema)
     *
     * @param pEntidade A entidade referenciada
     * @return todas as ações controller da entidade
     */
    public static List<ItfAcaoController> getAcoesControllersByEntidade(Class pEntidade) {
        return mapaAcoes.getAcoesControllersByEntidade(pEntidade);

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
    public static List<ItfAcaoController> getAcoesControllerByEntidadeEModulo(Class pEntidade, ItfModuloAcaoSistema pModulo) {

        return mapaAcoes.getAcoesControllerByEntidadeEModulo(pEntidade, pModulo);
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
    public static List<ItfAcaoFormulario> getAcoesListagemByEntidadeEModulo(Class pEntidade, ItfModuloAcaoSistema pModulo) {

        return mapaAcoes.getAcoesListagemByEntidadeEModulo(pEntidade, pModulo);
    }

    /**
     *
     * Retorna a ação a partir de uma Fabrica
     *
     * @param pFabAcao Fabrica refencia
     * @return Ação do Sistema instanciada
     */
    public static ItfAcaoDoSistema getAcaoDoSistema(ItfFabricaAcoes pFabAcao) {
        return mapaAcoes.getAcaoDoSistema(pFabAcao);
    }

    /**
     *
     * Retorna a ação a partir do nome unico
     *
     * @param pFabAcao Fabrica refencia
     * @return Ação do Sistema instanciada
     */
    public static ItfAcaoDoSistema getAcaoDoSistemaByNomeUnico(String pFabAcao) {
        if (!mapaCriado) {
            MapaAcoesSistema.makeMapaAcoesSistema();
        }
        return mapaAcoes.getAcaoDoSistemaByNomeUnico(pFabAcao);
    }

    public static ItfAcaoDoSistema getAcaoDoSistemaById(int pId) {

        if (!mapaCriado) {
            MapaAcoesSistema.makeMapaAcoesSistema();
        }
        return mapaAcoes.getAcaoByIdAcao(pId);

    }

    public static ItfAcaoFormulario getAcaoDoSistemaByFormulario(String formulario) {
        return mapaAcoes.getAcaoFormularioPorXhtml(formulario);
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

        return mapaAcoes.getAcaoDeEntidade(pFabAcao);

    }

    public ItfAcaoFormularioEntidade getAcaoEntidadeFormulario(ItfFabricaAcoes pFabAcao) {

        return mapaAcoes.getAcaoEntidadeFormulario(pFabAcao);
    }

    public ItfAcaoControllerEntidade getAcaoEntidadeController(ItfFabricaAcoes pFabAcao) {

        return mapaAcoes.getAcaoEntidadeController(pFabAcao);
    }

    public ItfAcaoController getAcaoController(ItfFabricaAcoes pFabAcao) {

        return mapaAcoes.getAcaoController(pFabAcao);
    }

    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade(ItfFabricaAcoes pFabAcao) {

        if (!mapaCriado) {
            MapaAcoesSistema.makeMapaAcoesSistema();
        }
        return mapaAcoes.geAcaoGerenciarEntidade(pFabAcao);

    }

    public static List<ItfModuloAcaoSistema> getModulos() {

        if (!mapaCriado) {
            MapaAcoesSistema.makeMapaAcoesSistema();
        }
        return mapaAcoes.getModulos();

    }

    public static List<ItfAcaoGerenciarEntidade> getListaTodasGestao() {
        if (!mapaCriado) {
            MapaAcoesSistema.makeMapaAcoesSistema();
        }
        return (List) mapaAcoes.getListaTodasAcoes().stream().filter(acao -> acao.isUmaAcaoGestaoDominio()).collect(Collectors.toList());
    }

    public static List<ItfAcaoDoSistema> getListaTodasAcoes() {
        if (!mapaCriado) {
            MapaAcoesSistema.makeMapaAcoesSistema();
        }

        return mapaAcoes.getListaTodasAcoes();
    }

    /**
     *
     *
     * @return Se o Mapa já foi criado ou não
     */
    public static boolean isMapaCriado() {
        return mapaCriado;
    }

}
