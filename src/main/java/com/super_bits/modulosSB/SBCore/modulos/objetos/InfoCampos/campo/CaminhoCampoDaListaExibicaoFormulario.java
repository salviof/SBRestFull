/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 *
 *
 * @author desenvolvedor
 */
public class CaminhoCampoDaListaExibicaoFormulario implements ItfCaminhoCampoDaListaFormulario {

    private final ItfCampoExibicaoFormulario campoVinculado;
    private final CaminhoCampoListagemExibicaoFormulario caminhoListaPai;
    private final String caminhoRelativoListaPai;

    public CaminhoCampoDaListaExibicaoFormulario(CaminhoCampoListagemExibicaoFormulario pCaminhoLista, ItfCampoExibicaoFormulario pCampo) {
        campoVinculado = pCampo;
        caminhoListaPai = pCaminhoLista;
        caminhoRelativoListaPai = campoVinculado.getCaminhoComleto().replace(caminhoListaPai.getCaminhoComleto(), "").substring(1);
    }

    @Override
    public void addParteCaminho(String pParteCaminho) {
        campoVinculado.addParteCaminho(pParteCaminho);
    }

    @Override
    public void defineNomeCompleto(String pCaminho, Class pClasse) {
        campoVinculado.defineNomeCompleto(pCaminho);
    }

    @Override
    public void defineNomeCompleto(String pCaminho) {
        campoVinculado.defineNomeCompleto(pCaminho);
    }

    @Override
    public String getCaminhoComleto() {
        return campoVinculado.getCaminhoComleto();
    }

    @Override
    public String getCaminhoCompletoString() {
        return campoVinculado.getCaminhoCompletoString();
    }

    @Override
    public String getCaminhoSemNomeClasse() {
        return campoVinculado.getCaminhoSemNomeClasse();
    }

    @Override
    public Field getCampoFieldReflection() {
        return campoVinculado.getCampoFieldReflection();
    }

    @Override
    public int getId() {
        return campoVinculado.getId();
    }

    @Override
    public String getLabel() {
        return campoVinculado.getLabel();
    }

    @Override
    public String getLabelDoCampo() {
        return campoVinculado.getLabelDoCampo();
    }

    @Override
    public List<String> getPartesCaminho() {
        return campoVinculado.getPartesCaminho();
    }

    @Override
    public Class getTipoCampo() {
        return campoVinculado.getTipoCampo();
    }

    @Override
    public TIPO_ORIGEM_VALOR_CAMPO getTipoRegistro() {
        return campoVinculado.getTipoRegistro();
    }

    @Override
    public List<String> getTodasListas() {
        return campoVinculado.getTodasListas();
    }

    @Override
    public List<String> getTodosCaminhosPossiveis() {
        return campoVinculado.getTodosCaminhosPossiveis();
    }

    @Override
    public List<String> getTodosCaminhosPossiveisSemUltimoParametro() {
        return campoVinculado.getTodosCaminhosPossiveisSemUltimoParametro();
    }

    @Override
    public String getUltimoNome() {
        return campoVinculado.getUltimoNome();
    }

    @Override
    public boolean isUmCampoListavel() {
        return campoVinculado.isUmCampoListavel();
    }

    @Override
    public boolean isUmCampoSeparador() {
        return campoVinculado.isUmCampoSeparador();
    }

    @Override
    public boolean isUmCampoVinculado() {
        return campoVinculado.isUmCampoVinculado();
    }

    @Override
    public boolean isUmTipoComOutrasPropriedades() {
        return campoVinculado.isUmTipoComOutrasPropriedades();
    }

    @Override
    public boolean isUmaEntidade() {
        return campoVinculado.isUmaEntidade();
    }

    @Override
    public void setCaminhoComleto(String caminhoComleto) {
        campoVinculado.setCaminhoComleto(caminhoComleto);
    }

    @Override
    public void setId(int pID) {
        campoVinculado.setId(pID);
    }

    @Override
    public boolean temUmTipoComOutrasPropriedades() {
        return campoVinculado.temUmTipoComOutrasPropriedades();
    }

    @Override
    public boolean isUmCampoFilhoDeLista() {
        return true;
    }

    @Override
    public boolean isUmCampoComLista() {
        return false;
    }

    @Override
    public ItfCampoListagemExibicaoFormulario getCaminhoListaPai() {
        return caminhoListaPai;
    }

    @Override
    public void setIdAtualizacao(String idAtualizacao) {
        campoVinculado.setIdAtualizacao(idAtualizacao);
    }

    @Override
    public void setAtualizarFormAoAlterar(boolean atualizarFormAoAlterar) {
        campoVinculado.setAtualizarFormAoAlterar(atualizarFormAoAlterar);
    }

    @Override
    public void setAtualizarIdSelecionadoAoAlterar(boolean atualizarIdSelecionadoAoAlterar) {
        campoVinculado.setAtualizarIdSelecionadoAoAlterar(atualizarIdSelecionadoAoAlterar);
    }

    @Override
    public boolean isAtualizarFormAoAlterar() {
        return campoVinculado.isAtualizarFormAoAlterar();
    }

    @Override
    public boolean isAtualizarIdSelecionadoAoAlterar() {
        return campoVinculado.isAtualizarIdSelecionadoAoAlterar();
    }

    @Override
    public String getIdAtualizacao() {
        return campoVinculado.getIdAtualizacao();
    }

    @Override
    public void setLabelAlternativo(String labelAlternativo) {
        campoVinculado.setLabelAlternativo(labelAlternativo);
    }

    @Override
    public void setAtualizarGrupoAoAlterar(boolean atualizarGrupoAoAlterar) {
        campoVinculado.setAtualizarFormAoAlterar(atualizarGrupoAoAlterar);
    }

    @Override
    public ItfCampoListagemExibicaoFormulario getComoCampoListagem() {
        try {
            throw new UnsupportedOperationException("O campo não é do tipo Listagem");
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando ler um campo de formulário simples, como se fosse um campo de listagem", t);

        }
        return null;
    }

    @Override
    public CaminhoCampoReflexao getCaminhoCampo() {
        return (CaminhoCampoReflexao) campoVinculado.getCaminhoCampo();
    }

    @Override
    public String toString() {
        return getCaminhoCompletoString();
    }

    public String getCaminhoParcialSemListaPai() {
        return campoVinculado.getCaminhoComleto().replace(caminhoListaPai.getCaminhoComleto() + ".", "");
    }

    @Override
    public String getPrimeiroCaminhoSemNomeClasse() {
        return campoVinculado.getPrimeiroCaminhoSemNomeClasse();
    }

    @Override
    public String getIdentificadorGrupoCampo() {
        return campoVinculado.getIdentificadorGrupoCampo();
    }

    @Override
    public String getCaminhoCompletoComGrupoCampo() {
        return campoVinculado.getCaminhoCompletoComGrupoCampo();
    }

    @Override
    public String getCaminhoApenasClasseInicial() {
        return campoVinculado.getCaminhoApenasClasseInicial();
    }

    @Override
    public String getLabelAlternativo() {
        return campoVinculado.getLabelAlternativo();
    }

    @Override
    public String getCaminhoCampoRelativoAListaPai() {
        return caminhoRelativoListaPai;
    }

    @Override
    public boolean isSomenteLeitura() {
        return campoVinculado.isSomenteLeitura();
    }

    @Override
    public void setSomenteLeitura(boolean b) {
        campoVinculado.setSomenteLeitura(b);
    }

}
