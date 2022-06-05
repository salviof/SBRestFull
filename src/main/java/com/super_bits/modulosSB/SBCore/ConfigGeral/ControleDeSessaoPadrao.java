/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fonteDados.ItfTokenAcessoDados;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.SessaoOffline;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;

import com.super_bits.modulosSB.SBCore.modulos.sessao.ControleDeSessaoAbstratoSBCore;

/**
 *
 * COntrole de acesso de sessao para Aplicações Offline
 *
 * @author Salvio
 */
public class ControleDeSessaoPadrao extends ControleDeSessaoAbstratoSBCore {

    private static final ItfSessao sessao = new SessaoOffline();
    public static ItfTokenAcessoDados acessoDados;

    @Override
    public ItfSessao getSessaoAtual() {
        return sessao;
    }

    @Override
    public void efetuarLogIn() {
        sessao.setUsuario(new UsuarioAnonimo());
    }

    @Override
    public void efetuarLogOut() {
        sessao.setUsuario(new UsuarioAnonimo());
    }

    @Override
    public void logarComoRoot() {
        if (SBCore.getEstadoAPP().equals(SBCore.ESTADO_APP.DESENVOLVIMENTO)) {
            sessao.setUsuario(new UsuarioSistemaRoot());
        } else {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "a função logar como root só é permitida no modo Desenvolvimento", null);
        }
    }

    @Override
    public void logarComoAnonimo() {
        efetuarLogOut();
    }

}
