/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.GeradorCpfCnpj;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreRandomico;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidadorGoverno;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.UtilSBCoreReflexaoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.DATA;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.DATAHORA;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.ENUM_FABRICA;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.MOEDA_REAL;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.TEXTO_SIMPLES;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.VERDADEIRO_FALSO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciadoDInamico.CampoInstanciadoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author desenvolvedor
 */
public final class TipoAtributoMetodosBase {

    private final FabTipoAtributoObjeto tipo;

    public TipoAtributoMetodosBase(FabTipoAtributoObjeto pTipo) {
        tipo = pTipo;

    }

    public TipoAtributoObjetoSB getRegistro() {
        TipoAtributoObjetoSB sbCampo = new TipoAtributoObjetoSB(tipo);
        tipo.configuraPropriedadesBasicas(sbCampo);
        return sbCampo;

    }

    /**
     *
     * Retorna um tipo de campo padrão de acordo com a classe.
     *
     * @param pClasse
     * @return
     */
    public static FabTipoAtributoObjeto getTipoPadraoByClasse(Class pClasse) {
        try {
            if (pClasse == null) {
                throw new UnsupportedOperationException("Classe não foi enviada para obter o tipo padrão por classe");
            }
            if (pClasse.getSimpleName().equals("String")) {
                return TEXTO_SIMPLES;
            }
            if (pClasse.getSimpleName().equals("List")) {
                return LISTA_OBJETOS_PUBLICOS;
            }

            if (pClasse.getSimpleName().equals("Date")) {
                return FabTipoAtributoObjeto.CALENDARIO;
            }

            if (pClasse.getSimpleName().equals(Integer.class.getSimpleName())) {
                return FabTipoAtributoObjeto.QUANTIDADE;
            }

            if (pClasse.getSimpleName().equals(int.class.getSimpleName())) {
                return FabTipoAtributoObjeto.QUANTIDADE;
            }
            if (pClasse.getSimpleName().equals(long.class.getSimpleName())) {
                return FabTipoAtributoObjeto.QUANTIDADE;
            }
            if (pClasse.getSimpleName().equals(Long.class.getSimpleName())) {
                return FabTipoAtributoObjeto.QUANTIDADE;
            }

            if (pClasse.getSimpleName().equals(double.class.getSimpleName())) {
                return FabTipoAtributoObjeto.PERCENTUAL;
            }
            if (pClasse.getSimpleName().equals(Double.class.getSimpleName())) {
                return FabTipoAtributoObjeto.MOEDA_REAL;
            }
            if (pClasse.getSimpleName().equals(float.class.getSimpleName())) {
                return FabTipoAtributoObjeto.QUANTIDADE;
            }

            if (pClasse.getSimpleName().equals(boolean.class.getSimpleName())) {
                return FabTipoAtributoObjeto.VERDADEIRO_FALSO;
            }

            if (pClasse.isAssignableFrom(ItfGrupoCampos.class)) {
                return FabTipoAtributoObjeto.GRUPO_CAMPO;
            }
            if (UtilSBCoreReflexao.isInterfaceImplementadaNaClasse(pClasse, ItfBeanSimples.class)) {
                return FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA;
            }
            if (pClasse.isEnum()) {
                return ENUM_FABRICA;
            }
            if (UtilSBCoreReflexao.isInterfaceImplementadaNaClasse(pClasse, ItfFabrica.class)) {
                return FabTipoAtributoObjeto.ENUM_FABRICA;
            }
            return FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Determinando tipo de Objeto por classe" + pClasse, t);
            return TEXTO_SIMPLES;
        }
    }

