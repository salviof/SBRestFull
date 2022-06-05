/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao;

import org.coletivojava.fw.api.analiseDados.ItfMapaSubstituicao;
import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringBuscaTrecho;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public class MapaSubstituicao implements ItfMapaSubstituicao {

    protected final Map<String, String> mapaSubstituicao = new HashMap<>();
    protected final Map<String, String> mapaSubstituicaoImagem = new HashMap<>();
    protected final Map<String, Map<String, String>> mapaSubstituicaoListas = new HashMap<>();
    protected final Map<String, Map<Integer, List<String>>> ordemMapaSubstituicaoListas = new HashMap<>();

    @Override
    public String substituirEmString(String pString) {

        if (pString == null) {
            return "";
        }
        String novaString = pString;
        for (String palavraChave : mapaSubstituicao.keySet()) {

            if (pString.contains(palavraChave)) {
                System.out.println("Substituindo [" + pString + "] por" + mapaSubstituicao.get(palavraChave));
                novaString = novaString.replace(palavraChave, mapaSubstituicao.get(palavraChave));
            }
        }
        System.out.println("Resultado=" + novaString);
        return novaString;

    }

    protected Map<String, String> getMapaSubstituicao() {
        return mapaSubstituicao;
    }

    protected final String getChaveListas(String texto) {
        String chave = UtilSBCoreStringFiltros.getStringSemNumeros(texto);
        chave = UtilSBCoreStringBuscaTrecho.getStringAteEncontrarIsto(chave, "[]");
        return chave;
    }

    @Override
    public final void adicionarPalavraChave(String palavra, String valor) {
        if (!palavra.replaceAll("\\[[0-9]", "-").equals(palavra)) {
            String chavelista = getChaveListas(palavra);
            Integer palavraIndice = Integer.valueOf(UtilSBCoreStringFiltros.getNumericosDaString(palavra));

            if (mapaSubstituicaoListas.get(chavelista) == null) {
                mapaSubstituicaoListas.put(chavelista, new HashMap<>());
                ordemMapaSubstituicaoListas.put(chavelista, new HashMap<>());
            }
            if (ordemMapaSubstituicaoListas.get(chavelista).get(palavraIndice) == null) {
                ordemMapaSubstituicaoListas.get(chavelista).put(palavraIndice, new ArrayList<>());
            }

            if (mapaSubstituicaoListas.get(chavelista) != null) {
                mapaSubstituicaoListas.get(chavelista).put(palavra, valor);
                ordemMapaSubstituicaoListas.get(chavelista).get(palavraIndice).add(palavra);
            }
        } else {
            mapaSubstituicao.put(palavra, valor);
        }

    }

    @Override
    public final void adicionarPalavrasChaveDoObjeto(ItfBeanSimples pObjeto) {
        adicionarPalavrasChaveDoObjeto(null, pObjeto);
        adicionarPalavrasChaveDoObjeto(pObjeto.getClass().getSimpleName(), pObjeto);
    }

    @Override
    public void adicionarPalavrasChaveDoObjeto(String prefixo, ItfBeanSimples pObjeto) {
        if (pObjeto == null) {
            return;
        }
        if (prefixo != null) {
            prefixo = prefixo + ".";
        } else {
            prefixo = "";
        }
        for (ItfCampoInstanciado campo : pObjeto.getCamposInstanciados()) {
            String nomeVariavelCampo = "não determinado";

            if (campo != null && !campo.isCampoNaoInstanciado()) {
                try {
                    nomeVariavelCampo = campo.getNomeCamponaClasse();
                } catch (Throwable t) {

                }
                if (nomeVariavelCampo.contains("json")) {
                    continue;
                }
                try {
                    if (campo.isUmCampoArquivoEntidade()) {
                        if (campo.getValor() != null) {
                            switch (FabTipoArquivoConhecido.getTipoArquivoByNomeArquivo(campo.getValor().toString())) {
                                case IMAGEM_WEB:
                                    adicionarImagem("[" + prefixo + campo.getNomeCamponaClasse() + "]", SBCore.getCentralDeArquivos().getEndrLocalArquivoCampoInstanciado(campo));

                                    break;
                            }
                        }

                    } else {
                        if (!campo.isCampoNaoInstanciado()) {
                            switch (campo.getFabricaTipoAtributo()) {

                                case LC_BAIRRO:
                                case LC_CIDADE:
                                case LC_LOCALIDADE:
                                case LC_UNIDADE_FEDERATIVA:
                                case OBJETO_DE_UMA_LISTA:
                                case ENUM_FABRICA:
                                case LISTA_OBJETOS_PUBLICOS:
                                case LISTA_OBJETOS_PARTICULARES:
                                case LC_LOCALIZACAO:
                                case CAMPO_SEPARADOR:
                                case GRUPO_CAMPO:
                                case GRUPOS_DE_CAMPO:
                                case FORMULARIO_DE_ACAO:
                                case TIPO_PADRAO_POR_DECLARACAO:
                                    break;
                                default:
                                    String valorCampo = "Valor não informado";
                                    if (campo.getValor() != null) {
                                        valorCampo = campo.getValor().toString();

                                    }

                                    mapaSubstituicao.put("[" + prefixo + nomeVariavelCampo + "]", valorCampo);

                            }

                        }
                    }

                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "o Campo:" + nomeVariavelCampo + "não foi adicionado ao mapeamento" + t.getMessage(), t);
                    System.out.println("Observacao, o Campo:" + nomeVariavelCampo + "não foi adicionado ao mapeamento");
                }
            }
        }
    }

    @Override
    public final void adicionarImagem(String palavraChave, String valor) {

        mapaSubstituicaoImagem.put(palavraChave, valor);
    }

    /**
     *
     * @return Lista de Palavra chave dispon
     */
    @Override
    public List<String> getpalavrasChave() {
        List<String> palavras = new ArrayList<>();
        palavras.addAll(Lists.newArrayList(mapaSubstituicao.keySet().iterator()));
        palavras.addAll(Lists.newArrayList(mapaSubstituicaoImagem.keySet().iterator()));

        List<String> listagensEncontradas = new ArrayList<>();
        for (String chave : mapaSubstituicaoListas.keySet()) {
            for (String valor : mapaSubstituicaoListas.get(chave).keySet()) {
                String valorFormatado = UtilSBCoreStringFiltros.getStringSemNumeros(valor);
                if (!listagensEncontradas.contains(valorFormatado)) {
                    listagensEncontradas.add(valorFormatado);
                }
            }
        }
        palavras.addAll(listagensEncontradas);
        return palavras;
    }

}
