/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.calculos;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ItfCalculoValorLogicoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoInstanciadoGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author desenvolvedor
 */
public class ValorLogicoCalculoGenerico implements ItfCalculoValorLogicoAtributoObjeto {

    private final ItfCampoInstanciado campoInst;
    private boolean cacheAtivado;

    public boolean isCacheAtivado() {
        return cacheAtivado;

    }

    public void setValorPorReflexao(Object pValor) {

        getCampoInst().setValor(pValor);

    }

    public ValorLogicoCalculoGenerico(ItfCampoInstanciado pCampo) {
        campoInst = pCampo;
    }

    public void desativarCache() {
        cacheAtivado = false;
    }

    public void ativarCache(int pSegundos) {
        cacheAtivado = true;
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(pSegundos * 1000);
                    cacheAtivado = false;
                } catch (InterruptedException ex) {
                    Logger.getLogger(ValorLogicoCalculoGenerico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }.start();
    }

    @Override
    public Object getValor(Object... pEntidade) {
        SBCore.getCentralDeMensagens().enviarMsgAvisoAoDesenvolvedor("O valor logico para " + getCampoInst().getLabel() + "Não foi definido");
        return null;
    }

    @Override
    public ItfCampoInstanciado getCampoInst() {
        return campoInst;
    }

    protected ItfAtributoObjetoEditavel getAtributosDoCampo() {
        CampoInstanciadoGenerico cp = (CampoInstanciadoGenerico) campoInst;
        return (ItfAtributoObjetoEditavel) cp.getAtributosDoObjeto();
    }

}
