/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.listagemItem.ItflistagemItemEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author desenvolvedor
 */
public class CampoInstanciadoSubItens implements ItfCampoIsntanciadoSubItens {

    private final Class classeItemListagem;
    private final ItfCampoInstanciado campoInstanciadoVinculado;
    private final ItflistagemItemEditavel<ItfBeanSimples> subItens;

    public CampoInstanciadoSubItens(ItfCampoInstanciado pCampoVinculado) {
        try {
            if (pCampoVinculado == null) {
                throw new UnsupportedOperationException("Erro o campo vinculado a lista de de itens não pode ser nulo");
            }
            campoInstanciadoVinculado = pCampoVinculado;

            subItens = SBCore.getCentralFonteDeDados().getClasseListaRegistrosEditavel().getConstructor(ItfCampoInstanciado.class).newInstance(pCampoVinculado);
            classeItemListagem = campoInstanciadoVinculado.getPropriedadesRefexao().getClasseDeclaracaoAtributo();
        } catch (UnsupportedOperationException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro instanciando listagem de itens SubForm", t);
            throw new UnsupportedOperationException("Não foi possível instanciar uma sublista de entidade");
        }
    }

    @Override
    public ItflistagemItemEditavel<ItfBeanSimples> getSubItens() {
        return subItens;
    }

}
