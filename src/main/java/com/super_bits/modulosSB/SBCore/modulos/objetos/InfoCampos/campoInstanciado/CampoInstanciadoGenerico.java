/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaPesquisaFonetica;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringComparador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidacao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfValidacao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.estadoFormulario.FabEstadoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ItfCalculoValorLogicoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.ItfPropriedadesReflexaoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.PropriedadesReflexaoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.AtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.LATITUDE;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.LCCEP;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.LC_BAIRRO;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.LC_CAMPO_ABERTO;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.LC_CIDADE;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.LC_COMPLEMENTO_E_NUMERO;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.LC_LOCALIDADE;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.LC_LOGRADOURO;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.LC_UNIDADE_FEDERATIVA;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.Longitude;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfTipoAtributoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_ORIGEM_VALOR_CAMPO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TipoAtributoMetodosBase;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.FabFamiliaCompVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs;
import static com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs.TEXTO_COM_FORMATACAO;
import static com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs.TEXTO_SEM_FORMATACAO;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItem;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSistema;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * novo
 *
 * TODO-> Utilizar padrão decorator
 *
 * @author sfurbino
 */
public abstract class CampoInstanciadoGenerico extends CampoInstanciadoBase implements ItfCampoInstanciado, ItfAtributoObjetoSB {

    private ItfCampoInstArquivoEntidade campoArquivoEntidade;
    private ItfAssistenteDeLocalizacao campoLocalizacao;
    private ItfCampoInstSeletorItens campoSeletorItens;
    private ItfCampoInstSeletorItem campoSeletorItem;
    private CampoInstanciadoSubItens campoSubItens;
    private CampoInstanciadoEnumFabricaObjeto campoInstanciadoEnum;

    private ItfCampoInstanciadoVerdadeiroOuFalso campoInstanciadoVerdadeiroOuFalso;
    private ItfCampoInstTemplate campoInstanciadoTemplate;
    private ItfCampoInstanciado campoInstanciadoPai;
    private boolean valorModificado;
    protected final ItfAtributoObjetoEditavel atributoAssociado;
    private final PropriedadesReflexaoCampo propriedadesReflexao;
    private ItfValidacao validacaoLogica;
    private ItfCalculoValorLogicoAtributoObjeto valorLogicoEstrategia;

    private FabEstadoFormulario statusFormulario = FabEstadoFormulario.INDEFINIDO;
    private int indiceValorLista = -1;
    // TODO, Justificativas para alteração de Campos
    private List<String> justificativasAlteracaoCampos;

    /**
     *
     * Constructor padrão para campo Instanciado Generico
     *
     * @param pCampoReflection
     */
    public CampoInstanciadoGenerico(Field pCampoReflection) {

        super(pCampoReflection);
        try {
            propriedadesReflexao = new PropriedadesReflexaoCampo(campoReflection);
            atributoAssociado = new AtributoObjetoSB(propriedadesReflexao);
            propriedadesReflexao.aplicarAnotacoesEmAtributo(atributoAssociado);

        } catch (Throwable t) {
            String nomeCampo = "Indefinido";
            if (pCampoReflection != null) {
                nomeCampo = pCampoReflection.getName();
            }

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando campo instanciado generico [" + nomeCampo + "]", t);
            throw new UnsupportedOperationException("impossível criar o campo instanciado para " + pCampoReflection + " " + nomeCampo);
        }
    }

    /**
     *
     * Constructor que desacopla as anotações do Field, com o campo instanciado
     *
     * @param pCampoSetValorReflection
     * @param pCampoDinamico
     *
     *
     *
     *
     */
    public CampoInstanciadoGenerico(Field pCampoSetValorReflection, ItfAtributoObjetoEditavel pCampoDinamico) {

        super(pCampoSetValorReflection);
        try {
            if (pCampoDinamico == null) {
                throw new UnsupportedOperationException("os Atributos do campo dinamico não foram definidos, impossível instanciar o campo");
            }
            if (pCampoDinamico.getFabricaTipoAtributo() == null) {
                throw new UnsupportedOperationException("O tipo basico do atributo não foi definido para " + pCampoDinamico.getLabelPadrao());
            }
            atributoAssociado = pCampoDinamico;
            propriedadesReflexao = new PropriedadesReflexaoCampo(campoReflection);

        } catch (Throwable t) {

            String nomeCampo = "Indefinido";
            if (pCampoSetValorReflection != null) {
                nomeCampo = pCampoSetValorReflection.getName();
            }

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando campo instanciado generico [" + nomeCampo + "]", t);
            throw new UnsupportedOperationException("impossível criar o campo instanciado para " + pCampoDinamico + " " + nomeCampo);
        }

    }

    @Override
    public boolean isUmCampoArquivoEntidade() {
        return getFabricaTipoAtributo().isCampoUmArquivoDeEntidade();
    }

