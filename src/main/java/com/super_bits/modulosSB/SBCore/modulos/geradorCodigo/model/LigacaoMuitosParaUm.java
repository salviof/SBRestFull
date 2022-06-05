/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfLigacaoMuitosParaUm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.FabTipoBeanSBGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaCampoDinamicoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaCampoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfLigacaoMuitosParaMuitos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfLigacaoUmParaMuitos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfListaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = {"Ligação Muitos para um"}, plural = "Ligações muitos para um")
public class LigacaoMuitosParaUm extends ItemSimples implements ItfLigacaoMuitosParaUm {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    private String label, descricao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nomeDeclarado;
    private final ItfEstruturaDeEntidade estruturaObjeto;
    private final Class classeObjetoVinculado;
    private ItfEstruturaDeEntidade estruturaPai;

    public LigacaoMuitosParaUm(ItfEstruturaCampoEntidade campo) {

        classeObjetoVinculado = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(campo.getClasseCampoDeclaradoOuTipoLista());
        nomeDeclarado = campo.getNomeDeclarado();
        label = campo.getLabel();
        descricao = campo.getLabel();
        estruturaPai = campo.getEstruturaPai();
        estruturaObjeto = (ItfEstruturaDeEntidade) MapaObjetosProjetoAtual.getEstruturaObjeto(classeObjetoVinculado);
        estruturaPai = campo.getEstruturaPai();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     *
     * @return O Nome do declarado atributo do objeto: <br>
     * Ex: private String nomeTeste; <- retorna nomeTeste
     */
    public String getNomeDeclarado() {
        return nomeDeclarado;
    }

    public void setNomeDeclarado(String nomeDeclarado) {
        this.nomeDeclarado = nomeDeclarado;
    }

    @Override
    public void adicionarCampo(Field pCampo) {
        estruturaObjeto.adicionarCampo(pCampo);
    }

    @Override
    public void adicionarEnum(String enums) {
        estruturaObjeto.adicionarEnum(enums);
    }

    @Override
    public void adicionarTags(String pTag) {
        estruturaObjeto.adicionarTags(pTag);
    }

    @Override
    public List<ItfEstruturaCampoDinamicoEntidade> getCalculos() {
        return estruturaObjeto.getCalculos();
    }

    @Override
    public ItfEstruturaCampoEntidade getCampoByNomeDeclarado(String pNome) {
        return estruturaObjeto.getCampoByNomeDeclarado(pNome);
    }

    @Override
    public List<ItfEstruturaCampoEntidade> getCampos() {
        return estruturaObjeto.getCampos();
    }

    @Override
    public String getIcone() {
        return estruturaObjeto.getIcone();
    }

    @Override
    public String getIconeDaClasse() {
        return estruturaObjeto.getIconeDaClasse();
    }

    @Override
    public List<String> getListaEnum() {
        return estruturaObjeto.getListaEnum();
    }

    @Override
    public List<ItfListaDeEntidade> getListas() {
        return estruturaObjeto.getListas();
    }

    @Override
    public List<ItfLigacaoMuitosParaMuitos> getMuitosParaMuitos() {
        return estruturaObjeto.getMuitosParaMuitos();
    }

    @Override
    public List<ItfLigacaoMuitosParaUm> getMuitosParaUm() {
        return estruturaObjeto.getMuitosParaUm();
    }

    @Override
    public String getNomeEntidade() {
        return estruturaObjeto.getNomeEntidade();
    }

    @Override
    public String getPlural() {
        return estruturaObjeto.getPlural();
    }

    @Override
    public List<String> getTags() {
        return estruturaObjeto.getTags();
    }

    @Override
    public FabTipoBeanSBGenerico getTipoEntidade() {
        return estruturaObjeto.getTipoEntidade();
    }

    @Override
    public List<ItfLigacaoUmParaMuitos> getUmParaMuitos() {
        return estruturaObjeto.getUmParaMuitos();
    }

    @Override
    public void setIcone(String icone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setListaEnum(List<String> listaEnum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMuitosParaMuitos(List<ItfLigacaoMuitosParaMuitos> muitosParaMuitos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMuitosParaUm(List<ItfLigacaoMuitosParaUm> muitosParaUm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNomeEntidade(String nomeEntidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPlural(String plural) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTags(List<String> tags) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTipoEntidade(FabTipoBeanSBGenerico tipoEntidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUmParaMuitos(List<ItfLigacaoUmParaMuitos> umParaMuitos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Class getClasseObjetoVinculado() {
        return classeObjetoVinculado;
    }

    public ItfEstruturaDeEntidade getEstruturaObjeto() {
        return estruturaObjeto;
    }

    @Override
    public void setCalculos(List<ItfEstruturaCampoDinamicoEntidade> calculos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCampos(List<ItfEstruturaCampoEntidade> campos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setListas(List<ItfListaDeEntidade> listas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItfEstruturaCampoEntidade> getCamposComListaDinamica() {
        return estruturaObjeto.getCamposComListaDinamica();
    }

    @Override
    public List<ItfEstruturaCampoEntidade> getCamposComValidadorLogico() {
        return estruturaObjeto.getCamposComValidadorLogico();
    }

    @Override
    public boolean isTemCampoListaDinamica() {
        return estruturaObjeto.isTemCampoListaDinamica();
    }

    @Override
    public boolean isTemCampoValorLogico() {
        return estruturaObjeto.isTemCampoValorLogico();
    }

    @Override
    public boolean isTemCampoValidadoresLogicos() {
        return estruturaObjeto.isTemCampoValidadoresLogicos();
    }

    @Override
    public List<ItfEstruturaCampoEntidade> getCamposComValorLogico() {
        return estruturaObjeto.getCamposComValidadorLogico();
    }

    @Override
    public String getSlugIdentificador() {
        return estruturaObjeto.getSlugIdentificador(); //To change body of generated methods, choose Tools | Templates.
    }

}
