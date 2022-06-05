/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.requisito;

import com.super_bits.modulosSB.SBCore.modulos.requisito.ComentarioRequisitoEnvioNovo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
public interface ItfServiceComentariosJira {

    public ItfResposta adicionarComentario(ComentarioRequisitoEnvioNovo coment);

    public ItfResposta obterRequisitoDeAcaoDoProjeto(String acaoNomeUnico);

    public ItfResposta obterTabelaDeAcaoDoProjeto(String pNomeClasse);

}
