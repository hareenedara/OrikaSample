package com.samples.model;

import lombok.Data;

@Data
public class OriginalMonies {
    private Double amount;
    private String type;
    private Identifier identifier;
}
