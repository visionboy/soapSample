package com.example.soap.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConverterController {

    @PostMapping(value = "/convert", consumes = "application/xml", produces = "application/json")
    public Object convertXmlToJson(@RequestBody String xmlInput) {
        try {
            // 1. XML을 읽기 위한 XmlMapper 객체 생성
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode node = xmlMapper.readTree(xmlInput.getBytes());

            // 2. JSON으로 출력하기 위한 ObjectMapper 객체 생성
            // ObjectMapper는 Spring Boot에서 기본적으로 Jackson을 사용하므로 
            // node를 그대로 반환하면 MappingJackson2HttpMessageConverter가 처리합니다.
            
            // 3. 객체 그대로 반환하면 Spring이 JSON으로 response 해줍니다.
            return node; 
            
        } catch (Exception e) {
            return "Error during conversion: " + e.getMessage();
        }
    }

    @PostMapping(value = "/convert2", consumes = "application/xml", produces = "application/json")
    public String convert2(@RequestBody String xmlInput) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        // 1. XML을 트리 구조로 읽기
        JsonNode node = xmlMapper.readTree(xmlInput);

        ObjectMapper jsonMapper = new ObjectMapper();
        // 2. JSON도 들여쓰기(Pretty Print) 적용하여 String으로 반환
        return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }
}
