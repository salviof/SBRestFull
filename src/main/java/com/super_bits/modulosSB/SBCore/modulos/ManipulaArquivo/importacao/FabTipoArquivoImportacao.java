/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.importacao;

import javax.ws.rs.core.MediaType;

/**
 *
 * @author SalvioF
 */
public enum FabTipoArquivoImportacao {

    JSON, XML, XLS, CVS, INDETERMINADO;

    public MediaType getMediaType() {
        switch (this) {
            case JSON:
                return MediaType.APPLICATION_JSON_TYPE;

            case XML:
                return MediaType.APPLICATION_ATOM_XML_TYPE;

            case XLS:
                return null;

            case CVS:
                return MediaType.TEXT_PLAIN_TYPE;
            case INDETERMINADO:
                return null;

            default:
                throw new AssertionError(this.name());

        }
    }

}
