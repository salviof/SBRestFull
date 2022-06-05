package com.super_bits.modulosSB.SBCore.UtilGeral;

import static com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore.RelatarErro;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.lang.reflect.ParameterizedType;

/// Toda Classe do TIPO <T> extende dessa classe, ela possui m[etodo de constructor expecificando o tipo, e expecificacao por reflexao, que
// pode ser usada em caso onde o T est[a explicito, em runtime declarar com {} (Classe "dinamica"
@Deprecated
public abstract class ClasseTipada {

    protected Class<?> classe;

    private void setClasseGenericaPorReflexao() {
        try {

            @SuppressWarnings("rawtypes")
            Class clazz = (Class) ((ParameterizedType) getClass().asSubclass(getClass()).getGenericSuperclass()).getActualTypeArguments()[0];
            classe = clazz;
            if (classe == null) {
                System.out.println("erro de definicao de classe generica");
                RelatarErro(FabErro.SOLICITAR_REPARO, "Nao foi possivel obter o tipo da classe[" + this.getClass().getName() + " ]por reflexao, Especifique explicitamente o tipo da classe no constructor, ou utilize  new classe<tipo>{}", null);

            }
        } catch (Exception e) {

            RelatarErro(FabErro.SOLICITAR_REPARO, "Nao foi possivel obter o tipo da classe[" + this.getClass().getName() + " ]por reflexao, Especifique explicitamente o tipo da classe no constructor, ou utilize  new classe<tipo>{}", null);

        }
    }

    protected ClasseTipada(Class<?> pClasse) {

        if (pClasse == null) {
            setClasseGenericaPorReflexao();
        } else {
            classe = pClasse;
        }
    }

    protected ClasseTipada() {
        System.out.println(this.getClass().getName() + " é uma classe Tipada mas foi inicializada sem setar o tipo");
        setClasseGenericaPorReflexao();

    }

    public Class<?> getTipo() {
        return classe;
    }

    protected Class<?> getClasse() {
        return classe;
    }

}
