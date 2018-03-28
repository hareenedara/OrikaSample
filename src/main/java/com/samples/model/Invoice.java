package com.samples.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Invoice {
    private String invoiceNumber;
    private CashAspect cashAspect;
    private Date date;
    private List<Identifier> alternateIds;
}
