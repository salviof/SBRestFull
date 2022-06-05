package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.excecao;

public class ErroDeFormatoDoCampo extends Exception {

	private static final long serialVersionUID = 8464144343246315472L;

	public ErroDeFormatoDoCampo() {
	}

    public ErroDeFormatoDoCampo(String s) {
        super(s);
    }

    public ErroDeFormatoDoCampo(Throwable throwable) {
        super(throwable);
    }
    
    public ErroDeFormatoDoCampo(String s, Throwable throwable) {
        super(s, throwable);
    }
	
}
