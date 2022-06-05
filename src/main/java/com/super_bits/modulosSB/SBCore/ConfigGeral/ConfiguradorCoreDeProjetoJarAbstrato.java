/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.CentralComunicacaoApenasLogs;

/**
 *
 * @author salvioF
 */
public abstract class ConfiguradorCoreDeProjetoJarAbstrato extends ConfiguradorCoreAbstrato {

    @Override
    public void defineClassesBasicas(ItfConfiguracaoCoreCustomizavel pConfiguracao) {
        UtilConfiguracaoCore.setclassesPadraoJar(pConfiguracao);
        setIgnorarConfiguracaoAcoesDoSistema(true);
        setIgnorarConfiguracaoPermissoes(true);

        pConfiguracao.setCentralComunicacao(CentralComunicacaoApenasLogs.class);

    }

    public ConfiguradorCoreDeProjetoJarAbstrato() {
        super(true);
    }

    public ConfiguradorCoreDeProjetoJarAbstrato(boolean buscarArquivoConfiguracaoEmResource) {
        super(buscarArquivoConfiguracaoEmResource);

    }

}