    public ItfResposta validar(Object pValor, ItfResposta pResp) {

        switch (tipo) {
            case AAA_NOME:
                break;
            case AAA_NOME_LONGO:
                break;
            case IMG_PEQUENA:
                break;
            case IMG_MEDIA:
                break;
            case IMG_GRANDE:
                break;
            case AAA_DESCRITIVO:
                break;
            case ID:
                break;
            case LATITUDE:
                break;
            case Longitude:
                break;
            case LC_LOGRADOURO:
                break;
            case LCCEP:
                break;
            case LC_BAIRRO:
                break;
            case LC_CIDADE:
                break;
            case LC_UNIDADE_FEDERATIVA:
                break;
            case SENHA:
                break;
            case SENHA_SEGURANCA_MAXIMA:
                break;
            case LC_COMPLEMENTO_E_NUMERO:
                break;
            case LC_CAMPO_ABERTO:

                break;
            case LC_COMPLEMENTO:
                break;
            case HTML:
                break;
            case CHART_VALOR:
                break;
            case CHART_LABEL:
                break;
            case CHART_CATEGORIA:
                break;
            case CALENDARIO:
                break;
            case DATAHORA:
                break;
            case DATA:
                break;
            case HORA:
                break;
            case TELEFONE_FIXO_NACIONAL:
                break;
            case TELEFONE_FIXO_INTERNACIONAL:
                break;
            case TELEFONE_CELULAR:
                break;
            case TELEFONE_GENERICO:
                break;
            case MOEDA_REAL:
                break;
            case MOEDA_DOLAR:
                break;
            case QUANTIDADE:
                break;
            case PERCENTUAL:
                break;
            case OBJETO_DE_UMA_LISTA:
                break;
            case LISTA_OBJETOS_PUBLICOS:
                break;
            case TEXTO_SIMPLES:
                break;
            case VERDADEIRO_FALSO:
                break;
            case COR:
                break;
            case EMAIL:
                break;
            case SITE:
                break;
            case URL:
                break;
            case RESPONSAVEL:
                break;
            case NOME_COMPLETO:
                break;
            case CNPJ:
                if (!UtilSBCoreValidadorGoverno.validaCNPJ((String) pValor)) {
                    pResp.addErro("Digito Verificador Não confere");
                }
                break;
            case CPF:
                break;
            case INSCRICAO_ESTADUAL:
                break;
            case INSCRIACAO_MUNICIPAL:
                break;
            case REG_DATAALTERACAO:
                break;
            case REG_DATAINSERCAO:
                break;
            case REG_USUARIO_ALTERACAO:
                break;
            case REG_USUARIO_INSERCAO:
                break;
            case REG_ATIVO_INATIVO:
                break;
            case CODIGO_DE_BARRAS:
                break;
            case ICONE:
                break;
            case SEGURANCA_ATIVA:
                break;
            case ARQUIVO_DE_ENTIDADE:
                break;
            case LC_LOCALIZACAO:
                break;
            case CAMPO_SEPARADOR:
                break;
            case LC_LOCALIDADE:
                break;
            case HTML_TEMPLATE:
                break;
            case GRUPO_CAMPO:
                break;
            case GRUPOS_DE_CAMPO:
                break;
            case FORMULARIO_DE_ACAO:
                break;
            default:
                throw new AssertionError(tipo.name());

        }
        return pResp;
    }

