/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreCriptrografia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfCentralPermissoes;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 *
 *
 *
 * @author Salvio
 */
public abstract class ConfigPermissaoSBCoreAbstrato implements ItfCentralPermissoes {

    private final Class[] classesControllers;

    protected static final Map<Integer, String> ACOES_NOME_UNICO_BY_HASH_METODO = new HashMap<>();
    private static final Map<Integer, Method> METODOS_BY_HASH_METODO = new HashMap<>();
    private static final Map<String, Method> METODO_BY_ACAO = new HashMap<>();

    public static List<ItfAcaoDoSistema> getAcoesDoSistema() {
        throw new UnsupportedOperationException("Get Açoes do Sistema ainda não foi implementado");
    }

    public static List<ItfAcaoDoSistema> getAcoesDoModulo(Class<? extends ControllerAppAbstratoSBCore> modulo) {
        throw new UnsupportedOperationException("Get Açoes do modulo ainda não foi implementado");

    }

    private boolean confereSenha(String senhaEnviada, String senhaArmazenada) {
        if (senhaArmazenada == null || senhaEnviada == null) {
            return false;
        }
        if (senhaArmazenada.length() > 40) {
            return UtilSBCoreCriptrografia.checarCriptografiaTextoSimetricoSaltAleatorio(senhaEnviada, senhaArmazenada);
        } else {
            return senhaArmazenada.equals(senhaEnviada);
        }
    }

    @Override
    public void logarEmailESenha(String pEmail, String pSenha) {
        ItfUsuario usuarioEncontrado = SBCore.getCentralPermissao().getUsuarioByEmail(pEmail);
        EntityManager em = null;
        if (usuarioEncontrado != null) {

            if (confereSenha(pSenha, usuarioEncontrado.getSenha())) {
                try {
                    if (usuarioEncontrado.getGrupo() == null || !usuarioEncontrado.getGrupo().isAtivo()) {
                        SBCore.enviarMensagemUsuario("O Grupo de usuário está desativado", FabMensagens.ALERTA);
                        return;
                    }

                    if (!usuarioEncontrado.isAtivo()) {
                        SBCore.enviarMensagemUsuario("Atenção, O Usuário " + usuarioEncontrado.getNome() + "está Desativado", FabMensagens.ALERTA);
                        return;
                    }

                    SBCore.getControleDeSessao().getSessaoAtual().setUsuario(usuarioEncontrado);
                    SBCore.enviarAvisoAoUsuario("Bem vindo " + usuarioEncontrado.getNome());

                    return;
                } catch (Throwable t) {

                } finally {

                }

            }
        }
        // Verificando se o usuário é um usuário adminstrador
        UsuarioSistemaRoot userSystem = new UsuarioSistemaRoot();
        if (pEmail.equals(userSystem.getEmail()) & pSenha.equals(userSystem.getSenha())) {
            SBCore.getControleDeSessao().getSessaoAtual().setUsuario(userSystem);
            SBCore.enviarAvisoAoUsuario("Você está logado como root");
            return;
        }

        SBCore.enviarMensagemUsuario("Autenticação negada", FabMensagens.ALERTA);
    }

    protected ItfUsuario getUsuarioNativoByEmail(String pEmail) {
        if (pEmail.equals(new UsuarioSistemaRoot().getEmail())) {
            return new UsuarioSistemaRoot();
        }
        if (pEmail.equals(new UsuarioAnonimo().getEmail())) {
            return new UsuarioAnonimo();
        }

        return null;
    }

    @Override
    public ItfUsuario getUsuarioByEmail(String pEmail) {
        ItfUsuario usuarioNativo = getUsuarioNativoByEmail(pEmail);
        if (usuarioNativo != null) {
            return usuarioNativo;
        }
        List<ItfUsuario> usuarios = configuraUsuarios();
        for (ItfUsuario usr : usuarios) {
            if (usr.getEmail().equals(pEmail)) {
                return usr;
            }
        }
        return null;
    }

    /**
     *
     * Retorna a Ação do sistema criada apartir de seu factury (sem ligação com
     * o banco de dados)
     *
     * @param pMetodo
     * @return
     */
    public ItfAcaoDoSistema getAcaoByMetodo(Method pMetodo) {
        return MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(ACOES_NOME_UNICO_BY_HASH_METODO.get(UtilSBController.gerarIDMetodoAcaoDoSistema(pMetodo)));
    }

