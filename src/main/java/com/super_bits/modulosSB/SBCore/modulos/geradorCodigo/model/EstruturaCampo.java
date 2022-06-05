/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaCampoEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfValidacao;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.UtilSBGeradorDeCodigoBase;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.PropriedadesReflexaoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.AtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FieldComSerializacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_DECLARACAO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_ORIGEM_VALOR_CAMPO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.UtilSBCoreReflecaoIEstruturaEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoListaDinamica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

import java.lang.reflect.Field;
import java.util.List;
import org.reflections.ReflectionUtils;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = "Estrutura do Campo", plural = "Estruturas de Campo")
public class EstruturaCampo extends ItemSimples implements ItfEstruturaCampoEntidade {

    private final AtributoObjetoSB campoVinculado;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private final int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nomeDeclarado;
    private ItfEstruturaDeEntidade estruturaPai;
    private TIPO_DECLARACAO tipoDeclaracao;
    private final boolean temValidadorLogico;
    private final boolean temValorLogico;
    private final boolean temListaDinamica;
    Class<? extends ItfValidacao> classeValidacao;
    private Class classeListaDinamica;
    private Class classeValorDinamico;
    private boolean atualizarValorLogicoAoSalvar;

    public EstruturaCampo(Field pCampo, EstruturaDeEntidade est) {

        PropriedadesReflexaoCampo prop = new PropriedadesReflexaoCampo(new FieldComSerializacao(pCampo));
        AtributoObjetoSB campoSB = (AtributoObjetoSB) prop.getAtributoGerado();
        prop.aplicarAnotacoesEmAtributo(campoSB);
        nomeDeclarado = pCampo.getName();
        tipoDeclaracao = prop.getTipoDeclaracaoEstrutura();
        campoVinculado = campoSB;
        estruturaPai = est;
        id = (estruturaPai.getNome() + nomeDeclarado).hashCode();

        temValidadorLogico = (pCampo.getAnnotation(InfoCampoValidadorLogico.class) != null);
        InfoCampoValorLogico vl = pCampo.getAnnotation(InfoCampoValorLogico.class);
        temValorLogico = (pCampo.getAnnotation(InfoCampoValorLogico.class) != null);
        if (temValorLogico) {
            atualizarValorLogicoAoSalvar = vl.atualizarSempreQueSalvar();
        }

        temListaDinamica = (pCampo.getAnnotation(InfoCampoListaDinamica.class) != null);
        if (temValorLogico) {
            tipoDeclaracao = TIPO_DECLARACAO.CAMPO_TRANSIENTE;
        } else if (temListaDinamica) {
            tipoDeclaracao = TIPO_DECLARACAO.LISTA_DINIMICA;
        }

        registrarListasECalculos(pCampo);
    }

    private void registrarListasECalculos(Field pCAmpo) {
        if (temValorLogico) {
            CalculoDeEntidade calculo = UtilSBCoreReflecaoIEstruturaEntidade.
                    getCalculoEstruturaByField(pCAmpo, campoVinculado, this);
            estruturaPai.getCalculos().add(calculo);
        } else {
            if (temListaDinamica) {
                ListaDeEntidade lista = UtilSBCoreReflecaoIEstruturaEntidade.getListaEstruturaByField(pCAmpo);
                if (lista != null) {
                    estruturaPai.getListas().add(lista);
                }
            }
        }

    }

    @Override
    public TIPO_DECLARACAO getTipoDeclaracao() {
        return tipoDeclaracao;
    }

    @Override
    public void setTipoDeclaracao(TIPO_DECLARACAO tipoDeclaracao) {
        this.tipoDeclaracao = tipoDeclaracao;
    }

    @Override
    public ItfEstruturaDeEntidade getEstruturaPai() {
        return estruturaPai;
    }

    @Override
    public void setEstruturaPai(ItfEstruturaDeEntidade estruturaPai) {
        this.estruturaPai = estruturaPai;
    }

    @Override
    public String getNomeDeclarado() {
        return nomeDeclarado;
    }

