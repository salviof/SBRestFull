package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
/**
 *
 * @author SalvioF
 */
public class UtilSBCoreBytes {

    public static byte[] gerarByteByStringBase64(String pString) {
        try {
            return Base64.decodeBase64(pString);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando Byte por String", t);
            return null;
        }
    }

    public static BufferedImage gerarBufferedImagemByByteArray(byte[] pByte) {
        try {
            InputStream in = new ByteArrayInputStream(pByte);
            BufferedImage imagem = ImageIO.read(in);

            return imagem;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando BufferedImage por byte Array", t);
            return null;
        }

    }

    public static InputStream geraInputStreamByByteArray(byte[] pByte) {
        try {
            InputStream in = new ByteArrayInputStream(pByte);
            in.reset();
            return in;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando BufferedImage por byte Array", t);
            return null;
        }

    }

    public static BufferedImage getBufferedImagebyStringBase64(String pString) {
        try {
            return gerarBufferedImagemByByteArray(gerarByteByStringBase64(pString));
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando BufferedImage por String base 64", t);
            return null;
        }

    }

    public static byte[] gerarBytePorUrltream(String pUrl) {
        try {
            byte[] bytes = IOUtils.toByteArray(UTilSBCoreInputs.getStreamBuffredByURL(pUrl));

            return bytes;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static byte[] gerarBytePorInputstream(InputStream pInput) {
        try {
            byte[] bytes = IOUtils.toByteArray(pInput);

            return bytes;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static byte[] gerarBytePorBufferedImagem(BufferedImage pImagem) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(pImagem, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static byte[] gerarBytesPorArquivo(File pArquivo) throws Exception {
        FileInputStream fis = new FileInputStream(pArquivo);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int bytesRead = 0;
        while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        fis.close();
        return baos.toByteArray();
    }

}
