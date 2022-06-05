/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoEnuns;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringSlugs;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 *
 *
 * @author SalvioF
 */
public class ConfigModulo extends ArquivoConfiguracaoModulo implements ItfConfigModulo {

    private static Map<String, String> propriedadesEnv = new HashMap<>();

    @Deprecated
    public ConfigModulo(Class<? extends ItfFabConfigModulo> pFabricaConfig, ClassLoader resourceLoader) throws IOException {
        this(pFabricaConfig);

    }

    public ConfigModulo(Class<? extends ItfFabConfigModulo> pFabricaConfig) throws IOException {
        super(pFabricaConfig);
    }

    public static String getNomeCompleto(ItfFabConfigModulo pPropriedades) {
        String moduloPrincipal = UtilSBCoreStringSlugs.gerarSlugCaixaAltaByCammelCase(pPropriedades.getClass().getSimpleName().replace("FabConfig", ""));
        moduloPrincipal = moduloPrincipal.replace("CONFIG_", "");
        moduloPrincipal = moduloPrincipal.replace("CONF_", "");
        String nomeCompleto = moduloPrincipal + "_" + pPropriedades.toString();
        return nomeCompleto;
    }

    @Override
    public String getPropriedade(ItfFabConfigModulo pPropriedades) {
        if (SBCore.isEmModoProducao()) {
            if (!propriedadesEnv.containsKey(pPropriedades.toString())) {

                String nomeCompleto = getNomeCompleto(pPropriedades);
                String propriedadeEnv = System.getenv(nomeCompleto);
                if (propriedadeEnv == null) {

                    propriedadeEnv = System.getenv(pPropriedades.toString());
                    if (propriedadeEnv != null) {
                        System.out.println("ATENÇÃO, VARIAVEL DE AMBIENTE DEFINIDA VIA ABREVIAÇÃO, ESTE TIPO DE CHAMADA SERÁ DEPRECIADO EM BREVE");
                        System.out.println("ALTERE PARA de " + pPropriedades.toString() + " para: " + nomeCompleto);
                    }
                }

                if (propriedadeEnv != null) {
                    propriedadesEnv.put(pPropriedades.toString(), propriedadeEnv);
                }
            }
            String valorEnv = propriedadesEnv.get(pPropriedades.toString());
            if (valorEnv != null) {
                return valorEnv;
            }
        }
        if (proppriedadesBasicas.get(pPropriedades.toString()) != null) {
            return proppriedadesBasicas.getProperty(pPropriedades.toString());
        }
        // ATENÇÃO, SUPORTE LEGADO NÃO REMOVER ATÉ O ANO DE  2025 OU ATÉ A VERSÃO 2.0
        return proppriedadesBasicas.getProperty(pPropriedades.getNomePropriedade());
    }

    public String getPropriedadePorAnotacao(ItfFabrica pPropriedades) {

        ItfFabConfigModulo atributo = UtilSBCoreReflexaoEnuns.getEnumConfigComEstaFabricaNaAnotacao(fabricaConfig, pPropriedades);
        if (atributo == null) {
            throw new UnsupportedOperationException("Atributo " + pPropriedades + "não parece ser um parametro esperado ");
        }
        return getPropriedade(atributo);

    }

    @Override
    public String toString() {
        String descricao = fabricaConfig.getSimpleName() + "/n";

        for (Iterator iterator = proppriedadesBasicas.entrySet().iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            descricao += next + "/n";
        }
        return descricao;
    }

}
