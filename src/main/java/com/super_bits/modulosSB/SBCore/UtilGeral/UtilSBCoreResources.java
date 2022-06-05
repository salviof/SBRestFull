package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
/**
 *
 * @author sfurbino
 */
public class UtilSBCoreResources {

    /**
     *
     * REtorna um arquivo do tipo File
     *
     * @param pCaminhoResource
     * @return
     */
    public static File getFile(String pCaminhoResource) {
        throw new UnsupportedOperationException("Ainda não implementado");
        // return new File(.getResource(pCaminhoResource).getFile(), "UTF-8"));
    }

    /**
     * Copia um arquivo do pacote jar para um destino externo
     *
     *
     * @param classeDoResource Classe referencia para encontrar o arquivo
     * (Qualquer classe que esteja dentro do mesmo pacote .Jar)
     * @param caminhoArquivoResource Caminho relativo a pasta resource
     * @param destinoArquivo destino onde o arquivo será copiado
     * @return
     */
    public static boolean criarArquivoResourceEmDestino(Class classeDoResource, String caminhoArquivoResource, String destinoArquivo) {
        return UtilSBCoreArquivos.copiarArquivoResourceJar(classeDoResource, caminhoArquivoResource, destinoArquivo);
    }

    /**
     *
     * ATENÇÃO-> EM MODO DE DEPURAÇÃO O HASH DA CLASSE APRESENTA VALOR ALEATÓRIO
     *
     * @param pClass A classe que deseja o hash
     * @return o hash do arquivo class encontrado no pacote. ou -1 caso algo
     * errado aconteça
     */
    public static long getHashCodeClasseDoPacote(Class pClass) {
        //  System.out.println(Logger.GLOBAL_LOGGER_NAME);
        try {
            if (pClass == null || pClass.getCanonicalName() == null) {
                return -1;
            }
            String caminhoArquivp = pClass.getCanonicalName().replaceAll("\\.", "/") + ".class";
            InputStream teste = pClass.getClassLoader().getResourceAsStream(caminhoArquivp);

            return Arrays.hashCode(IOUtils.toByteArray(teste));
        } catch (IOException ex) {
            Logger.getLogger(UtilSBCoreResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
