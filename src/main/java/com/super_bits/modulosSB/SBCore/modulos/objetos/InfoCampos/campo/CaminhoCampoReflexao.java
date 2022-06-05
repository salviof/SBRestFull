/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroCaminhoCampoNaoExiste;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCaminhoCampo;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCaminhoCampo.getFieldByNomeCompletoCaminho;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.ManyToOne;

/**
 *
 * @author desenvolvedor
 */
public final class CaminhoCampoReflexao extends ItemSimples implements ItfCaminhoCampo {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    private final List<String> partesCaminho = new ArrayList<>();
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String caminhoComleto;

    private boolean umCampoListavel;
    private boolean umaEntidade;

    private CaminhoCampoReflexao campoListaPaiDocampoFilho;

    private boolean umCampoVinculado = false;

    private TIPO_ORIGEM_VALOR_CAMPO tipoRegistro;

    @Override
    public String getLabelDoCampo() {

        return getLabel();
    }

    @Override
    public void defineNomeCompleto(String pCaminho, Class pClasse) {
        if (UtilSBCoreReflexaoCaminhoCampo.isUmCampoSeparador(pCaminho)) {
            caminhoComleto = pCaminho;
            return;
        }
        if (UtilSBCoreStringValidador.isPrimeiraLetraMaiusculaSegundaMinuscula(pCaminho)) {
            caminhoComleto = pCaminho;
        } else {
            if (pClasse == null) {
                throw new UnsupportedOperationException("impossícel determinar o caminho completo pela string, sem enviar a classe como parametro");
            }
            caminhoComleto = pClasse.getSimpleName() + "." + pCaminho;

        }

    }

    @Override
    public void defineNomeCompleto(String pCaminho) {

        if (UtilSBCoreReflexaoCaminhoCampo.isUmCampoSeparador(pCaminho)) {
            caminhoComleto = pCaminho;
            return;
        }

        if (UtilSBCoreStringValidador.isPrimeiraLetraMaiusculaSegundaMinuscula(pCaminho)) {
            caminhoComleto = pCaminho;
        } else {

            throw new UnsupportedOperationException("impossível determinar o caminho completo pela string, sem enviar a classe como parametro");
        }

    }

    /**
     *
     * @param pCaminho Caminho para encontrar o Campo, separado por . exemplo:
     * usuario.localizacao.bairro
     */
    public CaminhoCampoReflexao(String pCaminho) {
        try {
            //setCaminho(pCaminho);
            defineNomeCompleto(pCaminho);

            configuraInformacoesBasicasDoCampoPorReflexao(validaCampo(null));
            makePartesCaminho();
            id = caminhoComleto.hashCode();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ocorreu um erro criando o campo" + pCaminho, t);
            throw new UnsupportedOperationException("Não foi possível executar o contructor para o caminhoCampoReflexão, utilizando o paramentro" + pCaminho);
        }
    }

    public CaminhoCampoReflexao(Field pCampo) throws ErroCaminhoCampoNaoExiste {

        this(pCampo.getName(), pCampo.getDeclaringClass());

    }

    public CaminhoCampoReflexao(String pCaminho, Class pClasse) throws ErroCaminhoCampoNaoExiste {
        //setCaminho(pCaminho);

        if (pCaminho == null && pClasse == null) {
            throw new ErroCaminhoCampoNaoExiste("Classe nula", "Caminho nulo");
        }

        if (pCaminho == null) {
            throw new ErroCaminhoCampoNaoExiste(pClasse, "Caminho nulo");
        }
        if (pClasse == null) {
            throw new ErroCaminhoCampoNaoExiste("Classe Nula", pCaminho);
        }
        defineNomeCompleto(pCaminho, pClasse);
        configuraInformacoesBasicasDoCampoPorReflexao(validaCampo(pClasse));
        makePartesCaminho();
        id = caminhoComleto.hashCode();

    }

    public CaminhoCampoReflexao(FabTipoAtributoObjeto pTipoAtributo, Class pClasse) throws ErroCaminhoCampoNaoExiste {
        //setCaminho(pCaminho);

        this(UtilSBCoreReflexaoCaminhoCampo.getNomeCampobyTipoCampo(pClasse, pTipoAtributo), pClasse);

    }

    private Field validaCampo(Class pClasse) throws ErroCaminhoCampoNaoExiste {
        if (UtilSBCoreReflexaoCaminhoCampo.isUmCampoSeparador(caminhoComleto)) {
            return null;
        }
        Field campo = null;

        if (pClasse == null) {
            campo = getFieldByNomeCompletoCaminho(caminhoComleto);
        } else {
            campo = UtilSBCoreReflexaoCaminhoCampo.getFieldByNomeCompletoCaminhoEClasse(caminhoComleto, pClasse);
        }

        if (campo == null) {
            if (pClasse != null) {
                throw new ErroCaminhoCampoNaoExiste(pClasse, caminhoComleto);
            } else {
                throw new ErroCaminhoCampoNaoExiste(caminhoComleto, caminhoComleto);
            }
        }
        return campo;

    }

