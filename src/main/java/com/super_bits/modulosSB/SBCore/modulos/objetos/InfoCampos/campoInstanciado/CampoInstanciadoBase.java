/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ItfCalculoValorLogicoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FieldComSerializacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TipoAtributoMetodosBase;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author SalvioF
 */
public abstract class CampoInstanciadoBase implements ItfCampoInstanciadoBase {

    protected final FieldComSerializacao campoReflection;

    private boolean bloquearProximaTentativaDeAlteracao = false;
    private boolean possuiValorCalculoDinamico = false;

    public CampoInstanciadoBase(Field pCampoReflection) {
        try {
            InfoCampoValorLogico valorlogico = pCampoReflection.getAnnotation(InfoCampoValorLogico.class);
            if (valorlogico != null) {
                possuiValorCalculoDinamico = true;
            }
            campoReflection = new FieldComSerializacao(pCampoReflection);
        } catch (Throwable t) {
            throw new UnsupportedOperationException("Erro configurando" + Field.class.getCanonicalName() + "  ");
        }

    }

    @Override
    public void bloquearProximaAlteracao() {
        bloquearProximaTentativaDeAlteracao = true;
    }

    protected void desbloquearProximaAlteracao() {
        bloquearProximaTentativaDeAlteracao = false;
    }

    public boolean isProximaTentativaDeAlteracaoBloqueado() {
        return bloquearProximaTentativaDeAlteracao;
    }

