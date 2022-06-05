package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;

import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.io.File;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class ItemSimples extends ItemGenerico implements
        ItfBeanSimples {

    public ItemSimples() {
        super();

        //	adcionaCampoEsperado(new CampoEsperado(TC.IMG_PEQUENA, CInfo.SITE_URL
        //			+CInfo.pastaImagens + "/SBPequeno.jpg"));
        //adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.AAA_NOME_CURTO), true);
        //adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.ID), true);
        // adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.AAA_NOME), true);
        // adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.ID), true);
    }

    public ItemSimples(Object pInstancia) {
        super(pInstancia);
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.AAA_NOME), true);
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.ID), true);
    }

    @Override
    public String getImgPequena() {
        return SBCore.getCentralDeArquivos().getEndrRemotoImagem(this, FabTipoAtributoObjeto.IMG_PEQUENA);

    }

    @Override
    public String getNomeCurto() {

        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.AAA_NOME);

        /**
         *
         * TODO String nome = (String)
         * getValorByTipoCampoEsperado(FabCampos.AAA_NOME_CURTO); String
         * nomeCurto = ""; nome = nome.replace("-", " "); nome =
         * nome.replace(".", " "); for (String parte : nome.split(" ")) { if
         * (nomeCurto.length() < 25) {
         * if (nomeCurto.length() > 0) { nomeCurto = nomeCurto + " " + parte; }
         * else { nomeCurto = nomeCurto + parte; } } } return nomeCurto;
         */
    }

    public String getNomeCurtoURLAmigavel() {
        return "url amigavel para Item simples não foi implementado";
        //String nomeCurto = (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.AAA_NOME_CURTO);
        // return UtilSBCoreStrings.makeStrUrlAmigavel(nomeCurto);
    }

    @Override
    public int getId() {
        return Integer.parseInt(getValorByTipoCampoEsperado(FabTipoAtributoObjeto.ID).toString());
    }

    public String getCampoSQLNomeCurto() {

        Field campo = getCampoByAnotacao(FabTipoAtributoObjeto.AAA_NOME);
        if (campo == null) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Nome curto não foi encontrado para obter o nome SQL do campo", null);

            return "Nome não encontrado na classe" + this.getClass().getSimpleName();
        }
        return campo.getName();

    }

    @Override
    public String getNome() {
        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.AAA_NOME);
    }

    @Override
    public void setNome(String pNome) {
        setValorByTipoCampoEsperado(FabTipoAtributoObjeto.AAA_NOME, pNome);
    }

    @Override
    public void setId(int pID) {
        setValorByTipoCampoEsperado(FabTipoAtributoObjeto.ID, pID);
    }

    @Override
    public String getNomeUnicoSlug() {
        return getNomeCurto() + "-" + getId();
    }

    @Override
    public String getIconeDaClasse() {
        return UtilSBCoreReflexaoObjeto.getIconeDoObjeto(this.getClass());
    }

    @Override
    public String getXhtmlVisao() {
        return SBCore.getCentralVisualizacao().getCaminhoXhtmlItemCard(this.getClass());
    }

    @Override
    public boolean validar() {
        return true;
    }

    @Override
    public List<ItfMensagem> validarComMensagens() {
        return new ArrayList<>();
    }

    @Override
    public boolean uploadArquivoDeEntidade(ItfCampoInstanciado prcampo, byte[] pStream, String pNomeArquivo) {
        return SBCore.getCentralDeArquivos().salvarArquivo(prcampo, pStream, pNomeArquivo);
    }

    @Override
    public boolean uploadFotoTamanhoGrande(InputStream pStream) {
        return SBCore.getCentralDeArquivos().salvarImagemTodosOsFormatos(this, pStream);
    }

    @Override
    public boolean uploadFotoTamanhoMedio(InputStream pStream) {
        return SBCore.getCentralDeArquivos().salvarImagemTamanhoMedio(this, pStream);
    }

    @Override
    public boolean uploadFotoTamanhoPequeno(InputStream pStream) {
        return SBCore.getCentralDeArquivos().salvarImagemTamanhoPequeno(this, pStream);
    }

    @Override
    public boolean uploadFotoTodosFormatos(InputStream pStream) {
        return SBCore.getCentralDeArquivos().salvarImagemTodosOsFormatos(this, pStream);
    }

    @Override
    public String getSlugIdentificador() {
        return gerarSlug();
    }

    @Override
    public boolean isTemImagemPequenaAdicionada() {
        return new File(SBCore.getCentralDeArquivos().getEndrLocalImagem(this, FabTipoAtributoObjeto.IMG_PEQUENA)).exists();
    }

    @Override
    public String getXhtmlVisaoMobile() {
        return MapaObjetosProjetoAtual.getVisualizacaoDoObjeto(this.getClass());
    }

    @Override
    public String getXhtmlVisao(int numeroColunas) {
        return MapaObjetosProjetoAtual.getVisualizacaoDoObjeto(this.getClass());
    }

}
