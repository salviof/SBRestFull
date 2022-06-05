/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.permissaoPadrao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ConfigPermissaoSBCoreAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAplicacaoEmExecucao;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author SalvioF
 */
public abstract class ConfigPermissaoPadraoEmMemoria extends ConfigPermissaoSBCoreAbstrato {

    private final List<ItfPermissao> permissoes = new ArrayList<>();
    private final List<ItfUsuario> usuarios = new ArrayList<>();

    public ConfigPermissaoPadraoEmMemoria(Class[] pClassesControllers) {
        super(pClassesControllers);
    }

    @Override
    public List<ItfPermissao> configuraPermissoes() {
        if (permissoes.isEmpty()) {
            MapaAcoesSistema.getListaTodasAcoes().forEach(ac -> {
                permissoes.add(new PermissaoSBTransient(ac));

            });
        }
        return permissoes;

    }

    @Override
    public List<ItfUsuario> configuraUsuarios() {
        try {
            if (SBCore.isEmModoProducao()) {
                throw new UnsupportedOperationException("A classe " + ConfigPermissaoPadraoEmMemoria.class.getSimpleName() + "Não oferece segurança no nível de permissões para execução das ações");
            }
            if (usuarios.isEmpty()) {
                usuarios.add(new UsuarioAnonimo());
                usuarios.add(new UsuarioSistemaRoot());
                usuarios.add(new UsuarioAplicacaoEmExecucao());
            }
        } catch (Throwable pErroJava) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Atenção você subiu o serviço de permissões simples do Framework, isto está certo?", pErroJava);
        }
        return usuarios;
    }

    @Override
    public void atualizarInformacoesDePermissoesDoSistema() {

    }

    @Override
    public boolean isAcaoPermitidaUsuarioLogado(ItfAcaoDoSistema acao) {
        if (acao.isPrecisaPermissao()) {
            return SBCore.getUsuarioLogado().equals(new UsuarioSistemaRoot());
        }
        return true;
    }

    @Override
    public boolean isAcaoPermitidaUsuario(ItfUsuario pUsuario, ItfAcaoDoSistema acao) {
        if (acao.isPrecisaPermissao()) {
            return pUsuario.equals(new UsuarioSistemaRoot());
        }
        return true;
    }

    @Override
    public boolean isPermitidoUsuario(ItfUsuario pUsuario, ItfPermissao pPermissao) {
        if (pPermissao.getAcao().isPrecisaPermissao()) {
            return pUsuario.equals(new UsuarioSistemaRoot());
        }
        return true;
    }

}
