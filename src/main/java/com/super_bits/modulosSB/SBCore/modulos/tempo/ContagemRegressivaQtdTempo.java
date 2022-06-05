/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.tempo;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class ContagemRegressivaQtdTempo {

    private List<QuantidadeTempo> quantidadeTempo;

    private FabTipoQuantidadeTempo baseCalculo;
    private int quantidadeInformacao = 3;
    private boolean dividirDiasEmSemanas = false;
    private long valorQuantidade;
    private long quantidadeTotalEmSegundos;

    private FabTipoQuantidadeTempo tipoQuantidadeInicioDaContagem() {
        //TODO
        // SE for uma quantidade suficiente para contabilizar um ano, retorna ano, um mes, retorna mes, e assim por diante...
        // Até chegar no tipo de quantidade maior de acordo com a quantidade de informações...
        // Retorna de onde a contagem deve começar, baseado na quantidade de tempo base de Calculo e quantidade de informação.
        // sendo que: a quantidade de informação deve ser respeitada, mesmo que o valor do tempo seja zero,
        // -> ou seja se Quantidadeinformação for 3, mesmo que os milisegundos correspondam a 0 dias, o inicio da contagem deve ser O dia, pois Segundos Minutos e Dias devem ser Exibidos na tela.
        // Já a baseCalculo é o limite da informação.... o ideal é que se a base for hora, e a quantidade de informção for 3, retorne erro... pois exibir faltam 0 meses e 45 dias não faz sentido
        // e por fim a quantidadeTempo influi em quando a informação vai começar, exemplo: se ouver quantidade suficiente para anos com quantidade de informação 3, mostra-se ano,mes, e dia, e se a quantidade ultrapassar apenas meses, mostra-se mes, dia, e horas...
        return FabTipoQuantidadeTempo.MESES;
    }

    private FabTipoQuantidadeTempo tipoQuantidadeFimDaContagem() {
        //TODO
        // SE for uma quantidade suficiente para contabilizar um ano, retorna ano, um mes, retorna mes, e assim por diante...
        // Até chegar no tipo de quantidade maior de acordo com a quantidade de informações...
        // Retorna de onde a contagem deve começar, baseado na quantidade de tempo base de Calculo e quantidade de informação.
        // sendo que: a quantidade de informação deve ser respeitada, mesmo que o valor do tempo seja zero,
        // -> ou seja se Quantidadeinformação for 3, mesmo que os milisegundos correspondam a 0 dias, o inicio da contagem deve ser O dia, pois Segundos Minutos e Dias devem ser Exibidos na tela.
        // Já a baseCalculo é o limite da informação.... o ideal é que se a base for hora, e a quantidade de informção for 3, retorne erro... pois exibir faltam 0 meses e 45 dias não faz sentido
        // e por fim a quantidadeTempo influi em quando a informação vai começar, exemplo: se ouver quantidade suficiente para anos com quantidade de informação 3, mostra-se ano,mes, e dia, e se a quantidade ultrapassar apenas meses, mostra-se mes, dia, e horas...
        return FabTipoQuantidadeTempo.HORAS;
    }

    private void iniciarContagem() {
        //Metodo que inicia o timer de contagem regressiva. (o timer deve ajir de acordo com tipoQuantidadeFImDaContagem
        //se o fim da contagem tiver em dias, o timer deve alterar de 24 em 24 horas, se o fim for em segundos deve ser de 1000 em 1000 mileSegundos
    }

    public ContagemRegressivaQtdTempo() {
    }

    /**
     * Inicia uma contagem regressiva a partir do momento Atual
     *
     * @param pDataFinal
     */
    public ContagemRegressivaQtdTempo(Date pDataFinal) {
        quantidadeTotalEmSegundos = UtilSBCoreDataHora.intervaloTempoSegundos(new Date(), pDataFinal);
    }

    public ContagemRegressivaQtdTempo(Long pQuantidadeTotal) {
        valorQuantidade = pQuantidadeTotal;
    }

    /**
     *
     * @param pDataInicial
     * @param pDatafinal
     */
    public ContagemRegressivaQtdTempo(Date pDataInicial, Date pDatafinal) {

        iniciarContagem();
    }

    public ContagemRegressivaQtdTempo(Integer[] pValores) {

    }

    public List<QuantidadeTempo> getQuantidadesTempo() {

        /// Valor temporario para testes de visualização
        List<QuantidadeTempo> quantidade = new ArrayList<>();

        quantidade.add(new QuantidadeTempo(Long.parseLong("0"), FabTipoQuantidadeTempo.MESES));
        quantidade.add(new QuantidadeTempo(Long.parseLong("0"), FabTipoQuantidadeTempo.DIAS));
        quantidade.add(new QuantidadeTempo(Long.parseLong("0"), FabTipoQuantidadeTempo.HORAS));
        return quantidade;
    }

    public void setQuantidadeTempo(List<QuantidadeTempo> quantidadeTempo) {
        this.quantidadeTempo = quantidadeTempo;
    }

    public FabTipoQuantidadeTempo getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(FabTipoQuantidadeTempo baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public int getQuantidadeInformacao() {
        return quantidadeInformacao;
    }

    public void setQuantidadeInformacao(int quantidadeInformacao) {
        this.quantidadeInformacao = quantidadeInformacao;
    }

    public boolean isDividirDiasEmSemanas() {
        return dividirDiasEmSemanas;
    }

    public void setDividirDiasEmSemanas(boolean dividirDiasEmSemanas) {
        this.dividirDiasEmSemanas = dividirDiasEmSemanas;
    }

    public long getQuantidadeTotalSegudos() {
        return quantidadeTotalEmSegundos;
    }

}
