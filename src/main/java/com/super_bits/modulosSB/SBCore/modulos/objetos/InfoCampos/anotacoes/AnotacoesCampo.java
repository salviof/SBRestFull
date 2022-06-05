/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.InfoGrupoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_DECLARACAO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author desenvolvedor
 */
public class AnotacoesCampo implements ItfAnotacoesCampo {

    private InfoCampo infoCampo;
    private NotNull naonulo;
    private Past passado;
    private Future futuro;
    private Max maximo;
    private Min minimo;
    private Size tamanho;
    private Digits digitos;
    private Pattern regex;
    private AssertTrue verdadeiro;
    private ManyToOne muitosParaUm;
    private OneToMany umParaMuitos;
    private ManyToMany muitosParaMuitos;
    private InfoCampoModeloDocumento infoModeloPalavrasChave;
//    private Object calculoItem;
    //  private Object calculoLista;
    private Column column;
    private InfoGrupoCampo grupoCampo;
    private InfoCampoVerdadeiroOuFalso infoVerdadeiroFalso;
    private InfoCampoLocalizacao infoCampoLocalizacao;
    private InfoCampoValidadorLogico infoCampoValidadorLogico;
    private InfoCampoValorLogico infoCampoValorLogico;
    private InfoCampoListaDinamica infoCampoListaDinamica;
    private final Map<FabTipoAnotacaoCampo, Object> mapaTiposAnotados = new HashMap<>();

    public AnotacoesCampo(Field pCampoReflexao) {
        defineAnotacoesEncontradaEmCampo(pCampoReflexao);

        if (infoCampo == null) {
            //     throw new UnsupportedOperationException("O campo " + pCampoReflexao.getName() + " não foi anotado com InfoCampo, Esta anotação é obrigatória em todos os campos de um objetoSB");
        }
    }

    public AnotacoesCampo(InfoCampo pInfoCampo, Class pTipoCampoDeclaradoJava) {
        adicionarAnotacao(pInfoCampo, FabTipoAnotacaoCampo.INFOCAMPO);

        if (infoCampo == null) {
            //  throw new UnsupportedOperationException("O campo " + pInfoCampo + " não foi anotado com InfoCampo, Esta anotação é obrigatória em todos os campos de um objetoSB");
        }
    }

    private void defineAnotacoesEncontradaEmCampo(Field pCampo) {
        try {
            if (pCampo == null) {
                throw new UnsupportedOperationException("Impossível definir as anotações encontradas em campo, enviado Field nulo");
            }

            for (Object anotacao : pCampo.getAnnotations()) {

                if (anotacao != null) {
                    FabTipoAnotacaoCampo tipoAnotacao = FabTipoAnotacaoCampo.getTipoByAnotacaoInstanciada(anotacao);
                    if (anotacao != null && tipoAnotacao != null) {
                        adicionarAnotacao(anotacao, FabTipoAnotacaoCampo.getTipoByAnotacaoInstanciada(anotacao));
                    }
                }

            }
            if (infoCampo == null) {
                //    throw new UnsupportedOperationException("A anotação InfoCampo é obrigatória em todos os campos de um objetoSB");
            }
        } catch (Throwable t) {
            String nomecampo = "Capo reflexao nulo";
            if (pCampo == null) {
                nomecampo = pCampo.getName();
            }
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro definido anotação no campo " + nomecampo, t);
        }

    }

