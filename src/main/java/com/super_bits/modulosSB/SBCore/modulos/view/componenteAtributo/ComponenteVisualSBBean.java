/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.componenteAtributo;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanVinculadoAEnum;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.FabFamiliaCompVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import java.util.List;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualBase;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreDiretoriosSimples;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = {"Componente Visual"}, plural = "Componentes Visuais")
public class ComponenteVisualSBBean extends ItemSimples implements ItfComponenteVisualSB, ItfBeanVinculadoAEnum {

    private final ComponenteVisualBase base;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID, label = "Código")
    private int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME, label = "Nome")
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_DESCRITIVO, label = "Descrição")
    private String descricao;
    private FabFamiliaCompVisual familia;

    /**
     *
     * @deprecated Mantido apenas para compatibilidade com soluções de reflexão
     */
    @Deprecated
    public ComponenteVisualSBBean() {
        this.base = null;
    }

    public ComponenteVisualSBBean(ComponenteVisualBase pComponente) {
        base = pComponente;
    }

    @Override
    public int getId() {
        return base.getId();
    }

    @Override
    public String getIdHTMLObjetoPrincipal() {
        return base.getIdHTMLObjetoPrincipal();
    }

    @Override
    public String getDescricao() {
        return base.getDescricao();
    }

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return base.getFamilia();
    }

    @Override
    public String getHtmlWordPress() {
        return base.getHtmlWordPress();
    }

    @Override
    public String getNomeComponente() {
        return base.getNomeComponente();
    }

    @Override
    public List<Object> getParametros() {
        return base.getParametros();
    }

    @Override
    public String getXhtmlAndroid() {
        return base.getXhtmlAndroid();
    }

    @Override
    public String getXhtmlJSF() {
        return base.getXhtmlJSF();
    }

    @Override
    public String getClasseCSS() {
        return base.getClasseCSS();
    }

    @Override
    public int getPesoLargura() {
        return base.getPesoLargura();
    }

    @Override
    public void setPesoLargura(int pesoLargura) {
        base.setPesoLargura(pesoLargura);
    }

    @Override
    public void setEnumVinculado(ItfFabrica pFabrica) {
        base.setEnumVinculado(pFabrica);
    }

    @Override
    public ItfFabrica getEnumVinculado() {
        return base.getEnumVinculado();
    }

    @Override
    public List<ItfAcaoSecundaria> getAcoesDoContexto(ItfAcaoGerenciarEntidade pGestao) {
        return base.getAcoesDoContexto(pGestao);
    }

    @Override
    public ItfCampoInstanciado getCPinst(String pNomeOuANotacao) {
        return super.getCPinst(pNomeOuANotacao); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getClassePontoIdentificador() {
        return base.getClassePontoIdentificador(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getClasseLarguraPorPeso() {
        return base.getClasseLarguraPorPeso();
    }

    @Override
    public String getCaminhoFabrica() {
        return base.getCaminhoFabrica();
    }

    public String getNomeArquivoXHTMLJSF() {
        return UtilSBCoreDiretoriosSimples.getNomeArquivo(getXhtmlJSF());
    }

    public String getXhtmlJsfCaminhoRelativo() {
        return base.getXhtmlJsfCaminhoRelativo();
    }

    public boolean isXhtmlJSFCaminhoRelativoIgualA(String pCaminhoReferencia) {
        return base.isXhtmlJSFCaminhoRelativoIgualA(pCaminhoReferencia);
    }

    @Override
    public String getNome() {
        return base.getNome(); //To change body of generated methods, choose Tools | Templates.
    }

}
