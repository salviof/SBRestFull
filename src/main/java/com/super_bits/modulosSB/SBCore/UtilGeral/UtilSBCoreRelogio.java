/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreRelogio extends UtilSBCoreDataHora {

    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minutos;
    private int segundos;

    public UtilSBCoreRelogio(int dia, int mes, int ano, int hora, int minutos, int segundos) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    /**
     *
     * @param pDataInicial
     * @param pDataFinal
     * @return Lista de Long contendo intervalos de tempo predefinidos, primeiro
     * índice anos, segundo meses, terceiro dias, quarto horas, quinto minutos e
     * sexto segundos
     * @throws ParseException
     */
    public static List<Long> intervalTempDatas(Date pDataInicial, Date pDataFinal) throws ParseException {

        Date dataInicial = pDataInicial;
        Date dataFinal = pDataFinal;

        BigDecimal segundos = new BigDecimal((dataInicial.getTime() - dataFinal.getTime()) / 1000);
        BigDecimal minutos = segundos.divide(new BigDecimal("60"), 0, RoundingMode.HALF_DOWN);
        BigDecimal horas = minutos.divide(new BigDecimal("60"), 0, RoundingMode.HALF_DOWN);
        BigDecimal dias = horas.divide(new BigDecimal("24"), 0, RoundingMode.HALF_DOWN);
        BigDecimal meses = dias.divide(new BigDecimal("30"), 0, RoundingMode.HALF_DOWN);
        BigDecimal anos = meses.divide(new BigDecimal("365"), 0, RoundingMode.HALF_DOWN);

        Long segundosLong = segundos.longValue();
        Long minutosLong = minutos.longValue();
        Long horasLong = horas.longValue();
        Long diasLong = dias.longValue();
        Long mesesLong = meses.longValue();
        Long anosLong = anos.longValue();

        List<Long> intervalTime = new ArrayList<>();
        intervalTime.add(anosLong);
        intervalTime.add(mesesLong);
        intervalTime.add(diasLong);
        intervalTime.add(horasLong);
        intervalTime.add(minutosLong);
        intervalTime.add(segundosLong);

        return intervalTime;

    }

    public static List<Long> intervalTempDatas(Long pIntervaloTempo) {

        BigDecimal segundos = new BigDecimal(intervaloTempoSegundos(pIntervaloTempo));

        BigDecimal minutos = segundos.divide(new BigDecimal("60"), 0, RoundingMode.HALF_DOWN);
        BigDecimal horas = minutos.divide(new BigDecimal("60"), 0, RoundingMode.HALF_DOWN);
        BigDecimal dias = horas.divide(new BigDecimal("24"), 0, RoundingMode.HALF_DOWN);
        BigDecimal meses = dias.divide(new BigDecimal("30"), 0, RoundingMode.HALF_DOWN);
        BigDecimal anos = meses.divide(new BigDecimal("365"), 0, RoundingMode.HALF_DOWN);

        Long segundosLong = segundos.longValue();
        Long minutosLong = minutos.longValue();
        Long horasLong = horas.longValue();
        Long diasLong = dias.longValue();
        Long mesesLong = meses.longValue();
        Long anosLong = anos.longValue();

        List<Long> intervalos = new ArrayList<>();
        intervalos.add(anosLong);
        intervalos.add(mesesLong);
        intervalos.add(diasLong);
        intervalos.add(horasLong);
        intervalos.add(minutosLong);
        intervalos.add(segundosLong);

        return intervalos;

    }

}
