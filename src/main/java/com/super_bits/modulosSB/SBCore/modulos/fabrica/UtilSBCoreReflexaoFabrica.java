/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.fabrica;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TipoAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author sfurbino
 */
public abstract class UtilSBCoreReflexaoFabrica {

    public static Object getObjetoByString(ItfFabrica fabrica, String pString) {

        return getObjetoByString(fabrica.getClass(), pString);

    }

    public static Object getObjetoByString(Class<? extends ItfFabrica> classeFabrica, String pString) {

        try {
            for (ItfFabrica enumFab : classeFabrica.getEnumConstants()) {
                if (enumFab.toString().equals(pString)) {
                    return enumFab.getRegistro();
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo objeto via fabrica de " + classeFabrica + " -" + pString, t);
        }
        return null;

    }

    /**
     *
     * Retorna todos os registros de uma fábrica
     *
     * @param fabrica Fabrica de referencia
     * @return Todos os registros criados pela fabrica
     */
    public static List getListaTodosRegistrosDaFabrica(Class<? extends ItfFabrica> fabrica) {
        List todos = new ArrayList<>();
        for (ItfFabrica fb : fabrica.getEnumConstants()) {
            todos.add(fb.getRegistro());
        }
        return todos;
    }

    public static ItfFabrica getEnumDoObjetoFabrica(Class<? extends ItfFabrica> fabrica, ItfBeanSimplesSomenteLeitura pObjetoReferencia) {
        try {
            List todos = new ArrayList<>();

            if (fabrica.getSimpleName().equals(FabTipoAtributoObjeto.class.getSimpleName())) {
                TipoAtributoObjetoSB tipoAtributo = (TipoAtributoObjetoSB) pObjetoReferencia;
                for (FabTipoAtributoObjeto tipo : FabTipoAtributoObjeto.values()) {
                    if (tipo.equals(tipoAtributo.getTipoCampo())) {
                        return tipo;
                    }
                }
            }
            for (ItfFabrica fb : fabrica.getEnumConstants()) {
                if (((ItfBeanSimplesSomenteLeitura) fb.getRegistro()).getId() == pObjetoReferencia.getId()) {
                    return fb;
                }
            }
            throw new UnsupportedOperationException("Objeto não encontrado na fabrica");
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando encontrar objeto em Fabrica de Objetos", t);
            return null;
        }

    }

    /**
     *
     * Retorna todos os registros de uma fábrica
     *
     * @param fabrica Fabrica de referencia
     * @return Todos os registros criados pela fabrica
     */
    public static List<String> getListaStringsDaFabrica(Class<? extends ItfFabrica> fabrica) {
        List<String> todos = new ArrayList<>();
        for (ItfFabrica fb : fabrica.getEnumConstants()) {
            todos.add(fb.toString());
        }
        return todos;
    }

    /**
     *
     * Retorna todos os registros de uma fábrica
     *
     * @param fabrica Fabrica de referencia
     * @return Todos os registros criados pela fabrica
     */
    public static List<Integer> getListaIntegerDaFabrica(Class<? extends ItfFabrica> fabrica) {
        List<Enum> lista = (List) Arrays.asList(fabrica.getEnumConstants());
        List<Integer> todos = new ArrayList<>();
        for (Enum enunciado : lista) {
            todos.add(enunciado.ordinal());

        }

        return todos;
    }

    public static ItfFabrica getFabricaPorOrdinal(Class<? extends ItfFabrica> fabrica, int pId) {

        try {
            List<Enum> lista = (List) Arrays.asList(fabrica.getEnumConstants());

            for (Enum enunciado : lista) {
                if (enunciado.ordinal() == pId) {
                    return (ItfFabrica) enunciado;
                }

            }
            throw new UnsupportedOperationException("Nenhum enunciado com o ordinal " + pId + " foi encontrado");
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo fabrica por ordinal", t);
        }
        return null;

    }

    public static <T> T getAnotacaoDoEnum(ItfFabrica pEnumFabrica, Class pAnotacao) {
        try {
            Field campo = pEnumFabrica.getClass().getField(pEnumFabrica.toString());
            return (T) campo.getAnnotation(pAnotacao);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo anotação " + pAnotacao + " em enum " + pEnumFabrica, t);
            return null;
        }

    }
}
