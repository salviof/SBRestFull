/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces;

import com.super_bits.modulosSB.SBCore.ConfigGeral.FabTipoEmpacotamento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicao;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.TIPO_ESTRUTURA_LOCAL_XHTML_PADRAO;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo.FabTipoAcessoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import java.io.File;

import java.io.InputStream;
import java.util.List;

/**
 *
 * ############################################################# <br/>
 * Esta Interface assina um dos serviços do núcleo SBCore
 * <br/>
 * Veja todos os serviços disponíveis digitando SBCore.getServico...
 * ############################################################# <br/>
 *
 *
 * O serviço de arquivos de entidade, manipula um arquivo vinculado a um
 * registro de entidade do sistema, e associação de imagens tamanho pequeno
 * médio e grande vinculado a um objeto
 *
 *
 *
 * @author salvioF
 */
public interface ItfCentralDeArquivos {

    public final static String PALAVRA_CHAVE_NOME_ARQUIVO = "[nomeArquivo]";
    public final static String PALAVRA_CHAVE_TRECHO_CONTEUDO = "[trechoConteudo]";
    public final static String CATEGORIA_PADRAO_ARQUIVO_DE_REGISTRO = "arquivoDeRegistro";

    public String getEntrLocalArquivosFormulario();

    /**
     * Retorna o caminho completo onde todos os arquivos do projeto ficarão
     * armazenados
     *
     * @return caminho onde todos os arquivos do projeto ficarão armazenados
     */
    public String getEndrLocalResources();

    public String getEndrRemotoResources();

    /**
     *
     * Retorna o caminho completo onde ficarão os arquivos referentes a
     * entidades ex de arquivos de entidade
     * diretorioAplicacao/pastaLocalResources/pastaLocalResourceObjeto/Cliente/1/fotoDocliente.jpg
     *
     * @return caminho local completo dos arquivos de objeto
     */
    public String getEndrLocalResourcesObjeto();

    public String getEndrRemotoResourcesObjeto();

    /**
     *
     * Retorna a pasta Onde as imagens do sistema são armazenadas
     *
     *
     * @return
     */
    public String getEndrLocalImagens();

    /**
     * Retorna o caminho remoto para pasta imagens: ex:
     * http://sitedoprojeto.com.br/resources/img
     *
     *
     * @return Caminho http completo para pasta de imagens
     */
    public String getEndrRemotoImagens();

    /**
     *
     * ex: /resources/img
     *
     * @return Caminho relativo ao dominio para pasta imagens
     */
    public String getNomeRemotoPastaImagem();

    /**
     *
     * Nome da pasta de imagens, ex:
     *
     * @return
     */
    public String getNomeLocalPastaImagem();

    /**
     *
     * Eto
     *
     * ex: diretorioAplicacao/pastaLocalResources/objetos/Cliente
     *
     *
     * @param entidade
     * @return
     */
    public String getEndrLocalRecursosDoObjeto(Class entidade);

    public String getEndrLocalRecursosDoObjeto(Class entidade, String pGaleria);

    /**
     *
     * ex: http://www.meuprojeto.com.br/resoureces/objetos/Cliente
     *
     * @Deprecated talvez este método deva ser privado, mais análises
     * nesscessárias (29-2020) Salvio Furbino
     *
     * @param entidade
     * @param tipoAcesso
     * @param pTipoArquivo
     * @return
     */
    @Deprecated
    public String getEndrRemotoRecursosDoObjeto(Class entidade, FabTipoAcessoArquivo tipoAcesso, FabTipoArquivoConhecido pTipoArquivo);

    /**
     *
     * O endereço remoto que representa determinado campo padrão:
     *
     * ex: http://meuprojeto.com.br/resources/img/imagemNaoDisponivel.jpg
     *
     *
     * @param tipo
     * @return
     */
    public String getEndrRemotoIMGPadraoPorTipoCampo(FabTipoAtributoObjeto tipo);