    public AnotacoesCampo(boolean lancarExcecao, Object... pAnotacoes) {
        try {
            for (Object anotacao : pAnotacoes) {
                if (anotacao != null) {
                    FabTipoAnotacaoCampo tipo = FabTipoAnotacaoCampo.getTipoByAnotacaoInstanciada(anotacao);
                    if (tipo != null) {
                        adicionarAnotacao(anotacao, tipo);
                    } else {
                        //   System.out.println("Nao encontrou");
                    }
                }
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao criar anotações para campo " + pAnotacoes, t);
            if (lancarExcecao) {
                throw new UnsupportedOperationException("Erro criando anotações para Campos");
            }
        }

    }

    public final void adicionarAnotacao(Object pAnotacao, FabTipoAnotacaoCampo pTipo) {
        if (pAnotacao != null) {
            mapaTiposAnotados.put(pTipo, pAnotacao);
            switch (pTipo) {
                case INFOCAMPO:
                    infoCampo = (InfoCampo) pAnotacao;
                    break;
                case NAO_NULO:
                    naonulo = (NotNull) pAnotacao;
                    break;
                case PASSADO:
                    passado = (Past) pAnotacao;
                    break;
                case FUTURO:
                    futuro = (Future) pAnotacao;
                    break;
                case MAXIMO:
                    maximo = (Max) pAnotacao;
                    break;
                case MINIMO:
                    minimo = (Min) pAnotacao;
                    break;
                case TAMANHO:
                    tamanho = (Size) pAnotacao;
                    break;
                case DIGITOS:
                    digitos = (Digits) pAnotacao;
                    break;
                case REGEX:
                    regex = (Pattern) pAnotacao;
                    break;
                case VERDADERO:
                    verdadeiro = (AssertTrue) pAnotacao;
                    break;
                case MUITOS_PARA_UM:
                    muitosParaUm = (ManyToOne) pAnotacao;
                    break;
                case UM_PARA_MUITOS:
                    umParaMuitos = (OneToMany) pAnotacao;
                    break;
                //case VALOR_CALCULADO:
                //   calculoItem = pAnotacao;
                //  break;
                //case LISTA_CALCULAD:
                //   calculoLista = pAnotacao;
                //  break;
                case MUITOS_PARA_MUITOS:
                    muitosParaMuitos = (ManyToMany) pAnotacao;
                    break;
                case GRUPO_CAMPOS:
                    grupoCampo = (InfoGrupoCampo) pAnotacao;
                    break;
                case INFOCAMPO_MODELO:
                    infoModeloPalavrasChave = (InfoCampoModeloDocumento) pAnotacao;
                    break;
                case VERDADEIRO_FALSO:
                    infoVerdadeiroFalso = (InfoCampoVerdadeiroOuFalso) pAnotacao;
                    break;
                case COLUMN:
                    column = (Column) pAnotacao;
                    break;
                case INFO_CAMPO_LOCALIZACAO:
                    infoCampoLocalizacao = (InfoCampoLocalizacao) pAnotacao;
                    break;
                case INFO_CAMPO_VALIDADOR_LOGICO:
                    infoCampoValidadorLogico = (InfoCampoValidadorLogico) pAnotacao;
                    break;
                case INFO_CAMPO_VALOR_LOGICO:
                    infoCampoValorLogico = (InfoCampoValorLogico) pAnotacao;
                    break;
                case INFO_CAMPO_LISTA_DINAMICA:
                    infoCampoListaDinamica = (InfoCampoListaDinamica) pAnotacao;
                    break;

                default:
                    throw new AssertionError(pTipo.name());

            }
        }
    }

    @Override
    public InfoCampo getInfoCampo() {

        return infoCampo;
    }

    @Override
    public NotNull getNaonulo() {
        return naonulo;
    }

    @Override
    public Past getPassado() {
        return passado;
    }

    @Override
    public Future getFuturo() {
        return futuro;
    }

    @Override
    public Max getMaximo() {
        return maximo;
    }

    @Override
    public Min getMinimo() {
        return minimo;
    }

    @Override
    public Size getTamanho() {
        return tamanho;
    }

    @Override
    public Digits getDigitos() {
        return digitos;
    }

    @Override
    public Pattern getRegex() {
        return regex;
    }

    @Override
    public AssertTrue getVerdadeiro() {
        return verdadeiro;
    }

    public ManyToOne getMuitosParaUm() {
        return muitosParaUm;
    }

    public OneToMany getUmParaMuitos() {
        return umParaMuitos;
    }

    public ManyToMany getMuitosParaMuitos() {
        return muitosParaMuitos;
    }

    public Map<FabTipoAnotacaoCampo, Object> getMapaTiposAnotados() {
        return mapaTiposAnotados;
    }

    @Override
    public List<FabTipoAnotacaoCampo> getTiposAnotacoesRealizadas() {

        return Lists.newArrayList(mapaTiposAnotados.keySet());
    }

    @Override
    public Object getAnotacao(FabTipoAnotacaoCampo pTipoAnotacao) {
        return mapaTiposAnotados.get(pTipoAnotacao);
    }

    public void validar() throws UnsupportedOperationException {
        if (infoCampo == null) {
            throw new UnsupportedOperationException("A anotação infoCampo não foi definida");
        }
    }

    @Deprecated
    public TIPO_DECLARACAO getTipoDeclaracao() {

        System.out.println("Gettipo declaracao está depreciado");
        return TIPO_DECLARACAO.SIMPLES;
    }

    public InfoGrupoCampo getGrupoCampo() {
        return grupoCampo;
    }

    @Override
    public InfoCampoModeloDocumento getInfoModeloDocumeto() {
        return infoModeloPalavrasChave;
    }

    public InfoCampoVerdadeiroOuFalso getInfoVerdadeiroFalso() {
        return infoVerdadeiroFalso;
    }

    public InfoCampoLocalizacao getInfoCampoLocalizacao() {
        return infoCampoLocalizacao;
    }

    public Column getColumn() {
        return column;
    }

    public boolean isUmCampoValorLogico() {
        return infoCampoValorLogico != null;
    }

    public InfoCampoValorLogico getInfoCampoValorLogico() {
        return infoCampoValorLogico;
    }

}
