package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.io.Serializable;
import java.util.List;

@InfoObjetoSB(tags = {"Atributo de Objeto"}, plural = "Atributos de Objeto", fabricaVinculada = FabTipoAtributoObjeto.class)
public class TipoAtributoObjetoSB extends ItemSimples implements Serializable, ItfTipoAtributoSB, ItfBeanSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private FabTipoAtributoObjeto tipoCampo;

    private TIPO_PRIMITIVO tipoValor;

    private TIPO_ORIGEM_VALOR_CAMPO tipoOrigemValor;

    private String tipoVisualizacao;

    private boolean somenteLeitura;

    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;

    private String mascara;

    private String valorPadrao;

    private String labelPadrao;

    private String descricao;

    public int[] seguranca = {1};

    public List<ItfBeanSimples> listaDeOpcoes;

    protected boolean obrigatorio;

    private int valorMaximo = 99999;

    private int valorMinimo;

    private String validacaoRegex;

    private char separadorDeciamal, separadorMilhar;

    private int numCasasDecimais;

    private String fraseValidacao;

    @Deprecated
    public TipoAtributoObjetoSB() {

    }

    @Override
    public void setNome(String pNome) {
        nome = pNome;
    }

    private void configFabricaCampo(FabTipoAtributoObjeto pTipo) {
        tipoCampo = pTipo;
        tipoVisualizacao = pTipo.toString().toUpperCase();
        tipoOrigemValor = pTipo.getTipoOrigemPadrao();
        pTipo.configuraPropriedadesBasicas(this);

    }

    public TipoAtributoObjetoSB(FabTipoAtributoObjeto pTipo) {

        configFabricaCampo(pTipo);

    }

    @Deprecated
    public FabTipoAtributoObjeto getTipoCampo() {
        return tipoCampo;
    }

    @Deprecated
    public void setTipoCampo(FabTipoAtributoObjeto tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    @Override
    public boolean isSomenteLeitura() {
        return somenteLeitura;
    }

    @Override
    public void setSomenteLeitura(boolean somenteLeitura) {
        this.somenteLeitura = somenteLeitura;
    }

    @Override
    public FabTipoAtributoObjeto getFabricaTipoAtributo() {
        return tipoCampo;
    }

    private boolean isOrigemDeste(TIPO_ORIGEM_VALOR_CAMPO pOrigem) {
        return tipoOrigemValor.equals(pOrigem);
    }

    @Override
    public final void setFabricaTipoAtributo(FabTipoAtributoObjeto tipoCampo) {

        this.tipoCampo = tipoCampo;
        configFabricaCampo(tipoCampo);
    }

    @Override
    public TIPO_PRIMITIVO getTipoPrimitivoDoValor() {
        return tipoCampo.getTipoPrimitivo();
    }

    @Override
    public String getTipoVisualizacao() {
        return tipoVisualizacao;
    }

    public void setTipoVisualizacao(String tipoVisualizacao) {
        this.tipoVisualizacao = tipoVisualizacao;
    }

    @Override
    public String getMascara() {
        return mascara;
    }

    @Override
    public final void setMascara(String mascara) {
        this.mascara = mascara;
    }

    @Override
    public String getValorPadrao() {
        return valorPadrao;
    }

    public void setValorPadrao(String valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    public int[] getSeguranca() {
        return seguranca;
    }

    public void setSeguranca(int[] seguranca) {
        this.seguranca = seguranca;
    }

    @Override
    public int getValorMaximo() {
        if (valorMaximo == 0) {
            valorMaximo = InfoCampo.MAX_PADRAO;
        }
        return valorMaximo;
    }

    @Override
    public void setValorMaximo(int valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    @Override
    public int getValorMinimo() {
        return valorMinimo;
    }

    @Override
    public void setValorMinimo(int valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    @Override
    public boolean isObrigatorio() {
        return obrigatorio;
    }

    @Override
    @Deprecated
    public String getLabel() {
        return labelPadrao;
    }

    @Override
    public String getLabelPadrao() {
        return labelPadrao;
    }

    @Override
    public final void setLabelPadrao(String pLabel) {
        this.labelPadrao = pLabel;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public final void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public final void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    @Override
    public final String getIdComponente() {
        return UtilSBCoreStringFiltros.gerarUrlAmigavel(getLabel());
    }

    @Override
    public final void setListaDeOpcoes(List<ItfBeanSimples> pLista) {
        listaDeOpcoes = pLista;
    }

    @Override
    public final String getValidacaoRegex() {
        return validacaoRegex;
    }

    @Override
    public final void setValidacaoRegex(String validacaoRegex) {
        this.validacaoRegex = validacaoRegex;
    }

    @Override
    public boolean isTemValidacaoRegex() {

        return UtilSBCoreStringValidador.isNAO_NuloNemBranco(validacaoRegex);

    }

    @Override
    public boolean isTemValidacaoMinimo() {

        return valorMinimo > 0;
    }

    @Override
    public boolean isTemValidacaoMaximo() {
        return (valorMaximo != InfoCampo.MAX_PADRAO && valorMaximo > 0);
    }

    @Override
    public boolean isTemMascara() {
        if (mascara == null) {
            return false;
        }
        return !mascara.isEmpty();
    }

    @Override
    public boolean isNumeral() {
        return tipoCampo.getTipoPrimitivo().isNumeral();
    }

    @Override
    public boolean isMoeda() {
        return !(tipoCampo != FabTipoAtributoObjeto.MOEDA_REAL && tipoCampo != FabTipoAtributoObjeto.MOEDA_DOLAR);
    }

    @Override
    public char getSeparadorDecimal() {
        return this.separadorDeciamal;
    }

    @Override
    public void setSeparadorDecimal(char pSeparadorDecimal) {
        this.separadorDeciamal = pSeparadorDecimal;
    }

    @Override
    public char getSeparadorMilhar() {
        return separadorMilhar;
    }

    @Override
    public void setSeparadorMilhar(char pSeparadorMilhar) {
        this.separadorMilhar = pSeparadorMilhar;
    }

    @Override
    public int getNumeroDeCasasDecimais() {
        return numCasasDecimais;
    }

    @Override
    public void setNumeroDeCasasDecimais(int pNumeroDeCasasDecimais) {
        this.numCasasDecimais = pNumeroDeCasasDecimais;
    }

    @Override
    public String getMascaraJqueryMode() {
        return UtilSBCoreStringFiltros.getMascaraJavaMaskParaJQueryMask(mascara);
    }

    @Override
    public String getTipoCampoSTR() {
        return tipoCampo.toString();
    }

    @Override
    public String getImgPequena() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeCurto() {
        return tipoCampo.toString();
    }

    @Override
    public int getId() {
        return getNome().hashCode();
    }

    @Override
    public String getNome() {
        return tipoCampo.toString();
    }

    @Override
    public int configIDPeloNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeDoObjeto() {

        InfoObjetoSB info = UtilSBCoreReflexaoObjeto.getInfoClasseObjeto(this.getClass());

        if (info != null) {
            return info.tags()[0];
        } else {
            return labelPadrao;

        }

    }

    @Override
    public String getNomeDoObjetoPlural() {
        InfoObjetoSB info = UtilSBCoreReflexaoObjeto.getInfoClasseObjeto(this.getClass());

        if (info != null) {
            return info.plural();
        } else {
            return labelPadrao;

        }
    }

    @Override
    public final String getFraseValidacao() {
        return fraseValidacao;
    }

    @Override
    public final void setFraseValidacao(String fraseValidacao) {
        this.fraseValidacao = fraseValidacao;
    }

    public String getStrTipoInput() {
        return tipoCampo.getTipo_input_prime().getRegistro().getXhtmlJSF();
    }

    @Override
    public TIPO_ORIGEM_VALOR_CAMPO getOrigemValor() {
        return tipoOrigemValor;
    }

    @Override
    public boolean isUmValorLivre() {
        return isOrigemDeste(TIPO_ORIGEM_VALOR_CAMPO.VALORES_LIVRE);
    }

    @Override
    public boolean isUmValorComLista() {
        return isOrigemDeste(TIPO_ORIGEM_VALOR_CAMPO.VALOR_COM_LISTA);
    }

    @Override
    public boolean isUmValorMultiploLivre() {
        return isOrigemDeste(TIPO_ORIGEM_VALOR_CAMPO.VALORES_LIVRE);
    }

    @Override
    public boolean isUmValorMultiploComLista() {

        switch (tipoCampo) {
            case LC_CIDADE:
            case LC_BAIRRO:
            case LC_UNIDADE_FEDERATIVA:
            case LISTA_OBJETOS_PUBLICOS:

                return true;
            default:
                return false;

        }

    }

    public final void setTipoOrigemValor(TIPO_ORIGEM_VALOR_CAMPO tipoOrigemValor) {
        this.tipoOrigemValor = tipoOrigemValor;
    }

    @Override
    public void adicionarItemNaLista(String nomeDaLista) {
        throw new UnsupportedOperationException("O metodo adicionar Item na lista não é suportado em Beans do tipo campo"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIconeDaClasse() {
        return "fa-pencil-square-o";
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
    public boolean isUmCampoDinamico() {
        return this instanceof ItfAtributoObjetoSB;
    }

    @Override
    public String toString() {
        return getTipoCampoSTR();
    }

    @Override
    public boolean isAtualizarValorLogicoAoPersistir() {
        return false;
    }

    @Override
    public void setEnumVinculado(ItfFabrica pFabrica) {
        tipoCampo = (FabTipoAtributoObjeto) pFabrica;
    }

    @Override
    public ItfFabrica getEnumVinculado() {
        return tipoCampo;
    }

}