    public static Object converterValorPorTipoObjeto(ItfCampoInstanciado pCampo, Object pValor) {

        TIPO_PRIMITIVO tipoPrimitivo = pCampo.getTipoPrimitivoDoValor();

        if (pCampo.getFabricaTipoAtributo() == ENUM_FABRICA) {
            if (pValor != null) {
                if (pValor instanceof ItfFabrica) {
                    pValor = pValor;
                } else if (pValor instanceof Integer) {
                    pValor = UtilSBCoreReflexaoFabrica.getFabricaPorOrdinal(pCampo.getComoEnumFabricaObjeto().getClasseEnumFab(), (int) pValor);
                } else if (pValor instanceof String) {
                    pValor = Enum.valueOf(pCampo.getComoEnumFabricaObjeto().getClasseEnumFab(), (String) pValor);
                } else if (pValor instanceof ItfBeanSimples) {
                    pValor = UtilSBCoreReflexaoFabrica.getEnumDoObjetoFabrica(pCampo.getComoEnumFabricaObjeto().getClasseEnumFab(), (ItfTipoAtributoSBSomenteLeitura) pValor);
                }
            }

        } else {

            FabTipoAtributoObjeto tipoCampo = pCampo.getFabricaTipoAtributo();
            //tipoCampo.validarTipoCompativel(pCampo.getCampoReflection());

            switch (tipoPrimitivo) {
                case INTEIRO:
                    pValor = Integer.parseInt(pValor.toString());
                    break;

                case NUMERO_LONGO:
                    pValor = Long.parseLong(pValor.toString(), 0);
                    break;

                case LETRAS:
                    if (pCampo.isTemMascara()) {
                        if (pValor == null) {
                            return pValor;
                        }
                        if (pCampo.getMascara().equals(pValor) || pCampo.getMascaraJqueryMode().equals(pValor)) {
                            return null;
                        }
                        // Evitar má pŕatica incompativel com unique de banco de dados
                        if (pValor.equals("")) {
                            return null;
                        }
                        return pValor;
                    }
                    if (pCampo.isValorCampoUnico()) {
                        if (pValor == null) {
                            return null;
                        }
                        if (pValor.toString().isEmpty()) {
                            return null;
                        }
                    }
                    break;

                case DATAS:

                    break;
                case BOOLEAN:

                    break;
                case DECIMAL:
                    pValor = Double.parseDouble(pValor.toString());
                    break;
                case ENTIDADE:

                case OUTROS_OBJETOS:
                    break;
                default:
                    throw new AssertionError(tipoPrimitivo.name());

            }
        }
        return pValor;

    }

    public static Object converterStringDinamicoEmValorReal(CampoInstanciadoDinamico pCampo, String pValor) {

        try {
            switch (pCampo.getFabricaTipoAtributo()) {
                case DATA:
                    return UtilSBCoreDataHora.converteStringDD_MM_YYYYEmData(pValor);
                case DATAHORA:
                    return UtilSBCoreDataHora.converteStringDD_MM_YYYYEmData(pValor);
                case MOEDA_REAL:
                    return pValor;
                case VERDADEIRO_FALSO:
                    if (pValor.equals("true") || pValor.toUpperCase().equals("SIM")) {
                        return true;
                    } else {
                        return false;
                    }

                case OBJETO_DE_UMA_LISTA:
                    if (pValor == null) {
                        return null;
                    }

                    if (pValor.isEmpty()) {
                        return null;
                    }
                    pCampo.getNomeClasseAtributoDeclarado();
                    String nomeEntidade = pCampo.getAtributosCampoDinamico().getNomeClasseAtributoDeclarado();
                    Class entidade = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(nomeEntidade);
                    if (entidade == null) {
                        return null;
                    }
                    if (entidade.getAnnotation(Entity.class) != null) {
                        return SBCore.getCentralDados().getRegistroByID(null, entidade, Integer.valueOf(pValor));

                    } else {
                        return null;
                    }
                //for (ItfBeanSimples opcao : getListaDeOpcoes()) {
                //     if (String.valueOf(opcao.getId()).equals(valor)) {
                //         return opcao;
                //     }
                // }

                default:
                    return pValor;

            }

        } catch (Throwable t) {
            return null;
        }
        //return campoReflection.getValorDesteCampoEmObjetoInstanciado(getInstancia(), true);

    }

