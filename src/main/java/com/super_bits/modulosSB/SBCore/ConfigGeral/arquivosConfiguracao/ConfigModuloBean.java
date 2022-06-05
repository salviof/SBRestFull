/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author SalvioF
 */
@InfoObjetoSB(plural = "Configurações de Modulo", tags = {"Configuração de Modulos"})
public class ConfigModuloBean extends ItemSimples implements Serializable {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    private final ConfigModulo configuracao;
    private List<String> campos;
    private Map<String, String> mapaValores;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nomeModulo;

    @Deprecated
    public ConfigModuloBean() {
        this.configuracao = null;
    }

    public ConfigModuloBean(ConfigModulo pConfig) {
        if (pConfig == null) {
            throw new UnsupportedOperationException("Uma configuração de módulo é obrigatŕia para construir um " + this.getClass().getSimpleName());
        }
        configuracao = pConfig;

        nomeModulo = "Configurações do Modulo " + pConfig.fabricaConfig.getSimpleName();
        id = nomeModulo.hashCode();
        buildCampos();
    }

    private void buildCampos() {
        mapaValores = new HashMap<>();
        campos = new ArrayList<>();
        Enumeration propriedadeExistentes = configuracao.proppriedadesBasicas.propertyNames();
        while (propriedadeExistentes.hasMoreElements()) {
            String propriedade = (String) propriedadeExistentes.nextElement();
            campos.add(propriedade);
            mapaValores.put(propriedade, configuracao.proppriedadesBasicas.getProperty(propriedade));
        }
    }

    public List<String> getCampos() {

        return campos;
    }

    @Override
    public int getId() {
        return id;
    }

    public boolean salvarAlteracoes() {
        Properties prop = new Properties();
        for (String propriedade : mapaValores.keySet()) {
            prop.setProperty(propriedade, mapaValores.get(propriedade));
        }
        return configuracao.salvarPropriedades(prop);
    }

    public class ExtensorPropriedadeSimples {

        private final String nomePropriedade;

        public ExtensorPropriedadeSimples(String propriedade) {
            nomePropriedade = propriedade;
        }

        public String getValor() {
            return mapaValores.get(nomePropriedade);
        }

        public void setValor(String pNovo) {
            mapaValores.put(nomePropriedade, pNovo);
        }
    }

    /**
     *
     * @param p
     * @return
     */
    public ExtensorPropriedadeSimples getPropriedadeCampo(String p) {
        return new ExtensorPropriedadeSimples(p);
    }

    public String getNomeModulo() {
        return nomeModulo;
    }

    public void setNomeModulo(String nomeModulo) {
        this.nomeModulo = nomeModulo;
    }

}
