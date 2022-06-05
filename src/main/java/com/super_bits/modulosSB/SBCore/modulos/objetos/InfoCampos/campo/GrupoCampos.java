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
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public class GrupoCampos extends ItemSimples implements ItfGrupoCampos {

    @InfoCampo(label = "Nome do Grupo", tipo = FabTipoAtributoObjeto.AAA_NOME, obrigatorio = true)
    private final String nomeGrupo;
    private final String nomeIdentificadorSlug;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    protected final Map<String, ItfCampoExibicaoFormulario> campos = new HashMap<>();
    private final Map<Integer, String> ordemCampos = new HashMap();

    private ItfCampoExibicaoFormulario[] camposArray;

    public GrupoCampos(String pTituloGrupo) {

        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pTituloGrupo)) {
            throw new UnsupportedOperationException("Atenção, o título do grupo é obrigatório, e não deve ser repetir na mesma tela.");
        }
        nomeGrupo = pTituloGrupo;
        nomeIdentificadorSlug = UtilSBCoreStringFiltros.removeCaracteresEspeciais(
                UtilSBCoreStringsCammelCase.getCammelByTexto(nomeGrupo));

    }

    @Override
    public int getId() {
        if (id == 0) {
            StringBuilder codigoGrupoSTR = new StringBuilder();
            codigoGrupoSTR.append(getNomeGrupo());
            getCampos().forEach(cp -> codigoGrupoSTR.append(cp.getCaminhoComleto()));
            id = codigoGrupoSTR.toString().hashCode();
        }
        return id;
    }

    @Override
    public boolean isUmCampoComNome() {
        return nomeGrupo != null;
    }

    @Override
    public ItfCampoExibicaoFormulario getCampoInfoExibicaoByIndex(int pIndex) {
        try {
            return campos.get(ordemCampos.get(pIndex - 1));
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo campo pelo index " + pIndex + " em  " + nomeGrupo, t);
            return null;
        }
    }

    @Override
    public void adicionarCampo(ItfCampoExibicaoFormulario pCampo) {
        if (!campos.containsKey(pCampo.getCaminhoSemNomeClasse())) {

            if (!pCampo.isUmCampoFilhoDeLista()) {
                campos.put(pCampo.getCaminhoSemNomeClasse(), pCampo);
                //Utiliza sizeAtual para criar indice de ordenação
                ordemCampos.put(ordemCampos.size(), pCampo.getCaminhoSemNomeClasse());
            } else {
                // Caso seja um campo filho de um subformulário exemplo: Cliente.boletos[].valor
                try {
                    String caminhoLista = UtilSBCoreReflexaoCaminhoCampo.getStrCaminhoCampoAteCampoLista(pCampo.getCaminhoComleto());
                    CaminhoCampoListagemExibicaoFormulario campoSublista
                            = new CaminhoCampoListagemExibicaoFormulario(
                                    new CaminhoCampoExibicaoFormulario(new CaminhoCampoReflexao(caminhoLista), getNomeIdentificadorSlug()));

                    adicionarCampo(campoSublista);
                    campos.get(campoSublista.getCaminhoSemNomeClasse()).getComoCampoListagem().adicionarCampo(pCampo);

                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando lista pai de subCampo" + pCampo.getCaminhoComleto(), t);
                }

            }

        }

    }

    @Override
    public List<ItfCampoExibicaoFormulario> getCampos() {

        List<ItfCampoExibicaoFormulario> listaOrdenada = new ArrayList();
        for (int ordem : ordemCampos.keySet()) {
            listaOrdenada.add(campos.get(ordemCampos.get(ordem)));
        }

        return listaOrdenada;
    }

    @Override
    public String getNomeGrupo() {
        return nomeGrupo;
    }

    @Override
    public int getResponsividade() {
        if (campos.size() % 2 == 0) {
            return 33;
        } else {
            return 50;
        }
    }

    @Override
    public void configurarSomenteLeitura(InfoTipoAcaoFormCamposSomenteLeitura pCamposSomenteLeitura) {
        if (pCamposSomenteLeitura == null) {
            return;
        }

        try {
            int i = 0;
            for (String campo : pCamposSomenteLeitura.campos()) {
                ItfCampoExibicaoFormulario caminho = campos.get(campo);
                if (caminho != null) {
                    campos.get(campo).setSomenteLeitura(true);

                } else {
                    //    throw new UnsupportedOperationException("O campo " + campo + " não foi encontrado no grupo de campos");
                }
                i++;
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando campos que devem atualizar formulario, campo", t);
        }
    }

    @Override
    public void configurarLabelsDinamicos(InfoTipoAcaoFormCamposLabelAlternativo pLabelDinamico) {
        if (pLabelDinamico == null) {
            return;
        }

        try {
            int i = 0;

            for (String campo : pLabelDinamico.campos()) {
                ItfCampoExibicaoFormulario caminho = campos.get(campo);
                if (caminho instanceof CaminhoCampoListagemExibicaoFormulario) {
                    CaminhoCampoListagemExibicaoFormulario campoLista = (CaminhoCampoListagemExibicaoFormulario) caminho;
                    campoLista.getCamposDoSubFormulario().forEach(cp -> {

                    });
                }
                if (caminho != null) {
                    campos.get(campo).setLabelAlternativo(pLabelDinamico.labels()[i]);

                } else {
                    //    throw new UnsupportedOperationException("O campo " + campo + " não foi encontrado no grupo de campos");
                }
                i++;
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando campos que devem atualizar formulario, campo", t);
        }
    }

    @Override
    public void configurarAtualizacaoGrupo(InfoTipoAcaoFormCamposAtualizaGrupoDoCampo pInfoGrupo) {
        if (pInfoGrupo == null) {
            return;
        }

        try {

            for (String campo : pInfoGrupo.campos()) {
                ItfCampoExibicaoFormulario caminho = campos.get(campo);
                if (caminho != null) {
                    campos.get(campo).setAtualizarGrupoAoAlterar(true);

                } else {
                    //    throw new UnsupportedOperationException("O campo " + campo + " não foi encontrado no grupo de campos");
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando campos que devem atualizar formulario, campo", t);
        }
    }

    @Override
    public void configurarAtualizacaoPorID(InfoTipoAcaoFormCamposAtualizaComponentePeloId pInfoGrupo) {
        if (pInfoGrupo == null) {
            return;
        }

        try {

            for (String campo : pInfoGrupo.campos()) {
                ItfCampoExibicaoFormulario caminho = campos.get(campo);
                if (caminho != null) {
                    campos.get(campo).setAtualizarIdSelecionadoAoAlterar(true);
                    campos.get(campo).setIdAtualizacao(pInfoGrupo.nomeID());
                } else {
                    //    throw new UnsupportedOperationException("O campo " + campo + " não foi encontrado no grupo de campos");
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando campos que devem atualizar formulario, campo", t);
        }
    }

    @Override
    public void configurarAtualizacaoDeFormulario(InfoTipoAcaoFormCamposAtualizaForm pInfoGrupo) {
        if (pInfoGrupo == null) {
            return;
        }
        try {

            for (String campo : pInfoGrupo.campos()) {
                ItfCampoExibicaoFormulario caminho = campos.get(campo);
                if (caminho != null) {
                    campos.get(campo).setAtualizarFormAoAlterar(true);
                } else {
                    //    throw new UnsupportedOperationException("O campo " + campo + " não foi encontrado no grupo de campos");
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando campos que devem atualizar formulario, campo", t);
        }

    }

    @Override
    public void configurarAtualizacaoAreaEspecifica() {

    }

    /**
     *
     * @return @deprecated
     */
    @Override
    @Deprecated
    public String getIdNomeGrupoCampo() {
        return nomeIdentificadorSlug;
    }

    @Override
    public String getNomeIdentificadorSlug() {
        return nomeIdentificadorSlug;
    }

    @Override
    public ItfCampoExibicaoFormulario[] getCamposArray() {
        if (camposArray == null) {
            getCampos().toArray(camposArray);
        }
        return camposArray;
    }

}
