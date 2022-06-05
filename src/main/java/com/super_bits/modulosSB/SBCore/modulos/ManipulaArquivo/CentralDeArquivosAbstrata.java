/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.FabTipoEmpacotamento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo.FabTipoAcessoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralDeArquivos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.io.File;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author salvioF
 */
public abstract class CentralDeArquivosAbstrata implements ItfCentralDeArquivos {

    protected final FabTipoEmpacotamento tipoEmpacotamento;
    private final TIPO_ESTRUTURA_LOCAL_XHTML_PADRAO tipoEstruturaCaminhoFormPadrao;

    protected String diretorioResourcesJava;

    public void defineVariaveisPadrao() {

    }

    public CentralDeArquivosAbstrata(FabTipoEmpacotamento pTipoEmpacotamento,
            TIPO_ESTRUTURA_LOCAL_XHTML_PADRAO pTipoEstruturaCaminhoFormPadrao) {
        tipoEmpacotamento = pTipoEmpacotamento;
        tipoEstruturaCaminhoFormPadrao = pTipoEstruturaCaminhoFormPadrao;
    }

    @Override
    public boolean salvarArquivo(ItfBeanSimplesSomenteLeitura entidade, byte[] arqivo, String pCategoria, String pNome) {
        String caminhoArquivo = null;
        if (entidade.getId() == 0) {
            caminhoArquivo = getEndrLocalArquivoTemporario(pCategoria, entidade.getClass().getSimpleName(), pNome);
        } else {
            caminhoArquivo = getEndrLocalArquivoItem(entidade, pNome, pCategoria);
        }
        try {
            FileUtils.writeByteArrayToFile(new File(caminhoArquivo), arqivo);

            return true;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro Gravando arquivo " + caminhoArquivo, t);
            return false;
        }

    }

    @Override
    public boolean salvarArquivo(ItfCampoInstanciado pCampo, byte[] arquivo, String pNomeArquivo) {
        pCampo.setValor(pNomeArquivo);
        return salvarArquivo((ItfBeanSimplesSomenteLeitura) pCampo.getObjetoDoAtributo(), arquivo, pCampo.getNomeCamponaClasse(), pNomeArquivo);
    }
    private static final String SLUG_IMAGEM_PEQUENA = FabTipoAtributoObjeto.IMG_PEQUENA.toString();
    private static final String SLUG_IMAGEM_MEDIA = FabTipoAtributoObjeto.IMG_MEDIA.toString();
    private static final String SLUG_IMAGEM_GRANDE = FabTipoAtributoObjeto.IMG_GRANDE.toString();

    @Override
    public String getEndrLocalArquivoItem(ItfBeanSimplesSomenteLeitura pItem, String nomeArquivo, String categoria) {
        if (categoria == null) {
            return getEndrLocalRecursosDoObjeto(pItem.getClass()) + "/" + pItem.getId() + "/" + nomeArquivo;
        }
        switch (categoria) {
            case "IMG_PEQUENA":
                return getEndrLocalImagem(pItem, FabTipoAtributoObjeto.IMG_PEQUENA);
            case "IMG_MEDIA":
                return getEndrLocalImagem(pItem, FabTipoAtributoObjeto.IMG_MEDIA);
            case "IMG_GRANDE":
                return getEndrLocalImagem(pItem, FabTipoAtributoObjeto.IMG_GRANDE);

            default:
                return getEndrLocalRecursosDoObjeto(pItem.getClass()) + "/" + pItem.getId() + "/" + categoria + "/" + nomeArquivo;
        }

    }

    @Override
    public String getEndrRemotoArquivoCampoInstanciadoBaixar(ItfCampoInstanciado pCampo) {
        try {
            if (pCampo == null) {
                throw new UnsupportedOperationException("Enviado campo instanciado nulo para obter URL");
            }
            String urlEntidade = getEndrRemotoRecursosItem((ItfBeanSimples) pCampo.getObjetoDoAtributo(), pCampo.getNomeCamponaClasse(), FabTipoAcessoArquivo.BAIXAR, FabTipoArquivoConhecido.getTipoArquivoByNomeArquivo((String) pCampo.getValor()));
            String nomeArquivo = pCampo.getValor().toString();
            return urlEntidade + nomeArquivo;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo Endereço remoto de campo instanciado", t);
            return null;
        }
    }

    @Override
    public String getEndrRemotoArquivoCampoInstanciadoAbrir(ItfCampoInstanciado pCampo) {
        try {
            if (pCampo == null) {
                throw new UnsupportedOperationException("Enviado campo instanciado nulo para obter URL");
            }
            String urlEntidade = getEndrRemotoRecursosItem((ItfBeanSimples) pCampo.getObjetoDoAtributo(), pCampo.getNomeCamponaClasse(), FabTipoAcessoArquivo.VISUALIZAR, FabTipoArquivoConhecido.getTipoArquivoByNomeArquivo((String) pCampo.getValor()));
            String nomeArquivo = pCampo.getValor().toString();
            return urlEntidade + nomeArquivo;
        } catch (Throwable t) {
            if (pCampo != null) {
                System.out.println("nome do arquovo não encontrado em " + pCampo.getNomeCompostoIdentificador());
            }

            //      SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo Endereço remoto de campo instanciado", t);
            return null;
        }
    }

    @Override
    public String getEndrLocalArquivoCampoInstanciado(ItfCampoInstanciado pCampo) {

        String caminhoArquivo = getEndrLocalArquivoItem((ItfBeanSimplesSomenteLeitura) pCampo.getObjetoDoAtributo(), (String) pCampo.getValor(), pCampo.getNomeCamponaClasse());
        return caminhoArquivo;
    }

    @Override
    public String getEndrLocalArquivoTemporario(String pCategoria, String pEntidade, String pNomeArquivo) {
        String endrlocal = getEndrLocalResourcesObjeto() + "/" + pEntidade + "/temp/" + pNomeArquivo;
        return endrlocal;
    }

    @Override
    public String getEndrLocalArquivoItem(ItfBeanSimplesSomenteLeitura pItem, String nomeArquivo) {
        return getEndrLocalArquivoItem(pItem, nomeArquivo, null);
    }

    @Override
    public String getEndrRemotoArquivoItem(ItfBeanSimplesSomenteLeitura pItem, String nomeArquivo, FabTipoAcessoArquivo pTipoAcesso, FabTipoArquivoConhecido pTipoArquivo) {
        return getEndrRemotoRecursosDoObjeto(pItem.getClass(), pTipoAcesso, pTipoArquivo) + "/" + nomeArquivo;

    }

    @Override
    public String getEndrRemotoArquivoItem(ItfBeanSimplesSomenteLeitura pItem, String nomeArquivo, String categoria, FabTipoAcessoArquivo pTipoAcesso, FabTipoArquivoConhecido pTipoArquivo) {
        return getEndrRemotoRecursosDoObjeto(pItem.getClass(), pTipoAcesso, pTipoArquivo) + "/ " + categoria + "/" + nomeArquivo;
    }

    @Override
    public String getEndrLocalRecursosDoObjeto(Class entidade) {
        return getEndrLocalResourcesObjeto() + "/" + entidade.getSimpleName();
    }

    public TIPO_ESTRUTURA_LOCAL_XHTML_PADRAO getTipoEstruturaCaminhoFormPadrao() {
        return tipoEstruturaCaminhoFormPadrao;
    }

    @Override
    public FabTipoEmpacotamento getTipoEmpacotamento() {
        return tipoEmpacotamento;
    }

}
