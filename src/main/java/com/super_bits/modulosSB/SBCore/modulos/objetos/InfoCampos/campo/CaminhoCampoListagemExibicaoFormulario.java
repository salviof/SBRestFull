/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public class CaminhoCampoListagemExibicaoFormulario implements ItfCampoListagemExibicaoFormulario {

    private final ItfCampoExibicaoFormulario caminhoCampoReflexao;

    private final Map<String, CaminhoCampoDaListaExibicaoFormulario> mapaCamposAdicionados = new HashMap<>();
    private final List<ItfCaminhoCampoDaListaFormulario> camposDoSubFormulario = new ArrayList<>();
    private GrupoCamposSub grupoCampo;

    public CaminhoCampoListagemExibicaoFormulario(CaminhoCampoExibicaoFormulario pCampoReferencia) {

        caminhoCampoReflexao = pCampoReferencia;
    }

    @Override
    public void adicionarCampo(ItfCampoExibicaoFormulario pCampo) {
        if (!mapaCamposAdicionados.containsKey(pCampo.getCaminhoSemNomeClasse())) {
            CaminhoCampoDaListaExibicaoFormulario campo = new CaminhoCampoDaListaExibicaoFormulario(this, pCampo);
            mapaCamposAdicionados.put(pCampo.getCaminhoSemNomeClasse(), campo);
            camposDoSubFormulario.add(campo);
        }
    }

    @Override
    public void addParteCaminho(String pParteCaminho) {
        caminhoCampoReflexao.addParteCaminho(pParteCaminho);
    }

    @Override
    public void defineNomeCompleto(String pCaminho, Class pClasse) {
        caminhoCampoReflexao.defineNomeCompleto(pCaminho);
    }

    @Override
    public void defineNomeCompleto(String pCaminho) {
        caminhoCampoReflexao.defineNomeCompleto(pCaminho);
    }

    @Override
    public String getCaminhoComleto() {
        return caminhoCampoReflexao.getCaminhoComleto();
    }

    @Override
    public String getCaminhoCompletoString() {
        return caminhoCampoReflexao.getCaminhoCompletoString();
    }

    @Override
    public String getCaminhoSemNomeClasse() {
        return caminhoCampoReflexao.getCaminhoSemNomeClasse();
    }

    @Override
    public Field getCampoFieldReflection() {
        return caminhoCampoReflexao.getCampoFieldReflection();
    }

    @Override
    public int getId() {
        return caminhoCampoReflexao.getId();
    }

    @Override
    public String getLabel() {
        return caminhoCampoReflexao.getLabel();
    }

    @Override
    public String getLabelDoCampo() {
        return caminhoCampoReflexao.getLabelDoCampo();
    }

    @Override
    public List<String> getPartesCaminho() {
        return caminhoCampoReflexao.getPartesCaminho();
    }

    @Override
    public Class getTipoCampo() {
        return caminhoCampoReflexao.getTipoCampo();
    }

    @Override
    public TIPO_ORIGEM_VALOR_CAMPO getTipoRegistro() {
        return caminhoCampoReflexao.getTipoRegistro();
    }

    @Override
    public List<String> getTodasListas() {
        return caminhoCampoReflexao.getTodasListas();
    }

    @Override
    public List<String> getTodosCaminhosPossiveis() {
        return caminhoCampoReflexao.getTodosCaminhosPossiveis();
    }

    @Override
    public List<String> getTodosCaminhosPossiveisSemUltimoParametro() {
        return caminhoCampoReflexao.getTodosCaminhosPossiveisSemUltimoParametro();
    }

    @Override
    public String getUltimoNome() {
        return caminhoCampoReflexao.getUltimoNome();
    }

    @Override
    public boolean isUmCampoListavel() {
        return caminhoCampoReflexao.isUmCampoListavel();
    }

    @Override
    public boolean isUmCampoSeparador() {
        return caminhoCampoReflexao.isUmCampoSeparador();
    }

    @Override
    public boolean isUmCampoVinculado() {
        return caminhoCampoReflexao.isUmCampoVinculado();
    }

    @Override
    public boolean isUmTipoComOutrasPropriedades() {
        return caminhoCampoReflexao.isUmTipoComOutrasPropriedades();
    }

    @Override
    public boolean isUmaEntidade() {
        return caminhoCampoReflexao.isUmaEntidade();
    }

    @Override
    public void setCaminhoComleto(String caminhoComleto) {
        caminhoCampoReflexao.setCaminhoComleto(caminhoComleto);
    }

    @Override
    public void setId(int pID) {
        caminhoCampoReflexao.setId(pID);
    }

    @Override
    public boolean temUmTipoComOutrasPropriedades() {
        return caminhoCampoReflexao.temUmTipoComOutrasPropriedades();
    }

    @Override
    public boolean isUmCampoFilhoDeLista() {
        return caminhoCampoReflexao.isUmCampoFilhoDeLista();
    }

    @Override
    public void setIdAtualizacao(String idAtualizacao) {
        caminhoCampoReflexao.setIdAtualizacao(idAtualizacao);
    }

    @Override
    public void setAtualizarFormAoAlterar(boolean atualizarFormAoAlterar) {
        caminhoCampoReflexao.setAtualizarFormAoAlterar(atualizarFormAoAlterar);
    }

    @Override
    public void setAtualizarIdSelecionadoAoAlterar(boolean atualizarIdSelecionadoAoAlterar) {
        caminhoCampoReflexao.setAtualizarFormAoAlterar(atualizarIdSelecionadoAoAlterar);
    }

    @Override
    public boolean isAtualizarFormAoAlterar() {
        return caminhoCampoReflexao.isAtualizarFormAoAlterar();
    }

    @Override
    public boolean isAtualizarIdSelecionadoAoAlterar() {
        return caminhoCampoReflexao.isAtualizarFormAoAlterar();
    }

    @Override
    public String getIdAtualizacao() {
        return caminhoCampoReflexao.getIdAtualizacao();
    }

    @Override
    public void setLabelAlternativo(String labelAlternativo) {
        caminhoCampoReflexao.setLabelAlternativo(labelAlternativo);
    }

    @Override
    public void setAtualizarGrupoAoAlterar(boolean atualizarGrupoAoAlterar) {
        caminhoCampoReflexao.setAtualizarGrupoAoAlterar(atualizarGrupoAoAlterar);
    }

    @Override
    public boolean isUmCampoComLista() {
        return true;
    }

    @Override
    public ItfCampoListagemExibicaoFormulario getComoCampoListagem() {
        return this;
    }

    @Override
    public List<ItfCaminhoCampoDaListaFormulario> getCamposDoSubFormulario() {
        return camposDoSubFormulario;
    }

    @Override
    public CaminhoCampoReflexao getCaminhoCampo() {
        return (CaminhoCampoReflexao) caminhoCampoReflexao.getCaminhoCampo();
    }

    @Override
    public ItfGrupoCampos getGrupoSubcampos() {
        if (camposDoSubFormulario.isEmpty()) {

        } else {
            grupoCampo = new GrupoCamposSub(this);

        }

        return grupoCampo;

    }

    @Override
    public String getPrimeiroCaminhoSemNomeClasse() {
        return caminhoCampoReflexao.getPrimeiroCaminhoSemNomeClasse();
    }

    @Override
    public String getIdentificadorGrupoCampo() {
        return caminhoCampoReflexao.getIdentificadorGrupoCampo();
    }

    @Override
    public String getCaminhoCompletoComGrupoCampo() {
        return caminhoCampoReflexao.getCaminhoCompletoComGrupoCampo();
    }

    @Override
    public String getCaminhoApenasClasseInicial() {
        return caminhoCampoReflexao.getCaminhoApenasClasseInicial();
    }

    @Override
    public String getLabelAlternativo() {
        return caminhoCampoReflexao.getLabelAlternativo();
    }

    @Override
    public boolean isSomenteLeitura() {
        return caminhoCampoReflexao.isSomenteLeitura();
    }

    @Override
    public void setSomenteLeitura(boolean b) {
        caminhoCampoReflexao.setSomenteLeitura(b);
    }

}
