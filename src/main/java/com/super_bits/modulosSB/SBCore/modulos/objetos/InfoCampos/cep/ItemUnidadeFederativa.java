/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.modulos.localizacao.FabCidadesSemPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.localizacao.FabUnidadesFederativasSemPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfUnidadeFederativa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Transient;

/**
 *
 * @author desenvolvedor
 */
public class ItemUnidadeFederativa extends ItemSimples implements ItfUnidadeFederativa {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;

    private String sigla;

    @Transient
    private ItfLocal vinculoLocalizacao;

    private List<ItfCidade> cidades;

    @Override
    public int getId() {
        configIDPeloNome();
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int pId) {

        this.id = pId;

    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(String pNome) {
        this.nome = pNome;
    }

    @Override
    public List<ItfCidade> getCidades() {
        if (cidades == null || cidades.isEmpty()) {
            if (sigla.toLowerCase().equals("mg")) {
                cidades = (List) FabCidadesSemPersistencia.getCidadesPorEstado(FabUnidadesFederativasSemPersistencia.MG);
            } else {
                cidades = new ArrayList<>();
            }

        }
        return cidades;
    }

    @Override
    public String getSigla() {
        return sigla;
    }

    @Override
    public void setSigla(String pSigla) {
        sigla = pSigla;
    }

    @Override
    public void setCidades(List<ItfCidade> pCidades) {
        System.out.println("cidades do estado não podem ser alteradas");
    }

}
