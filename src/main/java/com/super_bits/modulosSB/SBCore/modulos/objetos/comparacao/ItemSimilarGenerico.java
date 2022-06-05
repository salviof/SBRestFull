/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringComparador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampoInvalido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAssistenteDeLocalizacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.TipoOrganizacaoDadosEndereco;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
public abstract class ItemSimilarGenerico<T> implements ItfBeanSimples, ItfItemSimilarGenerico<T> {

    protected final ItfBeanSimples objetoAnalizado;
    protected double nota;
    protected final List<Double> notasValidas = new ArrayList<>();
    protected final List<Double> notasIdentico = new ArrayList<>();
    protected final List<Double> notasIdenticoInicio = new ArrayList<>();
    protected final String textoReferenciaPesquisa;
    protected final String termoPesquisa;

    public ItemSimilarGenerico(ItfBeanSimples pObjetoAnalizado, String pTermpoPesquisa) {
        try {
            this.objetoAnalizado = pObjetoAnalizado;
            nota = 0;
            if (pTermpoPesquisa == null) {
                nota = -1;
                termoPesquisa = null;
                textoReferenciaPesquisa = null;
                return;
            }
            termoPesquisa = pTermpoPesquisa.toLowerCase();

            textoReferenciaPesquisa = getTextoReferencia();
            try {

                if (textoReferenciaPesquisa == null || textoReferenciaPesquisa.isEmpty()) {
                    nota = -1;
                    return;
                }

                Arrays.stream(pTermpoPesquisa.split("\\s"))
                        .forEach(parteParametro -> {
                            Arrays.stream(textoReferenciaPesquisa.toLowerCase().replace("-", " ").split("\\s"))
                                    .forEach(parteTextoAnalizadao -> {
                                        if (UtilSBCoreStringValidador.isNuloOuEmbranco(parteTextoAnalizadao)) {
                                            return;
                                        }
                                        if (parteTextoAnalizadao.length() > parteParametro.length()) {
                                            parteTextoAnalizadao = parteTextoAnalizadao.substring(0, parteParametro.length());
                                        }
                                        double nt = UtilSBCoreStringComparador.JaroWinkler(parteTextoAnalizadao, parteParametro);
                                        if (nt > 0.8) {
                                            if (nt >= 0.9) {
                                                notasIdentico.add(nt);
                                                if (textoReferenciaPesquisa.toLowerCase().startsWith(parteParametro)) {
                                                    notasIdenticoInicio.add(nt);
                                                }
                                            } else {
                                                notasValidas.add(nt);
                                            }
                                        }
                                        if (nt > nota) {
                                            nota = nt;
                                        }
                                    });

                        });
                if (pTermpoPesquisa.contains("@")) {
                    String[] partesEmail = textoReferenciaPesquisa.split("@");

                    String dominioDoItem;

                    dominioDoItem = partesEmail[1];

                    String[] partesEmailPesquisa = termoPesquisa.split("@");

                    String dominioDoItemPesquisado;

                    dominioDoItemPesquisado = partesEmailPesquisa[1];

                    double ntaDominio = UtilSBCoreStringComparador.JaroWinkler(dominioDoItem, dominioDoItemPesquisado);
                    notasValidas.add(ntaDominio);
                    if (ntaDominio >= 0.9) {
                        notasIdenticoInicio.add(ntaDominio);

                    }

                }

                if (!notasValidas.isEmpty()) {
                    nota = (notasValidas.stream().mapToDouble(Double::doubleValue)
                            .sum() / notasValidas.size());
                }
                if (!notasIdentico.isEmpty()) {
                    nota += (notasIdentico.size() * 1);
                }
                if (!notasIdenticoInicio.isEmpty()) {
                    nota += (notasIdenticoInicio.size() * 2);
                }
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando indice de semelhanca", t);
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro construindo objeto comparavel", t);
            throw new UnsupportedOperationException("Falha criando objeto comparavel, com parametros" + pObjetoAnalizado + " - " + pTermpoPesquisa);
        }
    }

    @Override
    public double getNota() {
        return nota;
    }

    @Override
    public String getNomeUnicoSlug() {
        return objetoAnalizado.getNomeUnicoSlug();
    }

    @Override
    public boolean validar() {
        return objetoAnalizado.validar();
    }

    @Override
    public void setNome(String pNome) {

    }

    @Override
    public List<ItfMensagem> validarComMensagens() {
        return objetoAnalizado.validarComMensagens();
    }

    @Override
    public boolean uploadArquivoDeEntidade(ItfCampoInstanciado prcampo, byte[] pStream, String pNomeArquivo) {
        return objetoAnalizado.uploadArquivoDeEntidade(prcampo, pStream, pNomeArquivo);
    }

    @Override
    public boolean isTemImagemPequenaAdicionada() {
        return objetoAnalizado.isTemImagemPequenaAdicionada();
    }

    @Override
    public String getSlugIdentificador() {
        return objetoAnalizado.getSlugIdentificador();
    }

    @Override
    public String getNomeCurto() {
        return objetoAnalizado.getNomeCurto();
    }

    @Override
    public String getNome() {
        return objetoAnalizado.getNome();
    }

    @Override
    public String getIconeDaClasse() {
        return objetoAnalizado.getIconeDaClasse();
    }

    @Override
    public String getXhtmlVisao() {
        return objetoAnalizado.getXhtmlVisao();
    }

    @Override
    public String getXhtmlVisaoMobile() {
        return objetoAnalizado.getXhtmlVisaoMobile();
    }

    @Override
    public String getXhtmlVisao(int numeroColunas) {
        return objetoAnalizado.getXhtmlVisao(numeroColunas);
    }

    @Override
    public int getId() {
        return objetoAnalizado.getId();
    }

    @Override
    public int configIDPeloNome() {
        return objetoAnalizado.configIDPeloNome();
    }

    @Override
    public String getNomeDoObjeto() {
        return objetoAnalizado.getNomeDoObjeto();
    }

    @Override
    public String getNomeDoObjetoPlural() {
        return objetoAnalizado.getNomeDoObjetoPlural();
    }

    @Override
    public void adicionarItemNaLista(String nomeDaLista) {
        objetoAnalizado.adicionarItemNaLista(nomeDaLista);
    }

    @Override
    public boolean isTemCampoAnotado(FabTipoAtributoObjeto pCampo) {
        return objetoAnalizado.isTemCampoAnotado(pCampo);
    }

    @Override
    public String getImgPequena() {
        return objetoAnalizado.getImgPequena();
    }

    @Override
    public void setId(int pID) {

    }

    @Override
    public void adicionarJustificativaExecucaoAcao(ItfAcaoDoSistema pAcao, String pJustificativa) {

    }

    @Override
    public String getJustificativa(ItfAcaoDoSistema pAcao) {
        return objetoAnalizado.getJustificativa(pAcao);
    }

    @Override
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        objetoAnalizado.prepararNovoObjeto(parametros);
    }

