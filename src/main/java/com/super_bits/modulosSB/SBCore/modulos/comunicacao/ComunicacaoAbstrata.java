/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author SalvioF
 */
public abstract class ComunicacaoAbstrata extends ItemSimples implements ItfComunicacao, Serializable {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;

    private boolean foiSelado;
    private String codigoSelo;
    private FabStatusComunicacao status;
    private final Date dataHoraDisparo;
    private Date dataHoraResposta;

    public ComunicacaoAbstrata() {
        dataHoraDisparo = new Date();
    }

    @Override
    public void selarComunicacao() {
        if (!foiSelado) {
            String usuario = getUsuarioRemetente().getEmail();
            String destinatario = getDestinatario().getEmailsConcatenados();
            Long idDataHora = dataHoraDisparo.getTime();
            id = (usuario + destinatario + String.valueOf(idDataHora)).hashCode();
            codigoSelo = String.valueOf(id);
            if (status == null) {
                status = FabStatusComunicacao.SELADO;
            }
        }
    }

    @Override
    public FabStatusComunicacao getStatusComunicacao() {
        if (status == null && isFoiSelado()) {
            status = FabStatusComunicacao.SELADO;
        }
        return status;
    }

    @Override
    public void setStatusComunicacao(FabStatusComunicacao pStatus) {
        status = pStatus;
    }

    @Override
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isFoiSelado() {
        return foiSelado;
    }

    @Override
    public String getCodigoSelo() {
        return codigoSelo;
    }

    @Override
    public Date getDataHoraDisparo() {
        return dataHoraDisparo;
    }

    /**
     *
     * @return
     */
    @Override
    public Date getDataHoraResposta() {
        return dataHoraResposta;
    }

}