    protected void aplicarValor(Object pInstancia, Object pValor) {

        if (bloquearProximaTentativaDeAlteracao) {
            bloquearProximaTentativaDeAlteracao = false;
            return;
        }

        try {

            /**
             * if (indiceValorLista > 0) { List lista = (List)
             * campoReflection.getValorDesteCampoEmObjetoInstanciado(pInstancia,
             * false); if (lista == null) { return;
             *
             * }
             * if (indiceValorLista <= lista.size() - 1) {
             * lista.set(indiceValorLista, pValor); }
             *
             * } else {
             */
            pValor = TipoAtributoMetodosBase.converterValorPorTipoObjeto((ItfCampoInstanciado) this, pValor);
            if (possuiValorCalculoDinamico) {

            }
            try {
                Method metodo = getMetodoSet();
                try {
                    if (pValor == null) {
                        metodo.invoke(getObjetoDoAtributo(), pValor);
                    } else {
                        if (metodo.getParameterCount() == 1) {
                            Class tipoParametro = metodo.getParameterTypes()[0];

                            if (tipoParametro.isInstance(pValor)) {
                                metodo.invoke(getObjetoDoAtributo(), pValor);
                            } else {
                                if (tipoParametro.getSimpleName().equals(int.class.getSimpleName())
                                        || tipoParametro.getSimpleName().equals(Integer.class.getSimpleName())) {
                                    if (pValor.getClass().equals(Double.class) || pValor.getClass().equals(double.class)) {
                                        if ((Double) pValor % 1 == 0) {
                                            int valorConvertido = ((Double) pValor).intValue();
                                            metodo.invoke(getObjetoDoAtributo(), valorConvertido);
                                        } else {
                                            throw new UnsupportedOperationException("O Atributo do campo foi declarado como inteiro, e não recebe Double");
                                        }

                                    } else {

                                        if (pValor.getClass().getSimpleName().equals(String.class.getSimpleName())) {
                                            metodo.invoke(getObjetoDoAtributo(), Integer.parseInt(pValor.toString()));
                                        } else {

                                            metodo.invoke(getObjetoDoAtributo(), (int) pValor);
                                        }

                                    }
                                    return;
                                }
                                if (pValor instanceof Object[]) {
                                    if (tipoParametro.getSimpleName().equals("List")) {
                                        List lista = new ArrayList();
                                        Object[] itens = (Object[]) pValor;
                                        lista.addAll(Arrays.asList(itens));
                                        metodo.invoke(getObjetoDoAtributo(), lista);
                                        return;
                                    }
                                }
                                metodo.invoke(getObjetoDoAtributo(), pValor);
                            }
                        }
                    }

                } catch (IllegalArgumentException t) {
                    String tipoClasse = "indefinido";
                    if (pValor != null) {
                        tipoClasse = pValor.getClass().getSimpleName();
                    }
                    String nomeparametroMetodo = "indefinido";
                    try {
                        nomeparametroMetodo = metodo.getParameterTypes()[0].getClass().getSimpleName();
                    } catch (Throwable tt) {

                    }

                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Tentativa de chamar metodo Set com parametro incompativel: " + metodo.getName() + " o tipo do parametro enviado foi " + tipoClasse + " já no método, o parametro é do tipo: " + nomeparametroMetodo, t);

                } catch (IllegalAccessException | InvocationTargetException t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro invocando método via reflexão", t);
                }

            } catch (NoSuchMethodException t) {
                try {
                    campoReflection.setValorDesteCampoEmObjetoInstanciado(getObjetoDoAtributo(), pValor, true);

                } catch (Throwable erroConfigurandoValores) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando valor do método Set", erroConfigurandoValores);
                }
            }

        } catch (Throwable t) {
            String nomeCampoErro = "Indeterminado";
            try {
                nomeCampoErro = campoReflection.getNomeDeclaracao();
            } catch (Throwable tt) {

            }
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro aplicando valor do campo via item Generico en campo:" + nomeCampoErro, t);
        }

    }

    protected Object obterValor(Object pInstancia) {
        String infomensagemErro = "";
        try {

            /*  if (indiceValorLista > 0) {

                List lista = (List) campoReflection.getValorDesteCampoEmObjetoInstanciado(pInstancia, true);
                if (lista == null) {
                    return null;

                }
                if (indiceValorLista <= lista.size() - 1) {
                    return lista.get(indiceValorLista);
                } else {
                    return null;
                }

            } else {
             */
            infomensagemErro = campoReflection.getNomeDeclaracao();
            try {
                if (campoReflection.isPossuiValorDinamicoCalculado()) {
                    ItfCampoInstanciado prCampoinstanciado = (ItfCampoInstanciado) this;
                    ItfCalculoValorLogicoAtributoObjeto calculo = prCampoinstanciado.getValorLogicaEstrategia();

                    return calculo.getValor();
                }
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo implementação de valor dinamico calculado" + campoReflection.getNomeDeclaracao(), t);
            }
            try {

                Method metodo = getMetodoGet();

                if (metodo != null) {
                    return metodo.invoke(pInstancia);
                } else {
                    return campoReflection.getValorDesteCampoEmObjetoInstanciado(pInstancia, true);
                }

            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException t) {

                return campoReflection.getValorDesteCampoEmObjetoInstanciado(pInstancia, true);

            }

        } catch (Throwable ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, ex.getMessage() + "Erro obtendo valor do item Generico Instanciado" + infomensagemErro + " ", ex);
            System.out.println(ex.getCause());
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Method getMetodoGet() throws NoSuchMethodException {
        String nomeMetodo = "impossivel definir o nome do método";
        String nomeCampo = campoReflection.getNomeDeclaracao();
        nomeMetodo = nomeCampo.substring(1);
        char primeiraLetra = campoReflection.getNomeDeclaracao().toUpperCase().charAt(0);
        try {
            if (campoReflection.isVerdadeiroOuFalso()) {
                nomeMetodo = "is" + Character.toString(primeiraLetra) + nomeMetodo;
            } else {

                nomeMetodo = "get" + Character.toString(primeiraLetra) + nomeMetodo;
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro identificando o método instanciado", t);
        }
        return getObjetoDoAtributo().getClass().getMethod(nomeMetodo);

    }

    @Override
    public Method getMetodoSet() throws NoSuchMethodException {
        String nomeMetodo = "impossivel definir o nome do método";
        try {
            String nomeCampo = campoReflection.getNomeDeclaracao();
            nomeMetodo = nomeCampo.substring(1);
            char primeiraLetra = campoReflection.getNomeDeclaracao().toUpperCase().charAt(0);
            nomeMetodo = "set" + Character.toString(primeiraLetra) + nomeMetodo;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro identificando o método instanciado", t);
        }
        return getObjetoDoAtributo().getClass().getMethod(nomeMetodo, campoReflection.getClasseDeclarada());

    }

    @Override
    public ItfBeanSimples getObjetoDoAtributo() {
        return (ItfBeanSimples) getParent();
    }

    /**
     * Será subistituido por isUmValorLogico
     *
     * @return @deprecated * Será subistituido por isUmValorLogico
     */
    @Deprecated
    public boolean isPossuiValorCalculoDinamico() {
        return possuiValorCalculoDinamico;
    }

    @Override
    public ItfBeanSimples getValorComoItemSimples() {
        return ItfCampoInstanciadoBase.super.getValorComoItemSimples(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getValorComoBoolean() {
        return ItfCampoInstanciadoBase.super.getValorComoBoolean(); //To change body of generated methods, choose Tools | Templates.
    }

}
