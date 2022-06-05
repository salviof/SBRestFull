
/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Salvio
 */
public class UtilSBCoreStringsTest {

    // @Test
    public void testarRetiradaDeEspaco() {
        String texto = "         111         2222 ";

        System.out.println(UtilSBCoreStringFiltros.getTRIMRemoveEspacos(texto));
    }

    // @Test
    public void testaInverterString() {

        String resultado = UtilSBCoreStringFiltros.inverteString("123");

        System.out.println(resultado);

    }

    //@Test
    public void testaInverterStringData() {

        String resultado = UtilSBCoreStringFiltros.inverteStringData("09122016");

        System.out.println(resultado);

    }

    public UtilSBCoreStringsTest() {
    }

    /**
     * Test of removeCaracteresEspeciais method, of class
     * UtilSBCoreStringValidador.
     */
    @Test
    public void testRemoveCaracteresEspeciais() {
        String n = Normalizer.normalize("schönÁáéÉíÍóÓúÚ", Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        System.out.println("_________________________________");
        SBCore.soutInfoDebug(n);
        System.out.println("_________________________________");

    }

    /**
     * Test of gerarUrlAmigavel method, of class UtilSBCoreStringValidador.
     */
    //@Test
    public void testMakeStrUrlAmigavel() {
    }

    /**
     * Test of splitMaiuscula method, of class UtilSBCoreStringValidador.
     */
    //@Test
    public void testSplitMaiuscula() {
    }

    /**
     * Test of strValorEntreParenteses method, of class
     * UtilSBCoreStringValidador.
     */
    //@Test
    public void testStrValorEntreParenteses() {
    }

    /**
     * Test of substituiParametrosNomeadosPorInterroga method, of class
     * UtilSBCoreStringValidador.
     */
    //@Test
    public void testSubstituiParametrosNomeadosPorInterroga() {
    }

    /**
     * Test of dtMesBarraAno method, of class UtilSBCoreStringValidador.
     */
    //@Test
    public void testDtMesBarraAno() throws Exception {
    }

    /**
     * Test of compara2StrArrays method, of class UtilSBCoreStringValidador.
     */
    //@Test
    public void testCompara2StrArrays() {
    }

    /**
     * Test of localizarParte method, of class UtilSBCoreStringValidador.
     */
    //@Test
    public void testLocalizarParte() {
    }

    /**
     * Test of getStringsByURL method, of class UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetStringsByURL() {

    }

    /**
     * Test of getStringsByLocalFile method, of class UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetStringsByLocalFile() {
    }

    @Before
    public void init() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

    /**
     * Test of getLpadInteiro method, of class UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetLpad() {

        String teste1 = "1234";
        teste1 = UtilSBCoreStringFiltros.getLpad(teste1, 10, "0");
        String teste2 = "abcd";
        teste2 = UtilSBCoreStringFiltros.getLpad(teste2, 10, "X");
        System.out.println(teste1 + teste2);
        Assert.assertTrue("Erro valor esperado para Lpad de 10 casas para string 1234 não confere", teste1.equals("0000001234"));
        //     Assert.assertTrue("Erro valor esperado para Lpad de 10 casas para string abcd não confere", teste2.equals("XXXXXXabcd"));
    }

    /**
     * Test of getListaStringEntreCaracter method, of class
     * UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetListaStringEntreCaracter_String_UtilSBCoreStringsTIPO_SINALIZADOR() {
    }

    /**
     * Test of getListaStringEntreCaracter method, of class
     * UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetListaStringEntreCaracter_3args() {
    }

    /**
     * Test of getlistadeLinhas method, of class UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetlistadeLinhas() {
    }

    /**
     * Test of getStringDaListaComBarraN method, of class
     * UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetStringDaListaComBarraN() {
    }

    /**
     * Test of getStringDaListaComSeparador method, of class
     * UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetStringDaListaComSeparador() {
    }

    /**
     * Test of getStringsByInputStream method, of class
     * UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetStringsByInputStream() {
    }

    /**
     * Test of getStringByInputStream method, of class
     * UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetStringByInputStream() {
    }

    /**
     * Test of getStringByOutputStream method, of class
     * UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetStringByOutputStream() {
    }

    /**
     * Test of substituirEspacosPorCaracter method, of class
     * UtilSBCoreStringValidador.
     */
    //@Test
    public void testSubstituirEspacosPorCaracter() {
    }

    /**
     * Test of getRpad method, of class UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetRpad() {
    }

    /**
     * Test of GetLorenIpsilum method, of class UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetLorenIpsilum_int_UtilSBCoreStringsTIPO_LOREN() {
    }

    /**
     * Test of getLorenIpsilum method, of class UtilSBCoreStringValidador.
     */
    //@Test
    public void testGetLorenIpsilum_UtilSBCoreStringsTIPO_LOREN() {
    }

    /**
     * Test of quebrarStringEmLinhas method, of class UtilSBCoreStringValidador.
     */
    @Test
    public void testQuebrarStringEmLinhas() {

        String teste = "@asdasd";

        if (teste.contains("@")) {
            System.out.println("é até que tem um arroba ai...");
        } else {
            System.out.println("Tem não");
        }
    }

    @Before
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

    public void testaPesquisa() {

        String teste = "adsasdasd[123]";

        String pLista = "asdasdasdcom";
        String[] campos = pLista.split("\\.");
        String campo = campos[campos.length - 1];
        System.out.println(campo);
        String teste1 = "fff[]";
        String teste2 = "fff[123]";

        final Pattern pattern = Pattern.compile("\\[(.\\d+)\\]");
        final Matcher matcher = pattern.matcher(teste1);
        final Matcher matcher2 = pattern.matcher(teste2);
        if (matcher.find()) {
            System.out.println("Encontrou no priiro");
        }

        if (matcher2.find()) {
            System.out.println("Encontrou segundo");
            System.out.println(matcher2.group(1));
        }

    }

    // @Test
    public void testaRandomico() {
        JOptionPane.showMessageDialog(null, UtilSBCoreStringGerador.getStringRandomica(8));
    }

    @Test
    public void testGetStringEntreCaracters() {
    }

    @Test
    public void testRemoveCaracteresEspeciaisEspacosETracos() {
    }

    @Test
    public void testGetStringAteEncontrarIsto() {

        String s = "52MB123456";
        String resp = UtilSBCoreStringBuscaTrecho.getStringAteEncontrarIsto(s, "MB");
        System.out.println("ATE ENCONTRAR ISTO " + resp);
    }

    @Test
    public void testGetStringAPartirDisto() {
    }

    @Test
    public void testGetPrimeirasXLetrasDaString() {
    }

    @Test
    public void testGetPrimeiraLetraMinuscula() {
    }

    @Test
    public void testGetPrimeiraLetraMaiuscula() {
    }

    @Test
    public void testGetStringRandomica() {
    }

    @Test
    public void testGetNumericosDaString() {
    }

    @Test
    public void testGetMascaraJavaMaskParaJQueryMask() {
    }

    @Test
    public void testIsPrimeiraLetraMaiuscula() {
    }

    @Test
    public void testIsPrimeiraApenasLetraMaiuscula() {
    }

    @Test
    public void testIsNuloOuEmbranco() {
    }

    @Test
    public void testIsNAO_NuloNemBranco() {
    }

    @Test
    public void testLimparCacteresEstranhosDaStringComNumeros() {
    }

}
