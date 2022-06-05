/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfValidacao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.InfoCalculo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.listas.InfoLista;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ItfCalculos;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.listas.ItfListas;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.CalculoDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaCampo;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.ListaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.AtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salvioF
 */
public class UtilSBCoreReflecaoIEstruturaEntidade {

    public static CalculoDeEntidade getCalculoEstruturaByField(Field campoReflection, AtributoObjetoSB pCampo, EstruturaCampo pEstrutura) {

        //ItfCalculos calculo = getCalculoByField(campoReflection);
        String nomeEnum = campoReflection.getName().toUpperCase();
        String tipoRetorno = campoReflection.getType().getSimpleName();

        String javaDoc = "Não especificado";
        String descricao = "Não Especificado";
        try {
            InfoCampoValorLogico valorLogico = campoReflection.getAnnotation(InfoCampoValorLogico.class);

            javaDoc = valorLogico.nomeCalculo();
            descricao = valorLogico.descricao();
        } catch (Throwable t) {

        }

        CalculoDeEntidade calculoDeEntidade = new CalculoDeEntidade(nomeEnum, descricao, tipoRetorno, pCampo, pEstrutura, javaDoc);
        return calculoDeEntidade;
    }

    public static ListaDeEntidade getListaEstruturaByField(Field pCampoReflection) {
        return null;
    }

    @Deprecated
    public static InfoLista getInfoListaByEnum(ItfListas pLista) {
        Field campo;
        try {
            campo = pLista.getClass().getField(pLista.toString());
            InfoLista InfoLista = campo.getAnnotation(InfoLista.class);
            return InfoLista;
        } catch (NoSuchFieldException | SecurityException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A  anotação de info Lista não pode ser recuperada em: " + pLista.toString(), ex);
            return null;
        }

    }

    @Deprecated
    public static InfoCalculo getAnotacaoCalulo(Field pCampo) {
        try {
            ItfCalculos calculo = getCalculoByField(pCampo);
            Field campo = calculo.getClass().getField(calculo.toString());
            return campo.getAnnotation(InfoCalculo.class);
        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(UtilSBCoreReflecaoIEstruturaEntidade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param pCampo
     * @return O enum do calculo, vinculado ao TipoAtributoObjetoSB
     * @throws UnsupportedOperationException Caso não encontre, dispara um erro
     */
    @Deprecated
    public static ItfCalculos getCalculoByField(Field pCampo) throws UnsupportedOperationException {
        try {
            ItfCalculos calculo = null;
            pCampo.setAccessible(true);
            Annotation anotacao = UtilSBCoreReflexao.getAnotacaoComEsteMetodo(pCampo.getAnnotations(), "calculo");
            Method metodoCalculo = anotacao.annotationType().getMethod("calculo");
            calculo = (ItfCalculos) metodoCalculo.invoke(anotacao);
            return calculo;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new UnsupportedOperationException("impossivel determinar o Calculo para o campo" + pCampo.getName(), ex);
        }
    }

    @Deprecated
    public static ItfListas getListaByField(Field pCampo) throws UnsupportedOperationException {
        try {
            ItfListas lista = null;
            pCampo.setAccessible(true);
            Annotation anotacao = UtilSBCoreReflexao.getAnotacaoComEsteMetodo(pCampo.getAnnotations(), "lista");
            Method metodoCalculo = anotacao.annotationType().getMethod("lista");
            lista = (ItfListas) metodoCalculo.invoke(anotacao);
            return lista;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new UnsupportedOperationException("impossivel determinar a lista para o campo" + pCampo.getName(), ex);
        }
    }

}
