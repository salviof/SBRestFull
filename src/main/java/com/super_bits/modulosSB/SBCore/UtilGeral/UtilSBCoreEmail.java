/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.email.ConfigEmailServersProjeto;
import com.super_bits.modulosSB.SBCore.modulos.email.ItfServidordisparoEmail;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Classe de UTILITÀRIOS (Métodos EStáticos commmente Utilizados)____________
 * Função: Lidar com envio de Email
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 */
public abstract class UtilSBCoreEmail {

    private static ConfigEmailServersProjeto configuracao;

    public static void verificarConfiguracao() {
        if (configuracao == null) {
            throw new UnsupportedOperationException("O servidor padrão para email transacional não foi enviado, execute " + UtilSBCoreEmail.class.getSimpleName() + ".configurar(configuracao); ao iniciar o projeto ");
        }
    }

    public static void configurar(ConfigEmailServersProjeto pConfig) {
        configuracao = pConfig;
    }

    public static boolean enviarPorServidorPadrao(String pDestinatario, String pMensagem, String pAssunto) {
        verificarConfiguracao();
        return enviaporSSL(configuracao.getServidorPrincipalTransacional().getEnderecoServidor(),
                configuracao.getFromEmail(),
                configuracao.getServidorPrincipalTransacional().getUsuarioSMTP(),
                configuracao.getServidorPrincipalTransacional().getSenhaSMTP(), pMensagem,
                pDestinatario, pAssunto);
    }

    public static boolean enviarPorServidorPadrao(String pDestinatario, String pMensagem, String pAssunto, Object... pAnexos) {
        return enviarPorServidorPadrao(pDestinatario, pMensagem, pAssunto);

    }

    public static boolean enviaporSSL(final String servidor, final String pFromEmail, final String pUsuario, final String pSenha, String mensagem, String para, String pAssunto) {

        return enviarEmail(getPropriedadesEmailSMTP(servidor), pFromEmail, pUsuario, pSenha, mensagem, para, pAssunto);
    }

    private static Properties getPropriedadesEmailSMTP(String enderecoServidorSMTP) {
        Properties props = new Properties();
        props.put("mail.smtp.host", enderecoServidorSMTP);
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        return props;
    }

    private static boolean enviarEmail(Properties props, final String pFromEmail, final String pUsuario,
            final String pSenha, String mensagem, String para, String pAssunto) {
        boolean envioucomsucesso = true;
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(pUsuario, pSenha);
            }
        });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(pFromEmail));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(para));
            message.setSubject(pAssunto);
            message.setText(mensagem);
            message.setContent(mensagem, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException e) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando e-mail", e);
            envioucomsucesso = false;
        }
        return envioucomsucesso;
    }

    /**
     *
     * @param pUsuario email do usuário
     * @param pSenha senha do usuário
     * @param mensagem mensagem enviada
     * @param para email do destinatario
     * @param pAssunto Assunto do e-mail
     * @return
     */
    public static boolean enviaGmailporSSL(final String pUsuario, final String pSenha, String mensagem, String para, String pAssunto) {

        Properties props = getPropriedadesEmailSMTP("smtp.gmail.com");
        return enviarEmail(props, pUsuario, pUsuario, pSenha, mensagem, para, pAssunto);

    }

    public static ItfServidordisparoEmail getServidorDisparoEmail() {
        verificarConfiguracao();
        return (ItfServidordisparoEmail) configuracao.getServidorPrincipalTransacional();
    }

}
