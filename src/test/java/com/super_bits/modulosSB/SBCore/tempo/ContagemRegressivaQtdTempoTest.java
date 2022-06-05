/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.tempo;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.UtilSBCoreReflexaoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.tempo.ContagemRegressivaQtdTempo;
import com.super_bits.modulosSB.SBCore.modulos.tempo.FabTipoQuantidadeTempo;
import com.super_bits.modulosSB.SBCore.modulos.tempo.QuantidadeTempo;
import com.super_bits.modulosSB.SBCore.modulos.tempo.TipoQuantidadeTempo;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author salvioF
 */
public class ContagemRegressivaQtdTempoTest {

    public ContagemRegressivaQtdTempoTest() {
    }

    private void testeQuantidadeTesteSimples(QuantidadeTempo pQuantidadeTempo) {
        // asserts refentes a esta quantidade de tempo:
        //  correspontente                      1 ano, 2 meses, 16 dias, 4 horas, 2 minutos, 15 segundos
        //  caso de ignorar semana = a false:   1 ano, 2 meses, 2 semanas ,2 dias, 4 horas, 2 minutos, 15 segundos
        //

        FabTipoQuantidadeTempo divisorMaximo = pQuantidadeTempo.getDivisorMaximo();
        String infoTeste = "Calculando " + pQuantidadeTempo.getTipoQuantidade().getNomePlural() + " com divisor Maximo " + divisorMaximo.toString();

        // Em cada caso do tipo da quantidade, faz um assert para cada tipo de divisor maximo, com o resultado esperado
        switch (pQuantidadeTempo.getTipoQuantidade().getTipoInformacao()) {
            //  correspontente                      1 ano, 2 meses, 16 dias, 4 horas, 2 minutos, 15 segundos
            //  caso de ignorar semana = a false:   1 ano, 2 meses, 2 semanas ,2 dias, 4 horas, 2 minutos, 15 segundos
            case ANOS:
                switch (divisorMaximo) {
                    case ANOS:
                        assertEquals("Esperado um resultado diferente  :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 1);
                        break;
                    case MESES:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    case SEMANAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    case DIAS:
                        if (pQuantidadeTempo.isIgnorarSemana()) {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        } else {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        }
                        break;
                    case HORAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    case MINUTOS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    case SEGUNDOS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    default:
                        throw new AssertionError(divisorMaximo.name());

                }

                break;
            //  correspontente                      1 ano, 2 meses, 16 dias, 4 horas, 2 minutos, 15 segundos
            //  caso de ignorar semana = a false:   1 ano, 2 meses, 2 semanas ,2 dias, 4 horas, 2 minutos, 15 segundos
            case MESES:
                switch (divisorMaximo) {
                    case ANOS:
                        assertEquals("Esperado um resultado diferente  :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 2);
                        break;
                    case MESES:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 14);
                        break;
                    case SEMANAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    case DIAS:
                        if (pQuantidadeTempo.isIgnorarSemana()) {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        } else {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        }
                        break;
                    case HORAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    case MINUTOS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    case SEGUNDOS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    default:
                        throw new AssertionError(divisorMaximo.name());
                }
                break;
            //  correspontente                      1 ano, 2 meses, 16 dias, 4 horas, 2 minutos, 15 segundos
            //  caso de ignorar semana = a false:   1 ano, 2 meses, 2 semanas ,2 dias, 4 horas, 2 minutos, 15 segundos
            case SEMANAS:
                if (pQuantidadeTempo.isIgnorarSemana()) {
                    throw new UnsupportedOperationException("Ao construir uma quantidade do tipo semana o isignorar semana deve ser setado como FALSE (implementar isso no contructor, e no set ignoraraSemana da quantidade)");
                }
                switch (divisorMaximo) {
                    case ANOS:
                        assertEquals("Esperado um resultado diferente  :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 2);
                        break;
                    case MESES:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 2);
                        break;
                    case SEMANAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 62);
                        break;
                    case DIAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    case HORAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    case MINUTOS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    case SEGUNDOS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    default:
                        throw new AssertionError(divisorMaximo.name());
                }
                break;
            //  correspontente                      1 ano, 2 meses, 16 dias, 4 horas, 2 minutos, 15 segundos
            //  caso de ignorar semana = a false:   1 ano, 2 meses, 2 semanas ,2 dias, 4 horas, 2 minutos, 15 segundos
            case DIAS:
                switch (divisorMaximo) {
                    case ANOS:
                        if (pQuantidadeTempo.isIgnorarSemana()) {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 16);
                        } else {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 2);
                        }
                        break;
                    case MESES:
                        if (pQuantidadeTempo.isIgnorarSemana()) {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 16);
                        } else {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 2);
                        }
                        break;
                    case SEMANAS:
                        if (pQuantidadeTempo.isIgnorarSemana()) {
                            throw new UnsupportedOperationException("Ao setar uma base do tipo semana o is ignorar semana da quantidade deve ser setado como FALSE (implementar isso no contructor, e no sert ignoraraSemana da quantidade)");
                        } else {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 2);
                        }
                        break;
                    case DIAS:
                        if (pQuantidadeTempo.isIgnorarSemana()) {
                            //356 (um ano)+60 (2 meses) + 16 dias
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 432);
                        } else {
                            throw new UnsupportedOperationException("Ao setar uma base do tipo DIA o is ignorar semana da quantidade deve ser setado como  (implementar isso no contructor, no SET BASE MAXIMA ,e no sert ignoraraSemana da quantidade)");
                        }
                        break;
                    case HORAS:
                        if (pQuantidadeTempo.isIgnorarSemana()) {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        } else {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        }
                        break;
                    case MINUTOS:
                        if (pQuantidadeTempo.isIgnorarSemana()) {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        } else {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        }
                        break;
                    case SEGUNDOS:
                        if (pQuantidadeTempo.isIgnorarSemana()) {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        } else {
                            assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        }
                        break;
                    default:
                        throw new AssertionError(divisorMaximo.name());

                }
                break;
            //  correspontente                      1 ano, 2 meses, 16 dias, 4 horas, 2 minutos, 15 segundos
            //  caso de ignorar semana = a false:   1 ano, 2 meses, 2 semanas ,2 dias, 4 horas, 2 minutos, 15 segundos
            case HORAS:
                switch (divisorMaximo) {
                    case ANOS:
                        assertEquals("Esperado um resultado diferente  :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 4);
                        break;
                    case MESES:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 4);
                        break;
                    case SEMANAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 4);
                        break;
                    case DIAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 4);

                        break;
                    case HORAS:
                        //356x24 (um ano)+ 60 x 24 + 4
                        long emHoras = (Long.parseLong("356") * Long.parseLong("24")) + (Long.parseLong("60") * 24) + (Long.parseLong("16") * 24) + 4;
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), emHoras);
                        break;
                    case MINUTOS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    case SEGUNDOS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    default:
                        throw new AssertionError(divisorMaximo.name());

                }
                break;
            //  correspontente                      1 ano, 2 meses, 16 dias, 4 horas, 2 minutos, 15 segundos
            //  caso de ignorar semana = a false:   1 ano, 2 meses, 2 semanas ,2 dias, 4 horas, 2 minutos, 15 segundos
            case MINUTOS:
                switch (divisorMaximo) {
                    case ANOS:
                        assertEquals("Esperado um resultado diferente  :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 2);
                        break;
                    case MESES:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 2);
                        break;
                    case SEMANAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 2);
                        break;
                    case DIAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 2);

                        break;
                    case HORAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 2);
                        break;
                    case MINUTOS:
                        //em horas vezes 60
                        long emMinutos = ((Long.parseLong("356") * Long.parseLong("24")) + (Long.parseLong("60") * 24) + (Long.parseLong("16") * 24) + 4) * 60;
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), emMinutos);
                        break;
                    case SEGUNDOS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 0);
                        break;
                    default:
                        throw new AssertionError(divisorMaximo.name());

                }
                break;
            //  correspontente                      1 ano, 2 meses, 16 dias, 4 horas, 2 minutos, 15 segundos
            //  caso de ignorar semana = a false:   1 ano, 2 meses, 2 semanas ,2 dias, 4 horas, 2 minutos, 15 segundos
            case SEGUNDOS:
                switch (divisorMaximo) {
                    case ANOS:
                        assertEquals("Esperado um resultado diferente  :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 15);
                        break;
                    case MESES:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 15);
                        break;
                    case SEMANAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 15);
                        break;
                    case DIAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 15);
                        break;
                    case HORAS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 15);
                        break;
                    case MINUTOS:
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), 15);
                        break;
                    case SEGUNDOS:
                        // em Minutos x 60
                        long emSegundos = 60 * (((Long.parseLong("356") * Long.parseLong("24")) + (Long.parseLong("60") * 24) + 4) * 60);
                        assertEquals("Esperado um resultado diferente :( em:" + infoTeste, pQuantidadeTempo.getQuantidade(), emSegundos);
                        break;
                    default:
                        throw new AssertionError(divisorMaximo.name());

                }
                break;
            default:
                throw new AssertionError(pQuantidadeTempo.getTipoQuantidade().getTipoInformacao().name());

        }
    }

    @Test
    public void testGetQuantidadeTempo() {

        // Setar uma variavel Long, correspontente a 1 ano, 2 meses, 16 dias, 4 horas, 2 minutos, 15 segundos
        //  e em caso de ignorar semana = a false:   1 ano, 2 meses, 2 semanas ,2 dias, 4 horas, 2 minutos, 15 segundos
        //                                  ************ Obs: 16 dias se tornaram 2 semanas e 2 dias no segundo caso *******************
//        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
//        DateTime datainicial = dtf.parseDateTime("01/01/2016 00:00:00");
//        DateTime dataFinal = dtf.parseDateTime("17/03/2017 04:02:15");
        long umanoMileSegundos = 365l * 24l * 60l * 60l * 1000l;
        long doisMesesMilesegundos = 2l * 30l * 24l * 60l * 60l * 1000l;
        //long dezesseisDiasMilesegundos = 16l * 30l * 24l * 60l * 60l * 1000l;
        long dezesseisDiasMilesegundos = 16l * 24l * 60l * 60l * 1000l;
        long quatrohorasMilesegundos = 4l * 60l * 60l * 1000l;
        long doisMinutosMilesegundos = 2l * 60l * 1000l;
        long quinseSegundosMilesegundo = 15l * 1000l;
        Long diferenca = umanoMileSegundos
                + doisMesesMilesegundos + dezesseisDiasMilesegundos + quatrohorasMilesegundos
                + doisMinutosMilesegundos + quinseSegundosMilesegundo;

        long valorVariavel;
        valorVariavel = diferenca;

        long diffSeconds = valorVariavel / 1000 % 60;
        long diffMinutes = valorVariavel / (60 * 1000) % 60;
        long diffHours = valorVariavel / ((60 * 60 * 1000) - 1) % 24;
        long diffDays = valorVariavel / (24 * 60 * 60 * 1000);
//        long difDaysMOD30 = valorVariavel / (24 * 60 * 60 * 1000) % 30; // somente se precisar de dias dentro do período de um mês
//        long diffMonthsTotal = (valorVariavel / (1000 * 60 * 60 * 24) / 30);
//        long difMonthsMOD12 = (valorVariavel / (1000 * 60 * 60 * 24) / 30) % 12; // somente se precisar de meses dentro do período de um ano
//        long difYearsTotal = ((valorVariavel / (1000 * 60 * 60 * 24) / 30) / 12);

        System.out.print(diffDays + " days, ");
        System.out.print(diffHours + " hours, ");
        System.out.print(diffMinutes + " minutes, ");
        System.out.print(diffSeconds + " seconds.");

        long resultadoSeg = UtilSBCoreDataHora.quantidadeTempoEmSegundos(valorVariavel, FabTipoQuantidadeTempo.ANOS);
        long resultadoDiasFalse = UtilSBCoreDataHora.quantidadeTempoEmDias(valorVariavel, FabTipoQuantidadeTempo.ANOS, true);
        long resultadoHoras = UtilSBCoreDataHora.quantidadeTempoEmHoras(valorVariavel, FabTipoQuantidadeTempo.ANOS);
        long resultadoMeses = UtilSBCoreDataHora.quantidadeTempoEmMeses(valorVariavel, FabTipoQuantidadeTempo.ANOS);
        long resultadoMinutos = UtilSBCoreDataHora.quantidadeTempoEmMinutos(valorVariavel, FabTipoQuantidadeTempo.ANOS);
        long resultadoSegundos = UtilSBCoreDataHora.quantidadeTempoEmSegundos(valorVariavel, FabTipoQuantidadeTempo.ANOS);
        long resultadoAnos = UtilSBCoreDataHora.quantidadeTempoEmAnos(valorVariavel, FabTipoQuantidadeTempo.ANOS);

        // Valor variável irá ser utilizado para setar o valor da diferença em milisegundos em QuantidadeTempo e o cálculo deste valor está feito acima e testado
        // Testes Iniciais (Testando o Objeto Quantidade Tempo)...
        QuantidadeTempo quantidadeEmAnos = new QuantidadeTempo(valorVariavel, FabTipoQuantidadeTempo.ANOS);
        testeQuantidadeTesteSimples(quantidadeEmAnos);

        QuantidadeTempo quantidadeEmMeses = new QuantidadeTempo(valorVariavel, FabTipoQuantidadeTempo.MESES);
        testeQuantidadeTesteSimples(quantidadeEmMeses);

        QuantidadeTempo quantidadeEmSemanas = new QuantidadeTempo(valorVariavel, FabTipoQuantidadeTempo.SEMANAS);
        testeQuantidadeTesteSimples(quantidadeEmSemanas);

        QuantidadeTempo quantidadeEmDiass = new QuantidadeTempo(valorVariavel, FabTipoQuantidadeTempo.DIAS.getTipoQuantidade(), FabTipoQuantidadeTempo.SEMANAS); // O construtor foi alterado para que o retorno fosse 2 e não 16 quando a quantidade for dias e o divisor for semanas
        //QuantidadeTempo quantidadeEmDiass = new QuantidadeTempo(valorVariavel, FabTipoQuantidadeTempo.DIAS);
        testeQuantidadeTesteSimples(quantidadeEmDiass);

        QuantidadeTempo quantidadeEmHoras = new QuantidadeTempo(valorVariavel, FabTipoQuantidadeTempo.HORAS);
        testeQuantidadeTesteSimples(quantidadeEmHoras);

        QuantidadeTempo quantidadeEmMinutos = new QuantidadeTempo(valorVariavel, FabTipoQuantidadeTempo.MINUTOS);
        testeQuantidadeTesteSimples(quantidadeEmMinutos);

        QuantidadeTempo quantidadeEmSegundos = new QuantidadeTempo(valorVariavel, FabTipoQuantidadeTempo.SEGUNDOS);
        testeQuantidadeTesteSimples(quantidadeEmSegundos);

        quantidadeEmDiass.setIgnorarSemana(false);

        testeQuantidadeTesteSimples(quantidadeEmDiass);

        /// criando array de quantidades para testes de DivisorMaximo
        List<QuantidadeTempo> quantidadesATestar = new ArrayList<>();

        quantidadesATestar.add(quantidadeEmAnos);
        quantidadesATestar.add(quantidadeEmMeses);
        quantidadesATestar.add(quantidadeEmSemanas);
        quantidadesATestar.add(quantidadeEmDiass);
        quantidadesATestar.add(quantidadeEmHoras);
        quantidadesATestar.add(quantidadeEmMinutos);
        quantidadesATestar.add(quantidadeEmSegundos);

        //para cada quantidade, muda o divisor maximo e testa novamente
        for (QuantidadeTempo qtd : quantidadesATestar) {
            List<TipoQuantidadeTempo> tiposQuantidade = UtilSBCoreReflexaoFabrica.getListaTodosRegistrosDaFabrica(FabTipoQuantidadeTempo.class);
            for (TipoQuantidadeTempo tipoQuantidadeTempo : tiposQuantidade) {
                qtd.setDivisorMaximo(tipoQuantidadeTempo.getTipoInformacao());
                testeQuantidadeTesteSimples(qtd);
            }
        }

        /// SEegunda etapa de testes, teste do objeto contagem regressiva
        ContagemRegressivaQtdTempo contagem = new ContagemRegressivaQtdTempo(valorVariavel);
        contagem.setQuantidadeInformacao(6);

        assertEquals("A contagem regressiva deve retonar 6 valores", contagem.getQuantidadesTempo().size(), 6);
        QuantidadeTempo ano = contagem.getQuantidadesTempo().get(0);
        assertEquals("O tipo da primeira quantidade esperada deveria ser anual", ano.getTipoQuantidade().getTipoInformacao(), FabTipoQuantidadeTempo.ANOS);
        QuantidadeTempo meses = contagem.getQuantidadesTempo().get(1);
        assertEquals("O tipo da 2 quantidade esperada deveria ser meses", ano.getTipoQuantidade().getTipoInformacao(), FabTipoQuantidadeTempo.MESES);
        QuantidadeTempo dias = contagem.getQuantidadesTempo().get(2);
        assertEquals("O tipo da 3 quantidade esperada deveria ser dias", ano.getTipoQuantidade().getTipoInformacao(), FabTipoQuantidadeTempo.DIAS);
        QuantidadeTempo horas = contagem.getQuantidadesTempo().get(3);
        assertEquals("O tipo da 4 quantidade esperada deveria ser horas", ano.getTipoQuantidade().getTipoInformacao(), FabTipoQuantidadeTempo.HORAS);
        QuantidadeTempo minutos = contagem.getQuantidadesTempo().get(4);
        assertEquals("O tipo da 5 quantidade esperada deveria ser minutos", ano.getTipoQuantidade().getTipoInformacao(), FabTipoQuantidadeTempo.MINUTOS);
        QuantidadeTempo segundos = contagem.getQuantidadesTempo().get(5);
        assertEquals("O tipo da 6 quantidade esperada deveria ser segundos", ano.getTipoQuantidade().getTipoInformacao(), FabTipoQuantidadeTempo.SEGUNDOS);

        assertEquals("A quantidade de anos não foi a esperada", ano.getQuantidade(), 1);
        assertEquals("A quantidade de anos não foi a esperada", meses.getQuantidade(), 2);
        assertEquals("A quantidade de anos não foi a esperada", dias.getQuantidade(), 16);
        assertEquals("A quantidade de anos não foi a esperada", horas.getQuantidade(), 4);
        assertEquals("A quantidade de anos não foi a esperada", minutos.getQuantidade(), 2);
        assertEquals("A quantidade de anos não foi a esperada", segundos.getQuantidade(), 15);

        contagem.setDividirDiasEmSemanas(true);

        // Agora o mesmo calculo adicionando as semanas
        contagem.setDividirDiasEmSemanas(true);

    }

}
