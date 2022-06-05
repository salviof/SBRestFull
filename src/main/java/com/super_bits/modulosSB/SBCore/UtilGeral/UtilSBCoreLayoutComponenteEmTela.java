/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampoExibicaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoNaoImplementado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSBEmLayout;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualBotaoAcao;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfTipoTela;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutComponentesEmTelaComGrupoDeAcoes;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.layout.ItfLayoutComponentesEmTelaComGrupoDeAcoes;
import java.util.List;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualEmLayout;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualInputEmLayout;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualSubListaEmLayout;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreLayoutComponenteEmTelaBasico;
import static org.coletivojava.fw.utilCoreBase.UtilSBCoreLayoutComponenteEmTelaBasico.NOME_COLUNA_BOTOES;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreLayoutComponenteEmTela extends UtilSBCoreLayoutComponenteEmTelaBasico {

    public static final CampoNaoImplementado CAMPO_NAO_INSTANCIADO = new CampoNaoImplementado();

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

    public static ItfLayoutComponentesEmTelaComGrupoDeAcoes gerarLayoutColunasComAcao(
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
                        int pesoComponente = pCampoInstanciado.getFabricaTipoAtributo().getPesoLarguraEspecifico();
                        componenteEmLayout.setPesoLargura(pesoComponente);
                        pesoTotal = pesoTotal + pesoComponente;
                    } else {
                        componenteEmLayout = new ComponenteVisualSubListaEmLayout(pCampoInstanciado, campo.getComoCampoListagem());
                    }
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

}
