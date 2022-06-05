/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

/**
 *
 * @author SalvioF
 */
public abstract class GlobalCaminhoPacotes {

    public static final String PACOTE_BASE = "org.coletivoJava";
    public static final String PACOTE_BASE_MODULOS_CORE = "org.coletivoJava.modulos";
    public static final String PACOTE_BASE_MODULOS_PROJETOS_APP = "org.coletivoJava.proj";
    public static final String PACOTE_BASE_MODULOS_PROJ = "org.coletivoJava.proj.modulo";

    //modulo.model ou model.modulo?????
    public static final String PACOTE_BASE_PROJ_MODELAGEM = "org.coletivoJava.proj.model." + SBCore.getGrupoProjeto();
    public static final String PACOTE_BASE_PROJ_WEB_PAGINAS = "org.coletivoJava.proj.webView." + SBCore.getGrupoProjeto();
    public static final String PACOTE_BASE_PROJ_WEB_SERVICE = "org.coletivoJava.proj.webService." + SBCore.getGrupoProjeto();
    public static final String PACOTE_BASE_PROJ_IOT = "org.coletivoJava.modulos.proj.iot." + SBCore.getGrupoProjeto();
    public static final String PACOTE_BASE_PROJ_ANDROID = "org.coletivoJava.modulos.proj.android." + SBCore.getGrupoProjeto();
    public static final String PACOTE_BASE_PROJ_IOS = "org.coletivoJava.modulos.proj.ios." + SBCore.getGrupoProjeto();

}
