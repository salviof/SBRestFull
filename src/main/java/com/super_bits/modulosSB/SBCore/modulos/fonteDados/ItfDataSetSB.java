/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.fonteDados;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public interface ItfDataSetSB<T> {

    public ItfCampoInstanciado getCampoInstanciadoByNome(String pNomeCampo);

    public void selecionarPorFabrica(Class<? extends ItfFabrica> pFabrica);

    public void carregarDados();

    public int getMaximoRegistros();

    public void isSelecionarPrimeiroAutomaticamente();

    public void proximo();

    public void anterior();

    public Map<String, Object> getFiltros();

    public void adicionarFiltro(String pNomePArametro, Object pValor);

    public int getUltimoIdEncontrado();

    public List<T> metodoAutoCompletar(String parametro);

    public boolean isModoLasy();

    public void setRegistroSelecionado(int pIdRegistro);

    public void setRegistroSelecionadao(String pNomeRegistro);

    public void setRegistroSelecionado(FabTipoAtributoObjeto pTipocampo, String pRegistro);

    public void atualizar();

}
