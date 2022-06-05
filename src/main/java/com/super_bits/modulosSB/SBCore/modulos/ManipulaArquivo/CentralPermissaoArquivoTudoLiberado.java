/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralPermissaoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstArquivoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

/**
 *
 * @author desenvolvedor
 */
public class CentralPermissaoArquivoTudoLiberado implements ItfCentralPermissaoArquivo {

    @Override
    public boolean isUsuarioPodeAlterar(ItfUsuario pUsuario, ItfCampoInstArquivoEntidade pArquivo) {
        return true;
    }

    @Override
    public boolean isUsuarioPodeBaixar(ItfUsuario pUsuario, ItfCampoInstArquivoEntidade pArquivo) {
        return true;
    }

    @Override
    public boolean isUsuarioPodeCriar(ItfUsuario pUsuario, ItfCampoInstArquivoEntidade pArquivo) {
        return true;
    }

    @Override
    public boolean isUsuarioLogadoPodeAlterar(ItfCampoInstArquivoEntidade pArquivo) {
        return true;
    }

    @Override
    public boolean isUsuarioLogadoPodeBaixar(ItfCampoInstArquivoEntidade pArquivo) {
        return true;
    }

    @Override
    public boolean isUsuarioLogadoPodeCriar(ItfCampoInstArquivoEntidade pArquivo) {
        return true;
    }

}
