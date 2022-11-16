//package com.samtuga.rmufilemanagement.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.samtuga.rmufilemanagement.entity.IncomingCorrespondence;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StringToUserConverter implements Converter<String, IncomingCorrespondence> {
//    @Autowired
//    private ObjectMapper objectMapper;
//    @Override
//    @SneakyThrows
//    public IncomingCorrespondence convert(String source) {
//        return objectMapper.convertValue(source,IncomingCorrespondence.class);
//    }
//}
