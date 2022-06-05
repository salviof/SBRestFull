/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeituraInstanciado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreListas {

    public static String getValoresSeparadosPorVirgula(List<String> valores) {
        return getValoresSeparadoPorCaracter(valores, ",");
    }

    public static <T> Iterable<T> iteratorToIterable(Iterator<T> iterator) {
        return () -> iterator;
    }

    public static String getValoresSeparadosPorVirgulaInt(List<Integer> valores) {
        return getValoresSeparadoPorCaracter(valores, ",");
    }

    public static String getValoresSeparadosPorPontoVirgula(List<String> valores) {
        return getValoresSeparadoPorCaracter(valores, ";");
    }

    public static String getAtributoSeparadosPorPontoVirgula(List<? extends ItfBeanSimples> pItens, String pAtributo) {
        List<String> lista = new ArrayList();
        pItens.stream().filter(item -> UtilSBCoreStringValidador.isNAO_NuloNemBranco(item.getCampoInstanciadoByNomeOuAnotacao(pAtributo).getValor().toString()))
                .forEach(item -> item.getCampoInstanciadoByNomeOuAnotacao(pAtributo).getValor());

        return getValoresSeparadoPorCaracter(lista, ";");
    }

    public static List<String> getListaStringAtributoObjeto(List<?> pLista, String pAtributo) {
        List<String> resposta = new ArrayList();
        try {

            if (pLista == null) {
                return resposta;
            }
            for (Object obj : pLista) {
                ItfCampoInstanciado campoinstanciado = (((ItfBeanSimplesSomenteLeituraInstanciado) obj).getCampoInstanciadoByNomeOuAnotacao(pAtributo));
                if (!campoinstanciado.isCampoNaoInstanciado()) {
                    Object valor = campoinstanciado.getValor();
                    if (valor != null) {
                        resposta.add(valor.toString());
                    }
                }
            }
            return resposta;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo String de atributo em lista de objeto", t);
            return resposta;
        }
    }

    public static String getValoresSeparadoPorCaracter(List valores, String pValor) {
        String resposta = "";
        if (valores == null || valores.isEmpty()) {
            return "";
        }
        for (Object v : valores) {
            if (resposta.isEmpty()) {
                resposta += v.toString();
            } else {
                resposta += "," + v.toString();
            }
        }
        return resposta;
    }

    public static boolean isNuloOuVazio(List pLista) {
        return (pLista == null || pLista.isEmpty());
    }

    public static <T> List<T> gerarComoLista(T... plista) {
        List<T> listaGerada = new ArrayList<>();
        try {
            if (plista != null) {
                listaGerada.addAll(Arrays.asList(plista));
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando lista de objeto", t);
        }
        return listaGerada;
    }

}