    public static String converterValorDinamicoEmString(ItfCampoInstanciado pCampo, Object pValor) {
        System.out.println("Setando valor tipo de objeto=" + pCampo.getClass().toString());

        // Todos os valores devem ser transoformados em String de maneira que possam ser transformados em valores posteriormente
        try {
            if (pValor != null) {
                switch (pCampo.getFabricaTipoAtributo()) {

                    case DATA:
                    case DATAHORA:
                        Date valorData = (Date) pValor;

                        pValor = UtilSBCoreDataHora.converteDateEmSTringDD_MM_YY(valorData);
                        break;
                    case OBJETO_DE_UMA_LISTA:
                        if (pValor == null) {
                            return null;
                        }
                        String idEmString = String.valueOf(((ItfBeanSimples) pValor).getId());
                        pValor = idEmString;
                        break;

                    default:
                        pValor = pValor.toString();
                }
                return pValor.toString();
            } else {
                return null;
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configuando valor de campo dinamico", t);
            if (pValor != null) {
                return pValor.toString();
            }
            return pValor.toString();
        }

    }

    public void validarTipoCompativel(FieldComSerializacao pCampo) {
        switch (tipo) {
            case AAA_NOME:
                break;
            case AAA_NOME_LONGO:
                break;
            case IMG_PEQUENA:
                break;
            case IMG_MEDIA:
                break;
            case IMG_GRANDE:
                break;
            case AAA_DESCRITIVO:
                break;
            case ID:
                break;
            case QUANTIDADE:
                break;
            case LATITUDE:
                break;
            case Longitude:
                break;
            case LC_LOGRADOURO:
                break;
            case LCCEP:
                break;
            case LC_BAIRRO:
                break;
            case LC_CIDADE:
                break;
            case LC_LOCALIDADE:
                break;
            case LC_UNIDADE_FEDERATIVA:
                break;
            case SENHA:
                break;
            case SENHA_SEGURANCA_MAXIMA:
                break;
            case LC_COMPLEMENTO_E_NUMERO:
                break;
            case LC_CAMPO_ABERTO:
                break;
            case HTML:
                break;
            case CHART_VALOR:
                break;
            case CHART_LABEL:
                break;
            case CHART_CATEGORIA:
                break;
            case CALENDARIO:
                break;
            case DATAHORA:
                break;
            case DATA:
                break;
            case HORA:
                break;
            case TELEFONE_FIXO_NACIONAL:
                break;
            case TELEFONE_FIXO_INTERNACIONAL:
                break;
            case TELEFONE_CELULAR:
                break;
            case TELEFONE_GENERICO:
                break;
            case MOEDA_REAL:
                break;
            case MOEDA_DOLAR:
                break;
            case PERCENTUAL:
                break;
            case OBJETO_DE_UMA_LISTA:
                break;
            case LISTA_OBJETOS_PUBLICOS:
                break;
            case TEXTO_SIMPLES:
                break;
            case VERDADEIRO_FALSO:
                break;
            case COR:
                break;
            case EMAIL:
                break;
            case SITE:
                break;
            case URL:
                break;
            case RESPONSAVEL:
                break;
            case NOME_COMPLETO:
                break;
            case CNPJ:
                break;
            case CPF:
                break;
            case INSCRICAO_ESTADUAL:
                break;
            case INSCRIACAO_MUNICIPAL:
                break;
            case REG_DATAALTERACAO:
                break;
            case REG_DATAINSERCAO:
                break;
            case REG_USUARIO_ALTERACAO:
                break;
            case REG_USUARIO_INSERCAO:
                break;
            case REG_ATIVO_INATIVO:
                break;
            case CODIGO_DE_BARRAS:
                break;
            case ICONE:
                break;
            case SEGURANCA_ATIVA:
                break;
            case ARQUIVO_DE_ENTIDADE:
                break;
            case LC_LOCALIZACAO:
                break;
            case CAMPO_SEPARADOR:
                break;
            case HTML_TEMPLATE:
                break;
            case GRUPO_CAMPO:
                break;
            case GRUPOS_DE_CAMPO:
                break;
            case LC_COMPLEMENTO:
                break;
            case FORMULARIO_DE_ACAO:

                break;
            case ENUM_FABRICA:
                break;
            case TIPO_PADRAO_POR_DECLARACAO:
                break;
            default:
                throw new AssertionError(tipo.name());

        }
    }

    public Object getValorAleatorioEmConformidade(ItfAtributoObjetoSB pAtributo) {

        switch (tipo) {
            case CPF:
                return (new GeradorCpfCnpj().cnpj(false));

            case CNPJ:
                return (new GeradorCpfCnpj().cnpj(false));

            case TELEFONE_GENERICO:
            case TELEFONE_FIXO_INTERNACIONAL:
            case TELEFONE_CELULAR:
            case TELEFONE_FIXO_NACIONAL:
            case TEXTO_SIMPLES:
            case OBJETO_DE_UMA_LISTA:
                ItfBeanSimplesSomenteLeitura valor = null;
                try {
                    if (pAtributo instanceof ItfCampoInstanciado) {
                        ItfCampoInstanciado cpi = (ItfCampoInstanciado) pAtributo;
                        if (cpi.getComoCampoSeltorItem().getSeletor().getOrigem().isEmpty()) {
                            valor = (ItfBeanSimplesSomenteLeitura) cpi.getComoCampoSeltorItem().getSeletor().getOrigem().get(0);
                        }
                    }
                } catch (Throwable t) {

                }
                return valor;
            case INSCRIACAO_MUNICIPAL:
            case INSCRICAO_ESTADUAL:
                ItfTipoAtributoSBSomenteLeitura tipoAtributo;
                if (pAtributo == null) {
                    tipoAtributo = this.getRegistro();
                } else {
                    tipoAtributo = pAtributo;
                }
                if (tipoAtributo != null) {
                    if (tipoAtributo.isTemMascara()) {
                        String valorMaskara = (UtilSBCoreRandomico.getValorStringRandomicoViaMaskara(pAtributo.getMascara()));
                        switch (tipo) {
                            case TELEFONE_FIXO_INTERNACIONAL:

                            case TELEFONE_FIXO_NACIONAL:
                                return "(31) 32" + valorMaskara.substring(7, valorMaskara.length() - 1);
                            case TELEFONE_CELULAR:
                                return "(31) 99" + valorMaskara.substring(7, valorMaskara.length() - 1);
                        }
                        return valorMaskara;
                    }
                }
                String valorAleatorio = UtilSBCoreRandomico.getValorStringRandomico(UtilSBCoreRandomico.TIPO_VALOR_RANDON.LETRAS, 10);
                valorAleatorio = UtilSBCoreStringFiltros.gerarUrlAmigavel(valorAleatorio);
                return valorAleatorio;

            case LCCEP:
                return "30190030";

            case COR:
                return "FFFFFF";

            case QUANTIDADE:
                return 999;

            case PERCENTUAL:
                return 50;

            case AAA_NOME:
                if (pAtributo != null) {
                    EstruturaDeEntidade estrutura = MapaObjetosProjetoAtual.getEstruturaObjeto(pAtributo.getNomeClasseOrigemAtributo());
                    return estrutura.getTags().get(0) + UtilSBCoreRandomico.getValorStringRandomico(UtilSBCoreRandomico.TIPO_VALOR_RANDON.NUMERO, 4);
                } else {
                    return UtilSBCoreRandomico.getValorStringRandomico(UtilSBCoreRandomico.TIPO_VALOR_RANDON.NUMERO, 15);
                }

            case EMAIL:
                String tipousuario = "usuario";
                if (pAtributo != null) {
                    EstruturaDeEntidade estrutura = MapaObjetosProjetoAtual.getEstruturaObjeto(pAtributo.getNomeClasseOrigemAtributo());
                    tipousuario = estrutura.getTags().get(0) + UtilSBCoreRandomico.getValorStringRandomico(UtilSBCoreRandomico.TIPO_VALOR_RANDON.NUMERO, 4);
                } else {
                    tipousuario = UtilSBCoreRandomico.getValorStringRandomico(UtilSBCoreRandomico.TIPO_VALOR_RANDON.LETRAS, 5);
                }

                String usuarioEmailFake = tipousuario;
                usuarioEmailFake = UtilSBCoreStringFiltros.gerarUrlAmigavel(usuarioEmailFake);
                if (usuarioEmailFake.length() > 10) {
                    usuarioEmailFake = usuarioEmailFake.substring(0, 10);
                }

                return usuarioEmailFake + UtilSBCoreRandomico.getValorStringRandomico(UtilSBCoreRandomico.TIPO_VALOR_RANDON.NUMERO, 4) + "@emailFake.com";

        }
        return null;

    }

    public Object getValorAleatorioEmConformidade() {
        return getValorAleatorioEmConformidade(null);
    }

}
