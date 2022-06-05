/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.permissaoPadrao;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.GrupoUsuariosDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SalvioF
 */
public class PermissaoSBTransient extends ItemSimples implements ItfPermissao {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nomePermissao;
    private final ItfAcaoDoSistema acao;

    public PermissaoSBTransient(ItfAcaoDoSistema pAcao) {
        acao = pAcao;
        nomePermissao = pAcao.getNomeUnico();
        id = pAcao.getNomeUnico().hashCode();
    }

    @Override
    public ItfAcaoDoSistema getAcao() {
        return acao;
    }

    @Override
    public String getDescricaoAcesso() {
        return "Permissão para: " + acao.getNomeAcao();
    }

    @Override
    public List<ItfGrupoUsuario> getGruposPermitidos() {
        return Lists.newArrayList(new GrupoUsuariosDoSistema(new UsuarioSistemaRoot()));

    }

    @Override
    public List<ItfGrupoUsuario> getGruposNegados() {
        return new ArrayList<>();
    }

    @Override
    public List<ItfGrupoUsuario> getGruposDisponiveis() {
        return new ArrayList<>();
    }

    @Override
    public List<ItfUsuario> getUsuariosPermitidos() {
        return new ArrayList<>();
    }

    @Override
    public List<ItfUsuario> getUsuariosNegados() {
        return new ArrayList<>();
    }

    @Override
    public List<ItfUsuario> getUsuariosDisponiveis() {
        return new ArrayList<>();
    }

    @Override
    public TIPO_AUTENTICACAO getTipoAutenticacao() {
        return TIPO_AUTENTICACAO.SOLICITAR_EMAIL;
    }

    @Override
    public String getNomeCurto() {
        return "Controle de acesso simples, para sistemas simples";
    }

    @Override
    public String getNome() {
        return "Controle de acesso simples, para sistemas simples";
    }

    @Override
    public String getIconeDaClasse() {
        return "Controle de acesso simples, para sistemas simples";
    }

}
