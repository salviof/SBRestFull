/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.componenteObjeto;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.view.ServicoVisualizacaoAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.FabTipoTamanhoTelas;
import java.io.File;

/**
 *
 * @author SalvioF
 */
public class NovoContainerVisualizacaoObjeto extends ContainerVisualizacaoObjeto {

    public NovoContainerVisualizacaoObjeto(Class pClasse) {
        super(false, false, false, 3, ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_PADRAO, pClasse, ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM.LABORATORIO);
    }

    public NovoContainerVisualizacaoObjeto(Class pClasse, ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM pServico) {
        super(false, false, false, 3, ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_PADRAO, pClasse, pServico);
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
        atualizarCaminhos();
    }

    public void setpTipoTela(FabTipoTamanhoTelas pTipoTela) {
        this.pTipoTela = pTipoTela;
        atualizarCaminhos();
    }

    public void setTemJavaScript(boolean temJavaScript) {
        this.temJavaScript = temJavaScript;
        atualizarCaminhos();
    }

    public void setTemCSS(boolean temCSS) {
        this.temCSS = temCSS;
        atualizarCaminhos();
    }

    public void setTemVersaoMobile(boolean temVersaoMobile) {
        this.temVersaoMobile = temVersaoMobile;
        atualizarCaminhos();
    }

    public void setNomeTipoVisualizacao(String nomeTipoVisualizacao) {
        this.nomeTipoVisualizacao = nomeTipoVisualizacao;
        atualizarCaminhos();
    }

    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
        atualizarCaminhos();
    }

    public String getEntrLocalArquivoDesktop() {

        return SBCore.getCentralDeArquivos().getEntrLocalArquivosFormulario() + getCaminhoRelativo();
    }

    public String getEntrLocalArquivoMobile() {
        return SBCore.getCentralDeArquivos().getEntrLocalArquivosFormulario() + getCaminhoRelativoMobile();
    }

    public void criarVisualizacaoContextoAtual(boolean criarVersaoMobile) {
        try {
            if (SBCore.isEmModoProducao()) {
                throw new UnsupportedOperationException("A visualização não pode ser criada em modo produção");
            }
            String caminhoArquivoXHTML = getEntrLocalArquivoDesktop();
            String caminhoMobile = getEntrLocalArquivoMobile();
            if (new File(caminhoArquivoXHTML).exists()) {
                SBCore.getCentralDeMensagens().enviarMsgAlertaAoUsuario("Esta visualização já existe, edite o arquivo" + caminhoArquivoXHTML + "com seu editor preferido");
            } else {
                UtilSBCoreArquivos.criarDiretorioParaArquivo(caminhoArquivoXHTML);
                if (!UtilSBCoreArquivos.copiarArquivos(SBCore.getCentralVisualizacao().getEndrLocalArquivoReferenciaNovoComponente(), caminhoArquivoXHTML)) {

                    SBCore.getCentralDeMensagens().enviarMsgErroAoUsuario("Erro criando arquivo modo Desenvolvimento");
                } else {
                    SBCore.getCentralDeMensagens().enviarMsgAvisoAoUsuario(caminhoArquivoXHTML);

                }
            }

            if (caminhoMobile != null) {
                if (new File(caminhoMobile).exists()) {
                    SBCore.getCentralDeMensagens().enviarMsgAlertaAoUsuario("Esta visualização Mobile já existe, edite o arquivo" + caminhoMobile + "com seu editor preferido");
                } else {
                    UtilSBCoreArquivos.criarDiretorioParaArquivo(caminhoMobile);
                    if (UtilSBCoreArquivos.copiarArquivos(SBCore.getCentralVisualizacao().getEndrLocalArquivoReferenciaNovoComponente(), caminhoArquivoXHTML)) {
                        SBCore.getCentralDeMensagens().enviarMsgAvisoAoUsuario(caminhoMobile);
                    }
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando visualização de contexto para" + getNomeEntidade(), t);
        }
    }

}
