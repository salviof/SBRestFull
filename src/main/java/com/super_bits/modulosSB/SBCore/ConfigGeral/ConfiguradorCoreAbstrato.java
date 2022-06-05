/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoBase;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoCliente;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoDistribuicao;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author salvioF
 */
public abstract class ConfiguradorCoreAbstrato implements ItfConfiguradorCore {

    private boolean ignorarConfiguracaoPermissoes = false;
    private boolean ignorarConfiguracaoAcoesDoSistema = false;

    protected ArquivoConfiguracaoBase arquivoConfiguradorBase;
    protected ArquivoConfiguracaoCliente arquivoConfiguradorCliente;
    protected ArquivoConfiguracaoDistribuicao arquivoConfiguradorDistribuicao;

    public abstract void defineFabricasDeACao(ItfConfiguracaoCoreCustomizavel pConfig);

    public abstract void defineClassesBasicas(ItfConfiguracaoCoreCustomizavel pConfiguracao);

    private void carregarArquivosConfiguracaoSecundario() throws IOException {
        arquivoConfiguradorCliente = new ArquivoConfiguracaoCliente(arquivoConfiguradorBase);
        try {

            ArquivoConfiguracaoDistribuicao distro = new ArquivoConfiguracaoDistribuicao(arquivoConfiguradorBase);
            arquivoConfiguradorDistribuicao = distro;
            System.out.println(arquivoConfiguradorDistribuicao);
        } catch (Throwable t) {
            SBCore.soutInfoDebug("O arquivo de distribuição não foi encontrado, você não precisa se preocupar com isso caso não pretenda executar este projeto fora do ambiente de desenvolvimento");
        }
    }

    public ConfiguradorCoreAbstrato(ArquivoConfiguracaoBase pArquivoBase) {

        try {
            arquivoConfiguradorBase = pArquivoBase;
            carregarArquivosConfiguracaoSecundario();
        } catch (Throwable erro) {
            System.out.println("SBCoreInfo: Erro lendo propriedade do arquivo de configuração ");
            System.out.println("SBCoreInfo: certifique que o arquivo SBProjeto.prop esteja na pasta src/main/resource do projeto, e que o seu arquivo pom contenha as tags de resources configuradas no build");
            System.out.println("SBCoreInfo: duvidas utilize o projeto SBCore como referencia");
            System.out.println("SBCoreInfo: *** Sem o arquivo de configuração basico, não é possível carregar o arquivo de configuração de produção");
        }

    }

    public ConfiguradorCoreAbstrato(Properties propriedadesARquivoBase) {
        try {
            arquivoConfiguradorBase = new ArquivoConfiguracaoBase(propriedadesARquivoBase);
            carregarArquivosConfiguracaoSecundario();
        } catch (Throwable erro) {
            System.out.println("SBCoreInfo: Erro lendo propriedade do arquivo de configuração ");
            System.out.println("SBCoreInfo: certifique que o arquivo SBProjeto.prop esteja na pasta src/main/resource do projeto, e que o seu arquivo pom contenha as tags de resources configuradas no build");
            System.out.println("SBCoreInfo: duvidas utilize o projeto SBCore como referencia");
            System.out.println("SBCoreInfo: *** Sem o arquivo de configuração basico, não é possível carregar o arquivo de configuração de produção");
        }
    }

    public ConfiguradorCoreAbstrato(boolean buscarArquivoConfiguracaoEmResource) {
        if (buscarArquivoConfiguracaoEmResource) {

            try {
                arquivoConfiguradorBase = new ArquivoConfiguracaoBase(this);
                carregarArquivosConfiguracaoSecundario();
            } catch (UnsupportedOperationException | IOException ex) {
                System.out.println("SBCoreInfo: Erro lendo propriedade do arquivo de configuração ");
                System.out.println("SBCoreInfo: certifique que o arquivo SBProjeto.prop esteja na pasta src/main/resource do projeto, e que o seu arquivo pom contenha as tags de resources configuradas no build");
                System.out.println("SBCoreInfo: duvidas utilize o projeto SBCore como referencia");
                System.out.println("SBCoreInfo: *** Sem o arquivo de configuração basico, não é possível carregar o arquivo de configuração de produção");
            }
        }
    }

