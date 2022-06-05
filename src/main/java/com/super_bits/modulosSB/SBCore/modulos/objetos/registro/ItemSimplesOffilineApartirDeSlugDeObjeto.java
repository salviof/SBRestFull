/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;

/**
 *
 * @author Salvio Furbino
 */
public class ItemSimplesOffilineApartirDeSlugDeObjeto extends ItemSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private final Integer id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private final String descricao;
    private final String slugEnviado;

     public ItemSimplesOffilineApartirDeSlugDeObjeto(String pSlug) {

        if (pSlug == null) {
            throw new UnsupportedOperationException("O valor não foi enviado");
        }
        String[] valores = pSlug.split("-");
        int idx = 0;
        Integer indexCodigo = null;
        for (String valor : valores) {
            if (UtilSBCoreStringValidador.isContemApenasNumero(valor)) {
                indexCodigo = idx;
            }
            idx++;
        }
        if (indexCodigo != null) {
            id = Integer.parseInt(valores[indexCodigo]);
        } else {
            id = null;
        }
        String texto = "";
        int idxv = 0;
        for (String valor : valores) {
            if (idxv != indexCodigo) {
                texto += valor;
            }
            idxv++;
        }
        descricao = texto;
        slugEnviado = pSlug;
    }

    @Override
    public int getId() {
        if (id == null) {
            return -1;
        }
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSlugEnviado() {
        return slugEnviado;
    }

}
