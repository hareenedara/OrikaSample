package com.samples;

import com.samples.converter.DateConverter;
import com.samples.dto.InvoiceDTO;
import com.samples.model.CashAspect;
import com.samples.model.Identifier;
import com.samples.model.Invoice;
import com.samples.model.OriginalMonies;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main{

    public static void main(String[] args) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(new DateConverter());
        mapperFactory.classMap(Invoice.class, InvoiceDTO.class)
                .field("invoiceNumber","invoiceNo")
                .field("date","dateSent")
                .field("cashAspect.originalMonies{type}","cashAspects{type}")
                .field("cashAspect.originalMonies{amount}", "cashAspects{amount}")
                .field("cashAspect.originalMonies{identifier.value}","cashAspects{id}")
                .field("alternateIds{value}", "subClientId")
                .byDefault()
                .register();

        BoundMapperFacade<Invoice,InvoiceDTO> boundMapperFacade = mapperFactory.getMapperFacade(Invoice.class, InvoiceDTO.class);
        InvoiceDTO dto = boundMapperFacade.map(prepareInvoice());

        Invoice invoiceReverse = boundMapperFacade.mapReverse(dto);
        System.out.println(dto.getInvoiceNo());

        main2(dto);
        //System.out.println(dto.getDateSent());
    }

    public static void main2(InvoiceDTO dto1) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(InvoiceDTO.class,InvoiceDTO.class)
                .byDefault()
                .register();

        BoundMapperFacade<InvoiceDTO,InvoiceDTO> boundMapperFacade = mapperFactory.getMapperFacade(InvoiceDTO.class,
                InvoiceDTO.class);
        InvoiceDTO dto2 = boundMapperFacade.map(dto1);
        //dto2.setDateSent("0000");
    }


    public static Invoice prepareInvoice(){
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber("inv-1");
        invoice.setDate(new Date());

        // sub client
        List<Identifier> altIds = new ArrayList<>();
        Identifier altId = new Identifier();
        altId.setValue("29210-1");
        altIds.add(altId);
        invoice.setAlternateIds(altIds);

        CashAspect cashAspect = new CashAspect();
        OriginalMonies om = new OriginalMonies();
        om.setAmount(99.99);
        om.setType("First");
        Identifier id = new Identifier();
        id.setValue("first-id");
        om.setIdentifier(id);
        List<OriginalMonies> oms = new ArrayList<>();
        oms.add(om);
        cashAspect.setOriginalMonies(oms);
        invoice.setCashAspect(cashAspect);
        return invoice;
    }

}
