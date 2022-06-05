package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.excecao;

public class ErroDeMapaDeCampos extends Exception {

	private static final long serialVersionUID = -8531171176826142401L;

	public ErroDeMapaDeCampos() {
    }

    public ErroDeMapaDeCampos(String s) {
        super(s);
    }

    public ErroDeMapaDeCampos(Throwable throwable) {
        super(throwable);
    }
    
    public ErroDeMapaDeCampos(String s, Throwable throwable) {
        super(s, throwable);
    }
    
}
