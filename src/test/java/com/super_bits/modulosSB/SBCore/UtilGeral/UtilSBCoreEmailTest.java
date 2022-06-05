/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.email.ConfigEmailServersProjeto;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreEmailTest {

    public UtilSBCoreEmailTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testEnviaGmailporSSL() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        UtilSBCoreEmail.enviaGmailporSSL(
                "guiasemktdigitalcontagem@gmail.com",
                "",
                "Olá, conforme solicitação, segue a senha,<br/> mas antes, como esssa "
                + "é uma mensagem provisória não posso deixar de observar a completa falta de senso nexo e conformidade com a realidade, um ser humano viver esquecendo sua senha...  <br/>"
                + "Mas blz, dessa vez passa a senha é  pEmail",
                "salviof@gmail.com",
                "Teste");
    }

    @Test
    public void testVerificarConfiguracao() {
    }

    @Test
    public void testConfigurar() {
    }

    @Test
    public void testEnviarPorServidorPadrao() {
        UtilSBCoreEmail.configurar(new ConfigEmailServersProjeto("", "", ""));
        UtilSBCoreEmail.enviarPorServidorPadrao("salviof@gmail.com", "sdjflkajsdflkajsdf <b> asasdfasdfa </b>", "asdfasdfa");

    }

    @Test
    public void testEnviaporSSL() {
    }

}