    /**
     * O endereço remoto que representa determinada Entidade, ex:
     *
     * ex: http://meuprojeto.com.br/resources/objeto/Cliente/clienteSemFoto.jpg
     *
     * @param entidade
     * @return
     */
    public String getEntdrRemotoIMGPadraoPorTipoClasse(Class entidade);

    /**
     *
     * REtorna a URL da imagem de acordo com o tipo de campo e entidade
     *
     * * ex:
     * http://meuprojeto.com.br/resources/objeto/Cliente/campoExcentrico/imagemCampoExcentrico.jpg
     *
     * @param item
     * @param tipo
     * @return
     */
    public String getEndrRemotoImagem(ItfBeanSimplesSomenteLeitura item, FabTipoAtributoObjeto tipo);

    /**
     *
     * @Deprecated -poderia ser um metodo privado? 29/05/2020 Sálvio Furbino
     *
     * @param item
     * @param galeria
     * @param pTipoAcesso
     * @param pTipoArquivo
     * @return
     */
    @Deprecated
    public String getEndrRemotoRecursosItem(ItfBeanSimples item, String galeria, FabTipoAcessoArquivo pTipoAcesso, FabTipoArquivoConhecido pTipoArquivo);

    /**
     *
     *
     *
     * @param item
     * @param galeria
     * @return
     */
    @Deprecated
    public List<String> getEnterecosLocaisRecursosItem(ItfBeanSimples item, String galeria);

    /**
     *
     * @param item
     * @return
     */
    public List<String> getEnterecosRemotosRecursosItem(ItfBeanSimplesSomenteLeitura item);

    /**
     *
     * Retorna os caminhos para pastas de categoria do objeto
     *
     * As subpastas no diretorio da classe são as categorias ex:
     *
     * diretorioAplicacao/pastaLocalResources/pastaLocalResourceObjeto/Cliente/1/categoria1/
     *
     *
     * @param item
     * @return
     */
    public List<String> getEndrsLocaisDeCategoriasItem(ItfBeanSimplesSomenteLeitura item);

    /**
     * Retorna o nome das pastas de categoria do objeto
     *
     * As subpastas no diretorio da classe são as categorias ex:
     *
     * diretorioAplicacao/pastaLocalResources/pastaLocalResourceObjeto/Cliente/1/categoria1/
     *
     * @param item
     * @return
     */
    public List<String> getNomesPastasCategoriasItem(ItfBeanSimplesSomenteLeitura item);

    /**
     *
     * @param pItem
     * @param nomeArquivo
     * @return
     */
    public String getEndrLocalArquivoItem(ItfBeanSimplesSomenteLeitura pItem, String nomeArquivo);

    /**
     *
     * @param pItem
     * @param nomeArquivo
     * @param Categoria
     * @return
     */
    public String getEndrLocalArquivoItem(ItfBeanSimplesSomenteLeitura pItem, String nomeArquivo, String Categoria);

    /**
     *
     * @param pItem
     * @param nomeArquivo
     * @param pTipoAcesso
     * @param pTipoArquivo
     * @return
     */
    public String getEndrRemotoArquivoItem(ItfBeanSimplesSomenteLeitura pItem, String nomeArquivo, FabTipoAcessoArquivo pTipoAcesso, FabTipoArquivoConhecido pTipoArquivo);

    /**
     *
     * @param pItem
     * @param nomeArquivo
     * @param Categoria
     * @return
     */
    public String getEndrRemotoArquivoItem(ItfBeanSimplesSomenteLeitura pItem, String nomeArquivo, String Categoria, FabTipoAcessoArquivo pTipoAcesso, FabTipoArquivoConhecido pTipoArquivo);

    /**
     *
     *
     *
     * @param entidade
     * @param foto
     *
     */
    public boolean salvarImagemTodosOsFormatos(ItfBeanSimplesSomenteLeitura entidade, InputStream foto);

    /**
     *
     *
     *
     * @param entidade
     * @param foto
     *
     */
    public boolean salvarImagemTamanhoMedio(ItfBeanSimplesSomenteLeitura entidade, InputStream foto);

    /**
     *
     *
     *
     * @param entidade
     * @param foto
     *
     */
    public boolean salvarImagemTamanhoPequeno(ItfBeanSimplesSomenteLeitura entidade, InputStream foto);

