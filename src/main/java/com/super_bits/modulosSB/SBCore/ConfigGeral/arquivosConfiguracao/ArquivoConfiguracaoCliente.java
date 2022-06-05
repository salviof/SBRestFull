/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivoTexto;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author salvioF
 */
public class ArquivoConfiguracaoCliente {

    public static String NOME_ARQUIVO_CLIENTE = "cliente.info";
    // Configurado no arquivo cliente.info pasta release
    private String SERVIDOR_GIT_RELEASE;
    // Configurado no arquivo cliente.info pasta source
    private String SERVIDOR_GIT_SOURCE;

    private void confgurar(Properties propriedadesClienteSource, Properties proPriedadesClienteRelease) {

        SERVIDOR_GIT_SOURCE = propriedadesClienteSource.getProperty("SERVIDOR_GIT_SOURCE");
        if (SERVIDOR_GIT_SOURCE == null) {
            throw new UnsupportedOperationException("O caminho para o servidor git da aplicaçao não foi cinfigurado, crie um arquivo chamado cliente.info na pasta source do cliente, com o conteúdo: SERVIDOR_GIT_SOURCE=seuServidorGit");
        }
        SERVIDOR_GIT_RELEASE = proPriedadesClienteRelease.getProperty("SERVIDOR_GIT_RELEASE");
        if (SERVIDOR_GIT_RELEASE == null) {
            throw new UnsupportedOperationException("O caminho para o servidor git da aplicaçao não foi encontrado, crie um arquivo chamado cliente.info na pasta release do cliente, com o conteúdo: SERVIDOR_GIT_RELEASE=seuServidorGit");
        }

    }

    public ArquivoConfiguracaoCliente(Properties prop) {

    }

    public ArquivoConfiguracaoCliente(ArquivoConfiguracaoBase baseConfig) throws UnsupportedOperationException, IOException {

        String arqInfoClienteSource = baseConfig.getCaminhoPastaClienteSource() + "/" + NOME_ARQUIVO_CLIENTE;
        String arqInfoClienteRelease = baseConfig.getCaminhoPastaClienteRelease() + "/" + NOME_ARQUIVO_CLIENTE;

        Properties propriedadesClienteSource = UtilSBCoreArquivoTexto.getPropriedadesNoArquivo(arqInfoClienteSource);
        if (propriedadesClienteSource == null) {
            throw new UnsupportedOperationException("Arquivo " + NOME_ARQUIVO_CLIENTE + " não encontrado em" + arqInfoClienteSource);
        }
        Properties propriedadesClienteInfoRelease = UtilSBCoreArquivoTexto.getPropriedadesNoArquivo(arqInfoClienteRelease);
        if (propriedadesClienteInfoRelease == null) {
            throw new UnsupportedOperationException("Arquivo não encontrado em " + arqInfoClienteRelease);
        }
        confgurar(propriedadesClienteSource, propriedadesClienteInfoRelease);

    }

}
