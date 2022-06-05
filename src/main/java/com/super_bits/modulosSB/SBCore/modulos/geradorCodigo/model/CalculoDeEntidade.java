/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaCampoDinamicoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaCampoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.AtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_DECLARACAO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_ORIGEM_VALOR_CAMPO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

import java.util.List;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = "Calculo de Entidade", plural = "Calculos")
public class CalculoDeEntidade extends ItemSimples implements ItfEstruturaCampoDinamicoEntidade {

    private final EstruturaCampo estruturaCampo;

    public CalculoDeEntidade(String nomeEnum, String descricao, String tipoRetorno, AtributoObjetoSB pCampo, EstruturaCampo pEstrutura, String javaDoc) {

        estruturaCampo = pEstrutura;
        this.nomeEnum = nomeEnum;
        this.descricao = descricao;
        this.tipoRetorno = tipoRetorno;
        this.javaDoc = javaDoc;

    }

    private String tipoRetorno;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nomeEnum;
    private String descricao;
    private String javaDoc;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;

    public String getTipoRetorno() {
        return tipoRetorno;
    }

    public void setTipoRetorno(String tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

    public String getNomeEnum() {
        return nomeEnum;
    }

    public void setNomeEnum(String nomeEnum) {
        this.nomeEnum = nomeEnum;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    public String getJavaDoc() {
        return javaDoc;
    }

    public void setJavaDoc(String javaDoc) {
        this.javaDoc = javaDoc;
    }

    @Override
    public String getClasseCampoDeclaradoOuTipoLista() {
        return estruturaCampo.getClasseCampoDeclaradoOuTipoLista();
    }

    @Override
    public ItfEstruturaDeEntidade getEstruturaPai() {
        return estruturaCampo.getEstruturaPai();
    }

    @Override
    public FabTipoAtributoObjeto getFabricaTipoAtributo() {
        return estruturaCampo.getFabricaTipoAtributo();
    }

    @Override
    public String getFraseValidacao() {
        return estruturaCampo.getFraseValidacao();
    }

    @Override
    public String getIdComponente() {
        return estruturaCampo.getIdComponente();
    }

    @Override
    public String getLabel() {
        return estruturaCampo.getLabel();
    }

    @Override
    public String getLabelPadrao() {
        return estruturaCampo.getLabelPadrao();
    }

    @Override
    public String getMascara() {
        return estruturaCampo.getMascara();
    }

    @Override
    public String getMascaraJqueryMode() {
        return estruturaCampo.getMascaraJqueryMode();
    }

    @Override
    public String getNomeDeclarado() {
        return estruturaCampo.getNomeDeclarado();
    }

    @Override
    public int getNumeroDeCasasDecimais() {
        return estruturaCampo.getNumeroDeCasasDecimais();
    }

    @Override
    public TIPO_ORIGEM_VALOR_CAMPO getOrigemValor() {
        return estruturaCampo.getOrigemValor();
    }

    @Override
    public char getSeparadorDecimal() {
        return estruturaCampo.getSeparadorDecimal();
    }

    @Override
    public char getSeparadorMilhar() {
        return estruturaCampo.getSeparadorMilhar();
    }

    @Override
    public String getTipoCampoSTR() {
        return estruturaCampo.getTipoCampoSTR();
    }

    @Override
    public TIPO_DECLARACAO getTipoDeclaracao() {
        return estruturaCampo.getTipoDeclaracao();
    }

    @Override
    public TIPO_PRIMITIVO getTipoPrimitivoDoValor() {
        return estruturaCampo.getTipoPrimitivoDoValor();
    }

    @Override
    public String getTipoVisualizacao() {
        return estruturaCampo.getTipoVisualizacao();
    }

    @Override
    public String getValidacaoRegex() {
        return estruturaCampo.getValidacaoRegex();
    }

    @Override
    public int getValorMaximo() {
        return estruturaCampo.getValorMaximo();
    }

    @Override
    public int getValorMinimo() {
        return estruturaCampo.getValorMinimo();
    }

    @Override
    public String getValorPadrao() {
        return estruturaCampo.getValorPadrao();
    }

    @Override
    public boolean isMoeda() {
        return estruturaCampo.isMoeda();
    }

    @Override
    public boolean isNumeral() {
        return estruturaCampo.isNumeral();
    }

    @Override
    public boolean isObrigatorio() {
        return estruturaCampo.isObrigatorio();
    }

    @Override
    public boolean isSomenteLeitura() {
        return estruturaCampo.isSomenteLeitura();
    }

    @Override
    public boolean isTemMascara() {
        return estruturaCampo.isTemMascara();
    }

    @Override
    public boolean isTemValidacaoMaximo() {
        return estruturaCampo.isTemValidacaoMaximo();
    }

    @Override
    public boolean isTemValidacaoMinimo() {
        return estruturaCampo.isTemValidacaoMinimo();
    }

    @Override
    public boolean isTemValidacaoRegex() {
        return estruturaCampo.isTemValidacaoRegex();
    }

    @Override
    public boolean isUmCampoDinamico() {
        return estruturaCampo.isUmCampoDinamico();
    }

    @Override
    public boolean isUmValorComLista() {
        return estruturaCampo.isUmValorComLista();
    }

    @Override
    public boolean isUmValorLivre() {
        return estruturaCampo.isUmValorLivre();
    }

    @Override
    public boolean isUmValorMultiploComLista() {
        return estruturaCampo.isUmValorMultiploComLista();
    }

    @Override
    public boolean isUmValorMultiploLivre() {
        return estruturaCampo.isUmValorLivre();
    }

    @Override
    public void setDescricao(String pDescricao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFabricaTipoAtributo(FabTipoAtributoObjeto pTipoCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFraseValidacao(String pFraseValidacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLabelPadrao(String pLabel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setListaDeOpcoes(List<ItfBeanSimples> pLista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMascara(String pMaskara) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNomeDeclarado(String nomeDeclarado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNumeroDeCasasDecimais(int pNumeroCasasDecimais) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setObrigatorio(boolean pObrigatorio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSeparadorDecimal(char pSeparadorDecimal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSeparadorMilhar(char pSeparadorMilhar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSomenteLeitura(boolean pSomenteLeitura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTipoDeclaracao(TIPO_DECLARACAO tipoDeclaracao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValidacaoRegex(String pValidacaoRegex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValorMaximo(int pValorMaximo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValorMinimo(int pValorMinimo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNome(String pNome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeCurto() {
        return estruturaCampo.getNomeCurto();
    }

    @Override
    public String getNome() {
        return estruturaCampo.getNome();
    }

    @Override
    public String getIconeDaClasse() {
        return estruturaCampo.getIconeDaClasse();
    }

    @Override
    public int getId() {
        return estruturaCampo.getId();
    }

    @Override
    public int configIDPeloNome() {
        return estruturaCampo.configIDPeloNome();
    }

    @Override
    public String getNomeDoObjeto() {
        return estruturaCampo.getNomeDoObjeto();
    }

    @Override
    public String getNomeDoObjetoPlural() {
        return estruturaCampo.getNomeDoObjetoPlural();
    }

    @Override
    public void adicionarItemNaLista(String nomeDaLista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTemCampoAnotado(FabTipoAtributoObjeto pCampo) {
        return estruturaCampo.isTemCampoAnotado(pCampo);
    }

    @Override
    public String getImgPequena() {
        return estruturaCampo.getImgPequena();
    }

    @Override
    public String getXhtmlVisao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getXhtmlVisaoMobile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getXhtmlVisao(int numeroColunas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEstruturaPai(ItfEstruturaDeEntidade estruturaPai) {
        estruturaCampo.setEstruturaPai(estruturaPai);
    }

    @Override
    public boolean isTemValorLogico() {
        return estruturaCampo.isTemValorLogico();
    }

    @Override
    public boolean isTemListaDinamica() {
        return estruturaCampo.isTemListaDinamica();
    }

    @Override
    public boolean isTemValidadorLogico() {
        return estruturaCampo.isTemValidadorLogico();
    }

    @Override
    public boolean isAtualizarValorLogicoAoPersistir() {
        return estruturaCampo.isAtualizarValorLogicoAoPersistir();
    }

    @Override
    public void setEnumVinculado(ItfFabrica pFabrica) {
        setFabricaTipoAtributo((FabTipoAtributoObjeto) pFabrica);
    }

    @Override
    public ItfFabrica getEnumVinculado() {
        return getFabricaTipoAtributo();
    }

}
