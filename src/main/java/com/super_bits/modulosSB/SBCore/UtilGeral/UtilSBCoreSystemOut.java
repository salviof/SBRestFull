/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreSystemOut {

    public static void exibirMensagemEmDestaque(String pMensagem) {

        System.out.println("############################################################################################");
        System.out.println("##");
        System.out.println("##");
        System.out.println("##");
        System.out.println("##" + UtilSBCoreStringFiltros.getRpad(pMensagem + "##", 40, " "));
        System.out.println("##");
        System.out.println("##");
        System.out.println("##");
        System.out.println("##");
        System.out.println("##");
        System.out.println("##");
        System.out.println("############################################################################################");

    }

    public static void primeirosTeses() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        //read a line from the console
        String lineFromInput;
        FileOutputStream file;
        try {
            file = new FileOutputStream("/home/desenvolvedor/teste.txt");
            TeePrintStream tee = new TeePrintStream(file, System.out);
            System.setOut(tee);

            String ANSI_RESET = "\u001B[0m";
            String ANSI_BLACK = "\u001B[30m";
            String ANSI_RED = "\u001B[31m";
            String ANSI_GREEN = "\u001B[32m";
            String ANSI_YELLOW = "\u001B[33m";
            String ANSI_BLUE = "\u001B[34m";
            String ANSI_PURPLE = "\u001B[35m";
            String ANSI_CYAN = "\u001B[36m";
            String ANSI_WHITE = "\u001B[37m";

            System.out.println(ANSI_RED + "Texto vermelho!" + ANSI_RESET);

            System.out.println("<a href=\"http://www.google.com.br\">Link q é bom nada!</a>\n"
                    + "");
            System.out.println("Teste");
        } catch (FileNotFoundException ex) {
            System.out.println("Erro");
        }
        throw new UnsupportedOperationException("teste");
    }

    private static class TeePrintStream extends PrintStream {

        private final PrintStream second;

        public TeePrintStream(OutputStream main, PrintStream second) {
            super(main);
            this.second = second;
        }

        /**
         * Closes the main stream. The second stream is just flushed but
         * <b>not</b> closed.
         *
         * @see java.io.PrintStream#close()
         */
        @Override
        public void close() {
            // just for documentation
            super.close();
        }

        @Override
        public void flush() {
            super.flush();
            second.flush();
        }

        @Override
        public void write(byte[] buf, int off, int len) {
            super.write(buf, off, len);
            second.write(buf, off, len);
        }

        @Override
        public void write(int b) {
            super.write(b);
            second.write(b);
        }

        @Override
        public void write(byte[] b) throws IOException {
            super.write(b);
            second.write(b);
        }
    }

}