    /**
     *
     *
     *
     * @param entidade
     * @param foto
     * @return
     *
     */
    public boolean salvarImagemTamanhoGrande(ItfBeanSimplesSomenteLeitura entidade, InputStream foto);

    /**
     *
     * @param entidade
     * @param arqivo
     * @param nome
     * @param categoria
     * @return
     */
    public boolean salvarArquivo(ItfBeanSimplesSomenteLeitura entidade, byte[] arqivo, String categoria, String nome);

    /**
     *
     * @param entidade
     * @param arquivo
     * @param nomeCampo
     * @return
     */
    public boolean salvarArquivo(ItfBeanSimplesSomenteLeitura entidade, byte[] arquivo, String nomeCampo);

    /**
     *
     *
     * @param arquivo
     * @param pCampo
     * @param pNomeArquivo
     *
     * @return
     */
    public boolean salvarArquivo(ItfCampoInstanciado pCampo, byte[] arquivo, String pNomeArquivo);

    /**
     *
     *
     * @Deprecated não está clara a função deste método 19/05/2020 Salvio
     * Furbino
     *
     * @param entidade
     * @param arqivo
     * @param pNomeCampoOuCategoria Categoria (nome da subpasta, em geral é o
     * nome original do atributo(campo da tabala) vinculado )
     * @param pNomeArquivo
     * @param mapaSubistituicao
     * @return
     */
    @Deprecated
    public boolean baixarArquivo(ItfBeanSimplesSomenteLeitura entidade, InputStream arqivo, String pNomeCampoOuCategoria, String pNomeArquivo, MapaSubstituicao mapaSubistituicao);

    public String getEndrLocalImagem(ItfBeanSimplesSomenteLeitura item, FabTipoAtributoObjeto tipo, ItfSessao pSessao);

    public String getEndrLocalImagem(ItfBeanSimplesSomenteLeitura item, FabTipoAtributoObjeto tipo);

    public void setCentralDePermissao(ItfCentralPermissaoArquivo pPermissao);

    public ItfCentralPermissaoArquivo getCentralPermissao();

    public String getEndrLocalArquivoCampoInstanciado(ItfCampoInstanciado pCampo);

    public String getEndrLocalTemporarioArquivoCampoInstanciado(ItfCampoInstanciado pCampo);

    public String getEndrRemotoArquivoCampoInstanciadoBaixar(ItfCampoInstanciado pCampo);

    public String getEndrRemotoBaixarArquivoPastaTemporaria(String pNomeArquivo);

    public String getEndrRemotoAbrirArquivoPastaTemporaria(String pNomeArquivo);

    public String getEndrRemotoArquivoCampoInstanciadoAbrir(ItfCampoInstanciado pCampo);

    public String getEndrLocalArquivoTemporario(String pCategoria, String pEntidade, String nomeArquivo);

    public FabTipoEmpacotamento getTipoEmpacotamento();

    public TIPO_ESTRUTURA_LOCAL_XHTML_PADRAO getTipoEstruturaCaminhoFormPadrao();

    public default boolean isTemImagem(ItfBeanSimplesSomenteLeitura item, FabTipoAtributoObjeto tipo) {
        try {
            String caminhoLocal = getEndrLocalImagem(item, tipo);
            if (caminhoLocal.startsWith("http")) {
                return true;
            }
            return new File(caminhoLocal).exists();
        } catch (Throwable t) {
            return false;
        }
    }

    public default boolean isExisteArquivo(ItfCampoInstanciado pCampo) {
        try {
            String caminhoLocal = getEndrLocalArquivoCampoInstanciado(pCampo);

            if (caminhoLocal.startsWith("http")) {
                return true;
            }
            return new File(caminhoLocal).exists();
        } catch (Throwable t) {
            return false;
        }
    }

    public boolean excluirArquivo(ItfCampoInstanciado pCampo);

    public String getHashArquivoDeEntidadeRegistrado(ItfCampoInstanciado pCampo);

}
