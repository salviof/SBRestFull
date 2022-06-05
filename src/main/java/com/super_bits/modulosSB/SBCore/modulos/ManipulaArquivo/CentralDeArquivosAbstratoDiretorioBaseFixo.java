/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.FabTipoEmpacotamento;

/**
 *
 * @author desenvolvedor
 */
public abstract class CentralDeArquivosAbstratoDiretorioBaseFixo extends CentralDeArquivosAbstrata {

    public final String diretoriobase;

    public CentralDeArquivosAbstratoDiretorioBaseFixo(String pDiretorio, FabTipoEmpacotamento pTipoEmpacotamento) {
        super(pTipoEmpacotamento, TIPO_ESTRUTURA_LOCAL_XHTML_PADRAO.MODULO_PREFIXO_SLUG_DO_MB_DE_GESTAO);
        diretoriobase = pDiretorio;
    }

}
