/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.PropriedadesReflexaoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanNormal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class AtributoObjetoSB extends AtributoObjetoSBAbstrato {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private final int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String label;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_DESCRITIVO)
    private String descricao;
    private ItfGrupoCampos grupoSubCamposExibicao;

    private List<ItfBeanSimples> listaDeOpcoes;

    private String iconeNegativo;
    private String iconePositivo;
    private boolean temValidacaoLogica;
    private boolean umValorLogico;
    private boolean umaListaDinamica;
    private String textoPositivo;
    private String textoNegativo;
    private boolean atualizarValorLogicoAoPersistir;

    private boolean permitirCadastroManualEndereco;

    private boolean valorCampoUnico;

    public AtributoObjetoSB(PropriedadesReflexaoCampo pPropriedadesReflexao) {
        super(pPropriedadesReflexao);
        id = super.idAtributo;
    }

    /**
     *
     * Retorna uma lista de opções para o caso de ManyToOne
     *
     * @return Lista com opções de seleção
     */
    @Override
    public final List<ItfBeanSimples> getListaDeOpcoes() {
        try {
            if (listaDeOpcoes == null || listaDeOpcoes.isEmpty()) {
                //          listaDeOpcoes = SBCore.getCentralFonteDeDados().getListaOpcoesCampo(propriedadesReflexao);
                throw new UnsupportedOperationException("A lista de Opções só pode ser obtida por um campo instanciado");
            }
            return listaDeOpcoes;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo lista de opções para o campo" + getLabel() + "em" + nomeClasseAtributo, t);
            return new ArrayList<>();
        }
    }

    @Override
    public String getNomeClasseAtributoDeclarado() {
        return nomeClasseAtributo;
    }

    @Override
    public void setNomeClasseAtributoDeclarado(String pObjetoEntidade) {

    }

    @Override
    public String getCaminhoListagem() {
        return caminhoListagem;
    }

    @Override
    public void setListaDeOpcoes(List<ItfBeanSimples> pLista) {
        tipoAtributo.setListaDeOpcoes(pLista);
    }

    @Override
    public void setDescricao(String pDescricao) {
        descricao = pDescricao;
    }

    @Override
    public void setFraseValidacao(String pFraseValidacao) {
        tipoAtributo.setFraseValidacao(pFraseValidacao);
    }

    @Override
    public void setMascara(String pMaskara) {
        tipoAtributo.setMascara(pMaskara);
    }

    @Override
    public void setValorMaximo(int pValorMaximo) {
        tipoAtributo.setValorMaximo(pValorMaximo);
    }

    @Override
    public void setValorMinimo(int pValorMinimo) {
        tipoAtributo.setValorMinimo(pValorMinimo);
    }

    @Override
    public void setValidacaoRegex(String pValidacaoRegex) {
        tipoAtributo.setValidacaoRegex(pValidacaoRegex);
    }

    @Override
    public void setLabel(String pLabel) {
        label = pLabel;

    }

    @Override
    public void setSeparadorDecimal(char pSeparadorDecimal) {
        tipoAtributo.setSeparadorDecimal(pSeparadorDecimal);
    }

    @Override
    public void setSeparadorMilhar(char pSeparadorMilhar) {
        tipoAtributo.setSeparadorMilhar(pSeparadorMilhar);
    }

    @Override
    public void setNumeroDeCasasDecimais(int pNumeroCasasDecimais) {
        tipoAtributo.setNumeroDeCasasDecimais(pNumeroCasasDecimais);
    }

    @Override
    public void setSomenteLeitura(boolean pSomenteLeitura) {
        tipoAtributo.setSomenteLeitura(pSomenteLeitura);
    }

    @Override
    public void setObrigatorio(boolean pObrigatorio) {
        tipoAtributo.setObrigatorio(pObrigatorio);
    }

    @Override
    public void setFabricaTipoAtributo(FabTipoAtributoObjeto pTipoCampo) {
        tipoAtributo.setFabricaTipoAtributo(pTipoCampo);
    }

    @Override
    public boolean isUmCampoDinamico() {
        return tipoAtributo.isUmCampoDinamico();
    }

    @Override
    public FabTipoAtributoObjeto getFabricaTipoAtributo() {
        return tipoAtributo.getFabricaTipoAtributo();
    }

    @Override
    public TIPO_PRIMITIVO getTipoPrimitivoDoValor() {
        return tipoAtributo.getFabricaTipoAtributo().getTipoPrimitivo();
    }

    @Override
    public TIPO_ORIGEM_VALOR_CAMPO getOrigemValor() {
        return tipoAtributo.getOrigemValor();
    }

    @Override
    public String getTipoVisualizacao() {
        return tipoAtributo.getTipoVisualizacao();
    }

    @Override
    public String getLabel() {
        if (label == null) {
            label = getLabelPadrao();
        }
        return label;
    }

    @Override
    public boolean isSomenteLeitura() {
        return tipoAtributo.isSomenteLeitura();
    }

    @Override
    public String getIdComponente() {
        return tipoAtributo.getIdComponente();
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getMascara() {
        return tipoAtributo.getMascara();
    }

    @Override
    public String getValorPadrao() {
        return tipoAtributo.getValorPadrao();
    }

    @Override
    public boolean isObrigatorio() {
        return tipoAtributo.isObrigatorio();
    }

    @Override
    public int getValorMaximo() {
        return tipoAtributo.getValorMaximo();
    }

    @Override
    public int getValorMinimo() {
        return tipoAtributo.getValorMinimo();
    }

    @Override
    public String getValidacaoRegex() {
        return tipoAtributo.getValidacaoRegex();
    }

    @Override
    public boolean isTemValidacaoRegex() {
        return tipoAtributo.isTemValidacaoRegex();
    }

    @Override
    public boolean isTemValidacaoMinimo() {
        return tipoAtributo.isTemValidacaoMinimo();
    }

    @Override
    public boolean isTemValidacaoMaximo() {
        return tipoAtributo.isTemValidacaoMaximo();
    }

    @Override
    public boolean isTemMascara() {
        return tipoAtributo.isTemMascara();
    }

    @Override
    public boolean isNumeral() {
        return tipoAtributo.isNumeral();
    }

    @Override
    public boolean isMoeda() {
        return tipoAtributo.isMoeda();
    }

    @Override
    public char getSeparadorDecimal() {
        return tipoAtributo.getSeparadorDecimal();
    }

    @Override
    public char getSeparadorMilhar() {
        return tipoAtributo.getSeparadorMilhar();
    }

    @Override
    public int getNumeroDeCasasDecimais() {
        if (classeDeclaracaoObjeto.equals(int.class) || classeDeclaracaoObjeto.equals(Integer.class)) {
            return 0;
        } else {
            return tipoAtributo.getNumeroDeCasasDecimais();
        }

    }

    @Override
    public String getMascaraJqueryMode() {
        return tipoAtributo.getMascaraJqueryMode();
    }

    @Override
    public String getTipoCampoSTR() {
        return tipoAtributo.getTipoCampoSTR();
    }

    @Override
    public String getFraseValidacao() {
        return tipoAtributo.getFraseValidacao();
    }

    @Override
    public boolean isUmValorLivre() {
        return tipoAtributo.isUmValorMultiploLivre();
    }

    @Override
    public boolean isUmValorComLista() {
        return tipoAtributo.isUmValorComLista();
    }

    @Override
    public boolean isUmValorMultiploLivre() {
        return tipoAtributo.isUmValorMultiploLivre();
    }

    @Override
    public boolean isUmValorMultiploComLista() {
        return tipoAtributo.isUmValorMultiploComLista();
    }

    @Override
    public String getNomeClasseOrigemAtributo() {
        return nomeClasseOrigemAtributo;
    }

    @Override
    public String getNome() {
        return nomeDeclaracao;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setNomeClasseOrigemAtributo(String pObjeto) {
        // nomeClasseOrigemAtributo = pObjeto;
        // nomeClasseOrigemAtributo = pObjeto;
    }

    @Override
    public String getNomeDoObjeto() {
        return UtilSBCoreReflexaoObjeto.getNomeDoObjetoPorAnotacaoInfoClasse(classeDeclaracaoObjeto);
    }

    @Override
    public String getNomeDoObjetoPlural() {
        return UtilSBCoreReflexaoObjeto.getNomeDoObjetoPorAnotacaoInfoClasse(classeDeclaracaoObjeto);
    }

    @Override
    public ItfGrupoCampos getGrupoSubCamposExibicao() {

        Class classeValorDoAtributo = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(nomeClasseAtributo);
        if (classeValorDoAtributo == null) {

            return null;
        } else {

            if (grupoSubCamposExibicao == null) {
                try {
                    Object objetoClasse = classeValorDoAtributo.newInstance();
                    if (objetoClasse instanceof ItfBeanNormal) {
                        grupoSubCamposExibicao = FabGruposPadrao.GRUPO_PADRAO_ITEM_NORMAL.getGrupoCampo(classeValorDoAtributo);
                    } else if (objetoClasse instanceof ItfBeanSimplesSomenteLeitura) {
                        grupoSubCamposExibicao = FabGruposPadrao.GRUPO_PADRAO_ITEM_SIMPLES.getGrupoCampo(classeValorDoAtributo);
                    }
                } catch (Throwable t) {
                    return (GrupoCampos) grupoSubCamposExibicao;
                }
            }
            return grupoSubCamposExibicao;
        }
    }

    @Override
    public void setGrupoSubCamposExibicao(ItfGrupoCampos grupoSubCamposExibicao) {
        this.grupoSubCamposExibicao = grupoSubCamposExibicao;
    }

    @Override
    public void setLabelPadrao(String pLabel) {
        label = pLabel;
    }

    @Override
    public String getLabelPadrao() {
        return tipoAtributo.getLabelPadrao();
    }

    @Override
    public List<String> getTemplateCamposDinamicos() {
        return templatePalavrasChave;
    }

    @Override
    public String getIconeNegativo() {
        return iconeNegativo;
    }

    @Override
    public void setIconeNegativo(String iconeNegativo) {
        this.iconeNegativo = iconeNegativo;
    }

    @Override
    public String getIconePositivo() {
        return iconePositivo;
    }

    /**
     *
     * @param iconePositivo
     */
    @Override
    public void setIconePositivo(String iconePositivo) {
        this.iconePositivo = iconePositivo;
    }

    @Override
    public String getTextoPositivo() {
        return textoPositivo;
    }

    @Override
    public void setTextoPositivo(String textoPositivo) {
        this.textoPositivo = textoPositivo;
    }

    @Override
    public String getTextoNegativo() {
        return textoNegativo;
    }

    @Override
    public void setTextoNegativo(String textoNegativo) {
        this.textoNegativo = textoNegativo;
    }

    @Override
    public void setValorCampoUnico(boolean pValor) {
        valorCampoUnico = pValor;
    }

    @Override
    public boolean isValorCampoUnico() {
        return valorCampoUnico;
    }

    @Override
    public void setUmValorLogico(boolean pValor) {
        umValorLogico = pValor;
    }

    @Override
    public void setUmaListaDinamica(boolean pValor) {
        umaListaDinamica = pValor;
    }

    @Override
    public void setTemValidacaoLogica(boolean pValor) {
        temValidacaoLogica = pValor;
    }

    @Override
    public boolean isUmValorLogico() {
        return umValorLogico;
    }

    @Override
    public boolean isUmaListaDinamica() {
        return umaListaDinamica;
    }

    @Override
    public boolean isTemValidadacaoLogica() {
        return temValidacaoLogica;
    }

    @Override
    public boolean isPermitirCadastroManualEndereco() {
        return permitirCadastroManualEndereco;
    }

    @Override
    public void setPermitirCadastroManualEndereco(boolean permitirCadastroManualEndereco) {
        this.permitirCadastroManualEndereco = permitirCadastroManualEndereco;
    }

    @Override
    public void setAtualizarValorLogicoAoPersistir(boolean pAtualizarValorLogico) {
        atualizarValorLogicoAoPersistir = pAtualizarValorLogico;
    }

    @Override
    public boolean isAtualizarValorLogicoAoPersistir() {
        return atualizarValorLogicoAoPersistir;
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
