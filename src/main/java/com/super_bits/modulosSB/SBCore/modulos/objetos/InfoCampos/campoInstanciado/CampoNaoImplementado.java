/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenvolvedor
 */
public class CampoNaoImplementado extends CampoInstanciadoGenerico implements ItfCampoNaoInstanciadoOuNaoImplementado {

    public static final String LABEL_NAO_IMPLEMENTADO = "Não Implementado ou Instanciado";
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME, label = LABEL_NAO_IMPLEMENTADO)
    public final String campoNaoImplementado = "TODO - CampoNão implementado";
    private Field fld;

    private static Field campoteste() {
        try {

            Field cp = CampoNaoImplementado.class.getField("campoNaoImplementado");
            return cp;
        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(CampoNaoImplementado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public CampoNaoImplementado() {
        super(campoteste());

    }

    @Override

    public boolean validarCampo() {
        return true;
    }

    @Override
    public Object getParent() {
        return null;
    }

    @Override
    public Object getValor() {
        return "Não Implementado";
    }

    @Override
    public void setValor(Object pValor) {

    }

    @Override
    public int configIDPeloNome() {
        System.out.println("Config id pelo nome não se aplica ");
        return 0;
    }

    @Override
    public ItfComponenteVisualSB getComponenteDiferenciado(ItfComponenteVisualSB pComponente) {
        return FabCompVisualInputs.TEXTO_SEM_FORMATACAO.getRegistro();
    }

    @Override
    public ItfCampoInstSeletorItens getComoSeltorItens() {
        return null;
    }

    @Override
    public String getNomeClasseAtributoDeclarado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNomeClasseAtributoDeclarado(String pObjetoEntidade) {

    }

    @Override
    public String getNomeClasseOrigemAtributo() {
        return "";
    }

    @Override
    public String getCaminhoListagem() {
        return null;
    }

    @Override
    public GrupoCampos getGrupoSubCamposExibicao() {
        return null;
    }

    @Override
    public String toString() {
        return "CampoNaoImplementadoOuPaiNaoInstanciado";
    }

    @Override
    public List<String> getTemplateCamposDinamicos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUmCampoCampoLocalizacao() {
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
    public String getLabelPadrao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCampoNaoInstanciado() {
        return true;
    }

    @Override
    public boolean isTemValidadacaoLogica() {
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
    public String getXhtmlVisaoMobile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getXhtmlVisao(int numeroColunas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
