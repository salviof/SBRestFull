/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.centralDados;

import com.super_bits.modulosSB.SBCore.modulos.fonteDados.FabTipoSelecaoRegistro;
import com.super_bits.modulosSB.SBCore.modulos.fonteDados.ItfTokenAcessoDados;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author SalvioF
 */
public interface ItfCentralDados {

    public List<?> selecaoRegistros(ItfTokenAcessoDados pEM, String pSQL, String pPQL, Integer maximo, Class tipoRegisto, FabTipoSelecaoRegistro pTipoSelecao, Object... parametros);

    public <T> T getRegistroByID(ItfTokenAcessoDados pToken, Class<T> pClasse, int id);

    public long getQuantidadeRegistros(ItfTokenAcessoDados pToken, Class pClasseObjeto);

    public ItfTokenAcessoDados getAcessoDadosDoContexto();

    public EntityManager gerarNovoEntityManagerPadrao();

}
