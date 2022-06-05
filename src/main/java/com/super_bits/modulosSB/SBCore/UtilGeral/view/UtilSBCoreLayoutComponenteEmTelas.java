/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.view;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampoExibicaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfGrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoNaoImplementado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSBEmLayout;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualBotaoAcao;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfTipoTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutComponentesEmTelaComGrupoDeAcoes;
import java.util.List;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualEmLayout;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualInputEmLayout;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualSubListaEmLayout;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreLayoutComponenteEmTelas {

    public static final CampoNaoImplementado CAMPO_NAO_INSTANCIADO = new CampoNaoImplementado();
    public static final double VALOR_UNIDADE_COLUNA_PORCENTAGEM = 100 / 12;
    public static final String NOME_COLUNA_BOTOES = "colunaBotoes";

    public static String gerarIDLayout(ItfGrupoCampos pGrupoCampo) {
        // Class objeto = getClassePorCampos(pGrupoCampo.getCampos());

        //   String idLayot = objeto.getSimpleName() + "." + pGrupoCampo.getNomeIdentificadorSlug();
        return String.valueOf(pGrupoCampo.getId());
    }

    public static Class getClassePorCampos(List<ItfCampoExibicaoFormulario> pCaminhos) {
        if (pCaminhos == null || pCaminhos.isEmpty()) {
            throw new UnsupportedOperationException("Nenhum campo foi enviado para montagem do layouy");
        }
        Class classeVinculada = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pCaminhos.get(0).getCaminhoApenasClasseInicial());
        if (classeVinculada != null) {
            return classeVinculada;
        } else {

            throw new UnsupportedOperationException("Impossível determinar o objeto vinculado a " + pCaminhos.get(0).getCaminhoApenasClasseInicial());

        }
    }

    public static LayoutComponentesEmTelaComGrupoDeAcoes gerarLayoutColunasComAcao(
            List<ItfCampoExibicaoFormulario> campos, List<ItfAcaoDoSistema> pAcoes, FabCompVisualBotaoAcao pTipoBotao,
            ItfTipoTela pTipoTEla, String pIdentificadorLayout, boolean pexpremerSeNaoCouber
    ) {
        try {

            ItfBeanSimples pItem = (ItfBeanSimples) getClassePorCampos(campos).newInstance();
            try {

                pItem.prepararNovoObjeto();
            } catch (ErroPreparandoObjeto t) {
                System.out.println("Erro preparando objeto ignorado");
            }
            LayoutComponentesEmTelaComGrupoDeAcoes layout = new LayoutComponentesEmTelaComGrupoDeAcoes(pTipoBotao, pTipoTEla, pIdentificadorLayout, pexpremerSeNaoCouber, pAcoes);
            int pesoTotal = 0;
            for (ItfCampoExibicaoFormulario campo : campos) {

                ItfCampoInstanciado pCampoInstanciado = pItem.getCampoInstanciadoByNomeOuAnotacao(campo.getCaminhoSemNomeClasse());
                if (pCampoInstanciado == null) {
                    throw new UnsupportedOperationException("Impossível obter as propriedades do campo" + campo);
                }
                ItfComponenteVisualSBEmLayout componenteEmLayout = null;
                if (!pCampoInstanciado.equals(CAMPO_NAO_INSTANCIADO)) {
                    if (!campo.isUmCampoListavel()) {
                        componenteEmLayout = new ComponenteVisualInputEmLayout(pCampoInstanciado, campo);

                    } else {
                        componenteEmLayout = new ComponenteVisualSubListaEmLayout(pCampoInstanciado, campo.getComoCampoListagem());
                    }
                    int pesoComponente = pCampoInstanciado.getFabricaTipoAtributo().getPesoLarguraEspecifico();
                    componenteEmLayout.setPesoLargura(pesoComponente);
                    pesoTotal = pesoTotal + pesoComponente;
                    layout.adicionarComponente(componenteEmLayout, campo.getCaminhoSemNomeClasse());

                }

            }
            int quantidadeBotoes = pAcoes.size();
            int minimoTamanhoBotoes = 1;
            if (quantidadeBotoes >= 5) {
                minimoTamanhoBotoes = 2;
            } else {
                minimoTamanhoBotoes = 1;
            }

            int resto = 12 - pesoTotal;
            int pesoBotoes = 1;
            switch (pTipoTEla.getColunas()) {
                case OITO:
                case NOVE:
                case DEZ:
                case ONZE:
                case DOZE:
                    pesoBotoes = (int) Math.round(quantidadeBotoes * 0.5);
                    break;
                default:
                    pesoBotoes = (int) Math.round(quantidadeBotoes * 0.1);

            }

            // situação especial, por garantia quando o peso for um, se ouver espaço e forem 2 botões aumentar o peso
            if (pesoBotoes == 1 && resto > 0) {
                if (quantidadeBotoes > 1) {
                    pesoBotoes = 2;
                }
            }

            if (pesoBotoes > resto || resto == 0) {
                if (resto < minimoTamanhoBotoes) {
                    pesoBotoes = minimoTamanhoBotoes;
                } else {
                    pesoBotoes = resto;
                }
            }

            if (pesoBotoes < minimoTamanhoBotoes) {
                pesoBotoes = minimoTamanhoBotoes;
            }

            ComponenteVisualEmLayout colunaBotoes = new ComponenteVisualEmLayout(FabCompVisualBotaoAcao.ICONE.getRegistro(), NOME_COLUNA_BOTOES, 0);
            colunaBotoes.setPesoLargura(pesoBotoes);
            layout.adicionarComponente(colunaBotoes, NOME_COLUNA_BOTOES);
            return layout;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando layout de colunas", t);
            return null;
        }
    }

    public static CampoNaoImplementado getCAMPO_NAO_INSTANCIADO() {
        return CAMPO_NAO_INSTANCIADO;
    }

    public static int getPrioridadePadraoPorTipoCampo(FabTipoAtributoObjeto pTipoATributo) {
        switch (pTipoATributo) {
            case AAA_NOME:
            case IMG_PEQUENA:
            case AAA_NOME_LONGO:
            case AAA_DESCRITIVO:
                return 0;
            case IMG_MEDIA:
            case ID:
            case CHART_VALOR:
            case DATA:
                return 1;
            case LC_LOGRADOURO:
            case CALENDARIO:
            case SITE:
            case VERDADEIRO_FALSO:
            case NOME_COMPLETO:
            case EMAIL:
            case ENUM_FABRICA:
            case CNPJ:
                return 2;
            case OBJETO_DE_UMA_LISTA:

            case SENHA:
            case TELEFONE_CELULAR:
            case MOEDA_REAL:
            case QUANTIDADE:
            case TELEFONE_FIXO_INTERNACIONAL:
            case INSCRICAO_ESTADUAL:
            case TELEFONE_FIXO_NACIONAL:
            case HTML_TEMPLATE:
            case PERCENTUAL:
            case LISTA_OBJETOS_PUBLICOS:

            case LISTA_OBJETOS_PARTICULARES:

            case INSCRIACAO_MUNICIPAL:
            case MOEDA_DOLAR:
            case HTML:
            case TELEFONE_GENERICO:
            case COR:
            case TEXTO_SIMPLES:
            case CHART_LABEL:
            case CHART_CATEGORIA:
            case DATAHORA:
            case URL:
            case CPF:

            case CODIGO_DE_BARRAS:

            case ICONE:

                return 3;
            case SENHA_SEGURANCA_MAXIMA:
            case IMG_GRANDE:
            case LATITUDE:
            case Longitude:
            case RESPONSAVEL:
            case LC_BAIRRO:

            case LC_LOCALIDADE:
            case LC_CIDADE:
            case LCCEP:
            case LC_COMPLEMENTO_E_NUMERO:
            case TIPO_PADRAO_POR_DECLARACAO:
            case LC_CAMPO_ABERTO:
            case LC_UNIDADE_FEDERATIVA:
            case REG_DATAALTERACAO:
            case REG_DATAINSERCAO:
            case REG_USUARIO_INSERCAO:
            case REG_ATIVO_INATIVO:
            case REG_USUARIO_ALTERACAO:
                return 4;

            case SEGURANCA_ATIVA:

            case ARQUIVO_DE_ENTIDADE:

            case LC_LOCALIZACAO:

            case CAMPO_SEPARADOR:

            case GRUPO_CAMPO:

            case GRUPOS_DE_CAMPO:

            case FORMULARIO_DE_ACAO:
                return 5;

            default:
                throw new AssertionError(pTipoATributo.name());

        }
    }
}
