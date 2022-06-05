/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocalPostagem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = "Localização", plural = "Localizações")
public class ItemLocalizacao extends ItemSimples implements ItfLocalPostagem {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.LC_LOGRADOURO)
    private String logradouro;
    private long longitude;
    private long latitude;
    @InfoCampo(tipo = FabTipoAtributoObjeto.LC_BAIRRO)
    private ItemBairro bairro;
    @InfoCampo(tipo = FabTipoAtributoObjeto.LC_COMPLEMENTO_E_NUMERO)
    private String complemento;
    private boolean obairroNulo;
    private boolean aCidadeNula;
    private boolean oEstadoNulo;
    private boolean oPaisNulo;
    private boolean locaPostavel;
    @InfoCampo(tipo = FabTipoAtributoObjeto.LCCEP)
    private String cep;

    @Override
    public void prepararNovoObjeto(Object... parametros) {
        try {
            super.prepararNovoObjeto(); //To change body of generated methods, choose Tools | Templates.
        } catch (ErroPreparandoObjeto t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro preparando item de localizacao" + t.getMessage(), t);
        }
        bairro = new ItemBairro();
        bairro.setCidade(new ItemCidade());
        bairro.getCidade().setUnidadeFederativa(new ItemUnidadeFederativa());
    }

    @Override
    public long getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    @Override
    public long getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    @Override
    public ItemBairro getBairro() {

        return bairro;

    }

    public void setBairro(ItemBairro bairro) {
        this.bairro = bairro;
    }

    @Override
    public String getComplemento() {
        return complemento;
    }

    @Override
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public boolean isObairroNulo() {
        return obairroNulo;
    }

    public void setObairroNulo(boolean obairroNulo) {
        this.obairroNulo = obairroNulo;
    }

    public boolean isaCidadeNula() {
        return aCidadeNula;
    }

    public void setaCidadeNula(boolean aCidadeNula) {
        this.aCidadeNula = aCidadeNula;
    }

    public boolean isoEstadoNulo() {
        return oEstadoNulo;
    }

    public void setoEstadoNulo(boolean oEstadoNulo) {
        this.oEstadoNulo = oEstadoNulo;
    }

    public boolean isoPaisNulo() {
        return oPaisNulo;
    }

    public void setoPaisNulo(boolean oPaisNulo) {
        this.oPaisNulo = oPaisNulo;
    }

    public void setLocaPostavel(boolean locaPostavel) {
        this.locaPostavel = locaPostavel;
    }

    @Override
    public boolean isLocaPostavel() {
        return (this instanceof ItfLocalPostagem);
    }

    @Override
    public ItfLocalPostagem getComoLocalPostavel() {
        if (!isLocaPostavel()) {
            throw new UnsupportedOperationException("Entidade " + this.getClass().getSimpleName() + " não é uma localização postável, impossível utilizar framework de cep");
        }
        return (ItfLocalPostagem) this;
    }

    @Override
    public void setBairro(ItfBairro pBairro) {
        bairro = (ItemBairro) pBairro;
    }

    @Override
    public String getLogradouro() {
        return nome;
    }

    @Override
    public String getCep() {
        return cep;
    }

    @Override
    public void setCep(String pCep) {
        cep = pCep;
    }

    @Override
    public void setLogradouro(String pLogradouro) {
        nome = pLogradouro;
    }

}
