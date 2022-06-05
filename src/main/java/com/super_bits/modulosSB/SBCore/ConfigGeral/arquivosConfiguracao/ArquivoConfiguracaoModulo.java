/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreDiretoriosSimples;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBcoreModulos;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.RecursosExternosPorIndice;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.commons.io.filefilter.FileFileFilter;

/**
 *
 * @author SalvioF
 */
public abstract class ArquivoConfiguracaoModulo {

    protected Properties proppriedadesBasicas = new Properties();
    protected Class<? extends ItfFabConfigModulo> fabricaConfig;
    protected String arquivoDeConfiguacao;
    protected RecursosExternosPorIndice repositorio;

    public RecursosExternosPorIndice getRepositorioDeArquivosExternos() {
        if (repositorio == null) {
            repositorio = new RecursosExternosPorIndice(fabricaConfig);

        }
        return repositorio;
    }

    public ArquivoConfiguracaoModulo(Class<? extends ItfFabConfigModulo> pFabricaConfig) throws IOException {
        if (pFabricaConfig == null) {
            throw new UnsupportedOperationException("Tentativa de obter propriedades do módulo com nulo");
        }
        fabricaConfig = pFabricaConfig;
        reloadConfiguracoes();

    }

    public final void reloadConfiguracoes() {
        String arquivo = UtilSBcoreModulos.getCaminhoAbsolutoDoContextoAtual(fabricaConfig);
        try {
            if (!new File(arquivo).exists()) {
                UtilSBCoreArquivos.criarDiretorioParaArquivo(arquivo);
            }
            if (!new File(arquivo).exists()) {
                if (!salvarPropriedadesPadrao()) {
                    throw new UnsupportedOperationException("O modulo " + fabricaConfig.getSimpleName() + " não pôde ser configurado, Houve um erro tentando salvar" + arquivo);
                } else {
                    SBCore.soutInfoDebug("Arquivo de cofiguração de módulo criado em:" + arquivo);
                }
            } else {
                if (!SBCore.isEmModoProducao()) {
                    SBCore.soutInfoDebug("Arquivo de cofiguração de módulo encontrado em:" + arquivo);
                }
            }
            FileInputStream inputstream = (FileInputStream) UTilSBCoreInputs.getStreamByLocalFile(arquivo);
            proppriedadesBasicas.load(inputstream);
            inputstream.close();

        } catch (IOException | UnsupportedOperationException t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo configurações de módulos", t);
        }
    }

    protected boolean salvarPropriedades(Properties pProp) {
        String caminhoCOmpleto = UtilSBcoreModulos.getCaminhoAbsolutoDoContextoAtual(fabricaConfig);
        try {
            Properties prop;
            if (pProp == null) {
                prop = new Properties();
                for (ItfFabConfigModulo fabrica : fabricaConfig.getEnumConstants()) {
                    prop.setProperty(fabrica.toString(), fabrica.getValorPadrao());
                }
            } else {
                prop = pProp;

            }
            UtilSBcoreModulos.getCaminhoRelativoConfigModulo(fabricaConfig);

            String diretorio = UtilSBCoreDiretoriosSimples.getDiretorioArquivo(caminhoCOmpleto);
            File dirFile = new File(diretorio);
            if (!dirFile.exists()) {
                new File(diretorio).mkdirs();
            }
            File arquivoConfigRuntime = new File(caminhoCOmpleto);

            OutputStream out = new FileOutputStream(arquivoConfigRuntime);
            prop.store(out, "Prorpiedades do módulo " + fabricaConfig.getClass().getSimpleName() + " armazanada em");
            out.close();

            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando arquivo de configuração padrão do modulo em [" + caminhoCOmpleto + "]", t);
            return false;
        }
    }

    private boolean salvarPropriedadesPadrao() {

        return salvarPropriedades(null);

    }

}
