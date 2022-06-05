/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreRelogioTest {

    public UtilSBCoreRelogioTest() {
    }

    //@Test
    public void testGetDia() {
    }

    //@Test
    public void testSetDia() {
    }

    //@Test
    public void testGetMes() {
    }

    //@Test
    public void testSetMes() {
    }

    //@Test
    public void testGetAno() {
    }

    //@Test
    public void testSetAno() {
    }

    //@Test
    public void testGetHora() {
    }

    //@Test
    public void testSetHora() {
    }

    //@Test
    public void testGetMinutos() {
    }

    //@Test
    public void testSetMinutos() {
    }

    //@Test
    public void testGetSegundos() {
    }

    //@Test
    public void testSetSegundos() {
    }

    @Test
    public void testIntervalTempDatas() {

        try {

            //   DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
            //  DateTime dataInicial = dtf.parseDateTime("07/09/2016 12:00:00");
            //    DateTime dataFinal = dataInicial.plusDays(5);
            Long anos = 0L;
            Long meses = 0L;
            Long dias = 5L;
            Long horas = 120L;
            Long minutos = 7200L;
            Long segundos = 432000L;

            //     assertEquals(anos, UtilSBCoreRelogio.intervalTempDatas(dataInicial, dataFinal).get(0));
            //      assertEquals(meses, UtilSBCoreRelogio.intervalTempDatas(dataInicial, dataFinal).get(1));
            //      assertEquals(dias, UtilSBCoreRelogio.intervalTempDatas(dataInicial, dataFinal).get(2));
            //      assertEquals(horas, UtilSBCoreRelogio.intervalTempDatas(dataInicial, dataFinal).get(3));
//            assertEquals(minutos, UtilSBCoreRelogio.intervalTempDatas(dataInicial, dataFinal).get(4));
            //   assertEquals(segundos, UtilSBCoreRelogio.intervalTempDatas(dataInicial, dataFinal).get(5));
        } catch (Throwable t) {

            t.printStackTrace();
        }
    }

}
