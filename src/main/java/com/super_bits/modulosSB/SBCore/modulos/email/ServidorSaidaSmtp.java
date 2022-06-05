/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.email;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(plural = "Servidores SMTP", tags = "Servidor SMTP")
public class ServidorSaidaSmtp extends ItemSimples implements ItfServidordisparoEmail {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;
    private String enderecoServidor;
    private int porta;
    private String usuario;
    private String fromEmail;
    private String senha;
    private boolean usarSSL;
    private boolean usarTSL;

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getId() {
        return super.getNome().hashCode();
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getEnderecoServidor() {
        return enderecoServidor;
    }

    public void setEnderecoServidor(String enderecoServidor) {
        this.enderecoServidor = enderecoServidor;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    @Override
    public String getUsuarioSMTP() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean isUsarSSLSMTP() {
        return usarSSL;
    }

    @Override
    public boolean isUsarTSLSMTP() {
        return usarTSL;
    }

    @Override
    public int getPortaSMTP() {
        return porta;
    }

    @Override
    public String getSenhaSMTP() {
        return senha;
    }

    public void setUsarSSL(boolean usarSSL) {
        this.usarSSL = usarSSL;
    }

    public void setUsarTSL(boolean usarTSL) {
        this.usarTSL = usarTSL;
    }

    @Override
    public String getSenha() {
        return getSenhaSMTP();
    }

    @Override
    public String getEmail() {
        return getUsuarioSMTP();
    }

    @Override
    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

}