    @Override
    public void setNomeDeclarado(String nomeDeclarado) {
        this.nomeDeclarado = nomeDeclarado;
    }

    @Override
    public String getClasseCampoDeclaradoOuTipoLista() {

        return campoVinculado.getNomeClasseAtributoDeclarado();
    }

    @Override
    public FabTipoAtributoObjeto getFabricaTipoAtributo() {
        return campoVinculado.getFabricaTipoAtributo();
    }

    @Override
    public TIPO_PRIMITIVO getTipoPrimitivoDoValor() {
        return campoVinculado.getTipoPrimitivoDoValor();
    }

    @Override
    public TIPO_ORIGEM_VALOR_CAMPO getOrigemValor() {
        return campoVinculado.getOrigemValor();
    }

    @Override
    public String getTipoVisualizacao() {
        return campoVinculado.getTipoVisualizacao();
    }

    @Override
    public String getLabel() {
        return campoVinculado.getLabel();
    }

    @Override
    public String getIdComponente() {
        return campoVinculado.getIdComponente();
    }

    @Override
    public String getDescricao() {
        return campoVinculado.getDescricao();
    }

    @Override
    public String getMascara() {
        return campoVinculado.getMascara();
    }

    @Override
    public String getValorPadrao() {
        return campoVinculado.getValorPadrao();
    }

    @Override
    public boolean isObrigatorio() {
        return campoVinculado.isObrigatorio();
    }

    @Override
    public int getValorMaximo() {
        return campoVinculado.getValorMaximo();
    }

    @Override
    public int getValorMinimo() {
        return campoVinculado.getValorMinimo();
    }

    @Override
    public String getValidacaoRegex() {
        return campoVinculado.getValidacaoRegex();
    }

    @Override
    public boolean isTemValidacaoRegex() {
        return campoVinculado.isTemValidacaoRegex();
    }

    @Override
    public boolean isTemValidacaoMinimo() {
        return campoVinculado.isTemValidacaoMinimo();
    }

    @Override
    public boolean isTemValidacaoMaximo() {
        return campoVinculado.isTemValidacaoMaximo();
    }

    @Override
    public boolean isTemMascara() {
        return campoVinculado.isTemMascara();
    }

    @Override
    public boolean isNumeral() {
        return campoVinculado.isNumeral();
    }

    @Override
    public boolean isMoeda() {
        return campoVinculado.isMoeda();
    }

    @Override
    public char getSeparadorDecimal() {
        return campoVinculado.getSeparadorDecimal();
    }

    @Override
    public char getSeparadorMilhar() {
        return campoVinculado.getSeparadorMilhar();
    }

    @Override
    public int getNumeroDeCasasDecimais() {
        return campoVinculado.getNumeroDeCasasDecimais();
    }

    @Override
    public String getMascaraJqueryMode() {
        return campoVinculado.getMascaraJqueryMode();
    }

    @Override
    public String getTipoCampoSTR() {
        return campoVinculado.getTipoCampoSTR();
    }

    @Override
    public String getFraseValidacao() {
        return campoVinculado.getFraseValidacao();
    }

    @Override
    public boolean isUmValorLivre() {
        return campoVinculado.isUmValorLivre();
    }

    @Override
    public boolean isUmValorComLista() {
        return campoVinculado.isUmValorMultiploComLista();
    }

    @Override
    public boolean isUmValorMultiploLivre() {
        return campoVinculado.isUmValorMultiploLivre();
    }

    @Override
    public boolean isUmValorMultiploComLista() {
        return campoVinculado.isUmValorMultiploComLista();
    }

    @Override
    public void setDescricao(String pDescricao) {
        campoVinculado.setDescricao(pDescricao);
    }

    @Override
    public boolean isSomenteLeitura() {
        return campoVinculado.isUmValorLivre();
    }

    @Override
    public void setListaDeOpcoes(List<ItfBeanSimples> pLista) {
        campoVinculado.setListaDeOpcoes(pLista);
    }

