package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.excecao;

public class ErroDeInjecao extends Exception {

    public ErroDeInjecao() {
    }

    public ErroDeInjecao(String s) {
        super(s);
    }

    public ErroDeInjecao(Throwable throwable) {
        super(throwable);
    }

    public ErroDeInjecao(String s, Throwable throwable) {
        super(s, throwable);
    }

}
