/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsCammelCase;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormCamposAtualizaComponentePeloId;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormCamposAtualizaForm;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormCamposAtualizaGrupoDoCampo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormCamposLabelAlternativo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormCamposSomenteLeitura;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class GrupoCamposSub extends ItemSimples implements ItfGrupoCampos {

    @InfoCampo(label = "Nome do Grupo", tipo = FabTipoAtributoObjeto.AAA_NOME)
    private final String nomeGrupo;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    private final CaminhoCampoListagemExibicaoFormulario campoListaPai;
    private final String nomeIdentificadorSlug;
    private CaminhoCampoExibicaoFormulario[] camposArray;

    public GrupoCamposSub(CaminhoCampoListagemExibicaoFormulario pCampoListaPai) {

        campoListaPai = pCampoListaPai;
        nomeGrupo = "Dados " + campoListaPai.getLabel();
        nomeIdentificadorSlug = UtilSBCoreStringFiltros.removeCaracteresEspeciais(
                UtilSBCoreStringsCammelCase.getCammelByTexto(nomeGrupo));
    }

    @Override
    public void adicionarCampo(ItfCampoExibicaoFormulario pCampo) {

    }

    @Override
    public void configurarAtualizacaoAreaEspecifica() {

    }

    @Override
    public void configurarAtualizacaoDeFormulario(InfoTipoAcaoFormCamposAtualizaForm pInfoGrupo) {

    }

    @Override
    public void configurarAtualizacaoGrupo(InfoTipoAcaoFormCamposAtualizaGrupoDoCampo pInfoGrupo) {

    }

    @Override
    public void configurarLabelsDinamicos(InfoTipoAcaoFormCamposLabelAlternativo pLabelDinamico) {

    }

    @Override
    public void configurarAtualizacaoPorID(InfoTipoAcaoFormCamposAtualizaComponentePeloId pInfoGrupo) {

    }

    @Override
    public void configurarSomenteLeitura(InfoTipoAcaoFormCamposSomenteLeitura pLabelDinamico) {

    }

    @Override
    public List<ItfCampoExibicaoFormulario> getCampos() {
        return (List) campoListaPai.getCamposDoSubFormulario();
    }

    @Override
    public String getIdNomeGrupoCampo() {
        return UtilSBCoreStringsCammelCase.getCammelByTexto(nomeGrupo);
    }

    @Override
    public String getNomeGrupo() {
        return nomeGrupo;
    }

    @Override
    public int getResponsividade() {
        if (getCampos().size() % 2 == 0) {
            return 33;
        } else {
            return 50;
        }
    }

    @Override
    public boolean isUmCampoComNome() {
        return true;
    }

    @Override
    public String getNomeIdentificadorSlug() {
        return nomeIdentificadorSlug;
    }

    @Override
    public CaminhoCampoExibicaoFormulario[] getCamposArray() {
        if (camposArray == null) {
            getCampos().toArray(new ItfCampoExibicaoFormulario[getCampos().size()]);
        }
        return camposArray;
    }

    @Override
    public ItfCampoExibicaoFormulario getCampoInfoExibicaoByIndex(int pIndex) {
        try {
            return getCampos().get(pIndex);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo informações de exibição do campo pelo indice em grupo campo" + nomeGrupo, t);
            return null;
        }
    }

}
