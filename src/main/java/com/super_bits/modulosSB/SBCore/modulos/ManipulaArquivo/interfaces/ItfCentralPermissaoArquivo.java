/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstArquivoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

/**
 *
 * @author desenvolvedor
 */
public interface ItfCentralPermissaoArquivo {

    public boolean isUsuarioPodeAlterar(ItfUsuario pUsuario, ItfCampoInstArquivoEntidade pArquivo);

    public boolean isUsuarioPodeBaixar(ItfUsuario pUsuario, ItfCampoInstArquivoEntidade pArquivo);

    public boolean isUsuarioPodeCriar(ItfUsuario pUsuario, ItfCampoInstArquivoEntidade pArquivo);

    public boolean isUsuarioLogadoPodeAlterar(ItfCampoInstArquivoEntidade pArquivo);

    public boolean isUsuarioLogadoPodeBaixar(ItfCampoInstArquivoEntidade pArquivo);

    public boolean isUsuarioLogadoPodeCriar(ItfCampoInstArquivoEntidade pArquivo);

}
