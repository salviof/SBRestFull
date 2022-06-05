/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.sessao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreEmail;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenRecuperacaoEmail;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ItfControleDeSessao;
import java.io.Serializable;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreComunicacao;

/**
 *
 *
 *
 * @author Salvio
 */
public abstract class ControleDeSessaoAbstratoSBCore implements ItfControleDeSessao, Serializable {

    @Override
    public void registrarAcao(ItfPermissao pAcesso) {
        getSessaoAtual().getAcoesRealizadas().add(pAcesso);
    }

    @Override
    @Deprecated
    public void logarEmailESenha(String pEmail, String pSenha) {

        SBCore.getCentralPermissao().logarEmailESenha(pEmail, pSenha);

    }

    public void logarIDFacebook() {
        throw new UnsupportedOperationException("Não suportado ainda");

    }

    protected void enviarSenhaParaEmail(String pEmail) {

        ItfUsuario usuarioEncontrado = SBCore.getCentralPermissao().getUsuarioByEmail(pEmail);

        if (usuarioEncontrado == null) {
            SBCore.enviarMensagemUsuario("O email " + pEmail + " não foi encontrado no sistema", FabMensagens.ALERTA);
        } else {

            ItfTokenRecuperacaoEmail token = SBCore.getServicoPermissao().gerarTokenRecuperacaoDeSenha(usuarioEncontrado, 10080);
            if (token == null) {
                SBCore.enviarMensagemUsuario("Erro gerando token de acesso", FabMensagens.ERRO);
                return;
            }
            if (UtilSBCoreEmail.enviarPorServidorPadrao(
                    pEmail,
                    UtilSBCoreComunicacao.getSaudacao() + ", " + usuarioEncontrado.getNome()
                    + ", utilize este token: " + token.getCodigo() + ", para recuperar sua senha",
                    "Recuperação de senha")) {
                SBCore.enviarAvisoAoUsuario("Um e-mail com a senha foi enviado para " + pEmail);
            } else {
                SBCore.enviarMensagemUsuario("Um erro ocorreu ao tentar enviar o e-mail com a senha para: " + pEmail + " entre em contato conosco para recuperar a senha", FabMensagens.ALERTA);
            }
        }
    }

}
