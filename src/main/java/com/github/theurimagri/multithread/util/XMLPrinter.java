package com.github.theurimagri.multithread.util;

import com.github.theurimagri.multithread.model.SaleCounter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public final class XMLPrinter {

    private XMLPrinter() {
    }

    public static void print(SaleCounter saleCounter)
    {
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(SaleCounter.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(saleCounter, sw);

            String xmlContent = sw.toString();
            System.out.println( xmlContent );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
