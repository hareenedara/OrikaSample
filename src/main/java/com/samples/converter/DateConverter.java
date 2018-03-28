package com.samples.converter;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateConverter extends BidirectionalConverter<Date,String> {

    String date_format = "yyyy-MM-dd";
    @Override
    public String convertTo(Date source, Type<String> destinationType, MappingContext mappingContext) {
        return source.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern
                (date_format));
    }

    @Override
    public Date convertFrom(String source, Type<Date> destinationType, MappingContext mappingContext) {
//        return Date.from(LocalDateTime.parse(source,DateTimeFormatter.ofPattern(date_format)).atZone(ZoneId.systemDefault
//                ()).toInstant());
        return new Date();
    }
}
