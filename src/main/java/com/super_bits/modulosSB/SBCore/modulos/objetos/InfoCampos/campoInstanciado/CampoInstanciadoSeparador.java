/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfValidacao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.estadoFormulario.FabEstadoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ItfCalculoValorLogicoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.ItfPropriedadesReflexaoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_ORIGEM_VALOR_CAMPO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 *
 *
 *
 * @author desenvolvedor
 */
public class CampoInstanciadoSeparador implements ItfCampoInstanciado {

    private final String nomeDoSeparador;

    public CampoInstanciadoSeparador(String pnome) {
        nomeDoSeparador = pnome;
    }

    @Override
    public Object getParent() {
        return null;
    }

    @Override
    public Object getValor() {
        return nomeDoSeparador;
    }

    @Override
    public void setValor(Object pValor) {

    }

    @Override
    public String getNomeCamponaClasse() {
        return nomeDoSeparador;
    }

    @Override
    public boolean validarCampo() {
        return true;
    }

    @Override
    public boolean isVazio() {
        return false;
    }

    @Override
    public FabTipoAtributoObjeto getFabricaTipoAtributo() {
        return FabTipoAtributoObjeto.CAMPO_SEPARADOR;
    }

    @Override
    public TIPO_PRIMITIVO getTipoPrimitivoDoValor() {
        return TIPO_PRIMITIVO.LETRAS;
    }

    @Override
    public String getTipoVisualizacao() {
        return "linha";
    }

    @Override
    public String getLabel() {
        return nomeDoSeparador;
    }

