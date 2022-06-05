/*
 *  Desenvolvido pela equipe coletivojava.com.br

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.listas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;
import org.coletivojava.fw.api.objetoNativo.controller.acao.AcaoBotaoNaoRequisitado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfGrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.listagemItem.ItflistagemItemEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author Salvio Furbino
 */
public class B_ListaItemEditavel implements ItflistagemItemEditavel<ItfBeanSimples> {

    protected final ItfCampoInstanciado campoInstanciado;
    private int indiceItemSelecionado;
    private ItfGrupoCampos grupoCampo;

    public B_ListaItemEditavel(ItfCampoInstanciado pCampoInstanciado) {
        campoInstanciado = pCampoInstanciado;
        grupoCampo = (GrupoCampos) campoInstanciado.getPropriedadesRefexao().getAtributoGerado().getGrupoSubCamposExibicao();
    }

    @Override
    public void adicionarItem() {
        try {
            ItfBeanSimples novoObjeto = (ItfBeanSimples) campoInstanciado.getPropriedadesRefexao().getClasseDeclaracaoAtributo().newInstance();

            Class classeObjetoPrincipal = campoInstanciado.getPropriedadesRefexao().getClasseOrigemAtributo();
            InfoPreparacaoObjeto preparadorObjeto = UtilSBCoreReflexaoObjeto.getInfoPreparacaoObjeto(novoObjeto.getClass());

            Class[] classeSConstructor = null;
            if (preparadorObjeto != null) {
                classeSConstructor = preparadorObjeto.classesPrConstructorPrincipal();
            }
            boolean objetoPaiCompativelcomClasseConstructorPrincipal = false;
            try {
                Object objetoTeste = preparadorObjeto.classesPrConstructorPrincipal()[0].newInstance();
                if (objetoTeste.getClass().isInstance(campoInstanciado.getObjetoDoAtributo())) {
                    objetoPaiCompativelcomClasseConstructorPrincipal = true;
                }

            } catch (Throwable t) {
                objetoPaiCompativelcomClasseConstructorPrincipal = false;
            }

            if (classeSConstructor != null && classeSConstructor.length == 1) {
                if (preparadorObjeto.classesPrConstructorPrincipal()[0].equals(classeObjetoPrincipal) || objetoPaiCompativelcomClasseConstructorPrincipal) {
                    novoObjeto.prepararNovoObjeto(campoInstanciado.getObjetoDoAtributo());
                } else {
                    for (ItfCaminhoCampo caminho : novoObjeto.getEntidadesVinculadas()) {
                        if (caminho.isUmaEntidade()) {
                            if (caminho.getCampoFieldReflection().getType().getSimpleName().equals(campoInstanciado.getPropriedadesRefexao().getClasseOrigemAtributo().getSimpleName())) {
                                novoObjeto.prepararNovoObjeto(campoInstanciado.getObjetoDoAtributo());
                            }
                        }
                    }

                }
            } else {

                novoObjeto.prepararNovoObjeto();
                for (ItfCaminhoCampo caminho : novoObjeto.getEntidadesVinculadas()) {
                    if (caminho.isUmaEntidade()) {
                        if (caminho.getCampoFieldReflection().getType().getSimpleName().equals(campoInstanciado.getPropriedadesRefexao().getClasseOrigemAtributo().getSimpleName())) {
                            novoObjeto.getCampoInstanciadoByNomeOuAnotacao(caminho.getCaminhoSemNomeClasse()).setValor(campoInstanciado.getObjetoDoAtributo());
                        }
                    }
                }
            }

            ((List) campoInstanciado.getValor()).add(novoObjeto);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Adicionando item dinamicamente", t);
        }
    }

    @Override
    public void removerItem() {
        try {
            ((List) campoInstanciado.getValor()).remove(0);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Removendo Item da lista", t);
        }
    }

    @Override
    public void removerItem(int pId) {
        ((List) campoInstanciado.getValor()).remove(pId);
    }

    @Override
    public void removerItemSelecionadoPeloIndice() {
        try {
            ((List) campoInstanciado.getValor()).remove(indiceItemSelecionado);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Removendo Item da lista", t);
        }
    }

    @Override
    public void removerItem(ItfBeanSimples pItem) {
        try {
            int idremover = pItem.getId();
            List lista = (List) campoInstanciado.getValor();
            int indiceRemocao = 0;

            for (int i = 0; i < lista.size(); i++) {
                ItfBeanSimples item = (ItfBeanSimples) lista.get(i);
                if (item.getId() == idremover) {
                    indiceRemocao = i;
                    break;
                }
            }

            lista.remove(indiceRemocao);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao remover o item", t);
        }
    }

    @Override
    public List<ItfBeanSimples> getListaObjetosSelecionados() {
        try {
            return (List<ItfBeanSimples>) campoInstanciado.getValor();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obter uma lista de beanSimples no campo Instanciado", t);
            return null;
        }
    }

    @Override
    public void setListaObjetosSelecionados(List<ItfBeanSimples> pLista) {
        campoInstanciado.setValor(pLista);
    }

    @Override
    public int getIndiceItemSelecionado() {
        return indiceItemSelecionado;
    }

    @Override
    public void setIndiceItemSelecionado(int pIdItem) {
        indiceItemSelecionado = pIdItem;
    }

    @Override
    public ItfAcaoDoSistema getAcaoAdicionarItem() {
        ItfAcaoDoSistema acao = new AcaoBotaoNaoRequisitado();
        try {

            Field campo = UtilSBCoreReflexaoCaminhoCampo.getFieldByCaminho(new CaminhoCampoReflexao(campoInstanciado.getObjetoDoAtributo().getClass().getSimpleName() + "." + campoInstanciado.getNomeCompostoIdentificador()));
            InfoObjetoSB infoObj = UtilSBCoreReflexaoObjeto.getInfoClasseObjeto(UtilSBCoreReflexao.getClasseGenericaDaClasseDoCampo(campo));
            if (!infoObj.generoFeminino()) {
                acao.setNomeAcao("Incluir " + infoObj.tags()[0]);
            } else {
                acao.setNomeAcao("Incluir " + infoObj.tags()[0]);
            }

        } catch (Throwable t) {
            acao.setNomeAcao("Incluir" + campoInstanciado.getNomeDoObjeto());
            acao.setIconeAcao(FabIconeFontAwesome.REG_NOVO.getIcone().getTagHtml());
        }

        return acao;
    }

    @Override
    public ItfAcaoDoSistema getAcaoRemoverItem() {
        ItfAcaoDoSistema acao = new AcaoBotaoNaoRequisitado();
        acao.setNomeAcao("Remover " + campoInstanciado.getNomeDoObjeto());
        acao.setIconeAcao(FabIconeFontAwesome.REG_REMOVER.getIcone().getTagHtml());
        return acao;
    }

}
