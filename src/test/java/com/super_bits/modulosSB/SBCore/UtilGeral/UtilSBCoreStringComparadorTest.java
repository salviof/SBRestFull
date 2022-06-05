/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import mtfn.MetaphonePtBr;
import mtfn.MetaphonePtBrFrouxo;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class UtilSBCoreStringComparadorTest {

    @Before
    public void UtilSBCoreStringComparadorTest() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

    public void teste() {

    }

    /**
     * Test of isParecido method, of class UtilSBCoreStringComparador.
     */
    @Test
    public void testIsParecido_String_String() {
        
        String testeMaluco= "ROSCA CHOCOLATE";
        String testeMalucoPrametro="coca cola";
         for (String ss : testeMalucoPrametro.toLowerCase().split("\\s")){
       for (String s : testeMaluco.toLowerCase().split("\\s")){
           UtilSBCoreStringComparador.JaroWinkler(s,ss);
       }
         }
        
    double score=    UtilSBCoreStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
     UtilSBCoreStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
     UtilSBCoreStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
     UtilSBCoreStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "CHOCOLATE");
     UtilSBCoreStringComparador.JaroWinkler("CHOCOLATE", "CHOCO");
     UtilSBCoreStringComparador.JaroWinkler("CHOCO","CHOCOLATE" );
     UtilSBCoreStringComparador.JaroWinkler("CHOCOLATE","CHOCOLATE" );
     UtilSBCoreStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
     UtilSBCoreStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
        System.out.println(score);
           
        UtilSBCoreStringComparador.isParecido("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "CHOCOLATE");

        System.out.println("isParecido");
        MetaphonePtBr testeFonetic;
        MetaphonePtBrFrouxo testeFonetic2;
        UtilSBCoreStringComparador.isParecido("CREME PARA PENTEAR NIELY GOLD QUERATINA 280G", "manteiga");
        System.out.println(new MetaphonePtBr("CREME PARA PENTEAR NIELY GOLD QUERATINA 280G").toString());
        System.out.println(new MetaphonePtBr("queratina").toString());
        assertTrue("Metaphone", new MetaphonePtBr("CREME PARA PENTEAR NIELY GOLD QUERATINA 280G").toString().contains(new MetaphonePtBr("nieli").toString()));

        String pReferencia = "";
        String pParametro = "";
        boolean expResult = false;
        boolean result = UtilSBCoreStringComparador.isParecido(pReferencia, pParametro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
