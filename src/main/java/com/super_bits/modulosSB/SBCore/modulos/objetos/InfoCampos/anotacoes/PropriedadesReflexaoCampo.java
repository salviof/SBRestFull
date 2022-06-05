/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoLisgagemOpcoesCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoAtributoDeObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.AtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FieldComSerializacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_DECLARACAO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TipoAtributoMetodosBase;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TipoAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.FabTipoConversaoEnum;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ManyToOne;

/**
 *
 * Este Bean extende a capacidade do sistema, para obter as propriedades de um
 * campo instanciado dinamico, tanto via passagem do campo configurado, quanto
 * via anotações em enuns de fabrica de campo dinâmico
 *
 *
 * @author desenvolvedor
 */
public class PropriedadesReflexaoCampo implements ItfPropriedadesReflexaoCampos, Serializable {

    /**
     *
     * @return @deprecated usar getClasseOrigemAtributo
     */
    @Override
    @Deprecated
    public Class getClasseDoObjetoDesteAtributo() {
        return getClasseOrigemAtributo();
    }

    private enum TIPO_ORIGEM_PROPRIEDADE {
        CAMPO_REFLEXAO, ATRIBUTO_REFERENCIA
    };

    private final AnotacoesCampo anotacoes;
    private final ItfAtributoObjetoSB atributoReferencia;
    private final FabTipoAtributoObjeto fabTipoAtributo;

    private final TIPO_ORIGEM_PROPRIEDADE tipoOrigem;

    public boolean anotacoesAplicadas = false;
    public final Class classeDeclaracaoAtributo;
    public final Class classeOrigemAtributo;
    private String labelPadrao;
    private boolean tipoEnum = false;
    private FabTipoConversaoEnum tipoConversao;
    private String nomeDeclaracaoAtributo;

    private void validar() {
        if (classeDeclaracaoAtributo == null) {
            throw new UnsupportedOperationException("A classe declarada do não pôde ser determinado");
        }
        if (classeOrigemAtributo == null) {
            throw new UnsupportedOperationException("A classe declarada do Objeto do Atributo não pôde ser determinado");
        }
        if (atributoReferencia == null) {
            throw new UnsupportedOperationException("O vinculado a propriedades de Reflexão não foi definido");
        }
    }

    public PropriedadesReflexaoCampo(FieldComSerializacao campoVInculado) {

        try {
            this.anotacoes = campoVInculado.gerarAnotacoes();

            this.tipoOrigem = TIPO_ORIGEM_PROPRIEDADE.CAMPO_REFLEXAO;

            classeDeclaracaoAtributo = campoVInculado.getClasseDeclaradaOuTipoEmCasoDeLista();

            fabTipoAtributo = campoVInculado.getTipoAtributo();

            Class classeOrigem = campoVInculado.getClasseOndeOCampoEstaDeclarado();
            labelPadrao = campoVInculado.getLabelPadrao();
            nomeDeclaracaoAtributo = campoVInculado.getNomeDeclaracao();
            classeOrigemAtributo = classeOrigem;
            tipoConversao = campoVInculado.getTipoConversao();
            tipoEnum = campoVInculado.isCampoEnum();

            this.atributoReferencia = new AtributoObjetoSB(this);

            //    validar();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao instanciar propriedades de reflexão de atributo, enviando campo como parametro", t);
            throw new UnsupportedOperationException("Erro iniciando Proprediadas ReflexaoCampo");
        }

    }

    public PropriedadesReflexaoCampo(ItfAtributoObjetoSB pAtributoReferencia) {
        try {
            anotacoes = null;
            if (pAtributoReferencia == null) {

            }
            this.atributoReferencia = pAtributoReferencia;
            tipoOrigem = TIPO_ORIGEM_PROPRIEDADE.ATRIBUTO_REFERENCIA;
            fabTipoAtributo = pAtributoReferencia.getFabricaTipoAtributo();
            String nomeClasseDeclarada = pAtributoReferencia.getNomeClasseAtributoDeclarado();
            if (!UtilSBCoreStringValidador.isNuloOuEmbranco(nomeClasseDeclarada)) {
                classeDeclaracaoAtributo = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(nomeClasseDeclarada);
            } else {
                classeDeclaracaoAtributo = null;
            }
            String classeOrigem = pAtributoReferencia.getNomeClasseOrigemAtributo();
            //nomeClasseDeclarada = pAtributoReferencia.getNome();
            if (!UtilSBCoreStringValidador.isNuloOuEmbranco(classeOrigem)) {
                classeOrigemAtributo = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(classeOrigem);
            } else {
                classeOrigemAtributo = null;
            }

            //  validar();
        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao instanciar propriedades de reflexão de atributo, enviando atributo ", t);
            throw new UnsupportedOperationException("Erro iniciando Proprediadas ReflexaoCampo");
        }

    }

