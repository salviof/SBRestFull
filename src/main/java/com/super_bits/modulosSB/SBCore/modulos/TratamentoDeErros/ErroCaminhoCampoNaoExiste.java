/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

/**
 *
 * @author SalvioF
 */
public class ErroCaminhoCampoNaoExiste extends Throwable {

    InfoErroSBComAcoes infoErro;

    private static String getNomeDaClasse(Class pClasse) {
        String nomeClasse = "Classe nula";
        if (pClasse != null) {
            nomeClasse = pClasse.getSimpleName();
        }
        return nomeClasse;
    }

    public ErroCaminhoCampoNaoExiste(String nomeClasse, String pCaminhoCampo) {

        super("O caminho campo " + pCaminhoCampo + " não foi encontrado em " + nomeClasse);

    }

    public ErroCaminhoCampoNaoExiste(Class pClasse, String pCaminhoCampo) {

        super("O caminho campo " + pCaminhoCampo + " não foi encontrado em " + getNomeDaClasse(pClasse));

    }

    public ErroCaminhoCampoNaoExiste(String nomeClasse, String pCaminhoCampo, Throwable causa) {

        super("O caminho campo " + pCaminhoCampo + " não foi encontrado em " + nomeClasse);

    }

    public ErroCaminhoCampoNaoExiste(Class pClasse, String pCaminhoCampo, Throwable causa) {

        super("O caminho campo " + pCaminhoCampo + " não foi encontrado em " + getNomeDaClasse(pClasse));

    }

}
