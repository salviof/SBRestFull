/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsCammelCase;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoAtributoDeObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.AnotacoesCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoModeloDocumento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.PropriedadesReflexaoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.FabTipoConversaoEnum;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author desenvolvedor
 */
public class FieldComSerializacao implements Serializable {

    private final Field campo;

    public FieldComSerializacao(Field campo) {

        if (campo == null) {
            throw new UnsupportedOperationException("enviado nulo para criar " + FieldComSerializacao.class.getName());
        }
        this.campo = campo;
        campo.setAccessible(true);

    }

    public boolean isCampoEnum() {
        return campo.getType().isEnum();
    }

    public boolean isVerdadeiroOuFalso() {
        return campo.getType().getSimpleName().equals(boolean.class.getSimpleName());
    }

    public FabTipoConversaoEnum getTipoConversao() {
        if (isCampoEnum()) {

            Enumerated anotacaoEnum = campo.getAnnotation(Enumerated.class);
            if (anotacaoEnum != null) {
                if (anotacaoEnum.value() == EnumType.ORDINAL) {
                    return FabTipoConversaoEnum.CONVERSAO_POR_ORDINAL;
                } else {
                    return FabTipoConversaoEnum.CONVERSAO_POR_STRING;
                }

            }
            return FabTipoConversaoEnum.CONVERSAO_POR_ORDINAL;
        } else {
            return null;
        }
    }

    public boolean isUmObjetoSB() {

        Class classe = campo.getType();

        return classe.isAnnotationPresent(InfoObjetoSB.class);

    }

    public Class getClasseDeclarada() {
        return campo.getType();
    }

    public Class getClasseDeclaradaOuTipoEmCasoDeLista() {
        return UtilSBCoreReflexaoAtributoDeObjeto.getClassePrincipalDoCampo(campo);
    }

    public String getNomeDeclaracao() {
        return campo.getName();
    }

    public Class getClasseOndeOCampoEstaDeclarado() {
        if (campo.isEnumConstant()) {
            InfoCampoDinamico campoDinamico = campo.getAnnotation(InfoCampoDinamico.class);
            if (campoDinamico == null) {
                //return null;
            } else {
                return campoDinamico.classeInjecaoDeValorDireto();
            }

        } else {
            return campo.getDeclaringClass();
        }
        return campo.getDeclaringClass();
    }

    public void setValorDesteCampoEmObjetoInstanciado(Object objeto, Object valor, boolean lancarExcecao) {
        try {
            if (!campo.isAccessible()) {
                campo.setAccessible(true);
            }
            campo.set(objeto, valor);
        } catch (IllegalArgumentException | IllegalAccessException t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro aplicando valor do campo em objeto por reflexão no campo " + getNomeDeclaracao()
                    + "em objetodo tipo" + getClasseOndeOCampoEstaDeclarado() + " na instancia:" + objeto + "com o valor" + valor + " ", t);
            if (lancarExcecao) {
                throw new UnsupportedOperationException("Erro setando valor do campo por reflexão", t);
            }
        }
    }

    public void setValorDesteCampoEmObjetoInstanciado(Object objeto, String valor, boolean lancarExcecao) {
        try {
            if (!campo.isAccessible()) {
                campo.setAccessible(true);
            }
            campo.set(objeto, valor);
        } catch (IllegalArgumentException | IllegalAccessException t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro aplicando valor do campo em objeto por reflexão no campo " + getNomeDeclaracao()
                    + "em objetodo tipo" + getClasseOndeOCampoEstaDeclarado() + " na instancia:" + objeto + "com o valor" + valor + " ", t);
            if (lancarExcecao) {
                throw new UnsupportedOperationException("Erro setando valor do campo por reflexão", t);
            }
        }
    }

    public Object getValorDesteCampoEmObjetoInstanciado(Object pObjetoInstanciado, boolean lancarExcecao) {
        try {
            return campo.get(pObjetoInstanciado);
        } catch (IllegalArgumentException | IllegalAccessException t) {
            System.out.println(t.getCause());
            System.out.println(t.getLocalizedMessage());
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo valor do campo em objeto por reflexão no campo " + getNomeDeclaracao()
                    + "em objetodo tipo" + getClasseOndeOCampoEstaDeclarado() + " na instancia:" + pObjetoInstanciado, t);
            if (lancarExcecao) {
                throw new UnsupportedOperationException("Erro setando valor do campo por reflexão", t);
            }
            return null;
        }

    }

    public TIPO_PRIMITIVO getTipoPrimitivo() {
        return TIPO_PRIMITIVO.getTIPO_PRIMITIVO(campo);
    }

    public InfoCampo getInfoCampo() {
        return campo.getAnnotation(InfoCampo.class);
    }

    public Annotation[] getAnotacoes() {
        return campo.getAnnotations();
    }

    public boolean isValorCampoUnico() {
        Column coluna = campo.getAnnotation(Column.class);
        if (coluna == null) {
            return false;
        }
        return coluna.unique();

    }

    public NotNull getAnotacaoNotNull() {
        return campo.getAnnotation(NotNull.class);
    }

    public boolean isPossuiAnotacaoNotull() {
        return (campo.getAnnotation(NotNull.class) != null);
    }

    public boolean isPossuiAnotacaoManyToOne() {
        return (campo.getAnnotation(ManyToOne.class) != null);
    }

    public boolean isPossuiAnotacaoOneToMany() {
        return (campo.getAnnotation(OneToMany.class) != null);
    }

    public boolean isPossuiAnotacaoMuitosParaMuitos() {
        return (campo.getAnnotation(ManyToMany.class) != null);
    }

    public boolean isPossuiValorDinamicoCalculado() {
        return (campo.getAnnotation(InfoCampoValorLogico.class) != null);
    }

    public AnotacoesCampo gerarAnotacoes() {
        return new AnotacoesCampo(campo);
    }

    public PropriedadesReflexaoCampo gerarPropriedadesReflexaoCampo() {
        return new PropriedadesReflexaoCampo(this);
    }

    public FabTipoAtributoObjeto getTipoAtributo() {
        if (isCampoEnum()) {
            return FabTipoAtributoObjeto.ENUM_FABRICA;
        }
        if (getInfoCampo() != null) {
            return getInfoCampo().tipo();
        } else {
            return TipoAtributoMetodosBase.getTipoPadraoByClasse(campo.getType());
        }
    }

    public String getLabelPadrao() {
        if (getInfoCampo() != null) {
            if (UtilSBCoreStringValidador.isNAO_NuloNemBranco(getInfoCampo().label())) {
                return getInfoCampo().label();
            }

        }
        return UtilSBCoreStringsCammelCase.getTextoByCammelPrimeiraLetraMaiuscula(campo.getName());
    }

    public Class getTemplateObjetoReferencia() {
        InfoCampoModeloDocumento anotacao = campo.getAnnotation(InfoCampoModeloDocumento.class);
        if (anotacao == null) {
            return null;
        }
        return anotacao.classeModeloVinculado();
    }

    public List<String> getTemplateCamposManual() {
        InfoCampoModeloDocumento anotacao = campo.getAnnotation(InfoCampoModeloDocumento.class);
        if (anotacao == null) {
            return null;
        }
        return Lists.newArrayList(anotacao.camposCadastrados());
    }

}