    private void configurarTipoAtributoAnotacao(FabTipoAnotacaoCampo pTipoAnotacao, final ItfAtributoObjetoEditavel pCampo) {
        try {

            switch (tipoOrigem) {

                case CAMPO_REFLEXAO:
                    switch (pTipoAnotacao) {

                        case INFOCAMPO:
                            if (anotacoes.getInfoCampo() != null) {
                                TipoAtributoMetodosBase tipoMetodosBaseConfig = new TipoAtributoMetodosBase(anotacoes.getInfoCampo().tipo());
                                TipoAtributoObjetoSB tipoAtributoConfig = tipoMetodosBaseConfig.getRegistro();
                                InfoCampo infocampo = anotacoes.getInfoCampo();
                                if (UtilSBCoreStringValidador.isNAO_NuloNemBranco(anotacoes.getInfoCampo().Mask())) {
                                    pCampo.setMascara(anotacoes.getInfoCampo().Mask());
                                }
                                if (anotacoes.getInfoCampo().valorMaximo() != 99999) {
                                    pCampo.setValorMaximo(anotacoes.getInfoCampo().valorMaximo());
                                }

                                if (UtilSBCoreStringValidador.isNAO_NuloNemBranco(anotacoes.getInfoCampo().label())) {
                                    if (UtilSBCoreStringValidador.isNuloOuEmbranco(pCampo.getLabel())) {
                                        pCampo.setLabel(anotacoes.getInfoCampo().label());
                                    }
                                } else {
                                    String lbPadrao = tipoAtributoConfig.getLabelPadrao();
                                    if (UtilSBCoreStringValidador.isNAO_NuloNemBranco(lbPadrao)) {
                                        pCampo.setLabel(lbPadrao);
                                    }
                                }
                                if (UtilSBCoreStringValidador.isNAO_NuloNemBranco(anotacoes.getInfoCampo().descricao())) {
                                    if (UtilSBCoreStringValidador.isNuloOuEmbranco(pCampo.getDescricao())) {
                                        pCampo.setDescricao(anotacoes.getInfoCampo().descricao());
                                    }
                                }

                                if (pCampo.getFabricaTipoAtributo() == FabTipoAtributoObjeto.TIPO_PADRAO_POR_DECLARACAO || pCampo.getFabricaTipoAtributo() == null) {
                                    if (infocampo.tipo() == FabTipoAtributoObjeto.TIPO_PADRAO_POR_DECLARACAO) {
                                        FabTipoAtributoObjeto tipo = TipoAtributoMetodosBase.getTipoPadraoByClasse(classeDeclaracaoAtributo);
                                        pCampo.setFabricaTipoAtributo(tipo);
                                    } else {

                                        pCampo.setFabricaTipoAtributo(infocampo.tipo());

                                    }
                                }
                                switch (tipoOrigem) {
                                    case CAMPO_REFLEXAO:
                                        TipoAtributoMetodosBase tipoMetodosBase = new TipoAtributoMetodosBase(anotacoes.getInfoCampo().tipo());
                                        TipoAtributoObjetoSB tipoAtributo = tipoMetodosBase.getRegistro();
                                        pCampo.setValorMaximo(tipoAtributo.getValorMaximo());
                                        pCampo.setValorMinimo(tipoAtributo.getValorMinimo());

                                        if (pCampo.getValorMaximo() == 0
                                                || anotacoes.getInfoCampo().valorMaximo() != InfoCampo.MAX_PADRAO) {
                                            pCampo.setValorMaximo(anotacoes.getInfoCampo().valorMaximo());
                                        }
                                        if (pCampo.getValorMinimo() == 0 && anotacoes.getInfoCampo().valorMinimo() > 0) {
                                            pCampo.setValorMinimo(anotacoes.getInfoCampo().valorMinimo());
                                        }
                                        pCampo.setSomenteLeitura(anotacoes.getInfoCampo().somenteLeitura());

                                        pCampo.setValidacaoRegex(tipoAtributo.getValidacaoRegex());
                                        pCampo.setObrigatorio(anotacoes.getInfoCampo().obrigatorio());
                                        break;
                                    case ATRIBUTO_REFERENCIA:
                                        TipoAtributoMetodosBase tipoMetodosBaseRef = new TipoAtributoMetodosBase(anotacoes.getInfoCampo().tipo());
                                        TipoAtributoObjetoSB tipoAtributoRef = tipoMetodosBaseRef.getRegistro();
                                        pCampo.setValorMaximo(tipoAtributoRef.getValorMaximo());
                                        pCampo.setValorMinimo(tipoAtributoRef.getValorMinimo());
                                        pCampo.setValidacaoRegex(tipoAtributoRef.getValidacaoRegex());
                                        pCampo.setObrigatorio(tipoAtributoRef.isObrigatorio());
                                        break;
                                    default:
                                        throw new AssertionError(tipoOrigem.name());

                                }

                            }
                            break;
                        case NAO_NULO:
                            pCampo.setObrigatorio(true);
                            break;
                        case PASSADO:
                            break;
                        case FUTURO:
                            break;
                        case MAXIMO:
                            pCampo.setValorMaximo((int) anotacoes.getMaximo().value());
                            break;
                        case MINIMO:
                            pCampo.setValorMinimo((int) anotacoes.getMinimo().value());
                            break;
                        case TAMANHO:
                            pCampo.setValorMaximo(anotacoes.getTamanho().max());
                            break;
                        case DIGITOS:
                            pCampo.setNumeroDeCasasDecimais(anotacoes.getDigitos().fraction());
                            break;
                        case REGEX:

                            break;
                        case VERDADERO:

                            break;
                        case GRUPO_CAMPOS:
                            try {
                            pCampo.setGrupoSubCamposExibicao(UtilSBCoreReflexaoAtributoDeObjeto.getGrupoCampoPorAnotacao(anotacoes.getGrupoCampo(), getClasseDeclaracaoAtributo()));
                        } catch (Throwable t) {
                            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando Grupo de campos para exibição em " + getClasseDeclaracaoAtributo().getSimpleName() + "->" + getLabel(), t);
                        }
                        break;
                        case MUITOS_PARA_UM:
                            switch (pCampo.getFabricaTipoAtributo()) {

                                case LC_LOGRADOURO:
                                case LC_BAIRRO:
                                case LC_CIDADE:
                                case LC_LOCALIZACAO:
                                case LC_UNIDADE_FEDERATIVA:
                                    pCampo.setFabricaTipoAtributo(pCampo.getFabricaTipoAtributo());
                                    // Mantem tipo do campo como tipo Especial
                                    break;
                                default:
                                    pCampo.setFabricaTipoAtributo(FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA);

                            }
                            pCampo.setNomeClasseAtributoDeclarado(anotacoes.getMuitosParaUm().targetEntity().getSimpleName());

                            break;
                        case UM_PARA_MUITOS:
                            if (pCampo.getFabricaTipoAtributo().equals(FabTipoAtributoObjeto.TIPO_PADRAO_POR_DECLARACAO)) {
                                pCampo.setFabricaTipoAtributo(FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES);
                            }
                            pCampo.setNomeClasseAtributoDeclarado(anotacoes.getUmParaMuitos().targetEntity().getSimpleName());
                            break;

                        case MUITOS_PARA_MUITOS:
                            if (pCampo.getFabricaTipoAtributo().equals(FabTipoAtributoObjeto.TIPO_PADRAO_POR_DECLARACAO)) {
                                pCampo.setFabricaTipoAtributo(FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS);
                            }
                            break;
                        case INFOCAMPO_MODELO:
                            break;
                        case VERDADEIRO_FALSO:
                            pCampo.setTextoNegativo(anotacoes.getInfoVerdadeiroFalso().textoNegativo());
                            pCampo.setTextoPositivo(anotacoes.getInfoVerdadeiroFalso().textoPositivo());
                            pCampo.setIconeNegativo(anotacoes.getInfoVerdadeiroFalso().iconeNegativo());
                            pCampo.setIconePositivo(anotacoes.getInfoVerdadeiroFalso().iconePostivio());
                            break;
                        case COLUMN:

                            Column col = anotacoes.getColumn();
                            pCampo.setValorCampoUnico(col.unique());
                            break;
                        case INFO_CAMPO_VALOR_LOGICO:
                            pCampo.setAtualizarValorLogicoAoPersistir(anotacoes.getInfoCampoValorLogico().atualizarSempreQueSalvar());
                            pCampo.setSomenteLeitura(anotacoes.getInfoCampoValorLogico().somenteLeitura());
                            pCampo.setUmValorLogico(true);
                            break;
                        case INFO_CAMPO_LISTA_DINAMICA:
                            pCampo.setSomenteLeitura(true);
                            pCampo.setUmaListaDinamica(true);

                            break;
                        case INFO_CAMPO_VALIDADOR_LOGICO:
                            pCampo.setTemValidacaoLogica(true);
                            break;
                        case INFO_CAMPO_LOCALIZACAO:

                            pCampo.setPermitirCadastroManualEndereco(anotacoes.getInfoCampoLocalizacao().permitirCadastroCepNaoEncontrado());
                            //TODO:
                            anotacoes.getInfoCampoLocalizacao().permitirCadastroCepNaoEncontrado();
                            anotacoes.getInfoCampoLocalizacao().permitirCadstroNovoBairro();
                            anotacoes.getInfoCampoLocalizacao().permitirCadastroNovaCidade();
                            break;

                        default:
                            throw new AssertionError(FabTipoAnotacaoCampo.getTipoByClasse(pTipoAnotacao.getClass()).name());

                    }
                    break;
                case ATRIBUTO_REFERENCIA:
                    break;
                default:
                    throw new AssertionError(tipoOrigem.name());

            }
        } catch (Throwable t) {
            String textoCampo = "Campo NULO";
            if (pCampo != null) {
                textoCampo = pCampo.getClassePontoIdentificador();
            }
            System.out.println("Erro configurando valores de anotração em atributo de Objeto " + pTipoAnotacao + "->" + pCampo);

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando valores de anotração em atributo de Objeto " + pTipoAnotacao + "->" + textoCampo, t);
        }

    }

