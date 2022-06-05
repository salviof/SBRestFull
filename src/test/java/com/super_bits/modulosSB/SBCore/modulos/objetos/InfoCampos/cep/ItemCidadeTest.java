/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.testes.TestesCore;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class ItemCidadeTest extends TestesCore {

    public ItemCidadeTest() {
    }

    @Test
    public void testeGetEstado() {

        ItemCidade cidade = new ItemCidade();
        List<Integer> teste = new ArrayList();
        for (int i = 0; i < 10000; i++) {
            teste.add(i);
        }
        //   cidade.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.LC_UNIDADE_FEDERATIVA);
        teste.parallelStream().forEach(i -> {
            cidade.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.LC_UNIDADE_FEDERATIVA);
        });

        ItemBairro bairro = new ItemBairro();
        bairro.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.LC_CIDADE);
    }

}
