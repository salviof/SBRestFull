/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoAtributoDeObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author SalvioF
 */
public enum FabGruposPadrao implements ItfFabrica {
    @InfoGrupoCampo(camposPorTipoAtributo = {FabTipoAtributoObjeto.ID,
        FabTipoAtributoObjeto.AAA_NOME})
    GRUPO_PADRAO_ITEM_SIMPLES,
    @InfoGrupoCampo(camposPorTipoAtributo = {FabTipoAtributoObjeto.ID,
        FabTipoAtributoObjeto.AAA_NOME, FabTipoAtributoObjeto.AAA_DESCRITIVO})
    GRUPO_PADRAO_ITEM_NORMAL,
    @InfoGrupoCampo(camposPorTipoAtributo = {FabTipoAtributoObjeto.ID,
        FabTipoAtributoObjeto.AAA_NOME, FabTipoAtributoObjeto.AAA_DESCRITIVO, FabTipoAtributoObjeto.REG_USUARIO_INSERCAO, FabTipoAtributoObjeto.REG_DATAINSERCAO})
    GRUPO_PADRAO_ITEM_NORMAL_AVANCADO,
    @InfoGrupoCampo(camposPorTipoAtributo = {FabTipoAtributoObjeto.ID,
        FabTipoAtributoObjeto.AAA_NOME, FabTipoAtributoObjeto.AAA_DESCRITIVO, FabTipoAtributoObjeto.REG_USUARIO_INSERCAO, FabTipoAtributoObjeto.REG_DATAINSERCAO,
        FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO, FabTipoAtributoObjeto.REG_DATAALTERACAO})
    GRUPO_PADRAO_ITEM_NORMAL_AVANCADO_COMPLETO;

    @Override
    public GrupoCampos getRegistro() {
        try {
            throw new UnsupportedOperationException("é preciso enviar uma entidade como paramentro para obter um grupo de Campos");

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ", t);
            return null;
        }
    }

    public ItfGrupoCampos getGrupoCampo(Class p) {
        try {
            InfoGrupoCampo anotacaoGrupoCampo = UtilSBCoreReflexao.getAnotacaoEmEnum(InfoGrupoCampo.class, this);
            return UtilSBCoreReflexaoAtributoDeObjeto.getGrupoCampoPorAnotacao(anotacaoGrupoCampo, p);
        } catch (Throwable t) {
            String nomeClasse = "Classe não enviada";
            if (p != null) {
                nomeClasse = p.getSimpleName();
            }
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Oube um erro obtendo o grupo de campo padrão em" + nomeClasse, t);
            return null;
        }

    }

    public GrupoCampos getGrupoCampoIgnorandoCamposNaoEncontrados(Class p) {
        try {
            InfoGrupoCampo anotacaoGrupoCampo = UtilSBCoreReflexao.getAnotacaoEmEnum(InfoGrupoCampo.class, this);
            return UtilSBCoreReflexaoAtributoDeObjeto.getGrupoCampoPorAnotacaoIgnorandoCamposNaoEcontrados(anotacaoGrupoCampo, p);
        } catch (Throwable t) {
            String nomeClasse = "Classe não enviada";
            if (p != null) {
                nomeClasse = p.getSimpleName();
            }
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Oube um erro obtendo o grupo de campo padrão em" + nomeClasse, t);
            return null;
        }

    }

    public GrupoCampos getGrupoCampo(ItfBeanSimples p) {
        try {

            return (GrupoCampos) getGrupoCampo(p.getClass());

        } catch (Throwable t) {
            String nomeClasse = "Classe não enviada";
            if (p != null) {
                nomeClasse = p.getClass().getSimpleName();
            }
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Oube um erro obtendo o grupo de campo padrão em" + nomeClasse, t);
            return null;
        }

    }

}
