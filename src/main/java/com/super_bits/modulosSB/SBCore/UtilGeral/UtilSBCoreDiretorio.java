/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.google.common.collect.Lists;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreDiretoriosSimples;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreDiretorio extends UtilSBCoreDiretoriosSimples {

    private static List<String> getDiretoriosRecursivoOrdemMaoirArvore(List<String> listaAntiga, File pDiretorio) {
        boolean raiz = false;
        if (listaAntiga == null) {
            listaAntiga = new ArrayList<>();
            raiz = true;
            listaAntiga.add(pDiretorio.getAbsolutePath());
        }
        List<String> diretorios = new ArrayList<>();
        if (!pDiretorio.isDirectory()) {
            throw new UnsupportedOperationException("O caminho enviado não é um diretorio");
        }

        FileFileFilter filtro = new FileFileFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir, name).isDirectory(); //To change body of generated methods, choose Tools | Templates.
            }

        };

        String[] subdirs = pDiretorio.list(filtro);
        if (subdirs.length > 0) {
            for (String subdir : subdirs) {
                System.out.println("Encontrado:" + subdir);
                listaAntiga.add(pDiretorio.getAbsolutePath() + "/" + subdir);
                listaAntiga.addAll(getDiretoriosRecursivoOrdemMaoirArvore(listaAntiga, new File(pDiretorio.getAbsolutePath() + "/" + subdir)));
            }
        } else {
            return new ArrayList<>();
        }

        if (raiz) {
            return Lists.reverse(listaAntiga);
        } else {
            return new ArrayList<>();
        }

    }

    public static List<String> getDiretoriosRecursivoOrdemMaoirArvore(File pDiretorio) {

        return getDiretoriosRecursivoOrdemMaoirArvore(null, pDiretorio);

    }

}