    @Override
    public boolean isConstruidoComAnotacoes() {
        return anotacoes != null;
    }

    public String getMaskara() {
        switch (tipoOrigem) {
            case CAMPO_REFLEXAO:
                if (anotacoes.getInfoCampo() != null) {
                    return anotacoes.getInfoCampo().Mask();
                } else {
                    return null;
                }

            case ATRIBUTO_REFERENCIA:
                return atributoReferencia.getMascara();
            default:
                throw new AssertionError(tipoOrigem.name());

        }
    }

    @Override
    @Deprecated
    public String getLabel() {
        try {
            switch (tipoOrigem) {
                case CAMPO_REFLEXAO:
                    return labelPadrao;

                case ATRIBUTO_REFERENCIA:
                    return atributoReferencia.getLabel();
                default:
                    throw new AssertionError(tipoOrigem.name());

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo Label da propriedade de campo", t);
            return "Erro determinando label";
        }
    }

    @Override
    public String getCaminhoListagemOpcoes() {
        try {
            switch (tipoOrigem) {
                case CAMPO_REFLEXAO:
                    if (anotacoes.getInfoCampo() != null) {
                        if (anotacoes.getInfoCampo().caminhoParaLista() != null) {
                            return anotacoes.getInfoCampo().caminhoParaLista();
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }

                case ATRIBUTO_REFERENCIA:
                    return atributoReferencia.getLabel();
                default:
                    throw new AssertionError(tipoOrigem.name());

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo subLista de escolha em " + getLabel(), t);
            return "Erro determinando label";
        }
    }

    @Override
    public void aplicarAnotacoesEmAtributo(final ItfAtributoObjetoEditavel pAtributoConfiguravel) {

        configurarTipoAtributoAnotacao(FabTipoAnotacaoCampo.INFOCAMPO, pAtributoConfiguravel);

        switch (tipoOrigem) {
            case CAMPO_REFLEXAO:
                for (FabTipoAnotacaoCampo anotacao : anotacoes.getMapaTiposAnotados().keySet()) {
                    if (anotacao != FabTipoAnotacaoCampo.INFOCAMPO) {
                        configurarTipoAtributoAnotacao(anotacao, pAtributoConfiguravel);
                    }
                }
                break;
            case ATRIBUTO_REFERENCIA:
                break;
            default:
                throw new AssertionError(tipoOrigem.name());

        }

    }

    @Override
    public ItfAtributoObjetoSB getAtributoGerado() {

        return atributoReferencia;

    }

    @Override
    public FabTipoLisgagemOpcoesCampo getTipoListagem() {

        switch (tipoOrigem) {
            case CAMPO_REFLEXAO:
                InfoCampo infocampo = anotacoes.getInfoCampo();

                if (infocampo == null) {

                    throw new UnsupportedOperationException("O campo " + getAtributoGerado().getNomeClasseOrigemAtributo() + "." + getAtributoGerado().getNomeClasseAtributoDeclarado() + " não foi anotado com InfoCampo");

                }
                if (infocampo.fabricaDeOpcoes() != void.class) {
                    return FabTipoLisgagemOpcoesCampo.LISTA_POR_FABRICA_DE_REGISTROS;
                }
                if (!infocampo.caminhoParaLista().isEmpty()) {
                    return FabTipoLisgagemOpcoesCampo.LISTAR_POR_SUBLISTA;

                }

                if (infocampo.entidadeOpcoesDisponiveis() != void.class) {
                    return FabTipoLisgagemOpcoesCampo.LISTA_POR_ENTIDADE;
                }

                if (anotacoes.getMuitosParaUm() != null || anotacoes.getUmParaMuitos() != null || anotacoes.getMuitosParaMuitos() != null) {
                    return FabTipoLisgagemOpcoesCampo.LISTA_POR_ENTIDADE;
                }

                return null;

            case ATRIBUTO_REFERENCIA:
                FabTipoLisgagemOpcoesCampo listagemCampo = null;
                String nomeClasseAtributoDeclarado = atributoReferencia.getNomeClasseAtributoDeclarado();
                if (UtilSBCoreStringValidador.isNAO_NuloNemBranco(nomeClasseAtributoDeclarado)) {

                    if (MapaObjetosProjetoAtual.getClasseDoObjetoByNome(nomeClasseAtributoDeclarado) != null) {
                        return FabTipoLisgagemOpcoesCampo.LISTA_POR_ENTIDADE;
                    }

                }
                if (atributoReferencia.getCaminhoListagem() != null && !atributoReferencia.getCaminhoListagem().isEmpty()) {
                    throw new UnsupportedOperationException("Ainda não implementado, precisa de getEntidadeVinculadaAoDadoDinamico");

                }
                return listagemCampo;
            default:
                throw new AssertionError(tipoOrigem.name());

        }

    }

    @Override
    public Class getEntidadeLista() {
        Class entidade = null;

        try {

            switch (tipoOrigem) {
                case CAMPO_REFLEXAO:
                    if (anotacoes.getInfoCampo().entidadeOpcoesDisponiveis() != void.class) {
                        entidade = anotacoes.getInfoCampo().entidadeOpcoesDisponiveis();

                    }
                    if (entidade == null && anotacoes.getUmParaMuitos() != null) {
                        if (anotacoes.getUmParaMuitos().targetEntity() != void.class) {
                            entidade = anotacoes.getMuitosParaUm().targetEntity();
                            if (entidade != Void.class) {
                                return entidade;
                            }
                        }
                    }
                    if (entidade != null) {
                        switch (fabTipoAtributo) {
                            case OBJETO_DE_UMA_LISTA:
                                ManyToOne anotacao = anotacoes.getMuitosParaUm();
                                if (anotacao != null) {
                                    entidade = anotacao.targetEntity();
                                    if (entidade != Void.class) {
                                        return entidade;
                                    }
                                }
                                break;
                        }
                    }
                    if (entidade == null) {
                        entidade = MapaObjetosProjetoAtual.getClasseDoObjetoByNomeIgnorandoErro(atributoReferencia.getNomeClasseAtributoDeclarado());
                    }
                    return entidade;

                case ATRIBUTO_REFERENCIA:
                    return MapaObjetosProjetoAtual.getClasseDoObjetoByNome(atributoReferencia.getNomeClasseAtributoDeclarado());

                default:
                    throw new AssertionError(tipoOrigem.name());

            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo fabrica de objetos offiline, tipo origem: " + tipoOrigem + " ", t);
            return null;
        }

    }

    @Override
    public boolean isUmCampoListavel() {
        try {
            return getTipoListagem() != null;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando existemncia de listagem de opções " + tipoOrigem + " ", t);
            return false;
        }
    }

    @Override
    public Class<? extends ItfFabrica> getFabricaCriacaoOpcoes() {
        try {
            switch (tipoOrigem) {
                case CAMPO_REFLEXAO:
                    return anotacoes.getInfoCampo().fabricaDeOpcoes();

                case ATRIBUTO_REFERENCIA:

                    Class classeObjeto = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(atributoReferencia.getNomeClasseAtributoDeclarado());
                    if (classeObjeto == null) {
                        throw new UnsupportedOperationException("Nenhuma classe vinculada ao campo" + atributoReferencia + " foi encontrado em" + getLabel());
                    }
                    InfoObjetoSB obj = UtilSBCoreReflexaoObjeto.getInfoClasseObjeto(classeObjeto);
                    return obj.fabricaVinculada();
                default:
                    throw new AssertionError(tipoOrigem.name());

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo fabrica de objetos offiline, tipo origem: " + tipoOrigem + " ", t);
            return null;
        }

    }

    @Override
    public Class getClasseOrigemAtributo() {
        try {

            return classeOrigemAtributo;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro obtendo Objeto de origem do Atributo " + tipoOrigem + " ", t);
            return null;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public FabTipoAtributoObjeto getFabTipoAtributo() {
        return fabTipoAtributo;
    }

    /**
     *
     * @return A Classe do Atributo, ou O tipo Em caso de Generic (ex:
     * List\<Cliente\> retornaria Cliente)
     */
    @Override
    public Class getClasseDeclaracaoAtributo() {
        return classeDeclaracaoAtributo;
    }

    @Deprecated
    public TIPO_DECLARACAO getTipoDeclaracaoEstrutura() {

        return TIPO_DECLARACAO.SIMPLES;
    }

    @Override
    public List<String> getTemplateCampos() {

        switch (tipoOrigem) {
            case CAMPO_REFLEXAO:
                InfoCampoModeloDocumento modelo = anotacoes.getInfoModeloDocumeto();
                Class classeVinculada = getClasseDeclaracaoAtributo();
                List<String> palavrasAdicionadas = new ArrayList<>();
                boolean adicionarTodasPropriedades = true;
                if (modelo != null) {
                    classeVinculada = modelo.classeModeloVinculado();
                    //TODO propriedades adicionar todos os campos em classe modelo
                    palavrasAdicionadas.addAll(Lists.newArrayList(modelo.camposCadastrados()));
                    if (classeVinculada == null) {
                        return palavrasAdicionadas;
                    }
                }
                if (adicionarTodasPropriedades) {
                    for (Field campo : classeVinculada.getDeclaredFields()) {

                        palavrasAdicionadas.add(classeVinculada.getSimpleName() + "." + campo.getName());

                    }
                }

                return palavrasAdicionadas;

            case ATRIBUTO_REFERENCIA:
                return atributoReferencia.getTemplateCamposDinamicos();

            default:
                throw new AssertionError(tipoOrigem.name());

        }

    }

    @Override
    public TIPO_PRIMITIVO getTipoPrimitivo() {
        switch (tipoOrigem) {
            case CAMPO_REFLEXAO:
                return fabTipoAtributo.getTipoPrimitivo();

            case ATRIBUTO_REFERENCIA:
                return atributoReferencia.getTipoPrimitivoDoValor();

            default:
                throw new AssertionError(tipoOrigem.name());

        }
    }

    public String getNomeDeclaracaoAtributo() {
        return nomeDeclaracaoAtributo;
    }

}
