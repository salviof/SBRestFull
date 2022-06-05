/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class ItemBairro extends ItemSimples implements ItfBairro {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;

    private List<Long> coordenadas = new ArrayList<Long>();

    @InfoCampo(tipo = FabTipoAtributoObjeto.LC_CIDADE)
    private ItemCidade cidade;

    public ItemBairro() {
        cidade = new ItemCidade();
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
    public List<Long> getCordenadas() {

        return this.coordenadas;

    }

    @Override
    public ItfCidade getCidade() {
        return cidade;
    }

    @Override
    public void setCidade(ItfCidade pCidade) {
        cidade = (ItemCidade) pCidade;
    }

}