    @Override
    public void setFraseValidacao(String pFraseValidacao) {
        campoVinculado.setFraseValidacao(pFraseValidacao);
    }

    @Override
    public void setMascara(String pMaskara) {
        campoVinculado.setMascara(pMaskara);
    }

    @Override
    public void setValorMaximo(int pValorMaximo) {
        campoVinculado.setValorMaximo(pValorMaximo);
    }

    @Override
    public void setValorMinimo(int pValorMinimo) {
        campoVinculado.setValorMinimo(pValorMinimo);

    }

    @Override
    public void setValidacaoRegex(String pValidacaoRegex) {
        campoVinculado.setValidacaoRegex(pValidacaoRegex);
    }

    @Override
    public void setSeparadorDecimal(char pSeparadorDecimal) {
        campoVinculado.setSeparadorDecimal(pSeparadorDecimal);
    }

    @Override
    public void setSeparadorMilhar(char pSeparadorMilhar) {
        campoVinculado.setSeparadorMilhar(pSeparadorMilhar);
    }

    @Override
    public void setNumeroDeCasasDecimais(int pNumeroCasasDecimais) {
        campoVinculado.setNumeroDeCasasDecimais(pNumeroCasasDecimais);
    }

    @Override
    public void setSomenteLeitura(boolean pSomenteLeitura) {
        campoVinculado.setSomenteLeitura(pSomenteLeitura);
    }

    @Override
    public void setObrigatorio(boolean pObrigatorio) {
        campoVinculado.setObrigatorio(pObrigatorio);
    }

    @Override
    public void setFabricaTipoAtributo(FabTipoAtributoObjeto pTipoCampo) {
        campoVinculado.setFabricaTipoAtributo(pTipoCampo);
    }

    @Override
    public boolean isUmCampoDinamico() {
        return campoVinculado.isUmCampoDinamico();
    }

    @Override
    public void setLabelPadrao(String pLabel) {
        campoVinculado.setLabelPadrao(pLabel);
    }

    @Override
    public String getLabelPadrao() {
        return getLabel();
    }

    public boolean isTemValidadorLogico() {
        return temValidadorLogico;
    }

    @Override
    public boolean isTemValorLogico() {
        return temValorLogico;
    }

    @Override
    public boolean isTemListaDinamica() {
        return temListaDinamica;
    }

    /**
     *
     * @return Entidade.nomeDeclarado
     */
    @Override
    public String getSlugIdentificador() {
        return getEstruturaPai().getNomeEntidade() + "." + getNomeDeclarado();
    }

    public Class<? extends ItfValidacao> getClasseValidacao() {
        if (!isTemValidadorLogico()) {
            return null;
        }
        if (classeValidacao == null) {

            String nomeClasse = UtilSBGeradorDeCodigoBase.gerarcaminhoPacoteClasse(this) + "." + UtilSBGeradorDeCodigoBase.getNomeClasseValidacao(this);

            try {
                classeValidacao = (Class<? extends ItfValidacao>) ReflectionUtils.forName(nomeClasse);
                return classeValidacao;
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro procurando validador logico para " + getSlugIdentificador(), t);
            }

        }
        return classeValidacao;
    }

    public Class<? extends ItfValidacao> getClasseValorLogico() {
        if (!isTemValorLogico()) {
            return null;
        }
        if (classeValorDinamico == null) {

            String nomeClasse = UtilSBGeradorDeCodigoBase.gerarcaminhoPacoteClasse(this) + "." + UtilSBGeradorDeCodigoBase.getNomeClasseValorLogico(this);

            try {
                classeValorDinamico = (Class<? extends ItfValidacao>) ReflectionUtils.forName(nomeClasse);
                return classeValorDinamico;
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro procurando implementação para valor lógico dinamico para " + getSlugIdentificador(), t);
            }

        }
        return classeValorDinamico;
    }

    @Override
    public boolean isAtualizarValorLogicoAoPersistir() {
        return atualizarValorLogicoAoSalvar;
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
