/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import java.util.List;

/**
 *
 * @author SalvioF
 */
public class CampoInstanciadoTemplate implements ItfCampoInstTemplate {

    private final ItfCampoInstanciado campoInstanciado;

    private List<String> opcoesPalavraChave;
    private String opcoesPalavaChaveJson;

    public CampoInstanciadoTemplate(ItfCampoInstanciado pCampoInstanciado) {
        campoInstanciado = pCampoInstanciado;
    }

    private void builPalavraChave() {
        opcoesPalavraChave = campoInstanciado.getPropriedadesRefexao().getTemplateCampos();

    }

    @Override
    public List<String> getOpcoesPalavraChave() {

        if (opcoesPalavraChave == null) {
            builPalavraChave();
        }
        return opcoesPalavraChave;
    }

    @Override
    public String getOpcoesPalavaChaveJson() {
        if (opcoesPalavaChaveJson == null) {
            opcoesPalavaChaveJson = "[";
            int i = 0;
            for (String palavraChave : getOpcoesPalavraChave()) {
                if (i > 0) {
                    opcoesPalavaChaveJson += (",\"" + palavraChave + "\"");
                } else {
                    opcoesPalavaChaveJson += ("\"" + palavraChave + "\"");
                }
                i++;
            }
            opcoesPalavaChaveJson += "]";
        }

        return opcoesPalavaChaveJson;
    }

}
