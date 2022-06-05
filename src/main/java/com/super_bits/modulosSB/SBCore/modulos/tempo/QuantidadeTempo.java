/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.tempo;

/**
 *
 * @author salvioF
 */
public class QuantidadeTempo {

    private final TipoQuantidadeTempo tipoQuantidade;
    private long valorEmMileSegundos;
    private FabTipoQuantidadeTempo divisorMaximo = FabTipoQuantidadeTempo.ANOS;
    private long quantidade = Long.parseLong("0");
    private boolean ignorarSemana = true;
    private String nome;

    public QuantidadeTempo(Long pValorEmMileSegundos, FabTipoQuantidadeTempo pTipoQuantidade) {

        valorEmMileSegundos = pValorEmMileSegundos;
        tipoQuantidade = pTipoQuantidade.getTipoQuantidade();
        atualizarQuantidade();
        setIgnorarSemana(false);

    }

    public QuantidadeTempo(Long pValorEmMileSegundos, TipoQuantidadeTempo pTipoQuantidade, FabTipoQuantidadeTempo pBaseCalculo) {

        valorEmMileSegundos = pValorEmMileSegundos;
        divisorMaximo = pBaseCalculo;
        tipoQuantidade = pTipoQuantidade;
        atualizarQuantidade();
        setIgnorarSemana(false);

    }

    private void atualizarQuantidade() {
        if (valorEmMileSegundos == 0) {
            quantidade = 0;
        } else {
            quantidade = tipoQuantidade.getTipoInformacao().calcularQuantidade(valorEmMileSegundos, divisorMaximo, ignorarSemana);
        }
    }

    public void setValorEmMileSegundos(long valorEmMileSegundos, boolean pIgnorarSemana) {
        this.valorEmMileSegundos = valorEmMileSegundos;
        atualizarQuantidade();
    }

    /**
     * Exemplo : tipoQuantidade minutos e Divisor maximo Dias, retorna dias = 0
     * e o restante todo em minutos 60 minutos em dias = 0 dias e 60 minutos
     *
     * Exemplo: tipoQuantidade segundos e DivisorMaximo ano, se tenho 300
     * segundos, retorno 0 anos e 300 segundos. Quando o DivisorMaximo for maior
     * que o TipoQuantidade retorno o valor na mesma unidade do tipo quantidade
     *
     * @return quantidade de tempo de acordo com o tipoQuantidade e divisor
     * máximo
     */
    public long getQuantidade() {
        return quantidade;
    }

    /**
     * EXEMPLO: Anos, Dias, Minutos etc.
     *
     * @return A unidade máxima em que a quantidade pode ser dividida,
     *
     */
    public FabTipoQuantidadeTempo getDivisorMaximo() {
        return divisorMaximo;
    }

    public void setDivisorMaximo(FabTipoQuantidadeTempo divisorMaximo) {
        this.divisorMaximo = divisorMaximo;
        atualizarQuantidade();
    }

    public boolean isIgnorarSemana() {
        return ignorarSemana;
    }

    public void setIgnorarSemana(boolean ignorarSemana) {
        this.ignorarSemana = false;// ignorarSemana

    }

    /**
     *
     * @return tipo de quantidade de tempo, anos, meses, semanas, dias, horas,
     * minutos e segundos
     */
    public TipoQuantidadeTempo getTipoQuantidade() {
        return tipoQuantidade;
    }

    /**
     *
     * @return quantidade de tempo em milesegundos
     */
    public long getValorEmMileSegundos() {
        return valorEmMileSegundos;
    }

    public String getNome() {

        if (quantidade != 1) {
            return getTipoQuantidade().getNomePlural();
        } else {
            return getTipoQuantidade().getNomeSingular();
        }

    }

}