    @Override
    public List<ItfCampoInstanciado> getCamposInstanciados() {
        return objetoAnalizado.getCamposInstaciadosInvalidos();
    }

    @Override
    public void adicionarSubItem(String pNomeCampo) {

    }

    @Override
    public ItfAssistenteDeLocalizacao getAssistenteLocalizacao(ItfCampoInstanciado pCampoInst, TipoOrganizacaoDadosEndereco pTipo) {
        return objetoAnalizado.getAssistenteLocalizacao(pCampoInst, pTipo);
    }

    @Override
    public void adicionarAssitenteLocalizacao(ItfAssistenteDeLocalizacao pLocalizacao) {

    }

    @Override
    public void copiaDados(Object pObjetoReferencia) {
        objetoAnalizado.copiaDados(pObjetoReferencia);
    }

    @Override
    public List<ItfCampoInstanciado> getCamposInstaciadosInvalidos() {
        return objetoAnalizado.getCamposInstaciadosInvalidos();
    }

    @Override
    public ItfCampoInstanciado getCampoByNomeOuAnotacao(String pNome) {
        return objetoAnalizado.getCampoInstanciadoByNomeOuAnotacao(pNome);
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciadoByNomeOuAnotacao(String pNome) {
        return objetoAnalizado.getCampoInstanciadoByNomeOuAnotacao(pNome);
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto pTipocampo) {
        return objetoAnalizado.getCampoInstanciadoByAnotacao(pTipocampo);
    }

    @Override
    public ItfCampoInstanciado getCampoByCaminhoCampo(ItfCaminhoCampo pNome) {
        return objetoAnalizado.getCampoByCaminhoCampo(pNome);
    }

    @Override
    public Object getValorCampoByCaminhoCampo(ItfCaminhoCampo pNome) {
        return objetoAnalizado.getValorCampoByCaminhoCampo(pNome);
    }

    @Override
    public List<ItfCaminhoCampo> getEntidadesVinculadas() {
        return objetoAnalizado.getEntidadesVinculadas();
    }

    @Override
    public ItfBeanSimples getBeanSimplesPorNomeCampo(String pNomeCampo) {
        return objetoAnalizado.getBeanSimplesPorNomeCampo(pNomeCampo);
    }

    @Override
    public ItfBeanSimples getItemPorCaminhoCampo(ItfCaminhoCampo pCaminho) {
        return objetoAnalizado.getItemPorCaminhoCampo(pCaminho);
    }

    @Override
    public List<ItfBeanSimples> getListaPorCaminhoCampo(ItfCaminhoCampo pCaminho) {
        return objetoAnalizado.getListaPorCaminhoCampo(pCaminho);
    }

    @Override
    public List<ItfCaminhoCampoInvalido> getCamposInvalidos() {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    @Override
    public Field getCampoReflexaoByAnotacao(FabTipoAtributoObjeto pInfoCampo) {
        return objetoAnalizado.getCampoReflexaoByAnotacao(pInfoCampo);
    }

    @Override
    public String getNomeCampo(FabTipoAtributoObjeto pInfocampo) {
        return objetoAnalizado.getNomeCampo(pInfocampo);
    }

    @Override
    public boolean uploadFotoTodosFormatos(InputStream pStream) {
        return objetoAnalizado.uploadFotoTodosFormatos(pStream);
    }

    @Override
    public boolean uploadFotoTamanhoGrande(InputStream pStream) {
        return objetoAnalizado.uploadFotoTamanhoGrande(pStream);
    }

    @Override
    public boolean uploadFotoTamanhoPequeno(InputStream pStream) {
        return objetoAnalizado.uploadFotoTamanhoPequeno(pStream);
    }

    @Override
    public boolean uploadFotoTamanhoMedio(InputStream pStream) {
        return objetoAnalizado.uploadFotoTamanhoMedio(pStream);
    }

    @Override
    public int compareTo(T o) {
        try {
            return Double.compare(((ItfItemSimilarGenerico) o).getNota(), nota);
        } catch (Throwable t) {
            return 0;
        }
    }

    @Override
    public ItfBeanSimples getObjetoAnalizado() {
        return objetoAnalizado;
    }

}
