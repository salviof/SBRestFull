/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.regex;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;

/**
 *
 * @author desenvolvedor
 */
public enum FabProcessadorRegexConhecidos implements ItfFabrica {

    LOCALIZAR_DIMENCOES_EM_TEXTO,
    LARGURA_DOIS_PONTOS_VALOR,
    ALTURA_DOIS_PONTOS_VALOR,
    PROFUNDIDADE_DOIS_PONTOS_VALOR,
    LOCALIZAR_PESO_EM_TEXTO,
    LOCALIZAR_TAMANHO_EM_TEXTO,;

    @Override
    public ProcessadorRegexTexto getRegistro() {
        switch (this) {
            case LOCALIZAR_DIMENCOES_EM_TEXTO:
                return new ProcessadorRegexTexto(FabRegraReconhecimentoRegex.DIMENSOES_AVANCADO,
                        FabRegraReconhecimentoRegex.DIMENSOES, FabRegraReconhecimentoRegex.MEDIDAS);

            case LOCALIZAR_PESO_EM_TEXTO:
                return new ProcessadorRegexTexto(FabRegraReconhecimentoRegex.PESO_DOIS_PONTOS_VALOR);

            case LOCALIZAR_TAMANHO_EM_TEXTO:
                throw new UnsupportedOperationException("Não implementado");

            case LARGURA_DOIS_PONTOS_VALOR:
                return new ProcessadorRegexTexto(FabRegraReconhecimentoRegex.LARGURA_DOIS_PONTOS_VALOR);

            case ALTURA_DOIS_PONTOS_VALOR:
                return new ProcessadorRegexTexto(FabRegraReconhecimentoRegex.ALTURA_DOIS_PONTOS_VALOR);

            case PROFUNDIDADE_DOIS_PONTOS_VALOR:
                return new ProcessadorRegexTexto(FabRegraReconhecimentoRegex.PROFUNDIDADE_DOIS_PONTOS_VALOR);

            default:
                throw new AssertionError(this.name());

        }

    }
}
