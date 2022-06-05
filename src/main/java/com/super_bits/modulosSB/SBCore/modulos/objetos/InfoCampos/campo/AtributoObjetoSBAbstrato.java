/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.PropriedadesReflexaoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.List;

/**
 *
 * TODO: polimorfismo por tipo de atributo, separando as propriedades
 * específicas de cada um
 *
 * @author SalvioF
 */
public abstract class AtributoObjetoSBAbstrato extends ItemSimples implements ItfAtributoObjetoEditavel {

    protected final TipoAtributoObjetoSB tipoAtributo;
    protected String nomeClasseOrigemAtributo;
    protected String nomeClasseAtributo;
    protected String caminhoListagem;
    protected String nomeDeclaracao;

    protected final List<String> templatePalavrasChave;

    protected Class classeDeclaracaoObjeto;

    protected final int idAtributo;

    public AtributoObjetoSBAbstrato(PropriedadesReflexaoCampo pPropriedadesReflexao) {

        tipoAtributo = new TipoAtributoMetodosBase(pPropriedadesReflexao.getFabTipoAtributo()).getRegistro();
        if (tipoAtributo == null) {
            throw new UnsupportedOperationException("Erro criando atributo de Objeto, o tipo do atributo não pôde ser determinado " + pPropriedadesReflexao.getLabel());
        }

        idAtributo = (pPropriedadesReflexao.getClasseOrigemAtributo() + getLabel()).hashCode();
        classeDeclaracaoObjeto = pPropriedadesReflexao.getClasseDeclaracaoAtributo();
        nomeClasseOrigemAtributo = pPropriedadesReflexao.getClasseOrigemAtributo().toString();
        if (nomeClasseOrigemAtributo.contains(".")) {
            String[] partes = nomeClasseOrigemAtributo.split("\\.");
            nomeClasseOrigemAtributo = partes[partes.length - 1];
        }
        nomeClasseAtributo = pPropriedadesReflexao.getClasseDeclaracaoAtributo().getSimpleName();
        caminhoListagem = pPropriedadesReflexao.getCaminhoListagemOpcoes();
        templatePalavrasChave = pPropriedadesReflexao.getTemplateCampos();
        nomeDeclaracao = pPropriedadesReflexao.getNomeDeclaracaoAtributo();

        setLabel(pPropriedadesReflexao.getLabel());

    }

}