    @Override
    public Method getMetodoByAcao(ItfAcaoDoSistema pAcao) {

        return METODO_BY_ACAO.get(pAcao.getNomeUnico());

    }

    /**
     *
     * Encontra o Método de ação que contenha a string no nome
     *
     * @param nomeMetodo
     * @return
     */
    protected Method localizarLikeNomeMetodo(String nomeMetodo) {
        Method metodoEncontrado = null;

        for (Method metodo : METODOS_BY_HASH_METODO.values()) {
            if (metodo.getName().contains(nomeMetodo)) {
                metodoEncontrado = metodo;
            }
        }

        return metodoEncontrado;

    }

    /**
     *
     * Percorre todos as Classes do tipo controllers, e cadastra os métodos
     *
     * @param pClassesControllers
     */
    public ConfigPermissaoSBCoreAbstrato(Class[] pClassesControllers) {
        if (pClassesControllers == null) {
            pClassesControllers = new Class[0];
            if (SBCore.isEmModoDesenvolvimento()) {
                SBCore.getCentralDeEventos().registrarLogDeEvento(FabMensagens.AVISO, "Nenhuma Classes com métodos controller foi cadastrada");
            }
        }
        classesControllers = pClassesControllers;

        try {
            System.out.println("Ajustando configurações de serviços");
            if (pClassesControllers == null) {
                System.out.println("Nenhuma Classe de Controller foi Cadastrada no sistema");
                return;
            }
            System.out.println("As seguinte classes de serviço foram encontradas:" + Arrays.toString(pClassesControllers));

            for (Class classe : pClassesControllers) {
                try {
                    if (classe == null) {
                        throw new UnsupportedOperationException("Uma das classes Cadastradas no sistema como classe de controller é um null!!, verifique a declaração no core");
                    }
                    Method[] metodos = classe.getDeclaredMethods();
                    ACOES_NOME_UNICO_BY_HASH_METODO.clear();
                    for (Method metodo : metodos) {

                        ItfAcaoController acaovinculoMetodo = null;
                        try {
                            if (metodo.getDeclaredAnnotations().length > 0) {
                                acaovinculoMetodo = UtilSBController.getAcaoByMetodo(metodo, true);
                            }
                        } catch (Throwable t) {

                            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possível vincular uma ação do tipo controller via anotação para o método:" + metodo.getName(), t);
                        }
                        //   if (classeAcao.isAssignableFrom(ItfAcaoController.class) == false) {
                        //       throw new UnsupportedOperationException("A ação " + acaovinculoMetodo.getNomeAcao() + " não é do tipo controller e foi vinculada ao método:" + metodo.getName() + " Na classe " + metodo.getDeclaringClass().getSimpleName());
                        // }
                        if (acaovinculoMetodo != null) {
                            ACOES_NOME_UNICO_BY_HASH_METODO.put(UtilSBController.gerarIDMetodoAcaoDoSistema(metodo), acaovinculoMetodo.getNomeUnico());
                            METODO_BY_ACAO.put(acaovinculoMetodo.getNomeUnico(), metodo);
                            METODOS_BY_HASH_METODO.put(UtilSBController.gerarIDMetodoAcaoDoSistema(metodo), metodo);
                        }

                    }
                } catch (Throwable t) {
                    String nomeclasse = "Classe nula";
                    if (classe != null) {
                        nomeclasse = classe.getSimpleName();
                    }
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando metodos de ação para classe de serviço: " + nomeclasse, t);
                }
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando Vinculo de ações do sistema aos metodos", t);
        }

    }

    @Override
    public Class[] getClassesController() {
        return classesControllers;
    }

    protected Method MT(Class pClasse, String pNomeMetodo) {
        Method[] metodos = pClasse.getMethods();

        for (Method mt : metodos) {
            if (mt.getName().equals(pNomeMetodo)) {
                return mt;
            }
        }
        SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro procurando " + pNomeMetodo + " na classe " + pClasse, null);

        return null;
    }

    public static Method getMetodoByAcaoController(ItfAcaoController pAcaoController) {
        return METODO_BY_ACAO.get(pAcaoController.getNomeUnico());
    }

}