    @Override
    public String getNomeCamponaClasse() {
        return campoReflection.getNomeDeclaracao();
    }

    @Override
    public boolean isVazio() {
        switch (getFabricaTipoAtributo()) {
            case ARQUIVO_DE_ENTIDADE:
                return !getComoArquivoDeEntidade().isExisteArquivo();
            default:
                return (UtilSBCoreStringValidador.isNuloOuEmbranco(getValor()));
        }

    }

    @Override
    public boolean isUmCampoNaoInstanciado() {

        return getLabel().equals(CampoNaoImplementado.LABEL_NAO_IMPLEMENTADO);

    }

    @Override
    public String getLabelSlug() {
        return UtilSBCoreStringFiltros.gerarUrlAmigavel(getLabel());
    }

    @Override
    public int getIndiceValorLista() {
        return indiceValorLista;
    }

    @Override
    public void setIndiceValorLista(int pIndice) {
        indiceValorLista = pIndice;
    }

    @Override
    public String getNomeUnicoParaIDHtml(ItfComponenteVisualSB pComponente) {
        if (pComponente != null) {
            return getPrefixoUnicoParaIDHtml() + "_" + pComponente.getClasseCSS();
        } else {
            return getPrefixoUnicoParaIDHtml() + "_" + campoReflection.getNomeDeclaracao();
        }
    }

    @Override
    public String getPrefixoUnicoParaIDHtml() {
        return campoReflection.getClasseOndeOCampoEstaDeclarado().getSimpleName() + "_" + campoReflection.getNomeDeclaracao();
    }

    @Override
    public ItfComponenteVisualSB getComponenteVisualPadrao() {
        FabTipoAtributoObjeto tipoAtributo = getFabricaTipoAtributo();
        if (tipoAtributo == null) {
            throw new UnsupportedOperationException("o tipo de atributo do campo não foi configurado em " + getLabel() + getNomeDoObjeto());
        }
        ItfComponenteVisualSB componente = tipoAtributo.getTipo_input_prime().getRegistro();
        if (tipoAtributo.isCampoLocalizacao()) {
            return componente;
        }

        if (componente != null) {

            if (atributoAssociado.isUmValorComLista()) {
                if (atributoAssociado.getFabricaTipoAtributo().equals(FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)) {
                    return componente;
                }
                if (atributoAssociado.getFabricaTipoAtributo().equals(FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)) {
                    if (getFabricaTipoAtributo().getTipo_input_prime().getFamilia().equals(FabFamiliaCompVisual.SELETOR_ITEM)) {

                        if (campoReflection.isUmObjetoSB()) {

                            int maximoRegistros = SBCore.getCentralFonteDeDados().getNumeroMaximoRegistros(this);
                            if (maximoRegistros == 2) {

                                //TODO MENU BOTOES
                                //     return FabCompVisualSeletorItem.BOTOES_MENU.getRegistro();
                                return FabCompVisualSeletorItem.COMBO.getRegistro();
                            }
                            if (maximoRegistros == 3) {
                                //TODO MENU GRADE
                                //    return FabCompVisualSeletorItem.GRADE.getRegistro();
                                return FabCompVisualSeletorItem.COMBO.getRegistro();
                            }
                            if (maximoRegistros < 10) {
                                return FabCompVisualSeletorItem.COMBO.getRegistro();
                            }
                            return FabCompVisualSeletorItem.AUTO_COMPLETE.getRegistro();
                        }
                    }
                }
                return componente;
            }

        }

        if (getFabricaTipoAtributo().getTipo_input_prime().getFamilia().equals(FabFamiliaCompVisual.INPUT)) {
            FabCompVisualInputs campoFamiliaInput = (FabCompVisualInputs) getFabricaTipoAtributo().getTipo_input_prime();
            switch (campoFamiliaInput) {
                case TEXTO_COM_FORMATACAO:
                case TEXTO_SEM_FORMATACAO:
                    if (isTemMascara() || isTemValidacaoRegex()) {
                        return FabCompVisualInputs.TEXTO_COM_FORMATACAO.getRegistro();
                    } else {
                        return FabCompVisualInputs.TEXTO_SEM_FORMATACAO.getRegistro();
                    }
                default:
                    return componente;
            }
        }

        return getFabricaTipoAtributo().getTipo_input_prime().getFamilia().getComponentePadrao();

    }

