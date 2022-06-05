/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * Classe abstrata para consumo de WebService
 *
 *
 * @author Salvio
 * @param <T> Interface do WebService
 */
@Deprecated
public abstract class SBWS_Client<T> {

    protected String getUrl() {
        return "http://" + getDnsName() + ":" + getporta().toString() + "/ws/" + getNomeServico();
    }

    protected String getUrlWsdl() {
        return getUrl() + "?wsdl";

    }

    protected String getNomeServico() {
        return getInterface().getSimpleName().replace("Itf", "");
    }

    private String buildNameSpace() {
        String resp = "http://";

        String[] caminho = getInterface().getCanonicalName().split("\\.");

        for (int i = caminho.length - 2; i >= 0; i--) {
            if (i < caminho.length - 2) {
                resp = resp + ".";
            }
            resp = resp + caminho[i];

        }
        return resp + "/";
    }

    public T getServico() {
        URL url;
        try {
            url = new URL(getUrlWsdl());
            System.out.println(getInterface().getCanonicalName());

            QName qname = new QName(buildNameSpace(), getNomeServico() + "Service");
            Service service = Service.create(url, qname);
            return (T) service.getPort(getInterface());

        } catch (Exception ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando Serivico Para:", ex);

        }
        return null;
    }

    public abstract Class getInterface();

    public abstract String getDnsName();

    public abstract Integer getporta();

}
