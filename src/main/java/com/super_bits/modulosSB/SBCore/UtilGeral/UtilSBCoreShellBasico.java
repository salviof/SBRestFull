/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.coletivojava.fw.utilCoreBase.UtilSBCoreDiretoriosSimples;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * Funcções basicas relativas ao Shell, para funções mais avançadas importe o
 * SuperBits Shell Command
 *
 * @author desenvolvedor
 */
public abstract class UtilSBCoreShellBasico {

    public static String executeCommand(String... pComando) {
        return executeCommand(true, pComando);
    }

    public static String executeCommand(boolean detectarDiretorioExecucao, String... pComando) {
        System.out.println("Executando comando" + Arrays.toString(pComando));
        StringBuilder output = new StringBuilder();

        Process processo;
        try {

            if (detectarDiretorioExecucao && pComando[0].split("/").length > 1) {
                if (pComando.length > 1) {
                    throw new UnsupportedOperationException("A execução detectando diretorio de execução só é permitida para chamada de uma arquivo de Script específica, portando não suporta multiplas linhas de comando");
                }
                String diretorio = UtilSBCoreDiretoriosSimples.getDiretorioArquivo(pComando[0]) + "/";
                String comando = UtilSBCoreDiretoriosSimples.getNomeArquivo(pComando[0]);
                processo = Runtime.getRuntime().exec("/bin/bash ./" + comando, null, new File(diretorio));

            } else {
                processo = Runtime.getRuntime().exec(pComando);
            }
            processo.waitFor();
            int valorSaida = processo.exitValue();
            if (valorSaida != 0) {
                System.out.println("Ouve um erro executando,saida" + valorSaida + " para o comando" + Arrays.toString(pComando));
                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(processo.getErrorStream()));

                String line;

                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                    System.out.println(line);
                }
                throw new UnsupportedOperationException("ERRO executando script:[" + Arrays.toString(pComando) + "] ->" + output.toString());

            } else {
                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(processo.getInputStream()));

                String line;

                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                    System.out.println(line);
                }
            }
        } catch (IOException | InterruptedException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, Arrays.toString(pComando), e);
        }

        return output.toString();

    }

    public static void abrirScriptEmConsole(String... pScripteParametros) {

        File arquivo = new File(pScripteParametros[0]);
        String comandoCompleto = "";
        int i = 0;
        for (String linha : pScripteParametros) {
            if (i > 0) {
                comandoCompleto += " " + linha;
            } else {
                comandoCompleto = linha;
            }
            i++;
        }
        if (!arquivo.exists()) {
            throw new UnsupportedOperationException("O script " + arquivo + " não foi encontrado ");
        }
        System.out.println("Executando " + comandoCompleto + " em console");
        String command[] = {"/bin/sh", "-c",
            "gnome-terminal -e \"" + comandoCompleto + "\""};
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(command);

        } catch (Exception ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando executar" + pScripteParametros, ex);
        }

    }

}
