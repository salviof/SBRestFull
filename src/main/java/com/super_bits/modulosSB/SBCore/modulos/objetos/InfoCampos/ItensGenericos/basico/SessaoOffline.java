/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico;

import br.org.coletivojava.erp.comunicacao.transporte.ERPTransporteComunicacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ItfMenusDeSessao;

import com.super_bits.modulosSB.SBCore.modulos.view.menu.MenusDaSessao;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfTelaUsuario;

/**
 *
 * @author Salvio
 */
public class SessaoOffline implements ItfSessao {

    private int idSessao;
    private ItfUsuario usuarioLogado;
    private final Date dataInicial;
    private Date dataFinal;
    private final List<ItfPermissao> acoesRealizadas;
    private final Map<String, ItfAcaoDoSistema> acoesPermitidas;
    private final Map<String, ItfAcaoDoSistema> acoesNegadas;

    protected String pastaTempDeSessao;

    private ItfMenusDeSessao menusDaSessao;

    @Override
    public ItfMenusDeSessao getMenusDaSessao() {
        return menusDaSessao;
    }

    public void setMenusDaSessao(ItfMenusDeSessao menusDaSessao) {
        this.menusDaSessao = menusDaSessao;
    }

    @Override
    public String getPastaTempDeSessao() {
        String caminho = SBCore.getCaminhoGrupoProjetoSource() + "/modelRegras/target/" + String.valueOf(SBCore.getControleDeSessao().getSessaoAtual().getUsuario().getEmail().hashCode());

        File pastaTemo = new File(caminho + "/");
        pastaTemo.mkdirs();

        return caminho;
    }

    public SessaoOffline() {
        this.acoesRealizadas = new ArrayList();
        this.usuarioLogado = new UsuarioAnonimo();
        this.dataInicial = new Date();
        acoesPermitidas = new HashMap<>();
        acoesNegadas = new HashMap<>();

    }

    @Override
    public ItfUsuario getUsuario() {

        return usuarioLogado;
    }

    @Override
    public Date getInicio() {
        return dataInicial;
    }

    @Override
    public Date getFim() {
        return dataFinal;
    }

    @Override
    public List<ItfPermissao> getAcoesRealizadas() {
        return acoesRealizadas;
    }

    @Override
    public void setUsuario(ItfUsuario pUsuario) {

        usuarioLogado = pUsuario;
        menusDaSessao = (MenusDaSessao) SBCore.getCentralPermissao().definirMenu(pUsuario.getGrupo());

    }

    @Override
    public boolean isIdentificado() {
        if (usuarioLogado == null || usuarioLogado.equals(new UsuarioAnonimo())) {

            return false;
        }
        if (usuarioLogado.getId() == 0) {

            return false;
        }
        return true;
    }

    @Override
    public ItfTelaUsuario getTelaUsuario() {
        throw new UnsupportedOperationException("Tipo view deste tipo de sessão não foi desenvolvido"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTipoViewDefinido() {
        throw new UnsupportedOperationException("Tipo view deste tipo de sessão não foi desenvolvido"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void encerrarSessao() {
        pastaTempDeSessao = null;
        setUsuario(new UsuarioAnonimo());
        dataFinal = new Date();
    }

    @Override
    public boolean isAcessoPermitido(ItfAcaoDoSistema pAcao) {

        if (acoesPermitidas.containsKey(pAcao.getNomeUnico())) {
            return true;
        }

        if (acoesNegadas.containsKey(pAcao.getNomeUnico())) {
            return false;
        }

        if (ControllerAppAbstratoSBCore.isAcessoPermitido(pAcao)) {
            acoesPermitidas.put(pAcao.getNomeUnico(), pAcao);
            return true;
        } else {

            acoesNegadas.put(pAcao.getNomeUnico(), pAcao);
            return false;
        }

    }

    public List<ItfComunicacao> getComunicacaoAguardandoRespostaUsuarioSesso() {
        List<ItfComunicacao> comunicacoesEncontradas = SBCore.getCentralComunicacao().getComunicacoesAguardandoRespostaDoDestinatario(getUsuario());
        return comunicacoesEncontradas;
    }

    public List<ItfComunicacao> getUltimasComuniccoesAguardandoRespostaUsuarioSesso() {
        List<ItfComunicacao> lista = new ArrayList();
        SBCore.getCentralComunicacao().getComunicacoesAguardandoRespostaDoDestinatario(getUsuario())
                .stream().limit(6).forEach(lista::add);
        return lista;
    }

    public List<ItfComunicacao> getUltimasNotificacoesMenuAguardandoRespostaUsuarioSessao() {
        List<ItfComunicacao> lista = new ArrayList();
        SBCore.getCentralComunicacao().getComunicacoesAguardandoRespostaDoDestinatario(getUsuario()).stream()
                .filter(cm -> cm
                .getTransportes().stream().filter(tp -> tp.toString().equals(ERPTransporteComunicacao.INTRANET_MENU.getRegistro().toString()))
                .findFirst().isPresent())
                .limit(6).forEach(lista::add);
        for (ItfComunicacao teste : lista) {
            teste.getTransportes().stream().forEach(tf -> {
                System.out.println(tf.toString());
            });
            System.out.println(ERPTransporteComunicacao.INTRANET_MENU.getRegistro().toString());
            System.out.println(teste.getTransportes().contains(ERPTransporteComunicacao.INTRANET_MENU.getRegistro()));
        }
        return lista;
    }

    @Override
    public int getIdSessao() {
        if (idSessao == 0) {
            idSessao = Thread.currentThread().toString().hashCode();
        }
        return idSessao;
    }

}
