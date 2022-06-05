/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.gestaoObjeto;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.fonteDados.ItfNavegacaoDataSet;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfGestaoObjeto<T> extends ItfNavegacaoDataSet<T> {

    public List<T> pesquisar(String pParametro);

    public List<ItfAcaoDoSistema> getAcoesDisponiveis();

    public List<ItfAcaoDoSistema> getAcoesDeFormularioObjetoSelecionado();

    public List<ItfAcaoDoSistema> getAcoesDeFormularioNovoObjeto();

    public ItfResposta executarAcaoController(ItfAcaoController pAcaoExecucao);

    public void executarAcaoFormulario(ItfAcaoController pAcaoExecucao);

}
