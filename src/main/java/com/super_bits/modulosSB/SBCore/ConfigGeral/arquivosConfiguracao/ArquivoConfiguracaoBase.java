/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguradorCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * O arquivo de configuração base se encontra na pasta resource de cada projeto.
 *
 *
 *
 *
 *
 * @author salvioF
 */
public class ArquivoConfiguracaoBase {

    private String VERSAO;
    private String NOME_PROJETO;
    private String NOME_SOCIAL;
    private String TIPO_PROJETO;
    private String GRUPO_PROJETO;
    private String DIRETORIO_BASE;
    private String NOME_CLIENTE;

    @Override
    public String toString() {
        String texto
                = "\n"
                + "Nome do projeto: " + NOME_PROJETO + " \n"
                + "Nome Social: " + NOME_SOCIAL + " \n"
                + "Tipo Projeto Executado:" + TIPO_PROJETO + " \n"
                + "Grupo projeto: " + GRUPO_PROJETO + " \n"
                + "Diretorio Base: " + DIRETORIO_BASE + getDIRETORIO_BASE() + " \n"
                + "Cliente: " + NOME_CLIENTE + " \n";

        return texto;
    }

    public final void carregarPropriedadesArquivoConfiguradorBase(Properties pPropriedades) {
        VERSAO = (String) pPropriedades.get("VERSAO");
        if (VERSAO == null) {
            throw new UnsupportedOperationException("A versão do projeto não foi encontrada no ArquivoDeConfiguraçãoBase (SBProjeto)");
        }
        NOME_PROJETO = (String) pPropriedades.get("NOME_PROJETO");
        if (NOME_PROJETO == null) {
            throw new UnsupportedOperationException("O nome  do projeto não foi encontrado no ArquivoDeConfiguraçãoBase (SBProjeto)");
        }
        NOME_SOCIAL = (String) pPropriedades.get("NOME_SOCIAL");
        if (NOME_SOCIAL == null) {
            throw new UnsupportedOperationException("O nome social  do projeto não foi encontrado no ArquivoDeConfiguraçãoBase (SBProjeto)");
        }
        TIPO_PROJETO = (String) pPropriedades.get("TIPO_PROJETO");
        if (TIPO_PROJETO == null) {
            throw new UnsupportedOperationException("O tipo do  do projeto ${pom.packaging} não foi encontrado no ArquivoDeConfiguraçãoBase (SBProjeto)");
        }
        NOME_CLIENTE = (String) pPropriedades.get("NOME_CLIENTE");
        System.out.println("nome cliente" + NOME_CLIENTE);
        if (NOME_CLIENTE == null) {
            throw new UnsupportedOperationException("O nome do cliente não foi encontrado no projeto");
        }

        GRUPO_PROJETO = pPropriedades.getProperty("GRUPO_PROJETO");
        if (GRUPO_PROJETO == null) {
            throw new UnsupportedOperationException("O gropo do projeto não foi encontrado no ArquivoDeConfiguraçãoBase (SBProjeto)");
        }

        DIRETORIO_BASE = pPropriedades.getProperty("DIRETORIO_BASE");

    }

    /**
     *
     * Carrega o arquivo SBProjeto.prop direto a partir do objeto Properties
     *
     * @param pPropriedades
     */
    public ArquivoConfiguracaoBase(Properties pPropriedades) {
        carregarPropriedadesArquivoConfiguradorBase(pPropriedades);
    }

    /**
     *
     * Carrega o SBProjeto localizado em /mail/src/resource/ A classe
     * configurador deve estar localizada no mesmo pacote jar onde o resource
     * faz parte...
     *
     * O arquivo pom também deve ser configurado para adicionar este arquivo
     * como resource do projeto., Duvidas utilize o projeto padrão como exemplo
     *
     * @param pConfigurador
     * @throws UnsupportedOperationException
     * @throws IOException
     */
    public ArquivoConfiguracaoBase(ItfConfiguradorCore pConfigurador) throws UnsupportedOperationException, IOException {

        Properties proppriedadesBasicas = new Properties();

        String arquivoDeConfiguacao = "SBProjeto.prop";
        Class classeDoResource = pConfigurador.getClass();
        InputStream stream = classeDoResource.getClassLoader().getResourceAsStream(arquivoDeConfiguacao);
        if (stream == null) {
            throw new UnsupportedOperationException("Arquivo SBProjeto.prop não encontrado na pasta resources");
        }
        proppriedadesBasicas.load(stream);
        stream.close();
        carregarPropriedadesArquivoConfiguradorBase(proppriedadesBasicas);
        // diretorio base não é obrigatório...
    }

    public String getVERSAO() {
        return VERSAO;
    }

    public void setVERSAO(String VERSAO) {
        this.VERSAO = VERSAO;
    }

    public String getNOME_PROJETO() {
        return NOME_PROJETO;
    }

    public void setNOME_PROJETO(String NOME_PROJETO) {
        this.NOME_PROJETO = NOME_PROJETO;
    }

    public String getNOME_SOCIAL() {
        return NOME_SOCIAL;
    }

    public void setNOME_SOCIAL(String NOME_SOCIAL) {
        this.NOME_SOCIAL = NOME_SOCIAL;
    }

    public String getTIPO_PROJETO() {
        return TIPO_PROJETO;
    }

    public void setTIPO_PROJETO(String TIPO_PROJETO) {
        this.TIPO_PROJETO = TIPO_PROJETO;
    }

    public String getNOME_CLIENTE() {
        return NOME_CLIENTE;
    }

    public void setNOME_CLIENTE(String NOME_CLIENTE) {
        this.NOME_CLIENTE = NOME_CLIENTE;
    }

    public String getGRUPO_PROJETO() {
        return GRUPO_PROJETO;
    }

    public void setGRUPO_PROJETO(String GRUPO_PROJETO) {
        this.GRUPO_PROJETO = GRUPO_PROJETO;
    }

    public String getDIRETORIO_BASE() {
        return DIRETORIO_BASE;
    }

    public void setDIRETORIO_BASE(String DIRETORIO_BASE) {
        this.DIRETORIO_BASE = DIRETORIO_BASE;
    }

    public String getCaminhoPastaCliente() {
        return SBCore.CAMINHO_BASE_PROJETOS + "/" + getNOME_CLIENTE();
    }

    public String getCaminhoPastaClienteRelease() {
        return getCaminhoPastaCliente() + "/release";
    }

    public String getCaminhoPastaClienteSource() {
        return getCaminhoPastaCliente() + "/source";
    }

    public boolean isTemDiretorioBase() {
        if (DIRETORIO_BASE == null) {
            return false;
        }
        return DIRETORIO_BASE.length() >= 1;

    }

    public String getCaminhoPastaProjetoRelease() {
        if (isTemDiretorioBase()) {
            return getCaminhoPastaClienteRelease() + "/" + DIRETORIO_BASE + "/" + GRUPO_PROJETO;
        } else {
            return getCaminhoPastaClienteRelease() + "/" + GRUPO_PROJETO;
        }
    }

    public String getCaminhoPastaProjetoSource() {
        if (isTemDiretorioBase()) {
            return getCaminhoPastaClienteSource() + "/" + DIRETORIO_BASE + "/" + GRUPO_PROJETO + "/" + NOME_PROJETO;
        } else {
            return getCaminhoPastaClienteSource() + "/" + GRUPO_PROJETO + "/" + NOME_PROJETO;
        }
    }

}
