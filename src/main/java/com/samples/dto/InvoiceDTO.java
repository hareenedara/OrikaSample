package com.samples.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class InvoiceDTO {
    private String invoiceNo;
    private List<CashAspectDTO> cashAspects;
    @Getter(AccessLevel.PRIVATE)
    private String dateSent;
    private String subClientId;
}
