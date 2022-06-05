/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import static com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringGerador.TIPO_LOREN.PALAVRAS;
import static com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringGerador.TIPO_LOREN.PARAGRAFO;
import de.svenjacobs.loremipsum.LoremIpsum;
import java.util.UUID;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreStringGerador {

    public enum TIPO_LOREN {

        PARAGRAFO, PALAVRAS
    }

    /**
     *
     *
     *
     * @param pQuantidade quantidade de caracteres retornados
     * @return Retorna uma String randomica de acordo com o n de caracteres
     */
    public static String getStringRandomica(int pQuantidade) {
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom.substring(0, pQuantidade);
    }

    /**
     * Gera Loren Ipsum para utilização em textos
     *
     * @param pQuantidade
     * @param pTipoLTipo_loren
     * @return
     */
    public static String GetLorenIpsilum(int pQuantidade, TIPO_LOREN pTipoLTipo_loren) {
        if (pQuantidade == 0) {
            pQuantidade = 3;
        }

        LoremIpsum lorem = new LoremIpsum();
        switch (pTipoLTipo_loren) {
            case PALAVRAS:

                return lorem.getWords(pQuantidade);
            case PARAGRAFO:
                return lorem.getParagraphs(pQuantidade);

        }
        return lorem.getWords(3);

    }

    /**
     * Gera Lorem ipsulum de 3 palavras
     *
     * @param pTipoLTipo_loren
     * @return Loren ipsulum de 3 palavras ou pragrafos de acordo com o campo
     * escolhido
     */
    public static String getLorenIpsilum(TIPO_LOREN pTipoLTipo_loren) {
        return GetLorenIpsilum(0, pTipoLTipo_loren);
    }
}
