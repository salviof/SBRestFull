/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroCaminhoCampoNaoExiste;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoExibicaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.InfoGrupoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;

import java.lang.reflect.Field;
import java.util.Date;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreReflexaoAtributoDeObjetoSimples;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreReflexaoAtributoDeObjeto extends UtilSBCoreReflexaoAtributoDeObjetoSimples {

    public static Class getClassePrincipalByString(String pTextoCampo) throws UnsupportedOperationException {

        TIPO_PRIMITIVO tipoPrimitivo = TIPO_PRIMITIVO.getTipoPrimitivoByTexto(pTextoCampo);
        switch (tipoPrimitivo) {
            case INTEIRO:
                return int.class;

            case NUMERO_LONGO:
                return long.class;
            case LETRAS:
                return String.class;
            case DATAS:
                return Date.class;
            case BOOLEAN:
                return boolean.class;
            case DECIMAL:
                return double.class;
            case ENTIDADE:
                Class classe = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pTextoCampo);
                if (classe == null) {
                    throw new UnsupportedOperationException("A classe não pôde ser determinada pela string [" + pTextoCampo + "] ");
                }
            case OUTROS_OBJETOS:
                throw new UnsupportedOperationException("A classe não pôde ser determinada pela string [" + pTextoCampo + "] ");

            default:
                throw new UnsupportedOperationException("A classe não pôde ser determinada pela string [" + pTextoCampo + "] ");

        }

    }

    private static GrupoCampos gerarGrupoCampoSemCampos(InfoGrupoCampo pAnotacaoGrupoCampo, Class pClasseEntidade) {
        String titulo = "Sem Titulo";
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pAnotacaoGrupoCampo.titulo())) {
            InfoObjetoSB infoObjeto = UtilSBCoreReflexaoObjeto.getInfoClasseObjeto(pClasseEntidade);
            titulo = "Dados de " + infoObjeto.tags()[0];
        } else {
            titulo = pAnotacaoGrupoCampo.titulo();
        }
        return new GrupoCampos(titulo);

    }

    public static GrupoCampos getGrupoCampoPorAnotacaoIgnorandoCamposNaoEcontrados(InfoGrupoCampo pAnotacaoGrupoCampo, Class pClasseEntidade) {

        GrupoCampos grupoCampos = gerarGrupoCampoSemCampos(pAnotacaoGrupoCampo, pClasseEntidade);

        for (FabTipoAtributoObjeto tipo : pAnotacaoGrupoCampo.camposPorTipoAtributo()) {
            try {
                CaminhoCampoExibicaoFormulario novoCampo = new CaminhoCampoExibicaoFormulario(new CaminhoCampoReflexao(tipo, pClasseEntidade), grupoCampos.getNomeIdentificadorSlug());
                if (novoCampo != null) {
                    grupoCampos.adicionarCampo(novoCampo);
                }
            } catch (Throwable t) {

            }

        }

        for (String tipo : pAnotacaoGrupoCampo.camposDeclarados()) {
            try {
                grupoCampos.adicionarCampo(new CaminhoCampoExibicaoFormulario(new CaminhoCampoReflexao(tipo, pClasseEntidade), grupoCampos.getNomeIdentificadorSlug()));
            } catch (Throwable t) {

            }
        }
        return grupoCampos;

    }

    public static GrupoCampos getGrupoCampoPorAnotacao(InfoGrupoCampo pAnotacaoGrupoCampo, Class pClasseEntidade) throws ErroCaminhoCampoNaoExiste {
        GrupoCampos pGrupoCampos = gerarGrupoCampoSemCampos(pAnotacaoGrupoCampo, pClasseEntidade);

        for (FabTipoAtributoObjeto tipo : pAnotacaoGrupoCampo.camposPorTipoAtributo()) {
            try {
                pGrupoCampos.adicionarCampo(new CaminhoCampoExibicaoFormulario(
                        new CaminhoCampoReflexao(tipo, pClasseEntidade), pGrupoCampos.getNomeIdentificadorSlug())
                );
                //Ignora o campo caso não exista um campo deste tipo
            } catch (Throwable t) {
                if (pClasseEntidade == null) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando (parametro classe nulo) campo " + tipo, t);
                }

            }
        }

        for (String tipo : pAnotacaoGrupoCampo.camposDeclarados()) {
            pGrupoCampos.adicionarCampo(new CaminhoCampoExibicaoFormulario(new CaminhoCampoReflexao(tipo, pClasseEntidade), pGrupoCampos.getNomeIdentificadorSlug()));

        }
        return pGrupoCampos;

    }

    /**
     * Retorna o generico da classe, ex: List<Tabela> retorna Tabela.class
     *
     * @param pCampoReflexao Field reflexão
     * @return
     */
    public static Class getClasseGenerica(Field pCampoReflexao) {
        return getClassePrincipalDoCampo(pCampoReflexao);
    }

}