    @Override
    public ItfComponenteVisualSB getComponenteDiferenciado(ItfComponenteVisualSB pComponente) {

        //caso não tenha sido enviado um componente diferenciado, retorna o componente do campo
        if (pComponente == null) {
            return getComponenteVisualPadrao();
        }

        // caso o componente diferenciado seja igual ao componente padrão da familia de componentes, retorna o compoenente do campo
        //if (pComponente.getXhtmlJSF().equals(getFabricaTipoAtributo().getTipo_input_prime().getFamilia().getComponentePadrao().getXhtmlJSF())) {
        //    return getComponenteVisualPadrao();
        //}
        ///caso o componente enviado não seja da familia retornar o xhtml campo incompativel
        if (getComponenteVisualPadrao().getFamilia().equals(pComponente.getFamilia())) {
            return pComponente;
        } else {
            return FabCompVisualSistema.INCOMPATIVEL.getRegistro();
        }
    }

    @Override
    public boolean isTemDescricao() {

        return !(getDescricao() == null || getDescricao().isEmpty() || getDescricao().equals(getLabel()) || getDescricao().length() < 4);

    }

    /**
     *
     *
     * @see ItfTipoAtributoSB#getFabricaTipoAtributo()
     */
    @Override
    public FabTipoAtributoObjeto getFabricaTipoAtributo() {
        return atributoAssociado.getFabricaTipoAtributo();
    }

    /**
     * @see ItfTipoAtributoSB#getTipoPrimitivoDoValor()
     */
    @Override
    public TIPO_PRIMITIVO getTipoPrimitivoDoValor() {
        return atributoAssociado.getTipoPrimitivoDoValor();
    }

    /**
     * @return @see ItfTipoAtributoSB#getOrigemValor()
     */
    @Override
    public TIPO_ORIGEM_VALOR_CAMPO getOrigemValor() {
        return atributoAssociado.getOrigemValor();
    }

    /**
     * @return @see ItfTipoAtributoSB#getTipoDeclaracao()
     */
    /**
     * @see ItfTipoAtributoSB#getTipoVisualizacao()
     */
    @Override
    public String getTipoVisualizacao() {
        return atributoAssociado.getTipoVisualizacao();
    }

    /**
     * @see ItfTipoAtributoSB#getLabel()
     */
    @Override
    public String getLabel() {
        return atributoAssociado.getLabel();
    }

    /**
     * @see ItfTipoAtributoSB#isSomenteLeitura()
     */
    @Override
    public boolean isSomenteLeitura() {
        if (statusFormulario.equals(FabEstadoFormulario.VISUALIZAR)) {
            return true;
        }
        return atributoAssociado.isSomenteLeitura();
    }

    /**
     * @see ItfTipoAtributoSB#getIdComponente()
     */
    @Override
    public String getIdComponente() {
        return atributoAssociado.getIdComponente();
    }

    /**
     * @see ItfTipoAtributoSB#getDescricao()
     */
    @Override
    public String getDescricao() {

        return atributoAssociado.getDescricao();
    }

    /**
     * @see ItfTipoAtributoSB#getMascara()
     */
    @Override
    public String getMascara() {
        return atributoAssociado.getMascara();
    }

    /**
     * @see ItfTipoAtributoSB#getValorPadrao()
     */
    @Override
    public String getValorPadrao() {
        return atributoAssociado.getValorPadrao();
    }

    /**
     * @see ItfTipoAtributoSB#isObrigatorio()
     */
    @Override
    public boolean isObrigatorio() {
        return atributoAssociado.isObrigatorio();
    }

    /**
     * @see ItfTipoAtributoSB#getValorMaximo()
     */
    @Override
    public int getValorMaximo() {
        return atributoAssociado.getValorMaximo();

    }

    /**
     * @see ItfTipoAtributoSB#getValorMinimo()
     */
    @Override
    public int getValorMinimo() {
        return atributoAssociado.getValorMinimo();
    }

    /**
     * @return @see ItfTipoAtributoSB#getListaDeOpcoes()
     */
    @Override
    public List<ItfBeanSimples> getListaDeOpcoes() {

        return SBCore.getCentralFonteDeDados().getListaOpcoesCampoInstanciado(this);

    }

    /**
     * @see ItfTipoAtributoSB#getValidacaoRegex()
     */
    @Override
    public String getValidacaoRegex() {
        return atributoAssociado.getValidacaoRegex();
    }

    /**
     * @see ItfTipoAtributoSB#isTemValidacaoRegex()
     */
    @Override
    public boolean isTemValidacaoRegex() {
        return atributoAssociado.isTemValidacaoRegex();
    }

    /**
     * @see ItfTipoAtributoSB#isTemValidacaoMinimo()
     */
    @Override
    public boolean isTemValidacaoMinimo() {
        return atributoAssociado.isTemValidacaoMinimo();
    }

    /**
     * @see ItfTipoAtributoSB#isTemValidacaoMaximo()
     */
    @Override
    public boolean isTemValidacaoMaximo() {
        return atributoAssociado.isTemValidacaoMaximo();
    }

    /**
     * @see ItfTipoAtributoSB#isTemMascara()
     */
    @Override
    public boolean isTemMascara() {
        return atributoAssociado.isTemMascara();
    }

