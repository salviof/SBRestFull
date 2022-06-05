/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.UtilSBCoreReflexaoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TipoAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SalvioF
 */
public class CampoInstanciadoEnumFabricaObjeto implements ItfCampoInstanciadoEnumFabricaObjeto {

    private final Class classeEnumFab;

    private final ItfCampoInstanciado campoInstanciado;
    private List<Integer> listaOpcoesOrdinal;
    private List<String> listaOpcoesString;

    private List<ItfBeanSimplesSomenteLeitura> listaOpcoesObjeto;
    private List<ItfFabrica> listaFabricas;

    private ItfFabrica enumSelecionado;
    private ItfBeanSimplesSomenteLeitura beanSelecionado;
    private int ordinalSelecionado;
    private String stringSelecionada;

    protected ItfFabrica getFabricaByOrdinal(int pId) {
        return UtilSBCoreReflexaoFabrica.getFabricaPorOrdinal(classeEnumFab, pId);

    }

    protected ItfFabrica getFabricaByString(String pStr) {
        try {
            return (ItfFabrica) Enum.valueOf(classeEnumFab, pStr);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo fabrica po string", t);
            return null;
        }

    }

    protected ItfFabrica getFabricaByObjeto(ItfBeanSimplesSomenteLeitura pEntidade) {
        try {
            return UtilSBCoreReflexaoFabrica.getEnumDoObjetoFabrica(classeEnumFab, beanSelecionado);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro localizando Enum do objeto", t);
            return null;
        }

    }

    public CampoInstanciadoEnumFabricaObjeto(Class pClasseEnumFab, CampoInstanciadoGenerico pCampoInstanciado) {

        try {
            if (!UtilSBCoreReflexao.isInterfaceImplementadaNaClasse(pClasseEnumFab, ItfFabrica.class)) {
                throw new UnsupportedOperationException("A classe referente a fabrica enviada para construir um campo instanciado EnuimFabricaObjeto enviado, não implmenta" + ItfFabrica.class.getSimpleName());
            }
            if (pCampoInstanciado == null) {
                throw new UnsupportedOperationException("Campo instanciado referente não enviado em tentativa de extende-lo como um " + CampoInstanciadoEnumFabricaObjeto.class);
            }

            campoInstanciado = pCampoInstanciado;
            classeEnumFab = pClasseEnumFab;
            if (campoInstanciado.getValor() != null) {
                enumSelecionado = (ItfFabrica) campoInstanciado.getValor();
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro, o campoInstanciado não é do tipo enum Fabrica", t);
            throw new UnsupportedOperationException("Impossível criar cmapo enum fabrica Objeto");
        }

    }

    @Override
    public List<Integer> getListaOpcoesOrdinal() {
        if (listaOpcoesOrdinal == null) {
            listaOpcoesOrdinal = UtilSBCoreReflexaoFabrica.getListaIntegerDaFabrica(classeEnumFab);
        }
        return listaOpcoesOrdinal;
    }

    @Override
    public List<ItfBeanSimplesSomenteLeitura> getListaOpcoesObjeto() {

        if (listaOpcoesObjeto == null) {

            if (classeEnumFab.getSimpleName().equals(FabTipoAtributoObjeto.class.getSimpleName())) {
                listaOpcoesObjeto = new ArrayList();
                for (FabTipoAtributoObjeto tipoAtr : FabTipoAtributoObjeto.class.getEnumConstants()) {

                    listaOpcoesObjeto.add(new TipoAtributoObjetoSB(tipoAtr));
                }
            } else {
                listaOpcoesObjeto = UtilSBCoreReflexaoFabrica.getListaTodosRegistrosDaFabrica(classeEnumFab);
            }
        }
        return listaOpcoesObjeto;
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciado() {
        return campoInstanciado;
    }

    @Override
    public List<String> getListaOpcoesString() {
        if (listaOpcoesString == null) {
            if (enumSelecionado.getClass().getSimpleName().equals(FabTipoAtributoObjeto.class.getSimpleName())) {
                listaOpcoesString = new ArrayList();
                for (FabTipoAtributoObjeto tipoAtr : FabTipoAtributoObjeto.class.getEnumConstants()) {

                    listaOpcoesString.add(tipoAtr.toString());
                }
            } else {
                listaOpcoesString = UtilSBCoreReflexaoFabrica.getListaStringsDaFabrica(classeEnumFab);
            }
        }
        return listaOpcoesString;
    }

    @Override
    public ItfBeanSimplesSomenteLeitura getBeanSelecionado() {
        if (enumSelecionado != null) {

            if (enumSelecionado.getClass().getSimpleName().equals(FabTipoAtributoObjeto.class.getSimpleName())) {
                TipoAtributoObjetoSB tipoObj = new TipoAtributoObjetoSB((FabTipoAtributoObjeto) enumSelecionado);
                beanSelecionado = tipoObj;
            } else {
                beanSelecionado = (ItfBeanSimplesSomenteLeitura) enumSelecionado.getRegistro();
            }
        }
        return beanSelecionado;
    }

    @Override
    public int getOrdinalSelecionado() {
        if (enumSelecionado != null) {
            ordinalSelecionado = ((Enum) enumSelecionado).ordinal();
        }
        return ordinalSelecionado;
    }

    @Override
    public String getStringSelecionada() {
        if (enumSelecionado != null) {
            stringSelecionada = ((Enum) enumSelecionado).toString();
        }
        return stringSelecionada;
    }

    @Override
    public void setStringSelecionada(String pStringSelecionada) {
        this.stringSelecionada = pStringSelecionada;
        if (pStringSelecionada == null) {
            enumSelecionado = null;
        } else {
            enumSelecionado = getFabricaByString(pStringSelecionada);
            if (enumSelecionado != null) {
                campoInstanciado.setValor(enumSelecionado);
            }
        }
    }

    @Override
    public void setOrdinalSelecionado(int ordinalSelecionado) {

        this.ordinalSelecionado = ordinalSelecionado;
        enumSelecionado = getFabricaByOrdinal(ordinalSelecionado);
        if (enumSelecionado != null) {
            campoInstanciado.setValor(enumSelecionado);
        }
    }

    @Override
    public void setBeanSelecionado(ItfBeanSimplesSomenteLeitura pBeanSelecionado) {
        if (pBeanSelecionado == null) {
            enumSelecionado = null;
        } else {
            this.beanSelecionado = pBeanSelecionado;
            enumSelecionado = getFabricaByObjeto(pBeanSelecionado);
            if (enumSelecionado != null) {
                campoInstanciado.setValor(enumSelecionado);
            }
        }

    }

    @Override
    public Class getClasseEnumFab() {
        return classeEnumFab;
    }

}
