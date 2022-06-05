/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author SalvioF
 */
public class UtilSBCoreInputOutputConversoes {

    public static ByteArrayInputStream byteArrayStreamPorBufferedImage(BufferedImage pImagem, String pExtencao) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(pImagem, pExtencao, os);
            ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro transformando BufferedImage em ByteArray", t);
            return null;
        }
    }

    public static InputStream BufferedImageToInputStream(BufferedImage pImagem, String pExtencao) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(pImagem, pExtencao, os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro transformando BufferedImage em ByteArray", t);
            return null;
        }
    }

}
