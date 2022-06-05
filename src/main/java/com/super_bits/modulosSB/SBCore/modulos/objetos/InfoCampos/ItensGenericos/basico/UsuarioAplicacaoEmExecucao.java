/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocalPostagem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.ItfBeanComIcone;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemNormal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(plural = "Usuários", tags = {"O Sistema"})
public class UsuarioAplicacaoEmExecucao extends ItemNormal implements ItfUsuario, ItfBeanComIcone, Serializable {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;

    @Override
    public String getEmail() {
        return "sistema@coletivojava.com.br";
    }

    @Override
    public String getSenha() {
        return null;
    }

    @Override
    public String getTipoUsuario() {
        return "APLICACAO";
    }

    @Override
    public ItfGrupoUsuario getGrupo() {
        return new GrupoUsuariosDoSistema();
    }

    @Override
    public void setGrupo(ItfGrupoUsuario grupo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItfGrupoUsuario> getGruposAdicionais() {
        return new ArrayList<>();
    }

    @Override
    public Date getDataCadastro() {
        return new Date();
    }

    @Override
    public String getApelido() {
        return SBCore.getGrupoProjeto() + "-" + SBCore.getNomeProjeto();
    }

    @Override
    public ItfLocalPostagem getLocalizacao() {
        return null;
    }

    @Override
    public String getComplemento() {
        return null;
    }

    @Override
    public void setComplemento(String pcomplemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void instanciarNovoEndereco() {

    }

    @Override
    public String getTelefone() {
        return null;
    }

    @Override
    public void setLocalizacao(ItfLocal pLocal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIcone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getXhtmlVisaoMobile() {
        return super.getXhtmlVisaoMobile(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getXhtmlVisao(int numeroColunas) {
        return super.getXhtmlVisao(numeroColunas); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getClassePontoIdentificador() {
        return super.getClassePontoIdentificador(); //To change body of generated methods, choose Tools | Templates.
    }

}