    /**
     *
     * @param pPartesCaminho
     * @param campo
     * @throws
     * com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroCaminhoCampoNaoExiste
     */
    public CaminhoCampoReflexao(List<String> pPartesCaminho) throws ErroCaminhoCampoNaoExiste {
        partesCaminho.addAll(pPartesCaminho);
        makeCaminhoCompleto();
        defineNomeCompleto(caminhoComleto);
        configuraInformacoesBasicasDoCampoPorReflexao(validaCampo(null));
        id = caminhoComleto.hashCode();

    }

    private void configuraInformacoesBasicasDoCampoPorReflexao(Field pField) throws ErroCaminhoCampoNaoExiste {

        if (UtilSBCoreReflexaoCaminhoCampo.isUmCampoSeparador(caminhoComleto)) {
            return;
        }

        umCampoVinculado = UtilSBCoreReflexaoCaminhoCampo.getFieldByNomeCompletoCaminho(caminhoComleto) != null;

        if (umCampoVinculado) {

            if (pField.getType().getSimpleName().equals("List")) {
                umCampoListavel = true;
                umaEntidade = true;
                if (UtilSBCoreReflexaoCaminhoCampo.isUmaStringNomeadaComoLista(caminhoComleto)) {

                } else {
                    caminhoComleto += "[]";
                    makePartesCaminho();
                }
                // verifica se a referencia é de uma lista, ou sobre um valor específico da lista
                //ex: Entidade.sublista[] ou Entidade.subLista[0]
                tipoRegistro = UtilSBCoreReflexaoCaminhoCampo.getTipoCampoLista(caminhoComleto);
            }

            if (pField.isAnnotationPresent(ManyToOne.class)) {
                umaEntidade = true;
                tipoRegistro = TIPO_ORIGEM_VALOR_CAMPO.VALOR_COM_LISTA;
            } else {
                try {
                    InfoCampo anotacaoCampo = pField.getAnnotation(InfoCampo.class);
                    tipoRegistro = anotacaoCampo.tipo().getTipoOrigemPadrao();
                } catch (Throwable t) {
                    tipoRegistro = TIPO_ORIGEM_VALOR_CAMPO.VALOR_LIVRE;
                }
            }

        } else {

            throw new ErroCaminhoCampoNaoExiste(caminhoComleto, caminhoComleto);

        }
    }

    private void makePartesCaminho() {

        if (caminhoComleto == null) {
            throw new UnsupportedOperationException("Impossivel criar o caminho, antes de definir as partes do caminho");
        }
        if (caminhoComleto.isEmpty()) {
            throw new UnsupportedOperationException("Impossivel criar o caminho, antes de definir as partes do caminho");

        }

        String[] partes = caminhoComleto.split("\\.");
        partesCaminho.clear();
        partesCaminho.addAll(Arrays.asList(partes));

    }

    private void makeCaminhoCompleto() {

        if (caminhoComleto == null) {
            throw new UnsupportedOperationException("Impossivel criar as partes do caminho, antes de definir o caminho completo");
        }
        if (caminhoComleto.isEmpty()) {
            throw new UnsupportedOperationException("Impossivel criar as partes do caminho, antes de definir o caminho completo");

        }

        String[] partes = caminhoComleto.split("\\.");
        partesCaminho.clear();
        partesCaminho.addAll(Arrays.asList(partes));

    }

    /**
     *
     * Adiciona uma parte do caminho para o campo caminho.add(Parate)
     *
     * @param pParteCaminho
     */
    @Override
    public void addParteCaminho(String pParteCaminho) {
        partesCaminho.add(pParteCaminho);
    }

    /**
     *
     * @return O caminho em formato de uma string, exemplo:
     * usuario.localizacao.bairro
     */
    @Override
    public String getCaminhoCompletoString() {
        return caminhoComleto;
    }

    @Override
    public String getCaminhoSemNomeClasse() {
        int i = 0;
        String caminhoParcial = "";
        for (String parte : getCaminhoCompletoString().split("\\.")) {

            if (i > 0) {
                if (i > 1) {
                    caminhoParcial += "." + parte;
                } else {
                    caminhoParcial += parte;
                }
            }
            i++;

        }
        return caminhoParcial;
    }

    @Override
    public String getPrimeiroCaminhoSemNomeClasse() {
        int i = 0;
        String caminhoParcial = "";
        for (String parte : getCaminhoCompletoString().split("\\.")) {

            if (i > 0) {

                return parte;

            }
            i++;

        }
        return caminhoParcial;
    }

    @Override
    public List<String> getPartesCaminho() {
        return partesCaminho;
    }