    /**
     *
     * O Get Configuração é onde o Objeto ConfiguracaoSBCOre é criado com as
     * definições do ambiente de execução
     *
     * O codigo faz referencia a 2 métodos abstratos, que devem ser
     * implementados a cada projeto i defineClassesBasicas, e
     * DefineFAbricasDeAcao
     *
     *
     *
     * @param pEstadoApp
     * @return
     */
    @Override
    public final ItfConfiguracaoCoreSomenteLeitura getConfiguracaoCore(SBCore.ESTADO_APP pEstadoApp) {

        ConfigCoreCustomizavel configuracao = new ConfigCoreCustomizavel();
        //Configura estado do app antes de qualquer coisa,
        //pois ele pode ser determinantes na definição de alguma configuração
        configuracao.setEstadoAPP(pEstadoApp);
        //Aplica os dados do arquivo SBProjeto.prop na configuração
        aplicarDadosArquivoConfiguracao(configuracao);
        try {
            defineClassesBasicas(configuracao);
        } catch (Throwable t) {
            throw new UnsupportedOperationException("Erro definindo classes básicas do projeto", t);
        }
        try {
            defineFabricasDeACao(configuracao);
        } catch (Throwable t) {
            throw new UnsupportedOperationException("Erro definindo Fabricas de ação do projeto", t);
        }
        return configuracao;
    }

    protected void aplicarDadosArquivoConfiguracao(ItfConfiguracaoCoreCustomizavel pConfigurador) {

        if (isTemArqConfiguracaoBase()) {
            pConfigurador.setNomeProjeto(getArquivoConfiguradorBase().getNOME_PROJETO());
            pConfigurador.setDiretorioBase(getArquivoConfiguradorBase().getDIRETORIO_BASE());
            pConfigurador.setCliente(getArquivoConfiguradorBase().getNOME_CLIENTE());
            pConfigurador.setGrupoProjeto(getArquivoConfiguradorBase().getGRUPO_PROJETO());
            pConfigurador.setNomeSocial(getArquivoConfiguradorBase().getNOME_SOCIAL());

        }
        if (isTemArqConfiguracaoCliente()) {

        }

        if (isTemArqConfiguracaoDistrivuicao()) {

        }

    }

    @Override
    public boolean isIgnorarConfiguracaoPermissoes() {
        return ignorarConfiguracaoPermissoes;
    }

    public final void setIgnorarConfiguracaoPermissoes(boolean ignorarConfiguracaoPermissoes) {
        this.ignorarConfiguracaoPermissoes = ignorarConfiguracaoPermissoes;
    }

    @Override
    public boolean isIgnorarConfiguracaoAcoesDoSistema() {
        return ignorarConfiguracaoAcoesDoSistema;
    }

    public final void setIgnorarConfiguracaoAcoesDoSistema(boolean ignorarConfiguracaoAcoesDoSistema) {
        this.ignorarConfiguracaoAcoesDoSistema = ignorarConfiguracaoAcoesDoSistema;
    }

    @Override
    public ArquivoConfiguracaoBase getArquivoConfiguradorBase() {
        return arquivoConfiguradorBase;
    }

    public void setArquivoConfiguradorBase(ArquivoConfiguracaoBase arquivoConfiguradorBase) {
        this.arquivoConfiguradorBase = arquivoConfiguradorBase;
    }

    @Override
    public ArquivoConfiguracaoCliente getArquivoConfiguradorCliente() {
        return arquivoConfiguradorCliente;
    }

    public void setArquivoConfiguradorCliente(ArquivoConfiguracaoCliente arquivoConfiguradorCliente) {
        this.arquivoConfiguradorCliente = arquivoConfiguradorCliente;
    }

    @Override
    public ArquivoConfiguracaoDistribuicao getArquivoConfiguradorDistribuicao() {
        return arquivoConfiguradorDistribuicao;
    }

    public void setArquivoConfiguradorDistribuicao(ArquivoConfiguracaoDistribuicao arquivoConfiguradorDistribuicao) {
        this.arquivoConfiguradorDistribuicao = arquivoConfiguradorDistribuicao;
    }

    @Override
    public boolean isTemArqConfiguracaoBase() {
        return arquivoConfiguradorBase != null;
    }

    @Override
    public boolean isTemArqConfiguracaoCliente() {
        return arquivoConfiguradorCliente != null;
    }

    @Override
    public boolean isTemArqConfiguracaoDistrivuicao() {
        return arquivoConfiguradorDistribuicao != null;
    }

}
