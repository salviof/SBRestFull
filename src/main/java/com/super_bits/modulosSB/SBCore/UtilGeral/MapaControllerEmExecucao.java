/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public abstract class MapaControllerEmExecucao {

    private static final Map<Integer, ItfRespostaAcaoDoSistema> RESPOSTAS_EM_EXECUCAO = new HashMap<>();

    private static int buildIDExecucao(ItfAcaoController acao) {

        String identificador = SBCore.getCentralDeSessao().getSessaoAtual().getIdSessao() + acao.getNomeUnico();
        return identificador.hashCode();
    }

    public static boolean removerResposta(ItfRespostaAcaoDoSistema resp) {
        try {
            int id = buildIDExecucao(resp.getAcaoVinculada().getComoController());
            RESPOSTAS_EM_EXECUCAO.remove(id, resp);
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro registrando regra de negocio", t);
            return false;
        }
    }

    public static boolean registrarRegra(ItfRespostaAcaoDoSistema resp) {
        try {
            int id = buildIDExecucao(resp.getAcaoVinculada().getComoController());
            RESPOSTAS_EM_EXECUCAO.put(id, resp);
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro registrando regra de negocio", t);
            return false;
        }
    }

    public static ItfRespostaAcaoDoSistema getRespostaDoContexto() {

        ItfAcaoController acao = UtilSBController.getAcaoDoContexto();
        if (acao == null) {
            return null;
        }
        int id = buildIDExecucao(acao);
        return RESPOSTAS_EM_EXECUCAO.get(id);

    }

}
