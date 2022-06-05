/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.tempo;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;

/**
 *
 * @author salvioF
 */
public enum FabTipoQuantidadeTempo implements ItfFabrica {

    ANOS,
    MESES,
    SEMANAS,
    DIAS,
    HORAS,
    MINUTOS,
    SEGUNDOS;

    public TipoQuantidadeTempo getTipoQuantidade() {

        TipoQuantidadeTempo tipoQuantidade = new TipoQuantidadeTempo();
        tipoQuantidade.setTipoInformacao(this);
        switch (this) {
            case ANOS:
                tipoQuantidade.setNomePlural("Anos");
                tipoQuantidade.setNomeSingular("Ano");
                break;
            case MESES:
                tipoQuantidade.setNomePlural("Meses");
                tipoQuantidade.setNomeSingular("Mês");
                break;
            case SEMANAS:
                tipoQuantidade.setNomeSingular("Semanas");
                tipoQuantidade.setNomePlural("Semanas");
                break;
            case DIAS:
                tipoQuantidade.setNomeSingular("Dia");
                tipoQuantidade.setNomePlural("Dias");
                break;
            case HORAS:
                tipoQuantidade.setNomeSingular("Hora");
                tipoQuantidade.setNomePlural("Horas");
                break;
            case MINUTOS:
                tipoQuantidade.setNomePlural("Minutos");
                tipoQuantidade.setNomeSingular("Minuto");
                break;
            case SEGUNDOS:
                tipoQuantidade.setNomeSingular("Segundo");
                tipoQuantidade.setNomePlural("Segundos");
                break;
            default:
                throw new AssertionError(this.name());

        }
        return tipoQuantidade;
    }

    /**
     *
     * @param pMedidaTempoCOmparada Medida que será comparada
     * @return 0 se igual, -1 se inferior a medida comparada, e 1 se superior a
     * medida comparada
     */
    public int maiorQueMedidaDeTempo(FabTipoQuantidadeTempo pMedidaTempoCOmparada) {

        Integer idThis = this.ordinal();
        Integer idMedidaComparada = pMedidaTempoCOmparada.ordinal();

        if (idThis.equals(pMedidaTempoCOmparada.ordinal())) {
            return 0;
        }
        if (idThis > idMedidaComparada) {
            return 1;
        } else {
            return -1;
        }

    }

