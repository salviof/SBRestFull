/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.localizacao;

import br.org.coletivoJava.fw.api.erp.codigoPostal.br.ERPCodigoPostalBR;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanLocalizavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfUnidadeFederativa;
import java.util.List;

/**
 * ############################################################# <br/>
 * Esta Interface assina um dos serviços do núcleo SBCore
 * <br/>
 * Veja todos os serviços disponíveis digitando SBCore.getServico...
 * ############################################################# <br/>
 *
 * Serviço de gestão de CEP
 *
 *
 * @author salviof@gmail.com
 */
@Deprecated
public interface ItfCentralLocalizacao {

    public List<ItfUnidadeFederativa> getUnidadesFederativas();

    public List<ItfCidade> gerarListaDeCidades(String pNomePesquisa, ItfUnidadeFederativa pUnidadeFederativa);

    public List<ItfBairro> gerarListaDeBairros(String pNomePesquisa, ItfCidade pCidade);

    public List<ItfCidade> gerarListaDeCidades(String pNomePesquisa, ItfUnidadeFederativa pUnidadeFederativa, String parametroEspecial);

    public List<ItfBairro> gerarListaDeBairros(String pNomePesquisa, ItfCidade pCidade, String parametroEspecial);

    public void configurarPosicionamento(ItfLocal pLocal);

    public boolean salvarFlexivel(ItfBeanLocalizavel pBeanLocalizava);

    public void configurarCep(ItfLocal pLocal);

    public void configurarEndereco(String cep, ItfLocal pLocal);

    public ItfBairro instanciarNovoBairo(String pBairro, ItfCidade pCidade);

    public default ERPCodigoPostalBR getImplementacaoPadraoApiCep() {
        return ERPCodigoPostalBR.API_FREE_REDUNTANTE;
    }
}
