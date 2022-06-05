/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreStringsCammelCaseTest {

    public UtilSBCoreStringsCammelCaseTest() {
    }

    /**
     * Test of getTextoBySlug method, of class UtilSBCoreStringsCammelCase.
     */
    @Test
    public void testGetTextoBySlug() {

        System.out.println(UtilSBCoreStringsCammelCase.getTextoByCammel("descricaoTesteYRDDdddd"));

    }

    @Test
    public void testgetTextoByCammel() {
        String teste = UtilSBCoreStringsCammelCase.getCammelByTexto("asdkfjalskdfjalksdjf sdklafjalksdfj asdf lkajsdfklajsd");
        System.out.println(UtilSBCoreStringsCammelCase.getCammelByTexto("as-dkfj_alskdfjalksdjf uuuuuuuuuuuuuuuuuu sdklafjalksdfj asdf lkajsdfklajsd"));
    }

    @Test
    public void testGetCammelByTextoSemCaractersEspeciais() {
    }

    @Test
    public void testGetCammelByTexto() {
    }

    @Test
    public void testGetTextoByCammelMinusculo() {
    }

    @Test
    public void testGetCamelByTextoPrimeiraLetraMaiuscula() {
    }

    @Test
    public void testGetCamelByTextoPrimeiraLetraMaiusculaSemCaracterEspecial() {
    }

    @Test
    public void testGetTextoByCammelPrimeiraLetraMaiuscula() {
    }
}