    /**
     * @see ItfTipoAtributoSB#isNumeral()
     */
    @Override
    public boolean isNumeral() {
        return atributoAssociado.isNumeral();
    }

    /**
     * @see ItfTipoAtributoSB#isMoeda()
     */
    @Override
    public boolean isMoeda() {
        return atributoAssociado.isMoeda();
    }

    /**
     * @see ItfTipoAtributoSB#getSeparadorDecimal()
     */
    @Override
    public char getSeparadorDecimal() {
        return atributoAssociado.getSeparadorDecimal();
    }

    /**
     * @see ItfTipoAtributoSB#getSeparadorMilhar()
     */
    @Override
    public char getSeparadorMilhar() {
        return atributoAssociado.getSeparadorMilhar();
    }

    /**
     * @see ItfTipoAtributoSB#<error>
     */
    @Override
    public int getNumeroDeCasasDecimais() {
        return atributoAssociado.getNumeroDeCasasDecimais();
    }

    /**
     * @see ItfTipoAtributoSB#getMascaraJqueryMode()
     */
    @Override
    public String getMascaraJqueryMode() {
        return atributoAssociado.getMascaraJqueryMode();
    }

    /**
     * @see ItfTipoAtributoSB#getTipoCampoSTR()
     */
    @Override
    public String getTipoCampoSTR() {
        return atributoAssociado.getTipoCampoSTR();
    }

    /**
     * @see ItfTipoAtributoSB#getFraseValidacao()
     */
    @Override
    public String getFraseValidacao() {
        return atributoAssociado.getFraseValidacao();
    }

    /**
     * @see ItfTipoAtributoSB#isUmValorLivre()
     */
    @Override
    public boolean isUmValorLivre() {
        return atributoAssociado.isUmValorLivre();
    }

    /**
     * @see ItfTipoAtributoSB#isUmValorMultiploComLista()
     */
    @Override
    public boolean isUmValorComLista() {
        return atributoAssociado.isUmValorComLista();
    }

    /**
     * @see ItfTipoAtributoSB#isUmValorMultiploComLista()
     */
    @Override
    public boolean isUmValorMultiploLivre() {
        return atributoAssociado.isUmValorMultiploLivre();
    }

    /**
     * @see ItfTipoAtributoSB#isUmValorMultiploComLista()
     *
     */
    @Override
    public boolean isUmValorMultiploComLista() {
        return atributoAssociado.isUmValorMultiploComLista();
    }

    @Override
    public String getNomeCurto() {
        return atributoAssociado.getNomeCurto();
    }

    @Override
    public String getNome() {
        return atributoAssociado.getNome();
    }

    @Override
    public String getIconeDaClasse() {
        return atributoAssociado.getIconeDaClasse();
    }

    @Override
    public String getXhtmlVisao() {
        return atributoAssociado.getXhtmlVisao();
    }

    @Override
    public String getImgPequena() {
        return atributoAssociado.getImgPequena();
    }

    @Override
    public int configIDPeloNome() {
        return atributoAssociado.configIDPeloNome();
    }

    @Override
    public String getNomeDoObjeto() {
        return atributoAssociado.getNomeDoObjeto();
    }

    @Override
    public String getNomeDoObjetoPlural() {
        return atributoAssociado.getNomeDoObjetoPlural();
    }

    @Override
    public void adicionarItemNaLista(String nomeDaLista
    ) {
        atributoAssociado.adicionarItemNaLista(nomeDaLista);
    }

    /**
     *
     * @param pCampo
     * @return
     */
    @Override
    public boolean isTemCampoAnotado(FabTipoAtributoObjeto pCampo
    ) {
        return atributoAssociado.isTemCampoAnotado(pCampo);
    }

    @Override
    public int getId() {
        return atributoAssociado.getId();
    }

    @Override
    public ItfCampoInstSeletorItens getComoSeltorItens() {

        try {
            if (getFabricaTipoAtributo() != FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS) {
                throw new UnsupportedOperationException("O Campo instanciado [" + getNome() + "] não é do tipo Seletor de Itens, impossível iniciar ");

            }
            if (campoSeletorItens == null) {
                if (getValor() == null) {
                    setValor(new ArrayList());
                }
                campoSeletorItens = new CampoInstanciadoSeletorDeItens(this);
            }
            return campoSeletorItens;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro otendo campo de arquivo de entidade em" + getObjetoDoAtributo() + " campo" + getNomeCamponaClasse(), t);
            return null;

        }

    }

