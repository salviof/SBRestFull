/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoBase;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoCliente;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoDistribuicao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreFabrica;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreSystemOut;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ConfigPermissaoSBCoreAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralDeArquivos;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfCentralMensagens;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.InfoErroSBComAcoes;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ItfCentralComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.UtilSBCoreReflexaoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ItfCentralAtributosDeObjetos;
import com.super_bits.modulosSB.SBCore.modulos.localizacao.ItfCentralLocalizacao;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.ItfCentralEventos;

import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ItfControleDeSessao;
import com.super_bits.modulosSB.SBCore.modulos.view.ItfServicoVisualizacao;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfCentralPermissoes;
import com.super_bits.modulosSB.SBCore.modulos.admin.ItfCentralAdministrativa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;

import com.super_bits.modulosSB.SBCore.modulos.centralDados.ItfCentralDados;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.tratamentoErros.ItfErroSBServico;
import org.coletivojava.fw.api.objetoNativo.log.LogPadraoSB;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfServicoLinkDeEntidadesERP;

/**
 *
 *
 * A classe SBCore permite acesso aos principais serviços de sistemas
 * desenvolvidos com o super-bits Framework
 *
 *
 *
 * Além fornecer acesso a configurações do projeto como: nome, cliente, Estado
 * da aplicação (Desenvolvimento, Testes ou Produção) de maneira rápida, é
 * atravez dela que é possível configurar e acessar as classes de acesso a
 * serviços do sistema, como estas:
 *
 * @see #getServicoSessao()
 * @see #getCentralDeMensagens();
 * @see #getCentralDeEventos();
 *
 *
 *
 * @see mais informações em <href src='www.coletivoJava.com.br/SBCore'>
 * coletivoJava.com.br/SBCore</a> <br>
 *
 *
 *
 *
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 24/05/2014
 *
 */
public class SBCore {

    @Deprecated
    public static final String DOMINIO_FICTICIO_INTRANET_DOCKER = "dockerIntranetColetivoJava.org.br";
    public static String CAMINHO_BASE_DESENVOLVIMENTO = "/home/superBits";
    public static String CAMINHO_BASE_PROJETOS = "/home/superBits/projetos";

    private static boolean ambienteExecucaoConfigurado = false;

    private static ESTADO_APP estadoAplicativo;
    private static boolean ignorarConfigurcoesDePermissao = false;
    private static boolean ignorarConfigurcoesDeAcoes = false;

    private static ItfCentralPermissoes configuradorDePermissao;
    private static final Map<String, ItfFabricaAcoes> ENUMACAO_BY_NOMEUNICO = new HashMap<>();
    private static final Map<String, Class<? extends ItfFabrica>> FABRICAS_OBJETO_ESTATICO = new HashMap<>();

    private static ItfConfiguracaoCoreSomenteLeitura infoAplicacao;
    private static ArquivoConfiguracaoBase arquivoConfigBase;
    private static ArquivoConfiguracaoCliente arquivoConfigCliente;
    private static ArquivoConfiguracaoDistribuicao arquivoConfigDistribuicao;
    private static ItfServicoVisualizacao servicoVisualizacao;
    private static ItfCentralDeArquivos servicoGestaoDeArquivos;
    private static ItfCentralComunicacao servicoComunucacao;
    private static ItfCentralLocalizacao servicoLocalizacao;
    private static ItfCentralAdministrativa servicoInterfaceGraficaDEV;
    private static Class<? extends ItfCentralAtributosDeObjetos> centralDeAtributosPadrao;
    private static ItfCentralDados centralDados;
    private static FabTipoProjeto tipoProjeto;

    /**
     * INDICA O ESTADO DA APLICAÇÃO: DESENVOLVIMENTO, HOMOLOGACAO, E PRODUÇÃO
     */
    public static enum ESTADO_APP {

        /**
         * Aplicação em desenvolvimento, em geral, modo de execução Junit
         */
        DESENVOLVIMENTO,
        /**
         * Aplicação em produção
         */
        PRODUCAO,
        /**
         *
         */
        HOMOLOGACAO;

    }

