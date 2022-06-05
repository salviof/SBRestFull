/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.FabTipoEmpacotamento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicao;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo.FabTipoAcessoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralPermissaoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvioF
 */
public class CentralDeArquivosJar extends CentralDeArquivosAbstrata {

    private static final String TEXTOPADRAO = "Central de arquivos em Jars ainda não foi implementado";
    private final String PASTA_ARQUIVOS_DE_ENTIDADE_JUNIT = SBCore.getCaminhoGrupoProjetoSource() + "/arquivosDeEntidade";
    private final String PASTA_ARQUIVOS_DE_ENTIDADE_PRODUCAO = "/home/servidorSBFW/arquivosDeEntidade/" + SBCore.getGrupoProjeto();
    private final String PASTA_ARQUIVOS_ENTIDADE = "arquivos";

    public CentralDeArquivosJar() {
        super(FabTipoEmpacotamento.BIBLIOTECA_JAR, TIPO_ESTRUTURA_LOCAL_XHTML_PADRAO.MODULO_PREFIXO_SLUG_DO_MB_DE_GESTAO);

    }

    @Override
    public String getEndrLocalResources() {
        if (SBCore.isEmModoProducao()) {
            return PASTA_ARQUIVOS_DE_ENTIDADE_PRODUCAO;
        } else {
            return PASTA_ARQUIVOS_DE_ENTIDADE_JUNIT;
        }
    }

    @Override
    public String getEndrLocalImagens() {
        if (SBCore.isEmModoProducao()) {
            return PASTA_ARQUIVOS_DE_ENTIDADE_PRODUCAO;
        } else {
            return PASTA_ARQUIVOS_DE_ENTIDADE_JUNIT;
        }
    }

    @Override
    public String getEndrRemotoImagens() {
        if (SBCore.isEmModoProducao()) {
            return PASTA_ARQUIVOS_DE_ENTIDADE_PRODUCAO;
        } else {
            return PASTA_ARQUIVOS_DE_ENTIDADE_JUNIT;
        }
    }

    @Override
    public String getNomeRemotoPastaImagem() {
        return TEXTOPADRAO;
    }

    @Override
    public String getNomeLocalPastaImagem() {
        return TEXTOPADRAO;
    }

    @Override
    public String getEndrRemotoIMGPadraoPorTipoCampo(FabTipoAtributoObjeto tipo) {
        return TEXTOPADRAO;
    }

    @Override
    public String getEntdrRemotoIMGPadraoPorTipoClasse(Class entidade) {
        return TEXTOPADRAO;
    }

    @Override
    public String getEndrRemotoResources() {
        return TEXTOPADRAO;
    }

    @Override
    public String getEndrRemotoResourcesObjeto() {
        return TEXTOPADRAO;
    }

    @Override
    public String getEndrLocalRecursosDoObjeto(Class entidade, String pGaleria) {
        return TEXTOPADRAO;
    }

    @Override
    public String getEndrRemotoImagem(ItfBeanSimplesSomenteLeitura item, FabTipoAtributoObjeto tipo) {
        return TEXTOPADRAO;
    }

    @Override
    public List<String> getEnterecosLocaisRecursosItem(ItfBeanSimples item, String galeria) {
        return new ArrayList<>();
    }

    @Override
    public String getEndrLocalArquivoItem(ItfBeanSimplesSomenteLeitura pItem, String nomeArquivo) {
        return TEXTOPADRAO;
    }

