/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep.LocalizacaoInputAssistente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfUnidadeFederativa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.TipoOrganizacaoDadosEndereco;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class CampoInstanciadoLocalizacao implements ItfAssistenteDeLocalizacao {

    private final ItfAssistenteDeLocalizacao assistente;

    @Override
    public boolean isCepsnulo() {
        return getCep() == null;
    }

    public CampoInstanciadoLocalizacao(ItfCampoInstanciado pCampo) {

        TipoOrganizacaoDadosEndereco tipo = TipoOrganizacaoDadosEndereco.getTipoOrganizacaoByCampoInstanciado(pCampo);
        ItfBeanSimples beanArmazenamentoAssitente = tipo.getBeanDeArmazenamento(pCampo);

        if (beanArmazenamentoAssitente.getAssistenteLocalizacao(pCampo, tipo) == null) {
            beanArmazenamentoAssitente.adicionarAssitenteLocalizacao(new LocalizacaoInputAssistente(pCampo, tipo));
        }

        assistente = beanArmazenamentoAssitente.getAssistenteLocalizacao(pCampo, tipo);
        if (assistente == null) {
            throw new UnsupportedOperationException("Impossível determinar o assistente de Endereço, verifique as especificações em coletivojava.com.br");
        }

    }

    /**
     *
     * Preenche o endereço de um local a partir do CEP
     *
     * @param pcep Cep Do Endereço
     *
     */
    public void preencherEndereco(String pcep) {
        System.out.println("CEP ENVIADO:" + pcep);
        assistente.atualizarEnderecoPorCep();
        //  UtilSBCoreCEP.configuraEndereco(pcep, pLocal);
    }

    @Override
    public String getCep() {
        return assistente.getCep();
    }

    @Override
    public void setCep(String pCep) {
        assistente.setCep(pCep);

    }

    @Override
    public List<ItfCidade> metodoAutoCompleteCidade(String pCidadeTXT) {

        return assistente.metodoAutoCompleteCidade(pCidadeTXT);

    }

    @Override
    public List<ItfUnidadeFederativa> metodoAutoCompleteEstado(String pCidadeTXT) {
        return assistente.metodoAutoCompleteEstado(pCidadeTXT);
    }

    @Override
    public List<ItfBairro> metodoAutoCompleteBairro(String pBairroTXT) {

        List<ItfBairro> resultadoPesquisa = new ArrayList<>();

        if (getCidade() == null) {
            SBCore.enviarMensagemUsuario("Selecione o a Cidade para listar o bairro", FabMensagens.ALERTA);
            return resultadoPesquisa;
        }

        for (ItfBairro pEstado : getCidade().getBairros()) {
            if (pEstado.getNome().toLowerCase().contains(pBairroTXT.toLowerCase())) {
                resultadoPesquisa.add(pEstado);
            }
        }
        if (resultadoPesquisa.isEmpty()) {
            if (isPermitidoCriarBairro()) {
                resultadoPesquisa.add(SBCore.getCentralDeLocalizacao().instanciarNovoBairo(pBairroTXT, getCidade()));
            }

        }
        assistente.adicionarListaOpcoesBairro(resultadoPesquisa);

        return resultadoPesquisa;

    }

    @Override
    public ItfBairro getBairro() {
        return assistente.getBairro();
    }

    @Override
    public void setBairro(ItfBairro pBairro) {

        assistente.setBairro(pBairro);
    }

    @Override
    public ItfCidade getCidade() {
        return assistente.getCidade();
    }

    @Override
    public void setCidade(ItfCidade pCidade) {
        assistente.setCidade(pCidade);

    }

    @Override
    public void setUnidadeFederativa(ItfUnidadeFederativa pUnidadeFerativa) {
        assistente.setUnidadeFederativa(pUnidadeFerativa);
    }

    @Override
    public ItfUnidadeFederativa getUnidadeFederativa() {
        return assistente.getUnidadeFederativa();
    }

    @Override
    public String getNomeLocal() {
        return assistente.getNomeLocal();
    }

    @Override
    public void setLogradouro(String pLogradouro) {

        assistente.setLogradouro(pLogradouro);

    }

    @Override
    public void setNomeLocal(String pLocal) {
        assistente.setNomeLocal(pLocal);

    }

    @Override
    public boolean isEstadoPreenchidoECidadeNula() {
        if (!isUnidadeFederativaEstadoNulo() && isCidadeNula()) {

            return true;
        } else {
            return false;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public boolean isCidadePreenchidaEBairroNulo() {
        if (!isCidadeNula() && isBairroNulo()) {

            return true;
        } else {
            return false;
        }

    }

    @Override
    public ItfLocal getLocal() {
        return assistente.getLocal();
    }

    @Override
    public boolean isBairroNulo() {
        return getBairro() == null;
    }

    @Override
    public boolean isCidadeNula() {
        return getCidade() == null;
    }

    @Override
    public boolean isUnidadeFederativaEstadoNulo() {
        return getUnidadeFederativa() == null;
    }

    @Override
    public boolean isPaisnulo() {
        return false;
    }

    @Override
    public boolean isColetarBairroAgora() {
        return assistente.isColetarBairroAgora();
    }

    @Override
    public boolean isColetarEstadoAgora() {
        return assistente.isColetarEstadoAgora();
    }

    @Override
    public boolean isColetarCidadeAgora() {
        return assistente.isColetarCidadeAgora();
    }

    @Override
    public boolean isColetarCepAgora() {
        return assistente.isColetarCepAgora();
    }

    @Override
    public boolean isColetarLogradouroAgora() {
        return assistente.isColetarLogradouroAgora();
    }

    @Override
    public boolean isColetarNomeELogradouroAgora() {
        return assistente.isColetarLogradouroAgora();
    }

    @Override
    public boolean isColetarComplementoAgora() {
        return assistente.isColetarComplementoAgora();
    }

    @Override
    public void limparEstado() {
        setUnidadeFederativa(null);

    }

    @Override
    public void limparCidade() {
        setCidade(null);
//        local.setBairro(null);
    }

    @Override
    public void limparBairro() {
        //      local.setBairro(null);
    }

    @Override
    public void limparCep() {
        setCep(null);
    }

    @Override
    public boolean atualizarEnderecoPorCep() {
        return assistente.atualizarEnderecoPorCep();
    }

    @Override
    public boolean isPodeColetarBairro() {
        return assistente.isPodeColetarBairro();
    }

    @Override
    public boolean isPodeColetarCidade() {
        return assistente.isPodeColetarCidade();
    }

    @Override
    public boolean isPodeColetarDescricaoLogradouro() {
        return assistente.isPodeColetarDescricaoLogradouro();
    }

    @Override
    public String getLogradouro() {
        return assistente.getLogradouro();
    }

    @Override
    public void setComplemento(String pComplemento) {
        assistente.setComplemento(pComplemento);
    }

    @Override
    public String getComplemento() {
        return assistente.getComplemento();
    }

    public boolean isBairroSomenteLeitura() {
        if (assistente == null) {
            return false;
        }
        return (isPodeColetarBairro() && assistente.isPesquisaSucessoBairro());
    }

    public boolean isCidadeSomenteLeitura() {
        if (assistente == null) {
            return false;
        }
        return (isPodeColetarCidade() && assistente.isPesquisaSucessoCidade());
    }

    public boolean isUnidadeFederativaSomenteLeitura() {
        if (assistente == null) {
            return false;
        }
        return assistente.isPesquisaSucessoUnidadeFederativa();

    }

    @Override
    public boolean isCepEncontradoObrigatorio() {
        return assistente.isCepEncontradoObrigatorio();

    }

    @Override
    public boolean isPesquisaSucessoBairro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPesquisaSucessoCidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPesquisaSucessoUnidadeFederativa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void adicionarListaOpcoesBairro(List<ItfBairro> bairros) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIdentificacaoMapa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
