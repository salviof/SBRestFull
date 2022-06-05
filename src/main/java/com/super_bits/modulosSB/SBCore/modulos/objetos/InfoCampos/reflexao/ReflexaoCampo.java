/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.reflexao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanGenerico;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author salvioF
 */
public abstract class ReflexaoCampo {

    protected abstract Object getInstancia();
    private final Field campoReflection;
    private boolean possuiMetodoPublicoAlteracao = false;
    private boolean possuiMetodoPublicoLeitura = false;
    private String nomeMetodoPublicoLeitura;
    private String nomeMetodoPublicoEscrita;
    private boolean somenteLeitura;
    private Class pClasse;

    public ReflexaoCampo(Field campoReflection) {
        this.campoReflection = campoReflection;
        defineMetodoPublico();
    }

    private void defineMetodoPublico() {

        String nomeMetodoSemPrimeiraLetra = campoReflection.getName().substring(1);
        char primeiraLetraMaiusculo = campoReflection.getName().toUpperCase().charAt(0);
        nomeMetodoPublicoEscrita = "set" + primeiraLetraMaiusculo + nomeMetodoSemPrimeiraLetra;
        nomeMetodoPublicoLeitura = "get" + primeiraLetraMaiusculo + nomeMetodoSemPrimeiraLetra;

        try {
            campoReflection.getDeclaringClass().getMethod(nomeMetodoPublicoLeitura);
            possuiMetodoPublicoLeitura = true;
        } catch (NoSuchMethodException | SecurityException ex) {
            System.out.println("METODO GET NAO ENCONTRADO" + nomeMetodoPublicoLeitura + " " + ex.getMessage());
            possuiMetodoPublicoLeitura = false;
        }

        try {
            campoReflection.getDeclaringClass().getMethod(nomeMetodoPublicoEscrita, campoReflection.getType());
            possuiMetodoPublicoAlteracao = true;
        } catch (NoSuchMethodException | SecurityException ex) {
            possuiMetodoPublicoAlteracao = false;
        }

    }

    public void setValor(Object pValor) {
        Object valor = getValorPorTipo(pValor, TIPO_PRIMITIVO.getTIPO_PRIMITIVO(campoReflection));
        if (possuiMetodoPublicoAlteracao) {
            try {
                Method metodo = getInstancia().getClass().getMethod(nomeMetodoPublicoEscrita, campoReflection.getType());
                metodo.invoke(getInstancia(), getValorPorTipo(pValor, TIPO_PRIMITIVO.getTIPO_PRIMITIVO(campoReflection)));
                return;
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ocorreu um erro ao tentar setar um valor por reflexao", ex);
            }
        }
        SBCore.enviarAvisoAoUsuario("Ouve um erro ao atribuir um valor para " + campoReflection.getName());
    }

    private Object getValorPorTipo(Object pValorEnviado, TIPO_PRIMITIVO tipoDeclaracao) {

        if (pValorEnviado == null) {
            switch (tipoDeclaracao) {
                case ENTIDADE:
                case LETRAS:
                case DATAS:
                    return null;
                case INTEIRO:
                case DECIMAL:
                    return 0;
                case BOOLEAN:
                    return false;
                default:
                    throw new AssertionError(tipoDeclaracao.name());
            }

        }

        // Conversão automatica de valor caso envie String para setar
        if (pValorEnviado.getClass().getSimpleName().equals(String.class.getSimpleName())) {

            switch (tipoDeclaracao) {
                case INTEIRO:
                    return Integer.parseInt(pValorEnviado.toString());
                case LETRAS:
                    return pValorEnviado;
                case DATAS:
                    throw new UnsupportedOperationException("String enviada para setar datas, a conversão automatica de String para datas não foi implementada");
                case BOOLEAN:
                    return pValorEnviado.toString().equals("true") || pValorEnviado.equals("1") || pValorEnviado.toString().equals("verdadeiro") || pValorEnviado.toString().equals("ok");
                case DECIMAL:
                    return Double.parseDouble(pValorEnviado.toString());
                case ENTIDADE:
                    throw new UnsupportedOperationException("String enviada para setar Objeto, a conversão automatica de String para Objeto não foi implementada");
                default:
                    throw new AssertionError(tipoDeclaracao.name());

            }
        } else {
            try {
                switch (tipoDeclaracao) {
                    case INTEIRO:
                        return (int) pValorEnviado;
                    case LETRAS:
                        return (String) pValorEnviado;
                    case DATAS:
                        return (Date) pValorEnviado;
                    case BOOLEAN:
                        return (boolean) pValorEnviado;
                    case DECIMAL:
                        return (double) pValorEnviado;
                    case ENTIDADE:
                        return (ItfBeanGenerico) pValorEnviado;
                    default:
                        throw new AssertionError(tipoDeclaracao.name());

                }
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "O Valor enviado não é compatível com o tipo declarado", t);
                return null;
            }

        }

    }

    public Object getValor() {

        if (possuiMetodoPublicoLeitura) {
            try {
                Method metodo = getInstancia().getClass().getMethod(nomeMetodoPublicoLeitura);
                return metodo.invoke(getInstancia());
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando executar metodo para obtenção de valor do ben" + nomeMetodoPublicoLeitura, ex);
                return null;
            }
        } else {
            try {
                Logger.getGlobal().info("O metodo get do campo " + campoReflection.getName() + " não foi encontrado: " + nomeMetodoPublicoLeitura);
                campoReflection.setAccessible(true);
                return campoReflection.get(getInstancia());
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo campo" + campoReflection.getName() + " em " + getInstancia().getClass().getSimpleName(), t);
            }
        }
        return null;

    }

}
