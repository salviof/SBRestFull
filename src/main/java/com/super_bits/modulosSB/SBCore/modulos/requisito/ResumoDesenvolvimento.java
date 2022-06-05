/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.requisito;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemGenerico;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class ResumoDesenvolvimento extends ItemGenerico {

    private List<ComentarioRequisito> comentarios;
    private List<LogDeTrabalho> log;

    @Override
    public String getImgPequena() {
        return "naoInformado.jpg";
    }

}
