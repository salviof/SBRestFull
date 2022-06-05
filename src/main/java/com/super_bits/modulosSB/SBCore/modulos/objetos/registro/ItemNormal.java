package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringGerador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringGerador.TIPO_LOREN;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanNormal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanPermisionado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.io.File;

import java.util.Date;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public abstract class ItemNormal extends ItemSimples implements ItfBeanNormal, ItfBeanPermisionado {

    public ItemNormal() {
        super();

        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.AAA_NOME, getNomeCurto()));
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.IMG_GRANDE, getNomeCurto()));
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.AAA_NOME_LONGO, getNomeCurto()));
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.REG_DATAINSERCAO, null));
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.REG_DATAALTERACAO, null));
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO, null));
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.REG_USUARIO_INSERCAO, null));

        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.AAA_DESCRITIVO, UtilSBCoreStringGerador.getLorenIpsilum(TIPO_LOREN.PARAGRAFO)));

    }

    @Override
    public String getNomeLongo() {
        camposEsperados.getCampo(FabTipoAtributoObjeto.AAA_NOME_LONGO).setValorPadrao(getNomeCurto());
        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.AAA_NOME_LONGO);

    }

    @Override
    public String getDescritivo() {
        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.AAA_DESCRITIVO);
    }

    @Override
    public String getImgGrande() {

        return SBCore.getCentralDeArquivos().getEndrRemotoImagem(this, FabTipoAtributoObjeto.IMG_GRANDE);
        //	return OrganizadorDeArquivos.getURLImagem(this, TC.IMG_GRANDE);
    }

    @Override
    public String getImgMedia() {

        return SBCore.getCentralDeArquivos().getEndrRemotoImagem(this, FabTipoAtributoObjeto.IMG_MEDIA);
        //return OrganizadorDeArquivos.getURLImagem(this, TC.IMG_MEDIA);
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> getGaleria() {
        return null;// OrganizadorDeArquivos.getURLImagens(this, "galeria");
    }

    @Override
    public Date getDataHoraAlteracao() {
        return (Date) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.REG_DATAALTERACAO);
    }

    @Override
    public Date getDataHoraInsercao() {
        return (Date) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.REG_DATAINSERCAO);
    }

    @Override
    public ItfUsuario getUsuarioInsersao() {
        return (ItfUsuario) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.REG_USUARIO_INSERCAO);
    }

    @Override
    public ItfUsuario getUsuarioAlteracao() {
        return (ItfUsuario) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO);
    }

    @Override
    public void setAtivo(boolean pAtivo) {
        setValorByTipoCampoEsperado(FabTipoAtributoObjeto.REG_ATIVO_INATIVO, pAtivo);

    }

    @Override
    public boolean isAtivo() {
        return (Boolean) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.REG_ATIVO_INATIVO);
    }

    @Override
    public void setNomeLongo(String pnomeLongo) {
        setValorByTipoCampoEsperado(FabTipoAtributoObjeto.AAA_NOME, pnomeLongo);
    }

    @Override
    public void setDescritivo(String pDescritivo) {
        setValorByTipoCampoEsperado(FabTipoAtributoObjeto.AAA_DESCRITIVO, pDescritivo);
    }

    @Override
    public boolean isTemImagemMedioAdicionada() {
        return new File(SBCore.getCentralDeArquivos().getEndrLocalImagem(this, FabTipoAtributoObjeto.IMG_MEDIA)).exists();
    }

    @Override
    public boolean isTemImagemGrandeAdicionada() {
        return new File(SBCore.getCentralDeArquivos().getEndrLocalImagem(this, FabTipoAtributoObjeto.IMG_GRANDE)).exists();
    }

}