    @Override
    public Field getCampoFieldReflection() {
        try {
            return UtilSBCoreReflexaoCaminhoCampo.getFieldByNomeCompletoCaminho(caminhoComleto);
        } catch (ErroCaminhoCampoNaoExiste e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo Field do Campo" + caminhoComleto, e);
            return null;
        }
    }

    @Override
    public String toString() {
        return getCaminhoCompletoString();
    }

    @Override
    public int getId() {
        return id; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int pID) {
        setId(pID); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUmCampoSeparador() {
        return UtilSBCoreReflexaoCaminhoCampo.isUmCampoSeparador(caminhoComleto);
    }

    @Override
    public boolean temUmTipoComOutrasPropriedades() {

        return caminhoComleto.split("\\.").length > 2;

    }

    @Override
    public boolean isUmTipoComOutrasPropriedades() {
        return getTipoCampo().isAnnotationPresent(InfoObjetoSB.class);
    }

    /**
     *
     *
     * @return O tipo do campo caso seja uma
     */
    @Override
    public Class getTipoCampo() {
        try {
            Field campoFieldReflection = UtilSBCoreReflexaoCaminhoCampo.getFieldByNomeCompletoCaminho(caminhoComleto);
            if (umCampoListavel) {

                ParameterizedType genericoTipo = (ParameterizedType) campoFieldReflection.getGenericType();
                Class tipoDaLista = (Class<?>) genericoTipo.getActualTypeArguments()[0];

                return tipoDaLista;
            } else {
                return campoFieldReflection.getType();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo tipo do campo" + caminhoComleto, t);
            return null;
        }
    }

    @Override
    public boolean isUmCampoListavel() {
        return umCampoListavel;
    }

    /**
     * Verifica se o campo é um campo com outras propriedades, podendo ser ou
     * não uma lista
     *
     * @return True quando o campo conter propriedades e for persistivel
     */
    @Override
    public boolean isUmaEntidade() {
        return umaEntidade;
    }

    @Override
    public boolean isUmCampoVinculado() {
        return umCampoVinculado;
    }

    @Override
    public String getUltimoNome() {
        return partesCaminho.get(partesCaminho.size() - 1);
    }

    @Override
    public List<String> getTodosCaminhosPossiveis() {
        List<String> caminhosPossiveis = new ArrayList();
        String novoCaminho = "";
        for (String parte : partesCaminho) {
            if (!novoCaminho.isEmpty()) {
                novoCaminho += "." + parte;
            } else {
                novoCaminho = parte;
            }

            caminhosPossiveis.add(novoCaminho);

        }
        return caminhosPossiveis;

    }

    @Override
    public List<String> getTodosCaminhosPossiveisSemUltimoParametro() {
        List<String> caminhosPossiveis = new ArrayList();
        String novoCaminho = "";
        int i = 0;
        for (String parte : partesCaminho) {
            if (!novoCaminho.isEmpty()) {
                novoCaminho += "." + parte;
            } else {
                novoCaminho = parte;
            }
            if (i < partesCaminho.size() - 1) {
                caminhosPossiveis.add(novoCaminho);
            }
            i++;
        }
        return caminhosPossiveis;

    }

    @Override
    public List<String> getTodasListas() {
        List<String> caminhosComLista = new ArrayList();
        return caminhosComLista;
    }

    @Override
    public String getLabel() {

        EstruturaCampo estrutura = MapaObjetosProjetoAtual.getEstruturaCampoPorCaminhoCompleto(getCaminhoComleto());
        if (estrutura == null) {
            return getCaminhoSemNomeClasse();
        }

        String label = estrutura.getLabel();

        if (label != null && !label.isEmpty()) {
            return label;
        }
        return getCaminhoSemNomeClasse();

    }

    @Override
    public TIPO_ORIGEM_VALOR_CAMPO getTipoRegistro() {
        return tipoRegistro;
    }

    @Override
    public String getCaminhoComleto() {
        return caminhoComleto;
    }

    @Override
    public void setCaminhoComleto(String caminhoComleto) {
        this.caminhoComleto = caminhoComleto;
    }

    @Override
    public String getCaminhoApenasClasseInicial() {
        return getPartesCaminho().get(0);
    }

    public String getCaminhoRelativoItemSublista() {
        try {
            String caminhoRelativo = caminhoComleto;
            if (caminhoRelativo.endsWith("[]")) {
                caminhoRelativo = caminhoRelativo.substring(0, caminhoRelativo.length() - 2);
            }
            if (caminhoRelativo.contains("[]")) {
                return caminhoRelativo.substring(caminhoRelativo.lastIndexOf("]") + 2);
            } else {
                return getCaminhoSemNomeClasse();
            }
        } catch (Throwable t) {
            return getCaminhoSemNomeClasse();
        }

    }

}
