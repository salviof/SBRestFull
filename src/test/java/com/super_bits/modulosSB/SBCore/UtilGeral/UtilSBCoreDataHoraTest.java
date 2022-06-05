/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.tempo.FabTipoQuantidadeTempo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreDataHoraTest {

    public final static Long HORASDIA = 24L;
    public final static Long MESESANO = 12L;
    public final static Long MINUTOSHORA = 60L;
    public final static Long SEGUNDOSMINUTO = 60L;
    public final static Long QTDMILISEGUNDOSSEGUNDO = 1000L;
    public final static Long DIASMES = 30L;

    public UtilSBCoreDataHoraTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    //@Test
    public void testGetDataSTRFormatoUsuario() {
    }

    //@Test
    public void testSegundosEntre() {
    }

    //@Test
    public void testMileSegundosEntre() {
    }

    //@Test
    public void testGetAgoraString_UtilSBCoreDataHoraFORMATO_TEMPO() {
    }

    //@Test
    public void testGetAgoraString_String() {
    }

    //@Test
    public void testGetDataHoraString_Date_String() {
    }

    //@Test
    public void testGetDataHoraString_Date_UtilSBCoreDataHoraFORMATO_TEMPO() {
    }

    //@Test
    public void testGethoje() {
    }

    //@Test
    public void testIntervaloTempoMileSegundos() { // não fazer nenhuma multiplicação por mil 60 60 e 24 simplesmente o .getTime()
    }

    @Test
    public void testIntervaloTempoSegundos() {

        try {
            Date dataInicial;
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataInicial = ds.parse("07/09/2016 12:00:00");
            Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 5); // 5 dias * 24 horas * 60 minutos(1 hora) * 60 segundos (1 minuto)

            long diferencaSegundos;

            diferencaSegundos = (dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO);
            assertEquals(432000, diferencaSegundos);

            diferencaSegundos = UtilSBCoreDataHora.intervaloTempoSegundos(dataInicial, dataFinal);
            assertEquals(432000, diferencaSegundos);

        } catch (Throwable t) {
            t.getCause();
        }

    }

    @Test
    public void testIntervaloTempoMinutos() {

        try {
            Date dataInicial;
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataInicial = ds.parse("07/09/2016 12:00:00");
            Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 5); // 5 dias * 24 horas * 60 minutos(1 hora)

            long diferencaMinutos;

            diferencaMinutos = (dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO * MINUTOSHORA);
            assertEquals(7200, diferencaMinutos);

            diferencaMinutos = UtilSBCoreDataHora.intervaloTempoMinutos(dataInicial, dataFinal);
            assertEquals(7200, diferencaMinutos);

        } catch (Throwable t) {
            t.getCause();
        }

    }

    @Test
    public void testIntervaloTempoHoras() {

        try {
            Date dataInicial;
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataInicial = ds.parse("07/09/2016 12:00:00");
            Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 5); // 5 dias * 24 horas

            long diferencaHoras;

            diferencaHoras = (dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO * MINUTOSHORA);
            assertEquals(120, diferencaHoras);

            diferencaHoras = UtilSBCoreDataHora.intervaloTempoHoras(dataInicial, dataFinal);
            assertEquals(120, diferencaHoras);

        } catch (Throwable t) {
            t.getCause();
        }

    }

    @Test
    public void testIntervaloTempoDias() {

        try {
            Date dataInicial;
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataInicial = ds.parse("07/09/2016 12:00:00");
            Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 5);

            long diferencaDias;

            diferencaDias = (dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO * MINUTOSHORA * HORASDIA);
            assertEquals(5, diferencaDias);

            diferencaDias = UtilSBCoreDataHora.intervaloTempoDias(dataInicial, dataFinal);
            assertEquals(5, diferencaDias);

        } catch (Throwable t) {
            t.getCause();
        }

    }

    @Test
    public void testIntervaloTempoMeses() {

        try {
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dataInicial = ds.parse("07/09/2016 12:00:00");

            dataInicial = new Date(dataInicial.getTime());
            Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 30);

            long diferencaMeses = (dataFinal.getTime() - dataInicial.getTime()) / (HORASDIA * MINUTOSHORA * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO) / DIASMES;
            assertEquals(1, diferencaMeses);

            diferencaMeses = UtilSBCoreDataHora.intervaloTempoMeses(dataInicial, dataFinal);
            assertEquals(1, diferencaMeses);

        } catch (Throwable t) {
            t.getCause();
        }

    }

    @Test
    public void testIntervaloTempoAnos() {

        try {
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dataInicial = ds.parse("07/09/2016 12:00:00");
            Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 365);

            Long diferencaEsperada = 1L;

            Long diferencaAnos = ((dataFinal.getTime() - dataInicial.getTime()) / (HORASDIA * MINUTOSHORA * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO) / DIASMES) / MESESANO;
            assertEquals(diferencaEsperada, diferencaAnos);

            diferencaAnos = (long) UtilSBCoreDataHora.intervaloTempoAnos(dataInicial, dataFinal);
            assertEquals(diferencaEsperada, diferencaAnos);

        } catch (ParseException | NumberFormatException t) {
            t.getCause();
        }

    }

    //@Test
    public void testDecrementaMinutos() {
    }

    //@Test
    public void testDecrementarDias() {
    }

    //@Test
    public void testIncrementaDias() {
    }

    //@Test
    public void testIncrementaSegundos() {
    }

    //@Test
    public void testIncrementaHoras() {
    }

    //@Test
    public void testIncrementaMinutos() {
    }

    //@Test
    public void testIncrementaDiaHorasMinutosSegundo() {
    }

    //@Test
    public void testIncrementaDiaHorasMinutosSegundosDiasUteis() {
    }

    //@Test
    public void testIntervaloTempoDiasHorasMinutosSegundos() {
    }

    //@Test
    public void testIntervaloTempoSegundos_Date_Date() {
    }

    ///@Test
    public void testIntervaloTempoSegundos_Long() {
    }

    //@Test
    public void testIntervarlTempoMinutos() {
    }

    //@Test
    public void testIntervaloTempoHoras_Date_Date() {
    }

    //@Test
    public void testIntervaloTempoHoras_Long() {
    }

    //@Test
    public void testIntervaloTempoDias_Date_Date() {
    }

    //@Test
    public void testIntervaloTempoDias_Long() {
    }

    public void testIntervaloTempoSemanas_Date_Date() throws ParseException {

        SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dataInicial = ds.parse("07/09/2016 12:00:00");
        Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 365);

        Long diferencaEsperada = 52L;
        Long diferencaSemanas = ((dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO * MINUTOSHORA * HORASDIA * 24));
        assertEquals(diferencaEsperada, diferencaSemanas);

        diferencaSemanas = UtilSBCoreDataHora.intervaloTempoSemanas(dataInicial, dataFinal);
        assertEquals(diferencaEsperada, diferencaSemanas);

    }

    @Test
    public void testIsDiaIgualOuSuperior() {
        try {
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dataInicial = ds.parse("22/01/2020 12:00:00");

            Date dataIgual = ds.parse("22/01/2020 12:00:00");
            Date dataIgual2 = ds.parse("22/01/2020 10:00:00");
            Date dataSuperior = ds.parse("23/01/2020 12:00:00");
            Date dataSuperior3 = ds.parse("22/02/2020 12:00:00");
            Date dataSuperior2 = new Date();
            Date dataInferior = ds.parse("21/01/2020 12:00:00");
            assertTrue("Falha verificando dia igual1", UtilSBCoreDataHora.isDiaIgualOuSuperior(dataInicial, dataIgual));
            assertTrue("Falha verificando dia igual2", UtilSBCoreDataHora.isDiaIgualOuSuperior(dataInicial, dataIgual2));
            assertTrue("Falha verificando dia superior 1", UtilSBCoreDataHora.isDiaIgualOuSuperior(dataInicial, dataSuperior));
            assertTrue("Falha verificando dia superior 2", UtilSBCoreDataHora.isDiaIgualOuSuperior(dataInicial, dataSuperior2));
            assertTrue("Falha verificando dia superior 3", UtilSBCoreDataHora.isDiaIgualOuSuperior(dataInicial, dataSuperior3));
            assertFalse("Falha verificando dia inferior 2", UtilSBCoreDataHora.isDiaIgualOuSuperior(dataInicial, dataInferior));
        } catch (ParseException ex) {
            Logger.getLogger(UtilSBCoreDataHoraTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Falhou testando data igual ou superior");
        }
    }

    //@Test
    public void testIntervaloTempoSemanas_Long() {
    }

    //@Test
    public void testIntervaloTempoMeses_Date_Date() {
    }

    //@Test
    public void testIntervaloTempoMeses_Long() {
    }

    //@Test
    public void testIntervaloTempoAnos_Date_Date() {
    }

    //@Test
    public void testIntervaloTempoAnos_Long() {
    }

    //@Test
    public void testIntervaloTempoBaseAnos() {
    }

    //@Test
    public void testIntervaloTempoDiasHorasMinitosSegundos() {
    }

    //@Test
    public void testInterTempContRegSegundos() {
    }

    /**
     * Test of horasEntre method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testHorasEntre() {
        System.out.println("horasEntre");
        Date pDatainicial = null;
        Date pDatafinal = null;
        double expResult = 0.0;
        double result = UtilSBCoreDataHora.horasEntre(pDatainicial, pDatafinal);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmSegundos method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testQuantidadeTempoEmSegundos() {
        System.out.println("quantidadeTempoEmSegundos");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        Long expResult = null;
        Long result = UtilSBCoreDataHora.quantidadeTempoEmSegundos(valor, divisorMaximo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmMinutos method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testQuantidadeTempoEmMinutos() {
        System.out.println("quantidadeTempoEmMinutos");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        Long expResult = null;
        Long result = UtilSBCoreDataHora.quantidadeTempoEmMinutos(valor, divisorMaximo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmHoras method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testQuantidadeTempoEmHoras() {
        System.out.println("quantidadeTempoEmHoras");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        Long expResult = null;
        Long result = UtilSBCoreDataHora.quantidadeTempoEmHoras(valor, divisorMaximo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmDias method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testQuantidadeTempoEmDias() {
        System.out.println("quantidadeTempoEmDias");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        boolean contabilizarSemanas = false;
        Long expResult = null;
        Long result = UtilSBCoreDataHora.quantidadeTempoEmDias(valor, divisorMaximo, contabilizarSemanas);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmMeses method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testQuantidadeTempoEmMeses() {
        System.out.println("quantidadeTempoEmMeses");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        Long expResult = null;
        Long result = UtilSBCoreDataHora.quantidadeTempoEmMeses(valor, divisorMaximo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmSemanas method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testQuantidadeTempoEmSemanas() {
        System.out.println("quantidadeTempoEmSemanas");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        Long expResult = null;
        Long result = UtilSBCoreDataHora.quantidadeTempoEmSemanas(valor, divisorMaximo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmAnos method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testQuantidadeTempoEmAnos() {
        System.out.println("quantidadeTempoEmAnos");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        Long expResult = null;
        Long result = UtilSBCoreDataHora.quantidadeTempoEmAnos(valor, divisorMaximo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of incrementaMes method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testIncrementaMes() {
        System.out.println("incrementaMes");
        Date pData = null;
        int pNumeroMeses = 0;
        Date expResult = null;
        Date result = UtilSBCoreDataHora.incrementaMes(pData, pNumeroMeses);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gerarStringDiaMesAnoAtual method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testGerarStringDiaMesAnoAtual() {
        System.out.println("gerarStringDiaMesAnoAtual");
        String expResult = "";
        String result = UtilSBCoreDataHora.gerarStringDiaMesAnoAtual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gerarStringDiaMesAnoAtualFormatada method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testGerarStringDiaMesAnoAtualFormatada() {
        System.out.println("gerarStringDiaMesAnoAtualFormatada");
        String expResult = "";
        String result = UtilSBCoreDataHora.gerarStringDiaMesAnoAtualFormatada();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gerarInteiroDiaMesAnoAtual method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testGerarInteiroDiaMesAnoAtual() {
        System.out.println("gerarInteiroDiaMesAnoAtual");
        int expResult = 0;
        int result = UtilSBCoreDataHora.gerarInteiroDiaMesAnoAtual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gerarInteiroDiaMesAnoDataInformada method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testGerarInteiroDiaMesAnoDataInformada() {
        System.out.println("gerarInteiroDiaMesAnoDataInformada");
        Date pData = null;
        int expResult = 0;
        int result = UtilSBCoreDataHora.gerarInteiroDiaMesAnoDataInformada(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gerarInteiroInvertidoDiaMesAnoDataInformada method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testGerarInteiroInvertidoDiaMesAnoDataInformada() {
        System.out.println("gerarInteiroInvertidoDiaMesAnoDataInformada");
        Date pData = null;
        int expResult = 0;
        int result = UtilSBCoreDataHora.gerarInteiroInvertidoDiaMesAnoDataInformada(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gerarDataDiaMesAnoAtual method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testGerarDataDiaMesAnoAtual() {
        System.out.println("gerarDataDiaMesAnoAtual");
        Date expResult = null;
        Date result = UtilSBCoreDataHora.gerarDataDiaMesAnoAtual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteDataEmStringFormatada method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testConverteDataEmStringFormatada() {
        System.out.println("converteDataEmStringFormatada");
        Date pData = null;
        String expResult = "";
        String result = UtilSBCoreDataHora.converteDataEmStringFormatada(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteDataEmStringCorrida method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testConverteDataEmStringCorrida() {
        System.out.println("converteDataEmStringCorrida");
        Date pData = null;
        String expResult = "";
        String result = UtilSBCoreDataHora.converteDataEmStringCorrida(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteStringDD_MM_YYYYEmData method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testConverteStringDD_MM_YYYYEmData() {
        System.out.println("converteStringDD_MM_YYYYEmData");
        String pString = "";
        Date expResult = null;
        Date result = UtilSBCoreDataHora.converteStringDD_MM_YYYYEmData(pString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteString_dd_MM_yyEmData method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testConverteString_dd_MM_yyEmData() {
        System.out.println("converteString_dd_MM_yyEmData");
        String pString = "";
        Date expResult = null;
        Date result = UtilSBCoreDataHora.converteString_dd_MM_yyEmData(pString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteString_dd_MM_yyEmData method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testConverteString_hh_doisPontos_MinutosEmDate() {
        System.out.println("converteString_hh_doisPontos_MinutosEmDate");
        String pString = "";
        Date expResult = null;
        Date result = UtilSBCoreDataHora.converteString_dd_MM_yyEmData(pString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteDateEmSTringDD_MM_YY method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testConverteDateEmSTringDD_MM_YY() {
        System.out.println("converteDateEmSTringDD_MM_YY");
        Date pdata = null;
        String expResult = "";
        String result = UtilSBCoreDataHora.converteDateEmSTringDD_MM_YY(pdata);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteDateEmSTringDD_MM_YYYY method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testConverteDateEmSTringDD_MM_YYYY() {
        System.out.println("converteDateEmSTringDD_MM_YYYY");
        Date pdata = null;
        String expResult = "";
        String result = UtilSBCoreDataHora.converteDateEmSTringDD_MM_YYYY(pdata);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteStringEmDataEHora method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testConverteStringEmDataEHora() {
        System.out.println("converteStringEmDataEHora");
        String pString = "";
        Date expResult = null;
        Date result = UtilSBCoreDataHora.converteStringEmDataEHora(pString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retornaHoraAtual method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testRetornaHoraAtual() {
        System.out.println("retornaHoraAtual");
        Date pData = null;
        String expResult = "";
        String result = UtilSBCoreDataHora.retornaHoraAtual(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDiaIgual method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testIsDiaIgual() {
        System.out.println("isDiaIgual");
        Date pData1 = null;
        Date pData2 = null;
        boolean expResult = false;
        boolean result = UtilSBCoreDataHora.isDiaIgual(pData1, pData2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMesFazParteDoIntevalor method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testIsMesFazParteDoIntevalor() {
        System.out.println("isMesFazParteDoIntevalor");
        Date pMesReferencia = null;
        Date pMesinicial = null;
        Date pMesFinal = null;
        boolean expResult = false;
        boolean result = UtilSBCoreDataHora.isMesFazParteDoIntevalo(pMesReferencia, pMesinicial, pMesFinal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAnoIgual method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testIsAnoIgual() {
        System.out.println("isAnoIgual");
        Date pData1 = null;
        Date pData2 = null;
        boolean expResult = false;
        boolean result = UtilSBCoreDataHora.isAnoIgual(pData1, pData2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMesIgual method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testIsMesIgual() {
        System.out.println("isMesIgual");
        Date pData1 = null;
        Date pData2 = null;
        boolean expResult = false;
        boolean result = UtilSBCoreDataHora.isMesIgual(pData1, pData2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMes method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testGetMes() {
        System.out.println("getMes");
        Date pData = null;
        int expResult = 0;
        int result = UtilSBCoreDataHora.getMes(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDia method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testGetDia() {
        System.out.println("getDia");
        Date pData = null;
        int expResult = 0;
        int result = UtilSBCoreDataHora.getDia(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiaDaSemana method, of class UtilSBCoreDataHora.
     */
    @Test
    public void testGetDiaDaSemana() {
        System.out.println("getDiaDaSemana");
        Date pData = null;
        String expResult = "";
        String result = UtilSBCoreDataHora.getDiaDaSemana(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