    @Override
    public String getEndrRemotoRecursosDoObjeto(Class entidade, FabTipoAcessoArquivo tipoAcesso, FabTipoArquivoConhecido pTipoArquivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrRemotoRecursosItem(ItfBeanSimples item, String galeria, FabTipoAcessoArquivo pTipoAcesso, FabTipoArquivoConhecido pTipoArquivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getEnterecosRemotosRecursosItem(ItfBeanSimplesSomenteLeitura item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getEndrsLocaisDeCategoriasItem(ItfBeanSimplesSomenteLeitura item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getNomesPastasCategoriasItem(ItfBeanSimplesSomenteLeitura item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean salvarImagemTodosOsFormatos(ItfBeanSimplesSomenteLeitura entidade, InputStream foto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean salvarImagemTamanhoMedio(ItfBeanSimplesSomenteLeitura entidade, InputStream foto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean salvarImagemTamanhoPequeno(ItfBeanSimplesSomenteLeitura entidade, InputStream foto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean salvarImagemTamanhoGrande(ItfBeanSimplesSomenteLeitura entidade, InputStream foto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean salvarArquivo(ItfBeanSimplesSomenteLeitura entidade, byte[] arquivo, String nomeCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean baixarArquivo(ItfBeanSimplesSomenteLeitura entidade, InputStream arqivo, String pNomeCampoOuCategoria, String pNomeArquivo, MapaSubstituicao mapaSubistituicao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCentralDePermissao(ItfCentralPermissaoArquivo pPermissao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfCentralPermissaoArquivo getCentralPermissao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalTemporarioArquivoCampoInstanciado(ItfCampoInstanciado pCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalResourcesObjeto() {
        return getEndrLocalResources() + "/" + PASTA_ARQUIVOS_ENTIDADE;
    }

    @Override
    public String getEndrRemotoBaixarArquivoPastaTemporaria(String pNomeArquivo) {
        return SBCore.getControleDeSessao().getSessaoAtual().getPastaTempDeSessao() + "/" + pNomeArquivo;
    }

    @Override
    public String getEndrRemotoAbrirArquivoPastaTemporaria(String pNomeArquivo) {
        return SBCore.getControleDeSessao().getSessaoAtual().getPastaTempDeSessao() + "/" + pNomeArquivo;
    }

    @Override
    public String getEndrLocalImagem(ItfBeanSimplesSomenteLeitura item, FabTipoAtributoObjeto tipo) {
        String diretorioBase = getEndrLocalImagens() + "/" + item.getClass().getSimpleName() + "/" + item.getId() + "/";
        switch (tipo) {

            case IMG_PEQUENA:
                return diretorioBase + "imagemLogoPequeno.jpg";

            case IMG_MEDIA:
                return diretorioBase + "imagemLogoMedio.jpg";
            case IMG_GRANDE:
                return diretorioBase + "imagemLogoGrande.jpg";
            default:
                return diretorioBase + "imagemLogoPequeno.jpg";

        }

    }

    @Override
    public String getEntrLocalArquivosFormulario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalImagem(ItfBeanSimplesSomenteLeitura item, FabTipoAtributoObjeto tipo, ItfSessao pSessao) {
        String diretorioBase = "ERRO";
        if (item.getId() == 0) {
            diretorioBase = pSessao.getPastaTempDeSessao() + "/" + item.getClass().getSimpleName() + "/0/";
        } else {
            diretorioBase = getEndrLocalImagens() + "/" + item.getClass().getSimpleName() + "/" + item.getId() + "/";
        }
        switch (tipo) {

            case IMG_PEQUENA:
                return diretorioBase + "imagemLogoPequeno.jpg";

            case IMG_MEDIA:
                return diretorioBase + "imagemLogoMedio.jpg";
            case IMG_GRANDE:
                return diretorioBase + "imagemLogoGrande.jpg";
            default:
                return diretorioBase + "imagemLogoPequeno.jpg";

        }
    }

    @Override
    public boolean excluirArquivo(ItfCampoInstanciado pCampo) {
        try {
            String arquivo = SBCore.getCentralDeArquivos().getEndrLocalArquivoCampoInstanciado(pCampo);

            File novoFile = new File(arquivo);
            novoFile.delete();
            pCampo.setValor(null);
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha excluindo" + pCampo, t);
            return false;
        }
    }

    @Override
    public String getHashArquivoDeEntidadeRegistrado(ItfCampoInstanciado pCampo) {
        return null;
    }

}
