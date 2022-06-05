/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;
import org.junit.Test;

/**
 *
 * @author salvioF
 */
public class SBCoreTest {

    public SBCoreTest() {
    }

    @Test
    public void testGetCliente() {
        double valor = 1500;

        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ITALY);
        DecimalFormat df = (DecimalFormat) nf;
        df.setMinimumFractionDigits(2);
        String valorFormat = df.format(valor);
        System.out.println(valorFormat);
        String path = "SBProjeto.prop";
        InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
        if (stream == null) {
            throw new UnsupportedOperationException("arquivo não encontrado");
        }
        Properties props = new Properties();
        try {
            props.load(stream);
            stream.close();
            String versao = (String) props.get("VERSAO");
            if (versao == null) {
                throw new UnsupportedOperationException("A versão do projeto não foi encontrada no arquivo");
            }
            String nomeProjeto = (String) props.get("NOME_PROJETO");
            String nomeComercial = (String) props.get("NOME_SOCIAL");
            String titpoProjeto = (String) props.get("TIPO_PROJETO");
            String nomeCliente = (String) props.get("NOME_CLIENTE");
        } catch (IOException e) {
            throw new UnsupportedOperationException("erro lendo propriedade");
        }

    }

}
