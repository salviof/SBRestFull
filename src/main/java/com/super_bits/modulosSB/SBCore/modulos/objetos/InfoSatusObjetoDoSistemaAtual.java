/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos;

import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfInfoStatusObjetoAplicacaoContexto;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import java.util.Date;

/**
 *
 * @author desenvolvedor
 */
public class InfoSatusObjetoDoSistemaAtual implements ItfInfoStatusObjetoAplicacaoContexto {

    private final EstruturaDeEntidade estrutura;
    private final Class classeObjeto;
    private Date dataUltimaAtualizacao;
    private int quantidadeRegistro;

    private void atualizaUltimaAtualizacao() {
        dataUltimaAtualizacao = new Date();
    }

    public InfoSatusObjetoDoSistemaAtual(EstruturaDeEntidade pEstrutura, Class pClasseObjeto) {
        estrutura = pEstrutura;
        classeObjeto = pClasseObjeto;
    }

    @Override
    public void atualizarInfomacoes() {
        SBCore.getCentralFonteDeDados().atualizarInformacoesDeObjeto(this);
    }

    @Override
    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    @Override
    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    @Override
    public int getQuantidadeRegistro() {
        return quantidadeRegistro;
    }

    @Override
    public void setQuantidadeRegistro(int quantidadeRegistro) {
        this.quantidadeRegistro = quantidadeRegistro;
    }

    @Override
    public EstruturaDeEntidade getEstrutura() {
        return estrutura;
    }

    @Override
    public Class getClasseObjeto() {
        return classeObjeto;
    }

}
