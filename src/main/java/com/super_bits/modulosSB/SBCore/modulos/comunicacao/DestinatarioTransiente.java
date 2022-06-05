/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreListas;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class DestinatarioTransiente implements ItfDestinatario {

    private final FabTipoDestinatario tipoDestinatario;
    private final ItfUsuario usuario;
    private List<ItfUsuario> usuarios;
    private ItfGrupoUsuario grupoUsuario;

    private List<ItfGrupoUsuario> gruposUsuario;

    public DestinatarioTransiente(ItfUsuario pUsuario) {
        tipoDestinatario = FabTipoDestinatario.USUARIO;
        usuario = pUsuario;
    }

    @Override
    public FabTipoDestinatario getTipoDestinatario() {
        return tipoDestinatario;
    }

    @Override
    public List<ItfUsuario> getUsuarios() {
        List<ItfUsuario> usuariosEncontrados = new ArrayList<>();
        switch (tipoDestinatario) {
            case USUARIO:
                usuariosEncontrados.add(usuario);
                break;
            case USUARIOS:
                return usuarios;

            case GRUPO:
                return (List) grupoUsuario.getUsuarios();

            case GRUPOS:
                for (ItfGrupoUsuario grupo : gruposUsuario) {
                    usuariosEncontrados.addAll((List) grupo.getUsuarios());
                }

                break;
            default:
                throw new AssertionError(tipoDestinatario.name());

        }
        return usuariosEncontrados;
    }

    @Override
    public ItfUsuario getUsuario() {
        switch (tipoDestinatario) {
            case USUARIO:
                return usuario;

            case USUARIOS:
            case GRUPO:
            case GRUPOS:

            default:
                throw new UnsupportedOperationException("O tipo " + tipoDestinatario + " não suporta o metodo getUsuario");

        }
    }

    @Override
    public ItfGrupoUsuario getGrupoUsuario() {
        switch (tipoDestinatario) {
            case GRUPO:
                return grupoUsuario;
            case USUARIO:
            case USUARIOS:
            case GRUPOS:
            default:
                throw new UnsupportedOperationException("O tipo " + tipoDestinatario + " não suporta o metodo getGrupoUsuario");

        }

    }

    @Override
    public List<ItfGrupoUsuario> getGruposUsuario() {
        switch (tipoDestinatario) {
            case GRUPOS:
                return gruposUsuario;
            case GRUPO:

            case USUARIO:
            case USUARIOS:

            default:
                throw new UnsupportedOperationException("O tipo " + tipoDestinatario + " não suporta o metodo getGruposUsuario");

        }
    }

    @Override
    public String getEmailsConcatenados() {
        switch (tipoDestinatario) {
            case USUARIO:
                return getUsuario().getEmail();

            case USUARIOS:

                return UtilSBCoreListas.getValoresSeparadosPorPontoVirgula(UtilSBCoreListas.getListaStringAtributoObjeto(getUsuarios(), "email"));

            case GRUPO:

                return getGrupoUsuario().getEmail();

            case GRUPOS:
                return UtilSBCoreListas.getValoresSeparadosPorPontoVirgula(UtilSBCoreListas.getListaStringAtributoObjeto(getGruposUsuario(), "email"));

            default:
                throw new AssertionError(tipoDestinatario.name());

        }
    }

}
