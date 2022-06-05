/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.fonteDados;

import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ItfCentralAtributosDeObjetos;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringComparador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.FabricaObjetosRegistrados;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.UtilSBCoreReflexaoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoLisgagemOpcoesCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.ItfPropriedadesReflexaoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.listas.B_ListaItemEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.listagemItem.ItfListagemItensSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.listagemItem.ItflistagemItemEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.offiline.B_ObjetoDeUmaListaOffilinecpInst;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.offiline.B_listaComOrigemOffiline;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorMultiplo.ItfselecaoListaComOrigem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorUnicoObjeto.ItfSelecaoObjetoDeUmaLista;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfInfoStatusObjetoAplicacaoContexto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class CentralAtributosDeObjetosSemPersistencia implements ItfCentralAtributosDeObjetos {

    @Override
    public List getListaOpcoesCampoInstanciado(ItfCampoInstanciado pCampoInstanciado) {
        try {

            ItfPropriedadesReflexaoCampos propAtributoReflexao = pCampoInstanciado.getPropriedadesRefexao();
            FabTipoLisgagemOpcoesCampo tipoLista = propAtributoReflexao.getTipoListagem();
            switch (tipoLista) {

                case LISTAR_POR_SUBLISTA:
                    return (List) pCampoInstanciado.getObjetoDoAtributo().getCPinst(propAtributoReflexao.getCaminhoListagemOpcoes()).getValor();

                default:
                    return getListaOpcoesCampo(propAtributoReflexao);

            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo lista de atributos para o campo " + pCampoInstanciado, t);
            return new ArrayList<>();
        }

    }

    @Override
    public List getListaOpcoesCampo(ItfPropriedadesReflexaoCampos pPropriedades) {
        if (pPropriedades == null) {
            throw new UnsupportedOperationException("Tentativa de chamada de lista de opções do campo de atributo, enviando propriedae de campoAtributo nulo");
        }
        FabTipoLisgagemOpcoesCampo tipoLista = pPropriedades.getTipoListagem();
        try {

            if (tipoLista == null) {
                return new ArrayList();
            }
            switch (tipoLista) {
                case LISTA_POR_FABRICA_DE_REGISTROS:

                    return UtilSBCoreReflexaoFabrica.getListaTodosRegistrosDaFabrica(pPropriedades.getFabricaCriacaoOpcoes());

                case LISTAR_POR_SUBLISTA:
                    throw new UnsupportedOperationException("Para listar a partir de sublista, é nescessário utilizar um campo instanciado");

                case LISTA_POR_ENTIDADE:

                    throw new UnsupportedClassVersionError("A central configura não suporta persistencia de objetos");

                case LISTA_POR_LISTAGEM_DE_ENTIDADE:
                    throw new UnsupportedClassVersionError("Este tipo de Objeto não está configurado para utilizar persistencia de objetos");

                default:
                    throw new AssertionError(tipoLista.name());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo lista de atributos para o campo " + pPropriedades.getAtributoGerado() + " utilizando a estrategia" + tipoLista, t);
            return new ArrayList<>();
        }
    }

    @Override
    public Class<? extends ItfselecaoListaComOrigem> getClasseSelecaoItens() {
        return B_listaComOrigemOffiline.class;
    }

    @Override
    public Class<? extends ItfSelecaoObjetoDeUmaLista> getClasseSelecaoItem() {
        return B_ObjetoDeUmaListaOffilinecpInst.class;
    }

    @Override
    public List getListaOpcoesCampoInstanciado(ItfCampoInstanciado pCampoInstanciado, String pFiltro, String... pCampos
    ) {

        List resultado = new ArrayList();

        //pCampoInstanciado.getComoCampoComListaDeOpcoes().getSeletor().getOrigem()
        // pCampoInstanciado.getComoCampoComListaDeOpcoes().getSeletor().getOrigem().addAll(getListaOpcoesCampoInstanciado(pCampoInstanciado));
        if (!pCampoInstanciado.getListaDeOpcoes().isEmpty()) {
            pCampoInstanciado.getListaDeOpcoes().stream().filter((item) -> (item != null)).forEachOrdered((item) -> {
                String nome = item.getNome();
                if (nome != null && UtilSBCoreStringValidador.isNAO_NuloNemBranco(pFiltro)) {
                    if (UtilSBCoreStringComparador.isParecido(item.getNome(), pFiltro)) {
                        resultado.add(item);
                    }
                }
            });
        }

        return resultado;
    }

    @Override
    public void atualizarInformacoesDeObjeto(ItfInfoStatusObjetoAplicacaoContexto pObjeto
    ) {

        try {
            InfoObjetoSB infoObj = UtilSBCoreReflexaoObjeto.getInfoClasseObjeto(pObjeto.getClasseObjeto());
            if (!infoObj.fabricaVinculada().equals(FabricaObjetosRegistrados.class)) {
                pObjeto.setQuantidadeRegistro(infoObj.fabricaVinculada().getEnumConstants().length);
            }
            pObjeto.setQuantidadeRegistro(0);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro atualizando informacoes do objeto" + pObjeto.getClasseObjeto(), t);
        }
    }

    @Override
    public Class<? extends ItfListagemItensSomenteLeitura> getClasseListaRegistrosSomenteLeitura() {
        return B_ListaItemEditavel.class;
    }

    @Override
    public Class<? extends ItflistagemItemEditavel> getClasseListaRegistrosEditavel() {
        return B_ListaItemEditavel.class;
    }

    @Override
    public int getNumeroMaximoRegistros(ItfCampoInstanciado pCampoInstanciado) {

        return getListaOpcoesCampoInstanciado(pCampoInstanciado).size();
    }

}
