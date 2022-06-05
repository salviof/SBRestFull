/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeituraInstanciado;
import java.util.HashMap;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvioF
 */
public enum FabTipoArquivoConhecido implements ItfFabrica {

    IMAGEM_WEB,
    IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO,
    IMAGE_REPRESENTATIVA_ENTIDADE_MEDIO,
    IMAGE_REPRESENTATIVA_ENTIDADE_GRANDE,
    VIDEO,
    DOCUMENTO_WORD_XDOC2007,
    FOLHA_DE_ESTILO_CSS,
    JAVASCRIPT,
    DOCUMENTO_PDF,
    ARQUIVO_TEXTO_SIMPLES;

    private static final Map<FabTipoArquivoConhecido, String> mapaSlugTipoArquivos = new HashMap();

    @Override
    public TipoRecurso getRegistro() {
        TipoRecurso novoRecurso = new TipoRecurso(this);
        novoRecurso.setId(this.ordinal() + 1);
        novoRecurso.setNome(this.toString());
        switch (this) {
            case IMAGEM_WEB:
                novoRecurso.setNome("IMG");
                break;
            case VIDEO:
                break;
            case DOCUMENTO_WORD_XDOC2007:
                novoRecurso.setNome("MS_WORD");
                break;
            case DOCUMENTO_PDF:
                novoRecurso.setNome("PDF");
                break;
            case ARQUIVO_TEXTO_SIMPLES:
                novoRecurso.setNome("TEXTO");
                break;
            case IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO:
                novoRecurso.setNome("IMG_LOGO_PEQUENO");
                break;
            case IMAGE_REPRESENTATIVA_ENTIDADE_MEDIO:
                novoRecurso.setNome("IMG_LOGO_TM_NORMAL");
                break;
            case IMAGE_REPRESENTATIVA_ENTIDADE_GRANDE:
                novoRecurso.setNome("IMG_LOGO_GRANDE");
                break;
            case FOLHA_DE_ESTILO_CSS:
                novoRecurso.setNome("css");
                break;
            case JAVASCRIPT:
                novoRecurso.setNome("JavaScript");
                break;
            default:
                throw new AssertionError(this.name());

        }
        return novoRecurso;
    }

    /**
     *
     * @param pNomeArquivo
     * @return O tipo conhecido a partir do nome do arquivo
     */
    public static FabTipoArquivoConhecido getTipoArquivoByNomeArquivo(String pNomeArquivo) {
        if (pNomeArquivo == null) {
            return ARQUIVO_TEXTO_SIMPLES;
        }
        String extencao = UtilSBCoreStringNomeArquivosEDiretorios.getExtencaoNomeArquivo(pNomeArquivo);
        switch (extencao) {
            case ".js":
                return JAVASCRIPT;
            case ".css":
                return FOLHA_DE_ESTILO_CSS;
            case ".docx":
                return DOCUMENTO_WORD_XDOC2007;
            case ".jpg":
            case ".gif":
            case ".png":
            case ".jpeg":
                return IMAGEM_WEB;
            case ".mpeg":

                return VIDEO;
            case ".pdf":
                return DOCUMENTO_PDF;
        }
        return ARQUIVO_TEXTO_SIMPLES;

    }

    public String getTipoConteudoRespostaHTML() {
        switch (this) {
            case JAVASCRIPT:
                return "application/javascript";
            case FOLHA_DE_ESTILO_CSS:
                return "text/css";
            case IMAGEM_WEB:
            case IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO:
            case IMAGE_REPRESENTATIVA_ENTIDADE_GRANDE:
            case IMAGE_REPRESENTATIVA_ENTIDADE_MEDIO:
                return "image/jpeg";
            case VIDEO:
                return "video/x-motion-jpeg";
            case DOCUMENTO_WORD_XDOC2007:
                return "application/msword";
            case DOCUMENTO_PDF:
                return "application/pdf";
            case ARQUIVO_TEXTO_SIMPLES:
                return "text/html";

            default:
                return "text/html";

        }
    }

    public String getSlugUrl() {
        String slug = mapaSlugTipoArquivos.get(this);
        if (slug == null) {
            slug = UtilSBCoreStringFiltros.gerarUrlAmigavel(this.getRegistro().getNome());
            if (slug != null) {
                mapaSlugTipoArquivos.put(this, slug);
            }
        }
        return slug;
    }

}