    public long calcularQuantidade(Long valor, FabTipoQuantidadeTempo divisorMaximo, boolean ignorarSemanas) {

        switch (this) {
            case ANOS:
                // A base de Calculos sempre será anual
                switch (divisorMaximo) {
                    case ANOS:
                        return UtilSBCoreDataHora.quantidadeTempoEmAnos(valor, divisorMaximo);
                    //return UtilSBCoreDataHora.intervaloTempoAnos(valor);
                    case MESES:
                    case SEMANAS:
                    case DIAS:
                    case HORAS:
                    case MINUTOS:
                    case SEGUNDOS:
                        return 0L;
                    default:
                        return 0L;
                }
            case MESES:
                // Caso a base de calulos seja abaixo de Anos, não dividir por 12
                switch (divisorMaximo) {
                    case ANOS:
                        //return UtilSBCoreDataHora.quantidadeTempoEmMeses(valor, FabTipoQuantidadeTempo.ANOS);
                        return UtilSBCoreDataHora.intervaloTempoMeses(valor) % 12L; // optei por utilizar o cálculo antigo devido ao retorno inesperado no calculo quantidadeTempoMeses acima
                    case MESES:
                        return UtilSBCoreDataHora.quantidadeTempoEmMeses(valor, divisorMaximo);
                    //return UtilSBCoreDataHora.intervaloTempoMeses(valor);
                    default:
                        return 0L;
                }

            case SEMANAS:
                //Caso a base de calculos seja abaixo de meses não dividir o mês em semanas
                //caso ignorar semanas, retornar -1
                switch (divisorMaximo) {
                    case ANOS://2
                        return UtilSBCoreDataHora.quantidadeTempoEmSemanas(valor, FabTipoQuantidadeTempo.ANOS);
                    case MESES://2
                    //return UtilSBCoreDataHora.quantidadeTempoEmSemanas(valor, FabTipoQuantidadeTempo.MESES);
                    case SEMANAS:
                        //return UtilSBCoreDataHora.intervaloTempoSemanas(valor
                        UtilSBCoreDataHora.quantidadeTempoEmSemanas(valor, divisorMaximo);
                    default:
                        return 0L;
                }

            case DIAS:
                //caso a base de calculo for :
                //Anos: dividir apenas por 365
                //Meses:
                //Semanas:
                //dias:
                //Minutos:
                //Segundos:
                switch (divisorMaximo) {
                    case ANOS:
                        //long diasAno = 365L;
                        //long semanasAno = 52L;
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365L) % 30L; // 16
                    case MESES:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365L) % 30L; // 16
                    case SEMANAS:
                    //return (UtilSBCoreDataHora.intervaloTempoSemanas(valor) - 52L) % 3; // 2
                    case DIAS:
                        return UtilSBCoreDataHora.intervaloTempoDias(valor);
                    default:
                        return 0L;
                }
            case HORAS:
                //caso a base de calculo for :
                //Anos: dividir apenas por 365
                //Meses:
                //Semanas:
                //dias:
                //Minutos:  (não dividir por minutos)
                //Segundos: (dividir por tudo)
                switch (divisorMaximo) {
                    case ANOS:
                        //Long diasAno = 365L; dias em um ano
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365L) % 24L; //4
                    case MESES:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365L) % 24L; //4
                    case SEMANAS:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365L) % 24L; //4
                    case DIAS:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365L) % 24L; //4
                    case HORAS:
                        return UtilSBCoreDataHora.intervaloTempoHoras(valor);

                    default:
                        return 0L;

                }
            case MINUTOS:
                //caso a base de calculo for :
                //Anos: dividir apenas por 365
                //Meses:
                //Semanas:
                //dias:
                //Minutos:
                //Segundos:
                switch (divisorMaximo) {
                    case ANOS: // 2
                        // Long minutosAno = 525600L; valor referência para somente um ano
                        return (UtilSBCoreDataHora.intervarlTempoMinutos(valor) - 525600L) % 12; //2
                    case MESES:
                        return (UtilSBCoreDataHora.intervarlTempoMinutos(valor) - 525600L) % 12; //2
                    case SEMANAS:
                        return (UtilSBCoreDataHora.intervarlTempoMinutos(valor) - 525600L) % 12; //2
                    case DIAS:
                        return (UtilSBCoreDataHora.intervarlTempoMinutos(valor) - 525600L) % 12; //2
                    case HORAS:
                        return (UtilSBCoreDataHora.intervarlTempoMinutos(valor) - 525600L) % 12; //2
                    case MINUTOS:
                        return UtilSBCoreDataHora.intervarlTempoMinutos(valor);
                    default:
                        return 0L;
                }

            case SEGUNDOS:
                //caso a base de calculo for :
                //Anos: dividir apenas por 365
                //Meses:
                //Semanas:
                //dias:
                //Minutos:
                //Segundos:
                switch (divisorMaximo) {
                    case ANOS: // 15
                        //Long segundosAno = 31536000L; // valor referência de segundos somente para um ano
                        return (UtilSBCoreDataHora.intervaloTempoSegundos(valor) - 31536000L) % 60; //15
                    case MESES:
                        return (UtilSBCoreDataHora.intervaloTempoSegundos(valor) - 31536000L) % 60; //15
                    case SEMANAS:
                        return (UtilSBCoreDataHora.intervaloTempoSegundos(valor) - 31536000L) % 60; //15
                    case DIAS:
                        return (UtilSBCoreDataHora.intervaloTempoSegundos(valor) - 31536000L) % 60; //15
                    case HORAS:
                        return (UtilSBCoreDataHora.intervaloTempoSegundos(valor) - 31536000L) % 60; //15
                    case MINUTOS:
                        return (UtilSBCoreDataHora.intervaloTempoSegundos(valor) - 31536000L) % 60; //15
                    case SEGUNDOS:
                        return UtilSBCoreDataHora.intervaloTempoSegundos(valor);
                    default:
                        return 0L;

                }

            default:
                throw new AssertionError(this.name());
        }

    }

    @Override
    public TipoQuantidadeTempo getRegistro() {
        return getTipoQuantidade();
    }

}
