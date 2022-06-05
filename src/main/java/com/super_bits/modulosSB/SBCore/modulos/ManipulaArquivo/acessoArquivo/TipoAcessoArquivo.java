/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanEstatico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanVinculadoAEnum;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = {"Tipo Acesso Arquivo"}, plural = "tipos de acesso a arquivos", fabricaVinculada = FabTipoAcessoArquivo.class)
public class TipoAcessoArquivo extends ItemSimples implements ItfBeanVinculadoAEnum {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA)
    private FabTipoAcessoArquivo tipoAcessoArquivo;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSlugURL() {
        return UtilSBCoreStringFiltros.gerarUrlAmigavel(nome);
    }

    public ItfFabrica getFabricaObjeto() {
        return tipoAcessoArquivo;
    }

    @Override
    public void setEnumVinculado(ItfFabrica pFabrica) {
        tipoAcessoArquivo = (FabTipoAcessoArquivo) pFabrica;
    }

    @Override
    public ItfFabrica getEnumVinculado() {
        return tipoAcessoArquivo;
    }

}
