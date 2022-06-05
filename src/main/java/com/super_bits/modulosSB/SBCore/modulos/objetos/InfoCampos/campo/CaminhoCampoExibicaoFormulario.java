/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.logica.exibeForm.CondicaoExibeCampoForm;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCaminhoCampo;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author SalvioF
 */
public class CaminhoCampoExibicaoFormulario implements ItfCampoExibicaoFormulario {

    private final CaminhoCampoReflexao caminhoCampo;
    private final boolean umCampoFilhoDeLista;
    private final String identificadorGrupoCampo;

    public CaminhoCampoExibicaoFormulario(CaminhoCampoReflexao pCaminhoCampo, String pIdentificacaoGrupo) {
        caminhoCampo = pCaminhoCampo;
        String caminhoSemUltimocampo = UtilSBCoreReflexaoCaminhoCampo.getStrCaminhoCampoSemUltimoCampo(pCaminhoCampo.getCaminhoComleto());
        identificadorGrupoCampo = pIdentificacaoGrupo;
        umCampoFilhoDeLista = caminhoSemUltimocampo.contains("[]");

    }

    private boolean atualizarFormAoAlterar = false;
    private boolean atualizarGrupoAoAlterar = false;
    private boolean obrigatorio;
    private boolean atualizarIdSelecionadoAoAlterar = false;
    private boolean somenteLeitura = false;
    private String idAtualizacao;
    private String labelAlternativo;
    private CondicaoExibeCampoForm condicao;

    @Override
    public boolean isAtualizarFormAoAlterar() {
        return atualizarFormAoAlterar;
    }

    @Override
    public void setAtualizarFormAoAlterar(boolean atualizarFormAoAlterar) {
        this.atualizarFormAoAlterar = atualizarFormAoAlterar;
    }

    @Override
    public boolean isAtualizarIdSelecionadoAoAlterar() {
        return atualizarIdSelecionadoAoAlterar;
    }

    @Override
    public void setAtualizarIdSelecionadoAoAlterar(boolean atualizarIdSelecionadoAoAlterar) {
        this.atualizarIdSelecionadoAoAlterar = atualizarIdSelecionadoAoAlterar;
    }

    @Override
    public String getIdAtualizacao() {
        return idAtualizacao;
    }

    @Override
    public void setIdAtualizacao(String idAtualizacao) {
        this.idAtualizacao = idAtualizacao;
    }

    @Override
    public void addParteCaminho(String pParteCaminho) {
        caminhoCampo.addParteCaminho(pParteCaminho);
    }

    @Override
    public void defineNomeCompleto(String pCaminho, Class pClasse) {
        caminhoCampo.defineNomeCompleto(pCaminho);
    }

    @Override
    public void defineNomeCompleto(String pCaminho) {
        caminhoCampo.defineNomeCompleto(pCaminho);
    }

    @Override
    public String getCaminhoComleto() {
        return caminhoCampo.getCaminhoComleto();
    }

    @Override
    public String getCaminhoCompletoString() {
        return caminhoCampo.getCaminhoCompletoString();
    }

    @Override
    public String getCaminhoSemNomeClasse() {
        return caminhoCampo.getCaminhoSemNomeClasse();
    }

    @Override
    public Field getCampoFieldReflection() {
        return caminhoCampo.getCampoFieldReflection();
    }

    @Override
    public int getId() {
        return caminhoCampo.getId();
    }

    @Override
    public String getLabel() {
        return caminhoCampo.getLabel();
    }

    @Override
    public String getLabelDoCampo() {
        return caminhoCampo.getLabelDoCampo();
    }

    @Override
    public List<String> getPartesCaminho() {
        return caminhoCampo.getPartesCaminho();
    }

    @Override
    public Class getTipoCampo() {
        return caminhoCampo.getTipoCampo();
    }

    @Override
    public TIPO_ORIGEM_VALOR_CAMPO getTipoRegistro() {
        return caminhoCampo.getTipoRegistro();
    }

    @Override
    public List<String> getTodasListas() {
        return caminhoCampo.getTodasListas();
    }

