/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

/**
 *
 * @author salvioF
 */
public enum FabTipoProjeto {

    /**
     * Possui todas as interfaces model e ações do sistema
     */
    MODEL,
    /**
     * Implementa o Hibernate, e a efetivação das ações do sistema Importa model
     * E as interfaces do model
     */
    MODEL_E_CONTROLLER,
    /**
     * Implementa o Hibernate, e a efetivação das ações do sistema Importa model
     */
    CONTROLLER,
    /**
     * Implementa controller, e abre uma porta para comunicação via API
     */
    CONTROLLER_SERVER,
    /**
     * Acessa o controller Sercer
     */
    CONTROLLER_CLIENT,
    /**
     *
     * Cria uma porta o serviço de controller
     */
    WEB_SERVICE,
    /**
     * Exibe formularios Web para gestão da aplicação
     */
    WEB_APP,
    /**
     * Exibe os requisitos do sistema, pode importar controollerServer ou
     * Controoler client
     */
    REQUISITOS_WEB_APP,
    BIBLIOTECA

}
