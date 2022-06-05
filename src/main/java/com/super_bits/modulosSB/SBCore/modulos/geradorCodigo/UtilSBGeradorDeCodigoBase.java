/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsMaiuculoMinusculo;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaCampoEntidade;

/**
 *
 * Utilse UtilSBDevelGeradorCodigo
 *
 * ->Mantido temporariamente para estudo
 *
 * @author salvioF
 */
@Deprecated
public class UtilSBGeradorDeCodigoBase {

    public static final String CAMINHO_PADRAO_PACOTE_IMPLEMENTACAO_MODEL = "org.coletivoJava.fw.projetos." + SBCore.getGrupoProjeto() + ".implemetation.model";

    public static String gerarcaminhoPacoteClasse(EstruturaCampo pCampo) {
        return gerarCaminhoPacoteEscopoModel(pCampo.getEstruturaPai().getNome());
    }

    public static String gerarCaminhoPacoteEscopoModel(String pSubPacote) {
        return CAMINHO_PADRAO_PACOTE_IMPLEMENTACAO_MODEL + "." + pSubPacote.toLowerCase();
    }

    public static String getNomeClasseValidacao(ItfEstruturaCampoEntidade pCampo) {
        return "Validacao" + pCampo.getEstruturaPai().getNome() + UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMaiusculo(pCampo.getNomeDeclarado());
    }

    public static String getNomeClasseValorLogico(ItfEstruturaCampoEntidade pCampo) {
        return "ValorLogico" + pCampo.getEstruturaPai().getNome() + UtilSBCoreStringsMaiuculoMinusculo.getPrimeiraLetraMaiusculo(pCampo.getNomeDeclarado());
    }
}