    @Override
    public List<String> getTodosCaminhosPossiveis() {
        return caminhoCampo.getTodosCaminhosPossiveis();
    }

    @Override
    public List<String> getTodosCaminhosPossiveisSemUltimoParametro() {
        return caminhoCampo.getTodosCaminhosPossiveisSemUltimoParametro();
    }

    @Override
    public String getUltimoNome() {
        return caminhoCampo.getUltimoNome();

    }

    @Override
    public boolean isUmCampoListavel() {
        return caminhoCampo.isUmCampoListavel();
    }

    @Override
    public boolean isUmCampoSeparador() {
        return caminhoCampo.isUmCampoSeparador();
    }

    @Override
    public boolean isUmCampoVinculado() {
        return caminhoCampo.isUmCampoVinculado();
    }

    @Override
    public boolean isUmTipoComOutrasPropriedades() {
        return caminhoCampo.isUmTipoComOutrasPropriedades();
    }

    @Override
    public boolean isUmaEntidade() {
        return caminhoCampo.isUmaEntidade();
    }

    @Override
    public void setCaminhoComleto(String caminhoComleto) {
        caminhoCampo.setCaminhoComleto(caminhoComleto);
    }

    @Override
    public void setId(int pID) {
        caminhoCampo.setId(pID);
    }

    @Override
    public boolean temUmTipoComOutrasPropriedades() {
        return caminhoCampo.temUmTipoComOutrasPropriedades();
    }

    public boolean isAtualizarGrupoAoAlterar() {
        return atualizarGrupoAoAlterar;
    }

    @Override
    public void setAtualizarGrupoAoAlterar(boolean atualizarGrupoAoAlterar) {
        this.atualizarGrupoAoAlterar = atualizarGrupoAoAlterar;
    }

    @Override
    public String getLabelAlternativo() {
        if (UtilSBCoreStringValidador.isNAO_NuloNemBranco(labelAlternativo)) {
            return labelAlternativo;
        } else {
            return getLabel();
        }
    }

    @Override
    public void setLabelAlternativo(String labelAlternativo) {
        this.labelAlternativo = labelAlternativo;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    @Override
    public CaminhoCampoReflexao getCaminhoCampo() {
        return caminhoCampo;
    }

    @Override
    public boolean isUmCampoFilhoDeLista() {
        return umCampoFilhoDeLista;
    }

    @Override
    public boolean isUmCampoComLista() {
        return false;
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
    public String toString() {
        return getCaminhoCompletoString();
    }

    @Override
    public String getPrimeiroCaminhoSemNomeClasse() {
        return caminhoCampo.getPrimeiroCaminhoSemNomeClasse();
    }

    @Override
    public String getIdentificadorGrupoCampo() {
        return identificadorGrupoCampo;
    }

    @Override
    public String getCaminhoCompletoComGrupoCampo() {
        if (UtilSBCoreStringValidador.isNAO_NuloNemBranco(getIdentificadorGrupoCampo())) {
            return getCaminhoApenasClasseInicial() + "." + getIdentificadorGrupoCampo() + "." + getCaminhoSemNomeClasse();
        } else {
            return getCaminhoApenasClasseInicial() + "." + getCaminhoSemNomeClasse();
        }

    }

    @Override
    public String getCaminhoApenasClasseInicial() {
        return caminhoCampo.getCaminhoApenasClasseInicial();
    }

    /**
     *
     *
     * @return O caminho sem o nome da classe
     * @deprecated Ativo Para fins de compatibilidade com tipos de chamada, este
     * médoto deve existir apenas em CaminhoCampoDaListaExibicaoFormulario
     */
    @Deprecated
    public String getCaminhoCampoRelativoAListaPai() {
        return caminhoCampo.getCaminhoSemNomeClasse();
    }

    @Override
    public boolean isSomenteLeitura() {
        return somenteLeitura;
    }

    @Override
    public void setSomenteLeitura(boolean b) {
        somenteLeitura = b;
    }
}
