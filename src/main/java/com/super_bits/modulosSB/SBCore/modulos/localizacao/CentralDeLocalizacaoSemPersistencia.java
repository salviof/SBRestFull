/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.localizacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep.ItemBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep.ItemCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanLocalizavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfUnidadeFederativa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class CentralDeLocalizacaoSemPersistencia implements ItfCentralLocalizacao {

    @Override
    public List<ItfUnidadeFederativa> getUnidadesFederativas() {
        return (List) FabUnidadesFederativasSemPersistencia.getTodos();
    }

    @Override
    public List<ItfCidade> gerarListaDeCidades(String pNomePesquisa, ItfUnidadeFederativa pUnidadeFederativa) {
        List<ItemCidade> cidadesEncontradas = new ArrayList<>();
        pUnidadeFederativa.getCidades().stream().filter((cidade) -> (cidade.getNome().contains(pNomePesquisa))).forEach((cidade) -> {
            cidadesEncontradas.add((ItemCidade) cidade);
        });
        return (List) cidadesEncontradas;

    }

    @Override
    public List<ItfBairro> gerarListaDeBairros(String pNomePesquisa, ItfCidade pCidade) {

        List<ItfBairro> cidadesEncontradas = new ArrayList<>();
        pCidade.getBairros().stream().filter((bairro) -> (bairro.getNome().contains(pNomePesquisa))).forEach((bairro) -> {
            cidadesEncontradas.add(bairro);
        });
        return (List) cidadesEncontradas;

    }

    @Override
    public List<ItfCidade> gerarListaDeCidades(String pNomePesquisa, ItfUnidadeFederativa pUnidadeFederativa, String parametroEspecial) {
        List<ItfCidade> cidadesEncontradas = new ArrayList<>();
        pUnidadeFederativa.getCidades().stream().filter((cidade) -> (cidade.getNome().contains(pNomePesquisa))).forEach((cidade) -> {
            cidadesEncontradas.add(cidade);
        });
        return (List) cidadesEncontradas;

    }

    @Override
    public List<ItfBairro> gerarListaDeBairros(String pNomePesquisa, ItfCidade pCidade, String parametroEspecial) {
        List<ItfBairro> bairrosEncontrados = SBCore.getCentralDeLocalizacao().gerarListaDeBairros(pNomePesquisa, pCidade);

        pCidade.getBairros().stream().filter((bairro) -> (bairro.getNome().contains(pNomePesquisa))).forEach((bairro) -> {
            bairrosEncontrados.add(bairro);
        });
        return (List) bairrosEncontrados;
    }

    @Override
    public void configurarPosicionamento(ItfLocal pLocal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configurarCep(ItfLocal pLocal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configurarEndereco(String cep, ItfLocal pLocal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean salvarFlexivel(ItfBeanLocalizavel pBeanLocalizava) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfBairro instanciarNovoBairo(String pBairro, ItfCidade pCidade) {
        ItemBairro bairro = new ItemBairro();
        bairro.setCidade(pCidade);
        bairro.setNome(pBairro);
        bairro.configIDPeloNome();
        return bairro;
    }

}
