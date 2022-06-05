/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.modeloFormulario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfModeloFormularioSBFW extends ItfBeanSimples {

    String getAreaExtra1();

    String getAreaExtra2();

    String getAreaExtra3();

    String getAreaPrincipal();

    String getAreaSecundaria();

    List<String> getAreas();

    String getDescricao();

    int getId();

    String getNomeModelo();

    String getXhtmlVinculado();

    void setAreas(List<String> areas);

    void setDescricao(String descricao);

    void setId(int id);

    void setNomeModelo(String nomeModelo);

    void setXhtmlVinculado(String xhtmlVinculado);

}
