/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converte TimeStamp padrão:
 *
 * ex: "20/12/2014 14:30:59"
 *
 * @author Salvio
 */
public class XmlPrAdaptadorTimeStampPadrao extends XmlAdapter<String, Date> {

    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    @Override
    public Date unmarshal(String v) throws Exception {
        try {
            return formato.parse(v);
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro convertendo" + v, e);

            return null;
        }
    }

    @Override
    public String marshal(Date v) throws Exception {
        try {
            return formato.format(v);
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro convertendo" + v, e);
            return null;
        }
    }

}