    @Override
    public String getIdComponente() {
        throw new UnsupportedOperationException("Id do componente não é suportadno em campo Instanciado separador"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescricao() {
        return "Separador para montagem de formularios";
    }

    @Override
    public String getMascara() {
        throw new UnsupportedOperationException("Mascaras não são suportadas em campos intanciado Separador"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getValorPadrao() {
        return nomeDoSeparador;
    }

    @Override
    public boolean isObrigatorio() {
        return false;
    }

    @Override
    public int getValorMaximo() {
        return 0;
    }

    @Override
    public int getValorMinimo() {
        return 0;
    }

    @Override
    public List<ItfBeanSimples> getListaDeOpcoes() {
        return null;
    }

    @Override
    public String getValidacaoRegex() {
        return null;
    }

    @Override
    public boolean isTemValidacaoRegex() {
        return false;
    }

    @Override
    public boolean isTemValidacaoMinimo() {
        return false;
    }

    @Override
    public boolean isTemValidacaoMaximo() {
        return false;
    }

    @Override
    public boolean isTemMascara() {
        return false;
    }

    @Override
    public boolean isNumeral() {
        return false;
    }

    @Override
    public boolean isMoeda() {
        return false;
    }

    @Override
    public char getSeparadorDecimal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public char getSeparadorMilhar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumeroDeCasasDecimais() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMascaraJqueryMode() {
        return null;
    }

    @Override
    public String getTipoCampoSTR() {
        return FabTipoAtributoObjeto.TEXTO_SIMPLES.toString();
    }

    @Override
    public String getFraseValidacao() {
        return null;
    }

    @Override
    public String getNomeCurto() {
        return nomeDoSeparador;
    }

    @Override
    public String getNome() {
        return nomeDoSeparador;
    }

    @Override
    public int getId() {
        return -1;
    }

    @Override
    public String getImgPequena() {
        return null;
    }

    @Override
    public int configIDPeloNome() {
        return 0;
    }

    @Override
    public String getNomeDoObjeto() {
        return nomeDoSeparador;
    }

    @Override
    public boolean isUmCampoNaoInstanciado() {
        return true;
    }

    @Override
    public String getLabelSlug() {
        return UtilSBCoreStringFiltros.gerarUrlAmigavel(getLabel());
    }

    @Override
    public int getIndiceValorLista() {
        throw new UnsupportedOperationException("Indice de calor não é compatível com separador."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIndiceValorLista(int pIndice) {

    }

    @Override
    public ItfComponenteVisualSB getComponenteDiferenciado(ItfComponenteVisualSB pComponente) {
        return FabCompVisualInputs.TEXTO_SEM_FORMATACAO.getRegistro();

    }

    @Override
    public ItfComponenteVisualSB getComponenteVisualPadrao() {
        return FabCompVisualInputs.TEXTO_SEM_FORMATACAO.getRegistro();
    }

    @Override
    public TIPO_ORIGEM_VALOR_CAMPO getOrigemValor() {
        return TIPO_ORIGEM_VALOR_CAMPO.VALOR_LIVRE;
    }

    @Override
    public boolean isUmValorLivre() {
        return true;
    }

    @Override
    public boolean isUmValorComLista() {
        return false;
    }

    @Override
    public boolean isUmValorMultiploLivre() {
        return false;
    }

    @Override
    public boolean isUmValorMultiploComLista() {
        return false;
    }

    @Override
    public String getNomeUnicoParaIDHtml(ItfComponenteVisualSB pComponente) {
        return "SEPARADOR";
    }

    @Override
    public String getPrefixoUnicoParaIDHtml() {
        return "SEPARADOR";
    }

    @Override
    public boolean isTemDescricao() {
        return false;
    }

    @Override
    public void adicionarItemNaLista(String nomeDaLista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIconeDaClasse() {
        return "fa fa-minus";
    }

    @Override
    public String getXhtmlVisao() {
        return MapaObjetosProjetoAtual.getVisualizacaoDoObjeto(this.getClass());
    }

    @Override
    public boolean isTemCampoAnotado(FabTipoAtributoObjeto pCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSomenteLeitura() {
        return true;
    }

    @Override
    public ItfBeanSimples getObjetoDoAtributo() {
        return (ItfBeanSimples) getParent();
    }

    @Override
    public ItfCampoInstArquivoEntidade getComoArquivoDeEntidade() {
        return null;
    }

    @Override
    public boolean isUmCampoArquivoEntidade() {
        return false;
    }

    @Override
    public ItfAssistenteDeLocalizacao getComoCampoLocalizacao() {
        return null;
    }

    @Override
    public boolean isUmCampoCampoLocalizacao() {
        return false;
    }

    @Override
    public Method getMetodoGet() throws NoSuchMethodException {
        return null;
    }

    @Override
    public Method getMetodoSet() throws NoSuchMethodException {
        return null;
    }

    @Override
    public ItfCampoInstSeletorItens getComoSeltorItens() {
        return null;
    }

    @Override
    public ItfCampoInstSeletorItem getComoCampoSeltorItem() {
        throw new UnsupportedOperationException(" um campo separador, não pode ser um seletor de item"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUmCampoDinamico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeClasseAtributoDeclarado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNomeClasseAtributoDeclarado(String pObjetoEntidade) {

    }

    @Override
    public String getCaminhoListagem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeClasseOrigemAtributo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNomeClasseOrigemAtributo(String pObjeto) {

    }

    @Override
    public GrupoCampos getGrupoSubCamposExibicao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CampoInstanciadoEnumFabricaObjeto getComoEnumFabricaObjeto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLabelPadrao() {
        return "";
    }

    @Override
    public String getNomeDoObjetoPlural() {
        return "Campo não instanciado";
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciadoRaiz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCampoInstanciadoRaiz(ItfCampoInstanciado pCampoInstanciado) {

    }

    @Override
    public String getNomeCompostoIdentificador() {
        return getNomeCamponaClasse();
    }

    @Override
    public boolean isCampoNaoInstanciado() {
        return false;
    }

    @Override
    public CampoInstanciadoSubItens getComoSubItens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfComponenteVisualSB getComponenteVisualPadraoCompacto() {
        return getComponenteVisualPadrao();
    }

    @Override
    public List<String> getTemplateCamposDinamicos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfCampoInstTemplate getComoTemplate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfCampoInstanciadoVerdadeiroOuFalso getComoCampoVerdadeiroOuFalso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfPropriedadesReflexaoCampos getPropriedadesRefexao() {
        return null;
    }

    @Override
    public boolean isUmValorNuloOuEmBranco() {
        return true;
    }

    @Override
    public String getValorTextoFormatado() {
        return "Separador";
    }

    @Override
    public boolean isUmaListagemParticular() {
        return false;
    }

    @Override
    public String getTextoPositivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTextoNegativo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIconePositivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIconeNegativo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfCampoInstSeletor getComoCampoComListaDeOpcoes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GrupoCampos getGrupoCampoExibicao() {
        return null;
    }

    @Override
    public boolean isValorCampoUnico() {
        return false;
    }

    @Override
    public ItfValidacao getValidacaoLogicaEstrategia() {
        return null;
    }

    @Override
    public boolean isTemValidadacaoLogica() {
        return false;
    }

    @Override
    public boolean isUmValorLogico() {
        return false;
    }

    @Override
    public boolean isUmaListaDinamica() {
        return false;
    }

    @Override
    public boolean validarCampo(Object pValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void preenchimentoAleatorio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contem(Object pParametro, int pIndice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contem(Object pParametro) {
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
    public void bloquearProximaAlteracao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUmValorEmLista() {
        return false;
    }

    @Override
    public void setStatusFormularioExibicao(FabEstadoFormulario pStatusForm) {

    }

    @Override
    public boolean isPermitirCadastroManualEndereco() {
        return false;
    }

    @Override
    public boolean isAtualizarValorLogicoAoPersistir() {
        return false;
    }

    @Override
    public ItfCalculoValorLogicoAtributoObjeto getValorLogicaEstrategia() {
        return null;
    }

}
