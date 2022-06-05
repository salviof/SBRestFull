/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.modulos.tratamentoErros.ItfInfoErroSB;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfRelatorioTestes {

    public List<ItfInfoErroSB> executarTestesAcoes();

    public List<ItfInfoErroSB> executarTestesBanco();

    public List<ItfInfoErroSB> executarTestesBancoAcoes();

    public void exibirRelatorioCompleto();

    public void exibirRelatorioBanco();

    public void exibirRelatorioAcoes();

}
