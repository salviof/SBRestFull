/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.Optional;
import javax.persistence.DiscriminatorColumn;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreReflexaoObjetoSimples;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreReflexaoObjeto extends UtilSBCoreReflexaoObjetoSimples {

    public static Class getClassExtraindoProxy(String pNomeClasse) {
        if (pNomeClasse.indexOf("$") <= 0) {
            return MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pNomeClasse);
        }
        String nomeClasse = UtilSBCoreStringBuscaTrecho.getStringAteEncontrarIsto(pNomeClasse, "$");
        return MapaObjetosProjetoAtual.getClasseDoObjetoByNome(nomeClasse);
    }

    public static String getClasseDiscriminatoriaPolimorfismoDeEntidade(ItfBeanSimples pEntidade) {
        String nomeColuna;
        Optional<Class> classeBaseEncontrada = UtilSBCoreReflexao.getClasseESubclassesAteClasseBaseDeEntidade(pEntidade.getClass()).stream().filter(
                classe -> classe.isAnnotationPresent(DiscriminatorColumn.class)).findFirst();
        if (classeBaseEncontrada.isPresent()) {

            Class classe = classeBaseEncontrada.get();
            DiscriminatorColumn campoDiscriminidor = (DiscriminatorColumn) classe.getAnnotation(DiscriminatorColumn.class);
            nomeColuna = campoDiscriminidor.name();
            return (String) pEntidade.getCampoInstanciadoByNomeOuAnotacao(nomeColuna).getValor();

        } else {
            return null;
        }

    }

    public static boolean classeImplementaInterface(Class pClasse, Class pInterface) {

        return UtilSBCoreReflexao.isInterfaceImplementadaNaClasse(pClasse, pInterface);

    }
}
