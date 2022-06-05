/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocalidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfUnidadeFederativa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class ItemCidade extends ItemSimples implements ItfCidade {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.LC_UNIDADE_FEDERATIVA)
    private ItfUnidadeFederativa unidadeFederativa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LC_LOCALIDADE)
    private ItfLocalidade localidade;

    private ItfLocal vinculoLocalizacao;

    private final List<ItfBairro> listaDeBairros;

    public ItemCidade() {
        super();
        this.listaDeBairros = new ArrayList<>();
        unidadeFederativa = new ItemUnidadeFederativa();
    }

    @Override
    public void setId(int pId) {

        this.id = pId;

    }

    @Override
    public String getNome() {
        configIDPeloNome();
        return this.nome;
    }

    @Override
    public void setNome(String pNome) {

        this.nome = pNome;

    }

    @Override
    public ItfUnidadeFederativa getUnidadeFederativa() {

        return this.unidadeFederativa;

    }

    @Override
    public List<ItfBairro> getBairros() {

        return this.listaDeBairros;

    }

    @Override
    public String getEstadoPontoNomeCidade() {
        return getUnidadeFederativa() + getNome();
    }

    @Override
    public ItfLocalidade getLocalidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLocalidade(ItfLocalidade pLocalidade) {
        localidade = pLocalidade;
    }

    @Override
    public void setUnidadeFederativa(ItfUnidadeFederativa pUnidadeFederativa) {
        unidadeFederativa = pUnidadeFederativa;
    }

}
