/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringGerador.TIPO_LOREN;
import java.util.Date;
import org.apache.commons.lang3.RandomUtils;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilSBCoreRandomico {

    public enum TIPO_VALOR_RANDON {
        NUMERO, LETRAS, DATA, DATA_FUTURO, DATA_PASSADO
    }

    public static String getValorStringRandomico(TIPO_VALOR_RANDON pTipo, int tamanho) {
        if (tamanho == 0) {
            tamanho = 10;
        }

        switch (pTipo) {
            case NUMERO:
                String valorMaximo = "";
                for (int i = 0; i < tamanho; i++) {
                    valorMaximo += "9";
                }
                long max = Long.valueOf(valorMaximo);
                long valorRamdon = RandomUtils.nextLong(0, max);
                return String.valueOf(valorRamdon);

            case LETRAS:
                int numeropalavras = 10;
                if (tamanho > 30) {
                    numeropalavras = tamanho;
                }
                String palavra = UtilSBCoreStringGerador.GetLorenIpsilum(numeropalavras, TIPO_LOREN.PALAVRAS);
                int metade = (int) Math.ceil((palavra.length() - 1) / 2);
                int tamanhoGerado = palavra.length() - 1;
                int fimAleatorio = RandomUtils.nextInt(metade, tamanhoGerado);
                return palavra.substring(fimAleatorio - tamanho, fimAleatorio);

            case DATA:
                return UtilSBCoreDataHora.converteDateEmSTringDD_MM_YY(new Date());

            case DATA_FUTURO:
                return UtilSBCoreDataHora.converteDateEmSTringDD_MM_YY(new Date());

            case DATA_PASSADO:
                return UtilSBCoreDataHora.converteDateEmSTringDD_MM_YY(new Date());

            default:
                throw new AssertionError(pTipo.name());

        }

    }

    public static char getValorRandomicoDaMaskara(char pChar) {
        if (pChar == '#') {
            return getValorStringRandomico(TIPO_VALOR_RANDON.NUMERO, 1).charAt(0);
        }
        if (pChar == '?') {
            return getValorStringRandomico(TIPO_VALOR_RANDON.LETRAS, 1).charAt(0);
        }
        if (pChar == '*') {
            return getValorStringRandomico(TIPO_VALOR_RANDON.LETRAS, 1).charAt(0);
        }
        return pChar;

    }

    public static boolean isValorMaskaraCompativel(char pChar) {
        return true;
    }

    public static String getValorStringRandomicoViaMaskara(String maskara) {
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(maskara)) {
            return getValorStringRandomico(TIPO_VALOR_RANDON.LETRAS, 0);
        }
        StringBuilder builder = new StringBuilder();
        maskara.chars().filter(carac -> isValorMaskaraCompativel((char) carac)).
                forEach(caracter -> builder.append(getValorRandomicoDaMaskara((char) caracter)));

        return builder.toString();
    }

}
