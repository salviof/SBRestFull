/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.URL;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.apache.commons.mail.util.MimeMessageParser;

/**
 *
 * @author salviofurbino
 * @since 19/04/2019
 * @version 1.0
 */
public class UtilSBCoreEmailObjetos {

    public static String getAddresEmFormatoTextoPadrao(Address[] pDEstinatarios) {
        StringBuilder str = new StringBuilder();
        if (pDEstinatarios == null) {
            return "";
        }
        for (Address endreco : pDEstinatarios) {
            if (endreco == pDEstinatarios[pDEstinatarios.length - 1]) {
                endreco.toString();
            }
        }
        return str.toString();
    }

    /**
     * Extrai o e-mail de um texto de e-mail padrão, caso tenha vários e-mails
     * retorna o primeiro
     *
     * @param pTextoProtocoloMailPadrao
     * @return
     */
    public static String extrairTextoEamailDeStringProtocoloPadrao(String pTextoProtocoloMailPadrao) {
        if (pTextoProtocoloMailPadrao == null) {
            return null;
        }
        if (!pTextoProtocoloMailPadrao.contains("@")) {
            return null;
        }
        if (pTextoProtocoloMailPadrao.contains("<")) {
            String email = UtilSBCoreStringBuscaTrecho.getPartesEntreMaiorMenor(pTextoProtocoloMailPadrao).get(0);
            if (email.contains("@")) {
                return email;
            } else {
                return null;
            }
        }
        return UtilSBCoreStringsExtrator.extrairEmail(pTextoProtocoloMailPadrao);
    }

    public static String getAddresEmFormatoSeparadoVirgula(Address[] pDEstinatarios) {
        if (pDEstinatarios == null) {
            return "";
        }
        StringBuilder str = new StringBuilder();
        for (Address endreco : pDEstinatarios) {
            str.append(endreco.toString());
            str.append(",");

        }
        return str.toString();
    }

    public static class ArquivoAnexoEmail {

        private int id;
        private String nome;
        private String tipo;
        private InputStream arquivo;
        private String cid;

        public ArquivoAnexoEmail(ByteArrayDataSource pAnexo, String pCid) throws IOException {
            nome = pAnexo.getName();
            tipo = pAnexo.getContentType();
            arquivo = IOUtils.toBufferedInputStream(pAnexo.getInputStream());
            cid = pCid;
        }

        public int getId() {
            return id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getNome() {
            return nome;
        }

        public String getTipo() {
            return tipo;
        }

        public InputStream getArquivo() {
            return arquivo;
        }

        public boolean isContemCid() {
            return !UtilSBCoreStringValidador.isNuloOuEmbranco(cid);
        }

    }

    public static String substituirCidPorUrl(String texto, Map<String, String> mapaUrls) {
        Pattern r = Pattern.compile("(cid:).*?([^\"]*)");

        // Now create matcher object.
        Matcher m = r.matcher(texto);

        while (m.find()) {
            String cidCompleta = m.group(0);
            String cid = cidCompleta.replace("cid:", "");
            if (mapaUrls.get(cid) != null) {
                texto = texto.replace(cidCompleta, mapaUrls.get(cid));
            }

        }
        return texto;
    }

    public static List<String> getcids(String texto) {
        try {
            Pattern r = Pattern.compile("(cid:).*?([^\"]*)");

            // Now create matcher object.
            Matcher m = r.matcher(texto);
            List<String> cids = new ArrayList<>();
            while (m.find()) {
                String cidCompleta = m.group(0);
                //     String imagem = m.group(0);
                cids.add(cidCompleta.replace("cid:", ""));
            }
            return cids;
        } catch (Throwable t) {

            return new ArrayList<>();
        }

    }

    public static List<ArquivoAnexoEmail> lerAnexos(Message m)
            throws IOException, MessagingException {
        MimeMessageParser conteudo;
        List<ArquivoAnexoEmail> listaanexos = new ArrayList<>();
        try {

            conteudo = new MimeMessageParser((MimeMessage) m);
            conteudo.parse();
            List<String> cids = getcids(conteudo.getHtmlContent());
            List<DataSource> streamsDeArquivosAnexados = new ArrayList<>();
            for (String cid : cids) {
                DataSource arquivo = conteudo.findAttachmentByCid(cid);

                if (arquivo instanceof ByteArrayDataSource) {
                    streamsDeArquivosAnexados.add(arquivo);
                    ByteArrayDataSource anexo = (ByteArrayDataSource) arquivo;

                    listaanexos.add(new ArquivoAnexoEmail(anexo, cid));

                    //UtilSBCoreOutputs.salvarArquivoBfInput(new BufferedInputStream(IOUtils.toBufferedInputStream(anexo.getInputStream())), "/home/superBits/temp/" + anexo.getName());
                }
            }

            for (Iterator iterator = conteudo.getAttachmentList().iterator(); iterator.hasNext();) {
                DataSource next = (DataSource) iterator.next();

                if (next instanceof ByteArrayDataSource) {
                    if (!streamsDeArquivosAnexados.contains(next)) {
                        ByteArrayDataSource anexo = (ByteArrayDataSource) next;
                        System.out.println(anexo.getName());
                        listaanexos.add(new ArquivoAnexoEmail(anexo, null));

                    }
                    //UtilSBCoreOutputs.salvarArquivoBfInput(new BufferedInputStream(IOUtils.toBufferedInputStream(anexo.getInputStream())), "/home/superBits/temp/" + anexo.getName());
                }
                System.out.println(next);
                System.out.println(next.getClass().getSimpleName());
            }

            System.out.println(conteudo.getHtmlContent());
            System.out.println(conteudo.getPlainContent());

        } catch (Exception ex) {
            Logger.getLogger(UtilSBCoreEmailObjetos.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listaanexos;
    }

    public static String lerConteudo(Message m)
            throws IOException, MessagingException {
        MimeMessageParser conteudo;
        try {
            conteudo = new MimeMessageParser((MimeMessage) m);
            conteudo.parse();
            if (conteudo.getHtmlContent() != null) {
                return conteudo.getHtmlContent();
            } else {
                return conteudo.getPlainContent();
            }

        } catch (Exception ex) {
            Logger.getLogger(UtilSBCoreEmailObjetos.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
