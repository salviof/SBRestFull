/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreStringSlugsTest {

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

    @Test
    public void testGerarSlugCaixaAlta() {

        String testeeee = "https://muffatosupermercados.vteximg.com.br/arquivos/ids/196246-500-500/21735-feijao-biju-cor-vermelho-500-g.jpg?v=635711724964800000";
        testeeee = testeeee.replace("-400-400", "-500-500");
        System.out.println(testeeee);
        String retorno = UtilSBCoreStringSlugs.getSlugPagina("casanovadigital.com.br/home/fullBanner.frase1");
        System.out.println(retorno);
        assertEquals(retorno, "casanovadigital.com.br/home/");

        String retorno2 = UtilSBCoreStringSlugs.getSlugPagina("/home/fullBanner.frase1");
        System.out.println(retorno2);
        assertEquals(retorno2, "/home/");

        String retorno3 = UtilSBCoreStringSlugs.getSlugPagina("casanovadigital.com.br/home");
        System.out.println(retorno3);
        assertEquals(retorno3, "casanovadigital.com.br/");
        String retorno4 = UtilSBCoreStringSlugs.getSlugPagina("casanovadigital.com.br/servicos");
        System.out.println(retorno4);
        assertEquals(retorno4, "casanovadigital.com.br/");
        String retorno5 = UtilSBCoreStringSlugs.getSlugPagina("casanovadigital.com.br/servicos/");
        System.out.println(retorno5);
        assertEquals(retorno5, "casanovadigital.com.br/servicos/");

        String retorno6 = UtilSBCoreStringSlugs.getSlugPagina("servicosDestaque.produto1");
        System.out.println(retorno6);
        assertEquals(retorno6, "/");

        String retorno7 = UtilSBCoreStringSlugs.getSlugPagina("casanovadigital.com.br/servicos/destaque.banner");
        System.out.println(retorno7);
        assertEquals(retorno7, "casanovadigital.com.br/servicos/");

    }

    /**
     * Test of gerarSlugCaixaAltaByCammelCase method, of class
     * UtilSBCoreStringSlugs.
     */
    @Test
    public void testGerarSlugCaixaAltaByCammelCase() {
        System.out.println("");

        String result = UtilSBCoreStringSlugs.gerarSlugCaixaAltaByCammelCase("gerarSlugCaixaAltaByCammelCase");
        assertEquals("GERAR_SLUG_CAIXA_ALTA_BY_CAMMEL_CASE", result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of gerarSlugSimples method, of class UtilSBCoreStringSlugs.
     */
    @Test
    public void testGerarSlugSimples() {
        fail("The test case is a prototype.");
        System.out.println("gerarSlugSimples");
        String pValor = "";
        String expResult = "";
        String result = UtilSBCoreStringSlugs.gerarSlugSimples(pValor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of gerarSlugSimplesForcandoMaiusculoAposEspaco method, of class
     * UtilSBCoreStringSlugs.
     */
    @Test
    public void testGerarSlugSimplesForcandoMaiusculoAposEspaco() {
        fail("The test case is a prototype.");
        System.out.println("gerarSlugSimplesForcandoMaiusculoAposEspaco");
        String pValor = "";
        String expResult = "";
        String result = UtilSBCoreStringSlugs.gerarSlugSimplesForcandoMaiusculoAposEspaco(pValor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of gerarSlugCaixaBaixa method, of class UtilSBCoreStringSlugs.
     */
    @Test
    public void testGerarSlugCaixaBaixa() {
        fail("The test case is a prototype.");
        System.out.println("gerarSlugCaixaBaixa");
        String pValor = "";
        String expResult = "";
        String result = UtilSBCoreStringSlugs.gerarSlugCaixaBaixa(pValor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of formatarSlugDeConteudo method, of class UtilSBCoreStringSlugs.
     */
    @Test
    public void testFormatarSlugDeConteudo() {
        fail("The test case is a prototype.");
        System.out.println("formatarSlugDeConteudo");
        String pDominio = "";
        String pSlug = "";
        String expResult = "";
        String result = UtilSBCoreStringSlugs.formatarSlugDeConteudo(pDominio, pSlug);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSlugPagina method, of class UtilSBCoreStringSlugs.
     */
    @Test
    public void testGetSlugPagina() {
        fail("The test case is a prototype.");
        System.out.println("getSlugPagina");
        String pSlug = "";
        String expResult = "";
        String result = UtilSBCoreStringSlugs.getSlugPagina(pSlug);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
