/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fonteDados.FabTipoSelecaoRegistro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author sfurbino
 */
public abstract class UtilSBCoreValidacao {

    public static boolean validacoesBasicas(ItfCampoInstanciado pCampo, Object pValor) {

        return !Arrays.stream(FabTipoValidacaoUnitaria.values()).filter(validacao
                -> !validacao.getValidador(pCampo).isValorValido(pValor)).findFirst().isPresent();

    }

    public static String getPrimeiraInconsistenciaDeValidacao(ItfBeanSimples pObjeto) {
        Optional<ItfCampoInstanciado> cpEncontrado = pObjeto.getCamposInstanciados().stream().
                filter(cp
                        -> (!cp.validarCampo())
                ).findFirst();

        if (cpEncontrado.isPresent()) {
            return gerarMensagensValidacao(cpEncontrado.get(), cpEncontrado.get().getValor(), pObjeto.getId() == 0, true).get(0);
        } else {
            return null;
        }

    }

    public static List<String> getValidacoes(ItfBeanSimples pObjeto) {
        List<String> lista = new ArrayList<>();

        pObjeto.getCamposInstanciados().stream().filter(cp -> (!cp.validarCampo()))
                .forEach(cp -> lista.add(gerarMensagensValidacao(cp, cp.getValor(), pObjeto.getId() == 0, true).get(0)));
        return lista;

    }

    public static boolean isTodosCamposValidadados(ItfBeanSimples pObjeto) {
        try {

            Optional<ItfCampoInstanciado> campoInvalido = pObjeto.getCamposInstanciados().stream().filter(cp -> (!cp.validarCampo())).findFirst();
            boolean todosValidados = !(campoInvalido.isPresent());
            if (!todosValidados && !SBCore.isEmModoProducao()) {
                //algoritimo declarativo para fins de debug, pois não só de logs vive um Jedi.
                //Todo retirar quando log estiver alcançar o estado da arte
                ItfCampoInstanciado cpNaoValidado = pObjeto.getCamposInstanciados().stream().filter(cp -> (!cp.validarCampo())).findFirst().get();
                cpNaoValidado.getLabel();
            }

            return todosValidados;
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean isValorUnico(ItfCampoInstanciado pCampo, Object pValor) {
        try {
            ItfCampoInstanciado cpId = pCampo.getObjetoDoAtributo().getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.ID);
            if (cpId == null) {
                cpId = pCampo.getObjetoDoAtributo().getCampoInstanciadoByNomeOuAnotacao("id");
            }
            List lista = SBCore.getCentralDados().selecaoRegistros(null, null,
                    "from " + pCampo.getNomeClasseOrigemAtributo() + " where " + pCampo.getNome() + "= ?0 and " + cpId.getNomeCamponaClasse() + " != ?1", 1,
                    MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pCampo.getNomeClasseOrigemAtributo()), FabTipoSelecaoRegistro.NOMECURTO, pValor, cpId.getValor());
            if (lista == null) {
                return true;
            }
            return lista.isEmpty();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro validando valor unico em " + pCampo.getNomeClasseOrigemAtributo() + "." + pCampo.getNome(), t);
            return true;
        }
    }

    public static List<String> gerarMensagensValidacao(ItfCampoInstanciado pCampo, Object pValor, boolean pUmaNovaEntidade, boolean imporMensagemPadrao) {
        List<String> resp = new ArrayList<>();
        try {

            Arrays.stream(FabTipoValidacaoUnitaria.values()).forEach(validacao -> {
                String mensagem = validacao.getValidador(pCampo).gerarMensagemErroValidacao(pValor);
                if (mensagem != null) {
                    resp.add(mensagem);
                }
            });

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro validando" + pCampo, t);
        }
        if (imporMensagemPadrao) {
            if (resp.isEmpty()) {
                resp.add("Valor inválido para " + pCampo.getLabel());
            }
        }
        return resp;

    }

}
