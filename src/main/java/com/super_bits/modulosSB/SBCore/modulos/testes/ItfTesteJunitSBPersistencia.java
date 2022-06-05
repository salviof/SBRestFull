/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.testes;

import javax.persistence.EntityManager;

/**
 *
 * @author desenvolvedor
 */
public interface ItfTesteJunitSBPersistencia {

    public EntityManager getEMTeste();

    public EntityManager renovarConexao();

}