    public static boolean isAmbienteCoreConfigurado() {
        return ambienteExecucaoConfigurado;
    }

    public static boolean isEmModoDesenvolvimento() {

        return getEstadoAPP().equals(ESTADO_APP.DESENVOLVIMENTO);
    }

    public static boolean isEmModoProducao() {

        return getEstadoAPP().equals(ESTADO_APP.PRODUCAO);
    }

    public static ItfConfiguracaoCoreSomenteLeitura getInfoAplicacao() {
        return infoAplicacao;
    }

    public static ArquivoConfiguracaoDistribuicao getArquivoDistribuicao() {
        return arquivoConfigDistribuicao;
    }

    private static boolean isAmbienteExecucaoConfigurado() {
        return ambienteExecucaoConfigurado;
    }

    public static ItfServicoVisualizacao getCentralVisualizacao() {
        return servicoVisualizacao;
    }

    private static void fecharSistemaCasoNaoCOnfigurado() {
        if (ambienteExecucaoConfigurado) {
            return;
        }
        soutInfoDebug("É nescessário executar a configuração do core, antes de proceguir ;)", true);
        soutInfoDebug("Para configurar o core basta chamar o método SBCore.configurar(configurador) ", true);
        soutInfoDebug("Obs: A classe configurador deve se encontrar dentro do pacote principal do projeto", true);
        soutInfoDebug("na pasta resources do jar principal deve ser criado o arquivo SBProjeto.prop", true);
        System.exit(0);

    }

