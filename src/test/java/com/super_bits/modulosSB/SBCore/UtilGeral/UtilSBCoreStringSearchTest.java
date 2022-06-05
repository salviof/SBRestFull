/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.localizacao.FabCidadesSemPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.localizacao.FabUnidadesFederativasSemPersistencia;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreStringSearchTest {

    public UtilSBCoreStringSearchTest() {
    }

    @Test
    public void testIsParecido() {

        FabCidadesSemPersistencia.getCidadesPorEstado(FabUnidadesFederativasSemPersistencia.MG).forEach(cidade -> {

            System.out.println(UtilSBCoreStringComparador.isParecido(cidade.getNome(), "belos horizonte"));
        });

    }

}
