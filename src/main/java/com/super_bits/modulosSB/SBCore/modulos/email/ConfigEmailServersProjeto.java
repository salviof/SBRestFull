/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.email;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public class ConfigEmailServersProjeto {

    /**
     *
     */
    public static final String NOME_SERVIDOR_SMTP_PRINCIPAL = "smtpPrincipal";
    ServidorSaidaSmtp servidorPrincipalTransacional;

    Map<String, ItfServidorEmailReceptor> servidoresEntrada = new HashMap<>();
    Map<String, ServidorSaidaSmtp> servidoresSaidaSMTP = new HashMap<>();

    public ConfigEmailServersProjeto(String pServidor, String pUsuario, String pSenha) {
        this(pServidor, pUsuario, pUsuario, pSenha);
    }

    public ConfigEmailServersProjeto(String servidor, String pFromEmail, String Usuario, String senha) {

        servidorPrincipalTransacional = new ServidorSaidaSmtp();
        servidorPrincipalTransacional.setPorta(587);
        servidorPrincipalTransacional.setEnderecoServidor(servidor);
        servidorPrincipalTransacional.setFromEmail(pFromEmail);
        servidorPrincipalTransacional.setUsuario(Usuario);
        servidorPrincipalTransacional.setSenha(senha);
        servidorPrincipalTransacional.setUsarSSL(true);
        servidorPrincipalTransacional.setUsarTSL(true);
        servidorPrincipalTransacional.setNome(NOME_SERVIDOR_SMTP_PRINCIPAL);
        servidoresSaidaSMTP.put(NOME_SERVIDOR_SMTP_PRINCIPAL, servidorPrincipalTransacional);

    }

    public String getFromEmail() {
        return servidorPrincipalTransacional.getFromEmail();
    }

    public ServidorSaidaSmtp getServidorPrincipalTransacional() {
        return servidorPrincipalTransacional;
    }

    public Map<String, ItfServidorEmailReceptor> getServidoresEntrada() {
        return servidoresEntrada;
    }

    public Map<String, ServidorSaidaSmtp> getServidoresSaidaSMTP() {
        return servidoresSaidaSMTP;
    }

}