    @Override
    public ItfCampoInstSeletorItem getComoCampoSeltorItem() {

        try {

            if (!getFabricaTipoAtributo().isUmCampoDeObjetoComListaDeOpcoes()) {
                return null;
                //throw new UnsupportedOperationException("O Campo instanciado  [" + getNome() + "]  NÃO é do tipo Seletor de IteM, impossível iniciar ");

            }
            if (campoSeletorItem == null) {
                campoSeletorItem = new CampoInstanciadoSeletorDeItem(this);
            }
            return campoSeletorItem;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro otendo campo de arquivo de entidade em" + getObjetoDoAtributo() + " campo" + getNomeCamponaClasse(), t);
            return null;

        }

    }

    @Override
    public ItfCampoInstSeletor getComoCampoComListaDeOpcoes() {

        try {

            switch (getFabricaTipoAtributo()) {

                case OBJETO_DE_UMA_LISTA:
                    return getComoCampoSeltorItem();
                case ENUM_FABRICA:
                    throw new UnsupportedOperationException("Os campos do tipo enum ainda não são compatíveis com Lista de opção , este componente possui seus próprio métodos idependentes ");
                case LISTA_OBJETOS_PUBLICOS:

                case LISTA_OBJETOS_PARTICULARES:
                    return getComoSeltorItens();
                default:
                    throw new UnsupportedOperationException("O atributo " + getNome() + "Não é compatível com listagem de opções");

            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro otendo campo com lista de oções entidade em" + getObjetoDoAtributo() + " campo" + getNomeCamponaClasse(), t);
            return null;

        }

    }

    @Override
    public ItfCampoInstanciadoVerdadeiroOuFalso getComoCampoVerdadeiroOuFalso() {

        try {

            if (campoInstanciadoVerdadeiroOuFalso == null) {
                campoInstanciadoVerdadeiroOuFalso = new CampoInstanciadoVerdadeiroOuFalso(this);
            }
            return campoInstanciadoVerdadeiroOuFalso;
            //alteracao de atualizacao
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro otendo campo como Verdadeiro ou falso em" + getObjetoDoAtributo() + " campo" + getNomeCamponaClasse(), t);
            return null;
        }

    }

    @Override
    public ItfCampoInstArquivoEntidade getComoArquivoDeEntidade() {
        try {
            if (!getFabricaTipoAtributo().isCampoUmArquivoDeEntidade()) {
                return null;
            }
            if (campoArquivoEntidade == null) {
                campoArquivoEntidade = new CampoInstanciadoArquivoDeEntidadeGenerico(this);
            }
            return campoArquivoEntidade;
            //alteracao de atualizacao
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro otendo campo de arquivo de entidade em" + getObjetoDoAtributo() + " campo" + getNomeCamponaClasse(), t);
            return null;
        }
    }

    @Override
    public ItfCampoInstTemplate getComoTemplate() {
        try {
            if (campoInstanciadoTemplate == null) {
                if (!getFabricaTipoAtributo().equals(FabTipoAtributoObjeto.HTML_TEMPLATE)) {
                    throw new UnsupportedOperationException("O campo " + getLabel() + " não é do tipo Template");
                }
                campoInstanciadoTemplate = new CampoInstanciadoTemplate(this);
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo campo instanciado como template", t);
        }
        return campoInstanciadoTemplate;
    }

    @Override
    public ItfAssistenteDeLocalizacao getComoCampoLocalizacao() {
        if (campoLocalizacao != null) {
            return campoLocalizacao;
        }
        try {

            switch (getFabricaTipoAtributo()) {

                case LATITUDE:
                case Longitude:
                case LC_LOGRADOURO:
                case LCCEP:
                case LC_BAIRRO:
                case LC_CIDADE:
                case LC_LOCALIDADE:
                case LC_UNIDADE_FEDERATIVA:
                case LC_COMPLEMENTO_E_NUMERO:
                case LC_CAMPO_ABERTO:

                    campoLocalizacao = new CampoInstanciadoLocalizacao(this);
                    return campoLocalizacao;

            }

            if (getFabricaTipoAtributo() != FabTipoAtributoObjeto.LC_LOCALIZACAO) {
                //  throw new UnsupportedOperationException("O TipoAtributoObjetoSB instanciado não é do tipo localização, impossível iniciar ");
                return null;
            }
            if (campoLocalizacao == null) {
                campoLocalizacao = new CampoInstanciadoLocalizacao(this);
            }
            return campoLocalizacao;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro otendo campo de arquivo de entidade em" + getObjetoDoAtributo() + " campo" + getNomeCamponaClasse(), t);
            return null;

        }
    }

    @Override
    public boolean isUmCampoDinamico() {
        return atributoAssociado.isUmCampoDinamico();
    }

    @Override
    public String getCaminhoListagem() {
        return atributoAssociado.getCaminhoListagem();

    }

    @Override
    public String getNomeClasseOrigemAtributo() {

        return atributoAssociado.getNomeClasseOrigemAtributo();
    }

    @Override
    public String getNomeClasseAtributoDeclarado() {
        return atributoAssociado.getNomeClasseOrigemAtributo();
    }

    @Override
    public void setNomeClasseAtributoDeclarado(String pObjetoEntidade) {
        atributoAssociado.setNomeClasseAtributoDeclarado(pObjetoEntidade);
    }

    @Override
    public void setNomeClasseOrigemAtributo(String pObjeto) {
        atributoAssociado.setNomeClasseOrigemAtributo(pObjeto);
    }

    @Override
    public GrupoCampos getGrupoSubCamposExibicao() {
        return (GrupoCampos) atributoAssociado.getGrupoSubCamposExibicao();
    }

    @Override
    public CampoInstanciadoEnumFabricaObjeto getComoEnumFabricaObjeto() {
        try {
            if (!atributoAssociado.getFabricaTipoAtributo().equals(FabTipoAtributoObjeto.ENUM_FABRICA)) {
                //   throw new UnsupportedOperationException("O Campo não é do tipo enumFabricaObjeto");
                return null;
            }
            if (campoInstanciadoEnum == null) {
                campoInstanciadoEnum = new CampoInstanciadoEnumFabricaObjeto(propriedadesReflexao.getClasseDeclaracaoAtributo(), this);
            }
            return campoInstanciadoEnum;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "", t);
            return null;
        }
    }

    @Override
    public CampoInstanciadoSubItens getComoSubItens() {
        try {
            if (!atributoAssociado.getFabricaTipoAtributo().equals(FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)) {
                throw new UnsupportedOperationException("O Campo não é do tipo Lista Objetos particulares");

            }

            if (campoSubItens == null) {
                campoSubItens = new CampoInstanciadoSubItens(this);
            }
            return campoSubItens;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo campo como SubItem", t);
            return null;
        }
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciadoRaiz() {
        return campoInstanciadoPai;
    }

    @Override
    public void setCampoInstanciadoRaiz(ItfCampoInstanciado pCampoInstanciado) {
        campoInstanciadoPai = pCampoInstanciado;
    }

    @Override
    public String toString() {
        return getObjetoDoAtributo().getClass().getSimpleName() + "." + getNomeCamponaClasse();
    }

    @Override
    public String getNomeCompostoIdentificador() {
        if (getCampoInstanciadoRaiz() != null) {
            return getCampoInstanciadoRaiz().getNomeCamponaClasse() + "." + getNomeCamponaClasse();
        } else {
            return getNomeCamponaClasse();
        }
    }

    @Override
    public boolean isCampoNaoInstanciado() {
        return this.equals(new CampoNaoImplementado());
    }

    @Override
    public ItfComponenteVisualSB getComponenteVisualPadraoCompacto() {
        return getComponenteVisualPadrao();
    }

    @Override
    public List<String> getTemplateCamposDinamicos() {
        return atributoAssociado.getTemplateCamposDinamicos();
    }

    @Override
    public ItfPropriedadesReflexaoCampos getPropriedadesRefexao() {

        return propriedadesReflexao;
    }

    @Override
    public boolean isUmValorNuloOuEmBranco() {
        if (isCampoNaoInstanciado()) {
            return true;
        }
        if (getValor() == null) {
            return true;
        }
        if (getValor().toString().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public String getValorTextoFormatado() {
        Object valor = getValor();
        switch (atributoAssociado.getFabricaTipoAtributo()) {

            case QUANTIDADE:
                if (valor == null) {
                    return "-";
                }
                String valorComoString = String.valueOf(valor).replace(".", ",");

                return valorComoString;
            case MOEDA_REAL:

                if (valor instanceof Double) {
                    try {
                        double valorDbl = (double) valor;
                        if (valorDbl == 0) {
                            return " - ";
                        }

                        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ITALY);
                        DecimalFormat df = (DecimalFormat) nf;
                        df.setMinimumFractionDigits(2);
                        String valorFormat = df.format(valorDbl);
                        System.out.println(valorFormat);
                        return "R$ " + valorFormat;
                    } catch (Throwable t) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro formatando valor moeda real ", t);
                    }
                }
                if (valor == null) {
                    return " - ";
                }
                return "R$ " + String.valueOf(getValor()).replace(".", ",");

            case DATA:
            case DATAHORA:
                if (valor == null) {
                    return "Data não Informada";
                }
                Date data = (Date) valor;
                return UtilSBCoreDataHora.converteDataEmStringFormatada(data);

            case VERDADEIRO_FALSO:
                if (valor == null) {
                    return "Não";
                } else {
                    boolean valorComoboolean = (boolean) valor;
                    if (valorComoboolean) {
                        return "Sim";
                    } else {
                        return "Não";
                    }
                }

            default: {
                if (getValor() == null) {
                    return "Ñ Informado";
                } else {
                    if (valor instanceof ItfBeanSimplesSomenteLeitura) {
                        return ((ItfBeanSimplesSomenteLeitura) valor).getNome();
                    }
                    return getValor().toString();
                }
            }

        }

    }

    @Override
    public boolean isUmaListagemParticular() {
        return atributoAssociado.getFabricaTipoAtributo().equals(FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES);
    }

    @Override
    public boolean isUmCampoCampoLocalizacao() {
        return atributoAssociado.getFabricaTipoAtributo().isCampoLocalizacao();
    }

    @Override
    public String getIconeNegativo() {
        return atributoAssociado.getIconeNegativo();
    }

    @Override
    public String getIconePositivo() {
        return atributoAssociado.getIconePositivo();
    }

    @Override
    public String getTextoNegativo() {
        return atributoAssociado.getTextoNegativo();
    }

    @Override
    public String getTextoPositivo() {
        return atributoAssociado.getTextoPositivo();
    }

    @Override
    public String getLabelPadrao() {
        return atributoAssociado.getLabelPadrao();
    }

    @Override
    public GrupoCampos getGrupoCampoExibicao() {
        return (GrupoCampos) atributoAssociado.getGrupoSubCamposExibicao();
    }

    @Override
    public boolean isValorCampoUnico() {
        return atributoAssociado.isValorCampoUnico();
    }

    @Override
    public boolean validarCampo() {
        try {
            if (atributoAssociado.isUmValorLogico()) {

                if (!atributoAssociado.isTemValidadacaoLogica()) {
                    return true;
                }

            }
            if (atributoAssociado.isObrigatorio()) {
                if (isUmCampoArquivoEntidade()) {
                    if (!getComoArquivoDeEntidade().isExisteArquivo()) {
                        return false;
                    } else {
                        return true;
                    }
                }
                return UtilSBCoreValidacao.validacoesBasicas(this, getValor());
            } else {
                if (isUmCampoArquivoEntidade()) {
                    return true;
                }
                if (isUmCampoCampoLocalizacao()) {
                    //Caso a localização seja inválida, e este valor não for obrigatório, Configura valor nulo
                    if (!UtilSBCoreValidacao.gerarMensagensValidacao(this, getValor(), valorModificado, false).isEmpty()) {
                        setValor(null);
                    }

                } else {
                    if (getValor() != null) {
                        return UtilSBCoreValidacao.validacoesBasicas(this, getValor());
                    }
                }
            }
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Indetermnando tentando validar: " + getNomeCompostoIdentificador(), t);
            return true;
        }

    }

    public boolean isUmItemDeUmaLista() {
        return getFabricaTipoAtributo().isUmCampoDeObjetoComListaDeOpcoes();

    }

    @Override
    public boolean isUmaListaDinamica() {
        return atributoAssociado.isUmaListaDinamica();
    }

    @Override
    public boolean isUmValorLogico() {
        return isPossuiValorCalculoDinamico();
    }

    @Override
    public boolean isTemValidadacaoLogica() {
        return atributoAssociado.isTemValidadacaoLogica();
    }

    @Override
    public boolean contem(Object pParametro) {
        Object valor = getValor();
        if (pParametro == null) {
            return valor == null;
        }
        if (valor == null) {
            return false;
        }
        try {

            return UtilSBCoreStringComparador.isParecido(valor.toString(), pParametro.toString());

        } catch (Throwable t) {
            return false;
        }
    }

    @Override
    @Deprecated
    public boolean contem(Object pParametro, int pIndice) {
        Object valor = getValor();
        if (pParametro == null) {
            return valor == null;
        }
        if (valor == null) {
            return false;
        }

        try {
            if (pParametro.toString().toLowerCase().contains(valor.toString().toLowerCase())) {
                return true;
            } else {
                return MapaPesquisaFonetica.isValorFoneticoCompativel(this, pParametro.toString());
            }

        } catch (Throwable t) {
            return false;
        }
    }

    @Override
    public void preenchimentoAleatorio() {
        try {
            if (isCampoNaoInstanciado()) {
                return;
            }
            TipoAtributoMetodosBase tipoAtributo = new TipoAtributoMetodosBase(getPropriedadesRefexao().getAtributoGerado().getFabricaTipoAtributo());
            Object valorGerado = tipoAtributo.getValorAleatorioEmConformidade(this);
            boolean valorNaoPrenchido = false;
            try {
                if (isUmItemDeUmaLista()) {
                    try {
                        ItfBeanSimplesSomenteLeitura itemSelecionado = (ItfBeanSimplesSomenteLeitura) getValor();
                        if (itemSelecionado.getId() == 0 && UtilSBCoreStringValidador.isNuloOuEmbranco(itemSelecionado.getNome())) {
                            valorNaoPrenchido = true;
                        }
                    } catch (Throwable t) {

                    }
                } else if (isUmaListaDinamica()) {

                } else if (isUmaListagemParticular()) {

                } else {
                    valorNaoPrenchido = (getValor() == null || getValor().toString().isEmpty());
                }
            } catch (Throwable t) {

            }

            if ((valorNaoPrenchido)) {
                if (isUmCampoCampoLocalizacao()) {
                    if (valorGerado != null) {
                        if (getTipoCampoSTR().equals(FabTipoAtributoObjeto.LCCEP.toString())) {
                            getComoCampoLocalizacao().setCep((String) valorGerado);
                        }
                    }
                    if (getTipoCampoSTR().equals(FabTipoAtributoObjeto.LC_LOCALIZACAO.toString())) {
                        TipoAtributoMetodosBase tipoAtributoCep = new TipoAtributoMetodosBase(FabTipoAtributoObjeto.LCCEP);
                        String valorGeradoCep = (String) tipoAtributoCep.getValorAleatorioEmConformidade();
                        getObjetoDoAtributo().getCampoInstanciadoByNomeOuAnotacao(getNomeCamponaClasse() + ".cep").getComoCampoLocalizacao().setCep(valorGeradoCep);
                    }

                } else {
                    if (valorGerado != null) {
                        setValor(valorGerado);
                    }
                }
            }

        } catch (Throwable t) {
            SBCore.RelatarErroAoUsuario(FabErro.SOLICITAR_REPARO, "Erro preenchimento aleatório em" + getNomeCamponaClasse(), t);
        }
    }

    @Override
    public boolean validarCampo(Object pValor) {

        return UtilSBCoreValidacao.validacoesBasicas(this, pValor);
    }

    @Override
    public String getXhtmlVisao(int numeroColunas) {
        return atributoAssociado.getXhtmlVisao();
    }

    @Override
    public String getXhtmlVisaoMobile() {
        return atributoAssociado.getXhtmlVisaoMobile();
    }

    @Override
    public boolean isUmValorEmLista() {
        switch (atributoAssociado.getFabricaTipoAtributo()) {
            case LISTA_OBJETOS_PUBLICOS:
            case LISTA_OBJETOS_PARTICULARES:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setStatusFormularioExibicao(FabEstadoFormulario pStatusForm) {
        statusFormulario = pStatusForm;
    }

    public ItfAtributoObjetoSB getAtributosDoObjeto() {
        return atributoAssociado;
    }

    @Override
    public boolean isPermitirCadastroManualEndereco() {
        return atributoAssociado.isPermitirCadastroManualEndereco();
    }

    @Override
    public boolean isAtualizarValorLogicoAoPersistir() {
        return atributoAssociado.isAtualizarValorLogicoAoPersistir();
    }

    @Override
    public ItfCalculoValorLogicoAtributoObjeto getValorLogicaEstrategia() {
        if (isCampoNaoInstanciado()) {
            return null;
        }

        if (valorLogicoEstrategia != null) {
            return valorLogicoEstrategia;
        } else {

            try {
                Class<? extends ItfCalculoValorLogicoAtributoObjeto> implementacaoCalculo
                        = MapaObjetosProjetoAtual.getEstruturaObjeto(getObjetoDoAtributo().getClass()).
                                getClasseImplementacaoValorLogico(campoReflection.getNomeDeclaracao());

                if (implementacaoCalculo == null) {
                    System.out.println("");
                    MapaObjetosProjetoAtual.getEstruturaObjeto(getObjetoDoAtributo().getClass()).
                            getClasseImplementacaoValorLogico(campoReflection.getNomeDeclaracao());
                }

                try {
                    valorLogicoEstrategia = implementacaoCalculo.getConstructor(ItfCampoInstanciado.class).newInstance(this);

                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(CampoInstanciadoGenerico.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (NoSuchMethodException | SecurityException ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo instancia do valor lógico", ex);
                return null;
            }

        }
        return valorLogicoEstrategia;

    }

    @Override
    public ItfValidacao getValidacaoLogicaEstrategia() {
        if (isCampoNaoInstanciado()) {
            return null;
        }

        if (validacaoLogica != null) {
            return validacaoLogica;
        } else {

            if (validacaoLogica == null) {
                validacaoLogica = UtilSBCoreReflexaoCaminhoCampo.getValidadorDoCampoInstanciado((ItfCampoInstanciado) this);
            }
        }
        return validacaoLogica;

    }

    @Override
    public Object getValorValidando() {
        return super.getValorValidando(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValorValidando(Object pValor) {

        try {
            setValorSeValido(pValor);
        } catch (ErroValidacao ex) {
            SBCore.enviarMensagemUsuario(ex.getMensagemAoUsuario(), FabMensagens.ALERTA);
        }

    }

}