    private static boolean validaConfiguracoes() {
        try {

            if (infoAplicacao == null) {
                throw new UnsupportedOperationException("A aplicação  não foi configurado");
            }

            if (UtilSBCoreStringValidador.isNuloOuEmbranco(infoAplicacao.getNomeProjeto())) {
                throw new UnsupportedOperationException("O nome do projeto não foi configurado");
            }

            if (UtilSBCoreStringValidador.isNuloOuEmbranco(infoAplicacao.getCliente())) {
                throw new UnsupportedOperationException("O cliente não foi configurado");
            }
            //     SBCore.enviarAvisoAoUsuario(SBCore.getCaminhoDesenvolvimento());
            if (infoAplicacao.getCentralDeMensagens() == null) {
                throw new UnsupportedOperationException("Central de mensagens não configurada");

            }
            if (infoAplicacao.getEstadoApp() == null) {
                throw new UnsupportedOperationException("Estado do aplicativo não configurado");

            }
            if (infoAplicacao.getClasseErro() == null) {
                throw new UnsupportedOperationException("Classe Erro não configurada");

            }
            if (infoAplicacao.getDiretorioBase() == null) {
                throw new UnsupportedOperationException("Diretorio base não configurado");

            }

            if (tipoProjeto == null) {

                throw new UnsupportedOperationException("O tipo de projeto não foi configurado");
            }

            if (SBCore.getCentralDeArquivos() == null) {
                throw new UnsupportedOperationException("A Central de Arquivos do Projeto não foi definido");
            }
            if (estadoAplicativo == ESTADO_APP.DESENVOLVIMENTO) {
                File pastaTemp = new File("/home/developer/temp/servlet");
                pastaTemp.mkdirs();
            }

            if (SBCore.getFabricasDeAcaoDoSistema() != null) {
                MapaAcoesSistema.makeMapaAcoesSistema();
            }
            if (!ignorarConfigurcoesDePermissao) {
                if (infoAplicacao.getFabricaDeAcoes() == null) {
                    throw new UnsupportedOperationException("As configurações de permissão não foram definidas, você pode declarar desuso na classe de configuração, ou criar uma implementação");
                }
                //MapaAcoesSistema.makeMapaAcoesSistema();

                try {
                    // Caso a classe não tenha sido definida na mão, utilizando primeira classe encontrada que extenda ConfigPermissaoSBCoreAbstrato

                    if (infoAplicacao.getConfigPermissoes() == null) {
                        Class configPermissao = UtilSBCoreReflexao.getClasseQueEstendeIsto(ConfigPermissaoSBCoreAbstrato.class, "com.super_bits.configSBFW.acessos");

                        if (configPermissao == null) {
                            throw new UnsupportedOperationException("A classe que configura permissão não foi encontrada, crie uma classe que implemente config permssaoSBcore, ou altere a configuração do core dispensando as configurações de permissão");
                        }
                        configuradorDePermissao = (ItfCentralPermissoes) configPermissao.newInstance();

                    } else {
                        try {
                            configuradorDePermissao = (ItfCentralPermissoes) infoAplicacao.getConfigPermissoes().newInstance();
                        } catch (Throwable t) {
                            String nomeClassePermissao = "nulo";
                            if (infoAplicacao.getConfigPermissoes() != null) {
                                nomeClassePermissao = infoAplicacao.getConfigPermissoes().getName();
                            }
                            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro inicializando classes de permissões e serviços (controller da aplicação)"
                                    + "a partir de  \n " + nomeClassePermissao, t);
                            if (ignorarConfigurcoesDePermissao) {
                                System.out.println("A Classe de permissões não foi definida");

                            } else {
                                throw new UnsupportedOperationException("Erro configurando classes de serviço");
                            }

                        }

                    }

                } catch (InstantiationException | IllegalAccessException t) {
                    if (ignorarConfigurcoesDePermissao) {
                        System.out.println("A Classe de permissões não foi definida");
                    } else {
                        throw new UnsupportedOperationException("Erro configurando classe responsavel por permissao");
                    }

                }

            }

            if (estadoAplicativo == ESTADO_APP.DESENVOLVIMENTO) {
                String arquivoPomDoProjeto = SBCore.getCaminhoDesenvolvimento() + "/pom.xml";
                if (!UtilSBCoreArquivos.isArquivoExiste(arquivoPomDoProjeto)) {
                    throw new UnsupportedOperationException("O arquivo pom não foi encontrado em " + arquivoPomDoProjeto);
                }
            }
            System.out.println("COnfigurando Mapa de Ações do sistema");
            if (!ignorarConfigurcoesDeAcoes) {

                if (MapaAcoesSistema.isMapaCriado()) {
                    System.out.println("Mapa de ações criado com sucesso!");
                }
            }
            ambienteExecucaoConfigurado = true;
            return true;
        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro validação de configurações do projeto (SBCore)", t);

            ambienteExecucaoConfigurado = false;
            return false;
        }
    }

    public static boolean isControleDeAcessoDefinido() {
        return configuradorDePermissao != null;

    }

    /**
     *
     *
     *
     * @param configurador
     * @param pEstado
     *
     */
    public static void configurar(ItfConfiguradorCore configurador, ESTADO_APP pEstado) {

        try {

            if (ambienteExecucaoConfigurado) {
                //throw new UnsupportedOperationException("A configuração do ambiente de execução só pode ser realizada uma vez");
                System.out.println("ATENÇÃO, OCORREU UMA TENTATIVA DUPLA  DE CONFIGURAR O CORE, ESTA AÇÃO IRÁ GERAR UMA ERRO PARA_TUDO NAS PROXIMAS VERSÕES");
                // return;
                return;
            }
            estadoAplicativo = pEstado;

            ItfConfiguracaoCoreSomenteLeitura configuracoes = configurador.getConfiguracaoCore(pEstado);
            ignorarConfigurcoesDeAcoes = configurador.isIgnorarConfiguracaoAcoesDoSistema();
            ignorarConfigurcoesDePermissao = configurador.isIgnorarConfiguracaoPermissoes();
            infoAplicacao = configuracoes;
            LogPadraoSB.registrarClasseErro(infoAplicacao.getClasseErro());
            estadoAplicativo = configuracoes.getEstadoApp();
            arquivoConfigBase = configurador.getArquivoConfiguradorBase();
            arquivoConfigCliente = configurador.getArquivoConfiguradorCliente();

            arquivoConfigDistribuicao = configurador.getArquivoConfiguradorDistribuicao();
            servicoVisualizacao = configuracoes.getServicoVisualizacao().newInstance();
            servicoGestaoDeArquivos = configuracoes.getCentralDeArquivo().newInstance();
            servicoComunucacao = configuracoes.getCentralComunicacao().newInstance();
            tipoProjeto = configuracoes.getTipoProjeto();
            ambienteExecucaoConfigurado = validaConfiguracoes();

            servicoLocalizacao = configuracoes.getCentralDeLocalizacao().newInstance();
            centralDeAtributosPadrao = configuracoes.getCentralAtributoDados();
            servicoInterfaceGraficaDEV = configuracoes.getCentralAdministrativa();

            if (configuracoes.getCentralDados() != null) {
                registrarCentralDeDados(configuracoes.getCentralDados());
            }

            if (!ambienteExecucaoConfigurado) {
                throw new UnsupportedOperationException("O core não pôde determinar as configurações básicas");
            } else {

                if (SBCore.getEstadoAPP().equals(ESTADO_APP.HOMOLOGACAO)) {
                    UtilSBCoreSystemOut.exibirMensagemEmDestaque("CORE INICIADO EM MODO HOMOLOGAÇÃO (UTILIZE PARA TESTAR SEU WAR em localHost)");
                }

                if (SBCore.isEmModoProducao()) {
                    UtilSBCoreSystemOut.exibirMensagemEmDestaque("CORE INICIADO EM MODO PRODUÇÃO ");
                }

                if (SBCore.isEmModoDesenvolvimento()) {

                    UtilSBCoreSystemOut.exibirMensagemEmDestaque("CORE INICIADO EM MODO DESENVOLVIMENTO (UTILIZE APENAS PARA TESTES UNITÁRIOS) \n ");

                }
                if (!SBCore.isEmModoProducao()) {
                    UtilSBCoreSystemOut.exibirMensagemEmDestaque(
                            "Atenção, a Classe SBCore é o coração do superbits framework, \n"
                            + "## através do core é possível executar as ações do sistema em diversos ambientes \n"
                            + "## As seguintes classes foram configuradas para este ambiente: \n"
                            + "## Central de Mensagens: " + infoAplicacao.getCentralDeMensagens().getSimpleName() + ".class \n"
                            + "## Central de Eventos: " + infoAplicacao.getCentralDeEventos().getSimpleName() + ".class \n"
                            + "## Controle de Sessao: " + infoAplicacao.getControleDeSessao().getSimpleName() + ".class \n"
                            + "## Central de Localização: " + configuracoes.getCentralDeLocalizacao().getSimpleName() + ".class \n"
                            + "## Central de Arquivos: " + configuracoes.getCentralDeArquivo().getSimpleName() + ".class \n"
                            + "## Central de comunicacao:" + configuracoes.getCentralComunicacao().getSimpleName() + ".class \n"
                            + "## Central de Visualização: " + configuracoes.getServicoVisualizacao().getSimpleName() + ".class  \n"
                            + "## Central de Atributos: " + configuracoes.getCentralAtributoDados().getSimpleName() + ".class \n");
                    if (!isIgnorarPermissoes()) {
                        if (configuradorDePermissao != null) {
                            System.out.println("## Central de permissoes: " + configuradorDePermissao.getClass().getSimpleName() + ".class \n");
                        }
                    } else {
                        System.out.println("--> Nenhuma classe de permissao foi definida");
                    }
                    System.out.println("************************************************************* \n");
                    soutInfoDebug(" Informações de Do projeto: \n" + arquivoConfigBase.toString() + "\n");
                    System.out.println("************************************************************* \n");
                    System.out.println("-Caminho do projeto:" + arquivoConfigBase.getCaminhoPastaProjetoSource());
                    if (arquivoConfigDistribuicao != null) {
                        if (UtilSBCoreArquivos.isArquivoExiste(arquivoConfigDistribuicao.getCaminhoArquivoReleaseLocal())) {
                            soutInfoDebug(
                                    UTilSBCoreInputs.getStringByArquivoLocal(arquivoConfigDistribuicao.getCaminhoArquivoReleaseLocal()));
                        }
                    }
                }
            }

        } catch (Throwable t) {
            ambienteExecucaoConfigurado = false;

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando o Core" + t.getMessage(), t);
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro configurando o Core" + t.getMessage(), t);
        }

    }

    public static ESTADO_APP getEstadoAPP() {
        //    ValidaConfigurado(); Retirado para permitir consulta de estado da aplicação ao InfoErroSBComAcoes
        // o diretorio do aplicativo o mesmo que o servet
        return estadoAplicativo;
    }

    public static String getNomeProjeto() {
        fecharSistemaCasoNaoCOnfigurado();
        return infoAplicacao.getNomeProjeto();
    }

    public static String getDiretorioBase() {
        return infoAplicacao.getDiretorioBase();
    }

    /**
     *
     *
     *
     *
     * @param pTipoErro Programador: em desenvolvimento faz sout e se possivel
     * mostra na tela, em testes salva em log, em produção não faz nada Usuario:
     * Mostra na tela o erro. ParaTudo: Interrompe a execução do sistema
     * @param pMensagem Mensagem que será exibida
     * @param pErroJava O exception que gerou esse relato de erro
     */
    public static void RelatarErro(FabErro pTipoErro, String pMensagem, Throwable pErroJava) {

        if (!isAmbienteExecucaoConfigurado()) {
            pErroJava.printStackTrace();
            soutInfoDebug("O sistema encontrou um erro antes de configurar a classe que lida com erros", true);
            soutInfoDebug("O erro encontrado foi:" + pMensagem, true);
            soutInfoDebug(pErroJava.getMessage(), true);
            soutInfoDebug(pErroJava.getLocalizedMessage(), true);

            fecharSistemaCasoNaoCOnfigurado();
        }

        try {

            if (infoAplicacao == null || infoAplicacao.getClasseErro() == null) {
                System.out.println("a classe de erro não foi definida no core, utilizando classe de erro padrao");

                fecharSistemaCasoNaoCOnfigurado();
            }
            ItfErroSBServico erro = (InfoErroSBComAcoes) infoAplicacao.getClasseErro().newInstance();

            erro.configurar(FabMensagens.ERRO.getMsgDesenvolvedor(pMensagem), pTipoErro, pErroJava);

            erro.executarErro();
        } catch (InstantiationException | IllegalAccessException ex) {
            System.out.println("Erro Criando objeto ErrroSB erro" + ex.getMessage());
        }
    }

    /**
     *
     *
     *
     *
     * @param pTipoErro Programador: em desenvolvimento faz sout e se possivel
     * mostra na tela, em testes salva em log, em produção não faz nada Usuario:
     * Mostra na tela o erro. ParaTudo: Interrompe a execução do sistema
     * @param pMensagem Mensagem que será exibida
     * @param pErroJava O exception que gerou esse relato de erro
     */
    public static void RelatarErroAoUsuario(FabErro pTipoErro, String pMensagem, Throwable pErroJava) {
        fecharSistemaCasoNaoCOnfigurado();
        try {
            if (infoAplicacao.getClasseErro() == null) {
                System.out.println("a classe de erro não foi definida no core, utilizando classe de erro padrao");

            }
            ItfErroSBServico erro = (InfoErroSBComAcoes) infoAplicacao.getClasseErro().newInstance();

            erro.configurar(FabMensagens.ERRO.getMsgUsuario(pMensagem), pTipoErro, pErroJava);

            erro.executarErro();
        } catch (InstantiationException | IllegalAccessException ex) {
            System.out.println("Erro Criando objeto ErrroSB erro" + ex.getMessage());
        }
    }

    public static boolean isPermitido(ItfAcaoDoSistema pAcao) {
        return ControllerAppAbstratoSBCore.isAcessoPermitido(pAcao);
    }

    public static String getCaminhoGrupoProjetoSource() {

        String caminho = "";
        boolean temCaminhoDiretorioBase = UtilSBCoreStringValidador.isNAO_NuloNemBranco(getDiretorioBase());
        boolean temCaminhoGrupoProjeto = UtilSBCoreStringValidador.isNAO_NuloNemBranco(getGrupoProjeto());

        caminho = arquivoConfigBase.getCaminhoPastaClienteSource();

        if (temCaminhoDiretorioBase) {
            caminho = caminho + "/" + infoAplicacao.getDiretorioBase();
        }

        if (!temCaminhoGrupoProjeto) {
            return caminho + "/" + infoAplicacao.getNomeProjeto();
        } else {
            return caminho + "/" + getGrupoProjeto();
        }
    }

    public static String getCaminhoDesenvolvimento() {

        return arquivoConfigBase.getCaminhoPastaProjetoSource();

    }

    public static String getGrupoProjeto() {
        return infoAplicacao.getGrupoProjeto();
    }

    // Atalhos de acesso
    public static void enviarMensagemUsuario(String pMensagem, FabMensagens pTipoMensagem) {

        getCentralDeMensagens().enviaMensagem(pTipoMensagem.getMsgUsuario(pMensagem));
    }

    public static void enviarAvisoAoUsuario(String pMensagem) {

        getCentralDeMensagens().enviaMensagem(FabMensagens.AVISO.getMsgUsuario(pMensagem));
    }

    /**
     *
     * Atalho para SBCore.getControleDeSessao.getsessaoAtual.getusuarioLogado;
     *
     * @return
     */
    public static ItfUsuario getUsuarioLogado() {
        return getControleDeSessao().getSessaoAtual().getUsuario();
    }

    private static void gerarEnumByNomeUnico() {

        ENUMACAO_BY_NOMEUNICO.clear();

        for (Class fabrica : infoAplicacao.getFabricaDeAcoes()) {

            for (Object objAcao : fabrica.getEnumConstants()) {
                ItfFabricaAcoes acao = (ItfFabricaAcoes) objAcao;
                ENUMACAO_BY_NOMEUNICO.put(UtilSBController.gerarNomeUnicoAcaoDoSistema(acao), acao);
            }

        }

    }

    public static ItfFabricaAcoes getFabricaByNOME_UNICO(String pNomeUnico) {
        try {
            if (pNomeUnico == null) {
                throw new UnsupportedOperationException("Tebtativa de obter a fabrica de ação com parametro nulo");
            }
            if ("".equals(pNomeUnico)) {
                throw new UnsupportedOperationException("Tebtativa de obter a fabrica de ação com parametro nulo");
            }

            if (ENUMACAO_BY_NOMEUNICO.isEmpty()) {
                gerarEnumByNomeUnico();
            }

            ItfFabricaAcoes acao = ENUMACAO_BY_NOMEUNICO.get(pNomeUnico);
            if (acao == null) {
                throw new UnsupportedOperationException("A ação do sistema não foi encontrada pelo nome único " + pNomeUnico);
            }
            return acao;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo fabrica por nome único", t);
        }
        return null;

    }

    public static Class<? extends ItfFabricaAcoes>[] getFabricasDeAcaoDoSistema() {
        return infoAplicacao.getFabricaDeAcoes();
    }

    public static String getUrlJira() {

        throw new UnsupportedOperationException("Utilize SBCore.getConfigModulo para obter a url de conexão do Jira");
    }

    public static boolean isIgnorarPermissoes() {
        return ignorarConfigurcoesDePermissao;
    }

    /**
     *
     * Retona um Objeto estatico apartir do nome da fabrica
     *
     *
     * -> Atenção, o prefixo Fab não é obrigatório
     *
     *
     * @param pNome Nome da fabrica ex: FabCompVisualBotaoAcao.ICONE_E_NOME
     * @return O objeto instanciado
     */
    public static Object getObjetoEstatico(String pNome) {
        try {
            if (pNome == null || pNome.isEmpty()) {
                throw new UnsupportedOperationException("Enviou nulo ouvazio para obter metodo estatico");
            }

            String nomeFabrica = pNome.split("\\.")[0];
            if (!nomeFabrica.startsWith("Fab")) {
                nomeFabrica = "Fab" + nomeFabrica;
            }
            String nomeCampo = pNome.split("\\.")[1];

            Class fabrica = FABRICAS_OBJETO_ESTATICO.get(nomeFabrica);

            return UtilSBCoreReflexaoFabrica.getObjetoByString(fabrica, nomeCampo);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo objeto Estático a partir do nome: " + pNome, t);
            return null;
        }

    }

    public static void adicionarFabricaObjetoEstatico(Class pClasse) {
        try {
            FABRICAS_OBJETO_ESTATICO.put(pClasse.getSimpleName(), pClasse);

            ItfBeanSimples objeto = (ItfBeanSimples) UtilSBCoreFabrica.listaRegistros(pClasse).get(0);
            MapaObjetosProjetoAtual.adcionarObjeto(objeto.getClass());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando classe Fabrica Objeto Estatico" + pClasse, t);
        }
    }

    /**
     *
     * @return O classe loader onde a classe de configuração foi criada
     */
    public static ClassLoader getClasseLoaderAplicacao() {
        return infoAplicacao.getClass().getClassLoader();
    }

    public static ConfigModulo getConfigModulo(Class<? extends ItfFabConfigModulo> pFabricaConfig) {
        ConfigModulo config = null;
        try {
            return new ConfigModulo(pFabricaConfig, getClasseLoaderAplicacao());

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo configuração do modulo", t);
            return null;
        } finally {
            if (!SBCore.isEmModoProducao()) {
                if (config != null) {
                    UtilSBCoreSystemOut.exibirMensagemEmDestaque("Um módulo foi configurado: " + config.toString());
                }
            }
        }

    }

    public static void registrarCentralDeDados(Class<? extends ItfCentralDados> pCentralDeDados) {
        try {
            centralDados = (ItfCentralDados) pCentralDeDados.newInstance();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro registrando central de dados", t);
        }

    }

    /**
     *
     * @see SBCore#getServicoArquivosDeEntidade()
     *
     * @return Manipulação de Arquivos de Entidade e do Sistema
     */
    @Deprecated
    public static ItfCentralDeArquivos getCentralDeArquivos() {

        return servicoGestaoDeArquivos;
    }

    /**
     *
     * @see ItfCentralDeArquivos
     *
     * @return Manipulação de Arquivos de Entidade e do Sistema
     */
    public static ItfCentralDeArquivos getServicoArquivosDeEntidade() {

        return servicoGestaoDeArquivos;
    }

    /**
     *
     * @return @see SBCore#getServicoLocalizacao()
     */
    @Deprecated
    public static ItfCentralLocalizacao getCentralDeLocalizacao() {
        return getServicoLocalizacao();
    }

    /**
     * @see ItfCentralLocalizacao
     * @return Helper framework CEP
     */
    public static ItfCentralLocalizacao getServicoLocalizacao() {
        return servicoLocalizacao;
    }

    /**
     *
     *
     * @see ItfCentralMensagens
     *
     * @return Metodos de Controle da Central de Mensagens
     */
    @Deprecated
    public static ItfCentralMensagens getCentralDeMensagens() {
        return getServicoMensagens();
    }

    /**
     *
     *
     * @see ItfCentralMensagens
     *
     * @return Metodos de Controle da Central de Mensagens
     */
    public static ItfCentralMensagens getServicoMensagens() {
        try {
            return infoAplicacao.getCentralDeMensagens().newInstance();
        } catch (Throwable ex) {

            RelatarErro(FabErro.PARA_TUDO, " ERRO CRIANDO CENTRAL DE MENSAGENS", ex);
        }
        return null;
    }

    /**
     *
     * @see ItfCentralEventos
     *
     *
     * @return Controle de Log referente ao Sistema
     */
    @Deprecated
    public static ItfCentralEventos getCentralDeEventos() {

        return getServicoLogEventos();

    }

    /**
     *
     *
     *
     * @return
     */
    public static ItfCentralEventos getServicoLogEventos() {
        try {
            return infoAplicacao.getCentralDeEventos().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            RelatarErro(FabErro.PARA_TUDO, "ERRO CRIANDO CENTRAL DE EVENTOS", ex);
        }
        return null;
    }

    /**
     *
     * @see SBCore#getServicoSessao()
     * @return @deprecated Utilize getServico
     *
     */
    @Deprecated
    public static ItfControleDeSessao getCentralDeSessao() {
        return getServicoSessao();
    }

    /**
     *
     * @see ItfControleDeSessao
     * @return Controle de Sessão do contexto Atual de execução
     *
     */
    public static ItfControleDeSessao getServicoSessao() {
        try {
            return infoAplicacao.getControleDeSessao().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            RelatarErro(FabErro.PARA_TUDO, "Erro Criando controle de Sessao", ex);

        }
        return null;
    }

    @Deprecated
    public static ItfControleDeSessao getControleDeSessao() {
        return getCentralDeSessao();
    }

    /**
     *
     *
     * @deprecated Utilize getServicoComunicacao
     * @return
     */
    @Deprecated
    public static ItfCentralComunicacao getCentralDeComunicacao() {
        return getServicoComunicacao();
    }

    /**
     *
     * @see SBCore#getServicoComunicacao()
     * @deprecated Utilize getServiço
     * @return
     */
    @Deprecated
    public static ItfCentralComunicacao getCentralComunicacao() {
        return getServicoComunicacao();
    }

    /**
     *
     * @see ItfCentralComunicacao
     *
     * @return Controle de comunicação, entre Sistema, Usuário e Desenvolvedor
     */
    public static ItfCentralComunicacao getServicoComunicacao() {
        if (servicoComunucacao == null) {
            throw new UnsupportedOperationException("A central de comunicação não foi definida");
        }
        return servicoComunucacao;
    }

    public static void soutInfoDebug(String pInfo, boolean forcarExibicao) {
        if (estadoAplicativo != ESTADO_APP.PRODUCAO || forcarExibicao) {
            System.out.println("SBCoreInfo:" + pInfo);
        }

    }

    public static void soutInfoDebug(String pInfo) {
        soutInfoDebug(pInfo, false);
    }

    /**
     *
     * @return @deprecated Utilize getSErvicoFonteDeDados
     * @see SBCore#getServicoFonteDeDadosParaAtributos()
     */
    @Deprecated
    public static ItfCentralAtributosDeObjetos getCentralFonteDeDados() {
        return getServicoFonteDeDadosParaAtributos();

    }

    /**
     *
     * @see ItfCentralAtributosDeObjetos
     *
     * @return Helper para exibição de opções possiveis para determinado
     * atributo de objeto
     */
    public static ItfCentralAtributosDeObjetos getServicoFonteDeDadosParaAtributos() {
        try {
            return centralDeAtributosPadrao.newInstance();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Inciando Central de atributos via" + centralDeAtributosPadrao.getSimpleName(), t);
            return null;
        }
    }

    /**
     *
     *
     * @see SBCore#getServicoPermissao()
     * @return Asistente para Controle de Acesso as ações do sistema
     */
    @Deprecated
    public static ItfCentralPermissoes getCentralPermissao() {
        return getServicoPermissao();
    }

    /**
     *
     * @see ItfCentralPermissoes
     *
     * @return Asistente para Controle de Acesso as ações do sistema
     */
    public static ItfCentralPermissoes getServicoPermissao() {
        if (isIgnorarPermissoes()) {
            return null;
        }
        if (configuradorDePermissao == null) {
            RelatarErro(FabErro.PARA_TUDO, "Configurador de permissão não foi definido", null);

        }
        return configuradorDePermissao;
    }

    /**
     *
     * @see ItfCentralDados
     * @deprecated Usar getServicoRepositorio
     * @return Recupera Entidades persistidas
     */
    @Deprecated
    public static ItfCentralDados getCentralDados() {
        return getServicoRepositorio();
    }

    public static ItfCentralDados getServicoRepositorio() {
        if (centralDados == null) {
            throw new UnsupportedOperationException("A central de dados não foi registrada");
        }
        return centralDados;
    }

    public static ItfCentralAdministrativa getCentralAdministrativa() {

        return servicoInterfaceGraficaDEV;
    }

    public static FabTipoProjeto getTipoProjeto() {
        return tipoProjeto;
    }

}
